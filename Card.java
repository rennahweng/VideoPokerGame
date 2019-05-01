/*****************************************
 * Xin Ying Weng
 * xw2600
 * 04/20/19
 * 
 * Card.java - Card class for Deck class
 * Create a template for the 52 poker cards,
 * each card has a rank and a suit.
 ****************************************/ 

public class Card implements Comparable<Card>{
	
    // 13 ranks and 4 suits in total
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
        // make a card with suit s and rank r
		suit = s;
        rank = r;
	}
	
	public int compareTo(Card c){
		// compare cards to make sorting easier
		
		// compare first by rank
		if (rank > c.rank){
            return 1;
        }
        else if (rank < c.rank){
            return -1;
        }
        
        // if rank == c.rank
        else{
            // compare by suit
            if (suit == c.suit){
                return 0;
            }
            else if (suit > c.suit){
                return 1;
            }
            // else if (suit < c.suit)
            else{ 
                return -1;
            }
        }
	}
	
	public String toString(){
        // use switch case to decode ranks and suits
        String suitStr, rankStr;
        
        switch(suit){
            // follow the order spade > heart > diamond > club
            case 1:
                suitStr = "♣"; // Club, the smallest suit
                break;
            case 2:
                suitStr = "♦"; // Diamond
                break;
            case 3:
                suitStr = "♥"; // Heart
                break;
            case 4:
                suitStr = "♠"; // Spade, the greatest suit
                break;
            default:
                suitStr = "ERROR";
                break;
        }
        
        switch(rank){
                // 13 cards, from Ace to King
            case 1:
                rankStr = "A"; // Ace
                break;
            case 2:
                rankStr = "2";
                break;
            case 3:
                rankStr = "3";
                break;
            case 4:
                rankStr = "4";
                break;
            case 5:
                rankStr = "5";
                break;
            case 6:
                rankStr = "6";
                break;
            case 7:
                rankStr = "7";
                break;
            case 8:
                rankStr = "8";
                break;
            case 9:
                rankStr = "9";
                break;
            case 10:
                rankStr = "10";
                break;
            case 11:
                rankStr = "J"; // Jack
                break;
            case 12:
                rankStr = "Q"; // Queen
                break;
            case 13:
                rankStr = "K"; // King
                break;
            default:
                rankStr = "ERROR";
                break;
        }
        
		return String.format("[%s, %s]", suitStr, rankStr);
	}
	
    public int getSuit(){
        return suit;
    }
    public int getRank(){
        return rank;
    }

}
