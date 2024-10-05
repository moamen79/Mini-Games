import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int playerTotal = 0;
        int dealerTotal = 0;
        int playerCard1, playerCard2, dealerCard1, dealerCard2;
        boolean playerDone = false;
        boolean dealerDone = false;

        //deal initial cards
        playerCard1 = (int)(Math.random()*10 + 1);
        playerCard2 = (int)(Math.random()*10 + 1);
        dealerCard1 = (int)(Math.random()*10 + 1);
        dealerCard2 = (int)(Math.random()*10 + 1);

        //show initial cards
        System.out.println("Your cards are " + playerCard1 + " and " + playerCard2);
        System.out.println("Dealer's cards are " + dealerCard1 + " and " + dealerCard2);

        //calculate total
        playerTotal = playerCard1 + playerCard2;
        dealerTotal = dealerCard1 + dealerCard2;

        System.out.println("Your total is " + playerTotal);

        //player's turn
        while(playerDone == false) {
            System.out.println("Hit or Stay? (h/s)");
            String playerChoice = input.nextLine();

            if(playerChoice.equals("h")) {
                //hit
                int newCard = (int)(Math.random()*10 + 1);
                System.out.println("You drew a " + newCard);
                playerTotal += newCard;
                System.out.println("Your total is now " + playerTotal);

                if(playerTotal > 21) {
                    System.out.println("You bust!");
                    break;
                }
            } else {
                //stay
                System.out.println("You stayed.");
                playerDone = true;
            }
        }

        //dealer's turn
        while(dealerDone == false) {
            if(dealerTotal <= 16) {
                //hit
                int newCard = (int)(Math.random()*10 + 1);
                System.out.println("Dealer drew a " + newCard);
                dealerTotal += newCard;
                System.out.println("Dealer's total is now " + dealerTotal);

                if(dealerTotal > 21) {
                    System.out.println("Dealer busts!");
                    break;
                }
            } else {
                //stay
                System.out.println("Dealer stayed.");
                dealerDone = true;
            }
        }

        //determine winner
        if(playerTotal > 21) {
            System.out.println("Dealer wins!");
        } else if(dealerTotal > 21) {
            System.out.println("You win!");
        } else if(playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if(playerTotal < dealerTotal) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }

    }

}
