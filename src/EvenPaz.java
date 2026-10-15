import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EvenPaz {
    private ArrayList<Agent> agetns = new ArrayList<>();
    private Comparator<Agent> sortByMark = new SortByMark();
    private Scanner sc = new Scanner(System.in);
    private int numberOfAgents;

    public  EvenPaz(int numberOfAgents){
        this.numberOfAgents = numberOfAgents;
    }

    private void sort(ArrayList<Agent> agents){
        Collections.sort(agents, sortByMark);
    }
}
