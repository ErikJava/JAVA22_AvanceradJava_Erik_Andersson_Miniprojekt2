import java.util.Scanner;

class TicTacToeUI {
    private TicTacToe game;

    public TicTacToeUI(TicTacToe game) {
        this.game = game;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean isPlayerX = true; // Indicates whether it's the players turn (X)

        while (true) {
            printBoard(game.getBoard());

            // Determine the current player
            char currentPlayer = isPlayerX ? 'X' : 'O';

            if (isPlayerX) {
                // Players move
                System.out.print("Player " + currentPlayer + ", enter your move (choose a number 1-9): ");
                int playerChoice = scanner.nextInt();

                if (game.isValidMove(game.getBoard(), playerChoice)) {
                    game.makeMove(game.getBoard(), playerChoice, currentPlayer);
                } else {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                if (game.isGameFinished(currentPlayer)) {
                    printBoard(game.getBoard());
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }
            } else {
                // AIs move
                char aiPlayer = 'O'; // AI always plays as "O"
                int aiChoice = game.findBestMove(game.getBoard(), aiPlayer);
                game.makeMove(game.getBoard(), aiChoice, aiPlayer);

                if (game.isGameFinished(aiPlayer)) {
                    printBoard(game.getBoard());
                    System.out.println("Player " + aiPlayer + " wins!");
                    break;
                }
            }

            if (game.isBoardFull()) {
                printBoard(game.getBoard());
                System.out.println("It's a draw!");
                break;
            }

            isPlayerX = !isPlayerX; // Switch players
        }

        scanner.close();
    }

    public void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToeGame();
        TicTacToeUI ui = new TicTacToeUI(game);
        ui.playGame();
    }
}
