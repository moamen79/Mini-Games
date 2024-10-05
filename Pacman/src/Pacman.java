import java.util.Scanner;

public class Pacman {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // create the board
        char[][] board = new char[10][10];

        // fill in the board with '.'s
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '.';
            }
        }

        // randomly place the dot
        int dotRow = (int)(Math.random() * 10);
        int dotCol = (int)(Math.random() * 10);
        board[dotRow][dotCol] = 'o';

        // randomly place the pacman
        int pacmanRow = (int)(Math.random() * 10);
        int pacmanCol = (int)(Math.random() * 10);
        board[pacmanRow][pacmanCol] = 'c';

        // print out the board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        // start the game
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("Move Pacman (up, down, left, right): ");
            String move = in.nextLine();

            // make sure the move is valid
            if (move.equals("up") && pacmanRow > 0) {
                board[pacmanRow][pacmanCol] = '.';
                board[pacmanRow-1][pacmanCol] = 'c';
                pacmanRow--;
            } else if (move.equals("down") && pacmanRow < 9) {
                board[pacmanRow][pacmanCol] = '.';
                board[pacmanRow+1][pacmanCol] = 'c';
                pacmanRow++;
            } else if (move.equals("left") && pacmanCol > 0) {
                board[pacmanRow][pacmanCol] = '.';
                board[pacmanRow][pacmanCol-1] = 'c';
                pacmanCol--;
            } else if (move.equals("right") && pacmanCol < 9) {
                board[pacmanRow][pacmanCol] = '.';
                board[pacmanRow][pacmanCol+1] = 'c';
                pacmanCol++;
            }

            // check if pacman eats the dot
            if (pacmanRow == dotRow && pacmanCol == dotCol) {
                System.out.println("Pacman ate the dot!");
                gameOver = true;
            }

            // print out the board
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}