package harlekinskat4;

/**
 * This class models the decision making of the CPU on hard difficulty.
 * It does not create anything.
 * 
 */

import static harlekinskat4.Harlekinskat4.cpuReacts;
import static harlekinskat4.Harlekinskat4.currentColor;
import static harlekinskat4.Harlekinskat4.trumpPlayed;
import static harlekinskat4.Harlekinskat4.servable;
import static harlekinskat4.Harlekinskat4.trumpColor;
import static harlekinskat4.Harlekinskat4.counter1;
import static harlekinskat4.Harlekinskat4.counter2;
import static harlekinskat4.Harlekinskat4.counter3;
import static harlekinskat4.Harlekinskat4.counter4;
import static harlekinskat4.AIEasy.finalChoice;
import static harlekinskat4.Harlekinskat4.isShowingAIStrategies;
import static harlekinskat4.Harlekinskat4.infoDialog;

import static harlekinskat4.Harlekinskat4.stringDiscardLow;
import static harlekinskat4.Harlekinskat4.stringDiscardColor;
import static harlekinskat4.Harlekinskat4.stringBestMove;
import static harlekinskat4.Harlekinskat4.stringBestMoveTrump;
import static harlekinskat4.Harlekinskat4.stringTrumpHighWithJack;
import static harlekinskat4.Harlekinskat4.stringServeAce;
import static harlekinskat4.Harlekinskat4.stringServeTen;
import static harlekinskat4.Harlekinskat4.stringServeTrumpAce;
import static harlekinskat4.Harlekinskat4.stringServeKing;
import static harlekinskat4.Harlekinskat4.stringServeQueen;
import static harlekinskat4.Harlekinskat4.stringLureJack;
import static harlekinskat4.Harlekinskat4.stringUnserveNoTrump;
        
public class AIHard {
    
    public static boolean playerHasNoJacks;
    public static boolean playerHasMidOrLowClub; // king or lower
    public static boolean playerHasMidOrLowSpade;
    public static boolean playerHasMidOrLowHeart;
    public static boolean playerHasMidOrLowDiamond;
    
    public static boolean playerHasHighClub; // ace or 10
    public static boolean playerHasHighSpade;
    public static boolean playerHasHighHeart;
    public static boolean playerHasHighDiamond;
    
    // my experience with this difficulty level:
    // One can actually sometimes win against this opponent, if one is lucky and has actually more trump cards
    // (even if the CPU is choosing trump).
    // Better versions of the good strategies that the normal difficulty CPU and real players want to use
    // actually happen a lot more frequently, when cheating. This makes this AI-level a challenging opponent.
    // Here it often seems to make a huge difference, if the CPU choses trump or not.
    // If the player is really unlucky, the CPU might simply have all trump cards.
    // When that happens, the CPU is pretty much invincible.
    // On average, one loses more often against this opponent than one wins.
    
    // All in all the 3 CPU levels are pretty much alright. On lowest, one usually wins.
    // On normal, it is depending on luck, when one is a good player.
    // On the highest difficulty level the CPU wins significantly more often
    // even when playing very well.
    
    public static int aiLevelHard (Skatcard playedCardPlayer, Hand playableCPU, Hand backhandCPU, SkatcardDeck Deck)
    {
        if (cpuReacts){
            finalChoice=cpuReacts(playedCardPlayer, playableCPU, backhandCPU, Deck);
        }
        else {
            finalChoice=cpuActs(playableCPU, Deck);
        }
        return finalChoice;
    }
    
    public static int cpuReacts (Skatcard playedCardPlayer, Hand playableCPU, Hand backhandCPU, SkatcardDeck Deck)
    {
        trumpPlayed = playedCardPlayer.isTrump();
        if (trumpPlayed) {
            currentColor=trumpColor;
            servable=AINormal.cpuCanServeTrump(playableCPU); // since the same, reuse from AI level normal
            if (servable) {
                finalChoice = AINormal.trumpServingStrategies(playedCardPlayer, Deck); // since the same, reuse from AI level normal
            }
            else {
                finalChoice = trumpNonServingStrategies(playableCPU, backhandCPU, Deck);
            }
        }
        else {
            currentColor=playedCardPlayer.color;
            servable=AINormal.cpuCanServeColor(playableCPU); // since the same, reuse from AI level normal
            if (servable) {
                finalChoice = AINormal.colorServingStrategies(playedCardPlayer, Deck); // since the same, reuse from AI level normal
            }
            else {
                finalChoice = colorNonServingStrategies(playedCardPlayer, playableCPU, backhandCPU, Deck);
            }
        }
        return finalChoice;
    }
    
