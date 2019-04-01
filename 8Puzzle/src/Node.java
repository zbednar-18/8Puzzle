/**
 * 
 * @author Thomas Zanecoksy
 * @author Zackery Bednar
 * 
 * Node class that will be the wrapper to the State interface. Will get the cost of the state
 * and the cost of the state's parent node.
 *
 */
public class Node {
	
	private State current;
	private Node parent;
	private double cost; //cost of the node
	private double hCost; //heuristic cost
	private double aCost; //actual cost
	
	/**
	 * Constructor for the root Node
	 * 
	 * @param s
	 */
	public Node (State s) {
		current = s;
		parent = null;
		cost = 0;
		hCost = 0;
		aCost = 0;
		
	}
	
	/**
	 * Constructor for all other nodes
	 * @param prev
	 * @param s
	 * @param c
	 * @param h
	 */
	public Node(Node prev, State s, double c, double h) {
		parent = prev;
		current = s;
		cost = c;
		hCost = h;
		aCost = cost+hCost;
		
	}
	/**
	 * 
	 * @return the current state
	 */
	public State getCurrent() {
		return current;
	}

	/**
	 * 
	 * @return return the parent node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * 
	 * @return the cost to get the node
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * 
	 * @return the heuristic cost to get the node
	 */
	public double gethCost() {
		return hCost;
	}
	/**
	 * 
	 * @return the actual cost for A*
	 */
	public double getaCost() {
		return aCost;
	}

	/**
	 * 
	 * @param aCost
	 */
	public void setaCost(double aCost) {
		this.aCost = aCost;
	}
	
}