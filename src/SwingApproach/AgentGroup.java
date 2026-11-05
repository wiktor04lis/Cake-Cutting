package SwingApproach;

import java.util.ArrayList;

public class AgentGroup {
    protected ArrayList<Agent> agents;
    protected double leftBound;
    protected double rightBound;

    
    public AgentGroup(ArrayList<Agent> agents, double leftBound, double rightBound) {
        this.agents = agents;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }
    
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public double getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(double leftBound) {
        this.leftBound = leftBound;
    }

    public double getRightBound() {
        return rightBound;
    }

    public void setRightBound(double rightBound) {
        this.rightBound = rightBound;
    }
}
