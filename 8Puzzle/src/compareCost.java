import java.util.Comparator;

/**
 * 
 * @author Zackery Bednar
 * @author Thomas Zanecosky
 * 
 * compareCost.java
 * 
 * This java class simply allows for the program to call the compareCost
 * object to compare the cost between two separate nodes (x and y). This
 * will be used when determining the heuristic for the Hamming distance
 * and the Manhattan distance which are heuristics required to be used
 * by the project.
 *
 */

public class compareCost implements Comparator<Node>{
	
    @Override
    public int compare(Node x, Node y) {
        if (x.getTotalCost() < y.getTotalCost()) {
            return -1;
        }
        if (x.getTotalCost() > y.getTotalCost()) {
            return 1;
        }
        return 0;
    }
    
}
