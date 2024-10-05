import java.util.Scanner;

public class ShootingGame {

    public static void main(String[] args) {

        // Setup
        Scanner input = new Scanner(System.in);
        int score = 0;
        int enemies = 5;
        int lives = 3;
        boolean gameOver = false;

        while (!gameOver) {
            // Ask user to shoot
            System.out.println("Shoot an enemy! You have " + lives + " lives left.");
            String shoot = input.nextLine();

            // Generate a random number between 1 and 10
            int randomNumber = (int) (Math.random() * 10) + 1;

            // If the number is greater than or equal to 5, the enemy is hit
            if (randomNumber >= 5) {
                System.out.println("You hit the enemy!");
                System.out.println(randomNumber);
                score++;
                enemies--;
            } else {
                System.out.println("You missed!");
                System.out.println(randomNumber);
                lives--;
            }

            // Check if game is over
            if (lives == 0 || enemies == 0) {
                gameOver = true;
            }
        }

        // Show final score
        System.out.println("Game over! Your final score is " + score);
    }

}