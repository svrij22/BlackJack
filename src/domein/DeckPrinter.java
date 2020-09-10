package domein;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class DeckPrinter {

    public static void printDeck(ArrayList<Card> cards){
        String[] lines = new String[]{"","","","","","","","",""};
        char[] suits_symbols = new char[]{'♠', '♦', '♥', '♣'};

        for (Card c : cards){

            String cardValue = c.cardPrintValue();
            if (cardValue.length() > 2) cardValue = cardValue.substring(0,1);
            if (cardValue.length() == 1) cardValue = " " + cardValue;

            char suit_symbol = suits_symbols[0];
            if (c.getCardSuit().equals(Card.Suit.Diamonds)) suit_symbol = suits_symbols[1];
            if (c.getCardSuit().equals(Card.Suit.Hearts)) suit_symbol = suits_symbols[2];
            if (c.getCardSuit().equals(Card.Suit.Clubs)) suit_symbol = suits_symbols[3];

            if (c.isVisible()){
                lines[0] += String.format("┌─────────┐");
                lines[1] += String.format("│%s       │", cardValue);
                lines[2] += String.format("│         │");
                lines[3] += String.format("│         │");
                lines[4] += String.format("│    %s    │", suit_symbol);
                lines[5] += String.format("│         │");
                lines[6] += String.format("│         │");
                lines[7] += String.format("│       %s│", cardValue);
                lines[8] += String.format("└─────────┘");
            }else{
                lines[0] += "┌─────────┐";
                IntStream.range(1, 8).forEachOrdered(n -> {lines[n] += "│░░░░░░░░░│";});
                lines[8] += "└─────────┘";
            }
        }

        for (String line : lines){
            System.out.println(line);
        }
    }
}