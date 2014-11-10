package negotiation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;
import negotiation.coordinator.Issue;
import negotiation.db.Agent;
import negotiation.db.Attribute;
import negotiation.db.Product;
import negotiation.db.bean.AgentBeanLocal;
import negotiation.db.bean.ProductsBeanLocal;
import negotiation.db.bean.adaptor.Container;
import negotiation.db.bean.adaptor.ProductAdaptor;
import negotiation.tree.AttributeContainer;
import negotiation.tree.AttributeTree;
import negotiation.ws.AgentType;
import negotiation.ws.Candidate;
import negotiation.ws.ServerService_Service;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

@ManagedBean
@SessionScoped
public class ProductsController {
    @EJB
    private AgentBeanLocal agentBean;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Server/ServerService.wsdl")
    private ServerService_Service service;
    @EJB
    private ProductsBeanLocal productsBean;
    @ManagedProperty(value = "#{agentController.agent}")
    
    private Agent agent;
    private List<Product> products;
    private boolean insertable = false;
    private String newProduct;
    
    private Container<Product> currentProductContainer;
    private Attribute root; 
    private AttributeTree tree;
    
    private TagCloudModel attributeCloud;
    
    private String error;

    public String getError() {
        return error;
    }
    
    private List<Candidate> interlocutors;

    public List<Candidate> getInterlocutors() {
        return interlocutors;
    }
    
    public Attribute getRoot() {
        return root;
    }
    
    public Container getCurrentProductContainer() {
        return currentProductContainer;
    }

    public void setCurrentProductContainer(Container currentProductContainer) {
        this.currentProductContainer = currentProductContainer;
    }
    
    public TagCloudModel getAttributeCloud() {
        attributeCloud = new DefaultTagCloudModel();
        List<Attribute> attributes = currentProductContainer.getSharedAttribute().getRoot().getChildren();
        
        for(Attribute attribute : attributes)
            attributeCloud.addTag(new DefaultTagCloudItem(attribute.getName(), "#", 
                    ((int)attribute.getWeight()) % 6));
        
        return attributeCloud;
    }
    
    public String getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(String newProduct) {
        this.newProduct = newProduct;
    }
    
    @PostConstruct
    private void initialize() {
        products = agent.getProducts();
        currentProductContainer = new Container(new Product(""));
        for(Product product : products) {
            product.setProductListener(new ProductAdaptor(currentProductContainer));
        }
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void addProduct() throws Exception {
        Product product = new Product(newProduct);
        product.setProductListener(new ProductAdaptor(currentProductContainer));
        agent.getProducts().add(product);
        
        productsBean.addProduct(product, agent);
        
        insertable = false;
    }
    
    public String loadAttributesPage() {
        root = currentProductContainer.getSharedAttribute().getRoot();
        tree = new AttributeTree(root);
        return "Attributes.xhtml";
    }

    public List<AttributeContainer> getAttributeList() {
        return tree.getAttributeList();
    }
    
    public void update(AttributeContainer attributeContainer) {
        productsBean.update(attributeContainer.getAttribute());
        attributeContainer.setEditable(false);
        tree.updateExtendedName(attributeContainer);
    }
    
    public void addSibling(AttributeContainer attributeContainer) {
        Attribute attribute = new Attribute("new", 1, 1);
        Attribute parent = attributeContainer.getParent().getAttribute();
        parent.getChildren().add(attribute);
        attribute = productsBean.addAttribute(parent, attribute);
        
        int insertPosition = tree.getAttributeList().indexOf(attributeContainer);
        AttributeContainer container = new AttributeContainer(tree, attribute, attributeContainer.getParent());
        container.setVisible(true);
        container.setEditable(true);
        tree.getAttributeList().add(insertPosition, container);
        update(attributeContainer);
    }
    
    public void addChild(AttributeContainer attributeContainer) {
        Attribute attribute = new Attribute("new", 1, 1);
        Attribute parent = attributeContainer.getAttribute();
        parent.getChildren().add(attribute);
        attribute = productsBean.addAttribute(parent, attribute);
        
        attributeContainer.setExpanded(true);
        int insertPosition = tree.getAttributeList().indexOf(attributeContainer) + 1;
        AttributeContainer container = new AttributeContainer(tree, attribute, attributeContainer);
        container.setVisible(true);
        container.setEditable(true);
        tree.getAttributeList().add(insertPosition, container);
        
        update(attributeContainer);
    }
    
    public void remove(AttributeContainer attributeContainer) {
        tree.markToRemove(attributeContainer);
        
        for(AttributeContainer container : tree.getMarkedForRemoval()) {
            Attribute attribute = container.getAttribute();
            Attribute parent = container.getParent().getAttribute();
            parent.getChildren().remove(attribute);            

            productsBean.remove(parent, attribute);
            
            tree.getAttributeList().remove(container);
        }
    }

    public ArrayList<AttributeContainer> getMarkedForRemoval() {
        return tree.getMarkedForRemoval();
    }

    public ArrayList<Issue> getNegotiableNodes() {
        return tree.getNegotiableNodes();
    }
    
    public ArrayList<String> getNegotiableNodeNames() {
        return tree.getNegotiableNodeNames();
    }
    
    public void postAdvertisement() {
        try {
            negotiation.ws.AgentType type;
            type = (agent.getType() == negotiation.db.AgentType.Buyer) ? AgentType.BUYER : AgentType.SELLER;
            add(agent.getName(), type, currentProductContainer.getSharedAttribute().getName(), tree.getNegotiableNodeNames());
        } catch(Exception ex) {
            error = ex.toString();
        }
    }

    private String add(java.lang.String agent, negotiation.ws.AgentType type, java.lang.String product, java.util.List<java.lang.String> attributeNames) {
        negotiation.ws.ServerService port = service.getServerServicePort();
        return port.add(agent, type, product, attributeNames);
    }
    
    public void updateAgent() {
        agentBean.update(agent);
    }
    
    public void showInterlocutors() {
        interlocutors = getInterlocutors(agent.getName(), currentProductContainer.getSharedAttribute().getName());
    }
    
    public Candidate getFromCandidate() {
        Candidate from = new Candidate();
        from.setAgent(agent.getName());
        from.setProduct(currentProductContainer.getSharedAttribute().getName());
        return from;
    }

    private java.util.List<negotiation.ws.Candidate> getInterlocutors(java.lang.String agent, java.lang.String product) {
        negotiation.ws.ServerService port = service.getServerServicePort();
        return port.getInterlocutors(agent, product);
    }
}