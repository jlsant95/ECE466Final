package othello;

import java.util.ArrayList;

<<<<<<< HEAD
public class Node {
	protected board curBoard;
	protected int alpha;
	protected int beta;
	protected int depth;
	protected ArrayList<Node> children;
	protected int hVal; //heuristic value
=======
public class Node
{
	private board curBoard;
	private int alpha;
	private int beta;
	private int depth;
	private ArrayList<Node> children;
	private int hVal; //heuristic value
>>>>>>> 8cafa1fa36dd661861a62ac7c296e758916f177a

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

	public void calculateHeuristic(board current)
	{
		int count = 0;
		
		for(int rows = 0; rows < 8; rows++)
		{
			for(int cols = 0; cols < 8; cols++)
			{
				if(curBoard.theBoard[rows][cols] == 'o')
				{
					count++;
				}
			}
		}
		
		hVal = count;
	}
	
}


