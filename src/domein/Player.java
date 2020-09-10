package domein;

public class Player {
    private Hand hand = new Hand();

    public Player(DeckHolder deckHolder, int cardAmount) {
        hand.addCards(deckHolder, cardAmount, true);
    }

    public boolean hasPair() {
        Card card0 = this.hand.getCards().get(0);
        Card card1 = this.hand.getCards().get(1);
        return card0.equalsValue(card1);
    }

    public void hit(DeckHolder deckHolder){
        hand.addCards(deckHolder, 1, true);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isBust(){
        return hand.getHandValue() > 21;
    }
}
