package negotiation.db;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negotiation.db.Attribute;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-07T18:51:16")
@StaticMetamodel(Attribute.class)
public class Attribute_ { 

    public static volatile SingularAttribute<Attribute, Long> id;
    public static volatile SingularAttribute<Attribute, Float> weight;
    public static volatile SingularAttribute<Attribute, Float> costWithMargin;
    public static volatile SingularAttribute<Attribute, String> name;
    public static volatile ListAttribute<Attribute, Attribute> children;
    public static volatile SingularAttribute<Attribute, Float> actualCost;

}