    public static int trumpNonServingStrategies (Hand playableCPU, Hand backhandCPU, SkatcardDeck Deck)
    {
        finalChoice=0;
        tryDiscardColor(playableCPU, backhandCPU);
        if (finalChoice==0) {
            if (isShowingAIStrategies) {infoDialog(stringDiscardLow);}
            AINormal.discardLeastPointsPossible(Deck); // since the same, reuse from AI level normal
        }
        return finalChoice;
    }
    
    public static int colorNonServingStrategies (Skatcard playedCardPlayer, Hand playableCPU, Hand backhandCPU, SkatcardDeck Deck)
    {
        finalChoice=0;
        if (playedCardPlayer.hasPointsWorthIt()) {
            AINormal.tryGetValuableCard(Deck); // since the same, reuse from AI level normal
        }
        else {
            tryDiscardColor(playableCPU, backhandCPU);
            if (finalChoice==0) {AINormal.wasteLeastPossible(Deck);} // since the same, reuse from AI level normal
        }
        return finalChoice;
    }
    
    // The idea here is that thanks to cheating, the CPU can be sure to discard a color and not immediately turn around a card of the same color.
    // That means, if this strategy works, one has really discarded a color for at least one turn. An honest player could only be sure, if there was no card behind it.
    public static void tryDiscardColor (Hand playableCPU, Hand backhandCPU)
    { // almost same as version on normal, except that here is also looking at the backhand
        if (isShowingAIStrategies) {infoDialog(stringDiscardColor);}
        counter1=0; counter2=0; counter3=0; counter4=0;
        for (int index = 1; index <= 8; index++){ // count how many cards of which non-trump color
            Skatcard Card = playableCPU.getNthCardOfHand(index);
            Skatcard CardBehind = backhandCPU.getNthCardOfHand(index);
            if (Card.isNotTrump() && CardBehind.color!=Card.color && (CardBehind.isNotTrump() || CardBehind.isJack()) ) {
                switch (Card.color) {
                    case 1: counter1++; break;
                    case 2: counter2++; break;
                    case 3: counter3++; break;
                    case 4: counter4++; break;
                }
            }
        }
        if (counter1==1 && trumpColor!=1) {finalChoice = AINormal.lookUpSingleColorCard(playableCPU, 1);}
        if (counter2==1 && trumpColor!=2 && finalChoice==0) {finalChoice = AINormal.lookUpSingleColorCard(playableCPU, 2);}
        if (counter3==1 && trumpColor!=3 && finalChoice==0) {finalChoice = AINormal.lookUpSingleColorCard(playableCPU, 3);}
        if (counter4==1 && trumpColor!=4 && finalChoice==0) {finalChoice = AINormal.lookUpSingleColorCard(playableCPU, 4);}
    }
    
    // -- from here on: CPU acting (not just reacting) --
    
    public static int cpuActs (Hand playableCPU, SkatcardDeck Deck)
    {
        // analyse cards of player, since used in many strategies look it up just once
        playerHasNoJacks = playerHasNoJacks(Deck);
        playerHasMidOrLowClub = playerHasMidOrLowClub(Deck); // king or lower
        playerHasMidOrLowSpade = playerHasMidOrLowSpade(Deck);
        playerHasMidOrLowHeart = playerHasMidOrLowHeart(Deck);
        playerHasMidOrLowDiamond = playerHasMidOrLowDiamond(Deck);
        playerHasHighClub = playerHasHighClub(Deck); // ace or 10
        playerHasHighSpade = playerHasHighSpade(Deck);
        playerHasHighHeart = playerHasHighHeart(Deck);
        playerHasHighDiamond = playerHasHighDiamond(Deck);
        // use info obtained by cheating for strateies
        finalChoice=0;
        tryBestMoveStrategies(Deck);
        if (finalChoice==0) {tryGetHighTrumpCardWithJack(Deck);}
        if (finalChoice==0) {tryDefinitelyServableStrategies(Deck);}
        if (finalChoice==0) {tryLowCardStrategies(Deck);}
        if (finalChoice==0) {finalChoice=AIEasy.playRandomCard(playableCPU, Deck.placeholder);}
        return finalChoice;
    }
    
