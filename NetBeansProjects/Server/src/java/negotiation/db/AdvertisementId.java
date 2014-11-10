package negotiation.db;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class AdvertisementId implements Serializable, Comparable<AdvertisementId> {
    private String agent;
    private String product;

    public AdvertisementId() {
    }

    public AdvertisementId(String agent, String product) {
        this.agent = agent;
        this.product = product;
    }

    public String getAgent() {
        return agent;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return agent + "," + product;
    }

    @Override
    public int compareTo(AdvertisementId Id) {
        return (agent + product).compareTo(Id.agent + Id.product);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AdvertisementId) {
            return ((AdvertisementId)obj).agent.equals(agent) & ((AdvertisementId)obj).product.equals(product);
        } else 
            return false;
    }
    
    
}
