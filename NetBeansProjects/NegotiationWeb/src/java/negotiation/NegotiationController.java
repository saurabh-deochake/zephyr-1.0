package negotiation;

import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;
import negotiation.coordinator.Coordinator;
import negotiation.coordinator.Issue;
import negotiation.db.AgentType;
import negotiation.ws.Candidate;
import negotiation.ws.MessageType;
import negotiation.ws.ServerService_Service;
import negotiation.ws.SimpleMessage;

class CoordinatorComparator implements Comparator<Coordinator> {
    @Override
    public int compare(Coordinator c1, Coordinator c2) {
        Candidate candidate1 = c1.getTo();
        Candidate candidate2 = c2.getTo();
        
        return (candidate1.getAgent() + candidate1.getProduct())
                .compareTo(candidate2.getAgent() + candidate2.getProduct());
    }
}

@ManagedBean
@SessionScoped
public class NegotiationController {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Server/ServerService.wsdl")
    private ServerService_Service service;
    AgentType type;
    private Set<Coordinator> coordinators = new TreeSet<Coordinator>(new CoordinatorComparator());

    public Set<Coordinator> getCoordinators() {
        return coordinators;
    }
        
    private boolean activated = false;
    
    public boolean isActivated() {
        activated = !coordinators.isEmpty();
        return activated;
    }
    
    public NegotiationController() {
    }
    
    public void finalize() {
        Iterator<Coordinator> iterator = coordinators.iterator();
        
        if(coordinators.size() > 0) {
            Coordinator coordinator, bestCoordinator;
            coordinator = bestCoordinator = iterator.next();
            
            do {
                if(coordinator.isBetterThan(bestCoordinator))
                    bestCoordinator = coordinator;
                
                if(iterator.hasNext())
                    coordinator = iterator.next();
                else
                    break;
            } while(coordinator != null);
            
            SimpleMessage finalizeMessage = new SimpleMessage();
            finalizeMessage.setType(MessageType.FINALIZE);
            bestCoordinator.sendMessage(finalizeMessage);
            
            SimpleMessage rejectMessage = new SimpleMessage();
            rejectMessage.setType(MessageType.REJECT);
            
            for(Coordinator c : coordinators) {
                if(c != bestCoordinator)
                    c.sendMessage(rejectMessage);
            }
        }        
    }
    
    public void initialize(String fromAgent,AgentType type,String fromProduct,ArrayList<Issue> issues,Candidate to) {
        Candidate from = new Candidate();
        from.setAgent(fromAgent);
        from.setProduct(fromProduct);        
        
        activated = true;
        
        Coordinator.initialize(service.getServerServicePort());
        Coordinator coordinator = Coordinator.getCoordinator(fromAgent, type, fromProduct, issues, to);
        
        coordinators.remove(coordinator);
        coordinators.add(coordinator);
    }
}