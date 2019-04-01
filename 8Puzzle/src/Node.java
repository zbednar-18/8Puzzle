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
	
    private boolean visited;

    private String state;
    private ArrayList<Node> children;
    private Node parent;
    private int cost;
    private int estimatedCostToGoal;
    private int totalCost;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedCostToGoal) {
        this.estimatedCostToGoal = estimatedCostToGoal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


    // Constructor
    public Node(String state) {
        this.state = state;
        children = new ArrayList<Node>();
    }

    // Properties
    public String getCurrentState() {
        return state;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    // Public interface
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
//                                           Print the solution path and other statistics                                            \\
// ================================================================================================================================= \\
	public static void printOutput(Node goal, Set<String> visitedNodes, Node root) {
		
		int depth = 0;
		
        Stack<Node> moveList = new Stack<Node>();
        moveList.push(goal);
        while (!goal.getCurrentState().equals(root.getCurrentState())) {
            moveList.push(goal.getParent());
            goal = goal.getParent();
        }
        
        String initialState = root.getCurrentState();			// Initialize starting state
        String goalState;										// Initialize goal / ending state
                
        for (int i = moveList.size() - 1; i >= 0; i--) {
            System.out.println(" ");
            goalState = moveList.get(i).getCurrentState();
            if (!initialState.equals(goalState)) {
                System.out.println("************ Moving " + goalState.charAt(initialState.indexOf('0')) + " ************");
                depth++;
            }
            
            initialState = goalState;
            String topLine = (moveList.get(i).getCurrentState().substring(0, 3));
            String middleLine = (moveList.get(i).getCurrentState().substring(3, 6));
            String bottomLine = (moveList.get(i).getCurrentState().substring(6, 9));

            System.out.println("               " + topLine);
            System.out.println("               " + middleLine);
            System.out.println("               " + bottomLine);
            System.out.println("**********************************");

            System.out.println("");
        }
        
        // Print final statistics when the puzzle is solved
        System.out.println("============================= Statistics =============================");
        System.out.println("                     Solution path provided above                     ");
        System.out.println("");
        System.out.println("Number of moves to solve the puzzle:  " + (moveList.size() - 1));
        System.out.println("Depth traveled in the tree to find solution: " + depth);
        System.out.println("Number of nodes generated:  " + (visitedNodes.size()));
        System.out.println("======================================================================");
		
	}
	
}