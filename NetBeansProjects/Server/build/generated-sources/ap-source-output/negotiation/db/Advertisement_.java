package negotiation.db;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negotiation.db.AdvertisementId;
import negotiation.db.AgentType;
import negotiation.db.Attribute;
import negotiation.db.Status;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-07T18:48:52")
@StaticMetamodel(Advertisement.class)
public class Advertisement_ { 

    public static volatile SingularAttribute<Advertisement, AdvertisementId> id;
    public static volatile SingularAttribute<Advertisement, Status> status;
    public static volatile SetAttribute<Advertisement, Attribute> attributes;
    public static volatile SingularAttribute<Advertisement, AgentType> type;

}