    public static boolean playerHasNoJacks (SkatcardDeck Deck) {
        return (Deck.clubJ.positionInGame!=4 && Deck.spadeJ.positionInGame!=4 && Deck.heartJ.positionInGame!=4 && Deck.diamJ.positionInGame!=4);
    }
    
    public static boolean playerHasMidOrLowClub (SkatcardDeck Deck) {
        return (Deck.club7.positionInGame==4 || Deck.club8.positionInGame==4 || Deck.club9.positionInGame==4 || Deck.clubQ.positionInGame==4 || Deck.clubK.positionInGame==4);
    }
    public static boolean playerHasMidOrLowSpade (SkatcardDeck Deck) {
        return (Deck.spade7.positionInGame==4 || Deck.spade8.positionInGame==4 || Deck.spade9.positionInGame==4 || Deck.spadeQ.positionInGame==4 || Deck.spadeK.positionInGame==4);
    }
    public static boolean playerHasMidOrLowHeart (SkatcardDeck Deck) {
        return (Deck.heart7.positionInGame==4 || Deck.heart8.positionInGame==4 || Deck.heart9.positionInGame==4 || Deck.heartQ.positionInGame==4 || Deck.heartK.positionInGame==4);
    }
    public static boolean playerHasMidOrLowDiamond (SkatcardDeck Deck) {
        return (Deck.diam7.positionInGame==4 || Deck.diam8.positionInGame==4 || Deck.diam9.positionInGame==4 || Deck.diamQ.positionInGame==4 || Deck.diamK.positionInGame==4);
    }
    
    public static boolean playerHasHighClub (SkatcardDeck Deck) {
        return (Deck.club10.positionInGame==4 || Deck.clubA.positionInGame==4);
    }
    public static boolean playerHasHighSpade (SkatcardDeck Deck) {
        return (Deck.spade10.positionInGame==4 || Deck.spadeA.positionInGame==4);
    }
    public static boolean playerHasHighHeart (SkatcardDeck Deck) {
        return (Deck.heart10.positionInGame==4 || Deck.heartA.positionInGame==4);
    }
    public static boolean playerHasHighDiamond (SkatcardDeck Deck) {
        return (Deck.diam10.positionInGame==4 || Deck.diamA.positionInGame==4);
    }
    
    public static void tryBestMoveStrategies (SkatcardDeck Deck)
    {
        tryBestMove(Deck);
        if (finalChoice==0) {tryBestMoveWithTrump(Deck);}
    }
    
    public static void tryDefinitelyServableStrategies (SkatcardDeck Deck)
    {
        tryServableAce(Deck);
        if (finalChoice==0) {tryServable10(Deck);}
        if (finalChoice==0) {tryServableTrumpAce(Deck);}
        if (finalChoice==0) {tryServableKing(Deck);}
        if (finalChoice==0) {tryServableQueen(Deck);}
    }
    
    public static void tryLowCardStrategies (SkatcardDeck Deck)
    {
        tryLureOutJack(Deck);
        if (finalChoice==0) {tryDefUnservableCardWhenPlayerHasNoTrump(Deck);}
        if (finalChoice==0) {AINormal.tryPlayLowCard(Deck);}
    }
    
