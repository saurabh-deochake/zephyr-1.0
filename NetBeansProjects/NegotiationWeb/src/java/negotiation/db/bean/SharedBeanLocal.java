package negotiation.db.bean;

import javax.ejb.Local;
import negotiation.db.Attribute;

@Local
public interface SharedBeanLocal {
    Attribute getRoot();
    void setRoot(Attribute root);
}
