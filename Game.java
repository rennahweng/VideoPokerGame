/**************************************************
 * Xin Ying Weng
 * xw2600
 * 05/01/19
 * 
 * Game.java - Game class for the Video Poker game.
 * 
 * A casino game based on 5-card draw poker.
 * Player has a starting balance of Bankroll and
 * can bet one or more credits. Top 5 cards are
 * presented and player has the choice to discard
 * none, some, or all cards in exchange for new
 * ones. After the draw, if the hand played match
 * one of the winning combinations based on how
 * rare they are, player wins a payout.
 **************************************************/ 

import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	private Player p;
	private Deck deck;
    // player always have 5 cards in hand after deal
    private final int FULL_HAND_SIZE = 5;
    
    private Scanner in; // for user inputs later
	
	// test constructor
	public Game(String[] testHand){
		// This constructor is to help test the code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
        
        in = new Scanner(System.in); // promt player later
        
        p = new Player();
        deck = new Deck();
        System.out.println(deck);
        // *****The previous line shows a beautiful
        // representation of a deck of 52 cards!*****
        
        for (String card : testHand) {
            // determine the suit of each card by taking the
            // first character of each card the player puts in
            char readSuit = card.charAt(0);
            int suit = 0; // get suit, corresponding to integers 1-4
            switch (readSuit) {
                case 'c':
                    suit = 1; // club
                    break;
                case 'd':
                    suit = 2; // diamond
                    break;
                case 'h':
                    suit = 3; // heart
                    break;
                case 's':
                    suit = 4; // spade
                    break;
            }
            
            // determine the rank for each card by converting the
            // convert String at index 1 to last index to an Integer
            int rank = Integer.parseInt(card.substring(1));
            // get rank, corresponding to integers 1-13
            
            // create new Cards based on user inputs
            Card newCard = new Card(suit, rank);
            // add new cards to player's hand
            p.addCard(newCard);
        }
	}
    
    // normal constructor
	public Game(){
		// This no-argument constructor is to actually play a normal game
		in = new Scanner(System.in); // use to promt player later
        p = new Player();
        deck = new Deck();
        System.out.println(deck);
        // *****The previous line shows a beautiful
        // representation of a deck of 52 cards!*****
        
        
	}
	
	public void play(){
		System.out.println("------Welcome to Video Poker World!------");
        
        // starting a new round:
        // play if player still has availiable bankroll
        boolean start = true;
        while(start && p.getBankroll() > 0){
            // display player's tokens
            System.out.println("\n---New Round---");
            System.out.println("\nTokens: " + p.getBankroll());
            
            // prompt if player wants to continue
            // based on the amount of tokens left
            System.out.print("Enter [y]es to start a round, [n]o to end: ");
            String round = in.next().toLowerCase();
            
            // end the round if no
            if(round.equals("n")){
                start = false; // end the while loop
            }

            // start a new round if yes
            else if(round.equals("y")){
                p.bets(0); // reset player bet to zero for every new round
                while(true){
                    System.out.print("How many tokens do you want"
                                       + " to bet? 1-5 only: ");
                    double bet = in.nextDouble(); // set bet
                    
                    // check the following conditions for user's bet input:
                    
                    // reprompt if bet value is invalid
                    if( bet < 1 || bet > 5 ){
                        System.out.println("\nERROR! Your bet is not between"
                                           + " 1-5. Enter again.");
                        continue;
                    }
                    
                    // exit the round if not enough token left for bet
                    if( bet > p.getBankroll() ){
                        System.out.println("\nSorry, you're running"
                                           + " out of token.");
                        break;
                    }
                    
                    // update player's bet and bankroll if bet is valid
                    p.bets(bet);
                    
                    // get a completely new shuffled deck
                    deck.reStart();
                    System.out.println(deck);
                    
                    // deal top five cards of the deck
                    // only if player's hand is empty.
                    // This also ensures that a new hand won't
                    // be dealt when test-constructor is used,
                    // in which command line arguments are
                    // provided as the testHand.
                    if(p.getHand().size() == 0){
                        this.deal5Cards();
                        // 5 cards are dealt
                        // no repeating cards are dealt to player's hand
                    }

                    // present hand to the player
                    p.sortHand();
                    System.out.println("Your Hand: " + p.getHand());
                    
                    // ask if player wants to exchange any cards
                    while(true){
                        System.out.print("Do you want to exchange?"
                                       + " [y]es or [n]o: ");
                        String exch = in.next().toLowerCase();
                        
                        // exchange if yes
                        if(exch.equals("y")){
                            this.exchangeCard();
                            // sort hand after exchange is done
                            p.sortHand();
                            System.out.println("\nYour New Hand: "
                                               + p.getHand());
                            break;
                        }
                        // skip exchange if no
                        else if(exch.equals("n")){
                            break;
                        }
                        // prompt player again if invalid
                        else{
                            System.out.println("ERROR! Please enter y or n.");
                            continue;
                        }
                    }
                    
                    System.out.println("\n------TIME FOR PRIZES!!------");
                    // score player's hand based on the
                    // payout chart to see if there's
                    // any combinations
                    String combo = checkHand( p.getHand() );
                    System.out.printf("You got: %s! \n", combo);
                    
                    // get payout based on the combination
                    // get score using getPayout() method
                    
                    // calculate winning tokens won by player:
                    // winning tokens = payout * bet
                    double tokenWon = getPayout(combo) * bet;
                    System.out.printf("You gained: %s tokens. \n", tokenWon);
                    
                    // update player's bankroll
                    p.winnings(tokenWon);
                    System.out.printf("You now have: %s tokens "
                                       + "in total. \n", p.getBankroll());
                    
                    // empty player's hand at the end
                    // for the next new round
                    p.removeAllCards();
                    break;
                }
                continue; // end the round
            }
            
            // reprompt the player if neither yes or no
            else{
                System.out.println("ERROR! Please enter y or n only!");
                continue;
            }
        }
        System.out.println("\n---------Thank you for Playing!!---------\n");
	}
	
    // BELOW ARE ALL THE METHODS EXIST IN PLAY() METHOD:
    
    private Card newDealtCard(){
        // this method deals a new card that has not existed in hand
        
        Card newCard = null; // a placeholder for new dealt card
        
        boolean dealt = true;
        // assume a card is already dealt
        while(dealt){
            // deal the very top card
            newCard = deck.deal();
            dealt = false;
            
            // go through all the cards in player's current hand
            // keep dealing a newCard until all cards are different
            for (Card dealtCard : p.getHand()) {
                // continue if the newCard already exists in hand
                if (newCard.compareTo(dealtCard) == 0){
                     dealt = true;
                }
            }
            // at this point,
            // if dealt=true, continue the while loop and next comparison
            // if dealt=false, break the while loop and return newCard
        }
        return newCard;
    }
    
	private void deal5Cards(){
        // this method deals 5 cards to player
        
        for(int i=0; i<FULL_HAND_SIZE; i++){
            // ensures that a complete new card is dealt,
            // so that no repeating cards will
            // be dealt when using testHand.
            p.addCard( this.newDealtCard() );
        }
    }
    
    
	private void exchangeCard(){
        // this method exchanges one, some, or all cards
        // in hand based on player's preferences
        
        while(true){
            // ask for the amount of cards to be exchanged
            System.out.print("\nHow many cards do you want"
                           + " to exchange? (1-5): ");
            int numOfExch = in.nextInt();
            
            // reprompt if numOfExch is invalid
            if(numOfExch < 1 || numOfExch > 5){
                System.out.println("\nERROR! At least 1 and at most 5 cards!");
                continue;
            }
            
            // perform exchange if valid:
            
            // if (numOfExch >= 1 && numOfExch <= 4)
            // player picks whichever cards to exchange
            else{
                // store player's selected position so
                // player can't enter same position twice
                int[] selectedPos = new int[numOfExch];
                
                // exchange one card at a time for numOfExch times
                int i = 0;
                while(i < numOfExch){
                    // get the card position that the player wants to exchange
                    System.out.print("Which card? (1-5): ");
                    int cardPos = in.nextInt();
                    
                    // check if cardPos is valid
                    boolean validPos = true;
                    while(validPos){
                        validPos = false;
                        // reprompt if card position is invalid
                        if(cardPos < 1 || cardPos > 5){
                            System.out.print("\nCards are at position 1-5 only."
                                              + "\nPlease enter again: ");
                            cardPos = in.nextInt();
                            validPos = true; // repromt
                        }
                        
                        // check if player has previously chosen
                        // to exchange the selected card
                        else{
                            for(int existedPos : selectedPos){
                                // compare the existing positions with
                                // the cardPos that player just selected
                                if(cardPos == existedPos){
                                    // repromt if already selected previously
                                    System.out.print("\nYou already chose this "
                                                      + "card! \nPick another one: ");
                                    cardPos = in.nextInt();
                                    validPos = true;
                                }
                            }
                        }
                    }
                    // if card position is valid
                    // add it to the selectedPos array
                    selectedPos[i] = cardPos;
                    
                    // replace the card at the desired
                    // card position with a new card dealt
                    // from the top of the same deck 
                    p.getHand().set(cardPos-1, this.newDealtCard());
                    
                    // move on to next card
                    i++;
                }
            }
            // all cards at desired positions are exchanged
            break;
        }
    }
    
    
    // METHODS TO CHECK COMBINATIONS AND PAYOUTS:
    
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		if (isRoyalFlush(hand)) {
            return "Royal Flush";
        }
        else if (isStraightFlush(hand)) {
            return "Straight Flush";
        }
        else if (isFourOfAKind(hand)) {
            return "Four of a Kind";
        }
        else if (isFullHouse(hand)) {
            return "Full House";
        }
        else if (isFlush(hand)) {
            return "Flush";    
        }
        else if (isStraight(hand)) {
            return "Straight";
        }
        else if (isThreeOfAKind(hand)) {
            return "Three of a Kind";
        }
        else if (isTwoPairs(hand)) {
            return "Two Pairs";
        }
        else if (isOnePair(hand)) {
            return "One Pair";
        }
        else{
            return "No Pair";
        }

	}
    
    // METHODS FOR ALL DIFFERENT COMBINATIONS:
    
    private int numOfMatchRanks(ArrayList<Card> hand){
        // checks how many cards have the same rank
        // this method is useful for multiple
        // methods that checks combinations
        
        // compare Card at current position with
        // the rest of the cards
        
        // start off with one card
        // so NO PAIR means match=1
        int match = 1;
        for(int i=0; i < FULL_HAND_SIZE-1; i++){
            // comparison starts from the very next card
            for(int j=i+1; j< FULL_HAND_SIZE; j++){
                // currentCard = hand.get(i);
                // comparedCard = hand.get(j);
                if( hand.get(i).getRank() == hand.get(j).getRank() ){
                // uptick match by 1 every time
                // the card finds a matching rank
                match++;
                }
            }
        }
        // get total number of matchings
        return match;
    }
    
    // No Pair means match stays as 1
    // update total match based on the following combinations
    // ***HOWEVER, THIS LOGIC ONLY WORKS WHEN HAND SIZE IS 5!***
    
    private boolean isOnePair(ArrayList<Card> hand){
        // one pair of two cards of the same rank
        
        // ex: 1 1 2 3 4
        // 1 and 1 match once --> match upticks once --> match++
        // total match is 1+(1) = 2
        
        if(this.numOfMatchRanks(hand) == 2){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isTwoPairs(ArrayList<Card> hand){
        // two pairs of two cards of the same rank
        
        // ex: 2 2 3 4 4, or 2 2 4 4 5, or 1 2 2 4 4
        // 2 and 2 match --> match++ 
        // 4 and 4 match --> match++
        // total match is 1+(1+1) = 3
        if(this.numOfMatchRanks(hand) == 3){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean isThreeOfAKind(ArrayList<Card> hand){
        // three cards of the same rank
        
        // ex: 3 3 3 4 5, or 1 2 3 3 3, or 1 3 3 3 4
        // first and second 3 match --> match++
        // first and third 3 match --> match++
        // second and third 3 match --> match++
        // total match is 1+(1+1+1) = 4
        if(this.numOfMatchRanks(hand) == 4){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isFullHouse(ArrayList<Card> hand){
        // 3 of a kind && one pair
        
        // ex: 2 2 2 3 3
        // first 2 matches with second 2 and third 2 --> +2
        // second 2 matches with third 2 --> +1
        // first 3 matches with second 3 --> +1
        // total match = 1+(2+1+1) = 5
        if(this.numOfMatchRanks(hand) == 5){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isFourOfAKind(ArrayList<Card> hand){
        // four cards of the same rank
        
        // ex: 2 2 2 2 3, or 1 2 2 2 2
        // first and second 2, third 2, fourth 2 match --> +3
        // second and third 2, fourth 2 match --> +2
        // third and fourth 2 match --> +1
        // total match is 1+(3+2+1) = 7
        if(this.numOfMatchRanks(hand) == 7){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean isStraight(ArrayList<Card> hand){
        // ranks of all cards are in a consecutive order
        
        // loop through all cards,
        // compare the card only with
        // its immediate-next card
        for(int i=0; i<FULL_HAND_SIZE-1; i++){
            // compare current card's rank with the next card's
            int current = hand.get(i).getRank();
            int next = hand.get(i+1).getRank();
            
            // For special case A 10 J Q K:
            // if current rank is lowestAce A=1 and
            // next rank is 10. Then it is potentially
            // a A,10,J,Q,K straight, so A might be
            // highestAce A=14.
            // So skip this comparison and jump to
            // the next comparisons to see if the
            // rest are in order.
            if(current == 1 && next == 10){
                continue;
            }
            // if they are not A-10, then A is still 1,
            // move on and check them normally.
            
            // return false if one pair of cards
            // is not in consecutive order.
            // consecutive means: current=next-1
            if( current != next-1 ){
                return false;
            }
        }
        // return true if all pairs are in order
        return true;
    }
    
    private boolean isFlush(ArrayList<Card> hand){
        // five cards of the same suit
        
        for(int i=0; i<FULL_HAND_SIZE-1; i++){
            // compare current card's suit with next card's
            int currentSuit = hand.get(i).getSuit();
            int nextSuit = hand.get(i+1).getSuit();
            // return false if one pair doesn't match
            if(currentSuit != nextSuit){
                return false;
            }
        }
        // return true if all pairs match up
        return true;
    }
    
    private boolean isStraightFlush(ArrayList<Card> hand){
        // five cards of the same suit in consecutive order
        // straight + flush
        
        if( this.isFlush(hand) && this.isStraight(hand) ){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean isRoyalFlush(ArrayList<Card> hand){
        // cards A 10 J Q K of same suit
        // flush + lastCard=K + firstCard=A + isStraight
        int lastCard = hand.get(FULL_HAND_SIZE-1).getRank();
        int firstCard = hand.get(0).getRank();
        if( this.isFlush(hand) && lastCard==13 && 
            firstCard==1 && this.isStraight(hand) ){
            return true;
        }
        return false;
    }
    
    
    // GIVE PAYOUT TO PLAYERS
    private int getPayout(String combo){
        // this method gets player's score
        // based on combination player has
        if(combo.equals("Royal Flush")){
            return 250;
        }
        else if(combo.equals("Straight Flush")){
            return 50;
        }
        else if(combo.equals("Four of a Kind")){
            return 25;
        }
        else if(combo.equals("Full House")){
            return 6;
        }
        else if(combo.equals("Flush")){
            return 5;
        }
        else if(combo.equals("Straight")){
            return 4;
        }
        else if(combo.equals("Three of a Kind")){
            return 3;
        }
        else if(combo.equals("Two Pairs")){
            return 2;
        }
        else if(combo.equals("One Pair")){
            return 1;
        }
        else{ // if combo.equals("No Pair")
            return 0;
        }
    }
    
    // END OF PROGRAM
}
