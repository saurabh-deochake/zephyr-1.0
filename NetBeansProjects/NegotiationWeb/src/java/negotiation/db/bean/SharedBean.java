package negotiation.db.bean;

import javax.ejb.Stateful;
import negotiation.db.Attribute;

@Stateful
public class SharedBean implements SharedBeanLocal {
    private Attribute root;

    public Attribute getRoot() {
        return root;
    }

    public void setRoot(Attribute root) {
        this.root = root;
    }
}
