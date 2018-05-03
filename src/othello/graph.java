package othello;

import java.util.ArrayList;
import java.util.Stack;

public class graph {
	
	public ArrayList<Node> nodes;	//don't forget to allocate memory
	public Stack<Node> stack;
	public Node rootNode;
	
	public graph() {
		nodes = new ArrayList<Node>();
		
	}
	public graph(Node initial) {
		nodes = new ArrayList<Node>();
		stack = new Stack<Node>();
		rootNode = initial;
		nodes.add(initial);
		stack.push(initial);
	}
	
	public int alphaBetaSearch() {
		int i = 0;
		
		Node currNode = stack.peek();
		
		//depth first search
		
		while(stack.size() != 0) {
			
			currNode.visited = true;
			
			if(currNode.depth == 3) {
			
				//currNode.calculateH
				stack.pop();
				
				//update parent alpha
				if(currNode.hVal >= currNode.parent.alpha) {
					currNode.parent.alpha = currNode.hVal;
					
				}
				
			}	
			
			//need to add children nodes to stack
			else if(currNode.children.size() == 0) {
				
				//currNode.createChildren();	
				
				//add first child to stack as long as visited flag false
				
			}
			else {
				
				//add children to stack as long as visited flag false
				for(i=0; i < currNode.children.size(); ++i) {
					if(currNode.children.get(i).visited == false) {
						stack.push(currNode.children.get(i));
						break;
					}
				}
				//if no children with visited flag false,
				if(i == currNode.children.size()) {
					stack.pop();
				}
				
				//Need to update min/max values of parent
				if(currNode.parent != null ) {
					if(currNode.depth %2 == 1 && currNode.beta >= currNode.parent.alpha) {
						currNode.parent.alpha = currNode.beta;
					}
					else if(currNode.depth %2 == 0 && currNode.alpha <= currNode.parent.beta){
						currNode.parent.beta = currNode.alpha;
					}
					
				}
				
			}

			//go to next node depth-wise
			//stack.pop();
			currNode = stack.peek();
					
		}
		
		
		return 0;
		
		
	}
	
	
	
	
	
		
}
