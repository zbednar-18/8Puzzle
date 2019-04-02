import java.util.Scanner;

/**
 * 
 * @author Zackery Bednar
 * @author Thomas Zanecosky
 * 
 * createPuzzle.java
 * 
 * This program allows a user to enter a custom 8 Puzzle then select
 * which type of algorithm they would like to use to solve it. The user
 * has three choices of algorithms and each performs in a different way.
 * Most notably, some algorithms visit less states than others and
 * subsequently cuts a lot of time off of computation due to the
 * heuristics used.
 *
 */

public class createPuzzle {

	public static void main(String[] args) {

		int row = 3, col = 3; 								// Initialize row and col variables
		int[][] matrix = new int[row][col]; 				// Initialize the game's matrix

		Scanner puzzleScanner = new Scanner(System.in); 	// Initialize scanner to read in the user's puzzle
		Scanner searchScanner = new Scanner(System.in); 	// Initialize scanner to read which search algorithm to use

		enterMatrixData(puzzleScanner, matrix, row, col); 	// Call function for user to enter data

		StringBuffer rootStringBuffer = new StringBuffer(); // Initialize StringBuffer to convert matrix back into a
															// string
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				rootStringBuffer.append(matrix[i][j]);
			}
		}

		String root = rootStringBuffer.toString(); // Initialize the root variable by making rootStringBuffer a toString();
		String goal = "123804765"; // Initialize the goal variable

		// Determine if the user's puzzle is solvable and if it is, solve the puzzle
		if (solvable(matrix)) {
			System.out.print("\n" + "This puzzle is able to be solved!" + "\n\n");
			Search search = new Search(new Node(root), goal);
			selectSearchMethod(searchScanner, search);
		} else {
			System.out.print("\n" + "This puzzle is not able to be solved!" + "\n\n");
			printMatrix(matrix, row, col);
		}

	}

	/**
	 * Function that is called to allow the user to determine which type of search
	 * they would like to use to solve the puzzle.
	 * 
	 * 1 - h(n) = 0 (Breadth-First Search) 2 - h(n) = number of misplaced tiles 3 -
	 * h(n) = sum of distances of each tile to its goal position
	 * 
	 * @param searchScanner
	 * @param search
	 */
	public static void selectSearchMethod(Scanner searchScanner, Search search) {
		System.out.print("==========================================================================" + "\n\n");
		System.out.println("Which algorithm would you like to use to complete this puzzle? (Enter 1, 2, or 3)");
		System.out.println("	1 - Breadth First Search");
		System.out.println("	2 - A* - h(n) = number of misplaced tiles");
		System.out.print("	3 - A* - h(n) sum of distances of each tile to its goal position" + "\n\n");
		System.out.print("==========================================================================" + "\n");

		int searchMethod = searchScanner.nextInt(); // Initialize variable for scanner input
		if (searchMethod == 1) {
			search.breadthFirstSearch();			// h(n) = 0 (breadth-first search)
		} else if (searchMethod == 2) {
			search.hammingAStarSearch(); 			// h(n) = number of misplaced tiles
		} else if (searchMethod == 3) {
			search.manhattanAStarSearch(); 			// h(n) = sum of distances of each tile to its goal position
		} else {
			System.out.println("The entered number must be between the values of 0 and 8.");
			System.out.println("Please try again!");
			System.exit(0);
		}
	}

	/**
	 * Function that is called to allow a user to enter matrix data.
	 * 
	 * @param puzzleScanner
	 * @param matrix
	 * @param row
	 * @param col
	 */
	public static void enterMatrixData(Scanner puzzleScanner, int[][] matrix, int row, int col) {
		System.out.println("Please enter 9 values between 0 and 8 *(Example: 283164750)* : ");

		// Need to store the initial puzzle input from the user as a String
		String userInput = puzzleScanner.nextLine();
		String[] inputNumber = userInput.split("");

		// Create a 1D array to store the input string and parse the integers
		int matrixNum[] = new int[9];
		for (int i = 0; i < inputNumber.length; i++) {
			matrixNum[i] = Integer.parseInt(inputNumber[i]);
		}

		// Format the 1D array into a 2D array for display purposes
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrixNum[(j * row) + i] > 8) {
					System.out.println("You may not enter any values greater than 8!");
					System.out.println("Please try again!");
					System.exit(0);
				} else {
					matrix[j][i] = matrixNum[(j * row) + i];
				}
			}
		}

	}

	/**
	 * Function to display the user's puzzle that they entered. This only displays
	 * when the puzzle is not solvable due to the if statement in the main method.
	 * If the puzzle is solvable, it prints the solution through another function.
	 * 
	 * @param matrix
	 * @param row
	 * @param col
	 */
	public static void printMatrix(int[][] matrix, int row, int col) {
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

	/**
	 * Function that is called by the solvable function to determine how many tiles
	 * are inversed in the user's puzzle. If the number of inversed tiles are odd,
	 * then the puzzle can be solved. If the number of inversed tiles is even, the
	 * puzzle cannot be solved.
	 * 
	 * @param matrix
	 * @return
	 */
	static int getInverseCount(int[][] matrix) {
		int inverse_count = 0;
		for (int i = 0; i < 3 - 1; i++) {
			for (int j = i + 1; j < 3; j++) {
				if (matrix[j][i] > 0 && matrix[j][i] > 0 && matrix[j][i] > matrix[i][j]) {
					inverse_count++;
				}
			}
		}
		return inverse_count;
	}

	/**
	 * Function that returns a true or false value to determine if the puzzle is
	 * solvable or not. This function is called in the main method, and uses the
	 * function getInverseCount() above in order to determine if the puzzle can be
	 * solved.
	 * 
	 * @param puzzle
	 * @return
	 */
	static boolean solvable(int[][] puzzle) {
		int inverseCount = getInverseCount(puzzle);
		return (inverseCount % 2 == 0);
	}

}