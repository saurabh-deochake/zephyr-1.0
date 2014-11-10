package negotiation.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Attribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private float actualCost;
    private float costWithMargin;
    private float weight;

    @OneToMany
    @JoinTable(name="Attribute_mapping",
            joinColumns=@JoinColumn(name="parent"),
            inverseJoinColumns=@JoinColumn(name="child"))
    private List<Attribute> children = new ArrayList<Attribute>();

    public List<Attribute> getChildren() {
        return children;
    }

    public void setChildren(List<Attribute> children) {
        this.children = children;
    }

    public Attribute() {
    }

    public Attribute(String name, float actualCost, float costWithMargin) {
        this.name = name;
        this.actualCost = actualCost;
        this.costWithMargin = costWithMargin;
    }

    public float getActualCost() {
        return actualCost;
    }

    public void setActualCost(float actualCost) {
        this.actualCost = actualCost;
    }

    public float getCostWithMargin() {
        return costWithMargin;
    }

    public void setCostWithMargin(float costWithMargin) {
        this.costWithMargin = costWithMargin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    public float getWeight() {
        return weight;
    }
        
    public Long getId() {
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
        if (!(object instanceof Attribute)) {
            return false;
        }
        Attribute other = (Attribute) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negotiation.db.Attribute[ id=" + id + " ]";
    }
    
}