    // here CPU tries to pull off the best move simply by cheating and looking up, if the player has to serve a ten, when the ace is played
    public static void tryBestMove (SkatcardDeck Deck)
    {
        if (trumpColor!=1 && Deck.clubA.positionInGame==1 && Deck.club10.positionInGame==4) {
            if (!playerHasMidOrLowClub) {
                if (isShowingAIStrategies) {infoDialog(stringBestMove);}
                finalChoice=Deck.clubA.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=2 && Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame==4) {
            if (!playerHasMidOrLowSpade) {
                if (isShowingAIStrategies) {infoDialog(stringBestMove);}
                finalChoice=Deck.spadeA.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=3 && Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame==4) {
            if (!playerHasMidOrLowHeart) {
                if (isShowingAIStrategies) {infoDialog(stringBestMove);}
                finalChoice=Deck.heartA.positionOnHand;
            }
        }
        if (finalChoice==0 && trumpColor!=4 && Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame==4) {
            if (!playerHasMidOrLowDiamond) {
                if (isShowingAIStrategies) {infoDialog(stringBestMove);}
                finalChoice=Deck.diamA.positionOnHand;
            }
        }
    }
    
    public static void tryBestMoveWithTrump (SkatcardDeck Deck)
    {
        if (cpuHasTrumpAceAndPlayerHasOnlyTrump10(Deck)) {
            if (isShowingAIStrategies) {infoDialog(stringBestMoveTrump);}
            AINormal.playAceOfColor(Deck, trumpColor);
        }
    }
    
    public static boolean cpuHasTrumpAceAndPlayerHasOnlyTrump10 (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1: return (Deck.clubA.positionInGame==1 && Deck.club10.positionInGame==4 && !playerHasMidOrLowClub && playerHasNoJacks);
            case 2: return (Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame==4 && !playerHasMidOrLowSpade && playerHasNoJacks);
            case 3: return (Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame==4 && !playerHasMidOrLowHeart && playerHasNoJacks);
            case 4: return (Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame==4 && !playerHasMidOrLowDiamond && playerHasNoJacks);
            default: return false;
        }
    }
    
    public static boolean playerHasOnlyHighTrump()
    {
        switch (trumpColor) {
            case 1: return (playerHasHighClub && !playerHasMidOrLowClub && playerHasNoJacks);
            case 2: return (playerHasHighSpade && !playerHasMidOrLowSpade && playerHasNoJacks);
            case 3: return (playerHasHighHeart && !playerHasMidOrLowHeart && playerHasNoJacks);
            case 4: return (playerHasHighDiamond && !playerHasMidOrLowDiamond && playerHasNoJacks);
            default: return false;
        }
    }
    
    // This strategy is good enough. It happened often enough.
    public static void tryGetHighTrumpCardWithJack (SkatcardDeck Deck)
    {
        if (AINormal.cpuHasJack(Deck) && playerHasOnlyHighTrump()) {
            if (isShowingAIStrategies) {infoDialog(stringTrumpHighWithJack);}
            AINormal.tryPlayJackStartingWithLowest(Deck);
        }
    }
        
    // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move"! This strategy happens very often.
    public static void tryServableAce (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1:
                tryServableSpadeAce(Deck);
                tryServableHeartAce(Deck);
                tryServableDiamAce(Deck);
                break;
            case 2:
                tryServableClubAce(Deck);
                tryServableHeartAce(Deck);
                tryServableDiamAce(Deck);
                break;
            case 3:
                tryServableClubAce(Deck);
                tryServableSpadeAce(Deck);
                tryServableDiamAce(Deck);
                break;
            case 4:
                tryServableClubAce(Deck);
                tryServableSpadeAce(Deck);
                tryServableHeartAce(Deck);
                break;
        }
    }
    
    // In order not to repeat oneself, out-source here the servable ace strategies for different colors.
    public static void tryServableClubAce (SkatcardDeck Deck)
    {
        if (Deck.clubA.positionInGame==1 && playerHasMidOrLowClub) {
            if (isShowingAIStrategies) {infoDialog(stringServeAce);}
            finalChoice=Deck.clubA.positionOnHand;
        }
    }
    public static void tryServableSpadeAce (SkatcardDeck Deck)
    {
        if (finalChoice==0 && Deck.spadeA.positionInGame==1 && playerHasMidOrLowSpade) {
            if (isShowingAIStrategies) {infoDialog(stringServeAce);}
            finalChoice=Deck.spadeA.positionOnHand;
        }
    }
    public static void tryServableHeartAce (SkatcardDeck Deck)
    {
        if (finalChoice==0 && Deck.heartA.positionInGame==1 && playerHasMidOrLowHeart) {
            if (isShowingAIStrategies) {infoDialog(stringServeAce);}
            finalChoice=Deck.heartA.positionOnHand;
        }
    }
    public static void tryServableDiamAce (SkatcardDeck Deck)
    {
        if (finalChoice==0 && Deck.diamA.positionInGame==1 && playerHasMidOrLowDiamond) {
            if (isShowingAIStrategies) {infoDialog(stringServeAce);}
            finalChoice=Deck.diamA.positionOnHand;
        }
    }
    
    // This is the de facto 2nd best strategy on this difficulty level! It happens quite often.
    public static void tryServable10 (SkatcardDeck Deck)
    {
        for (int index = 1; index <= 4; index++){
            if (finalChoice==0 && cpuHasTenAndPlayerCanServeLowerColor(Deck, index) && (trumpColor!=index || playerHasNoJacks)) {
                if (isShowingAIStrategies) {infoDialog(stringServeTen);}
                    AINormal.playTenOfColor(Deck, index);
            }
        }
    }
    
