package negotiation.db.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negotiation.db.Agent;
import negotiation.db.Attribute;
import negotiation.db.Product;

@Stateless
public class ProductsBean implements ProductsBeanLocal {

    @PersistenceContext(unitName = "NegotiationWebPU")
    private EntityManager em;

    @Override
    public Product addProduct(Product product,Agent agent) {
        em.persist(product);
        em.persist(product.getRoot());
        em.merge(agent);

        return product;
    }

    @Override
    public Attribute addAttribute(Attribute parent, Attribute attribute) {
        em.persist(attribute);
        em.merge(parent);
        
        return attribute;
    }
    
    @Override
    public void update(Attribute attribute) {
        em.merge(attribute);
    }

    @Override
    public void remove(Attribute parent, Attribute attribute) {
        em.merge(parent);
        attribute = em.merge(attribute);
        em.remove(attribute);
    }
}