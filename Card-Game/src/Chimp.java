
/*
 * Eric Huang - Feb.12th 2020
 * CHIMP Game
 */

import java.util.*;

public class Chimp {

    static LinkedList<Player> players;
    static int counter = 1;
    static int playerIndex = 0;
    static int deckValue = 0;
    static Deck deck;
    static Deck discard;

    public static void nextPlayer() {
        System.out.println("\nDeck Value is : " + deckValue);
        Player next = players.get(playerIndex);
        System.out.println("Next round \nPlayer : " + next);
        Scanner scan = new Scanner(System.in);

        System.out.println("Which card do you want to play? Pick the index of the card, ie : First card do 1");
        int index = scan.nextInt() - 1;

        next.playCard(Card.decode(next.deck.cards.get(index))); // so gets the index, turns it into a card, and then plays it
        // would be better to simply pass in an int or a short
        // but a card is wanted (method specification)
        System.out.println("test " + deckValue);
        next.pickUp();
        roundOver();

    }

    public static void roundOver() { // this checks the roundover and stuff

        if (deckValue > 99) { // if it's over goes here
            deckValue = 0;
            Player playerNext = players.get(playerIndex); // gets the next player
            playerNext.chimp++; // adds a chimp to the player that got it


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

        nextPlayer();

    }

    public static void isWinner() {
        System.out.println(players.get(0).name + " is the WINNER!");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("How many players are there?");



                int num = scan.nextInt();

                if(num > 4){ // throws an exception if it's greater than 4

                }

                deck = new Deck();
                discard = new Deck(new LinkedList<>());

                players = new LinkedList<>();
                for (int i = 0; i < num; i++) {
                    System.out.println("What is Player " + i + "'s Name?");
                    players.add(new Player(scan.next()));
                }
                deck.shuffle();
                deck.deal(players, (byte)3);

                for (Player a: players) {
                    System.out.println(a);
                }

                nextPlayer();



        }

    }
