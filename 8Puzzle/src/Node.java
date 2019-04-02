import java.util.*;

/**
 * 
 * @author Zackery Bednar
 * @author Thomas Zanecoksy
 * 
 * Node.java
 * 
 * This class contains the getters and setters needed for creating a node (state). This is necessary
 * to keep track of the path that the program takes when determining the solution of the puzzle. This
 * class also contains a function to determine what the next node in the solution is. The function
 * switches through cases to determine what move was made which allows the program to determine what
 * tile was moved in the process (as shown in the output). This class also contains the function that
 * is called when printing the output.
 *
 * Node goal, Set<String> visitedNodes, and Node root are passed into the function so that we are able
 * to correctly push elements (states) onto a stack and proceed to output them once the solution is reached.
 */

public class Node {
	
    private boolean visited;

    private String state;
    private ArrayList<Node> children;
    private Node parent;
    private int totalCost;
    private int depth;

    /**
     * Getters and setters for the depth variable
     * 
     * Allows us to determine what level the program reached to find a solution
     * 
     * @param depth
     * @return depth
     */
    public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

    /**
     * Getters and setters for the visited variable
     * 
     * Allows us to determine if a state has previously been visited
     *  
     * @param visited
     * @return visited (true / false)
     */
	public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Getters and setters for the totalCost variable
     * 
     * Allows us to determine the totalCost of a move when dealing with a heuristic search
     * 
     * @param totalCost
     * @return totalCost
     */
    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

    /**
     * Getters and setters for the parent variable
     * 
     * Allows us to determine the parent node to a child
     * 
     * @param parent
     * @return parent
     */
    
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    
    /**
     * Getter and setter for the state variable
     * 
     * Allows us to determine what state (node) we are in
     * 
     * @param state
     * @return state
     */
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
		return state;
	}

    public String getCurrentState() {
        return state;
    }
    
    /**
     * Setter for the children variable
     * 
     * Allows us to determine what parent has a child and vice versa when pushing elements
     * from a stack for output
     * 
     * @param children
     */
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	/**
	 * Constructor for the Node variable
	 * 
	 * Allows us to create a node and determine if it has a child or not
	 * 
	 * @param state
	 */
	public Node(String state) {
        this.state = state;
        children = new ArrayList<Node>();
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
	
    /**
	 * 
	 * -------------
     * | 0 | 1 | 2 |	
     * | 3 | 4 | 5 |	
     * | 6 | 7 | 8 |
     * -------------
	 * 
	 * This is a representation of how the board is created and how each case below
	 * handles each index. Corner tiles will only have two possible options (case 0,
	 * case 2, case 6, case 8). Tiles in the middle of a row or column will have
	 * three possible cases (case 1, case 3, case 5, case 7). The center tile is the
	 * only tile with 4 possible cases (case 4).
	 * 
	 * This function will simply return the child node of the current (parent) node
	 * and allow us to easily push it onto a stack and allow us to display it
	 * properly.
	 * 
	 * @param state
	 * @return
	 */
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
            
            // Add the next node to a list when a move is made from the very middle of the board
            case 4: {
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                childNode.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }

            // Add the next node to a list when a move is made from the middle right hand side of the board
            case 5: {
                childNode.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                childNode.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                childNode.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            
            // Add the next node to a list when a move is made from the bottom left hand corner of the board
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
	
    /**
     * This function provides the output in console. It will display each visited node on route to the goal as well as
     * tell the user which tile was moved. In addition to this it will provide us with extra statistics from the search
     * including: the number of moves it took to solve the puzzle, how many levels the algorithm traveled to find the 
     * solution, and the total number of nodes that were generated and visited before it took the solution path.     * 
     * 
     * @param goal
     * @param visitedNodes
     * @param root
     */
	public static void printOutput(Node goal, Set<String> visitedNodes, Node root) {
		
		int depth = 0;											// Initialize variable to correctly retrieve depth of search
        String initialState = root.getCurrentState();			// Initialize variable for starting state
        String goalState;										// Initialize variable for goal / ending state        
        int moveNum = 1;										// Initialize variable to display correct move #
        
        Stack<Node> moveList = new Stack<Node>();				// Initialize a stack to push nodes into
        moveList.push(goal);									// Push the goal onto the stack first
        
        // This while loop compares the current state to the goal state. The loop will push the parent of the goal on top of it in the stack,
        // then the loop will push the parent of the parent of the goal on top of it in succession until the solution is found.
        while (!goal.getCurrentState().equals(root.getCurrentState())) {
            moveList.push(goal.getParent());
            goal = goal.getParent();
        }
        
    	// First retrieve root state and print it, then print the path taken to reach the goal
    	System.out.println("       This is your puzzle!         ");
    	System.out.println("===================================");
        for (int i = moveList.size() - 1; i >= 0; i--) {
            goalState = moveList.get(i).getCurrentState();
            if (!initialState.equals(goalState)) {						// If the initialState does not Equal the goal state, make the next move
            	System.out.println("    Move #" + moveNum++ + " - " + goalState.charAt(initialState.indexOf('0')) + " has been moved");
                System.out.println("===================================");
                depth++;												// Add a level to the depth
            }

            initialState = goalState;
            
            // Take the substrings from indexes and place them into a String variable. This was done to help format console display
            String topLine = (moveList.get(i).getCurrentState().substring(0, 3));
            String middleLine = (moveList.get(i).getCurrentState().substring(3, 6));
            String bottomLine = (moveList.get(i).getCurrentState().substring(6, 9));

            // Formatting
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