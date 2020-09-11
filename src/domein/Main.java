package domein;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static domein.BlackJackLogic.gameState;
import static domein.BlackJackLogic.setAllCardsVisible;

public class Main {

    public static void main(String[] args) throws IOException {

        //Deck tester
        Stack stack = new Stack(2);
        stack.printStack();
        stack.shuffle();
        stack.printStack();

        System.out.println("You got "+ stack.getCards(2, true));
        System.out.println("You got "+ stack.getCards(2, true));

        stack.printStack();

        //Hand tester
        Hand hand = new Hand();
        while (!hand.isBusted()){
            hand.addCards(stack, 1, true);
            int value = hand.getHandValue();
            System.out.println(value + " : " + hand.getCards());
        }
        DeckPrinter.printDeck(hand.getCards());
        System.out.println("Busted");

        //Game
        while (true){

            //new list of players
            ArrayList<Player> players = new ArrayList<>();

            //New player and dealer
            Player player = new Player(stack, 2);
            players.add(player);

            Dealer dealer = new Dealer(stack, 1);
            dealer.getHand().addCards(stack, 1, false);

            //new deck
            stack.clear();
            stack.getStack(1);
            stack.shuffle();

            //Wait for new game
            Scanner userChoice = new Scanner(System.in);
            waitForEnter("WELCOME TO BLACKJACK!", userChoice);
            boolean onMove = true;
            boolean isFirstMove = true;

            //New game state
            BlackJackLogic.gameState state = gameState.Playing;

            while (onMove){

                //Get state and print game
                state = BlackJackLogic.getGameState(state, player, dealer);
                if (state != gameState.Playing && state != gameState.Standing){
                    setAllCardsVisible(dealer);
                }
                BlackJackLogic.printGame(player, dealer);

                //Get hand
                int dealerValue = dealer.getHand().getHandValue();
                int playerValue = player.getHand().getHandValue();

                if (state == gameState.Playing){
                    System.out.print("ACTION: ");
                    System.out.print("[HIT : h]");
                    System.out.print("[STAND : s]");
                    if (isFirstMove) System.out.print("[DOUBLE : d]");
                    System.out.println();

                    boolean userChosen = false;
                    while (!userChosen){
                        String choice = userChoice.nextLine();
                        switch (choice){
                            case "h":
                                System.out.println("Player chose hit!");
                                player.getHand().addCards(stack, 1, true);
                                userChosen = true;
                                break;
                            case "s":
                                System.out.println("Player chose stand!");
                                state = gameState.Standing;
                                userChosen = true;
                                break;
                            case "d":
                                if (isFirstMove){
                                    System.out.println("Player chose double down!");
                                    player.getHand().addCards(stack, 1, true);
                                    state = gameState.Standing;
                                    userChosen = true;
                                    break;
                                }
                        }
                    }
                } else if (state == gameState.Standing){
                    if (dealerValue < 16) {
                        dealer.getHand().addCards(stack, 1, true);
                    }else{
                        state = gameState.BothStanding;
                    }
                } else{
                    onMove = false;
                }

                isFirstMove = false;
            }
        }
    }

    public static void waitForEnter(String message, Scanner scanner) {
        System.out.println(message);
        while (true){
            if (scanner.next() != null) break;
        }
    }
}
