/*
 * Eric Huang - Feb.10th 2020
 *
 * This is made for the CHIMP Game, this the deck game
 *
 */
 
public class Deck { // this is the deck class for the cards
    LinkedList<Short> cards; // this is the linkedlist of cards, easier cause it's dynamic
    final int DECK = 52; // this will toggle the amount of cards
    
   
    
    public Deck(){ // initializes a full deck
    	
    	for(int i = 0; i < DECK; i++){
    		cards.add(i);
    	}
    	
    }
    
    public void shuffle(){ // this shuffles the cards
    	
    	short[] shuffled = new short[this.cards.size()]; // creates a new array of all the shuffled cards size
        LinkedList<Short> list = new LinkedList<>(this); // creates a linkedlist of all the cards
       
        for(int i = 0; i <= shuffled.length-1; i++){ // iterates through them all, removing the cards from list one by one
            int takeOut = Math.random()*list.size(); // goes from the 0 to c-1 index
            shuffled[i] = list.remove(takeOut); // adds it to the next index that it can be
        }
        
        this.cards.clear(); // clears original cards
        this.cards.addAll(shuffled); // adds the shuffled to it
    	
    }
    
    public Deck(Stack<Short> stack){ // this is the constructor if it has a stack already made
    	this.cards.addAll(stack);
    }
    
    public short giveCard(int card){ // gives away the cards needed
    									// looks for the index of the card
    	return cards.remove(card);
    	
    }
    
    public int[] dealCards(byte n){ // this deals away n cards
    	int[] giveAway = new int[n]; // goes through, returns an array of the cards given
    	for (int i = 0; i<n; i++){
    		giveAway[i] = cards.removeFirst();
    	}
    	return giveAway;
    }
    
    public void sort(){ // this can sort the cards inside the deck
    	
    	Short[] deckSorted = cards.toArray(new Short[cards.size()]); // creates a new array for sorted
    	Arrays.sort(deckSorted); // sorts it
    	cards.removeAll(); // removes all the original ones
    	cards.addAll(new LinkedList<>(Arrays.asList(deckSorted))); // adds it all to it
    	
    }
    
    public String toString(){ // returns a string of all cards inside the deck
    	
    	String deck = "";
    	
    	for (Short a : cards){
    		
    		deck += Card.decode(a) + ", ";
    		
    	}
    	return deck;
    }
    
    public void deal(Person[] a, byte cards){ // this also deals cards, but to everyone in the game

        for (Person p: a) {
            for (int i = 0; i < cards; i++) {
                if(cards.isEmpty()){
                    return;
                }
                a.push(cards.pop());
            }
        }
        
    }
    
}
