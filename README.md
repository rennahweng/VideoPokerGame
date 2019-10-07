# Video Poker Game

Welcome to Video Poker World!

All codes are written in Java.
To start the game, compile all the files and run Game.java.

Before you start, make sure you read the followings game rules.

# Game Rules

-In PokerTester class, once Game g is created,
 a new Player p and a new Deck deck are also created.

-You are given 20 tokens in your bankroll to start the game.
-enter letter "y" from your keyboard to begin play.
-OR enter "n" to end the game completely.
-NO other strings are acceptable!!

-Once you begin, choose your bet.
-please choose a bet between 1 to 5, including 1 and 5.
-if you choose a bet that's not 1, 5, or in between, you will be asked to put a bet again.
-if you choose a bet that's larger than your available tokens,
 you will be warned and asked to enter again.

-You've successfully pick your bet.
-The same amount of tokens is taken from your bankroll.
-if your hand is currently empty, the top five cards from a shuffled deck are
 dealt to your hand using deal5Cards() method.
-if command line is used for creating a testHand, no card will be dealt.
-Your hand with 5 cards are presented to you.
-NOTE: The shuffled deck is also presented, but it's for the programmer's use.
       you can comment out the print line System.out.println(deck);

-After the hand is presented to you,
 you are asked whether to exchange or not.
-y for yes, n for no
 if you enter in neither y or n, you will be asked to enter again.
-You should try to exchange useless cards for new ones
 for better combinations and thus better payouts.
-However, you can only exchange once.

-If you do not wish to exchange, enter n.
-Then your hand will be evaluated and scored.
-Your score is presented to you.
-You will either gain token or gain/lose nothing.
-Your bankroll will update based on the tokens you gain.
-This round ended.
-GO TO LINE 87.

-If you do wish to exchange, enter y.
-Choose how many cards you wish to exchange from your hand.
-Please enter only a number from 1 through 5 because
 there're there are at least 1 card and at most 5 cards.
-You will be asked to enter again if your number is invalid.

-Once you choose the number of card,
 enter in the position of the cards that you wish to exchange.
-Enter in the position(s) one by one.
-Card positions are 1,2,3,4,5, corresponding to the 5 cards in hand.
-For example, if you want to exchange the first 2 cards,
 enter twice in the "Which card?" prompt: 1 and then 2.
-You are not allowed enter in the same position twice!
 So do not enter 1 and 1 again if you wish to exchange 2 cards.
 
-After you exchange your cards,
 a new hand of 5 cards is presented to you.
-Note: If testHand is used, during the exchange, the new dealt
 cards might contain the same exact card that's in testHand
 already because when we put cards in command line, we are not
 actually drawing from the deck, so there might be coincidence
 when the same exact cards exist in one hand. This cannot happen
 because no deck has two cards of the same rank and suit.
-Therefore, during each exchange, the newDealtCard() method will
 check if any already-dealt card in your hand is same as
 the newCard that's just dealt.
-If there is a repetition, then deal another newCard
 and check again.
-If there's no repetition, add the newCard to player's hand.
-Do the above until all desired cards are exchanged.
-After the exchange, you will have a hand.

-Based on your new hand, your hand will be evaluated and scored.
-Again, your score is presented to you.
-You will either gain token or gain/lose nothing.
-Your bankroll will update based on the tokens you gain.
-This round is end.


-After the previous round,
 your up-to-date available amount of tokens is shown.
-The game will ask you if you want to play another round.
-Go back to line 9, the cycle repeats.


-Note: logics behind the methods for scoring hand
        are all written as comments under those methods.
        Please take a look. Thanks.

# Thank you for playing!!!

