import java.util.Scanner;
/**
 * 
 * @author Zackery Bednar
 * @author Thomas Zanecosky
 * 
 * This program will allow a user to create an 8-puzzle in a 3x3 grid then
 * display the solution path, level of the goal, and the number of nodes generated.
 *
 */
public class createPuzzle {
	
	public static void main(String[] args) {
		
		int row = 3, col = 3;
		int[][] matrix = new int[row][col];
		
		Scanner puzzleScanner = new Scanner(System.in);
		Scanner searchScanner = new Scanner(System.in);
		
		enterMatrixData(puzzleScanner, matrix, row, col);
						
		// Convert matrix back into a string
		StringBuffer rootStringBuffer = new StringBuffer();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				rootStringBuffer.append(matrix[i][j]);
			}
		}
		
		String root = rootStringBuffer.toString();  		// Initialize the root variable
		String goal = "123804765";							// Initialize the goal variable

		// Determine if the user's puzzle is solvable and if it is, solve the puzzle
		if(solvable(matrix)) {
			System.out.print("\n" + "This puzzle is able to be solved!" + "\n\n");
			Search search = new Search(new Node(root), goal);
			selectSearchMethod(searchScanner, search);
		} else {
			System.out.print("\n" + "This puzzle is not able to be solved!" + "\n\n");
			printMatrix(matrix, row, col);
		}
		
	}
	
// ================================================================================================================================= \\
	
	// Function to allow the user to select a search method
	public static void selectSearchMethod(Scanner searchScanner, Search search) {
		System.out.print("==========================================================================" + "\n\n");
		System.out.println("Which algorithm would you like to use to complete this puzzle?");
		System.out.println("	1 - Breadth First Search");
		System.out.println("	2 - A* - h(n) = number of misplaced tiles");
		System.out.print("	3 - A* - h(n) sum of distances of each tile to its goal position" + "\n\n");
		System.out.print("==========================================================================" + "\n");
		
		// If-else statement to determine which search algorithm to use based off user input
		int searchMethod = searchScanner.nextInt();				 // Initialize variable for scanner input
		
		if ( searchMethod == 1) {		
			search.breadthFirstSearch();						// h(n) = 0 (breadth-first search)
		} else if ( searchMethod == 2) {
			search.hammingAStarSearch();						// h(n) = number of misplaced tiles
		} else if ( searchMethod == 3) {
			search.manhattanAStarSearch();						// h(n) = sum of distances of each tile to its goal position
		} else {
			System.out.println("The entered number must be between the values of 0 and 8.");
			System.out.println("Please try again!");
			System.exit(0);
		}
	}
	
// ================================================================================================================================= \\
	
	// Function to prompt user to enter custom puzzle
	public static void enterMatrixData(Scanner puzzleScanner, int[][] matrix, int row, int col) {
		System.out.println("Please enter 9 values between 0 and 8: ");
		
		// Need to store the initial puzzle input from the user as a String
		String userInput = puzzleScanner.nextLine();
		String[] inputNumber = userInput.split("");
		
		// Create a 1D array to store the input string and parse the integers with , as delimeter
		int matrixNum[] = new int[9];
		for ( int i = 0; i < inputNumber.length; i++) {
				matrixNum[i] = Integer.parseInt(inputNumber[i]);
		}
		
		// Format the 1D array into a 2D array for display purposes
		for( int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if ( matrixNum[(j*row) + i] > 8) {
					System.out.println("You may not enter any values greater than 8!");
					System.out.println("Please try again!");
					System.exit(0);
				} else {
					matrix[j][i] = matrixNum[(j*row) + i];
				}
			}
		}
				
	}
	
// ================================================================================================================================= \\
	
	// Function to display the user's custom puzzle
	public static void printMatrix(int[][] matrix, int row, int col){
    	System.out.println("        This is your puzzle!       ");
    	System.out.println("===================================");
		        
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print("        " + matrix[i][j] + "");
			}       
			System.out.println();
		}
        System.out.println("===================================");
	}
	
// ================================================================================================================================= \\

	// Find the amount of tiles that have inversed positions
	static int getInverseCount(int[][] matrix) {
		int inverse_count = 0;
		for( int i = 0; i < 3 - 1; i++) {
			for( int j = i + 1; j < 3; j++) {
				if ( matrix[j][i] > 0 && matrix[j][i] > 0 && matrix[j][i] > matrix[i][j]) {
					inverse_count++;
				}
			}
		}
		return inverse_count;
	}

// ================================================================================================================================= \\
	
	// Determine whether or not the puzzle is solvable
	static boolean solvable(int[][] puzzle) {
		int inverseCount = getInverseCount(puzzle);
		return (inverseCount % 2 == 0);
	}
		
}