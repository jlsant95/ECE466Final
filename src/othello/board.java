package othello;

public class board {
	
	char theBoard[][];
	
	public board()
	{
		theBoard = new char[8][8];
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				theBoard[i][j] = ' ';
				
				if((i == 3 && j == 3) || (i == 4 && j == 4))
					theBoard[i][j] = '*';
				else if((i == 4 && j == 3) || (i == 3 && j == 4))
					theBoard[i][j] = 'o';
					
			}
		}
	}
	
	public board(board orig)
	{
		theBoard = new char[8][8];
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				theBoard[i][j] = orig.getSpace(i, j);
			}
		}
	}
	
	public char getSpace(int i, int j)
	{
		return theBoard[i][j];
	}
	
	public boolean setSpace(char piece, int i, int j)
	{
		if(!checkIfValidSpot(piece, i, j))
		{
			//TODO Print a message to pick a valid spot
			return false;
		}
		else
		{
			theBoard[i][j] = piece;
			
			printBoard();
			
			return true;
		}
	}
	
	public boolean checkIfValidSpot(char piece, int i, int j)
	{
		boolean valid = false;
		
		if(theBoard[i][j] == ' ')
			valid = changesToBoard(piece, i, j);
		
		return valid;
	}
	
	public board checkIfValidChild(char piece, int i, int j, board childBoard)
	{
		boolean valid = false;
		
		if(childBoard.getSpace(i, j) == ' ')
		{
			valid = childBoard.changesToBoard(piece, i, j);
			childBoard.theBoard[i][j] = piece;
		}
		
		if(!valid)
			return null;
		
		return childBoard;
	}
	
	public boolean changesToBoard(char piece, int i, int j)
	{
		//Move in all 8 directions until I hit a blank, out of bounds, or a piece 
		//of the same color with minimum one opposite color in between
		
		boolean madeChange = false;
		
		madeChange = omniDirectionMove(1,1,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(1,0,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(1,-1,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(0,1,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(0,-1,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(-1,1,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(-1,0,i,j,piece) || madeChange;
		madeChange = omniDirectionMove(-1,-1,i,j,piece) || madeChange;
		
		return madeChange;
		
	}
	
	public boolean omniDirectionMove(int verMove, int horMove, int i, int j, char piece)
	{
		int curVerPos = i;
		int curHorPos = j;
		
		if(checkOmniDirection(verMove, horMove, i, j, piece))
		{
			do
			{
				
				curVerPos += verMove;
				curHorPos += horMove;
				
				if(curVerPos > 7 || curVerPos < 0 || curHorPos > 7 || curHorPos < 0)
					return false;
				
				char curSpacePiece =  theBoard[curVerPos][curHorPos];
				
				if(curSpacePiece != piece)
				{
					theBoard[curVerPos][curHorPos] = piece;
				}
				else
					return true;
				
			}
			while((curVerPos >= 0 && curVerPos < 8 && curHorPos >= 0 && curHorPos < 8));
		}
		
		return false;
	}
	
	public boolean checkOmniDirection(int verMove, int horMove, int i, int j, char piece)
	{
		int curVerPos = i;
		int curHorPos = j;
		
		char oppoPiece = piece;
		boolean oneFlip = false;
		
		if(oppoPiece == '*')
			oppoPiece = 'o';
		else
			oppoPiece = '*';
		
		do
		{
			curVerPos += verMove;
			curHorPos += horMove;
			
			if(curVerPos > 7 || curVerPos < 0 || curHorPos > 7 || curHorPos < 0)
				return false;
			
			char curSpacePiece =  theBoard[curVerPos][curHorPos];
			
			if(curSpacePiece == oppoPiece)
			{
				oneFlip = true;
			}
			else if(curSpacePiece == ' ')
				return false;
			else if(curSpacePiece == piece && oneFlip == false)
				return false;
			else if(curSpacePiece == piece && oneFlip == true)
				break;
				
		}
		while((curVerPos >= 0 && curVerPos < 8 && curHorPos >= 0 && curHorPos < 8));
		
		return true;
	}
	
	public void copyBoard(board daddy)
	{
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				
				theBoard[i][j] =  daddy.getSpace(i, j);
			}
		}
		
	}
	
	public void printBoard()
	{	
		System.out.println("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		System.out.println("---+---+---+---+---+---+---+---+---+");
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(j ==0) {
					System.out.print(i + " ");
				}
				System.out.print(" | ");
				char curr = theBoard[i][j];
				System.out.print(curr);
				
				
			}
			System.out.print(" | ");
			System.out.println();
			
			//if(i < 7)
			System.out.println("---+---+---+---+---+---+---+---+---+");
		}
	}
	
}
