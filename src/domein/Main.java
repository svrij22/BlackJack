package domein;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //Deck tester

        DeckHolder deckHolder = new DeckHolder();
        deckHolder.generateDeck(1);
        deckHolder.printDeck();
        deckHolder.shuffle();
        deckHolder.printDeck();

        System.out.println("You got "+ deckHolder.getCards(2, true));
        System.out.println("You got "+ deckHolder.getCards(2, true));

        deckHolder.printDeck();

        //Hand tester
        Hand hand = new Hand();
        while (!hand.isBusted()){
            hand.addCards(deckHolder, 1, true);
            int value = hand.getHandValue();
            System.out.println(value + " : " + hand.getCards());
        }
        DeckPrinter.printDeck(hand.getCards());
        System.out.println("Busted");

        //Game
        while (true){

            //New player and dealer
            Player player = new Player(deckHolder, 2);
            Dealer dealer = new Dealer(deckHolder, 1);
            dealer.getHand().addCards(deckHolder, 1, false);

            //new deck
            deckHolder.generateDeck(1);
            deckHolder.shuffle();

            //Wait for new game
            waitForEnter("WELCOME TO BLACKJACK!");
            boolean onMove = true;

            while (onMove){
                System.out.println("=============================DEALER=================================");
                DeckPrinter.printDeck(dealer.getHand().getCards());
                System.out.println("=============================PLAYER (" + player.getHand().getHandValue()+ ")=============================");
                DeckPrinter.printDeck(player.getHand().getCards());
                System.out.println("======================================================================");

                int dealerValue = dealer.getHand().getHandValue();
                int playerValue = player.getHand().getHandValue();

                if (playerValue == 21){
                    if (dealerValue < 21){
                        System.out.println("BLACKJACK! You won twice your bet!");
                        onMove = false;
                    }else{
                        System.out.println("PUSH! Better luck next time.");
                        onMove = false;
                    }
                    break;
                }

                if (player.isBust()) {
                    System.out.println("BUST! Better luck next time.");
                    onMove = false;
                    break;
                }else{
                    System.out.print("ACTION: ");
                    System.out.println("[HIT]");

//                    if (player.hasPair()){
//                        System.out.println("[SPLIT]");
//                    }

                    Scanner userChoice = new Scanner(System.in);
                    boolean userChosen = false;

                    while (!userChosen){
                        String choice = userChoice.nextLine();
                        switch (choice){
                            case "HIT":
                                System.out.println("Player chose hit!");
                                player.getHand().addCards(deckHolder, 1, true);
                                userChosen = true;
                                break;
                        }
                    }
                    userChoice.close();
                    userChoice.remove();
                }
            }
        }
    }

    public static void waitForEnter(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while (true){
            if (scanner.next() != null) break;
        }
    }
}
