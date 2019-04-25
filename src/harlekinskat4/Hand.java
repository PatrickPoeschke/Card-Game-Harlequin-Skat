package harlekinskat4;

/**
 * Based on previous the class for hands of cards of previous card games/versions.
 * (class for creating hands consisting of 8 cards from a skat deck)
 */

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
    
    // returns the nth card of a given hand
    public Skatcard getNthCardOfHand (int n)
    {
        switch (n) {
            case 1: return card1;
            case 2: return card2;
            case 3: return card3;
            case 4: return card4;
            case 5: return card5;
            case 6: return card6;
            case 7: return card7;
            case 8: return card8;
            default: Harlekinskat4.infoDialog("<html><body>Error: out of bounds in getNthCardOfHand(...); attempted N: " + n + "</body></html>"); return null;
        }
    }
    
    // sets the nth card of a given hand to a given card
    public void setNthCardOfHand (int n, Skatcard Card)
    {
        switch (n) {
            case 1: card1=Card; break;
            case 2: card2=Card; break;
            case 3: card3=Card; break;
            case 4: card4=Card; break;
            case 5: card5=Card; break;
            case 6: card6=Card; break;
            case 7: card7=Card; break;
            case 8: card8=Card; break;
            default: Harlekinskat4.infoDialog("<html><body>Error: out of bounds in setNthCardOfHand(...); attempted N: " + n + "</body></html>"); break;
        }
    }
    
    
}
