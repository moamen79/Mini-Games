import java.util.Scanner;

public class TicTacToe {

    public static final int SIZE = 3;
    public static final char EMPTY = ' ';
    public static final char PLAYER1 = 'X';
    public static final char PLAYER2 = 'O';
    public static char[][] board = new char[SIZE][SIZE];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initBoard();
        printBoard();
        boolean isPlayer1Turn = true;

        while (true) {
            int row, col;
            System.out.println("");
            if (isPlayer1Turn) {
                System.out.print("Player 1 [X] turn: ");
            } else {
                System.out.print("Player 2 [O] turn: ");
            }

            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= SIZE || col >= SIZE || row < 0 || col < 0) {
                System.out.println("Invalid position! Try again.");
                continue;
            }
            if (board[row][col] != EMPTY) {
                System.out.println("Position is already filled! Try again.");
                continue;
            }
            if (isPlayer1Turn) {
                board[row][col] = PLAYER1;
            } else {
                board[row][col] = PLAYER2;
            }
            if (checkWin(row, col)) {
                printBoard();
                if (isPlayer1Turn) {
                    System.out.println("Player X won!");
                } else {
                    System.out.println("Player O won!");
                }
                break;
            }
            isPlayer1Turn = !isPlayer1Turn;
            printBoard();
        }
    }

    public static void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }

    public static boolean checkWin(int row, int col) {
        char c = board[row][col];
        int count = 0;

        // check row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == c) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        // check col
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == c) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        // check diagonal
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] == c) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        // check anti diagonal
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] == c) {
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }

        return false;
    }
}
