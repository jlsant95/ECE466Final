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
		orig.curBoard.printBoard();
		
		int row;
		int column;
		String input;
		int i;
		boolean black = true;
		boolean white = true;
		
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
				break;
				
			}
			
			
			graph currGraph = new graph(orig);
			currGraph.alphaBetaSearch();
			
			System.out.println(currGraph.minimaxCount);
			System.out.println(currGraph.alphaBetaCount);
			
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
				white = false;
			}
			else {
				white = true;
			}
			
			if(white == false && black == false) {
				System.out.println("Game ended");
				System.out.println("There are " + orig.countBlack() + " black stones");
				System.out.println("There are " + orig.countWhite() + " white stones");
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
