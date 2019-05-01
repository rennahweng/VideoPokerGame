/*****************************************
 * Xin Ying Weng
 * xw2600
 * 04/20/19
 * 
 * PokerTest.java - a test class for video pocker game.
 ****************************************/ 

public class PokerTest {

    //this class must remain unchanged
    //your code must work with this test class
 
    public static void main(String[] args){
        if (args.length<1){
            Game g = new Game();
            g.play();
        }
        else{
            Game g = new Game(args);
            g.play();
        }
    }
}
// -------------TEST CASES--------------------
// no pair { s1 c2 d5 h9 s11 } 0
// one pair { s1 c1 d5 h9 s11 } 1 
// two pairs { s1 c1 d5 h5 s11 } 2
// 3 of a kind { s1 c1 d1 h9 s11 } 3
// straight { s1 c2 d3 h4 s5 } 4
// straight { s1 c10 d11 h12 s13 } 4
// flush { s1 s2 s5 s9 s11 } 5
// full house { s1 c1 d1 h10 s10 } 6
// 4 of a kind { s2 c3 d3 h3 s3 } 25
// straight flush { h1 h2 h3 h4 h5 } 50
// royal flush { s1 s10 s11 s12 s13 } 250
