/*
 *Eric Huang - Feb.10th 2020
 *
 * This is made for the CHIMP Game, this the deck game
 *
 */


import java.util.*;

public class Deck { // this is the deck class for the cards
    LinkedList<Short> cards; // this is the linkedlist of cards, easier cause it's dynamic
    final int DECK = 52; // this will toggle the amount of cards

    public Deck() { // initializes a full deck
        cards = new LinkedList<>();
        for (int i = 0; i < DECK; i++) {
            cards.add((short)i);
        }
    }

    public void shuffle() { // this shuffles the cards


        Short[] shuffled = new Short[this.cards.size()]; // creates a new array of all the shuffled cards size
        LinkedList<Short> list = new LinkedList<>(this.cards); // creates a linkedlist of all the cards

        for (int i = 0; i <= shuffled.length - 1; i++) { // iterates through them all, removing the cards from list one by one
            int takeOut = (int) (Math.random() * list.size()); // goes from the 0 to c-1 index
            shuffled[i] = list.remove(takeOut); // adds it to the next index that it can be
        }

        this.cards.clear(); // clears original cards
        this.cards.addAll(Arrays.asList(shuffled)); // adds the shuffled to it

    }
  
  
     public Deck(LinkedList<Short> addCards) {

        cards = new LinkedList<>(addCards);

    }

    public short giveCard(int cardIndex){ // gives away the cards needed
    									// removes card at that index
    	return cards.remove(cardIndex);
    	
    }

    public short receiveCard(short card){
    	cards.add(card); // adds a card into it
    }
  
    public int[] dealCards(byte n) { // this deals away n cards
        int[] giveAway = new int[n]; // goes through, returns an array of the cards given
        for (int i = 0; i < n; i++) {
            giveAway[i] = cards.removeFirst();
        }
        return giveAway;
    }

    public void sort() { // this can sort the cards inside the deck

        Short[] deckSorted = cards.toArray(new Short[0]); // creates a new array for sorted
        Arrays.sort(deckSorted); // sorts it
        cards.clear(); // removes all the original ones
        cards.addAll(new LinkedList<>(Arrays.asList(deckSorted))); // adds it all to it

    }

    public String toString() { // returns a string of all cards inside the deck

        String deck = "";

        for (int i = 0; i <= cards.size()-1; i++) {



            Short a = cards.get(i);


            deck += Card.decode(a) + (i == cards.size()-1 ? "." : ", ");

        }
        return deck;
    }

    public void deal(LinkedList<Player> players, byte cards) { // this also deals cards, but to everyone in the game


    }

}
