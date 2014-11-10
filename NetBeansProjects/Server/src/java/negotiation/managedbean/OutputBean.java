package negotiation.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import negotiation.db.Advertisement;
import negotiation.db.AgentType;
import negotiation.db.bean.AdvertisementBean;
import negotiation.db.bean.matcher.Candidate;
import negotiation.db.bean.matcher.ConditionChecker;
import negotiation.db.bean.matcher.NegotiationSpace;
import negotiation.db.bean.messages.Buffer;
import negotiation.db.bean.messages.MessageType;
import negotiation.db.bean.messages.Path;
import negotiation.db.bean.messages.SimpleMessage;

@ManagedBean
@SessionScoped
public class OutputBean {

    @EJB
    private Buffer buffer;
    @EJB
    private ConditionChecker conditionChecker;
    private int count = 0;
    @EJB
    private AdvertisementBean advertisementBean;
    private String output;
    private ArrayList<Candidate> interlocutors;

    public ArrayList<Candidate> getInterlocutors() {
        return interlocutors;
    }

    public List<NegotiationSpace> getNegotiationUnits() {
        return conditionChecker.getUnits();
    }

    public String getOutput() {
        return output + conditionChecker.match();
    }

    public OutputBean() {
    }

    public List<Advertisement> getAdvertisements() {
        return advertisementBean.getAllData();
    }

    public void postAdvertisement() {
        try {
            Set<String> attributes = new TreeSet<String>();
            attributes.add("virus");
            attributes.add("virus");
            attributes.add("malware");
            attributes.add("spyware");

            switch (++count) {
                case 1:
                    output = advertisementBean.add("agent1", AgentType.Buyer, "symantec", attributes);
                    break;
                case 3:
                    output = advertisementBean.add("agent2", AgentType.Buyer, "mcaffee", attributes);
                    attributes.add("backup");
                    output = advertisementBean.add("agent3", AgentType.Seller, "avast", attributes);
                    break;
                case 2: {
                    attributes = new TreeSet<String>();
                    attributes.add("engine");
                    attributes.add("seat");
                    attributes.add("tyre");
                    output = advertisementBean.add("agent1", AgentType.Buyer, "maruti800", attributes);
                    break;
                }
                case 4:
                    output = advertisementBean.add("agent1", AgentType.Buyer, "symantec", attributes);
                    break;
            }
        } catch (EJBException ex) {
            output = ex.getCause().toString();
        }
    }

    public void bid() {
        interlocutors = conditionChecker.getInterlocutors("agent1", "symantec");
    }

    public Set<Entry<Path, SimpleMessage>> getAllMessages() {
        return buffer.getAllMessages();
    }

    public void sayHelloAgent1() {
        buffer.addMessage(
                new SimpleMessage(new Candidate("agent1", "symantec"), new Candidate("agent3", "avast"), 
                MessageType.Hello,1,1,null));
    }
    
    public void sayHelloAgent2() {
        buffer.addMessage(
                new SimpleMessage(new Candidate("agent1", "symantec"), new Candidate("agent2", "mcaffee"), 
                MessageType.Hello,1,1,null));
    }

    public void sayHelloAgent3() {
        buffer.addMessage(new SimpleMessage(new Candidate("agent3", "avast"), new Candidate("agent1", "symantec"),
                MessageType.Hello,1,1,null));
    }
    
    public SimpleMessage testGetMessageFrom() {
        Path path = new Path(new Candidate("agent1", "symantec"),new Candidate("agent3", "avast"));
        SimpleMessage message = buffer.getMessageFrom(path);
        return message;
    }
    
    public void clearMessages() {
        buffer.clear();
    }
}