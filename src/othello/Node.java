package othello;

import java.util.ArrayList;

public class Node {
	protected board curBoard;
	protected int alpha;
	protected int beta;
	protected int depth;
	protected ArrayList<Node> children;
	protected int hVal; //heuristic value
	protected Node parent;
	protected boolean visited;
	protected boolean pruned;

	public Node () {
		curBoard = new board();
		alpha = 0;
		beta = 100;
		depth = 0;
		children = new ArrayList<Node>();
		hVal = -1;
		visited = false;
	}
	
	public Node(int daddy)
	{
		curBoard = new board();
		alpha = 0;
		beta = 100;
		depth = daddy + 1;
		children = new ArrayList<Node>();
		hVal = -1;
		visited = false;
		
		if(depth == 3)
		{
			hVal = 0; //TODO count board for hVal of Nose
		}
	}
	
	public void addChildren()
	{
		
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				Node child = new Node(depth);
				char piece = 'o';
				//System.out.println(i + " " + j);
				child.curBoard.copyBoard(curBoard);
				
				//child.curBoard.printBoard();
				
				if(depth % 2 == 1)
					piece = '*';
				
				
				if(curBoard.checkIfValidChild(piece, i, j, child.curBoard) != null)
				{
//					System.out.println("Current depth: " + child.depth);
//					System.out.println();
//					child.curBoard.printBoard();
//					System.out.println();
					
					
					child.parent = this;
					children.add(child);
					
				}
				
			}
		}
	}

	public void calculateHeuristic()
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


