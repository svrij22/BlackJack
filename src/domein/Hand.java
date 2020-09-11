package domein;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> holdingCards = new ArrayList<>();

    public void addCards(Stack stack, int cardAmt, boolean isVisible){
        holdingCards.addAll(stack.getCards(cardAmt, isVisible));
    }

    public void setVisible(int pos){
        holdingCards.get(pos).setVisible(true);
    }

    public ArrayList<Card> getCards(){
        return holdingCards;
    }

    public int getHandValue(){
        int totalValue = 0;
        for (Card c : holdingCards){
            totalValue += c.getCardValue();

            //Greater than 21 and its an ace? Bring that shit down
            if (totalValue > 21 && c.getCardType().equals(Card.Type.Ace)) totalValue -= 10;
        }
        return totalValue;
    }

    public boolean isBusted(){
        return getHandValue() > 21;
    }
}
