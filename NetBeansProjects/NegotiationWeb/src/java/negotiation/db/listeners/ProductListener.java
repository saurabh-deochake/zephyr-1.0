package negotiation.db.listeners;

import negotiation.db.Product;

public interface ProductListener {
    void initialize(Product product);
    void action();
    Long getID();
}
