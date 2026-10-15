import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EvenPaz {
    private ArrayList<Agent> agents = new ArrayList<>();
    private Comparator<Agent> sortByMark = new SortByMark();
    private Scanner sc = new Scanner(System.in);
    private int numberOfAgents;

    public  EvenPaz(int numberOfAgents){
        this.numberOfAgents = numberOfAgents;
    }

    private void initialiseGame(){
        for(int agent = 0; agent < numberOfAgents; agent++){
            agents.add(new Agent(agent));
        }
        divide(agents, 0.0, 1.0);
        printAllocation(agents);
        sc.close();
    }

    public void printAllocation(ArrayList<Agent> agents){
        for(Agent a : agents){
            System.out.println(a.toString());
        }
    }

    private double askForCut(Agent a, double leftBound, double rightBound){
        System.out.println("Agent: " + a.getId());
        System.out.println("On interval [" + leftBound + ", " + rightBound + "]");
        System.out.println("Choose a mark in which both sides of the cake are equal.");
        double mark = sc.nextDouble();
        return mark;
    }

    private void divide(ArrayList<Agent> currAgents, double leftBound, double rightBound) {
        if (currAgents.size() == 1) {
            currAgents.get(0).givePiece(leftBound, rightBound);
            return;
        }

        for(Agent a : currAgents){
            double mark = askForCut(a, leftBound, rightBound);
            a.setMark(mark);
        }

        sort(currAgents);

        int middleAgentIndex = currAgents.size()/2;
        double middleAgentMark = currAgents.get(middleAgentIndex).getMark();
        ArrayList<Agent> left = new ArrayList<>(currAgents.subList(0, middleAgentIndex));
        ArrayList<Agent> right = new ArrayList<>(currAgents.subList(middleAgentIndex, currAgents.size()));

        divide(left, leftBound, middleAgentMark);
        divide(right, middleAgentMark, rightBound);

    }

    private void sort(ArrayList<Agent> agents){
        Collections.sort(agents, sortByMark);
    }

    public static void main(String[] args) {
        EvenPaz ep = new EvenPaz(5);
        ep.initialiseGame();
    }
}
