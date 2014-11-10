package negotiation.db;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import negotiation.db.listeners.ProductListener;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private Attribute root = new Attribute("root", 1, 1);
    
    @Transient
    private ProductListener listener;

    public ProductListener getListener() {
        return listener;
    }
    
    public void setProductListener(ProductListener listener) {
        listener.initialize(this);
        this.listener = listener;
    }
    
    @PrePersist
    private void initializeRootAttribute() {
        if(root == null)
            root = new Attribute("root", 1, 1);
    }
    
    public Product() {
        initializeRootAttribute();
    }
    
    public Product(String name) {
        this.name = name;
    }

    public Attribute getRoot() {
        return root;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negotiation.db.Product[ id=" + id + " ," + root + " ]";
    }
    
}
