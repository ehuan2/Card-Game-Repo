/**
 * @(#)CardGame.java
 *
 * CardGame application
 *
 * @author 
 * @version 1.00 2020/2/4
 */
 
public class Deck {
    Stack<Short> cards;
    
    class Card {
    	byte value;
    	char face;
    	
    	public Card(){
    		value = 0;
    		face = '*';
    	}
    	
    	public Card(byte vle, char fce){
    		value = vle;
    		face = fce;
    	}
    	
    	public static short encode(Card card){ // so this encodes the card to a short, the hearts are 0-12 
    											// the spades are 13-25, the clubs are 26-38 
    											// and the diamonds are 39-51
    		byte fceValue = face == 'H' ? 0 : face == 'S' ? 1 : face == 'C' ? 2 : 3;
    		return value + fceValue*13;
    		
    	}
    	
    	public static Card decode(short cardShort){ // this decodes the short to a new card
    		
    		return new Card(cardShort % 13, cardShort/13); // this simply decodes the short, 
    														// by getting the remainder as the value, 
    														// then the face is through the multiple 
    		
    	}
    	
    }
    
    public Deck(){
    	
    	for(int i = 0; i < 52; i++){
    		cards.add(i);
    	}
    	
    }
    
    public void shuffle(){
    	
    	short[] shuffled = new short[this.cards.size()];
    	Set<Integer> set = new HashSet<>();
    	for(Short a : shuffled)
    	
    }
    
}
