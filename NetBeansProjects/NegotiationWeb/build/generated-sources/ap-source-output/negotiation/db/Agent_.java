package negotiation.db;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negotiation.db.AgentType;
import negotiation.db.Product;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-07T18:51:16")
@StaticMetamodel(Agent.class)
public class Agent_ { 

    public static volatile SingularAttribute<Agent, Long> id;
    public static volatile SingularAttribute<Agent, String> name;
    public static volatile SingularAttribute<Agent, AgentType> type;
    public static volatile SingularAttribute<Agent, String> password;
    public static volatile ListAttribute<Agent, Product> products;

}