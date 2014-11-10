package negotiation.coordinator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import negotiation.db.AgentType;
import negotiation.ws.Candidate;
import negotiation.ws.MessageType;
import negotiation.ws.ServerService;
import negotiation.ws.SimpleMessage;

class OfferComparator implements Comparator<SimpleMessage> {
    @Override
    public int compare(SimpleMessage message1, SimpleMessage message2) {
        if(message1.getId() != message2.getId())       
            return message1.getId() - message2.getId();
        else {
            String m1 =  message1.getFrom().getProduct() + message1.getFrom().getAgent();
            String m2 = message2.getFrom().getProduct() + message2.getFrom().getAgent();
            return m1.compareTo(m2);
        }            
    }
}

abstract public class Coordinator {
    private static negotiation.ws.ServerService port;
    
    protected float minimumPayoff, maximumPayoff;
    protected int noOfRounds;
    
    private int outgoingMessageId, incomingMessageId;
    
    protected ArrayList<Issue> issues;
    protected ArrayList<Issue> result;
    protected float finalCost;
    protected Set<SimpleMessage> offers = new TreeSet<SimpleMessage>(new OfferComparator());
    
    protected boolean finished;
   
    abstract protected void storeResult(SimpleMessage acceptMessage);
    abstract public void generateInitialOffer();
    abstract public double getDepreciationFactor();
    abstract public SimpleMessage generateCounterOffer(SimpleMessage offer);
    
    abstract public boolean isBetterThan(Coordinator coordinator);

    public static void initialize(ServerService port) {
        Coordinator.port = port;
    }

    private static void send(negotiation.ws.SimpleMessage message) {
        port.send(message);
    }
    
    private static SimpleMessage getReply(Candidate to,Candidate from) {
        return port.getMessageFrom(to.getAgent(), to.getProduct(),from.getAgent(),from.getProduct());        
    }
    
    Candidate from, to;
    protected String id;
    private boolean activated = false;

    public boolean isActivated() {
        return activated;
    }
    
    private void calculatePayoffs() {
        for(Issue issue : issues) {
            minimumPayoff += issue.getMinUtility();
            maximumPayoff += issue.getMaxUtility();
        }
    }
    
    public Coordinator(String fromAgent,String fromProduct,ArrayList<Issue> issues,Candidate to) {
        from = new Candidate();
        from.setAgent(fromAgent);
        from.setProduct(fromProduct);
        this.to = to;
        this.issues = issues;
        noOfRounds = 10;
        outgoingMessageId = incomingMessageId = 0;
        offers.clear();
        finished = false;
        
        id = to.getAgent() + to.getProduct();
        
        calculatePayoffs();
        
        result = new ArrayList<Issue>();
        activated = true;
    }
    
    public Candidate getFrom() {
        return from;
    }

    public Candidate getTo() {
        return to;
    }
    
    public void sendHelloMessage() {
        SimpleMessage helloMessage = new SimpleMessage();
        helloMessage.setType(MessageType.HELLO);
        
        sendMessage(helloMessage);
    }
    
    public void sendMessage(SimpleMessage message) {
        message.setFrom(from);
        message.setTo(to);
        message.setReplyId(outgoingMessageId);
        message.setId(outgoingMessageId++);
        
        offers.add(message);
        port.send(message);
    }
    
    public void getNewReply() {
        //if(finished == false) {
            SimpleMessage reply = port.getMessageFrom(to.getAgent(), to.getProduct(),from.getAgent(),from.getProduct());
            
            if(reply == null && outgoingMessageId == 0)
                generateInitialOffer();
            
            if(!finished && reply != null && reply.getId() == incomingMessageId) {
                if(reply.getType() == MessageType.ACCEPT) {
                    reply.setType(MessageType.ACCEPT_ACKNOWLEDGE);
                    finished = true;
                    storeResult(reply);
                    sendMessage(reply);
                    return;
                }
                
                incomingMessageId++;
                offers.add(reply);
                
                if(reply.getType() == MessageType.SEND) {
                    SimpleMessage counterOffer = generateCounterOffer(reply);
                
                    if(finished == true) {
                        counterOffer.setType(MessageType.ACCEPT);
                        storeResult(counterOffer);
                    }
                    sendMessage(counterOffer);
                    return;
                }
            }
            
            if(finished && reply != null) {
                if(reply.getType() == MessageType.FINALIZE || reply.getType() == MessageType.REJECT) {
                    offers.add(reply);
                    finished = false;
                }
            }
        //}
    }
    
    public float getMaximumPayoff() {
        return maximumPayoff;
    }

    public float getMinimumPayoff() {
        return minimumPayoff;
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }
    
    public ArrayList<Issue> getResult() {
        return result;
    }
    
    public float getFinalCost() {
        return finalCost;
    }
    
    public String getId() {
        return id;
    }
    
    public static Coordinator getCoordinator(String fromAgent,AgentType type,String fromProduct,ArrayList<Issue> issues,Candidate to) {
        if(type == AgentType.Buyer)
            return new BuyerCoordinator(fromAgent, fromProduct, issues, to);
        else if(type == AgentType.Seller)
            return new SellerCoordinator(fromAgent, fromProduct, issues, to);
        else
            throw new IllegalArgumentException("Agent Type not specified");
    }
    
    public Set<SimpleMessage> getOffers() {
        return offers;
    }
    
    public Issue getIssue(String name) {
        for(Issue issue : issues)
            if(issue.getName().equals(name))
                return issue;
        return null;
    }
}