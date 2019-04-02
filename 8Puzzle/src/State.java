import java.util.ArrayList;
/**
 * 
 * @author Thomas Zanecosky
 * @author Zackery Bednar
 * 
 * The state interface. Gets a method to check if goal is found, then creates
 * a successor list, and finds the cost of the current state.
 *
 */
public interface State {
	boolean isGoalMet();
	
	ArrayList<State> createNext();
	
	int getCost();
	
	void print();
	
	boolean equal(State s);
	
	
}
