import java.util.Comparator;

/**
 * SortByMark
 */
public class SortByMark implements Comparator<Agent>{

    @Override
    public int compare(Agent a1, Agent a2) {
        if (a1.getMark() < a2.getMark()) {return -1;}
        if (a1.getMark() > a2.getMark()) {return 1;}
        return 0;
    }

}
