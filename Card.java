public class Card {
    byte value; // this is the card class within the deck class. This will allow for the display of cards
    String suit; // while it is saved as a short

    public Card(){ // initialized with no values
        value = 0;
        suit = "*";
    }

    public Card(byte vle, String fce){ // initialized with values
        value = vle;
        suit = fce;
    }

    public static short encode(Card card){ // so this encodes the card to a short, the hearts are 0-12
        // the spades are 13-25, the clubs are 26-39
        // and the diamonds are 40-51

        char suit = card.suit.charAt(0);

        byte fceValue = (byte)(suit == 'H' ? 0 :
                suit == 'S' ? 1 : suit == 'C' ? 2 : 3); // returns the suit value
        // encoded as 0 - hearts, 1 - spades, 2 - clubs, 3 - diamonds


        return (short)(card.value + fceValue*13); // encodes a card back into a short


    }

    public static Card decode(short cardShort){ // this decodes the short to a new card

        int faceValue = (cardShort)/13;

        return new Card((byte)(cardShort % 13), faceValue == 0 ? "Hearts" :
                faceValue == 1 ? "Spades" :  					// follows what is state above
                        faceValue == 2 ? "Clubs" : "Diamonds") ; // this simply decodes the short,
        // by getting the remainder as the value,
        // then the suit is through the multiple
        // now able to show it as a card
    }

    public String toString(){ // displays it as a string
        if(value > 9 || value == 0){ // checks if it is a face card

            String face = value == 0 ? "Ace" : value == 10 ? "Jack" : value == 11 ? "Queen" : "King";
            return face + " of " + suit; // if so, then it displays it as that
        }

        return (value+1) + " of " + suit; // displays the cards
    }

}
