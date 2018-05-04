package othello;

import java.util.Scanner;

public class othello {
	
	public static void main(String[] args) {
		
		//board orig = new board();
		Node orig = new Node();

//		orig.setSpace('*', 3, 5);
//		orig.setSpace('*', 0, 0);
//		orig.setSpace('o', 2, 3);
//		orig.setSpace('*', 5, 2);
		
		//orig.printBoard();
		
		
		int row;
		int column;
		String input;
		int i;
		boolean black = true;
		boolean white = true;
		boolean depthFive;
		long totalMinimaxCount = 0;
		long totalAlphaBetaCount = 0;
		
		System.out.println("Choose Difficulty:");
		System.out.println("0. Easy (Depth 3)");
		System.out.println("1. Hard (Depth 5)");
		Scanner scan = new Scanner(System.in);
		input = scan.nextLine();
		
		
		if (Character.getNumericValue(input.charAt(0)) == 0) {
			depthFive = false;
		}
		else {
			depthFive = true;
		}
		
		//scan.close();
		
		orig.curBoard.printBoard();
		
		while(true) {
			for(i = 0; i<3; ++i){
				System.out.println("Please enter the position of a piece (*): row,column");
				Scanner scanner = new Scanner(System.in);
				input = scanner.nextLine();
				//scanner.close();
				
				row = Character.getNumericValue(input.charAt(0));
				column = Character.getNumericValue(input.charAt(2));
				
				if(!orig.curBoard.setSpace('*', row, column)) {
					System.out.println("Sorry that move isn't valid");
				}
				else {
					break;
				}
				
			}
			
			if(i == 3) {
				black = false;
			}
			else {
				black = true;
			}
			
			if(white == false && black == false) {
				System.out.println("Game ended");
				System.out.println("There are" + orig.countBlack() + "black stones");
				System.out.println("There are" + orig.countWhite() + "white stones");
				System.out.println("Total states explored for minimax: " + totalMinimaxCount);
				System.out.println("Total states explored for alpha-beta pruning: " + totalAlphaBetaCount);
				break;
				
			}
			
			
			graph currGraph = new graph(orig);
			
			if(!depthFive) {
				currGraph.alphaBetaSearch();
			}
			else {
				currGraph.alphaBetaSearchDepthFive();
			}

			
			System.out.println("States explored for minimax: " + currGraph.minimaxCount);
			System.out.println("States explored with pruning: " + currGraph.alphaBetaCount);
			
			totalMinimaxCount = totalMinimaxCount + currGraph.minimaxCount;
			totalAlphaBetaCount = totalAlphaBetaCount + currGraph.alphaBetaCount;
			
//			System.out.println("Root alpha value: " + orig.alpha);
//			
//			for(i = 0; i < orig.children.size(); ++i)
//			{
//				System.out.println(orig.children.get(i).beta);
//			}
//			
			for(i = 0; i < orig.children.size(); ++i) {
				if(orig.children.get(i).beta == orig.alpha) {
					break;
				}
				
			}
			//check if any valid moves for AI
			if(orig.children.size() == 0) {
				System.out.println("AI couldn't make a move");
				white = false;
			}
			else {
				white = true;
			}
			
			if(white == false && black == false) {
				System.out.println("Game ended");
				if(orig.countBlack() < orig.countWhite()) {
					System.out.println("YOU LOSE!!");
				}
				else if(orig.countBlack() > orig.countWhite()) {
					System.out.println("YOU WIN!!");
				}
				else if(orig.countBlack() == orig.countWhite()) {
					System.out.println("IT'S A TIE!!");
				}
				System.out.println("There are " + orig.countBlack() + " black stones");
				System.out.println("There are " + orig.countWhite() + " white stones");
				System.out.println("Total states explored for minimax: " + totalMinimaxCount);
				System.out.println("Total states explored for alpha-beta pruning: " + totalAlphaBetaCount);
				break;
			}
				
			if(i != orig.children.size()) {
				orig.curBoard.copyBoard(orig.children.get(i).curBoard);
			}
			else if(orig.children.size() != 0) {
				orig.curBoard.copyBoard(orig.children.get(0).curBoard);
			}
			orig.alpha = 0;
			

			orig.children.clear();
			orig.curBoard.printBoard();
			
//			orig.printBoard();
			
//			for(i = 0; i<3; ++i){
//				System.out.println("Please enter the position of a piece (o): row,column");
//				Scanner scanner = new Scanner(System.in);
//				input = scanner.nextLine();
//				//scanner.close();
//				
//				row = Character.getNumericValue(input.charAt(0));
//				column = Character.getNumericValue(input.charAt(2));
//				
//				if(!orig.setSpace('o', row, column)) {
//					System.out.println("Sorry that move isn't valid");
//				}
//				else {
//					break;
//				}
//				
//			}
		
		}
		
		
		
	}
}
