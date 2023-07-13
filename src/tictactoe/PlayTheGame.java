package tictactoe;

public class PlayTheGame {
	
	public static void playGame(SimpleTicTacToe game) {
		game.printGrid();
		game.getGameState();
		boolean shoulGameContinue = game.getShouldGameContinue();

		while (shoulGameContinue) {

			if (shoulGameContinue) {
				System.out.println("Taking input for X");
				game.takeInput('X');
				while (game.getIsInputSuccessful() != true) {
					game.takeInput('X');
				}

				game.printGrid();
				game.getGameState();
			}
			
			shoulGameContinue = game.getShouldGameContinue();
			if (shoulGameContinue) {
				//sc.nextLine();
				System.out.println("Taking input for O");
				game.takeInput('O');
				while (game.getIsInputSuccessful() != true) {
					game.takeInput('O');
				}
				game.printGrid();
				game.getGameState();
			}
			
			shoulGameContinue = game.getShouldGameContinue();
		}
	}

	public static void main(String[] args) {
		SimpleTicTacToe game = new SimpleTicTacToe();
		playGame(game);	
	}

}
