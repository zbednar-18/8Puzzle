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
		
		Scanner scanner = new Scanner(System.in);
		int row = 3;
		int col = 3;
		int[][] matrix = new int[row][col];
		
		enterMatrixData(scanner, matrix, row, col);
		printMatrix(matrix, row, col);
	}
	
	// Function to prompt user to enter custom puzzle
	public static void enterMatrixData(Scanner scanner, int[][] matrix, int row, int col) {
		System.out.println("Please enter 9 values between 0 and 8: ");
		          
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int matrixNum = scanner.nextInt();
				
				// If statement to handle whether or not a value can be accepted
				if(matrixNum > 8) {
					System.out.println("The entered number must be between the values of 0 and 8.");
					System.out.println("Please try again!");
					System.exit(0);
				} else {
					matrix[i][j] = matrixNum;
				}
				
			}
		}
	}
	
	// Function to display the user's custom puzzle
	public static void printMatrix(int[][] matrix, int row, int col){
		System.out.println("Your Matrix is : ");
		        
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j]+"\t");
			}       
			System.out.println();
		}
	}
	
}