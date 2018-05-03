package othello;

import java.util.ArrayList;

public class Node {
	protected board curBoard;
	protected int alpha;
	protected int beta;
	protected int depth;
	protected ArrayList<Node> children;
	protected int hVal; //heuristic value

	public Node () {
		curBoard = new board();
		alpha = 0;
		beta = 100;
		depth = 0;
		children = new ArrayList<Node>();
		hVal = -1;
	}
	
	public Node(int daddy)
	{
		alpha = 0;
		beta = 100;
		depth = daddy + 1;
		children = new ArrayList<Node>();
		hVal = -1;
		
		if(depth == 3)
		{
			hVal = 0; //TODO count board for hVal of Nose
		}
	}
	
	public void addChild(Node child)
	{
		
	}

}


