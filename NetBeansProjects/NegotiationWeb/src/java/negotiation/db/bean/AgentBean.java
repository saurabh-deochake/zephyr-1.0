package negotiation.db.bean;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;
import javax.annotation.PostConstruct;
import negotiation.db.Agent;
import negotiation.db.exception.*;
import java.util.*;

@Stateless
public class AgentBean implements AgentBeanLocal {
    @PersistenceContext(unitName = "NegotiationWebPU")
    private EntityManager em;
    
    private List<Agent> agents;

    public List<Agent> getAgents(String name) {
        Query query = em.createNamedQuery("findAgent").setParameter("name", (name == "" ? "" : (name + "%")));
        agents = query.getResultList();
        return agents;
    }

    @PostConstruct
    private void loadList() {
        Query query = em.createNamedQuery("findAgent").setParameter("name", "%");
        query.setMaxResults(1);
        
        agents = query.getResultList();
    }
    
    @Override
    public String sayHello() {
        return "hello";
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public Agent login(String name) {
        Query query = em.createNamedQuery("findAgent").setParameter("name", name);
        query.setMaxResults(1);
        
        List<Agent> list = query.getResultList();
        
        if(list.isEmpty())        
            throw new AgentException();
        
        return list.get(0);
    }

    @Override
    public void addNew(negotiation.db.Agent agent) {
        try {
            login(agent.getName());
        } catch(AgentException ex) {
            em.persist(agent);
        }
        
        throw new AgentException();
    }

    @Override
    public void update(Agent agent) {
        em.merge(agent);
    }
}
