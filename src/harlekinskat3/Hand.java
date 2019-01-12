/*
 * Exact copy of the Hand class from the previous Harlekinskat Java project
 * just it is now in the harlekinskat2 package
 * (class for creating hands consisting of 8 cards from a skat deck)
 */
package harlekinskat3;

public class Hand {
    
    // instance variables (different for every card)
    public Skatcard card1; // leftmost card as object
    public Skatcard card2; // 2nd card as object
    public Skatcard card3; // 3rd card as object
    public Skatcard card4; // 4th card as object
    public Skatcard card5; // 5th card as object
    public Skatcard card6; // 6th card as object
    public Skatcard card7; // 7th card as object
    public Skatcard card8; // rightmost card as object
        
    // constructor
    public Hand (Skatcard card1, Skatcard card2, Skatcard card3, Skatcard card4, Skatcard card5, Skatcard card6, Skatcard card7, Skatcard card8)
    {
        this.card1=card1;
        this.card2=card2;
        this.card3=card3;
        this.card4=card4;
        this.card5=card5;
        this.card6=card6;
        this.card7=card7;
        this.card8=card8;
    }
    
    
}
