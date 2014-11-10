package negotiation.coordinator;

import java.util.ArrayList;
import java.util.List;
import negotiation.ws.Candidate;
import negotiation.ws.MessageType;
import negotiation.ws.SimpleMessage;

public class BuyerCoordinator extends Coordinator {
    private double depreciationFactor;

    public BuyerCoordinator(String fromAgent, String fromProduct, ArrayList<Issue> issues, Candidate to) {
        super(fromAgent, fromProduct, issues, to);
    }
    
    @Override
    public void generateInitialOffer() {
        SimpleMessage offer = new SimpleMessage();
        offer.setType(MessageType.SEND);
        
        List<negotiation.ws.Issue> offerIssues = offer.getIssues();
        for(Issue i : issues) {
            if(!i.isAccepted()) {
                negotiation.ws.Issue issue = new negotiation.ws.Issue();
                issue.setName(i.getName());
                issue.setCost(i.getActualCost());
                offerIssues.add(issue);
            }
        }
        
        calculateDepreciationFactor();
        sendMessage(offer);
    }
    
    private void calculateDepreciationFactor() {
        int costSum = 0;
        
        for(Issue issue : issues)
            if(!issue.isAccepted()) {
                costSum += issue.getActualCost();
            }
        
        depreciationFactor = (maximumPayoff - minimumPayoff)/(noOfRounds*costSum);
        noOfRounds--;
    }

    @Override
    public double getDepreciationFactor() {
        return depreciationFactor;
    }

    @Override
    public SimpleMessage generateCounterOffer(SimpleMessage offer) {
        SimpleMessage counterOffer = new SimpleMessage();
        counterOffer.setType(MessageType.SEND);
        
        List<negotiation.ws.Issue> counterIssues = counterOffer.getIssues();
        
        finished = true;
        for(negotiation.ws.Issue issue : offer.getIssues()) {
            Issue buyerIssue = getIssue(issue.getName());
            
            if(!buyerIssue.isAccepted()) {
                if(issue.getCost() < buyerIssue.getActualCost()) {
                    buyerIssue.setActualCost(issue.getCost());
                    buyerIssue.setAccepted(true);                    
                } else
                    finished = false;
            }
        }
        
        calculateDepreciationFactor();
        
        if(finished) {
            for(Issue buyerIssue : issues) {               
                negotiation.ws.Issue counterIssue = new negotiation.ws.Issue();
                counterIssue.setName(buyerIssue.getName());
                counterIssue.setCost(buyerIssue.getActualCost());
                counterIssues.add(counterIssue);
            }
        } else {
            for(negotiation.ws.Issue issue : offer.getIssues()) {
                Issue buyerIssue = getIssue(issue.getName());
                
                if(!buyerIssue.isAccepted()) {
                        float oldCost = buyerIssue.getActualCost();
                        float weight = buyerIssue.getWeight();
                        buyerIssue.setActualCost(oldCost*(1 + (float)depreciationFactor/weight));
                        
                        negotiation.ws.Issue counterIssue = new negotiation.ws.Issue();
                        counterIssue.setName(buyerIssue.getName());
                        counterIssue.setCost(buyerIssue.getActualCost());
                        counterIssues.add(counterIssue);
                }
            }
        }
       
        return counterOffer;
    }

    @Override
    protected void storeResult(SimpleMessage acceptMessage) {
        finalCost = 0;
        
        for(negotiation.ws.Issue issue : acceptMessage.getIssues()) {
            Issue buyerIssue = getIssue(issue.getName());
            buyerIssue.setActualCost(issue.getCost());
            finalCost += issue.getCost();
        }
        
        result.clear();
        for(Issue issue : issues) {
            result.add(issue);
        }
    }

    @Override
    public boolean isBetterThan(Coordinator coordinator) {
        return finalCost <= coordinator.getFinalCost();
    }
}
