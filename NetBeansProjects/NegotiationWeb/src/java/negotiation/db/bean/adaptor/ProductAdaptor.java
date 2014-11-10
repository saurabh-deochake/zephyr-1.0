package negotiation.db.bean.adaptor;

import javax.swing.JFrame;
import negotiation.db.Product;
import negotiation.db.listeners.ProductListener;

public class ProductAdaptor implements ProductListener {

    Container<Product> container;
    Product product;

    @Override
    public Long getID() {
        return container.sharedAttribute.getId();
    }

    @Override
    public void initialize(Product product) {
        this.product = product;
    }

    @Override
    public void action() {
        container.sharedAttribute = product;
    }

    public ProductAdaptor(Container container) {
        this.container = container;
    }
}
