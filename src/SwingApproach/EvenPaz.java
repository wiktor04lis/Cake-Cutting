package SwingApproach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EvenPaz{
    private EvenPazState currState = EvenPazState.READY;
    private ArrayList<Agent> agents = new ArrayList<>();
    private Comparator<Agent> sortByMark = new SortByMark();
    protected Stack<AgentGroup> agentGroups = new Stack<>();
    protected Queue<SplitHistory> splitHistory = new LinkedList<>();

    private int parentID = 0;

    private int numberOfAgents;

    public EvenPaz(int numberOfAgents){
        this.numberOfAgents=numberOfAgents;
    }

    public void resume(){
        while (currState != EvenPazState.AWAITING_USER_INPUT) {
            switch (currState) {
                case READY:
                    initilise();
                    break;
                case CHECK_BASE_CASE:
                    baseCase();
                    break;
                case SPLITTING:
                    split();
                    break;
                case DONE:
                    finished();
                    break;
                default:
                    break;
            }
        }
    }

    public void initilise(){
        for(int agent = 0; agent < numberOfAgents; agent++){
            agents.add(new Agent(agent));
        }
        agentGroups.push(new AgentGroup(agents, 0, 1));
        currState = EvenPazState.CHECK_BASE_CASE;
        resume();
    }

    private void baseCase(){
        AgentGroup agentGroup = agentGroups.peek();
        if (agentGroup.agents.size() == 1) {
            agentGroup.agents.get(0).givePiece(agentGroup.leftBound, agentGroup.rightBound);
            agentGroups.pop();
            currState = EvenPazState.DONE;
            return;
        }
        currState = EvenPazState.AWAITING_USER_INPUT;
    }

    public void receiveMarks(ArrayList<Double> marks){
        AgentGroup agentGroup = agentGroups.peek();
        for(int a = 0; a < agentGroup.getAgents().size(); a++){
            agentGroup.agents.get(a).setMark(a);
        }
        currState = EvenPazState.SPLITTING;
    }

    private void split(){
        AgentGroup group = agentGroups.pop();
        ArrayList<Agent> agents = group.getAgents();
        double leftBound = group.getLeftBound();
        double rightBound = group.getRightBound();

        sort(agents);

        int middleAgentIndex = agents.size()/2;
        double middleAgentMark = agents.get(middleAgentIndex).getMark();
        ArrayList<Agent> left = new ArrayList<>(agents.subList(0, middleAgentIndex));
        ArrayList<Agent> right = new ArrayList<>(agents.subList(middleAgentIndex, agents.size()));

        agentGroups.push(new AgentGroup(right, middleAgentMark, rightBound));
        agentGroups.push(new AgentGroup(left, leftBound, middleAgentMark));
        splitHistory.add(new SplitHistory(parentID++, leftBound, middleAgentMark, left));
        splitHistory.add(new SplitHistory(parentID++, middleAgentMark, rightBound, right));
        currState = EvenPazState.CHECK_BASE_CASE;
    }

    private void finished(){
        if(!agentGroups.isEmpty()){
            currState = EvenPazState.CHECK_BASE_CASE;
        }
        currState = EvenPazState.COMPLETE;
    }

    public void printAllocation(ArrayList<Agent> agents){
        for(Agent a : agents){
            System.out.println(a.toString());
        }
    }

    private void sort(ArrayList<Agent> agents){
        Collections.sort(agents, sortByMark);
    }

    public EvenPazState getCurrState() {
        return currState;
    }

    public Stack<AgentGroup> getAgentGroups() {
        return agentGroups;
    }

    public Queue<SplitHistory> getSplitHistory() {
        return splitHistory;
    }
}