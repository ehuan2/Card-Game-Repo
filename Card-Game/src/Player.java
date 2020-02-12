
public class Player { // a class player for each player

	Deck deck;
	
	String name;
	
	int chimp;
	
	final static String CHIMP = "CHIMP";
	
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
	
	public short playCard(Card aCard){ // plays a card
		Short decode = aCard.decode(); // decodes the card
		Chimp.discard.receiveCard(this.deck.giveCard(this.deck.indexOf(decode))); // gets the index of the card, and gives it away
		
		checkMove(decode); // returns a value for it all
		
	}
	
	public void checkMove(Short cardValue){ // this goes through all of the possible moves, returns value
    	
    	switch (cardValue) { // switch statement that considers the moves
		case 1 : // if it's this case, goes to aceValue, asks for input of either 1 or 11
			Chimp.deckValue += aceValue();
			return;
		case 4 : // skips essentially just is a zero card
			return;
		
		case 9 :
			Chimp.counter = -Chimp.counter; // if it's a 9, reverse direction
			return;
			
		case 10 : // if it's a ten, minus ten
			
			Chimp.deckValue -= 10;
			return;
		case 12 :
			Chimp.deckValue = 99; // sets the deck value to 99, then returns a zero
			return;
		case 11 || 13 :
			Chimp.deckValue += 20;
			return;
		default : 
			Chimp.deckValue += cardValue; // nothing special, adds value
			return;
		}

    }

	public short aceValue(){
		Scanner scan = new Scanner(System.in); // opens new scanner
		
		byte a = scan.nextByte("Value is 1 or 11?"); // gets a byte
		if(a != 1 && a != 11){ // checks if it's one or eleven
			scan.close();
			System.out.println("Put in 1 or 11!");
			return aceValue(); // if not, then retries
		}
		return a; // otherwise, returns it
		
	}
    
    public void pickUp(){
    	
    	if(Chimp.deck.cards.size() <= 0){
    		
		for(Short a : Chimp.discard.cards){
			Chimp.deck.receiveCard(a);	
		}
		
    	}
    	this.deck.receive(Chimp.deck.giveCard(0)); // takes deck, and puts in the 0th index into player's deck
    	
    	
    }
    
    public String toString(){ // to string method for people's decks
	    String status = CHIMP.substring(0, chimp); // gets the substring of chimp in order to print it out later 
	    
	    return name + "'s deck : " + deck + " CHIMP : " + status; 
    }
    
	
}
