package harlekinskat4;

/**
 * This class models the decision making of the CPU on normal difficulty.
 * 
 */

import static harlekinskat4.Harlekinskat4.couldntServeClubsBefore;
import static harlekinskat4.Harlekinskat4.couldntServeDiamondsBefore;
import static harlekinskat4.Harlekinskat4.couldntServeHeartsBefore;
import static harlekinskat4.Harlekinskat4.couldntServeSpadesBefore;
import static harlekinskat4.Harlekinskat4.couldntServeTrumpBefore;
import static harlekinskat4.Harlekinskat4.counter1;
import static harlekinskat4.Harlekinskat4.counter2;
import static harlekinskat4.Harlekinskat4.counter3;
import static harlekinskat4.Harlekinskat4.counter4;
import static harlekinskat4.Harlekinskat4.cpuReacts;
import static harlekinskat4.Harlekinskat4.currentColor;
import static harlekinskat4.Harlekinskat4.numberOfClubCardsSinceThen;
import static harlekinskat4.Harlekinskat4.numberOfDiamondCardsSinceThen;
import static harlekinskat4.Harlekinskat4.numberOfHeartCardsSinceThen;
import static harlekinskat4.Harlekinskat4.numberOfSpadeCardsSinceThen;
import static harlekinskat4.Harlekinskat4.numberOfTrumpCardsSinceThen;
import static harlekinskat4.Harlekinskat4.servable;
import static harlekinskat4.Harlekinskat4.trumpPlayed;
import static harlekinskat4.Harlekinskat4.trumpColor;
import static harlekinskat4.AIEasy.finalChoice;
import static harlekinskat4.Harlekinskat4.isShowingAIStrategies;
import static harlekinskat4.Harlekinskat4.infoDialog;

import static harlekinskat4.Harlekinskat4.stringServeJack;
import static harlekinskat4.Harlekinskat4.stringServeTrumpHigh;
import static harlekinskat4.Harlekinskat4.stringServeHigh;
import static harlekinskat4.Harlekinskat4.stringServeTrumpMid;
import static harlekinskat4.Harlekinskat4.stringServeMid;
import static harlekinskat4.Harlekinskat4.stringServeTrumpLow;
import static harlekinskat4.Harlekinskat4.stringServeLow;
import static harlekinskat4.Harlekinskat4.stringDiscardLow;
import static harlekinskat4.Harlekinskat4.stringDiscardColor;
import static harlekinskat4.Harlekinskat4.stringTrumpHigh;
import static harlekinskat4.Harlekinskat4.stringWasteLeast;
import static harlekinskat4.Harlekinskat4.stringTryBestMove;
import static harlekinskat4.Harlekinskat4.stringTryBestMoveWithCounting;
import static harlekinskat4.Harlekinskat4.stringTryBestMoveTrump;
import static harlekinskat4.Harlekinskat4.stringBestMoveTrumpWithCounting;
import static harlekinskat4.Harlekinskat4.stringDefServeAce;
import static harlekinskat4.Harlekinskat4.stringDefServeTen;
import static harlekinskat4.Harlekinskat4.stringLikelyAce;
import static harlekinskat4.Harlekinskat4.stringLikelyTen;
import static harlekinskat4.Harlekinskat4.stringLikelyKing;
import static harlekinskat4.Harlekinskat4.stringLikelyQueen;
import static harlekinskat4.Harlekinskat4.stringLureJack;
import static harlekinskat4.Harlekinskat4.stringTryDefUnserveLow;
import static harlekinskat4.Harlekinskat4.stringLikelyUnserveLow;
import static harlekinskat4.Harlekinskat4.stringTryLow;


public class AINormal {
    public static int counter;
    
    // my experience with this difficulty level:
    // The CPU responds almost as well as on the highest difficulty level.
    // So in that respect it does not make much difference.
    // This opponent is way better in reacting than in acting.
    // When starting to play a card,
    // the CPU on this level usually has not enough information to make a really good move.
    // So here typically the CPU does the same what I usually do:
    // playing a low non-trump card (7, 8 or 9).
    // Also the CPU seems to choose the trump color relatively wisely
    // such that it has at least 2, but usually at least 3 trump card on the starting hand.
    // This shows that even with perfect memory, the rule-of-thumb-heuristics are not better than human players.
    // This is somehow a bit disappointing.
    // However, this also actually simulates a typical human player very well!
    // This AI can de facto not be distinguished from a human player.
    // So the actual difference to a human player is
    // that on one hand the CPU remembers perfectly,
    // however on the other hand does not go through all possible moves in the last few turns.
    // This seems to somehow balance out very well.
    
