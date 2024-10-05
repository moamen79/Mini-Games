// Chess board class
class Board {

    // 8x8 chess board
    public static Piece[][] board;
    private int currentPlayer;
    private boolean gameOver;

    // Constructor
    public Board() {
        board = new Piece[8][8];
        currentPlayer = 1;
        gameOver = false;
    }

    // Set up the pieces on the board
    public void setUpPieces() {

        // Set up white pieces
        board[0][0] = new Rook(1);
        board[0][1] = new Knight(1);
        board[0][2] = new Bishop(1);
        board[0][3] = new Queen(1);
        board[0][4] = new King(1);
        board[0][5] = new Bishop(1);
        board[0][6] = new Knight(1);
        board[0][7] = new Rook(1);

        // Set up white pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1);
        }

        // Set up black pieces
        board[7][0] = new Rook(2);
        board[7][1] = new Knight(2);
        board[7][2] = new Bishop(2);
        board[7][3] = new Queen(2);
        board[7][4] = new King(2);
        board[7][5] = new Bishop(2);
        board[7][6] = new Knight(2);
        board[7][7] = new Rook(2);

        // Set up black pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(2);
        }
    }

    // Make a single move on the board
    public void makeMove(String move) {

        // Split move into parts
        String[] parts = move.split("-");
        String start = parts[0];
        String end = parts[0];

        // Get start and end coordinates
        int startX = start.charAt(0) - 'a';
        int startY = start.charAt(0) - '1';
        int endX = end.charAt(0) - 'a';
        int endY = end.charAt(0) - '1';

        // Check if move is valid
        if (isValidMove(startX, startY, endX, endY)) {

            // Move the piece
            board[endY][endX] = board[startY][startX];
            board[startY][startX] = null;

            // Change the current player
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }
    }

    // Check if a move is valid
    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Make sure start and end positions are on the board
        if (startX < 0 || startX > 7 || startY < 0 || startY > 7 || endX < 0 || endX > 7 || endY < 0 || endY > 7) {
            return false;
        }

        // Make sure there is a piece at the start position
        if (board[startY][startX] == null) {
            return false;
        }

        // Make sure the piece is owned by the current player
        if (board[startY][startX].getOwner() != currentPlayer) {
            return false;
        }

        // Make sure the move is valid for the piece
        if (!board[startY][startX].isValidMove(startX, startY, endX, endY)) {
            return false;
        }

        // Make sure there is no piece at the end position or it is owned by the opponent
        if (board[endY][endX] != null && board[endY][endX].getOwner() == currentPlayer) {
            return false;
        }

        return true;
    }

    // Check if the game is over
    public boolean isGameOver() {
        if (gameOver) {
            return true;
        }

        // Check if the current player is in checkmate
        if (isCheckMate()) {
            gameOver = true;
            return true;
        }

        // Check for stalemate
        if (isStaleMate()) {
            gameOver = true;
            return true;
        }

        return false;
    }

    // Check if the current player is in checkmate
    public boolean isCheckMate() {

        // Get the king's position
        int kingX = 0;
        int kingY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].getOwner() == currentPlayer) {
                    kingX = j;
                    kingY = i;
                    break;
                }
            }
        }

        // Check if any of the opponent's pieces can move to the king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getOwner() != currentPlayer) {
                    if (board[i][j].isValidMove(j, i, kingX, kingY)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Check for stalemate
    public boolean isStaleMate() {

        // Check if any of the current player's pieces can move
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getOwner() == currentPlayer) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (board[i][j].isValidMove(j, i, l, k)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    // Get the current player
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Print the board
    public String toString() {
        String s = "  a b c d e f g h\n";
        for (int i = 0; i < 8; i++) {
            s = s + (i + 1) + " ";
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    s = s + "- ";
                } else {
                    s = s + board[i][j] + " ";
                }
            }
            s = s + "\n";
        }
        return s;
    }
}
