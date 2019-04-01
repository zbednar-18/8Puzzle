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

    public String getGoalSate() {
        return goal;
    }

    public void setGoalSate(String goal) {
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
    	node.setCost(0);
    	
    	Queue<Node> queue = new LinkedList<Node>();
    	Node currentNode = node;
    	
    	// While current node does not equal the goal node, continue looping to find solution
    	while( !currentNode.getCurrentState().equals(goal)) {
    		setOfStates.add(currentNode.getCurrentState());
    		List<String> nextNode = Node.getSuccessors(currentNode.getCurrentState());
    		for (String nodes : nextNode) {
    			if (setOfStates.contains(nodes)) 
    				continue;
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
//    public void hammingAStarSearch() {
//    	
//    }
    
    
    
// ================================================================================================================================= \\
//                                                 Manhattan Distance Search Function                                                \\
// ================================================================================================================================= \\
//    public void manhattanAStarSearch() {
//    	
//    }
}
