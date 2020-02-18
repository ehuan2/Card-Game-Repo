
/*
 * Eric Huang - Feb.12th 2020
 * CHIMP Game
 */

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Chimp {

    static LinkedList<Player> players;
    static int counter = 1;
    static int playerIndex = 0;
    static int deckValue = 0;
    static Deck deck;
    static Deck discard;
    static GUI gui;
    static boolean end = false;

    public static void nextPlayer(int index) {

        Player next = players.get(playerIndex);
        gui.txtMoves.append("\n" + next.name + " played " + Card.decode(next.deck.cards.get(index)));
        gui.txtMoves.append("\n");
        next.playCard(Card.decode(next.deck.cards.get(index))); // so gets the index, turns it into a card, and then plays it
        // would be better to simply pass in an int or a short
        // but a card is wanted (method specification)

        next.pickUp();
        roundOver();

        if(end){
            for (JButton a: gui.cardChoices) {
                a.setEnabled(false);
            }
            return;
        }

        gui.txtMoves.append("\nDeck Value is : " + deckValue);
        gui.txtMoves.append("\nNext round \nPlayer : " + players.get(playerIndex));
        gui.txtMoves.append("\nWhich card do you want to play? Pick the index of the card\n");

    }

    public static void roundOver() { // this checks the roundover and stuff

        if (deckValue > 99) { // if it's over goes here
            deckValue = 0;
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


        playerIndex += counter;

        if (playerIndex < 0) {
            playerIndex = players.size() - 1;
        } else if (playerIndex >= players.size()) {

            playerIndex = 0;

        }


    }

    public static void isWinner() {
        JOptionPane.showMessageDialog(gui.frame, players.get(0).name + " is the WINNER!");
        end = true;
    }

    public static void main(String[] args) {

        gui = new GUI();

        String[] options = new String[]{"2", "3", "4", "5"};
        int num = 0;
        while(num != 2 && num != 3 && num != 4 && num != 4+1) {
            num = JOptionPane.showOptionDialog(gui.frame, "Choose the number of players!",
                    "Number of Players",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]) + 2;
        }
        deck = new Deck();

        discard = new Deck(new LinkedList<>());

        players = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            players.add(new Player(
                    JOptionPane.showInputDialog(gui.frame, "What is Player " + (i + 1) + "'s name?")));
        }

        deck.shuffle();
        deck.deal(players, (byte) 3);

        for (Player a : players) {
            gui.txtMoves.append(a.toString() + "\n");
        }

        gui.txtMoves.append("\nDeck Value is : " + deckValue);
        gui.txtMoves.append("\nNext round \nPlayer : " + players.get(playerIndex));
        gui.txtMoves.append("\nWhich card do you want to play? Pick the index of the card\n");

    }

}
