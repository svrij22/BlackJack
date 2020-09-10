package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class DeckHolder {
    ArrayList<Card> holdingDeck = new ArrayList<>();

    public void generateDeck(int deckAmount){
        holdingDeck.clear();
        IntStream.range(0, deckAmount).forEachOrdered(n -> {
            for (Card.Suit suit : Card.Suit.values()){

                // 2 - 10
                IntStream.range(2, 11).forEachOrdered(n2 -> {
                    this.addNewCard(Card.Type.Number, suit, n2);
                });

                // J Q K A
                this.addNewCard(Card.Type.Jack, suit, 10);
                this.addNewCard(Card.Type.Queen, suit, 10);
                this.addNewCard(Card.Type.King, suit, 10);
                this.addNewCard(Card.Type.Ace, suit, 11);
            }
        });
    }

    public void addNewCard(Card.Type type, Card.Suit suit, int cardValue){
        holdingDeck.add(new Card(type, suit, cardValue, false));
    }

    public void printDeck(){
        System.out.println("This deck contains " + holdingDeck.size() + " cards.");
        for (Card c : holdingDeck) System.out.println(c.toString());
    }

    public void shuffle(){
        Collections.shuffle(this.holdingDeck);
    }

    public ArrayList<Card> getCards(int cardAmt, boolean isVisible) {
        if (this.holdingDeck.size() < 1) return null;
        ArrayList<Card> chosenCards = new ArrayList<>();

        IntStream.range(0, cardAmt).forEachOrdered(n -> {
            Card card = this.holdingDeck.get(0);
            card.setVisible(isVisible);
            this.holdingDeck.remove(0);
            chosenCards.add(card);
        });

        return chosenCards;
    }
}