package domein;

public class Card {

    public enum Suit {
        Hearts,
        Spades,
        Diamonds,
        Clubs
    }

    public enum Type{
        Number,
        Jack,
        Queen,
        King,
        Ace
    }

    private final Type cardType;
    private final Suit cardSuit;
    private final int cardValue;
    private boolean isVisible;

    public Card(Type cardType, Suit cardSuit, int cardValue, boolean isVisible) {
        this.cardType = cardType;
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Type getCardType() {
        return cardType;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    public int getCardValue() {
        return cardValue;
    }

    @Override
    public String toString() {

        return cardPrintValue() + " of " + this.cardSuit;
    }
    public String cardPrintValue(){
        Object value = (this.cardType != Type.Number) ? this.cardType : this.cardValue;
        return String.valueOf(value);
    }

    public boolean equalsValue(Card card){
        return this.cardValue == card.getCardValue();
    }
}