package harlekinskat4;

/**
 * Based on previous the class for cards of previous Skat games/versions.
 * (class for creating cards from a 32 card skat deck with properties useful for Harlekin Skat)
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static harlekinskat4.Harlekinskat4.trumpColor;

import static harlekinskat4.Harlekinskat4.stringPlayedJ1;
import static harlekinskat4.Harlekinskat4.stringPlayedJ2;
import static harlekinskat4.Harlekinskat4.stringPlayedJ3;
import static harlekinskat4.Harlekinskat4.stringPlayedJ4;
import static harlekinskat4.Harlekinskat4.stringPlayedTA;
import static harlekinskat4.Harlekinskat4.stringPlayedT10;
import static harlekinskat4.Harlekinskat4.stringPlayedTK;
import static harlekinskat4.Harlekinskat4.stringPlayedTQ;
import static harlekinskat4.Harlekinskat4.stringPlayedT9;
import static harlekinskat4.Harlekinskat4.stringPlayedT8;
import static harlekinskat4.Harlekinskat4.stringPlayedT7;
import static harlekinskat4.Harlekinskat4.stringPlayedCA;
import static harlekinskat4.Harlekinskat4.stringPlayedC10;
import static harlekinskat4.Harlekinskat4.stringPlayedCK;
import static harlekinskat4.Harlekinskat4.stringPlayedCQ;
import static harlekinskat4.Harlekinskat4.stringPlayedC9;
import static harlekinskat4.Harlekinskat4.stringPlayedC8;
import static harlekinskat4.Harlekinskat4.stringPlayedC7;
import static harlekinskat4.Harlekinskat4.stringPlayedSA;
import static harlekinskat4.Harlekinskat4.stringPlayedS10;
import static harlekinskat4.Harlekinskat4.stringPlayedSK;
import static harlekinskat4.Harlekinskat4.stringPlayedSQ;
import static harlekinskat4.Harlekinskat4.stringPlayedS9;
import static harlekinskat4.Harlekinskat4.stringPlayedS8;
import static harlekinskat4.Harlekinskat4.stringPlayedS7;
import static harlekinskat4.Harlekinskat4.stringPlayedHA;
import static harlekinskat4.Harlekinskat4.stringPlayedH10;
import static harlekinskat4.Harlekinskat4.stringPlayedHK;
import static harlekinskat4.Harlekinskat4.stringPlayedHQ;
import static harlekinskat4.Harlekinskat4.stringPlayedH9;
import static harlekinskat4.Harlekinskat4.stringPlayedH8;
import static harlekinskat4.Harlekinskat4.stringPlayedH7;
import static harlekinskat4.Harlekinskat4.stringPlayedDA;
import static harlekinskat4.Harlekinskat4.stringPlayedD10;
import static harlekinskat4.Harlekinskat4.stringPlayedDK;
import static harlekinskat4.Harlekinskat4.stringPlayedDQ;
import static harlekinskat4.Harlekinskat4.stringPlayedD9;
import static harlekinskat4.Harlekinskat4.stringPlayedD8;
import static harlekinskat4.Harlekinskat4.stringPlayedD7;

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

    // method for shuffling the whole deck
    public static Integer[] shuffleDeck(){
        // creates an array of the first 32 integers, shuffles it, and returns it
        int numberOfCards=32;
        Integer[] array32 = new Integer[numberOfCards];
        for (int index = 1; index <= numberOfCards; index++){
            array32[index-1]=index;
        }
        List<Integer> array32aslist = Arrays.asList(array32);
        Collections.shuffle(array32aslist);
        array32aslist.toArray(array32);
        return array32;
        
    }
    
    // returns the text for the dialog, that tells what card the player just played
    // (also telling the player, if it was a trump card or not)
    public static String playedCardText(Skatcard playedCardPlayer)
    {
        boolean isTrump = playedCardPlayer.isTrump();
        String text="";
        if (playedCardPlayer.cardId==1){text=stringPlayedJ1;}
        else if (playedCardPlayer.cardId==2){text=stringPlayedJ2;}
        else if (playedCardPlayer.cardId==3){text=stringPlayedJ3;}
        else if (playedCardPlayer.cardId==4){text=stringPlayedJ4;}
        else if (playedCardPlayer.cardId==5){
            if (isTrump){text=stringPlayedTA;}
            else {text=stringPlayedCA;}
        }
        else if (playedCardPlayer.cardId==6){
            if (isTrump){text=stringPlayedT10;}
            else {text=stringPlayedC10;}
        }
        else if (playedCardPlayer.cardId==7){
            if (isTrump){text=stringPlayedTK;}
            else {text=stringPlayedCK;}
        }
        else if (playedCardPlayer.cardId==8){
            if (isTrump){text=stringPlayedTQ;}
            else {text=stringPlayedCQ;}
        }
        else if (playedCardPlayer.cardId==9){
            if (isTrump){text=stringPlayedT9;}
            else {text=stringPlayedC9;}
        }
        else if (playedCardPlayer.cardId==10){
            if (isTrump){text=stringPlayedT8;}
            else {text=stringPlayedC8;}
        }
        else if (playedCardPlayer.cardId==11){
            if (isTrump){text=stringPlayedT7;}
            else {text=stringPlayedC7;}
        }
        else if (playedCardPlayer.cardId==12){
            if (isTrump){text=stringPlayedTA;}
            else {text=stringPlayedSA;}
        }
        else if (playedCardPlayer.cardId==13){
            if (isTrump){text=stringPlayedT10;}
            else {text=stringPlayedS10;}
        }
        else if (playedCardPlayer.cardId==14){
            if (isTrump){text=stringPlayedTK;}
            else {text=stringPlayedSK;}
        }
        else if (playedCardPlayer.cardId==15){
            if (isTrump){text=stringPlayedTQ;}
            else {text=stringPlayedSQ;}
        }
        else if (playedCardPlayer.cardId==16){
            if (isTrump){text=stringPlayedT9;}
            else {text=stringPlayedS9;}
        }
        else if (playedCardPlayer.cardId==17){
            if (isTrump){text=stringPlayedT8;}
            else {text=stringPlayedS8;}
        }
        else if (playedCardPlayer.cardId==18){
            if (isTrump){text=stringPlayedT7;}
            else {text=stringPlayedS7;}
        }
        else if (playedCardPlayer.cardId==19){
            if (isTrump){text=stringPlayedTA;}
            else {text=stringPlayedHA;}
        }
        else if (playedCardPlayer.cardId==20){
            if (isTrump){text=stringPlayedT10;}
            else {text=stringPlayedH10;}
        }
        else if (playedCardPlayer.cardId==21){
            if (isTrump){text=stringPlayedTK;}
            else {text=stringPlayedHK;}
        }
        else if (playedCardPlayer.cardId==22){
            if (isTrump){text=stringPlayedTQ;}
            else {text=stringPlayedHQ;}
        }
        else if (playedCardPlayer.cardId==23){
            if (isTrump){text=stringPlayedT9;}
            else {text=stringPlayedH9;}
        }
        else if (playedCardPlayer.cardId==24){
            if (isTrump){text=stringPlayedT8;}
            else {text=stringPlayedH8;}
        }
        else if (playedCardPlayer.cardId==25){
            if (isTrump){text=stringPlayedT7;}
            else {text=stringPlayedH7;}
        }
        else if (playedCardPlayer.cardId==26){
            if (isTrump){text=stringPlayedTA;}
            else {text=stringPlayedDA;}
        }
        else if (playedCardPlayer.cardId==27){
            if (isTrump){text=stringPlayedT10;}
            else {text=stringPlayedD10;}
        }
        else if (playedCardPlayer.cardId==28){
            if (isTrump){text=stringPlayedTK;}
            else {text=stringPlayedDK;}
        }
        else if (playedCardPlayer.cardId==29){
            if (isTrump){text=stringPlayedTQ;}
            else {text=stringPlayedDQ;}
        }
        else if (playedCardPlayer.cardId==30){
            if (isTrump){text=stringPlayedT9;}
            else {text=stringPlayedD9;}
        }
        else if (playedCardPlayer.cardId==31){
            if (isTrump){text=stringPlayedT8;}
            else {text=stringPlayedD8;}
        }
        else if (playedCardPlayer.cardId==32){
            if (isTrump){text=stringPlayedT7;}
            else {text=stringPlayedD7;}
        }
        return text;
    }
    
    // returns true, if a card is a trump card at the moment
    public boolean isTrump(){
        return (color==trumpColor || isJack()); // has either trump color or is a jack
    }
    
    // returns true, if a card is not a trump card at the moment
    public boolean isNotTrump(){
        return (color!=trumpColor && !isJack()); // has a color different from trump and is no jack
    }
    
    // returns true, if a card is a jack
    public boolean isJack(){
        return (value==1);
    }
    
    // returns true, if a card is an ace or a ten
    public boolean isAceOrTen(){
        return (value==2 || value==3);
    }
    
    // returns true, if a card is a ten
    public boolean isTen(){
        return (value==3);
    }
    
    // returns true, if a card is a king or a queen
    public boolean isKingOrQueen(){
        return (value==4 || value==5);
    }
    
    // returns true, if a card is a 7, an 8, or a 9
    public boolean isLowCard(){
        return (value==6 || value==7 || value==8);
    }
    
    // returns true, if a given card has enough points to be worth using a trump card on it
    public boolean hasPointsWorthIt(){
        return (points>4); // one may change this threshold here
    }
    
    
}
