/*
 * Exact copy of the Skatcard class from the previous Harlekinskat Java project
 * just it is now in the harlekinskat2 package
 * (class for creating cards from a 32 card skat deck with properties useful for Harlekin Skat)
 */
package harlekinskat3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Skatcard {
    
    // instance variables (different for every card)
    public int cardId; // ID of the card for identifying everything about it
    //public String cardName; // unneeded property => don't use
    public String displayName;
    // name of the card (in a more readable presentable form for enumeration at the end of the game with points in parenthesis)
    public String imagePath; // path to the image files for dispaying each card
    
    public int value; // value: 1=jack, 2=ace, 3=10, 4=king, 5=queen, 6=9, 7=8, 8=7
    public int color; // suit: 1=clubs, 2=spades, 3=hearts, 4=diamonds
    
    public int points; // points for adding up at the end of game: ace=11, 10=10, king=4, queen=3, jack=2, rest zero
    
    public int positionOnHand; // from left to right one of 8 cards (interger from 1 to 8 doesn't change over one game)
    public int positionInGame; // a number for each card that tells where inside the game the card is: (changes 2 to 3 times in each game)
    // 1 playable for CPU
    // 2 on the back of CPU's hand
    // 3 on the back of player's hand
    // 4 playable (for the player)
    // 5 won by the CPU
    // 6 played by the CPU
    // 7 won by player
    // 8 played by player
    // even numbers are visible for the player (odd numbers are represented by face down cards)
    
    // constructor
    public Skatcard (int cardId, String displayName, String imagePath, int value, int color, int points, int positionOnHand, int positionInGame)
    {
        this.cardId=cardId;
        //this.cardName=cardName;
        this.displayName=displayName;
        this.imagePath=imagePath;
        this.value=value;
        this.color=color;
        this.points=points;
        this.positionOnHand=positionOnHand;
        this.positionInGame=positionInGame;
    }
    
        // list of card IDs (might be useful)
        // 1 clubJ "jack of clubs"
        // 2 spadeJ "jack of spades"
        // 3 heartJ "jack of hearts"
        // 4 diamJ "jack of diamonds"
        // 5 clubA "ace of clubs"
        // 6 club10 "10 of clubs"
        // 7 clubK "king of clubs"
        // 8 clubQ "queen of clubs"
        // 9 club9 "9 of clubs"
        // 10 club8 "8 of clubs"
        // 11 club7 "7 of clubs"
        // 12 spadeA "ace of spades"
        // 13 spade10 "10 of spades"
        // 14 spadeK "king of spades"
        // 15 spadeQ "queen of spades"
        // 16 spade9 "9 of spades"
        // 17 spade8 "8 of spades"
        // 18 spade7 "7 of spades"
        // 19 heartA "ace of hearts"
        // 20 heart10 "10 of hearts"
        // 21 heartK "king of hearts"
        // 22 heartQ "queen of hearts"
        // 23 heart9 "9 of hearts"
        // 24 heart8 "8 of hearts"
        // 25 heart7 "7 of hearts"
        // 26 diamA "ace of diamonds"
        // 27 diam10 "10 of diamonds"
        // 28 diamK "king of diamonds"
        // 29 diamQ "queen of diamonds"
        // 30 diam9 "9 of diamonds"
        // 31 diam8 "8 of diamonds"
        // 32 diam7 "7 of diamonds"
        // also in main class a placeholder card with Id=33 has been added

    
    // method for shuffling the whole deck
    public static Integer[] shuffleDeck(){
        // creates an array of the first 32 integers
        // shuffles it
        // and returns it
        int numberOfCards=32;
        Integer[] array32 = new Integer[numberOfCards];
        for (int index = 1; index <= numberOfCards; index++){
            array32[index-1]=index;
        }
        List<Integer> array32aslist = Arrays.asList(array32);
        Collections.shuffle(array32aslist);
        array32aslist.toArray(array32);
        return array32;
        
            // shuffle a 32 card deck (shuffle the first 32 intergers)
            // for such long array better create array with for loop (see below)
            //Integer[] array32 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
            //List<Integer> array32aslist = Arrays.asList(array32);
            //Collections.shuffle(array32aslist);
            //array32aslist.toArray(array32); // array32 = array32aslist.toArray(array32); // this would tell the program the name of the resulting array twice
        
    }
    
    
}

