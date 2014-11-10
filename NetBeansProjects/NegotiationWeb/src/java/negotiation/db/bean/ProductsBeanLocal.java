package negotiation.db.bean;

import javax.ejb.Local;
import negotiation.db.Agent;
import negotiation.db.Attribute;
import negotiation.db.Product;

@Local
public interface ProductsBeanLocal {
    Product addProduct(Product product,Agent agent);
    Attribute addAttribute(Attribute parent,Attribute attribute);
    void update(Attribute attribute);
    void remove(Attribute parent,Attribute attribute);
}
