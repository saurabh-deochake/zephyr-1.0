package negotiation.db.bean.matcher;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Candidate implements Comparable<Candidate>, Serializable {
    private String agent;
    private String product;

    public Candidate() {
    }
    
    public Candidate(String agent, String product) {
        this.agent = agent;
        this.product = product;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "[ " + agent + ", " + product + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Candidate))
            return false;
        else {
            Candidate candidate = (Candidate) obj;
            return candidate.agent.equals(agent) & candidate.product.equals(product);
        }           
    }
    
    public boolean equals(Candidate candidate) {
        return candidate.agent.equals(agent) & candidate.product.equals(product);
    }

    @Override
    public int compareTo(Candidate c) {
        String thisCandidate = product + agent;
        String cCandidate = c.product + c.agent;
        return thisCandidate.compareTo(cCandidate);
    }
}
