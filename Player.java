/* Eric Huang
*
* Player Class
*
* The player class that contains the decks for the players, name and chimp status 
*
* Start date : Feb.10th 2020 
* End date : Feb.18th 2020
*/

import javax.swing.*;
import java.util.*;

public class Player { // a class player for each player

    Deck deck;
    String name;
    int chimp;

    static final String CHIMP = "CHIMP";

    public Player(){
        deck = new Deck(new LinkedList<>()); // initializes deck with being empty
        name = "N/A";
        chimp = 0;
    }

    public Player(String name){

        deck = new Deck(new LinkedList<>()); // names and initializes empty deck
        this.name = name;

        chimp = 0;

    }

    public void playCard(Card aCard){ // plays a card
        Short decode = Card.encode(aCard); // decodes the card

        Chimp.discard.receiveCard(this.deck.giveCard(this.deck.cards.indexOf(decode))); // gets the index of the card, and gives it away

        checkMove((short)aCard.value); // returns a value for it all

    }

    public void checkMove(Short cardValue){ // this goes through all of the possible moves, returns value

        switch (cardValue) { // switch statement that considers the moves
            case 0 : // if it's this case, goes to aceValue, asks for input of either 1 or 11
                Chimp.deckValue += aceValue();
                return;
            case 3 : // skips essentially just is a zero card
                return;

            case 8 :
                Chimp.counter = -Chimp.counter; // if it's a 9, reverse direction
                return;

            case 9 : // if it's a ten, minus ten

                Chimp.deckValue -= 10;
                return;
            case 11 :
                Chimp.deckValue = 99; // sets the deck value to 99, then returns a zero
                return;
            case 10 :
            case 12 :
                Chimp.deckValue += 20;
                return;
            default :
                Chimp.deckValue += cardValue+1; // nothing special, adds value
        }

    }

    public short aceValue(){ // returns the ace value
        
        // will display options until one is picked
        
        String[]options = {"1", "11"};
        
        int num = 0;
        
        while(num != 1 && num != 11){
        num = JOptionPane.showOptionDialog(Chimp.gui.frame, "Choose the value of the ace!",
                "Ace Value",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]) == 0 ? 1 : 11;
        }

        return (short)num; 

    }

    public void pickUp(){ // method to pick up a card

        // if empty, takes from discard pile
        
        if(Chimp.deck.cards.size() <= 0){

            for(Short a : Chimp.discard.cards){
                Chimp.deck.receiveCard(a);
            }

            Chimp.discard.cards.clear();

            Chimp.deck.shuffle();

        }

        this.deck.receiveCard(Chimp.deck.giveCard(0)); // takes deck, and puts in the 0th index into player's deck


    }

    public String toString(){ // to string method for people's decks
        String status = CHIMP.substring(0, chimp); // gets the substring of chimp in order to print it out later

        return name + "'s deck : " + deck + " CHIMP : " + status;
    }


}
