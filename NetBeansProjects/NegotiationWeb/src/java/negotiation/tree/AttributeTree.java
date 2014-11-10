package negotiation.tree;

import java.util.ArrayList;
import java.util.List;
import negotiation.db.Attribute;
import negotiation.coordinator.Issue;

public class AttributeTree {
    private Attribute root;
    private ArrayList<AttributeContainer> markedForRemoval;
    private List<AttributeContainer> attributeList;

    public ArrayList<AttributeContainer> getMarkedForRemoval() {
        return markedForRemoval;
    }
    
    public List<AttributeContainer> getAttributeList() {
        return attributeList;
    }
    
    private void loadTree(AttributeContainer parentContainer) {
        List<Attribute> children = parentContainer.getAttribute().getChildren();
        for(Attribute child : children) {
            AttributeContainer childContainer = new AttributeContainer(this,child, parentContainer);
            attributeList.add(childContainer);
            
            if(child.getChildren().size() > 0)
                loadTree(childContainer);
        }
    }
    
    public AttributeTree(Attribute root) {
        initialize(root);
    }
    
    private void initialize(Attribute root) {
        markedForRemoval = new ArrayList<AttributeContainer>();
        attributeList = new ArrayList<AttributeContainer>();
        this.root = root;
        AttributeContainer rootContainer = new AttributeContainer(this,root, null);
        rootContainer.setVisible(true);
        
        attributeList.add(rootContainer);
        loadTree(rootContainer);
    }
    
    private void markForRemoval(AttributeContainer attributeContainer) {
        markedForRemoval.add(attributeContainer);
        
        for(AttributeContainer container : attributeList)
            if(container.getParent() == attributeContainer)
            {
                markedForRemoval.add(container);
                markForRemoval(container);
            }
    }
    
    public void markToRemove(AttributeContainer attributeContainer) {
        markedForRemoval.clear();
        markForRemoval(attributeContainer);
    }
    
    public ArrayList<String> getNegotiableNodeNames() {
        ArrayList<String> nodeList = new ArrayList<String>();
        
        for(AttributeContainer attribute : attributeList)
            if(!attribute.hasChildren()) {
                nodeList.add(attribute.getExtendedName());
            }
        return nodeList;
    }
    
    public ArrayList<Issue> getNegotiableNodes() {
        ArrayList<Issue> nodeList = new ArrayList<Issue>();
        
        for(AttributeContainer attribute : attributeList)
            if(!attribute.hasChildren()) {
                Issue issue = new Issue(attribute.getExtendedName(), 
                                        attribute.getAttribute().getActualCost(), 
                                        attribute.getAttribute().getCostWithMargin(), 
                                        attribute.getAttribute().getWeight());
                nodeList.add(issue);
            }
        return nodeList;
    }
    
    public void updateExtendedName(AttributeContainer container) {
        
    }
}