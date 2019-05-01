/*****************************************
 * Xin Ying Weng
 * xw2600
 * 04/20/19
 * 
 * Deck.java - Deck class consists of 52 poker cards
 * 
 * Allows shuffling deck and dealing the top card.
 ****************************************/ 

import java.util.Random;

public class Deck {
	
    // have the total number of cards in a poker deck = 52
    private final static int TOTAL_CARDS = 52;
	private Card[] deck; 
	private int top; // the index of the top of the deck
	
    
	public Deck(){
        deck = new Card[TOTAL_CARDS]; // no magic number ;)
        top = 52; // top starts with the last card in the deck
        
        // create a deck of 52 cards using nested for loops
        // so that for each suit (4 in total), there are
        // 13 different ranks. So 4*13 --> 52 cards in total.
        
        int cardPosition = 0; // start with deck[0]
        for(int s=1; s<=4; s++){ // 4 suits
            for(int r=1; r<=13; r++){ // 13 ranks
                // add the new card to the deck
                deck[cardPosition] = new Card(s,r);
                cardPosition++;
            }
        }
        // a full deck of 52 cards from smallest to biggest is created
	}
	
	private void shuffle(){
		// shuffle the deck here
		// by changing the card positions randomly
		Random r = new Random();
        
        for (int i=0; i < TOTAL_CARDS; i++) {
            // generate a random index within the 
            // total number of cards in a deck
            int randomPosition = r.nextInt(TOTAL_CARDS);
            // get and store a card from the orginal deck
            Card tempCard = deck[i];
            // replace that card with another card that's
            // orginally located at a random position in the deck,
            // so the original card is replaced with a random card
            deck[i] = deck[randomPosition];
            // that random card's original spot is then
            // replaced with the card that's stored earlier
            deck[randomPosition] = tempCard;
            // keep switching the two cards randomly
        }
        // after 52 random switches, the whole deck is shuffled.
	}
	
	public Card deal(){
		// deal the top card in the deck
		
		// top card starts at the last 52th card,
		// Then the top index moves inward to smaller index
		// after each time the current top card is dealed.
		return deck[--top];
        // Start with top=52, --top makes top=51
        // so the very first deal is deck[51],
        // which is the first card in the deck
	}
	    
    // create a new deck of cards if game restarts
    public void reStart(){
        top = 52;
        this.shuffle();
    }
    
    
	public String toString(){
        
        String showDeck = "";
        // label card numbers to makes sure there're 52 of them
        int card_num = 1;
         
        for(Card cards : deck){
            showDeck += String.format("%d: %s\n", card_num, cards);
            card_num++;
        }
        return showDeck;
    }
}