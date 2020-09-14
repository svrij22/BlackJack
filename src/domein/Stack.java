package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Stack {
    private ArrayList<Card> cardStack = new ArrayList<>();

    public Stack(int stackAmt) {
        this.getStack(stackAmt);
    }

    public void clear(){
        cardStack.clear();
    }

    public void getStack(int stackAmt){
        cardStack.addAll(Deck.generateDeck(stackAmt));
    }

    public void shuffle(){
        Collections.shuffle(this.cardStack);
    }

    public void printStack(){
        System.out.println("This deck contains " + cardStack.size() + " cards.");
        for (Card c : cardStack) System.out.println(c.toString());
    }

    public ArrayList<Card> getCards(int cardAmt, boolean isVisible) {
        if (this.cardStack.size() < 1) return null;
        ArrayList<Card> chosenCards = new ArrayList<>();

        IntStream.range(0, cardAmt).forEachOrdered(n -> {
            Card card = this.cardStack.get(0);
            card.setVisible(isVisible);
            this.cardStack.remove(0);
            chosenCards.add(card);
        });

        return chosenCards;
    }
}