package SwingApproach;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Controller implements MarkListener{
    private EvenPaz ep;
    private EvenPazView epView;
    private MarkListener listener;
    private ArrayList<Double> marks = new ArrayList<>();
    private boolean awaitingInput = false;
    private AgentGroup currGroup;

    public Controller(EvenPaz ep, EvenPazView epView){
        this.ep=ep;
        this.epView=epView;
        this.epView.setMarkListener(this);
    }

    public void start(){
        ep.resume();
        nextPhase();
    }

    private void nextPhase(){
        switch (ep.getCurrState()) {
            case AWAITING_USER_INPUT:
                marks.clear();
                awaitingInput = true;
                currGroup = ep.getAgentGroups().peek();
                requestMark();
                break;
            case DONE:
                
                break;
            case COMPLETE:

                break;
            default:
                break;
        }
    }

    private void requestMark(){
        ArrayList<String> information = new ArrayList<>();
        Agent currAgent = currGroup.getAgents().get(marks.size());
        information.add("Agent: " + currAgent.getId());
        information.add("On interval [" + currGroup.getLeftBound() + ", " + currGroup.getRightBound() + "]");
        information.add("Choose a mark in which both sides of the cake are equal");
        epView.askAgentForMark(information);
    }

    @Override
    public void markChosen(double mark) {
        if (!awaitingInput) { return; }
        marks.add(mark);

        if (marks.size() < currGroup.getAgents().size()) {
            requestMark();
        } else {
            awaitingInput = false;
            ep.receiveMarks(marks);
            nextPhase();
        }
    }

}
