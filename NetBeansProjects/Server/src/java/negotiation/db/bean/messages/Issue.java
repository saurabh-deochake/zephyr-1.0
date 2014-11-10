package negotiation.db.bean.messages;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Issue {
    String name;
    float cost;
    
    public Issue() {}

    public Issue(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Issue{" + "name=" + name + ", cost=" + cost + '}';
    }
}
