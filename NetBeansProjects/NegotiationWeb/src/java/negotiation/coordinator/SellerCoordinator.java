package negotiation.coordinator;

import java.util.ArrayList;
import java.util.List;
import negotiation.ws.Candidate;
import negotiation.ws.MessageType;
import negotiation.ws.SimpleMessage;

public class SellerCoordinator extends Coordinator {
    private double depreciationFactor;
    
    public SellerCoordinator(String fromAgent, String fromProduct, ArrayList<Issue> issues, Candidate to) {
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
                issue.setCost(i.getCostWithMargin());
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
                costSum += issue.getCostWithMargin();
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
            Issue sellerIssue = getIssue(issue.getName());
            
            if(!sellerIssue.isAccepted()) {
                if(issue.getCost() > sellerIssue.getCostWithMargin()) {
                    sellerIssue.setCostWithMargin(issue.getCost());
                    sellerIssue.setAccepted(true);                    
                } else
                    finished = false;
            }
        }
        
        calculateDepreciationFactor();
        
        if(finished) {
            for(Issue buyerIssue : issues) {               
                negotiation.ws.Issue counterIssue = new negotiation.ws.Issue();
                counterIssue.setName(buyerIssue.getName());
                counterIssue.setCost(buyerIssue.getCostWithMargin());
                counterIssues.add(counterIssue);
            }
        } else {
        for(negotiation.ws.Issue issue : offer.getIssues()) {
            Issue sellerIssue = getIssue(issue.getName());
            
            if(!sellerIssue.isAccepted()) {
                    float oldCost = sellerIssue.getCostWithMargin();
                    float weight = sellerIssue.getWeight();
                    sellerIssue.setCostWithMargin(oldCost*(1 - (float)depreciationFactor/weight));
                
                    negotiation.ws.Issue counterIssue = new negotiation.ws.Issue();
                    counterIssue.setName(sellerIssue.getName());
                    counterIssue.setCost(sellerIssue.getCostWithMargin());
                    counterIssues.add(counterIssue);    
                }
            }
        }
        
        return counterOffer;
    }

    @Override
    protected void storeResult(SimpleMessage acceptMessage) {
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
        return finalCost >= coordinator.getFinalCost();
    }
}