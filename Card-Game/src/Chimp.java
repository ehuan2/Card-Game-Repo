/**
 * @(#)CHIMP.java
 *
 * CHIMP application
 *
 * @author 
 * @version 1.00 2020/2/10
 */
 
public class Chimp {
    
    static LinkedList<Player> players;
    static int counter = 1;
    static Player player;
    
    static int deckValue;
    
    public static void playCard(Card aCard){ // this is playing cards
    	
    switch (aCard.value) {
		case 1 : break;
		case 10 : break;
		default : 
			deckValue += aCard.value; // nothing special, adds value
			break;
	}
    	
   	
    	
    	
    }
    
    public static void main(String[] args) {
    	
    	
    }
}
