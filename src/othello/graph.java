package othello;

import java.util.ArrayList;
import java.util.Stack;

public class graph {
	
	public ArrayList<Node> nodes;	//don't forget to allocate memory
	public Stack<Node> stack;
	public Node rootNode;
	public int minimaxCount;
	public int alphaBetaCount;
	
	public graph() {
		nodes = new ArrayList<Node>();
		
	}
	public graph(Node initial) {
		nodes = new ArrayList<Node>();
		stack = new Stack<Node>();
		rootNode = initial;
		nodes.add(initial);
		stack.push(initial);
		minimaxCount = 0;
		alphaBetaCount = 0;
	}
	
	public int alphaBetaSearch() {
		
		int i = 0;
		
		Node currNode = stack.peek();
		
		//depth first search
		
		while(stack.size() != 0) {
			if(currNode.visited == false) {
				currNode.visited = true;
				minimaxCount++;
				if(currNode.pruned == false) {
					alphaBetaCount++;
				}
				
			}
			
			if(currNode.depth == 3) {
			
				currNode.calculateHeuristic();
				//System.out.println(currNode.hVal);
				stack.pop();
				
				//update parent alpha
				if(currNode.hVal >= currNode.parent.alpha) {
					currNode.parent.alpha = currNode.hVal;
					
				}
				
			}	
			
			//need to add children nodes to stack
			else if(currNode.children.size() == 0) {
				
				currNode.addChildren();	
				
				//TODO here check if size is 0
				if(currNode.children.size() != 0) {
					//add first child to stack as long as visited flag false
					stack.push(currNode.children.get(0));
				}
				else {
					stack.pop();
				}

				
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
				
				for(i = 0; i < currNode.children.size(); ++i) {
					
					if(currNode.depth %2 == 1 && currNode.children.get(i).alpha < currNode.beta && currNode.children.get(i).alpha != 0) {
						currNode.beta = currNode.children.get(i).alpha;
					}
					else if(currNode.depth %2 == 0 && currNode.children.get(i).beta > currNode.alpha && currNode.children.get(i).beta != 100) {
						currNode.alpha = currNode.children.get(i).beta;
					}
				
				}
			
			//alpha beta pruning
			if(currNode.parent != null ) {
				if(currNode.depth %2 == 1 && currNode.beta <= currNode.parent.alpha) {
					alphaBetaPrune(currNode);
				}
				else if(currNode.depth %2 == 0 && currNode.alpha >= currNode.parent.beta){
					alphaBetaPrune(currNode);
				}
				
			}
				
			}
			
			if(stack.empty())
				break;
			else
				currNode = stack.peek();
					
		}
		
		
		return 0;
		
		
	}
	
	public void alphaBetaPrune(Node currNode) {
		int i;
		int j;
		
		for(i = 0; i < currNode.children.size(); ++i) {
			
			if(currNode.children.get(i).visited == false) {
				currNode.children.get(i).pruned = true;
				
				for(j = 0; j < currNode.children.get(i).children.size(); ++j) {
					currNode.children.get(i).children.get(j).pruned = true;
				}
				
				
			}
			
		}
		
		
		
	}
	
	
	
	
	
		
}
