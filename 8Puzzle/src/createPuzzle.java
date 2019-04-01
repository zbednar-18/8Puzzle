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
		
//		System.out.println(root);
		Scanner searchScanner = new Scanner(System.in);
		Search search = new Search(new Node(root), goal);
		selectSearchMethod(searchScanner, search);
		
	}
	
// ================================================================================================================================= \\
	
	public static void selectSearchMethod(Scanner searchScanner, Search search) {
		System.out.println("Which type of search algorithm would you like to use to complete this puzzle?");
		System.out.println("	1 - Breadth First Search");
		System.out.println("	2 - A* using the number of misplaced tiles as the heuristic");
		System.out.println("	3 - A* using the sum of the distances of each tile to their goal position as the heuristic");
		
		int searchMethod = searchScanner.nextInt();
		
		if ( searchMethod == 1) {
			search.breadthFirstSearch();
		} else if ( searchMethod == 2) {
			System.out.println("Under Construction");
		} else if ( searchMethod == 3) {
			System.out.println("Under Construction");
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
		String[] inputNumber = userInput.split(",");
		
		// Create a 1D array to store the input string and parse the integers with , as delimeter
		int matrixNum[] = new int[9];
		for ( int i = 0; i < inputNumber.length; i++) {
				matrixNum[i] = Integer.parseInt(inputNumber[i]);
		}
		
		// Format the 1D array into a 2D array for display purposes
		for( int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if ( matrixNum[(j*row) + i] > 8) {
					System.out.println("The entered number must be between the values of 0 and 8.");
					System.out.println("Please try again!");
					System.exit(0);
				} else {
					matrix[j][i] = matrixNum[(j*row) + i];
				}
			}
		}
		
		// Determine if the user's puzzle is solvable
		if(solvable(matrix)) {
			System.out.println("This puzzle is solvable.");
			printMatrix(matrix, row, col);
		} else {
			System.out.println("This puzzle is not solvable.");
			printMatrix(matrix, row, col);
		}
		
	}
	
// ================================================================================================================================= \\
	
	// Function to display the user's custom puzzle
	public static void printMatrix(int[][] matrix, int row, int col){
		System.out.println("This is the starting matrix:  ");
		        
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j]+"\t");
			}       
			System.out.println();
		}
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