/**
 * @(#)CHIMP.java
 *
 * CHIMP application
 *
 * @author 
 * @version 1.00 2020/2/10
 */
 
public class CHIMP {
    
    static LinkedList<Player> players;
    static int counter = 1;
    static int playerIndex = 0;
    
    static int deckValue = 0;
    
    public static void playCard(Card aCard){ // this is playing cards
    	
    
    	
   		checkMove();
    	
    	
    }
    
    
    public static void checkMove(){
    	
    	switch (aCard.value) {
		case 1 : 
			deckValue += aceValue();
			break;
		case 10 : 
			
			deckValue -= 10;
			
			break;
		default : 
			deckValue += aCard.value; // nothing special, adds value
			break;
		}
    	
    }
    
    public static void main(String[] args) {
    	
    	
    }
}
