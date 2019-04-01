import java.awt.*;
import java.awt.List;
import java.util.*;

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
		
	private String currentState;
	private Node parent;
	private double cost; //cost of the node
	private double hCost; //heuristic cost
	private double aCost; //actual cost
	private ArrayList<Node> children;
	
	/**
	 * Constructor for the root Node
	 * 
	 * @param s
	 */
	public Node (String state) {
		this.currentState = state;
		children = new ArrayList<Node>();
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
	public Node(Node prev, String s, double c, double h) {
		parent = prev;
		currentState = s;
		cost = c;
		hCost = h;
		aCost = cost+hCost;
		
	}
	/**
	 * 
	 * @return the current state
	 */
	public String getCurrentState() {
		return currentState;
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
	 * @param Parent
	 */
	public void setParent(Node Parent) {
		this.parent = parent;
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
	
	/**
	 * 
	 * @param Cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
		
	/**
	 * 
	 * @param child
	 */
	public void addChild(Node child) {
		children.add(child);
	}
	
 // ================================================================================================================================= \\
 //                                              Get the next node in line                                                            \\
 // ================================================================================================================================= \\
	public static ArrayList<String> getSuccessors(String state) {
        ArrayList<String> successors = new ArrayList<String>();

        switch (state.indexOf("0")) {
            case 0: {
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            case 1: {
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            case 2: {

                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }
            case 3: {
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            case 4: {
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }
            case 5: {
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            case 6: {
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                break;

            }
            case 7: {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            case 8: {
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }

        return successors;


    }
	
// ================================================================================================================================= \\
//                                           Print the solution path and other information                                           \\
// ================================================================================================================================= \\
	public static void printOutput(Node goal, Set<String> visitedNodes, Node root) {
		
		Stack<Node> solution = new Stack<Node>();
		solution.push(goal);
		
		while( !goal.getCurrentState().equals(root.getCurrentState())) {
			solution.push(goal.getParent());
			goal = goal.getParent();
		}
		
		String initialState = root.getCurrentState();
		String goalState = "123804765";
		
		System.out.println(" Number of Transitions " + (solution.size() - 1));
		
	}
	
	
}