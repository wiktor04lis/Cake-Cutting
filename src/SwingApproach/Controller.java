package SwingApproach;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Controller implements MarkListener {
    private EvenPaz ep;
    private EvenPazView epView;
    private MarkListener listener;
    private ArrayList<Double> marks = new ArrayList<>();
    private boolean awaitingInput = false;
    private AgentGroup currGroup;

    public Controller(EvenPaz ep, EvenPazView epView) {
        this.ep = ep;
        this.epView = epView;
        this.epView.setMarkListener(this);
    }

    public void start() {
        ep.resume();
        epView.createCake(0, 0, 1);
        nextPhase();
    }

    private void nextPhase() {
        switch (ep.getCurrState()) {
            case AWAITING_USER_INPUT:
                cakeSplitting(ep.getSplitHistory());
                marks.clear();
                awaitingInput = true;
                currGroup = ep.getAgentGroups().peek();
                epView.currCakeID = currGroup.getGroupID();
                requestMarkFromGroup();
                break;
            case SPLITTING:
                ep.resume();
                nextPhase();
                break;
            case COMPLETE:
                cakeSplitting(ep.getSplitHistory());
                ArrayList<String> info = new ArrayList<>();
                epView.askAgentForMark(info);
                break;
            default:
                break;
        }
    }

    private void cakeSplitting(Queue<SplitHistory> splitHistory) {
        if (splitHistory.isEmpty()) {
            return;
        }
        while (!splitHistory.isEmpty()) {
            SplitHistory split = splitHistory.remove();
            epView.splitCake(split.getParentID(), split.getID(), split.getLeftBound(), split.getRightBound());;
        }
    }

    private void requestMarkFromGroup() {
        Agent currAgent = currGroup.getAgents().get(marks.size());
        requestMark(currAgent);
    }

    private void requestMark(Agent a) {
        ArrayList<String> information = new ArrayList<>();
        information.add("Agent: " + a.getId());
        information.add("On interval [" + currGroup.getLeftBound() + ", " + currGroup.getRightBound() + "]");
        information.add("Choose a mark in which both sides of the cake are equal");
        epView.askAgentForMark(information);
    }

    @Override
    public void markChosen(double mark) {
        if (!awaitingInput) {
            return;
        }
        
        marks.add(mark);

        if (marks.size() < currGroup.getAgents().size()) {
            requestMarkFromGroup();
        } else {
            awaitingInput = false;
            ep.receiveMarks(marks);
            nextPhase();
        }
    }

}
