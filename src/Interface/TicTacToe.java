package Interface;

public interface TicTacToe {
    boolean isValidMove(char[][] board, int choice);
    void makeMove(char[][] board, int choice, char player);
    boolean isGameFinished(char player);
    boolean isBoardFull();
    int findBestMove(char[][] board, char player);
    int minimax(char[][] board, int depth, boolean isMaximizing, char player, char opponent);
    int evaluate(char[][] board, char player, char opponent);
    boolean isMovesLeft(char[][] board);
    char[][] getBoard();
}