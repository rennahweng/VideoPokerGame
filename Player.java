/*****************************************
 * Xin Ying Weng
 * xw2600
 * 04/28/19
 * 
 * Player.java - Player class for Game class
 * 
 * Create a template for each poker player.
 * Each player consists of a bankroll value
 * for tokens and bets in Game class,
 * and a hand of cards, in which player can
 * choose to add or remove cards from hand.
 ****************************************/ 

import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	    hand = new ArrayList<Card>();
        bankroll = 20; // Player gets a starting bankroll of 25
        bet = 0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from player's hand
	    hand.remove(c);
    }
    
    public void removeAllCards(){
        // empty player's hand
        // clear all cards from hand
        hand.clear();
    }
    
    public void bets(double amt){
        // player makes a bet
        bet = amt;
        bankroll -= amt;
    }

    public void winnings(double odds){
        // adjust bankroll if player wins
        bankroll += odds;
    }

    public double getBankroll(){
        // return current balance of bankroll
        return bankroll;
    }
    
    public ArrayList<Card> getHand(){
        // get player's current hand
        return hand;
    }
    
    public void sortHand(){
        // put the five cards in order
        Collections.sort(hand);
    }
}


