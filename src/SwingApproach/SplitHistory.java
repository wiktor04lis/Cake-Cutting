package SwingApproach;

import java.util.ArrayList;

public class SplitHistory {
    protected int parent;
    protected double leftBound;
    protected double rightBound;
    protected ArrayList<Agent> agents;

    public SplitHistory(int parent, double leftBound, double rightBound, ArrayList<Agent> agents) {
        this.parent = parent;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.agents = agents;
    }

    public int getParent() {
        return parent;
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
