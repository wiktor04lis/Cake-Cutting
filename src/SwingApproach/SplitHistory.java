package SwingApproach;

import java.util.ArrayList;

public class SplitHistory {
    protected int parentID;
    protected int id;
    protected double leftBound;
    protected double rightBound;
    protected ArrayList<Agent> agents;
    
    public SplitHistory(int parentID, int id,double leftBound, double rightBound, ArrayList<Agent> agents) {
        this.parentID = parentID;
        this.id = id;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.agents = agents;
    }

    public int getParentID() {
        return parentID;
    }
    
    public int getID() {
        return id;
    }

    public double getLeftBound() {
        return leftBound;
    }

    public double getRightBound() {
        return rightBound;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }
}
