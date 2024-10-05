import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {

        // Create a chess board
        Board board = new Board();

        // Set up the pieces
        board.setUpPieces();

        // Print the initial board
        System.out.println(board.toString());

        Scanner scanner = new Scanner(System.in);

        // Play until someone wins or the game is a draw
        while (!board.isGameOver()) {

            // Player 1's turn
            System.out.println("Player 1's turn:");
            System.out.print("Enter a move: ");
            String move = scanner.nextLine();

            // Make the move
            board.makeMove(move);
            System.out.println(board.toString());

            // Check for game over
            if (board.isGameOver()) {
                break;
            }

            // Player 2's turn
            System.out.println("Player 2's turn:");
            System.out.print("Enter a move: ");
            move = scanner.nextLine();

            // Make the move
            board.makeMove(move);
            System.out.println(board.toString());

        }

        // Print the game result
        if (board.isCheckMate()) {
            System.out.println("Checkmate! Player " + board.getCurrentPlayer() + " wins!");
        } else {
            System.out.println("The game is a draw!");
        }

        scanner.close();
    }

}
// Abstract piece class
abstract class Piece {

    private int owner;

    public Piece(int owner) {
        this.owner = owner;
    }

    public int getOwner() {
        return owner;
    }
    public abstract boolean isValidMove(int startX, int startY, int endX, int endY);
}
// Rook class
class Rook extends Piece {

    public Rook(int owner) {
        super(owner);
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Can move horizontally or vertically
        if (startX == endX || startY == endY) {

            // Cannot move to a position with a piece of the same color
            if (Board.board[endY][endX] == null || Board.board[endY][endX].getOwner() != getOwner()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        if (getOwner() == 1) {
            return "R";
        } else {
            return "r";
        }
    }

}

// Knight class
class Knight extends Piece {

    public Knight(int owner) {
        super(owner);
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Can move in an L shape
        if ((Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2)
                || (Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1)) {

            // Cannot move to a position with a piece of the same color
            if (Board.board[endY][endX] == null || Board.board[endY][endX].getOwner() != getOwner()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        if (getOwner() == 1) {
            return "N";
        } else {
            return "n";
        }
    }

}

// Bishop class
class Bishop extends Piece {

    public Bishop(int owner) {
        super(owner);
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Can move diagonally
        if (Math.abs(startX - endX) == Math.abs(startY - endY)) {

            // Cannot move to a position with a piece of the same color
            if (Board.board[endY][endX] == null || Board.board[endY][endX].getOwner() != getOwner()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        if (getOwner() == 1) {
            return "B";
        } else {
            return "b";
        }
    }

}

// Queen class
class Queen extends Piece {

    public Queen(int owner) {
        super(owner);
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Can move horizontally, vertically, or diagonally
        if (startX == endX || startY == endY || Math.abs(startX - endX) == Math.abs(startY - endY)) {

            // Cannot move to a position with a piece of the same color
            if (Board.board[endY][endX] == null || Board.board[endY][endX].getOwner() != getOwner()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        if (getOwner() == 1) {
            return "Q";
        } else {
            return "q";
        }
    }

}

// King class
class King extends Piece {

    public King(int owner) {
        super(owner);
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Can move one position in any direction
        if (Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1) {

            // Cannot move to a position with a piece of the same color
            if (Board.board[endY][endX] == null || Board.board[endY][endX].getOwner() != getOwner()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        if (getOwner() == 1) {
            return "K";
        } else {
            return "k";
        }
    }

}

// Pawn class
class Pawn extends Piece {

    public Pawn(int owner) {
        super(owner);
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {

        // Can move one position forward
        if (startX == endX && ((getOwner() == 1 && startY == 1 && endY == 2) || (getOwner() == 2 && startY == 6 && endY == 5))) {

            // Cannot move to a position with a piece of the same color
            if (Board.board[endY][endX] == null) {
                return true;
            }
        }

        // Can move one position diagonally to capture an opponent's piece
        if (Math.abs(startX - endX) == 1 && ((getOwner() == 1 && startY == 6 && endY == 5) || (getOwner() == 2 && startY == 1 && endY == 2))) {

            // Cannot capture a piece of the same color
            if (Board.board[endY][endX] != null && Board.board[endY][endX].getOwner() != getOwner()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        if (getOwner() == 1) {
            return "P";
        } else {
            return "p";
        }
    }
}
