package othello;

import java.util.Scanner;

public class othello {
	
	public static void main(String[] args) {
		
		board orig = new board();
		
		
//		orig.setSpace('*', 3, 5);
//		orig.setSpace('*', 0, 0);
//		orig.setSpace('o', 2, 3);
//		orig.setSpace('*', 5, 2);
		
		orig.printBoard();
		
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
				
				if(!orig.setSpace('*', row, column)) {
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
				break;
				
			}
			
			orig.printBoard();
			
			for(i = 0; i<3; ++i){
				System.out.println("Please enter the position of a piece (o): row,column");
				Scanner scanner = new Scanner(System.in);
				input = scanner.nextLine();
				//scanner.close();
				
				row = Character.getNumericValue(input.charAt(0));
				column = Character.getNumericValue(input.charAt(2));
				
				if(!orig.setSpace('o', row, column)) {
					System.out.println("Sorry that move isn't valid");
				}
				else {
					break;
				}
				
			}
			
			orig.printBoard();
			
			if(i == 3) {
				white = false;
			}
			else {
				white = true;
			}
			
			if(white == false && black == false) {
				System.out.println("Game ended");
				break;
				
			}
		
		}
		
		
		
	}
}
