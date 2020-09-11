package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Deck {

    public static ArrayList<Card> generateDeck(int deckAmount){
        ArrayList<Card> deck = new ArrayList<>();
        IntStream.range(0, deckAmount).forEachOrdered(n -> {
            for (Card.Suit suit : Card.Suit.values()){

                // 2 - 10
                IntStream.range(2, 11).forEachOrdered(n2 -> {
                    deck.add(new Card(Card.Type.Number, suit, n2, false));
                });

                // J Q K A
                deck.add(new Card(Card.Type.Jack, suit, 10, false));
                deck.add(new Card(Card.Type.Queen, suit, 10, false));
                deck.add(new Card(Card.Type.King, suit, 10, false));
                deck.add(new Card(Card.Type.Ace, suit, 11, false));
            }
        });
        return deck;
    }
}