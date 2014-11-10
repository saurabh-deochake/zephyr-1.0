package negotiation.coordinator;

public class Issue {
    private String name;
    private float actualCost, costWithMargin;
    private float weight;
    
    private float minUtility, maxUtility;
    private boolean accepted;
    
    public Issue(String name, float actualCost, float costWithMargin, float weight) {
        this.name = name;
        this.actualCost = actualCost;
        this.costWithMargin = costWithMargin;
        this.weight = weight;
        
        minUtility = actualCost*weight;
        maxUtility = costWithMargin*weight;
        accepted = false;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public float getMaxUtility() {
        return maxUtility;
    }

    public float getMinUtility() {
        return minUtility;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    public float getActualCost() {
        return actualCost;
    }

    public void setActualCost(float actualCost) {
        this.actualCost = actualCost;
        minUtility = actualCost*weight;
    }

    public float getCostWithMargin() {
        return costWithMargin;
    }

    public void setCostWithMargin(float costWithMargin) {
        this.costWithMargin = costWithMargin;
        maxUtility = costWithMargin*weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return accepted ? 0 : weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}