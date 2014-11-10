package negotiation.db;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

@Entity
@NamedQueries({
@NamedQuery(name="getAll",query="select a from Advertisement a"),
@NamedQuery(name="getWithStatus",query="select a from Advertisement a where a.status = :status")
})
public class Advertisement implements Serializable, Comparable<Advertisement> {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private AdvertisementId id;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    private AgentType type;
    
    @ManyToMany(cascade={CascadeType.PERSIST},fetch= FetchType.EAGER)
    private Set<Attribute> attributes = new TreeSet<Attribute>();

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public AgentType getType() {
        return type;
    }

    public void setType(AgentType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Advertisement() {
    }

    public Advertisement(String agent,AgentType type,String product,Status status) {
        id = new AdvertisementId(agent, product);
        this.status = status;
        this.type = type;
    }

    public AdvertisementId getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Advertisement)) {
            return false;
        }
        Advertisement other = (Advertisement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negotiation.db.Advertisement[ id=" + id + " ," + type + " ]";
    }

    @Override
    public int compareTo(Advertisement t) {
        return id.compareTo(t.id);
    }
}