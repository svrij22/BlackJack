package domein;

public class BlackJackLogic {

    public enum gameState{
        Blackjack,
        Push,
        Bust,
        Playing,
        Standing,
        BothStanding,
        DealerWins
    }
    public static boolean hasMoreCards (Player player, Player player1){
        int cardAmt0 = player.getHand().getCards().size();
        int cardAmt1 = player1.getHand().getCards().size();
        return cardAmt0 > cardAmt1;
    }

    public static void setAllCardsVisible(Player player){
        for (Card c : player.getHand().getCards()) c.setVisible(true);
    }

    public static boolean isAllVisible(Player player){
        for (Card c : player.getHand().getCards()){
            if (!c.isVisible()) return false;
        }
        return true;
    }

    public static gameState getGameState(gameState state, Player player, Player dealer){

        int dealerValue = dealer.getHand().getHandValue();
        int playerValue = player.getHand().getHandValue();

        if (state == gameState.Playing){
            if (playerValue == 21){
                //Player has blackjack
                if (dealerValue < 21){
                    System.out.println("BLACKJACK! You won twice your bet!");
                    return gameState.Blackjack;
                }else{
                    //Same value
                    System.out.println("PUSH! Better luck next time.");
                    return gameState.Push;
                }
            }
        }

        if (state == gameState.Standing || state == gameState.BothStanding){
            // Dealer bust
            if (dealerValue > 21 && playerValue <= 21){
                System.out.println("BLACKJACK! You won twice your bet (1)!");
                return gameState.Blackjack;
            }

            //Both 21 but player has less cards
            if (playerValue == 21 && dealerValue == 21 && BlackJackLogic.hasMoreCards(player, dealer)){
                System.out.println("BLACKJACK! You won twice your bet (3)!");
                return gameState.Blackjack;
            }

            //Both same value
            if (playerValue == dealerValue && playerValue <= 21){
                System.out.println("BUST! Better luck next time.");
                return gameState.Bust;
            }
        }

        if (state == gameState.BothStanding){
            //Player has more value and not busted
            if (playerValue > dealerValue && playerValue <= 21){
                System.out.println("BLACKJACK! You won twice your bet (2)!");
                return gameState.Blackjack;
            }

            //Dealer wins
            System.out.println("Dealer wins! Better luck next time.");
            return gameState.DealerWins;
        }

        if (player.isBust()) {
            System.out.println("BUST! Better luck next time.");
            return gameState.Bust;
        }
        return state;
    }

    public static void printGame(Player player, Dealer dealer){
        if (isAllVisible(dealer)){
            System.out.println("=============================DEALER (" + dealer.getHand().getHandValue()+ ")=============================");
        }else{
            System.out.println("=============================DEALER=================================");
        }
        DeckPrinter.printDeck(dealer.getHand().getCards());
        System.out.println("=============================PLAYER (" + player.getHand().getHandValue()+ ")=============================");
        DeckPrinter.printDeck(player.getHand().getCards());
        System.out.println("======================================================================");
    }
}
