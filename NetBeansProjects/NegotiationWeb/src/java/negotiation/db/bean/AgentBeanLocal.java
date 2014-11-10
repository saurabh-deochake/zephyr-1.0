package negotiation.db.bean;

import java.util.List;
import javax.ejb.Local;
import negotiation.db.Agent;

@Local
public interface AgentBeanLocal {
    String sayHello();
    negotiation.db.Agent login(String agent);
    List<Agent> getAgents(String name);
    void addNew(negotiation.db.Agent agent);

    void update(Agent agent);
}
