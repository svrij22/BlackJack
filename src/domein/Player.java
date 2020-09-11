package domein;

public class Player {
    private Hand hand = new Hand();

    private boolean isFirstmove;
    private boolean hasChosen;

    public boolean isFirstmove() {
        return isFirstmove;
    }

    public void setFirstmove(boolean firstmove) {
        isFirstmove = firstmove;
    }

    public boolean isHasChosen() {
        return hasChosen;
    }

    public void setHasChosen(boolean hasChosen) {
        this.hasChosen = hasChosen;
    }

    public Player(Stack stack, int cardAmount) {
        hand.addCards(stack, cardAmount, true);
    }

    public boolean hasPair() {
        Card card0 = this.hand.getCards().get(0);
        Card card1 = this.hand.getCards().get(1);
        return card0.equalsValue(card1);
    }

    public void hit(Stack stack){
        hand.addCards(stack, 1, true);
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
