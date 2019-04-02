import java.util.*;

public class Search {
	private Node root;
	private String goal;
	
	// Getters and Setters for the root state and goal of a node
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getGoalState() {
        return goal;
    }

    public void setGoalState(String goal) {
        this.goal = goal;
    }

    public Search(Node root, String goal) {
        this.root = root;
        this.goal = goal;
    }
    
 // ================================================================================================================================= \\
 //                                              Breadth-First Search Function                                                        \\
 // ================================================================================================================================= \\
    public void breadthFirstSearch() {
    	Set<String> setOfStates = new HashSet<String>();
    	Node node = new Node(root.getCurrentState());
    	
    	Queue<Node> queue = new LinkedList<Node>();
    	Node currentNode = node;
    	
    	// While current node does not equal the goal node, continue looping to find solution while adding the solution
    	// path to a string. This will allow for the path to be displayed in the final output.
    	while( !currentNode.getCurrentState().equals(goal)) {
    		setOfStates.add(currentNode.getCurrentState());
    		List<String> nextNode = Node.getChildNode(currentNode.getCurrentState());
    		for(String nodes : nextNode) {
    			if (setOfStates.contains(nodes)) 
    				continue;								// Skips the initial child
    			setOfStates.add(nodes);
    			Node child = new Node(nodes);
    			currentNode.addChild(child);
    			child.setParent(currentNode);
    			queue.add(child);
    		}
    		currentNode = queue.poll();
    	}
    	Node.printOutput(currentNode, setOfStates, root);
    }
    
// ================================================================================================================================= \\
//                                                  Hamming Distance Search Function                                                 \\
// ================================================================================================================================= \\    
    public void hammingAStarSearch() {
    	Set<String> setOfStates = new HashSet<String>();
    	Node node = new Node(root.getCurrentState());
    	node.setTotalCost(0); 								// Set total cost for root node
    	
    	compareCost compareCost = new compareCost();
    	
    	PriorityQueue<Node> compareCostQueue = new PriorityQueue<Node>(10, compareCost);
    	Node currentNode = node;
    	
    	while (!currentNode.getCurrentState().equals(goal)) {
    		setOfStates.add(currentNode.getCurrentState());
    		List<String> nextChild = Node.getChildNode(currentNode.getCurrentState());
    		for (String nodes : nextChild) {
    			if (setOfStates.contains(nodes))
    				continue;								// Skips the initial child
    			setOfStates.add(nodes);
    			Node child = new Node(nodes);
    			currentNode.addChild(child);
    			child.setParent(currentNode);
    			child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getCurrentState().charAt(child.getParent().getCurrentState().indexOf('0'))), hammingDistance(child.getCurrentState(), goal));
    		}
    		currentNode = compareCostQueue.poll();
    	}
    	Node.printOutput(currentNode, setOfStates, root);
    }
    
    private int hammingDistance(String currentNode, String goal) {
    	int distance = 0;
    	for (int i = 0; i < currentNode.length(); i+= 1) {
    		if (currentNode.charAt(i) != goal.charAt(i)) {
    			distance += 1;
    		}
    	}
    	return distance;
    }
    
// ================================================================================================================================= \\
//                                                 Manhattan Distance Search Function                                                \\
// ================================================================================================================================= \\
    public void manhattanAStarSearch() {
    	Set<String> setOfStates = new HashSet<String>();
    	Node node = new Node(root.getCurrentState());
    	node.setTotalCost(0); 								// Set total cost for root node
    	
    	compareCost compareCost = new compareCost();
    	
    	PriorityQueue<Node> compareCostQueue = new PriorityQueue<Node>(10, compareCost);
    	Node currentNode = node;
    	
    	while (!currentNode.getCurrentState().equals(goal)) {
    		setOfStates.add(currentNode.getCurrentState());
    		List<String> nextChild = Node.getChildNode(currentNode.getCurrentState());
    		for (String nodes : nextChild) {
    			if (setOfStates.contains(nodes))
    				continue;								// Skips the initial child
    			setOfStates.add(nodes);
    			Node child = new Node(nodes);
    			currentNode.addChild(child);
    			child.setParent(currentNode);
    			child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getCurrentState().charAt(child.getParent().getCurrentState().indexOf('0'))), manhattanDistance(child.getCurrentState(), goal));
    		}
    		currentNode = compareCostQueue.poll();
    	}
    	Node.printOutput(currentNode, setOfStates, root);
    }
    
    private int manhattanDistance(String currentNode, String goal) {
    	int distance = 0;
    	for (int i = 0; i < currentNode.length(); i+= 1) {
    		if (currentNode.charAt(i) != goal.charAt(i)) {
    			distance += 1;
    		}
    	}
    	return distance;
    }
}