    public static boolean cpuHasTenAndPlayerCanServeLowerColor (SkatcardDeck Deck, int color)
    {
        switch (color) {
            case 1: return (Deck.club10.positionInGame==1 && !playerHasHighClub && playerHasMidOrLowClub);
            case 2: return (Deck.spade10.positionInGame==1 && !playerHasHighSpade && playerHasMidOrLowSpade);
            case 3: return (Deck.heart10.positionInGame==1 && !playerHasHighHeart && playerHasMidOrLowHeart);
            case 4: return (Deck.diam10.positionInGame==1 && !playerHasHighHeart && playerHasMidOrLowHeart);
            default: return false;
        }
    }
    
    public static boolean cpuHasTrumpAceAndPlayerCanServe (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1: return (Deck.clubA.positionInGame==1 && (playerHasMidOrLowClub || playerHasHighClub) && playerHasNoJacks);
            case 2: return (Deck.spadeA.positionInGame==1 && (playerHasMidOrLowSpade || playerHasHighSpade) && playerHasNoJacks);
            case 3: return (Deck.heartA.positionInGame==1 && (playerHasMidOrLowHeart || playerHasHighHeart) && playerHasNoJacks);
            case 4: return (Deck.diamA.positionInGame==1 && (playerHasMidOrLowDiamond || playerHasHighDiamond) && playerHasNoJacks);
            default: return false;
        }
    }
    
    // Play the trump ace, if the player has to serve it with a lower trump. This way the CPU can get an ace home, and steal a trump of the player.
    public static void tryServableTrumpAce (SkatcardDeck Deck)
    { // This is a good strategy. It happens often enough.
        if (cpuHasTrumpAceAndPlayerCanServe(Deck)) {
            if (isShowingAIStrategies) {infoDialog(stringServeTrumpAce);}
            AINormal.playAceOfColor(Deck, trumpColor);
        }
    }
    
    public static void tryServableKing (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1:
                tryServableSpadeKing(Deck);
                tryServableHeartKing(Deck);
                tryServableDiamKing(Deck);
                break;
            case 2:
                tryServableClubKing(Deck);
                tryServableHeartKing(Deck);
                tryServableDiamKing(Deck);
                break;
            case 3:
                tryServableClubKing(Deck);
                tryServableSpadeKing(Deck);
                tryServableDiamKing(Deck);
                break;
            case 4:
                tryServableClubKing(Deck);
                tryServableSpadeKing(Deck);
                tryServableHeartKing(Deck);
                break;
        }
    }
    
    // In order not to repeat oneself, out-source here the servable king strategies for different colors.
    public static void tryServableClubKing (SkatcardDeck Deck){
        if (Deck.clubK.positionInGame==1 && !playerHasHighClub && playerHasMidOrLowClub) {
            if (isShowingAIStrategies) {infoDialog(stringServeKing);}
            finalChoice=Deck.clubK.positionOnHand;
        }
    }
    public static void tryServableSpadeKing (SkatcardDeck Deck)
    {
        if (Deck.spadeK.positionInGame==1 && !playerHasHighSpade && playerHasMidOrLowSpade) {
            if (isShowingAIStrategies) {infoDialog(stringServeKing);}
            finalChoice=Deck.spadeK.positionOnHand;
        }
    }
    public static void tryServableHeartKing (SkatcardDeck Deck)
    {
        if (Deck.heartK.positionInGame==1 && !playerHasHighHeart && playerHasMidOrLowHeart) {
            if (isShowingAIStrategies) {infoDialog(stringServeKing);}
            finalChoice=Deck.heartK.positionOnHand;
        }
    }
    public static void tryServableDiamKing (SkatcardDeck Deck)
    {
        if (Deck.diamK.positionInGame==1 && !playerHasHighDiamond && playerHasMidOrLowDiamond) {
            if (isShowingAIStrategies) {infoDialog(stringServeKing);}
            finalChoice=Deck.diamK.positionOnHand;
        }
    }
    
    public static void tryServableQueen (SkatcardDeck Deck)
    {
        switch (trumpColor) {
            case 1:
                tryServableSpadeQueen(Deck);
                tryServableHeartQueen(Deck);
                tryServableDiamQueen(Deck);
                break;
            case 2:
                tryServableClubQueen(Deck);
                tryServableHeartQueen(Deck);
                tryServableDiamQueen(Deck);
                break;
            case 3:
                tryServableClubQueen(Deck);
                tryServableSpadeQueen(Deck);
                tryServableDiamQueen(Deck);
                break;
            case 4:
                tryServableClubQueen(Deck);
                tryServableSpadeQueen(Deck);
                tryServableHeartQueen(Deck);
                break;
        }
    }
    
    // In order not to repeat oneself, out-source here the servable king strategies for different colors.
    public static void tryServableClubQueen (SkatcardDeck Deck){
        boolean playerHasNoHigherClubCard = Deck.clubK.positionInGame!=4 && !playerHasHighClub;
        if (Deck.clubQ.positionInGame==1 && playerHasNoHigherClubCard && playerHasMidOrLowClub) {
            if (isShowingAIStrategies) {infoDialog(stringServeQueen);}
            finalChoice=Deck.clubQ.positionOnHand;
        }
    }
    public static void tryServableSpadeQueen (SkatcardDeck Deck)
    {
        boolean playerHasNoHigherSpadeCard = Deck.spadeK.positionInGame!=4 && !playerHasHighSpade;
        if (Deck.spadeQ.positionInGame==1 && playerHasNoHigherSpadeCard && playerHasMidOrLowSpade) {
            if (isShowingAIStrategies) {infoDialog(stringServeQueen);}
            finalChoice=Deck.spadeQ.positionOnHand;
        }
    }
    public static void tryServableHeartQueen (SkatcardDeck Deck)
    {
        boolean playerHasNoHigherHeartCard = Deck.heartK.positionInGame!=4 && !playerHasHighHeart;
        if (Deck.heartQ.positionInGame==1 && playerHasNoHigherHeartCard && playerHasMidOrLowHeart) {
            if (isShowingAIStrategies) {infoDialog(stringServeQueen);}
            finalChoice=Deck.heartQ.positionOnHand;
        }
    }
    public static void tryServableDiamQueen (SkatcardDeck Deck)
    {
        boolean playerHasNoHigherDiamCard = Deck.diamK.positionInGame!=4 && !playerHasHighDiamond;
        if (Deck.diamQ.positionInGame==1 && playerHasNoHigherDiamCard && playerHasMidOrLowDiamond) {
            if (isShowingAIStrategies) {infoDialog(stringServeQueen);}
            finalChoice=Deck.diamQ.positionOnHand;
        }
    }
    
    // This is a good strategy. It doesn't happen often, but is complementing the usual aggressive techniques quite well.
    public static void tryLureOutJack (SkatcardDeck Deck)
    {
        if (!playerHasNoJacks && AINormal.cpuHasLowCard(Deck, trumpColor) && playerHasNoColor(trumpColor)) {
            if (isShowingAIStrategies) {infoDialog(stringLureJack);}
            for (int index = 1; index <= 4; index++){
                switch (trumpColor) {
                    case 1: AINormal.tryPlayClubLowCardStartingWithLowest(Deck); break;
                    case 2: AINormal.tryPlaySpadeLowCardStartingWithLowest(Deck); break;
                    case 3: AINormal.tryPlayHeartLowCardStartingWithLowest(Deck); break;
                    case 4: AINormal.tryPlayDiamLowCardStartingWithLowest(Deck); break;
                }
            }
        }
    }
    
    // returns true, if the player can not serve a given color (jacks are ignored here)
    public static boolean playerHasNoColor (int color)
    {
        switch (color) {
            case 1: return (!playerHasMidOrLowClub && !playerHasHighClub);
            case 2: return (!playerHasMidOrLowSpade && !playerHasHighSpade);
            case 3: return (!playerHasMidOrLowHeart && !playerHasHighHeart);
            case 4: return (!playerHasMidOrLowDiamond && !playerHasHighDiamond);
            default: return false;
        }
    }
    
    public static void tryDefUnservableCardWhenPlayerHasNoTrump (SkatcardDeck Deck)
    {
        if (playerHasNoJacks && playerHasNoColor(trumpColor)) {
            if (isShowingAIStrategies) {infoDialog(stringUnserveNoTrump);}
            AINormal.discardLeastPointsPossible(Deck);
        }
    }
    
    // computer chooses trump by maximizing the number of trump cards on entire hand (cheating by looking at playable and back side)
    // returns the trump color as int
    public static int cpuChoosesTrump (Hand playableCPU, Hand backhandCPU)
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
            Card = backhandCPU.getNthCardOfHand(index);
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
        // choose maximum (if 4 or less of each kind, choose one randomly), results in at least 4 trump in total during game
        int maximum = Math.max(Math.max(numberOfClubs, numberOfSpades), Math.max(numberOfHearts, numberOfDiamonds));
        if (maximum<=4) {
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