    // starting point: here the decision making of the CPU on AI level normal branches off
    public static int aiLevelNormal (Skatcard playedCardPlayer, Hand playableCPU, Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    {
        if (cpuReacts){
            finalChoice=cpuReacts(playedCardPlayer, playableCPU, backhandPlayer, playable, Deck);
        }
        else {
            finalChoice=cpuActs(playableCPU, backhandPlayer, playable, Deck);
        }
        return finalChoice;
    }
    
    // returns true, if the CPU has a playable card on the hand that is a trump card
    public static boolean cpuCanServeTrump (Hand playableCPU)
    { // reuse also on hard AI level
        
        // just for debugging purposes (show Id of each card of CPU)
        //if (isShowingAIStrategies) {infoDialog(playableCPU.card1.cardId+","+playableCPU.card2.cardId+","+playableCPU.card3.cardId+","+playableCPU.card4.cardId+","+playableCPU.card5.cardId+","+playableCPU.card6.cardId+","+playableCPU.card7.cardId+","+playableCPU.card8.cardId);}
        
        for (int index = 1; index <= 8; index++){
            Skatcard Card = playableCPU.getNthCardOfHand(index);
            if (Card.isTrump()) {return true;}
        }
        return false;
    }
    
    // returns true, if the CPU has a playable card on the hand that has the currently played non-trump color/suit
    public static boolean cpuCanServeColor (Hand playableCPU)
    { // reuse also on hard AI level
        
        // just for debugging purposes (show Id of each card of CPU)
        //if (isShowingAIStrategies) {infoDialog(playableCPU.card1.cardId+","+playableCPU.card2.cardId+","+playableCPU.card3.cardId+","+playableCPU.card4.cardId+","+playableCPU.card5.cardId+","+playableCPU.card6.cardId+","+playableCPU.card7.cardId+","+playableCPU.card8.cardId);}
        
        for (int index = 1; index <= 8; index++){
            Skatcard Card = playableCPU.getNthCardOfHand(index);
            if ((Card.color==currentColor && !Card.isJack())) {return true;}
        }
        return false;
    }
    
    public static int cpuReacts (Skatcard playedCardPlayer, Hand playableCPU, Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    {
        trumpPlayed = playedCardPlayer.isTrump();
        if (trumpPlayed) {
            currentColor=trumpColor;
            servable=cpuCanServeTrump(playableCPU);
            if (servable) {
                finalChoice = trumpServingStrategies(playedCardPlayer, Deck);
            }
            else {
                finalChoice = trumpNonServingStrategies(playableCPU, Deck);
            }
        }
        else {
            currentColor=playedCardPlayer.color;
            servable=cpuCanServeColor(playableCPU);
            if (servable) {
                finalChoice = colorServingStrategies(playedCardPlayer, Deck);
            }
            else {
                finalChoice = colorNonServingStrategies(playedCardPlayer, playableCPU, Deck);
            }
        }
        return finalChoice;
    }
    
    public static int trumpServingStrategies (Skatcard playedCardPlayer, SkatcardDeck Deck)
    { // reuse also on hard AI level
        finalChoice=0;
        if (playedCardPlayer.isJack()) {
            serveJack(Deck);
        }
        else {
            finalChoice = colorServingStrategies(playedCardPlayer, Deck); // don't worry, the methods used in there are written such that they also serve trump
        }
        return finalChoice;
    }
    
    public static void serveJack (SkatcardDeck Deck)
    {
        if (isShowingAIStrategies) {infoDialog(stringServeJack);}
        switch (trumpColor) {
            case 1:
                tryPlayClubLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                tryPlayJackStartingWithHighest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.club10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubA); if (finalChoice!=0) {break;}
                break;
            case 2:
                tryPlaySpadeLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                tryPlayJackStartingWithHighest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spade10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeA); if (finalChoice!=0) {break;}
                break;
            case 3:
                tryPlayHeartLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                tryPlayJackStartingWithHighest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heart10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartA); if (finalChoice!=0) {break;}
                break;
            case 4:
                tryPlayDiamLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                tryPlayJackStartingWithHighest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diam10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamA); if (finalChoice!=0) {break;}
                break;
        }
    }
    
    public static void serveAceOrTen (SkatcardDeck Deck)
    {
        if (isShowingAIStrategies) {
            if (currentColor==trumpColor) {
                infoDialog(stringServeTrumpHigh);
            }
            else {
                infoDialog(stringServeHigh);
            }
        }
        switch (currentColor) {
            case 1:
                finalChoice = tryPlayCard(Deck.clubA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                tryPlayClubLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.club10); if (finalChoice!=0) {break;}
                break;
            case 2:
                finalChoice = tryPlayCard(Deck.spadeA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                tryPlaySpadeLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spade10); if (finalChoice!=0) {break;}
                break;
            case 3:
                finalChoice = tryPlayCard(Deck.heartA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                tryPlayHeartLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heart10); if (finalChoice!=0) {break;}
                break;
            case 4:
                finalChoice = tryPlayCard(Deck.diamA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                tryPlayDiamLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diam10); if (finalChoice!=0) {break;}
                break;
                // other cases don't exist! (so here it will definitely be over here)
        }
    }
    
    public static void serveKingOrQueen (SkatcardDeck Deck)
    {
        if (isShowingAIStrategies) {
            if (currentColor==trumpColor) {
                infoDialog(stringServeTrumpMid);
            }
            else {
                infoDialog(stringServeMid);
            }
        }
        switch (currentColor) {
            case 1:
                finalChoice = tryPlayCard(Deck.club10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubK); if (finalChoice!=0) {break;}
                tryPlayClubLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubQ); if (finalChoice!=0) {break;}
                break;
            case 2:
                finalChoice = tryPlayCard(Deck.spade10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeK); if (finalChoice!=0) {break;}
                tryPlaySpadeLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeQ); if (finalChoice!=0) {break;}
                break;
            case 3:
                finalChoice = tryPlayCard(Deck.heart10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartK); if (finalChoice!=0) {break;}
                tryPlayHeartLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartQ); if (finalChoice!=0) {break;}
                break;
            case 4:
                finalChoice = tryPlayCard(Deck.diam10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamK); if (finalChoice!=0) {break;}
                tryPlayDiamLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamQ); if (finalChoice!=0) {break;}
                break;
        }
    }
    
    public static void serveLowCard (SkatcardDeck Deck)
    {
        if (isShowingAIStrategies) {
            if (currentColor==trumpColor) {
                infoDialog(stringServeTrumpLow);
            }
            else {
                infoDialog(stringServeLow);
            }
        }
        switch (currentColor) {
            case 1:
                tryPlayClubLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.club10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.clubA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                break;
            case 2:
                tryPlaySpadeLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spade10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.spadeA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                break;
            case 3:
                tryPlayHeartLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heart10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.heartA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                break;
            case 4:
                tryPlayDiamLowCardStartingWithLowest(Deck); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamQ); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamK); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diam10); if (finalChoice!=0) {break;}
                finalChoice = tryPlayCard(Deck.diamA); if (finalChoice!=0) {break;}
                if (currentColor==trumpColor) {tryPlayJackStartingWithLowest(Deck);} if (finalChoice!=0) {break;}
                break;
        }
    }
    
    public static int trumpNonServingStrategies (Hand playableCPU, SkatcardDeck Deck)
    {
        finalChoice=0;
        tryDiscardColor(playableCPU);
        if (finalChoice==0) {
            if (isShowingAIStrategies) {infoDialog(stringDiscardLow);}
            discardLeastPointsPossible(Deck);
        }
        return finalChoice;
    }
    
    public static void tryDiscardColor (Hand playableCPU)
    { // almost same as version on hard, except that one leaves out here looking at the backhand
        if (isShowingAIStrategies) {infoDialog(stringDiscardColor);}
        counter1=0; counter2=0; counter3=0; counter4=0;
        for (int index = 1; index <= 8; index++){ // count how many cards of which non-trump color
            Skatcard Card = playableCPU.getNthCardOfHand(index);
            if (Card.isNotTrump()) {
                switch (Card.color) {
                    case 1: counter1++; break;
                    case 2: counter2++; break;
                    case 3: counter3++; break;
                    case 4: counter4++; break;
                }
            }
        }
        if (counter1==1 && trumpColor!=1) {finalChoice = lookUpSingleColorCard(playableCPU, 1);}
        if (counter2==1 && trumpColor!=2 && finalChoice==0) {finalChoice = lookUpSingleColorCard(playableCPU, 2);}
        if (counter3==1 && trumpColor!=3 && finalChoice==0) {finalChoice = lookUpSingleColorCard(playableCPU, 3);}
        if (counter4==1 && trumpColor!=4 && finalChoice==0) {finalChoice = lookUpSingleColorCard(playableCPU, 4);}
    }
    
    // returns the position of a single non-trump low card (7, 8, 9) of a given color on the hand of the CPU (only call, if you know that there is only one card of that color on the hand)
    public static int lookUpSingleColorCard (Hand playableCPU, int color) {
        for (int index = 1; index <= 8; index++){
            Skatcard Card = playableCPU.getNthCardOfHand(index);
            if (Card.color==color && !Card.isJack() && (Card.isLowCard() || Card.isKingOrQueen())) {return index;}
        }
        return 0;
    }
    
    public static void discardLeastPointsPossible (SkatcardDeck Deck)
    { // reuse also on hard AI level
        finalChoice=tryPlayDiamNonTrumpCard(Deck.diam7);
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heart7);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spade7);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.club7);}
        if (finalChoice==0) {finalChoice=tryPlayDiamNonTrumpCard(Deck.diam8);}
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heart8);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spade8);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.club8);}
        if (finalChoice==0) {finalChoice=tryPlayDiamNonTrumpCard(Deck.diam9);}
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heart9);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spade9);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.club9);}
        if (finalChoice==0) {finalChoice=tryPlayDiamNonTrumpCard(Deck.diamQ);}
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heartQ);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spadeQ);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.clubQ);}
        if (finalChoice==0) {finalChoice=tryPlayDiamNonTrumpCard(Deck.diamK);}
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heartK);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spadeK);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.clubK);}
        if (finalChoice==0) {finalChoice=tryPlayDiamNonTrumpCard(Deck.diam10);}
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heart10);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spade10);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.club10);}
        if (finalChoice==0) {finalChoice=tryPlayDiamNonTrumpCard(Deck.diamA);}
        if (finalChoice==0) {finalChoice=tryPlayHeartNonTrumpCard(Deck.heartA);}
        if (finalChoice==0) {finalChoice=tryPlaySpadeNonTrumpCard(Deck.spadeA);}
        if (finalChoice==0) {finalChoice=tryPlayClubNonTrumpCard(Deck.clubA);}
    }
    
    public static int colorServingStrategies (Skatcard playedCardPlayer, SkatcardDeck Deck)
    { // reuse also on hard AI level
        finalChoice=0;
        if (playedCardPlayer.isAceOrTen()) {
            serveAceOrTen(Deck);
        }
        else if (playedCardPlayer.isKingOrQueen()) {
            serveKingOrQueen(Deck);
        }
        else if (playedCardPlayer.isLowCard()) {
            serveLowCard(Deck);
        }
        return finalChoice;
    }
    
    public static int colorNonServingStrategies (Skatcard playedCardPlayer, Hand playableCPU, SkatcardDeck Deck)
    {
        finalChoice=0;
        if (playedCardPlayer.hasPointsWorthIt()) {
            tryGetValuableCard(Deck);
        }
        else {
            tryDiscardColor(playableCPU);
            if (finalChoice==0) {wasteLeastPossible(Deck);}
        }
        return finalChoice;
    }
    
    public static void tryGetValuableCard (SkatcardDeck Deck)
    { // reuse also on hard AI level
        if (isShowingAIStrategies) {infoDialog(stringTrumpHigh);}
        tryPlayTrumpAce(Deck);
        tryPlayTrump10(Deck);
        tryPlayTrumpKingOrQueen(Deck);
        tryPlayLowTrumpStartingWithLowest(Deck);
        tryPlayJackStartingWithLowest(Deck);
        tryDiscardLowCard(Deck);
        tryDiscardQueenOrKing(Deck);
        unfortunatelyDiscardHighCard(Deck);
    }
    
    public static void wasteLeastPossible (SkatcardDeck Deck)
    { // reuse also on hard AI level
        if (isShowingAIStrategies) {infoDialog(stringWasteLeast);}
        tryDiscardLowCard(Deck);
        tryPlayTrumpKingOrQueen(Deck);
        tryPlayTrump10(Deck);
        tryPlayTrumpAce(Deck);
        tryDiscardQueenOrKing(Deck);
        tryPlayLowTrumpStartingWithLowest(Deck);
        tryPlayJackStartingWithLowest(Deck);
        unfortunatelyDiscardHighCard(Deck);
    }
    
    public static void tryPlayTrumpAce (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diamA);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heartA);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spadeA);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.clubA);}
    }
    
    public static void tryPlayTrump10 (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diam10);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heart10);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spade10);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.club10);}
    }
    
    public static void tryPlayTrumpKingOrQueen (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diamK);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heartK);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spadeK);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.clubK);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diamQ);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heartQ);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spadeQ);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.clubQ);}
    }
    
    public static void tryPlayLowTrumpStartingWithLowest (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diam7);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heart7);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spade7);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.club7);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diam8);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heart8);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spade8);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.club8);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.diam9);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.heart9);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.spade9);}
        if (finalChoice==0) {finalChoice=tryPlayTrumpCard(Deck.club9);}
    }
    
    public static void tryDiscardLowCard (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diam7);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heart7);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spade7);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.club7);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diam8);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heart8);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spade8);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.club8);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diam9);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heart9);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spade9);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.club9);}
    }
    
    public static void tryDiscardQueenOrKing (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diamQ);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heartQ);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spadeQ);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.clubQ);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diamK);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heartK);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spadeK);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.clubK);}
    }
    
    public static void unfortunatelyDiscardHighCard (SkatcardDeck Deck)
    {
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diam10);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heart10);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spade10);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.club10);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.diamA);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.heartA);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.spadeA);}
        if (finalChoice==0) {finalChoice=tryPlayNonTrumpCard(Deck.clubA);}
    }
    
    // in order to repeat oneself less, out-source here typical priority lists of trying to play certain cards
    public static int tryPlayCard (Skatcard Card) {
        if (Card.positionInGame==1){return Card.positionOnHand;} else {return 0;}
    }
    public static void tryPlayDiamLowCardStartingWithLowest (SkatcardDeck Deck) {
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.diam7);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.diam8);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.diam9);}
    }
    public static void tryPlayHeartLowCardStartingWithLowest (SkatcardDeck Deck) {
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.heart7);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.heart8);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.heart9);}
    }
    public static void tryPlaySpadeLowCardStartingWithLowest (SkatcardDeck Deck) {
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.spade7);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.spade8);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.spade9);}
    }
    public static void tryPlayClubLowCardStartingWithLowest (SkatcardDeck Deck) {
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.club7);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.club8);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.club9);}
    }
    public static void tryPlayJackStartingWithLowest (SkatcardDeck Deck) {
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.diamJ);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.heartJ);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.spadeJ);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.clubJ);}
    }
    public static void tryPlayJackStartingWithHighest (SkatcardDeck Deck) {
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.clubJ);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.spadeJ);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.heartJ);}
        if (finalChoice==0) {finalChoice = tryPlayCard(Deck.diamJ);}
    }
    
    // The following methods try to play a specific non-trump card. (see further below for the trump card versions)
    // If it worked, they return the position of the card on the hand of the CPU (to be set as final choice outside of method). Otherwise return zero.
    public static int tryPlayDiamNonTrumpCard (Skatcard Card) {
        if (Card.positionInGame==1 && trumpColor!=4){return Card.positionOnHand;} else {return 0;}
    }
    public static int tryPlayHeartNonTrumpCard (Skatcard Card) {
        if (Card.positionInGame==1 && trumpColor!=3){return Card.positionOnHand;} else {return 0;}
    }
    public static int tryPlaySpadeNonTrumpCard (Skatcard Card) {
        if (Card.positionInGame==1 && trumpColor!=2){return Card.positionOnHand;} else {return 0;}
    }
    public static int tryPlayClubNonTrumpCard (Skatcard Card) {
        if (Card.positionInGame==1 && trumpColor!=1){return Card.positionOnHand;} else {return 0;}
    }
    
    public static int tryPlayNonTrumpCard (Skatcard Card) {
        if (Card.positionInGame==1 && Card.isNotTrump()){return Card.positionOnHand;} else {return 0;}
    }
    public static int tryPlayTrumpCard (Skatcard Card) {
        if (Card.positionInGame==1 && Card.isTrump()){return Card.positionOnHand;} else {return 0;}
    }
    
    // -- from here on: CPU acting (not just reacting) --
    
    public static int cpuActs (Hand playableCPU, Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    {
        finalChoice = activeStrategies(playableCPU, backhandPlayer, playable, Deck);
        return finalChoice;
    }
    
    public static int activeStrategies (Hand playableCPU, Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    {
        finalChoice=0;
        tryBestMoveStrategies(backhandPlayer, Deck);
        if (finalChoice==0) {tryDefinitelyServableStrategies(backhandPlayer, playable, Deck);}
        if (finalChoice==0) {tryLikelyServableStrategies(Deck);}
        if (finalChoice==0) {tryLureOutJack(backhandPlayer, playable, Deck);}
        if (finalChoice==0) {tryLowCardStrategies(Deck);}
        if (finalChoice==0) {finalChoice=AIEasy.playRandomCard(playableCPU, Deck.placeholder);}
        return finalChoice;
    }
    
    public static void tryBestMoveStrategies (Hand backhandPlayer, SkatcardDeck Deck)
    {
        tryBestMove(backhandPlayer, Deck);
        if (finalChoice==0) {tryBestMoveByCountingKnownCards(backhandPlayer, Deck);}
        if (finalChoice==0) {tryBestMoveWithTrump(backhandPlayer, Deck);}
        if (finalChoice==0) {tryBestMoveWithTrumpByCountingCards(backhandPlayer, Deck);}
    }
    
    public static void tryDefinitelyServableStrategies (Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    {
        defServableAce(backhandPlayer, playable, Deck);
        if (finalChoice==0) {defServableTen(backhandPlayer, playable, Deck);}
    }
    
    public static void tryLikelyServableStrategies (SkatcardDeck Deck)
    {
        tryLikelyServableAce(Deck);
        if (finalChoice==0) {tryLikelyServableTen(Deck);}
        if (finalChoice==0) {tryLikelyServableKing(Deck);}
        if (finalChoice==0) {tryLikelyServableQueen(Deck);}
    }
    
    public static void tryLowCardStrategies (SkatcardDeck Deck)
    {
        tryDefUnservableLowCard(Deck);
        if (finalChoice==0) {tryLikelyUnservableLowCard(Deck);}
        if (finalChoice==0) {tryPlayLowCard(Deck);}
    }
    
    // The best move in the game is to play an ace knowing the opponent has to play the ten of the same color.
    // This results in 21 points and thus a third of a victory!
    // It happens rarely. But when it happens, it probably decides the game.
    // 
    // In this method the computer tries to pull off the move by using its memory.
    // That is the sole purpose for the memory by the way.
    public static void tryBestMove (Hand backhandPlayer, SkatcardDeck Deck)
    {
        for (int index = 1; index <= 4; index++){
            if (trumpColor!=index && playerHasSingleCardOfColor(index)) {
                if (isShowingAIStrategies) {infoDialog(stringTryBestMove);}
                if (cpuHasAceOfColor(Deck, index) && playerHasTenOfColor(backhandPlayer, Deck, index)) {
                    playAceOfColor(Deck, index); break;
                }
            }
        }
    }
    
    public static void playAceOfColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: finalChoice=Deck.clubA.positionOnHand; break;
            case 2: finalChoice=Deck.spadeA.positionOnHand; break;
            case 3: finalChoice=Deck.heartA.positionOnHand; break;
            case 4: finalChoice=Deck.diamA.positionOnHand; break;
        }
    }
    
    public static boolean playerHasSingleCardOfColor (int color)
    {
        switch (color) {
            case 1: return (couldntServeClubsBefore && numberOfClubCardsSinceThen==1);
            case 2: return (couldntServeSpadesBefore && numberOfSpadeCardsSinceThen==1);
            case 3: return (couldntServeHeartsBefore && numberOfHeartCardsSinceThen==1);
            case 4: return (couldntServeDiamondsBefore && numberOfDiamondCardsSinceThen==1);
            default: return false;
        }
    }
    
    // returns true, if the player has turned around a 10 of a given color
    public static boolean playerHasTenOfColor (Hand backhandPlayer, SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return Deck.club10.positionInGame==1 && backhandPlayer.getNthCardOfHand(Deck.club10.positionOnHand)==Deck.placeholder;
            case 2: return Deck.spade10.positionInGame==1 && backhandPlayer.getNthCardOfHand(Deck.spade10.positionOnHand)==Deck.placeholder;
            case 3: return Deck.heart10.positionInGame==1 && backhandPlayer.getNthCardOfHand(Deck.heart10.positionOnHand)==Deck.placeholder;
            case 4: return Deck.diam10.positionInGame==1 && backhandPlayer.getNthCardOfHand(Deck.diam10.positionOnHand)==Deck.placeholder;
            default: return false;
        }
    }
    
    // returns true, if the computer has an ace of a given color
    public static boolean cpuHasAceOfColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.clubA.positionInGame==1);
            case 2: return (Deck.spadeA.positionInGame==1);
            case 3: return (Deck.heartA.positionInGame==1);
            case 4: return (Deck.diamA.positionInGame==1);
            default: return false;
        }
    }
    
    // In this strategy the CPU tries to pull off the best move by counting the cards that are already out.
    public static void tryBestMoveByCountingKnownCards (Hand backhandPlayer, SkatcardDeck Deck)
    {
        for (int index = 1; index <= 4; index++){
            if (trumpColor!=index && cpuHasAceOfColor(Deck, index) && playerHasTenOfColor(backhandPlayer, Deck, index)) {
                if (playerHasNoLowColor(Deck, index) && playerHasNoMidColor(Deck, index)){
                    if (isShowingAIStrategies) {infoDialog(stringTryBestMoveWithCounting);}
                    playAceOfColor(Deck, index); break;
                }
            }
        }
    }
    
    public static void tryBestMoveWithTrump (Hand backhandPlayer, SkatcardDeck Deck)
    {
        if (couldntServeTrumpBefore==true && numberOfTrumpCardsSinceThen==1){
            if (isShowingAIStrategies) {infoDialog(stringTryBestMoveTrump);}
            if (cpuHasAceOfColor(Deck, trumpColor) && playerHasTenOfColor(backhandPlayer, Deck, trumpColor)) {
                playAceOfColor(Deck, trumpColor);
            }
        }
    }
    
    public static void tryBestMoveWithTrumpByCountingCards (Hand backhandPlayer, SkatcardDeck Deck)
    {
        boolean playerHasNoJack = (Deck.clubJ.positionInGame%2==1 && Deck.spadeJ.positionInGame%2==1 && Deck.heartJ.positionInGame%2==1 && Deck.diamJ.positionInGame%2==1);
        if (playerHasNoJack) {
            if (cpuHasAceOfColor(Deck, trumpColor) && playerHasTenOfColor(backhandPlayer, Deck, trumpColor)) {
                boolean playerHasNoOtherTrump = playerHasNoMidColor(Deck, trumpColor) && playerHasNoLowColor(Deck, trumpColor);
                if (playerHasNoOtherTrump){
                    if (isShowingAIStrategies) {infoDialog(stringBestMoveTrumpWithCounting);}
                    playAceOfColor(Deck, trumpColor);
                }
            }
        }
    }
    
    // returns true, if the player has not the 10 of a given color on the back hand
    public static boolean playerHasTenOfColorNotOnBackhand (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.club10.positionInGame!=3);
            case 2: return (Deck.spade10.positionInGame!=3);
            case 3: return (Deck.heart10.positionInGame!=3);
            case 4: return (Deck.diam10.positionInGame!=3);
            default: return false;
        }
    }
    
    public static void defServableAce (Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    { // This strategy is good enough. It happened often enough.
        for (int index = 1; index <= 4; index++){
            if (trumpColor!=index && cpuHasAceOfColor(Deck, index) && playerHasTenOfColorNotOnBackhand(Deck, index)) {
                counter=countTurnedAroundCardsOfColor(backhandPlayer, playable, Deck.placeholder, index);
                if (counter>0) {
                    if (isShowingAIStrategies) {infoDialog(stringDefServeAce);}
                    playAceOfColor(Deck, index); break;
                }
            }
        }
    }
    
    // counts the cards on the hand of the player that the player already turned around and that are of a given color
    public static int countTurnedAroundCardsOfColor (Hand backhandPlayer, Hand playable, Skatcard placeholder, int color)
    {
        counter=0;
        for (int index = 1; index <= 8; index++){
            Skatcard Card = playable.getNthCardOfHand(index);
            if (backhandPlayer.getNthCardOfHand(index)==placeholder && Card.color==color && !Card.isJack()) {
                counter++;
            }
        }
        return counter;
    }
    
    // returns true, if computer has the 10 and player not the ace of a given color
    public static boolean cpuHasTenAndPlayerNotAceOfColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.club10.positionInGame==1 && Deck.clubA.positionInGame%2==1);
            case 2: return (Deck.spade10.positionInGame==1 && Deck.spadeA.positionInGame%2==1);
            case 3: return (Deck.heart10.positionInGame==1 && Deck.heartA.positionInGame%2==1);
            case 4: return (Deck.diam10.positionInGame==1 && Deck.diamA.positionInGame%2==1);
            default: return false;
        }
    }
    
    public static void playTenOfColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: finalChoice=Deck.club10.positionOnHand; break;
            case 2: finalChoice=Deck.spade10.positionOnHand; break;
            case 3: finalChoice=Deck.heart10.positionOnHand; break;
            case 4: finalChoice=Deck.diam10.positionOnHand; break;
        }
    }
    
    public static void defServableTen (Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    { // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
        for (int index = 1; index <= 4; index++){
            if (trumpColor!=index && cpuHasTenAndPlayerNotAceOfColor(Deck, index)) {
                counter=countTurnedAroundCardsOfColor(backhandPlayer, playable, Deck.placeholder, index);
                if (counter>0) {
                    if (isShowingAIStrategies) {infoDialog(stringDefServeTen);}
                    playTenOfColor(Deck, index); break;
                }
            }
        }
    }
    
    public static void tryLikelyServableAce (SkatcardDeck Deck)
    { // This strategy is good enough. It happened often enough.
        int counter1=0;
        int counter2=0;
        int counter3=0;
        int counter4=0;
        if (trumpColor!=1 && Deck.clubA.positionInGame==1 && Deck.club10.positionInGame!=3) {
            counter1=6; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
            if (Deck.club10.positionInGame%2==1){counter1--;}
            if (Deck.clubK.positionInGame%2==1){counter1--;}
            if (Deck.clubQ.positionInGame%2==1){counter1--;}
            if (Deck.club9.positionInGame%2==1){counter1--;}
            if (Deck.club8.positionInGame%2==1){counter1--;}
            if (Deck.club7.positionInGame%2==1){counter1--;}
        }
        if (trumpColor!=2 && Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame!=3) {
            counter2=6;
            if (Deck.spade10.positionInGame%2==1){counter2--;}
            if (Deck.spadeK.positionInGame%2==1){counter2--;}
            if (Deck.spadeQ.positionInGame%2==1){counter2--;}
            if (Deck.spade9.positionInGame%2==1){counter2--;}
            if (Deck.spade8.positionInGame%2==1){counter2--;}
            if (Deck.spade7.positionInGame%2==1){counter2--;}
        }
        if (trumpColor!=3 && Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame!=3) {
            counter3=6;
            if (Deck.heart10.positionInGame%2==1){counter3--;}
            if (Deck.heartK.positionInGame%2==1){counter3--;}
            if (Deck.heartQ.positionInGame%2==1){counter3--;}
            if (Deck.heart9.positionInGame%2==1){counter3--;}
            if (Deck.heart8.positionInGame%2==1){counter3--;}
            if (Deck.heart7.positionInGame%2==1){counter3--;}
        }
        if (trumpColor!=4 && Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame!=3) {
            counter4=6;
            if (Deck.diam10.positionInGame%2==1){counter4--;}
            if (Deck.diamK.positionInGame%2==1){counter4--;}
            if (Deck.diamQ.positionInGame%2==1){counter4--;}
            if (Deck.diam9.positionInGame%2==1){counter4--;}
            if (Deck.diam8.positionInGame%2==1){counter4--;}
            if (Deck.diam7.positionInGame%2==1){counter4--;}
        }
        counter = Math.max(Math.max(counter1, counter2), Math.max(counter3, counter4));
        if (counter>2) {
            if (isShowingAIStrategies) {infoDialog(stringLikelyAce);}
            if (counter1==counter){finalChoice=Deck.clubA.positionOnHand;}
            if (finalChoice==0 && counter2==counter){finalChoice=Deck.spadeA.positionOnHand;}
            if (finalChoice==0 && counter3==counter){finalChoice=Deck.heartA.positionOnHand;}
            if (finalChoice==0 && counter4==counter){finalChoice=Deck.diamA.positionOnHand;}
        }
    }
    
    public static void tryLikelyServableTen (SkatcardDeck Deck)
    {// it is so unlikely that the player does not have the ace for sure, go through all cases separately
        if (trumpColor!=1 && Deck.club10.positionInGame==1 && Deck.clubA.positionInGame%2==1) {
            counter=5; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
            if (Deck.clubK.positionInGame%2==1){counter--;}
            if (Deck.clubQ.positionInGame%2==1){counter--;}
            if (Deck.club9.positionInGame%2==1){counter--;}
            if (Deck.club8.positionInGame%2==1){counter--;}
            if (Deck.club7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyTen);}
                finalChoice=Deck.club10.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=2 && Deck.spade10.positionInGame==1 && Deck.spadeA.positionInGame%2==1) {
            counter=5;
            if (Deck.spadeK.positionInGame%2==1){counter--;}
            if (Deck.spadeQ.positionInGame%2==1){counter--;}
            if (Deck.spade9.positionInGame%2==1){counter--;}
            if (Deck.spade8.positionInGame%2==1){counter--;}
            if (Deck.spade7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyTen);}
                finalChoice=Deck.spade10.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=3 && Deck.heart10.positionInGame==1 && Deck.heartA.positionInGame%2==1) {
            counter=5;
            if (Deck.heartK.positionInGame%2==1){counter--;}
            if (Deck.heartQ.positionInGame%2==1){counter--;}
            if (Deck.heart9.positionInGame%2==1){counter--;}
            if (Deck.heart8.positionInGame%2==1){counter--;}
            if (Deck.heart7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyTen);}
                finalChoice=Deck.heart10.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=4 && Deck.diam10.positionInGame==1 && Deck.diamA.positionInGame%2==1) {
            counter=5;
            if (Deck.diamK.positionInGame%2==1){counter--;}
            if (Deck.diamQ.positionInGame%2==1){counter--;}
            if (Deck.diam9.positionInGame%2==1){counter--;}
            if (Deck.diam8.positionInGame%2==1){counter--;}
            if (Deck.diam7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyTen);}
                finalChoice=Deck.diam10.positionOnHand;
            }
        }
    }
    
    public static void tryLikelyServableKing (SkatcardDeck Deck)
    {
        if (trumpColor!=1 && Deck.clubK.positionInGame==1 && Deck.club10.positionInGame%2==1 && Deck.clubA.positionInGame%2==1) {
            counter=4; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
            if (Deck.clubQ.positionInGame%2==1){counter--;}
            if (Deck.club9.positionInGame%2==1){counter--;}
            if (Deck.club8.positionInGame%2==1){counter--;}
            if (Deck.club7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyKing);}
                finalChoice=Deck.clubK.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=2 && Deck.spadeK.positionInGame==1 && Deck.spade10.positionInGame%2==1 && Deck.spadeA.positionInGame%2==1) {
            counter=4;
            if (Deck.spadeQ.positionInGame%2==1){counter--;}
            if (Deck.spade9.positionInGame%2==1){counter--;}
            if (Deck.spade8.positionInGame%2==1){counter--;}
            if (Deck.spade7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyKing);}
                finalChoice=Deck.spadeK.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=3 && Deck.heartK.positionInGame==1 && Deck.heart10.positionInGame%2==1 && Deck.heartA.positionInGame%2==1) {
            counter=4;
            if (Deck.heartQ.positionInGame%2==1){counter--;}
            if (Deck.heart9.positionInGame%2==1){counter--;}
            if (Deck.heart8.positionInGame%2==1){counter--;}
            if (Deck.heart7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyKing);}
                finalChoice=Deck.heartK.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=4 && Deck.diamK.positionInGame==1 && Deck.diam10.positionInGame%2==1 && Deck.diamA.positionInGame%2==1) {
            counter=4;
            if (Deck.diamQ.positionInGame%2==1){counter--;}
            if (Deck.diam9.positionInGame%2==1){counter--;}
            if (Deck.diam8.positionInGame%2==1){counter--;}
            if (Deck.diam7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyKing);}
                finalChoice=Deck.diamK.positionOnHand;
            }
        }
    }
    
    public static void tryLikelyServableQueen (SkatcardDeck Deck)
    {
        if (trumpColor!=1 && Deck.clubQ.positionInGame==1 && Deck.clubK.positionInGame%2==1 && Deck.club10.positionInGame%2==1 && Deck.clubA.positionInGame%2==1) {
            counter=3; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
            if (Deck.club9.positionInGame%2==1){counter--;}
            if (Deck.club8.positionInGame%2==1){counter--;}
            if (Deck.club7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyQueen);}
                finalChoice=Deck.clubQ.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=2 && Deck.spadeQ.positionInGame==1 && Deck.spadeK.positionInGame%2==1 && Deck.spade10.positionInGame%2==1 && Deck.spadeA.positionInGame%2==1) {
            counter=3;
            if (Deck.spade9.positionInGame%2==1){counter--;}
            if (Deck.spade8.positionInGame%2==1){counter--;}
            if (Deck.spade7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyQueen);}
                finalChoice=Deck.spadeQ.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=3 && Deck.heartQ.positionInGame==1 && Deck.heartK.positionInGame%2==1 && Deck.heart10.positionInGame%2==1 && Deck.heartA.positionInGame%2==1) {
            counter=3;
            if (Deck.heart9.positionInGame%2==1){counter--;}
            if (Deck.heart8.positionInGame%2==1){counter--;}
            if (Deck.heart7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyQueen);}
                finalChoice=Deck.heartQ.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=4 && Deck.diamQ.positionInGame==1 && Deck.diamK.positionInGame%2==1 && Deck.diam10.positionInGame%2==1 && Deck.diamA.positionInGame%2==1) {
            counter=3;
            if (Deck.diam9.positionInGame%2==1){counter--;}
            if (Deck.diam8.positionInGame%2==1){counter--;}
            if (Deck.diam7.positionInGame%2==1){counter--;}
            if (counter>2){
                if (isShowingAIStrategies) {infoDialog(stringLikelyQueen);}
                finalChoice=Deck.diamQ.positionOnHand;
            }
        }
    }
    
    // This is a good strategy. It happens not often, but is complementing the usual aggressive techniques quite well.
    public static void tryLureOutJack (Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    {
        if (cpuHasLowCard(Deck, trumpColor) && (cpuHasMidTrump(Deck) || cpuHasHighTrump(Deck) || cpuHasJack(Deck))) {
            if (playerHasNoHighColor(Deck, trumpColor) && playerHasNoMidColor(Deck, trumpColor) && playerHasJack(backhandPlayer, playable, Deck.placeholder)) {
                if (isShowingAIStrategies) {infoDialog(stringLureJack);}
                switch (trumpColor) {
                    case 1: tryPlayClubLowCardStartingWithLowest(Deck); break;
                    case 2: tryPlaySpadeLowCardStartingWithLowest(Deck); break;
                    case 3: tryPlayHeartLowCardStartingWithLowest(Deck); break;
                    case 4: tryPlayDiamLowCardStartingWithLowest(Deck); break;
                }
            }
        }
    }
    
    // returns true, if the computer has a low card of a given color
    public static boolean cpuHasLowCard (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.club7.positionInGame==1 || Deck.club8.positionInGame==1 || Deck.club9.positionInGame==1);
            case 2: return (Deck.spade7.positionInGame==1 || Deck.spade8.positionInGame==1 || Deck.spade9.positionInGame==1);
            case 3: return (Deck.heart7.positionInGame==1 || Deck.heart8.positionInGame==1 || Deck.heart9.positionInGame==1);
            case 4: return (Deck.diam7.positionInGame==1 || Deck.diam8.positionInGame==1 || Deck.diam9.positionInGame==1);
            default: return false;
        }
    }
    
    // returns true, if the computer has a trump king or queen
    public static boolean cpuHasMidTrump (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1: return (Deck.clubQ.positionInGame==1 || Deck.clubK.positionInGame==1);
            case 2: return (Deck.spadeQ.positionInGame==1 || Deck.spadeK.positionInGame==1);
            case 3: return (Deck.heartQ.positionInGame==1 || Deck.heartK.positionInGame==1);
            case 4: return (Deck.diamQ.positionInGame==1 || Deck.diamK.positionInGame==1);
            default: return false;
        }
    }
    
    // returns true, if the computer has a trump ace or 10
    public static boolean cpuHasHighTrump (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1: return (Deck.club10.positionInGame==1 || Deck.clubA.positionInGame==1);
            case 2: return (Deck.spade10.positionInGame==1 || Deck.spadeA.positionInGame==1);
            case 3: return (Deck.heart10.positionInGame==1 || Deck.heartA.positionInGame==1);
            case 4: return (Deck.diam10.positionInGame==1 || Deck.diamA.positionInGame==1);
            default: return false;
        }
    }
    
    // returns true, if the computer has a playable jack
    public static boolean cpuHasJack (SkatcardDeck Deck){
        return (Deck.clubJ.positionInGame==1 || Deck.spadeJ.positionInGame==1 || Deck.heartJ.positionInGame==1 || Deck.diamJ.positionInGame==1);
    }
        
    // returns true, if the player has a playable jack
    public static boolean playerHasJack (Hand backhandPlayer, Hand playable, Skatcard placeholder)
    {
        for (int index = 1; index <= 8; index++){
            Skatcard Card = playable.getNthCardOfHand(index);
            if (backhandPlayer.getNthCardOfHand(index)==placeholder && Card.isJack()) {
                return true;
            }
        }
        return false;
    }
    
    // returns true, if the player has neither 9 nor 8 nor 7 of a given color
    public static boolean playerHasNoLowColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.club7.positionInGame%2==1 && Deck.club8.positionInGame%2==1 && Deck.club9.positionInGame==1);
            case 2: return (Deck.spade7.positionInGame%2==1 && Deck.spade8.positionInGame%2==1 && Deck.spade9.positionInGame==1);
            case 3: return (Deck.heart7.positionInGame%2==1 && Deck.heart8.positionInGame%2==1 && Deck.heart9.positionInGame==1);
            case 4: return (Deck.diam7.positionInGame%2==1 && Deck.diam8.positionInGame%2==1 && Deck.diam9.positionInGame==1);
            default: return false;
        }
    }
    
    // returns true, if the player has neither ace nor 10 nor king nor queen of a given color
    public static boolean playerHasNoMidColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.clubQ.positionInGame%2==1 && Deck.clubK.positionInGame%2==1);
            case 2: return (Deck.spadeQ.positionInGame%2==1 && Deck.spadeK.positionInGame%2==1);
            case 3: return (Deck.heartQ.positionInGame%2==1 && Deck.heartK.positionInGame%2==1);
            case 4: return (Deck.diamQ.positionInGame%2==1 && Deck.diamK.positionInGame%2==1);
            default: return false;
        }
    }
    
    // returns true, if the player has neither 9 nor 8 nor 7 of a given color
    public static boolean playerHasNoHighColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.club10.positionInGame%2==1 && Deck.clubA.positionInGame%2==1);
            case 2: return (Deck.spade10.positionInGame%2==1 && Deck.spadeA.positionInGame%2==1);
            case 3: return (Deck.heart10.positionInGame%2==1 && Deck.heartA.positionInGame%2==1);
            case 4: return (Deck.diam10.positionInGame%2==1 && Deck.diamA.positionInGame%2==1);
            default: return false;
        }
    }
    
    public static void tryDefUnservableLowCard (SkatcardDeck Deck)
    {
        if (isShowingAIStrategies) {infoDialog(stringTryDefUnserveLow);}
        for (int index = 1; index <= 4; index++){
            if (trumpColor!=index && cpuHasLowCard(Deck, index)) {
                if (playerHasNoLowColor(Deck, index) && playerHasNoMidColor(Deck, index) && playerHasNoHighColor(Deck, index)) {
                        switch (index) {
                            case 1: tryPlayClubLowCardStartingWithLowest(Deck); break;
                            case 2: tryPlaySpadeLowCardStartingWithLowest(Deck); break;
                            case 3: tryPlayHeartLowCardStartingWithLowest(Deck); break;
                            case 4: tryPlayDiamLowCardStartingWithLowest(Deck); break;
                        }
                }
            }
        }
    }
    
    // Play a low card, if the player can only have two or less of that color.
    // This seems to be one of the most often used strategies.
    public static void tryLikelyUnservableLowCard (SkatcardDeck Deck)
    {
        if (trumpColor!=1) {
            if (cpuHasLowCard(Deck, 1)) {
                counter=7;
                if (Deck.clubA.positionInGame%2==1){counter--;}
                if (Deck.club10.positionInGame%2==1){counter--;}
                if (Deck.clubK.positionInGame%2==1){counter--;}
                if (Deck.clubQ.positionInGame%2==1){counter--;}
                if (Deck.club9.positionInGame%2==1){counter--;}
                if (Deck.club8.positionInGame%2==1){counter--;}
                if (Deck.club7.positionInGame%2==1){counter--;}
                if (counter<3) {
                    if (isShowingAIStrategies) {infoDialog(stringLikelyUnserveLow);}
                    tryPlayClubLowCardStartingWithLowest(Deck);
                }
            }
        }
        if (finalChoice==0 && trumpColor!=2) {
            if (cpuHasLowCard(Deck, 2)) {
                counter=7;
                if (Deck.spadeA.positionInGame%2==1){counter--;}
                if (Deck.spade10.positionInGame%2==1){counter--;}
                if (Deck.spadeK.positionInGame%2==1){counter--;}
                if (Deck.spadeQ.positionInGame%2==1){counter--;}
                if (Deck.spade9.positionInGame%2==1){counter--;}
                if (Deck.spade8.positionInGame%2==1){counter--;}
                if (Deck.spade7.positionInGame%2==1){counter--;}
                if (counter<3) {
                    if (isShowingAIStrategies) {infoDialog(stringLikelyUnserveLow);}
                    tryPlaySpadeLowCardStartingWithLowest(Deck);
                }
            }
        }
        if (finalChoice==0 && trumpColor!=3) {
            if (cpuHasLowCard(Deck, 3)) {
                counter=7;
                if (Deck.heartA.positionInGame%2==1){counter--;}
                if (Deck.heart10.positionInGame%2==1){counter--;}
                if (Deck.heartK.positionInGame%2==1){counter--;}
                if (Deck.heartQ.positionInGame%2==1){counter--;}
                if (Deck.heart9.positionInGame%2==1){counter--;}
                if (Deck.heart8.positionInGame%2==1){counter--;}
                if (Deck.heart7.positionInGame%2==1){counter--;}
                if (counter<3) {
                    if (isShowingAIStrategies) {infoDialog(stringLikelyUnserveLow);}
                    tryPlayHeartLowCardStartingWithLowest(Deck);
                }
            }
        }
        if (finalChoice==0 && trumpColor!=4) {
            if (cpuHasLowCard(Deck, 4)) {
                counter=7;
                if (Deck.diamA.positionInGame%2==1){counter--;}
                if (Deck.diam10.positionInGame%2==1){counter--;}
                if (Deck.diamK.positionInGame%2==1){counter--;}
                if (Deck.diamQ.positionInGame%2==1){counter--;}
                if (Deck.diam9.positionInGame%2==1){counter--;}
                if (Deck.diam8.positionInGame%2==1){counter--;}
                if (Deck.diam7.positionInGame%2==1){counter--;}
                if (counter<3) {
                    if (isShowingAIStrategies) {infoDialog(stringLikelyUnserveLow);}
                    tryPlayDiamLowCardStartingWithLowest(Deck);
                }
            }
        }
    }
    
    // This is pretty much the standard strategy. It happens most often. (reused on AI level hard)
    public static void tryPlayLowCard (SkatcardDeck Deck)
    { // when in doubt, play a low non-trump card (7, 8, 9), maybe even try to play a queen or a king before using random choices
        if (isShowingAIStrategies) {infoDialog(stringTryLow);}
        tryDiscardLowCard(Deck);
        tryDiscardQueenOrKing(Deck);
    }
    
    // computer chooses trump by maximizing the number of trump cards on won starting hand
    // (returns the trump color as int), used on normal difficulty
    public static int cpuChoosesTrump (Hand playableCPU)
    {
        // count how many playable cards the CPU has
        int numberOfClubs=0;
        int numberOfSpades=0;
        int numberOfHearts=0;
        int numberOfDiamonds=0;
        int color;
        for (int index = 1; index <= 8; index++){
            Skatcard Card = playableCPU.getNthCardOfHand(index);
            if (!Card.isJack()) { // only count the number of non-Jacks
                color = Card.color;
                switch (color) {
                    case 1: numberOfClubs++; break;
                    case 2: numberOfSpades++; break;
                    case 3: numberOfHearts++; break;
                    case 4: numberOfDiamonds++; break;
                }
            }
        }
        // choose maximum (if 2 or less of each kind, choose one randomly), results in at least 2 trump on the starting hand
        int maximum = Math.max(Math.max(numberOfClubs, numberOfSpades), Math.max(numberOfHearts, numberOfDiamonds));
        if (maximum<=2) {
            return AIEasy.chooseRandomOption(4);
        }
        else {
            if (numberOfClubs==maximum) {
                return 1;
            }
            else if (numberOfSpades==maximum) {
                return 2;
            }
            else if (numberOfHearts==maximum) {
                return 3;
            }
            else {
                return 4;
            }
        }
    }
    
    
}
