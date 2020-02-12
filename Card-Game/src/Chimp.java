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
    static int playerIndex = 0;
    static int deckValue = 0;
    static Deck deck;
    static Deck discard;
    
    public static void nextPlayer(){
    	
    	Player next = players.get(playerIndex);
    	
    	Scanner scan = new Scanner(System.in);
    	
    	short index = scan.nextShort("Which card do you want to play? Pick the index of the card, ie : First card do 1") - 1;
    	
    	next.playCard(Card.decode(next.get(index))); // so gets the index, turns it into a card, and then plays it
    													// would be better to simply pass in an int or a short
    													// but a card is wanted (method specification)
    	next.pickUp(); 								
    	roundOver();
    	
    }
    
    public static void roundOver(){ // this checks the roundover and stuff
    	if(deckValue > 99){ // if it's over goes here
    		
    		players.get(playerIndex).chimp++; // adds a chimp to the player that got it
    		
    		
    		
			if(chimp >= 5){ // if it got chimp, remove the player
				
			 	discard.get(players.get(playerIndex).removeAll()); // removes all deck and puts into discard pile
    			players.remove(playerIndex); // removes that indexed player
    		
    			if(players.size() <= 1){
    				isWinner();
    				return;
    			}
    			playerIndex-=counter;
    		}
    		
    	}
    	
    	playerIndex += counter;
    	if(playerIndex < 0){
    		playerIndex = players.size()-1;
    	}
    	else if(playerIndex >= players.size()){
    		
    		playerIndex = 0;
    		
    	}
    	
    }
    
    public static void isWinner(){
    	System.out.println(players.get(0).name + " is the WINNER!");
    }
    
    public static void main(String[] args) {
    	
    	
    }
}
