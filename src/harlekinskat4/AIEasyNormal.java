package harlekinskat4;

/**
 * This class models the decision making of the CPU on easy and normal difficulty.
 * Since the computer on lowest difficulty uses random choices,
 * here are also methods for shuffling a deck and coin flips.
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

public class AIEasyNormal {
    public static int counter;
    
    public static int[] optionArray = new int[8]; // for counting the options for cpu decisions
    public static int numberOfOptions;
    public static int finalChoice=0; // integer value of card to be played by cpu after it finally decided on it
    
    public static int aiLevelNormal(Skatcard playedCardPlayer, Hand playableCPU, Hand backhandPlayer, Hand playable, SkatcardDeck Deck)
    //public static int aiLevelNormal(Skatcard playedCardPlayer, Hand playableCPU, Hand backhandPlayer, Hand playable, Skatcard clubJ, Skatcard spadeJ, Skatcard heartJ, Skatcard diamJ, Skatcard clubA, Skatcard club10, Skatcard clubK, Skatcard clubQ, Skatcard club9, Skatcard club8, Skatcard club7, Skatcard spadeA, Skatcard spade10, Skatcard spadeK, Skatcard spadeQ, Skatcard spade9, Skatcard spade8, Skatcard spade7, Skatcard heartA, Skatcard heart10, Skatcard heartK, Skatcard heartQ, Skatcard heart9, Skatcard heart8, Skatcard heart7, Skatcard diamA, Skatcard diam10, Skatcard diamK, Skatcard diamQ, Skatcard diam9, Skatcard diam8, Skatcard diam7)
    {
        // here the player has played a card and the computer reacts
        if (cpuReacts == true){
        
                // same as difficulty 3 just without cheating, i.e. leave out all conditions containing backhandCPU
                finalChoice=0;
                // first decide, if one can serve the color or not
                // if yes, try to take home as much points as possible
                // if this is not possible or if one can not serve the color, try to lose as least points as possible (ideally a 9, 8 or 7 such, that the CPU does not have to serve the color any more (also consider the card to turn around when doing so))
                if ((playedCardPlayer.color==trumpColor) || (playedCardPlayer.cardId < 5)){ // if a jack or other trump has been played
                    trumpPlayed=true;
                    currentColor=trumpColor;
                    servable=false;
                    if ((playableCPU.card1.color==currentColor && playableCPU.card1.cardId > 4) || playableCPU.card1.cardId < 5){
                        servable=true;
                    }
                    else {
                        if ((playableCPU.card2.color==currentColor && playableCPU.card2.cardId > 4) || playableCPU.card2.cardId < 5){
                            servable=true;
                        }
                        else {
                            if ((playableCPU.card3.color==currentColor && playableCPU.card3.cardId > 4) || playableCPU.card3.cardId < 5){
                                servable=true;
                            }
                            else {
                                if ((playableCPU.card4.color==currentColor && playableCPU.card4.cardId > 4) || playableCPU.card4.cardId < 5){
                                    servable=true;
                                }
                                else {
                                    if ((playableCPU.card5.color==currentColor && playableCPU.card5.cardId > 4) || playableCPU.card5.cardId < 5){
                                        servable=true;
                                    }
                                    else {
                                        if ((playableCPU.card6.color==currentColor && playableCPU.card6.cardId > 4) || playableCPU.card6.cardId < 5){
                                            servable=true;
                                        }
                                        else {
                                            if ((playableCPU.card7.color==currentColor && playableCPU.card7.cardId > 4) || playableCPU.card7.cardId < 5){
                                                servable=true;
                                            }
                                            else {
                                                if ((playableCPU.card8.color==currentColor && playableCPU.card8.cardId > 4) || playableCPU.card8.cardId < 5){
                                                    servable=true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (servable==true){ // here the CPU can serve trump (in all cases just make a priority list of all possible cards, since there are only 11 trump cards in total)
                        // if it is the trump 10, try to get it with the ace (or with a low jack)
                        // if it is the trump ace, try to get it with a low jack
                        // (in both cases try to get rid of the lowest trump card possible)
                        // if it is a trump king or queen, try to get it with a 10 (otherwise try low card, or low Jack)
                        // if it is a trump low card, try to get it with queen or king (otherwise try to use another low card)
                        // if it is a jack, try to get rid of low card (otherwise use another jack)
                        if (playedCardPlayer.value==3 || playedCardPlayer.value==2) { // if it is a trump 10 or ace
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "can serve trump ace or 10");
                    
                            // depending on what color is trump
                            switch (trumpColor) {
                                case 1: // if clubs is trump
                                    if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;}
                                    else if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;}
                                    else if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;}
                                    else if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;}
                                    else if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==4 || playedCardPlayer.value==5) { // if it is a trump king or queen
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "can serve trump king or queen");
                    
                            switch (trumpColor) {
                                case 1: // if clubs is trump
                                    if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==6 || playedCardPlayer.value==7 || playedCardPlayer.value==8) { // if it is a low trump (7, 8, 9)
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "can serve trump low card");
                    
                            switch (trumpColor) {
                                case 1: // if clubs is trump
                                    if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;}
                                    else if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    else if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;}
                                    else if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    else if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;}
                                    else if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    else if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;}
                                    else if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    else if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.cardId<5) { // if it is a jack
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "can serve a jack");
                    
                            switch (trumpColor) {
                                case 1: // if clubs is trump
                                    if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;}
                                    else if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    else if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;}
                                    else if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    else if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;}
                                    else if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    else if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand; break;}
                                    else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand; break;}
                                    else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand; break;}
                                    else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;}
                                    else if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    else if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                    }
                    else { // here the CPU can not serve trump => try to lose the least points possible
                        // try to discard a low card (but try to get rid of a whole color)
                        // look if CPU has only one card of a color
                        
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "can't serve trump, try to discard color");
                        
                        counter=0;
                        counter1=0;
                        counter2=0;
                        counter3=0;
                        counter4=0;
                        if (playableCPU.card1.color==1 && playableCPU.card1.cardId>4){counter1++;} if (playableCPU.card1.color==2 && playableCPU.card1.cardId>4){counter2++;} if (playableCPU.card1.color==3 && playableCPU.card1.cardId>4){counter3++;} if (playableCPU.card1.color==4 && playableCPU.card1.cardId>4){counter4++;}
                        if (playableCPU.card2.color==1 && playableCPU.card2.cardId>4){counter1++;} if (playableCPU.card2.color==2 && playableCPU.card2.cardId>4){counter2++;} if (playableCPU.card2.color==3 && playableCPU.card2.cardId>4){counter3++;} if (playableCPU.card2.color==4 && playableCPU.card2.cardId>4){counter4++;}
                        if (playableCPU.card3.color==1 && playableCPU.card3.cardId>4){counter1++;} if (playableCPU.card3.color==2 && playableCPU.card3.cardId>4){counter2++;} if (playableCPU.card3.color==3 && playableCPU.card3.cardId>4){counter3++;} if (playableCPU.card3.color==4 && playableCPU.card3.cardId>4){counter4++;}
                        if (playableCPU.card4.color==1 && playableCPU.card4.cardId>4){counter1++;} if (playableCPU.card4.color==2 && playableCPU.card4.cardId>4){counter2++;} if (playableCPU.card4.color==3 && playableCPU.card4.cardId>4){counter3++;} if (playableCPU.card4.color==4 && playableCPU.card4.cardId>4){counter4++;}
                        if (playableCPU.card5.color==1 && playableCPU.card5.cardId>4){counter1++;} if (playableCPU.card5.color==2 && playableCPU.card5.cardId>4){counter2++;} if (playableCPU.card5.color==3 && playableCPU.card5.cardId>4){counter3++;} if (playableCPU.card5.color==4 && playableCPU.card5.cardId>4){counter4++;}
                        if (playableCPU.card6.color==1 && playableCPU.card6.cardId>4){counter1++;} if (playableCPU.card6.color==2 && playableCPU.card6.cardId>4){counter2++;} if (playableCPU.card6.color==3 && playableCPU.card6.cardId>4){counter3++;} if (playableCPU.card6.color==4 && playableCPU.card6.cardId>4){counter4++;}
                        if (playableCPU.card7.color==1 && playableCPU.card7.cardId>4){counter1++;} if (playableCPU.card7.color==2 && playableCPU.card7.cardId>4){counter2++;} if (playableCPU.card7.color==3 && playableCPU.card7.cardId>4){counter3++;} if (playableCPU.card7.color==4 && playableCPU.card7.cardId>4){counter4++;}
                        if (playableCPU.card8.color==1 && playableCPU.card8.cardId>4){counter1++;} if (playableCPU.card8.color==2 && playableCPU.card8.cardId>4){counter2++;} if (playableCPU.card8.color==3 && playableCPU.card8.cardId>4){counter3++;} if (playableCPU.card8.color==4 && playableCPU.card8.cardId>4){counter4++;}
                        // if there is only one card of a color and it is a low card (7, 8, 9), play it
                        if (counter1==1 && trumpColor!=1) {
                            if (playableCPU.card1.color==1 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==1 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==1 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==1 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==1 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==1 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==1 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==1 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (playableCPU.card1.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (playableCPU.card2.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (playableCPU.card3.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (playableCPU.card4.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (playableCPU.card5.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (playableCPU.card6.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (playableCPU.card7.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (playableCPU.card8.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        } // same for other colors
                        if (counter2==1 && trumpColor!=2) {
                            if (playableCPU.card1.color==2 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==2 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==2 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==2 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==2 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==2 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==2 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==2 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (playableCPU.card1.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (playableCPU.card2.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (playableCPU.card3.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (playableCPU.card4.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (playableCPU.card5.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (playableCPU.card6.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (playableCPU.card7.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (playableCPU.card8.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        }
                        if (counter3==1 && trumpColor!=3) {
                            if (playableCPU.card1.color==3 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==3 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==3 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==3 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==3 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==3 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==3 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==3 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (playableCPU.card1.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (playableCPU.card2.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (playableCPU.card3.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (playableCPU.card4.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (playableCPU.card5.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (playableCPU.card6.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (playableCPU.card7.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (playableCPU.card8.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        }
                        if (counter4==1 && trumpColor!=4) {
                            if (playableCPU.card1.color==4 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==4 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==4 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==4 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==4 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==4 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==4 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==4 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (playableCPU.card1.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (playableCPU.card2.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (playableCPU.card3.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (playableCPU.card4.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (playableCPU.card5.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (playableCPU.card6.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (playableCPU.card7.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (playableCPU.card8.value>3) {
                                    // if the single color card is a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        }
                        
                        // if the above strategy did not work, try to play the non-trump color with the least points
                        // this is a long priority list
                        if (finalChoice==0){
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "discarding a color went wrong, try to discard low card");
                            
                            if (Deck.diam7.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam7.positionOnHand;}
                            else if (Deck.heart7.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart7.positionOnHand;}
                            else if (Deck.spade7.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade7.positionOnHand;}
                            else if (Deck.club7.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club7.positionOnHand;}
                            else if (Deck.diam8.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam8.positionOnHand;}
                            else if (Deck.heart8.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart8.positionOnHand;}
                            else if (Deck.spade8.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade8.positionOnHand;}
                            else if (Deck.club8.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club8.positionOnHand;}
                            else if (Deck.diam9.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam9.positionOnHand;}
                            else if (Deck.heart9.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart9.positionOnHand;}
                            else if (Deck.spade9.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade9.positionOnHand;}
                            else if (Deck.club9.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club9.positionOnHand;}
                            else if (Deck.diamQ.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamQ.positionOnHand;}
                            else if (Deck.heartQ.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartQ.positionOnHand;}
                            else if (Deck.spadeQ.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeQ.positionOnHand;}
                            else if (Deck.clubQ.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubQ.positionOnHand;}
                            else if (Deck.diamK.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamK.positionOnHand;}
                            else if (Deck.heartK.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartK.positionOnHand;}
                            else if (Deck.spadeK.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeK.positionOnHand;}
                            else if (Deck.clubK.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubK.positionOnHand;}
                            // if everything else breaks down, unfortunately discard a valuable card
                            else if (Deck.diam10.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam10.positionOnHand;}
                            else if (Deck.heart10.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart10.positionOnHand;}
                            else if (Deck.spade10.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade10.positionOnHand;}
                            else if (Deck.club10.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club10.positionOnHand;}
                            else if (Deck.diamA.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamA.positionOnHand;}
                            else if (Deck.heartA.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartA.positionOnHand;}
                            else if (Deck.spadeA.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeA.positionOnHand;}
                            else if (Deck.clubA.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubA.positionOnHand;}
                            // another case does not exist! (so here it will definitely be over)
                        }
                    }
                }
                else { // if a non-trump color has been played
                    trumpPlayed=false;
                    currentColor=playedCardPlayer.color;
                    // go through all cards and look if it is a card of the same "color" 
                    servable=false;
                    if (playableCPU.card1.color==currentColor && playableCPU.card1.cardId > 4){
                        servable=true;
                    }
                    else {
                        if (playableCPU.card2.color==currentColor && playableCPU.card2.cardId > 4){
                            servable=true;
                        }
                        else {
                            if (playableCPU.card3.color==currentColor && playableCPU.card3.cardId > 4){
                                servable=true;
                            }
                            else {
                                if (playableCPU.card4.color==currentColor && playableCPU.card4.cardId > 4){
                                    servable=true;
                                }
                                else {
                                    if (playableCPU.card5.color==currentColor && playableCPU.card5.cardId > 4){
                                        servable=true;
                                    }
                                    else {
                                        if (playableCPU.card6.color==currentColor && playableCPU.card6.cardId > 4){
                                            servable=true;
                                        }
                                        else {
                                            if (playableCPU.card7.color==currentColor && playableCPU.card7.cardId > 4){
                                                servable=true;
                                            }
                                            else {
                                                if (playableCPU.card8.color==currentColor && playableCPU.card8.cardId > 4){
                                                    servable=true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (servable==true){ // here the CPU can serve the non-trump color => try to make the most points possible (or at least lose the least points possible)
                        // same source code for being able to serve trump (just with trumpcolor replaced by currentColor and without the jacks as options)
                        if (playedCardPlayer.value==3 || playedCardPlayer.value==2) { // if it is a 10 or ace
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "answer an ace or 10");
                            
                            switch (currentColor) {
                                case 1: // if clubs is played
                                    if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;}
                                    else if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is played
                                    if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;}
                                    else if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is played
                                    if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;}
                                    else if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is played
                                    if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;}
                                    else if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==4 || playedCardPlayer.value==5) { // if it is a king or queen
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "answer a king or queen");
                            
                            switch (currentColor) {
                                case 1: // if clubs is played
                                    if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is played
                                    if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is played
                                    if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is played
                                    if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==6 || playedCardPlayer.value==7 || playedCardPlayer.value==8) { // if it is a low card (7, 8, 9)
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "answer a low card");
                            
                            switch (currentColor) {
                                case 1: // if clubs is played
                                    if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand; break;}
                                    else if (Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand; break;}
                                    else if (Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand; break;}
                                    else if (Deck.clubQ.positionInGame==1){finalChoice=Deck.clubQ.positionOnHand; break;}
                                    else if (Deck.clubK.positionInGame==1){finalChoice=Deck.clubK.positionOnHand; break;}
                                    else if (Deck.club10.positionInGame==1){finalChoice=Deck.club10.positionOnHand; break;}
                                    else if (Deck.clubA.positionInGame==1){finalChoice=Deck.clubA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is played
                                    if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand; break;}
                                    else if (Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand; break;}
                                    else if (Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand; break;}
                                    else if (Deck.spadeQ.positionInGame==1){finalChoice=Deck.spadeQ.positionOnHand; break;}
                                    else if (Deck.spadeK.positionInGame==1){finalChoice=Deck.spadeK.positionOnHand; break;}
                                    else if (Deck.spade10.positionInGame==1){finalChoice=Deck.spade10.positionOnHand; break;}
                                    else if (Deck.spadeA.positionInGame==1){finalChoice=Deck.spadeA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is played
                                    if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand; break;}
                                    else if (Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand; break;}
                                    else if (Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand; break;}
                                    else if (Deck.heartQ.positionInGame==1){finalChoice=Deck.heartQ.positionOnHand; break;}
                                    else if (Deck.heartK.positionInGame==1){finalChoice=Deck.heartK.positionOnHand; break;}
                                    else if (Deck.heart10.positionInGame==1){finalChoice=Deck.heart10.positionOnHand; break;}
                                    else if (Deck.heartA.positionInGame==1){finalChoice=Deck.heartA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is played
                                    if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand; break;}
                                    else if (Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand; break;}
                                    else if (Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand; break;}
                                    else if (Deck.diamQ.positionInGame==1){finalChoice=Deck.diamQ.positionOnHand; break;}
                                    else if (Deck.diamK.positionInGame==1){finalChoice=Deck.diamK.positionOnHand; break;}
                                    else if (Deck.diam10.positionInGame==1){finalChoice=Deck.diam10.positionOnHand; break;}
                                    else if (Deck.diamA.positionInGame==1){finalChoice=Deck.diamA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                    }
                    else { // here the CPU can not serve the non-trump color => try to play a trump ace or 10 (or at least lose the least points possible)
                        // 1st find out, if card is worthy (at least 10 points) of taking it
                        // find out, if CPU has a trump card at all (no, not needed with double priority list (all cards once assuming it is trump and once not))
                        // if it is worthy, try to get it with the most points as possible, otherwise try to lose as least points as possible
                        // if it is not worthy, try to lose as least points as possible
                        if (playedCardPlayer.points>4) {
                            // This strategy is good enough. It happened often enough.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "answer: try to trump the high card");
                            
                            // try to take it with high-value trump
                            if (Deck.clubA.positionInGame==1 && trumpColor==1){finalChoice=Deck.clubA.positionOnHand;}
                            else if (Deck.spadeA.positionInGame==1 && trumpColor==2){finalChoice=Deck.spadeA.positionOnHand;}
                            else if (Deck.heartA.positionInGame==1 && trumpColor==3){finalChoice=Deck.heartA.positionOnHand;}
                            else if (Deck.diamA.positionInGame==1 && trumpColor==4){finalChoice=Deck.diamA.positionOnHand;}
                            else if (Deck.club10.positionInGame==1 && trumpColor==1){finalChoice=Deck.club10.positionOnHand;}
                            else if (Deck.spade10.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade10.positionOnHand;}
                            else if (Deck.heart10.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart10.positionOnHand;}
                            else if (Deck.diam10.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam10.positionOnHand;}
                            // try to get it with low value trump
                            else if (Deck.clubK.positionInGame==1 && trumpColor==1){finalChoice=Deck.clubK.positionOnHand;}
                            else if (Deck.spadeK.positionInGame==1 && trumpColor==2){finalChoice=Deck.spadeK.positionOnHand;}
                            else if (Deck.heartK.positionInGame==1 && trumpColor==3){finalChoice=Deck.heartK.positionOnHand;}
                            else if (Deck.diamK.positionInGame==1 && trumpColor==4){finalChoice=Deck.diamK.positionOnHand;}
                            else if (Deck.clubQ.positionInGame==1 && trumpColor==1){finalChoice=Deck.clubQ.positionOnHand;}
                            else if (Deck.spadeQ.positionInGame==1 && trumpColor==2){finalChoice=Deck.spadeQ.positionOnHand;}
                            else if (Deck.heartQ.positionInGame==1 && trumpColor==3){finalChoice=Deck.heartQ.positionOnHand;}
                            else if (Deck.diamQ.positionInGame==1 && trumpColor==4){finalChoice=Deck.diamQ.positionOnHand;}
                            else if (Deck.diam7.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam7.positionOnHand;}
                            else if (Deck.heart7.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart7.positionOnHand;}
                            else if (Deck.spade7.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade7.positionOnHand;}
                            else if (Deck.club7.positionInGame==1 && trumpColor==1){finalChoice=Deck.club7.positionOnHand;}
                            else if (Deck.diam8.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam8.positionOnHand;}
                            else if (Deck.heart8.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart8.positionOnHand;}
                            else if (Deck.spade8.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade8.positionOnHand;}
                            else if (Deck.club8.positionInGame==1 && trumpColor==1){finalChoice=Deck.club8.positionOnHand;}
                            else if (Deck.diam9.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam9.positionOnHand;}
                            else if (Deck.heart9.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart9.positionOnHand;}
                            else if (Deck.spade9.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade9.positionOnHand;}
                            else if (Deck.club9.positionInGame==1 && trumpColor==1){finalChoice=Deck.club9.positionOnHand;}
                            // try to get it with jack
                            else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand;}
                            else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand;}
                            else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand;}
                            else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand;}
                            // discard low value card
                            else if (Deck.diam7.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam7.positionOnHand;}
                            else if (Deck.heart7.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart7.positionOnHand;}
                            else if (Deck.spade7.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade7.positionOnHand;}
                            else if (Deck.club7.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club7.positionOnHand;}
                            else if (Deck.diam8.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam8.positionOnHand;}
                            else if (Deck.heart8.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart8.positionOnHand;}
                            else if (Deck.spade8.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade8.positionOnHand;}
                            else if (Deck.club8.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club8.positionOnHand;}
                            else if (Deck.diam9.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam9.positionOnHand;}
                            else if (Deck.heart9.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart9.positionOnHand;}
                            else if (Deck.spade9.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade9.positionOnHand;}
                            else if (Deck.club9.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club9.positionOnHand;}
                            else if (Deck.diamQ.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamQ.positionOnHand;}
                            else if (Deck.heartQ.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartQ.positionOnHand;}
                            else if (Deck.spadeQ.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeQ.positionOnHand;}
                            else if (Deck.clubQ.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubQ.positionOnHand;}
                            else if (Deck.diamK.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamK.positionOnHand;}
                            else if (Deck.heartK.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartK.positionOnHand;}
                            else if (Deck.spadeK.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeK.positionOnHand;}
                            else if (Deck.clubK.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubK.positionOnHand;}
                            // if everything else breaks down, unfortunately discard a valuable card
                            else if (Deck.diam10.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam10.positionOnHand;}
                            else if (Deck.heart10.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart10.positionOnHand;}
                            else if (Deck.spade10.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade10.positionOnHand;}
                            else if (Deck.club10.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club10.positionOnHand;}
                            else if (Deck.diamA.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamA.positionOnHand;}
                            else if (Deck.heartA.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartA.positionOnHand;}
                            else if (Deck.spadeA.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeA.positionOnHand;}
                            else if (Deck.clubA.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubA.positionOnHand;}
                            // another case does not exist! (so here it will definitely be over)
                        }
                        else {
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "answer: try to discard a low card to the low card");
                            
                            // discard low value card
                            if (Deck.diam7.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam7.positionOnHand;}
                            else if (Deck.heart7.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart7.positionOnHand;}
                            else if (Deck.spade7.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade7.positionOnHand;}
                            else if (Deck.club7.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club7.positionOnHand;}
                            else if (Deck.diam8.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam8.positionOnHand;}
                            else if (Deck.heart8.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart8.positionOnHand;}
                            else if (Deck.spade8.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade8.positionOnHand;}
                            else if (Deck.club8.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club8.positionOnHand;}
                            else if (Deck.diam9.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam9.positionOnHand;}
                            else if (Deck.heart9.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart9.positionOnHand;}
                            else if (Deck.spade9.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade9.positionOnHand;}
                            else if (Deck.club9.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club9.positionOnHand;}
                            // take it with a semi high trump
                            else if (Deck.clubK.positionInGame==1 && trumpColor==1){finalChoice=Deck.clubK.positionOnHand;}
                            else if (Deck.spadeK.positionInGame==1 && trumpColor==2){finalChoice=Deck.spadeK.positionOnHand;}
                            else if (Deck.heartK.positionInGame==1 && trumpColor==3){finalChoice=Deck.heartK.positionOnHand;}
                            else if (Deck.diamK.positionInGame==1 && trumpColor==4){finalChoice=Deck.diamK.positionOnHand;}
                            else if (Deck.clubQ.positionInGame==1 && trumpColor==1){finalChoice=Deck.clubQ.positionOnHand;}
                            else if (Deck.spadeQ.positionInGame==1 && trumpColor==2){finalChoice=Deck.spadeQ.positionOnHand;}
                            else if (Deck.heartQ.positionInGame==1 && trumpColor==3){finalChoice=Deck.heartQ.positionOnHand;}
                            else if (Deck.diamQ.positionInGame==1 && trumpColor==4){finalChoice=Deck.diamQ.positionOnHand;}
                            // take it with a high trump
                            else if (Deck.club10.positionInGame==1 && trumpColor==1){finalChoice=Deck.club10.positionOnHand;}
                            else if (Deck.spade10.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade10.positionOnHand;}
                            else if (Deck.heart10.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart10.positionOnHand;}
                            else if (Deck.diam10.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam10.positionOnHand;}
                            else if (Deck.clubA.positionInGame==1 && trumpColor==1){finalChoice=Deck.clubA.positionOnHand;}
                            else if (Deck.spadeA.positionInGame==1 && trumpColor==2){finalChoice=Deck.spadeA.positionOnHand;}
                            else if (Deck.heartA.positionInGame==1 && trumpColor==3){finalChoice=Deck.heartA.positionOnHand;}
                            else if (Deck.diamA.positionInGame==1 && trumpColor==4){finalChoice=Deck.diamA.positionOnHand;}
                            // discard a semi-valuable card
                            else if (Deck.diamQ.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamQ.positionOnHand;}
                            else if (Deck.heartQ.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartQ.positionOnHand;}
                            else if (Deck.spadeQ.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeQ.positionOnHand;}
                            else if (Deck.clubQ.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubQ.positionOnHand;}
                            else if (Deck.diamK.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamK.positionOnHand;}
                            else if (Deck.heartK.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartK.positionOnHand;}
                            else if (Deck.spadeK.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeK.positionOnHand;}
                            else if (Deck.clubK.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubK.positionOnHand;}
                            // take it with a low trump card
                            else if (Deck.diam7.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam7.positionOnHand;}
                            else if (Deck.heart7.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart7.positionOnHand;}
                            else if (Deck.spade7.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade7.positionOnHand;}
                            else if (Deck.club7.positionInGame==1 && trumpColor==1){finalChoice=Deck.club7.positionOnHand;}
                            else if (Deck.diam8.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam8.positionOnHand;}
                            else if (Deck.heart8.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart8.positionOnHand;}
                            else if (Deck.spade8.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade8.positionOnHand;}
                            else if (Deck.club8.positionInGame==1 && trumpColor==1){finalChoice=Deck.club8.positionOnHand;}
                            else if (Deck.diam9.positionInGame==1 && trumpColor==4){finalChoice=Deck.diam9.positionOnHand;}
                            else if (Deck.heart9.positionInGame==1 && trumpColor==3){finalChoice=Deck.heart9.positionOnHand;}
                            else if (Deck.spade9.positionInGame==1 && trumpColor==2){finalChoice=Deck.spade9.positionOnHand;}
                            else if (Deck.club9.positionInGame==1 && trumpColor==1){finalChoice=Deck.club9.positionOnHand;}
                            // take it with a jack
                            else if (Deck.diamJ.positionInGame==1){finalChoice=Deck.diamJ.positionOnHand;}
                            else if (Deck.heartJ.positionInGame==1){finalChoice=Deck.heartJ.positionOnHand;}
                            else if (Deck.spadeJ.positionInGame==1){finalChoice=Deck.spadeJ.positionOnHand;}
                            else if (Deck.clubJ.positionInGame==1){finalChoice=Deck.clubJ.positionOnHand;}
                            // if everything else breaks down, unfortunately discard a valuable card
                            else if (Deck.diam10.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam10.positionOnHand;}
                            else if (Deck.heart10.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart10.positionOnHand;}
                            else if (Deck.spade10.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade10.positionOnHand;}
                            else if (Deck.club10.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club10.positionOnHand;}
                            else if (Deck.diamA.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamA.positionOnHand;}
                            else if (Deck.heartA.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartA.positionOnHand;}
                            else if (Deck.spadeA.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeA.positionOnHand;}
                            else if (Deck.clubA.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubA.positionOnHand;}
                            // another case does not exist! (so here it will definitely be over)
                        }
                    }
                }
                // here the AI finally leaves this method on medium difficulty, when starting to play
                
                //AIEasy.debugStrategyDialog(550, 300, 500, 100, "answer: finally decide on playing a card");
                
                return finalChoice;
            
        }
        
         // here the CPU begins with playing a card
        else {
        
                // priority list:
                // #1: try to pull off "best move" using memory (including trump version)
                // #2: Look if CPU has a non-trump ace and the player does not have the 10 on the backhand and can definitely serve it. If yes, play it.
                // #3: Look if CPU has a non-trump ace and the player does not have the 10 on the backhand, count the number of (non-jack)-cards of that color that are invisible to the CPU and only play it, if they are at least 3 (out of 6) [Also same with 10.]
                // #4: Look if CPU has a non-trump 10, and the player can not have the ace (because on backhand or CPU has it, or it is already out) and there are still 3 (out of the last 5 cards of that color in the game). If yes, play it. (basicly similar to #3)
                // #5: same as #4 with king
                // #6: same with #4 with queen
                // #7: Lure out the jacks: Look if the CPU has a trump low card (7, 8 or 9) and any higher trump card and the player has only jacks as trump. If yes, play it.
                // #8: Look if the CPU has a 7, 8 or 9 not trump, that the player can not serve. If yes, play it.
                // #9: Look if the CPU has a 7, 8 or 9 not trump, that the player can probably not serve (because can only have 2 of that color). If yes, play it.
                //#10: Look if the CPU has 9, 8 or 7 being not trump. If yes, play it. (this is this "when in doubt"-case)
                //#11: If none of the above happen, play a random existing card. (If everything else breaks down, the CPU does essentially the same as on the lowest difficulty.)
                finalChoice=0; // this is important for look up, if the CPU has already decided on playing a card
                // #1.1: try to pull off best move
                if (trumpColor!=1){
                    if (couldntServeClubsBefore==true && numberOfClubCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (Deck.clubA.positionInGame==1 && Deck.club10.positionInGame==4 && ((Deck.club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.clubA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=2) {
                    if (couldntServeSpadesBefore==true && numberOfSpadeCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame==4 && ((Deck.spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.spadeA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=3) {
                    if (couldntServeHeartsBefore==true && numberOfHeartCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame==4 && ((Deck.heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.heartA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=4) {
                    if (couldntServeDiamondsBefore==true && numberOfDiamondCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame==4 && ((Deck.diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.diamA.positionOnHand;
                        }
                    }
                }
                // 1.2 Try to pull off best move, by counting the visible cards (good for making it possible in the first few turns!)
                if (finalChoice==0 && trumpColor!=1){
                    if (Deck.clubA.positionInGame==1 && Deck.club10.positionInGame==4 && ((Deck.club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.clubK.positionInGame%2==1 && Deck.clubQ.positionInGame%2==1 && Deck.club9.positionInGame%2==1 && Deck.club8.positionInGame%2==1 && Deck.club7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=Deck.clubA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=2){
                    if (Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame==4 && ((Deck.spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.spadeK.positionInGame%2==1 && Deck.spadeQ.positionInGame%2==1 && Deck.spade9.positionInGame%2==1 && Deck.spade8.positionInGame%2==1 && Deck.spade7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=Deck.spadeA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=3){
                    if (Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame==4 && ((Deck.heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.heartK.positionInGame%2==1 && Deck.heartQ.positionInGame%2==1 && Deck.heart9.positionInGame%2==1 && Deck.heart8.positionInGame%2==1 && Deck.heart7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=Deck.heartA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=4){
                    if (Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame==4 && ((Deck.diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.diamK.positionInGame%2==1 && Deck.diamQ.positionInGame%2==1 && Deck.diam9.positionInGame%2==1 && Deck.diam8.positionInGame%2==1 && Deck.diam7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=Deck.diamA.positionOnHand;
                        }
                    }
                }
                // 1.3 now best move with trump
                if (finalChoice==0 && couldntServeTrumpBefore==true && numberOfTrumpCardsSinceThen==1){
                    // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                    //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try best move with trump");
                    
                    if (trumpColor==1) {
                        if (Deck.clubA.positionInGame==1 && Deck.club10.positionInGame==4 && ((Deck.club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.clubA.positionOnHand;
                        }
                    }
                    else if (trumpColor==2) {
                        if (Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame==4 && ((Deck.spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.spadeA.positionOnHand;
                        }
                    }
                    else if (trumpColor==3) {
                        if (Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame==4 && ((Deck.heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.heartA.positionOnHand;
                        }
                    }
                    else if (trumpColor==4) {
                        if (Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame==4 && ((Deck.diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=Deck.diamA.positionOnHand;
                        }
                    }
                }
                // 1.4 Try to pull off best move, by counting the visible cards (good for making it possible in the first few turns!)
                if (finalChoice==0 && trumpColor==1){
                    if (Deck.clubA.positionInGame==1 && Deck.club10.positionInGame==4 && ((Deck.club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (Deck.clubK.positionInGame%2==1 && Deck.clubQ.positionInGame%2==1 && Deck.club9.positionInGame%2==1 && Deck.club8.positionInGame%2==1 && Deck.club7.positionInGame%2==1 && Deck.clubJ.positionInGame%2==1 && Deck.spadeJ.positionInGame%2==1 && Deck.heartJ.positionInGame%2==1 && Deck.diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=Deck.clubA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor==2) {
                    if (Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame==4 && ((Deck.spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (Deck.spadeK.positionInGame%2==1 && Deck.spadeQ.positionInGame%2==1 && Deck.spade9.positionInGame%2==1 && Deck.spade8.positionInGame%2==1 && Deck.spade7.positionInGame%2==1 && Deck.clubJ.positionInGame%2==1 && Deck.spadeJ.positionInGame%2==1 && Deck.heartJ.positionInGame%2==1 && Deck.diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=Deck.spadeA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor==3) {
                    if (Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame==4 && ((Deck.heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (Deck.heartK.positionInGame%2==1 && Deck.heartQ.positionInGame%2==1 && Deck.heart9.positionInGame%2==1 && Deck.heart8.positionInGame%2==1 && Deck.heart7.positionInGame%2==1 && Deck.clubJ.positionInGame%2==1 && Deck.spadeJ.positionInGame%2==1 && Deck.heartJ.positionInGame%2==1 && Deck.diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=Deck.heartA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpColor==4) {
                    if (Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame==4 && ((Deck.diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (Deck.diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (Deck.diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (Deck.diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (Deck.diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (Deck.diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (Deck.diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (Deck.diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (Deck.diamK.positionInGame%2==1 && Deck.diamQ.positionInGame%2==1 && Deck.diam9.positionInGame%2==1 && Deck.diam8.positionInGame%2==1 && Deck.diam7.positionInGame%2==1 && Deck.clubJ.positionInGame%2==1 && Deck.spadeJ.positionInGame%2==1 && Deck.heartJ.positionInGame%2==1 && Deck.diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=Deck.diamA.positionOnHand;
                        }
                    }
                }
                // #2.1: like best move just with any lower-than-10-card instead of 10
                if (finalChoice==0 && trumpColor!=1 && Deck.clubA.positionInGame==1 && Deck.club10.positionInGame!=3) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==1 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==1 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==1 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==1 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==1 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==1 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==1 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==1 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.clubA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                if (finalChoice==0 && trumpColor!=2 && Deck.spadeA.positionInGame==1 && Deck.spade10.positionInGame!=3) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==2 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==2 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==2 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==2 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==2 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==2 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==2 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==2 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.spadeA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                if (finalChoice==0 && trumpColor!=3 && Deck.heartA.positionInGame==1 && Deck.heart10.positionInGame!=3) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==3 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==3 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==3 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==3 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==3 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==3 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==3 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==3 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.heartA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                if (finalChoice==0 && trumpColor!=4 && Deck.diamA.positionInGame==1 && Deck.diam10.positionInGame!=3) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==4 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==4 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==4 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==4 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==4 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==4 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==4 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==4 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.diamA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                // #2.2: definitly servable 10 (good on first turn, but write it such, that it is valid foor all turns)
                // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                if (finalChoice==0 && trumpColor!=1 && Deck.club10.positionInGame==1 && Deck.clubA.positionInGame%2==1) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==1 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==1 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==1 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==1 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==1 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==1 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==1 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==1 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.club10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                if (finalChoice==0 && trumpColor!=2 && Deck.spade10.positionInGame==1 && Deck.spadeA.positionInGame%2==1) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==2 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==2 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==2 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==2 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==2 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==2 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==2 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==2 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.spade10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                if (finalChoice==0 && trumpColor!=3 && Deck.heart10.positionInGame==1 && Deck.heartA.positionInGame%2==1) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==3 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==3 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==3 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==3 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==3 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==3 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==3 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==3 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.heart10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                if (finalChoice==0 && trumpColor!=4 && Deck.diam10.positionInGame==1 && Deck.diamA.positionInGame%2==1) {
                    counter=0;
                    if (backhandPlayer.card1.cardId>32 && playable.card1.color==4 && playable.card1.cardId>4) {counter++;}
                    if (backhandPlayer.card2.cardId>32 && playable.card2.color==4 && playable.card2.cardId>4) {counter++;}
                    if (backhandPlayer.card3.cardId>32 && playable.card3.color==4 && playable.card3.cardId>4) {counter++;}
                    if (backhandPlayer.card4.cardId>32 && playable.card4.color==4 && playable.card4.cardId>4) {counter++;}
                    if (backhandPlayer.card5.cardId>32 && playable.card5.color==4 && playable.card5.cardId>4) {counter++;}
                    if (backhandPlayer.card6.cardId>32 && playable.card6.color==4 && playable.card6.cardId>4) {counter++;}
                    if (backhandPlayer.card7.cardId>32 && playable.card7.color==4 && playable.card7.cardId>4) {counter++;}
                    if (backhandPlayer.card8.cardId>32 && playable.card8.color==4 && playable.card8.cardId>4) {counter++;}
                    if (counter>0) {
                        finalChoice=Deck.diam10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                // #3: Good strategy for an early (or 1st) move: play an ace, if the Player doesn't have the 10 on the backhand and if there are at least 3 more cards of that color in the game
                // (good choice for a 1st turn, but code it such that it would be valid for any number turns)
                if (finalChoice==0) {
                    // count the number of residual cards of colors in question
                    counter=0;
                    counter1=0;
                    counter2=0;
                    counter3=0;
                    counter4=0;
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
                    counter=counter1;
                    if (counter2>counter){counter=counter2;}
                    if (counter3>counter){counter=counter3;}
                    if (counter4>counter){counter=counter4;}
                    if (counter>2){
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable ace");
                        
                        if (counter==counter1){finalChoice=Deck.clubA.positionOnHand;}
                        if (finalChoice!=0 && counter==counter2){finalChoice=Deck.spadeA.positionOnHand;}
                        if (finalChoice!=0 && counter==counter3){finalChoice=Deck.heartA.positionOnHand;}
                        if (finalChoice!=0 && counter==counter4){finalChoice=Deck.diamA.positionOnHand;}
                    }
                }
                // #4: similar to #3 but with 10 instead of ace (but it is so unlikely that the player does not have the ace for sure, go through all cases separately)
                if (finalChoice==0) {
                    if (trumpColor!=1 && Deck.club10.positionInGame==1 && Deck.clubA.positionInGame%2==1) {
                        counter=5; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.clubK.positionInGame%2==1){counter--;}
                        if (Deck.clubQ.positionInGame%2==1){counter--;}
                        if (Deck.club9.positionInGame%2==1){counter--;}
                        if (Deck.club8.positionInGame%2==1){counter--;}
                        if (Deck.club7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.club10.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                    if (trumpColor!=2 && Deck.spade10.positionInGame==1 && Deck.spadeA.positionInGame%2==1) {
                        counter=5;
                        if (Deck.spadeK.positionInGame%2==1){counter--;}
                        if (Deck.spadeQ.positionInGame%2==1){counter--;}
                        if (Deck.spade9.positionInGame%2==1){counter--;}
                        if (Deck.spade8.positionInGame%2==1){counter--;}
                        if (Deck.spade7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.spade10.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                    if (trumpColor!=3 && Deck.heart10.positionInGame==1 && Deck.heartA.positionInGame%2==1) {
                        counter=5;
                        if (Deck.heartK.positionInGame%2==1){counter--;}
                        if (Deck.heartQ.positionInGame%2==1){counter--;}
                        if (Deck.heart9.positionInGame%2==1){counter--;}
                        if (Deck.heart8.positionInGame%2==1){counter--;}
                        if (Deck.heart7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.heart10.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                    if (trumpColor!=4 && Deck.diam10.positionInGame==1 && Deck.diamA.positionInGame%2==1) {
                        counter=5;
                        if (Deck.diamK.positionInGame%2==1){counter--;}
                        if (Deck.diamQ.positionInGame%2==1){counter--;}
                        if (Deck.diam9.positionInGame%2==1){counter--;}
                        if (Deck.diam8.positionInGame%2==1){counter--;}
                        if (Deck.diam7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.diam10.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                }
                // #5: similar to #4 (but with king)
                if (finalChoice==0) {
                    if (trumpColor!=1 && Deck.clubK.positionInGame==1 && Deck.club10.positionInGame%2==1 && Deck.clubA.positionInGame%2==1) {
                        counter=4; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.clubQ.positionInGame%2==1){counter--;}
                        if (Deck.club9.positionInGame%2==1){counter--;}
                        if (Deck.club8.positionInGame%2==1){counter--;}
                        if (Deck.club7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.clubK.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                    if (trumpColor!=2 && Deck.spadeK.positionInGame==1 && Deck.spade10.positionInGame%2==1 && Deck.spadeA.positionInGame%2==1) {
                        counter=4;
                        if (Deck.spadeQ.positionInGame%2==1){counter--;}
                        if (Deck.spade9.positionInGame%2==1){counter--;}
                        if (Deck.spade8.positionInGame%2==1){counter--;}
                        if (Deck.spade7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.spadeK.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                    if (trumpColor!=3 && Deck.heartK.positionInGame==1 && Deck.heart10.positionInGame%2==1 && Deck.heartA.positionInGame%2==1) {
                        counter=4;
                        if (Deck.heartQ.positionInGame%2==1){counter--;}
                        if (Deck.heart9.positionInGame%2==1){counter--;}
                        if (Deck.heart8.positionInGame%2==1){counter--;}
                        if (Deck.heart7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.heartK.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                    if (trumpColor!=4 && Deck.diamK.positionInGame==1 && Deck.diam10.positionInGame%2==1 && Deck.diamA.positionInGame%2==1) {
                        counter=4;
                        if (Deck.diamQ.positionInGame%2==1){counter--;}
                        if (Deck.diam9.positionInGame%2==1){counter--;}
                        if (Deck.diam8.positionInGame%2==1){counter--;}
                        if (Deck.diam7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.diamK.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                }
                // #6: similar to #4 & #5 (but with queen) (it is very unlikely that this happens, but still better than just playing a low card)
                if (finalChoice==0) {
                    if (trumpColor!=1 && Deck.clubQ.positionInGame==1 && Deck.clubK.positionInGame%2==1 && Deck.club10.positionInGame%2==1 && Deck.clubA.positionInGame%2==1) {
                        counter=3; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (Deck.club9.positionInGame%2==1){counter--;}
                        if (Deck.club8.positionInGame%2==1){counter--;}
                        if (Deck.club7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.clubQ.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                    if (trumpColor!=2 && Deck.spadeQ.positionInGame==1 && Deck.spadeK.positionInGame%2==1 && Deck.spade10.positionInGame%2==1 && Deck.spadeA.positionInGame%2==1) {
                        counter=3;
                        if (Deck.spade9.positionInGame%2==1){counter--;}
                        if (Deck.spade8.positionInGame%2==1){counter--;}
                        if (Deck.spade7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.spadeQ.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                    if (trumpColor!=3 && Deck.heartQ.positionInGame==1 && Deck.heartK.positionInGame%2==1 && Deck.heart10.positionInGame%2==1 && Deck.heartA.positionInGame%2==1) {
                        counter=3;
                        if (Deck.heart9.positionInGame%2==1){counter--;}
                        if (Deck.heart8.positionInGame%2==1){counter--;}
                        if (Deck.heart7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.heartQ.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                    if (trumpColor!=4 && Deck.diamQ.positionInGame==1 && Deck.diamK.positionInGame%2==1 && Deck.diam10.positionInGame%2==1 && Deck.diamA.positionInGame%2==1) {
                        counter=3;
                        if (Deck.diam9.positionInGame%2==1){counter--;}
                        if (Deck.diam8.positionInGame%2==1){counter--;}
                        if (Deck.diam7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=Deck.diamQ.positionOnHand;
                            
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                }
                // #7: try to lure out the jacks (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                if (finalChoice==0 && trumpColor==1 && (Deck.club7.positionInGame==1 || Deck.club8.positionInGame==1 || Deck.club9.positionInGame==1) && (Deck.clubQ.positionInGame==1 || Deck.clubK.positionInGame==1 || Deck.club10.positionInGame==1 || Deck.clubA.positionInGame==1 || Deck.clubJ.positionInGame==1 || Deck.spadeJ.positionInGame==1 || Deck.heartJ.positionInGame==1 || Deck.diamJ.positionInGame==1)) {
                    if (Deck.clubA.positionInGame%2==1 && Deck.club10.positionInGame%2==1 && Deck.clubK.positionInGame%2==1 && Deck.clubQ.positionInGame%2==1) {
                        // look here, if player has at least one jack on hand (go through all 8 positions on hand)
                        if ((Deck.clubJ.positionInGame==4 || Deck.spadeJ.positionInGame==4 || Deck.heartJ.positionInGame==4 || Deck.diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (Deck.clubJ.positionOnHand==1 || Deck.spadeJ.positionOnHand==1 || Deck.heartJ.positionOnHand==1 || Deck.diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (Deck.clubJ.positionOnHand==2 || Deck.spadeJ.positionOnHand==2 || Deck.heartJ.positionOnHand==2 || Deck.diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (Deck.clubJ.positionOnHand==3 || Deck.spadeJ.positionOnHand==3 || Deck.heartJ.positionOnHand==3 || Deck.diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (Deck.clubJ.positionOnHand==4 || Deck.spadeJ.positionOnHand==4 || Deck.heartJ.positionOnHand==4 || Deck.diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (Deck.clubJ.positionOnHand==5 || Deck.spadeJ.positionOnHand==5 || Deck.heartJ.positionOnHand==5 || Deck.diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (Deck.clubJ.positionOnHand==6 || Deck.spadeJ.positionOnHand==6 || Deck.heartJ.positionOnHand==6 || Deck.diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (Deck.clubJ.positionOnHand==7 || Deck.spadeJ.positionOnHand==7 || Deck.heartJ.positionOnHand==7 || Deck.diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (Deck.clubJ.positionOnHand==8 || Deck.spadeJ.positionOnHand==8 || Deck.heartJ.positionOnHand==8 || Deck.diamJ.positionOnHand==8)) )) {
                            if (Deck.club7.positionInGame==1) {finalChoice=Deck.club7.positionOnHand;}
                            else if (Deck.club8.positionInGame==1) {finalChoice=Deck.club8.positionOnHand;}
                            else if (Deck.club9.positionInGame==1) {finalChoice=Deck.club9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                if (finalChoice==0 && trumpColor==2 && (Deck.spade7.positionInGame==1 || Deck.spade8.positionInGame==1 || Deck.spade9.positionInGame==1) && (Deck.spadeQ.positionInGame==1 || Deck.spadeK.positionInGame==1 || Deck.spade10.positionInGame==1 || Deck.spadeA.positionInGame==1 || Deck.clubJ.positionInGame==1 || Deck.spadeJ.positionInGame==1 || Deck.heartJ.positionInGame==1 || Deck.diamJ.positionInGame==1)) {
                    if (Deck.spadeA.positionInGame%2==1 && Deck.spade10.positionInGame%2==1 && Deck.spadeK.positionInGame%2==1 && Deck.spadeQ.positionInGame%2==1) {
                        // look here, if player has at leats one jack on hand (go through all 8 positions on hand)
                        if ((Deck.clubJ.positionInGame==4 || Deck.spadeJ.positionInGame==4 || Deck.heartJ.positionInGame==4 || Deck.diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (Deck.clubJ.positionOnHand==1 || Deck.spadeJ.positionOnHand==1 || Deck.heartJ.positionOnHand==1 || Deck.diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (Deck.clubJ.positionOnHand==2 || Deck.spadeJ.positionOnHand==2 || Deck.heartJ.positionOnHand==2 || Deck.diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (Deck.clubJ.positionOnHand==3 || Deck.spadeJ.positionOnHand==3 || Deck.heartJ.positionOnHand==3 || Deck.diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (Deck.clubJ.positionOnHand==4 || Deck.spadeJ.positionOnHand==4 || Deck.heartJ.positionOnHand==4 || Deck.diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (Deck.clubJ.positionOnHand==5 || Deck.spadeJ.positionOnHand==5 || Deck.heartJ.positionOnHand==5 || Deck.diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (Deck.clubJ.positionOnHand==6 || Deck.spadeJ.positionOnHand==6 || Deck.heartJ.positionOnHand==6 || Deck.diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (Deck.clubJ.positionOnHand==7 || Deck.spadeJ.positionOnHand==7 || Deck.heartJ.positionOnHand==7 || Deck.diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (Deck.clubJ.positionOnHand==8 || Deck.spadeJ.positionOnHand==8 || Deck.heartJ.positionOnHand==8 || Deck.diamJ.positionOnHand==8)) )) {
                            if (Deck.spade7.positionInGame==1) {finalChoice=Deck.spade7.positionOnHand;}
                            else if (Deck.spade8.positionInGame==1) {finalChoice=Deck.spade8.positionOnHand;}
                            else if (Deck.spade9.positionInGame==1) {finalChoice=Deck.spade9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                if (finalChoice==0 && trumpColor==3 && (Deck.heart7.positionInGame==1 || Deck.heart8.positionInGame==1 || Deck.heart9.positionInGame==1) && (Deck.heartQ.positionInGame==1 || Deck.heartK.positionInGame==1 || Deck.heart10.positionInGame==1 || Deck.heartA.positionInGame==1 || Deck.clubJ.positionInGame==1 || Deck.spadeJ.positionInGame==1 || Deck.heartJ.positionInGame==1 || Deck.diamJ.positionInGame==1)) {
                    if (Deck.heartA.positionInGame%2==1 && Deck.heart10.positionInGame%2==1 && Deck.heartK.positionInGame%2==1 && Deck.heartQ.positionInGame%2==1) {
                        // look here, if player has at leats one jack on hand (go through all 8 positions on hand)
                        if ((Deck.clubJ.positionInGame==4 || Deck.spadeJ.positionInGame==4 || Deck.heartJ.positionInGame==4 || Deck.diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (Deck.clubJ.positionOnHand==1 || Deck.spadeJ.positionOnHand==1 || Deck.heartJ.positionOnHand==1 || Deck.diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (Deck.clubJ.positionOnHand==2 || Deck.spadeJ.positionOnHand==2 || Deck.heartJ.positionOnHand==2 || Deck.diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (Deck.clubJ.positionOnHand==3 || Deck.spadeJ.positionOnHand==3 || Deck.heartJ.positionOnHand==3 || Deck.diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (Deck.clubJ.positionOnHand==4 || Deck.spadeJ.positionOnHand==4 || Deck.heartJ.positionOnHand==4 || Deck.diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (Deck.clubJ.positionOnHand==5 || Deck.spadeJ.positionOnHand==5 || Deck.heartJ.positionOnHand==5 || Deck.diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (Deck.clubJ.positionOnHand==6 || Deck.spadeJ.positionOnHand==6 || Deck.heartJ.positionOnHand==6 || Deck.diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (Deck.clubJ.positionOnHand==7 || Deck.spadeJ.positionOnHand==7 || Deck.heartJ.positionOnHand==7 || Deck.diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (Deck.clubJ.positionOnHand==8 || Deck.spadeJ.positionOnHand==8 || Deck.heartJ.positionOnHand==8 || Deck.diamJ.positionOnHand==8)) )) {
                            if (Deck.heart7.positionInGame==1) {finalChoice=Deck.heart7.positionOnHand;}
                            else if (Deck.heart8.positionInGame==1) {finalChoice=Deck.heart8.positionOnHand;}
                            else if (Deck.heart9.positionInGame==1) {finalChoice=Deck.heart9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                if (finalChoice==0 && trumpColor==4 && (Deck.diam7.positionInGame==1 || Deck.diam8.positionInGame==1 || Deck.diam9.positionInGame==1) && (Deck.diamQ.positionInGame==1 || Deck.diamK.positionInGame==1 || Deck.diam10.positionInGame==1 || Deck.diamA.positionInGame==1 || Deck.clubJ.positionInGame==1 || Deck.spadeJ.positionInGame==1 || Deck.heartJ.positionInGame==1 || Deck.diamJ.positionInGame==1)) {
                    if (Deck.diamA.positionInGame%2==1 && Deck.diam10.positionInGame%2==1 && Deck.diamK.positionInGame%2==1 && Deck.diamQ.positionInGame%2==1) {
                        // look here, if player has at leats one jack on hand (go through all 8 positions on hand)
                        if ((Deck.clubJ.positionInGame==4 || Deck.spadeJ.positionInGame==4 || Deck.heartJ.positionInGame==4 || Deck.diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (Deck.clubJ.positionOnHand==1 || Deck.spadeJ.positionOnHand==1 || Deck.heartJ.positionOnHand==1 || Deck.diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (Deck.clubJ.positionOnHand==2 || Deck.spadeJ.positionOnHand==2 || Deck.heartJ.positionOnHand==2 || Deck.diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (Deck.clubJ.positionOnHand==3 || Deck.spadeJ.positionOnHand==3 || Deck.heartJ.positionOnHand==3 || Deck.diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (Deck.clubJ.positionOnHand==4 || Deck.spadeJ.positionOnHand==4 || Deck.heartJ.positionOnHand==4 || Deck.diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (Deck.clubJ.positionOnHand==5 || Deck.spadeJ.positionOnHand==5 || Deck.heartJ.positionOnHand==5 || Deck.diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (Deck.clubJ.positionOnHand==6 || Deck.spadeJ.positionOnHand==6 || Deck.heartJ.positionOnHand==6 || Deck.diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (Deck.clubJ.positionOnHand==7 || Deck.spadeJ.positionOnHand==7 || Deck.heartJ.positionOnHand==7 || Deck.diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (Deck.clubJ.positionOnHand==8 || Deck.spadeJ.positionOnHand==8 || Deck.heartJ.positionOnHand==8 || Deck.diamJ.positionOnHand==8)) )) {
                            if (Deck.diam7.positionInGame==1) {finalChoice=Deck.diam7.positionOnHand;}
                            else if (Deck.diam8.positionInGame==1) {finalChoice=Deck.diam8.positionOnHand;}
                            else if (Deck.diam9.positionInGame==1) {finalChoice=Deck.diam9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                // #8: try to play a low non-trump card that the player can not serve
                if (finalChoice==0 && trumpColor!=1) {
                    if ((Deck.club7.positionInGame==1 || Deck.club8.positionInGame==1 || Deck.club9.positionInGame==1) && Deck.club7.positionInGame%2==1 && Deck.club8.positionInGame%2==1 && Deck.club9.positionInGame%2==1 && Deck.clubQ.positionInGame%2==1 && Deck.clubK.positionInGame%2==1 && Deck.club10.positionInGame%2==1 && Deck.clubA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand;}
                        if (finalChoice==0 && Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand;}
                        if (finalChoice==0 && Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand;}
                    }
                }
                if (finalChoice==0 && trumpColor!=2) {
                    if ((Deck.spade7.positionInGame==1 || Deck.spade8.positionInGame==1 || Deck.spade9.positionInGame==1) && Deck.spade7.positionInGame%2==1 && Deck.spade8.positionInGame%2==1 && Deck.spade9.positionInGame%2==1 && Deck.spadeQ.positionInGame%2==1 && Deck.spadeK.positionInGame%2==1 && Deck.spade10.positionInGame%2==1 && Deck.spadeA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand;}
                        if (finalChoice==0 && Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand;}
                        if (finalChoice==0 && Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand;}
                    }
                }
                if (finalChoice==0 && trumpColor!=3) {
                    if ((Deck.heart7.positionInGame==1 || Deck.heart8.positionInGame==1 || Deck.heart9.positionInGame==1) && Deck.heart7.positionInGame%2==1 && Deck.heart8.positionInGame%2==1 && Deck.heart9.positionInGame%2==1 && Deck.heartQ.positionInGame%2==1 && Deck.heartK.positionInGame%2==1 && Deck.heart10.positionInGame%2==1 && Deck.heartA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand;}
                        if (finalChoice==0 && Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand;}
                        if (finalChoice==0 && Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand;}
                    }
                }
                if (finalChoice==0 && trumpColor!=4) {
                    if ((Deck.diam7.positionInGame==1 || Deck.diam8.positionInGame==1 || Deck.diam9.positionInGame==1) && Deck.diam7.positionInGame%2==1 && Deck.diam8.positionInGame%2==1 && Deck.diam9.positionInGame%2==1 && Deck.diamQ.positionInGame%2==1 && Deck.diamK.positionInGame%2==1 && Deck.diam10.positionInGame%2==1 && Deck.diamA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //AIEasy.debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand;}
                        if (finalChoice==0 && Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand;}
                        if (finalChoice==0 && Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand;}
                    }
                }
                // #9: try to play a low card that the player probably can not serve, because could only have maximally 2 others of that color
                if (finalChoice==0 && trumpColor!=1) {
                    if (Deck.club7.positionInGame==1 || Deck.club8.positionInGame==1 || Deck.club9.positionInGame==1) {
                        counter=7;
                        if (Deck.clubA.positionInGame%2==1){counter--;}
                        if (Deck.club10.positionInGame%2==1){counter--;}
                        if (Deck.clubK.positionInGame%2==1){counter--;}
                        if (Deck.clubQ.positionInGame%2==1){counter--;}
                        if (Deck.club9.positionInGame%2==1){counter--;}
                        if (Deck.club8.positionInGame%2==1){counter--;}
                        if (Deck.club7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (Deck.club7.positionInGame==1){finalChoice=Deck.club7.positionOnHand;}
                            if (finalChoice==0 && Deck.club8.positionInGame==1){finalChoice=Deck.club8.positionOnHand;}
                            if (finalChoice==0 && Deck.club9.positionInGame==1){finalChoice=Deck.club9.positionOnHand;}
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=2) {
                    if (Deck.spade7.positionInGame==1 || Deck.spade8.positionInGame==1 || Deck.spade9.positionInGame==1) {
                        counter=7;
                        if (Deck.spadeA.positionInGame%2==1){counter--;}
                        if (Deck.spade10.positionInGame%2==1){counter--;}
                        if (Deck.spadeK.positionInGame%2==1){counter--;}
                        if (Deck.spadeQ.positionInGame%2==1){counter--;}
                        if (Deck.spade9.positionInGame%2==1){counter--;}
                        if (Deck.spade8.positionInGame%2==1){counter--;}
                        if (Deck.spade7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (Deck.spade7.positionInGame==1){finalChoice=Deck.spade7.positionOnHand;}
                            if (finalChoice==0 && Deck.spade8.positionInGame==1){finalChoice=Deck.spade8.positionOnHand;}
                            if (finalChoice==0 && Deck.spade9.positionInGame==1){finalChoice=Deck.spade9.positionOnHand;}
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=3) {
                    if (Deck.heart7.positionInGame==1 || Deck.heart8.positionInGame==1 || Deck.heart9.positionInGame==1) {
                        counter=7;
                        if (Deck.heartA.positionInGame%2==1){counter--;}
                        if (Deck.heart10.positionInGame%2==1){counter--;}
                        if (Deck.heartK.positionInGame%2==1){counter--;}
                        if (Deck.heartQ.positionInGame%2==1){counter--;}
                        if (Deck.heart9.positionInGame%2==1){counter--;}
                        if (Deck.heart8.positionInGame%2==1){counter--;}
                        if (Deck.heart7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (Deck.heart7.positionInGame==1){finalChoice=Deck.heart7.positionOnHand;}
                            if (finalChoice==0 && Deck.heart8.positionInGame==1){finalChoice=Deck.heart8.positionOnHand;}
                            if (finalChoice==0 && Deck.heart9.positionInGame==1){finalChoice=Deck.heart9.positionOnHand;}
                        }
                    }
                }
                if (finalChoice==0 && trumpColor!=4) {
                    if (Deck.diam7.positionInGame==1 || Deck.diam8.positionInGame==1 || Deck.diam9.positionInGame==1) {
                        counter=7;
                        if (Deck.diamA.positionInGame%2==1){counter--;}
                        if (Deck.diam10.positionInGame%2==1){counter--;}
                        if (Deck.diamK.positionInGame%2==1){counter--;}
                        if (Deck.diamQ.positionInGame%2==1){counter--;}
                        if (Deck.diam9.positionInGame%2==1){counter--;}
                        if (Deck.diam8.positionInGame%2==1){counter--;}
                        if (Deck.diam7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //AIEasy.debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (Deck.diam7.positionInGame==1){finalChoice=Deck.diam7.positionOnHand;}
                            if (finalChoice==0 && Deck.diam8.positionInGame==1){finalChoice=Deck.diam8.positionOnHand;}
                            if (finalChoice==0 && Deck.diam9.positionInGame==1){finalChoice=Deck.diam9.positionOnHand;}
                        }
                    }
                }
                // #10: when in doubt, play a low non-trump card (7, 8, 9), maybe even try to play a queen or a king before using random choices 
                if (finalChoice==0) {
                    // This was supposed to be the standard strategy. However it seems that the slightly better strategy "likely unservable low card" happens as likely.
                    //AIEasy.debugStrategyDialog(550, 300, 500, 100, "try to play a low card");
                    
                    if (Deck.diam7.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam7.positionOnHand;}
                    else if (Deck.heart7.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart7.positionOnHand;}
                    else if (Deck.spade7.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade7.positionOnHand;}
                    else if (Deck.club7.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club7.positionOnHand;}
                    else if (Deck.diam8.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam8.positionOnHand;}
                    else if (Deck.heart8.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart8.positionOnHand;}
                    else if (Deck.spade8.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade8.positionOnHand;}
                    else if (Deck.club8.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club8.positionOnHand;}
                    else if (Deck.diam9.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diam9.positionOnHand;}
                    else if (Deck.heart9.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heart9.positionOnHand;}
                    else if (Deck.spade9.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spade9.positionOnHand;}
                    else if (Deck.club9.positionInGame==1 && trumpColor!=1){finalChoice=Deck.club9.positionOnHand;}
                    // discard a semi-valuable card
                    else if (Deck.diamQ.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamQ.positionOnHand;}
                    else if (Deck.heartQ.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartQ.positionOnHand;}
                    else if (Deck.spadeQ.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeQ.positionOnHand;}
                    else if (Deck.clubQ.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubQ.positionOnHand;}
                    else if (Deck.diamK.positionInGame==1 && trumpColor!=4){finalChoice=Deck.diamK.positionOnHand;}
                    else if (Deck.heartK.positionInGame==1 && trumpColor!=3){finalChoice=Deck.heartK.positionOnHand;}
                    else if (Deck.spadeK.positionInGame==1 && trumpColor!=2){finalChoice=Deck.spadeK.positionOnHand;}
                    else if (Deck.clubK.positionInGame==1 && trumpColor!=1){finalChoice=Deck.clubK.positionOnHand;}
                }
                // #11: if everything breaks down, play a random card
                // source code copied from lowest difficulty
                if (finalChoice==0) {
                    // This is a very often used strategy. Especially towards the end.
                    //AIEasy.debugStrategyDialog(550, 300, 500, 100, "play a random card");
                    
                    optionArray[0]=0; optionArray[1]=0; optionArray[2]=0; optionArray[3]=0; optionArray[4]=0; optionArray[5]=0; optionArray[6]=0; optionArray[7]=0;
                    numberOfOptions=0;
                    if (playableCPU.card1.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=1;}
                    if (playableCPU.card2.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=2;}
                    if (playableCPU.card3.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=3;}
                    if (playableCPU.card4.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=4;}
                    if (playableCPU.card5.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=5;}
                    if (playableCPU.card6.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=6;}
                    if (playableCPU.card7.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=7;}
                    if (playableCPU.card8.cardId < 33) {numberOfOptions++; optionArray[numberOfOptions-1]=8;}
                    finalChoice=optionArray[AIEasy.chooseRandomOption(numberOfOptions)-1]; // -1 needed, or else program goes in dead end
                }
                // here the AI finally leaves this method on medium difficulty, when starting to play
                
                //AIEasy.debugStrategyDialog(550, 300, 500, 100, "finally decide on playing a card");
                
                return finalChoice;
             
            
        }
        
    }
    
    
    
    
    
}
