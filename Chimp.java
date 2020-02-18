/* Eric Huang
* CHIMP Game - Chimp Class
* A class that plays the entire game
* Includes all game components alongside the GUI
* Start date : Feb.10th 2020 
* End date : Feb.18th 2020
*/

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Chimp {

    static LinkedList<Player> players; // a linkedlist that holds all the players
    static byte counter = 1; // counter that determines the next player in the list
    static int playerIndex = 0; // a playerindex that holds the index of the next player, and deckvalue for the value of the deck
    static int deckValue = 0;
    static Deck deck; // two decks, one for the regular deck, other is discard
    static Deck discard;
    static GUI gui; // a gui that displays it all
    static boolean end = false; // checks if the game ended or not

    public static void nextPlayer(int index) { // takes in an integer (has to be integer for the index) that determines the card picked

        Player next = players.get(playerIndex); // gets the next player
        
        // displays the move played
        gui.txtMoves.append("\n" + next.name + " played " + Card.decode(next.deck.cards.get(index)) + "\n"); 
        
        next.playCard(Card.decode(next.deck.cards.get(index))); // so gets the index, turns it into a card, and then plays it
        // would be better to simply pass in an int or a short
        // but a card is wanted (method specification) - not the most efficient, but client (Ms.Shaw) specified for playCard(Card aCard)

        next.pickUp(); // picks up a card
        
        roundOver(); // checks if the round is over, and chooses next player

        if(end){ // if it is the end, then you want to stop
            
            // for each loop to disable all buttons
            for (JButton a: gui.cardChoices) { 
                a.setEnabled(false);
            }
            
            return; // returns, stops it from running, prevents it from displaying next move
        }

        // displays information, deck value, player and the next card
        
        gui.txtMoves.append("\nDeck Value is : " + deckValue);
        gui.txtMoves.append("\nNext round \nPlayer : " + players.get(playerIndex));
        gui.txtMoves.append("\nWhich card do you want to play? Pick the index of the card\n");

    }

    public static void roundOver() { // this checks the roundover and stuff

        if (deckValue > 99) { // if it's over goes here
            
            deckValue = 0; // resets deck value
            
            Player playerNext = players.get(playerIndex); // gets the next player
            playerNext.chimp++; // adds a chimp to the player that got it
            gui.txtMoves.append("\n" + playerNext.name + " got chimped!! They have " + (5 - playerNext.chimp) + " lives left!!");

            if (playerNext.chimp >= 5) { // if it got chimp, remove the player
                
                int size = playerNext.deck.cards.size();
                
                for (int i = 0; i < size; i++) {
                    discard.cards.add(playerNext.deck.giveCard(0)); // removes all deck and puts into discard pile
                }

                players.remove(playerIndex); // removes that indexed player

                if (players.size() <= 1) { // if it's less than 2, then there is a winner
                    isWinner(); // goes to isWinner();
                    return;
                }
                
                playerIndex -= counter; // otherwise, it continues with one less person, this one goes back the counter, and then continues
            }

        }

        playerIndex += counter; // adds the counter to go to next person

        if (playerIndex < 0) { // if there is an index less than 0, means we are at the end
            
            playerIndex = players.size() - 1; // goes to end
            
        } else if (playerIndex >= players.size()) { // otherwise it goes to 0, if it is at the other end

            playerIndex = 0;

        }


    }

    public static void isWinner() {
        JOptionPane.showMessageDialog(gui.frame, players.get(0).name + " is the WINNER!"); // displays who is the winner
        
        end = true;
    }

    public static void main(String[] args) {

        gui = new GUI(); // generates the ui

       
        // the next block of code is to show the players possible
        // if a number is not picked, keeps regenerating the joptionpane until picked
       
        String[] options = new String[]{"2", "3", "4", "5"}; 
        int num = 0;
       
        while(num != 2 && num != 3 && num != 4 && num != 5) {
            num = JOptionPane.showOptionDialog(gui.frame, "Choose the number of players!",
                    "Number of Players",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]) + 2;
        }
        
        
        deck = new Deck(); // creates the two new decks
        discard = new Deck(new LinkedList<>());

        // adds in the players, gets the players' names
        players = new LinkedList<>();
        
        for (int i = 0; i < num; i++) {
            players.add(new Player(
                    JOptionPane.showInputDialog(gui.frame, "What is Player " + (i + 1) + "'s name?")));
        }

        // shuffles cards and adds them to the players
        
        deck.shuffle();
        deck.deal(players, (byte) 3);

        // for every player, display all their cards
        
        for (Player a : players) {
            gui.txtMoves.append(a.toString() + "\n");
        }

        
        // asks for the first person's cards
        
        gui.txtMoves.append("\nDeck Value is : " + deckValue);
        gui.txtMoves.append("\nNext round \nPlayer : " + players.get(playerIndex));
        gui.txtMoves.append("\nWhich card do you want to play? Pick the index of the card\n");

    }

}
