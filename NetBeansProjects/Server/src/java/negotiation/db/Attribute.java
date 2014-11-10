package negotiation.db;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

@Entity
@NamedQuery(name="findAttribute",query="select a from Attribute a where a.name = :name")
public class Attribute implements Serializable, Comparable<Attribute> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    
    @ManyToMany(mappedBy = "attributes",cascade={CascadeType.PERSIST})
    private Set<Advertisement> advertisements = new TreeSet<Advertisement>();

    public Attribute() {
    }

    public Attribute(String name) {
        this.name = name;
    }
 
    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Attribute)) {
            return false;
        }
        Attribute other = (Attribute) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negotiation.db.Attribute[ id=" + name + " ]";
    }

    @Override
    public int compareTo(Attribute t) {
        return name.compareTo(t.name);
    }    
}
