package Game;

import Interface.TicTacToe;

public class TicTacToeGame implements TicTacToe {
    private char[][] board;

    public TicTacToeGame() {
        // Initialize the Tic Tac Toe board with numbered positions
        board = new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
    }

    public void makeMove(char[][] board, int choice, char player) {
        // Update the board with the players move
        int row = (choice - 1) / 3;
        int col = (choice - 1) % 3;
        board[row][col] = player;
    }

    public boolean isValidMove(char[][] board, int choice) {
        // Check if the players move is valid
        if (choice < 1 || choice > 9)
            return false;

        int row = (choice - 1) / 3;
        int col = (choice - 1) % 3;

        return board[row][col] == (char) (choice + '0');
    }

    public boolean isGameFinished(char player) {
        // Check for winning conditions
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public boolean isBoardFull() {
        // Check if the board is full (no more moves can be made)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public int findBestMove(char[][] board, char player) {
        // Find the best move for the AI
        char opponent = (player == 'X') ? 'O' : 'X';

        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 1; i <= 9; i++) {
            if (isValidMove(board, i)) {
                makeMove(board, i, player);
                int score = minimax(board, 0, false, player, opponent);
                makeMove(board, i, (char) (i + '0'));

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    public int minimax(char[][] board, int depth, boolean isMaximizing, char player, char opponent) {
        // Minimax algorithm for AI move selection
        char currentPlayer = isMaximizing ? player : opponent;

        int score = evaluate(board, player, opponent);

        if (score != 0) {
            return score;
        }

        if (!isMovesLeft(board)) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 1; i <= 9; i++) {
            if (isValidMove(board, i)) {
                makeMove(board, i, currentPlayer);

                int currentScore = minimax(board, depth + 1, !isMaximizing, player, opponent);

                if (isMaximizing) {
                    bestScore = Math.max(bestScore, currentScore);
                } else {
                    bestScore = Math.min(bestScore, currentScore);
                }

                makeMove(board, i, (char) (i + '0'));
            }
        }

        return bestScore;
    }

    public int evaluate(char[][] board, char player, char opponent) {
        // Evaluate the game state and assign scores
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == player) {
                    return 10;
                } else if (board[row][0] == opponent) {
                    return -10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == player) {
                    return 10;
                } else if (board[0][col] == opponent) {
                    return -10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
                board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[1][1] == player) {
                return 10;
            } else if (board[1][1] == opponent) {
                return -10;
            }
        }

        return 0;
    }

    public boolean isMovesLeft(char[][] board) {
        // Check if there are any valid moves left on the board
        for (int i = 1; i <= 9; i++) {
            if (isValidMove(board, i)) {
                return true;
            }
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }
}
