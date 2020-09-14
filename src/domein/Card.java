package domein;

public class Card {

    public enum Suit {
        Hearts,
        Spades,
        Diamonds,
        Clubs
    }

    public enum Type{
        Number(0),
        Jack(10),
        Queen(10),
        King(10),
        Ace(11);

        private final int value;

        Type(int i) {
            this.value = i;
        }
    }

    private final Type cardType;
    private final Suit cardSuit;
    private final int cardValue;
    private boolean isVisible = false;

    public Card(Type cardType, Suit cardSuit) {
        this.cardType = cardType;
        this.cardSuit = cardSuit;
        this.cardValue = cardType.value;
    }

    public Card(int cardValue, Suit cardSuit) {
        this.cardType = Type.Number;
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
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

    public boolean isAce() {
        return (this.cardType == Type.Ace);
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