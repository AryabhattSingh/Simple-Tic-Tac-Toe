package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleTicTacToe {

	private final static Scanner sc = new Scanner (System.in);
	private char grid[][];
	private boolean shouldGameContinue;
	private boolean isInputSuccessful;

	// Initializing the grid with whitespaces
	public SimpleTicTacToe() {
		grid = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				grid[i][j] = ' ';
			}
		}
	}

	// getting if the game should continue
	protected boolean getShouldGameContinue() {
		return shouldGameContinue;
	}

	// getting if the input by player was successful
	protected boolean getIsInputSuccessful() {
		return isInputSuccessful;
	}

	// checking if any whitespaces left in grid 
	private boolean checkIfSpaceIsLeftInGrid () {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == '_' || grid[i][j] == ' ') {
					return true;
				}
			}
		}
		return false;
	}

	// Checking if a strike is present for the given character - either X or O
	private boolean checkStrikePresentFor (char ch) {
		//checking the rows for possible strike
		for (int i = 0; i < 3; i++) {
			if (grid[i][0] == ch && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
				return true;
			}
		}

		//checking the columns for possible strike
		for (int j = 0; j < 3; j++) {
			if (grid[0][j] == ch && grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]) {
				return true;
			}
		}

		//checking diagonals for possible strike
		if (grid[0][0] == ch && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
			return true;
		}
		if (grid[0][2] == ch && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
			return true;
		}

		// if no strike is present
		return false;
	}

	// checking the game state
	protected void getGameState () {

		boolean isStrikePresentForX = checkStrikePresentFor('X');
		boolean isStrikePresentForO = checkStrikePresentFor('O');

		//checking for Impossible states
		if (isStrikePresentForX && isStrikePresentForO) {
			System.out.println("Impossible");
			shouldGameContinue = false;
			return;
		}
		int countOfX = countOccurrenceOfInGrid('X');
		int countOfO = countOccurrenceOfInGrid('O');
		int difference = Math.abs(countOfX - countOfO);

		// Too many X or O
		if (difference < 0 || difference > 1) {
			System.out.println("Impossible");
			shouldGameContinue = false;
			return;
		}

		if (isStrikePresentForX) {
			System.out.println("X wins");
			shouldGameContinue = false;
			return;
		}

		if (isStrikePresentForO) {
			System.out.println("O wins");
			shouldGameContinue = false;
			return;
		}

		boolean isSpaceLeftInGrid = checkIfSpaceIsLeftInGrid();

		if (isSpaceLeftInGrid) {
			//System.out.println("Game not finished");
			shouldGameContinue = true;
			return;
		}
		else { //checking if game is a draw
			if (isStrikePresentForX == false && isStrikePresentForO == false) {
				System.out.println("Draw");
				shouldGameContinue = false;
				return;
			}
		}

		shouldGameContinue = true;
	}

	//counting the occurrence of a character in grid. Needed to check in Impossible states
	private int countOccurrenceOfInGrid(char ch) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == ch) {
					count++;
				}
			}
		}
		return count;
	}

	// printing the current grid
	protected void printGrid() {
		System.out.println("---------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("---------");
	}

	// Taking the input and performing necessary checks.
	protected void takeInput(char ch) {
		int xCoordinateInputByPlayer = -1;
		int yCoordinateInputByPlayer = -1;

		try {
			//System.out.println("Enter coordinates : " );
			xCoordinateInputByPlayer = sc.nextInt();
			yCoordinateInputByPlayer = sc.nextInt();
		}
		catch (InputMismatchException e) {

			System.out.println("You should enter numbers!");
			isInputSuccessful = false;
			sc.nextLine(); //----------------------------------> Important line to clear Scanner buffer after throwing exception
			return;
		}
		xCoordinateInputByPlayer--;
		yCoordinateInputByPlayer--;
		if (xCoordinateInputByPlayer < 0 || xCoordinateInputByPlayer > 2 || yCoordinateInputByPlayer < 0 || yCoordinateInputByPlayer > 2) {
			System.out.println("Coordinates should be from 1 to 3!");
			isInputSuccessful = false;
			return;
		}

		char charAtCoordinate = grid[xCoordinateInputByPlayer][yCoordinateInputByPlayer];

		if (charAtCoordinate == 'X' || charAtCoordinate == 'O') {
			System.out.println("This cell is occupied! Choose another one!");
			isInputSuccessful = false;
			return;
		}
		else {
			grid[xCoordinateInputByPlayer][yCoordinateInputByPlayer] = ch;
			isInputSuccessful = true;
			//return;
		}
	}
		
}
