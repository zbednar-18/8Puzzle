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
	public static ArrayList<String> getChildNode(String state) {
        ArrayList<String> childNode = new ArrayList<String>();

        switch (state.indexOf("0")) {
        
        	// Add the next node to a list when a move is made from the top left hand corner of the board
            case 0: {
                childNode.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                childNode.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            
        	// Add the next node to a list when a move is made from the top row middle of the board
            case 1: {
                childNode.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                childNode.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                childNode.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            
        	// Add the next node to a list when a move is made from the top right hand corner of the board
            case 2: {
                childNode.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                childNode.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }
            
        	// Add the next node to a list when a move is made from the middle row left hand side of the board
            case 3: {
                childNode.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                childNode.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                childNode.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            
            // Add succcesor when a move is made from the very middle of the board
            case 4: {
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }

            // Add succcesor when a move is made from the middle right hand side of the board
            case 5: {
                childNode.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                childNode.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                childNode.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            
            // Add succcesor when a move is made from the bottom left hand corner of the board
            case 6: {
                childNode.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                childNode.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                break;

            }
            
            // Add the next node to a list when a move is made from the bottom middle of the board
            case 7: {
                childNode.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                childNode.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                childNode.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            
            // Add the next node to a list when a move is made from the bottom right hand corner of the board
            case 8: {
                childNode.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                childNode.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }
        return childNode;
    }
	
// ================================================================================================================================= \\
//                                           Print the solution path and other statistics                                            \\
// ================================================================================================================================= \\
	public static void printOutput(Node goal, Set<String> visitedNodes, Node root) {
		
		int depth = 0;											// Initialize variable to correctly retrieve depth of search
        String initialState = root.getCurrentState();			// Initialize variable for starting state
        String goalState;										// Initialize variable for goal / ending state        
        int moveNum = 1;										// Initialize variable to display correct move #
        
        Stack<Node> moveList = new Stack<Node>();
        moveList.push(goal);
        while (!goal.getCurrentState().equals(root.getCurrentState())) {
            moveList.push(goal.getParent());
            goal = goal.getParent();
        }
        
    	System.out.println("       This is your puzzle!         ");
    	System.out.println("===================================");
    	
    	// First retrieve root state and print it, then print the path taken to reach the goal
        for (int i = moveList.size() - 1; i >= 0; i--) {
            goalState = moveList.get(i).getCurrentState();
            if (!initialState.equals(goalState)) {
            	System.out.println("    Move #" + moveNum++ + " - " + goalState.charAt(initialState.indexOf('0')) + " has been moved");
                System.out.println("===================================");
                depth++;
            }
            
            initialState = goalState;
            String topLine = (moveList.get(i).getCurrentState().substring(0, 3));
            String middleLine = (moveList.get(i).getCurrentState().substring(3, 6));
            String bottomLine = (moveList.get(i).getCurrentState().substring(6, 9));

            System.out.println(topLine.replace("", "        "));
            System.out.println(middleLine.replace("", "        "));
            System.out.println(bottomLine.replace("", "        "));        
            System.out.print("===================================" + "\n\n");
        }
        
        // Print final statistics when the puzzle is solved
        System.out.println("============================= Statistics =============================");
        System.out.println("                   **Solution path provided above**                   " + "\n");
        System.out.println("Number of moves to solve the puzzle: " + (moveList.size() - 1));
        System.out.println("Depth traveled in the tree to find solution: " + depth);
        System.out.println("Number of nodes generated: " + (visitedNodes.size()));
        System.out.println("======================================================================");
	}
	
}