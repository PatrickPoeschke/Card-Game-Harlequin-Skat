/*
 * This class does not create anything.
 * It exists only to have the very long method in it
 * that models the AI for making a decision on hard cpu level.
 * This is done in order to increase the readability of the overall program.
 */
package harlekinskat3;

import static harlekinskat3.Harlekinskat3.cpuReacts;
import static harlekinskat3.Harlekinskat3.currentColor;
import static harlekinskat3.Harlekinskat3.finalChoice;
import static harlekinskat3.Harlekinskat3.trumpPlayed;
import static harlekinskat3.Harlekinskat3.servable;
import static harlekinskat3.Harlekinskat3.trumpcolor;
import static harlekinskat3.Harlekinskat3.counter;
import static harlekinskat3.Harlekinskat3.counter1;
import static harlekinskat3.Harlekinskat3.counter2;
import static harlekinskat3.Harlekinskat3.counter3;
import static harlekinskat3.Harlekinskat3.counter4;
import static harlekinskat3.Harlekinskat3.optionArray;
import static harlekinskat3.Harlekinskat3.numberOfOptions;


public class AIHard {
    // doesn't create anything (rest of class is just one giant method)


    // --- hard difficulty here ---
    
    // my experience with this difficulty level:
    // One can actually rarely win against this opponent, if one is lucky and has actually more trump cards
    // (even if the CPU is choosing trump).
    // Better versions of the good strategies that the normal difficulty CPU and real players want to use
    // actually happen a lot more frequently. This makes this AI-level a challenging opponent.
    // Here it often seems to make a huge difference if the CPU can chosse trump or not.
    // If the player is really unlucky, the CPU might simply have all trump cards.
    // When that happens, the CPU is pretty much invincible.
    
    // All in all the 3 CPU levels are pretty much alright. On lowest one usually wins.
    // On normal it is depending on luck, when one is playing good.
    // On the highest difficulty level the CPU wins significantly more often
    // even when playing very well.
   
    public static int aiLevelHard(Skatcard playedCardPlayer, Hand playableCPU, Hand backhandCPU, Skatcard clubJ, Skatcard spadeJ, Skatcard heartJ, Skatcard diamJ, Skatcard clubA, Skatcard club10, Skatcard clubK, Skatcard clubQ, Skatcard club9, Skatcard club8, Skatcard club7, Skatcard spadeA, Skatcard spade10, Skatcard spadeK, Skatcard spadeQ, Skatcard spade9, Skatcard spade8, Skatcard spade7, Skatcard heartA, Skatcard heart10, Skatcard heartK, Skatcard heartQ, Skatcard heart9, Skatcard heart8, Skatcard heart7, Skatcard diamA, Skatcard diam10, Skatcard diamK, Skatcard diamQ, Skatcard diam9, Skatcard diam8, Skatcard diam7)
    {
        // here the player has played a card and the computer has to react to that
        if (cpuReacts == true){
        
                finalChoice=0;
                // first decide, if one can serve the color or not
                // if yes, try to take home as much points as possible
                // if this is not possible or if one can not serve the color, try to lose as least points as possible (ideally a 9, 8 or 7 such, that the CPU does not have to serve the color any more (also consider the card to turn around when doing so))
                if ((playedCardPlayer.color==trumpcolor) || (playedCardPlayer.cardId < 5)){ // if a jack or other trump has been played
                    trumpPlayed=true;
                    currentColor=trumpcolor;
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
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "can serve trump ace or 10");
                            
                            // depending on what color is trump
                            switch (trumpcolor) {
                                case 1: // if clubs is trump
                                    if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;}
                                    else if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;}
                                    else if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;}
                                    else if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;}
                                    else if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==4 || playedCardPlayer.value==5) { // if it is a trump king or queen
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "can serve trump king or queen");
                            
                            switch (trumpcolor) {
                                case 1: // if clubs is trump
                                    if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==6 || playedCardPlayer.value==7 || playedCardPlayer.value==8) { // if it is a low trump (7, 8, 9)
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "can serve trump low card");
                            
                            switch (trumpcolor) {
                                case 1: // if clubs is trump
                                    if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;}
                                    else if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    else if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;}
                                    else if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    else if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;}
                                    else if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    else if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;}
                                    else if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    else if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.cardId<5) { // if it is a jack
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "can serve a jack");
                            
                            switch (trumpcolor) {
                                case 1: // if clubs is trump
                                    if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;}
                                    else if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    else if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is trump
                                    if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;}
                                    else if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    else if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is trump
                                    if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;}
                                    else if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    else if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is trump
                                    if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand; break;}
                                    else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand; break;}
                                    else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand; break;}
                                    else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;}
                                    else if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    else if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                    }
                    else { // here the CPU can not serve trump => try to lose the least points possible
                        // try to discard a low card (but try to get rid of a whole color and cheat by knowing before if a possible card to turned around is neither of that color)
                        // look if CPU has only one card of a color
                        
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "can't serve trump, try to discard color");
                        
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
                        // if there is only one card of a color and no card of that color is behind it and it is a low card (7, 8, 9), play it
                        if (counter1==1 && trumpcolor!=1) {
                            if (playableCPU.card1.color==1 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==1 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==1 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==1 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==1 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==1 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==1 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==1 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (backhandCPU.card1.cardId==33 || (backhandCPU.card1.cardId<33 && backhandCPU.card1.cardId>4 && backhandCPU.card1.color!=playableCPU.card1.color && playableCPU.card1.value>3)) {
                                    // if no card is behind it or if behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (backhandCPU.card2.cardId==33 || (backhandCPU.card2.cardId<33 && backhandCPU.card2.cardId>4 && backhandCPU.card2.color!=playableCPU.card2.color && playableCPU.card2.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (backhandCPU.card3.cardId==33 || (backhandCPU.card3.cardId<33 && backhandCPU.card3.cardId>4 && backhandCPU.card3.color!=playableCPU.card3.color && playableCPU.card3.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (backhandCPU.card4.cardId==33 || (backhandCPU.card4.cardId<33 && backhandCPU.card4.cardId>4 && backhandCPU.card4.color!=playableCPU.card4.color && playableCPU.card4.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (backhandCPU.card5.cardId==33 || (backhandCPU.card5.cardId<33 && backhandCPU.card5.cardId>4 && backhandCPU.card5.color!=playableCPU.card5.color && playableCPU.card5.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (backhandCPU.card6.cardId==33 || (backhandCPU.card6.cardId<33 && backhandCPU.card6.cardId>4 && backhandCPU.card6.color!=playableCPU.card6.color && playableCPU.card6.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (backhandCPU.card7.cardId==33 || (backhandCPU.card7.cardId<33 && backhandCPU.card7.cardId>4 && backhandCPU.card7.color!=playableCPU.card7.color && playableCPU.card7.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (backhandCPU.card8.cardId==33 || (backhandCPU.card8.cardId<33 && backhandCPU.card8.cardId>4 && backhandCPU.card8.color!=playableCPU.card8.color && playableCPU.card8.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        } // same for other colors
                        if (counter2==1 && trumpcolor!=2) {
                            if (playableCPU.card1.color==2 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==2 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==2 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==2 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==2 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==2 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==2 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==2 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (backhandCPU.card1.cardId==33 || (backhandCPU.card1.cardId<33 && backhandCPU.card1.cardId>4 && backhandCPU.card1.color!=playableCPU.card1.color && playableCPU.card1.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (backhandCPU.card2.cardId==33 || (backhandCPU.card2.cardId<33 && backhandCPU.card2.cardId>4 && backhandCPU.card2.color!=playableCPU.card2.color && playableCPU.card2.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (backhandCPU.card3.cardId==33 || (backhandCPU.card3.cardId<33 && backhandCPU.card3.cardId>4 && backhandCPU.card3.color!=playableCPU.card3.color && playableCPU.card3.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (backhandCPU.card4.cardId==33 || (backhandCPU.card4.cardId<33 && backhandCPU.card4.cardId>4 && backhandCPU.card4.color!=playableCPU.card4.color && playableCPU.card4.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (backhandCPU.card5.cardId==33 || (backhandCPU.card5.cardId<33 && backhandCPU.card5.cardId>4 && backhandCPU.card5.color!=playableCPU.card5.color && playableCPU.card5.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (backhandCPU.card6.cardId==33 || (backhandCPU.card6.cardId<33 && backhandCPU.card6.cardId>4 && backhandCPU.card6.color!=playableCPU.card6.color && playableCPU.card6.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (backhandCPU.card7.cardId==33 || (backhandCPU.card7.cardId<33 && backhandCPU.card7.cardId>4 && backhandCPU.card7.color!=playableCPU.card7.color && playableCPU.card7.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (backhandCPU.card8.cardId==33 || (backhandCPU.card8.cardId<33 && backhandCPU.card8.cardId>4 && backhandCPU.card8.color!=playableCPU.card8.color && playableCPU.card8.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        }
                        if (counter3==1 && trumpcolor!=3) {
                            if (playableCPU.card1.color==3 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==3 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==3 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==3 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==3 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==3 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==3 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==3 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (backhandCPU.card1.cardId==33 || (backhandCPU.card1.cardId<33 && backhandCPU.card1.cardId>4 && backhandCPU.card1.color!=playableCPU.card1.color && playableCPU.card1.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (backhandCPU.card2.cardId==33 || (backhandCPU.card2.cardId<33 && backhandCPU.card2.cardId>4 && backhandCPU.card2.color!=playableCPU.card2.color && playableCPU.card2.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (backhandCPU.card3.cardId==33 || (backhandCPU.card3.cardId<33 && backhandCPU.card3.cardId>4 && backhandCPU.card3.color!=playableCPU.card3.color && playableCPU.card3.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (backhandCPU.card4.cardId==33 || (backhandCPU.card4.cardId<33 && backhandCPU.card4.cardId>4 && backhandCPU.card4.color!=playableCPU.card4.color && playableCPU.card4.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (backhandCPU.card5.cardId==33 || (backhandCPU.card5.cardId<33 && backhandCPU.card5.cardId>4 && backhandCPU.card5.color!=playableCPU.card5.color && playableCPU.card5.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (backhandCPU.card6.cardId==33 || (backhandCPU.card6.cardId<33 && backhandCPU.card6.cardId>4 && backhandCPU.card6.color!=playableCPU.card6.color && playableCPU.card6.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (backhandCPU.card7.cardId==33 || (backhandCPU.card7.cardId<33 && backhandCPU.card7.cardId>4 && backhandCPU.card7.color!=playableCPU.card7.color && playableCPU.card7.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (backhandCPU.card8.cardId==33 || (backhandCPU.card8.cardId<33 && backhandCPU.card8.cardId>4 && backhandCPU.card8.color!=playableCPU.card8.color && playableCPU.card8.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        }
                        if (counter4==1 && trumpcolor!=4) {
                            if (playableCPU.card1.color==4 && playableCPU.card1.cardId>4){counter=1;}
                            if (playableCPU.card2.color==4 && playableCPU.card2.cardId>4){counter=2;}
                            if (playableCPU.card3.color==4 && playableCPU.card3.cardId>4){counter=3;}
                            if (playableCPU.card4.color==4 && playableCPU.card4.cardId>4){counter=4;}
                            if (playableCPU.card5.color==4 && playableCPU.card5.cardId>4){counter=5;}
                            if (playableCPU.card6.color==4 && playableCPU.card6.cardId>4){counter=6;}
                            if (playableCPU.card7.color==4 && playableCPU.card7.cardId>4){counter=7;}
                            if (playableCPU.card8.color==4 && playableCPU.card8.cardId>4){counter=8;}
                            switch (counter) {
                                case 1: if (backhandCPU.card1.cardId==33 || (backhandCPU.card1.cardId<33 && backhandCPU.card1.cardId>4 && backhandCPU.card1.color!=playableCPU.card1.color && playableCPU.card1.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=1;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 2: if (backhandCPU.card2.cardId==33 || (backhandCPU.card2.cardId<33 && backhandCPU.card2.cardId>4 && backhandCPU.card2.color!=playableCPU.card2.color && playableCPU.card2.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=2;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 3: if (backhandCPU.card3.cardId==33 || (backhandCPU.card3.cardId<33 && backhandCPU.card3.cardId>4 && backhandCPU.card3.color!=playableCPU.card3.color && playableCPU.card3.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=3;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 4: if (backhandCPU.card4.cardId==33 || (backhandCPU.card4.cardId<33 && backhandCPU.card4.cardId>4 && backhandCPU.card4.color!=playableCPU.card4.color && playableCPU.card4.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=4;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 5: if (backhandCPU.card5.cardId==33 || (backhandCPU.card5.cardId<33 && backhandCPU.card5.cardId>4 && backhandCPU.card5.color!=playableCPU.card5.color && playableCPU.card5.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=5;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 6: if (backhandCPU.card6.cardId==33 || (backhandCPU.card6.cardId<33 && backhandCPU.card6.cardId>4 && backhandCPU.card6.color!=playableCPU.card6.color && playableCPU.card6.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=6;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 7: if (backhandCPU.card7.cardId==33 || (backhandCPU.card7.cardId<33 && backhandCPU.card7.cardId>4 && backhandCPU.card7.color!=playableCPU.card7.color && playableCPU.card7.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=7;
                                    break;
                                } break; // break needed or else if will go through all cases
                                case 8: if (backhandCPU.card8.cardId==33 || (backhandCPU.card8.cardId<33 && backhandCPU.card8.cardId>4 && backhandCPU.card8.color!=playableCPU.card8.color && playableCPU.card8.value>3)) {
                                    // if no card is behind it or behind the single color card is actually a card and it is not of the same color and a low card
                                    finalChoice=8;
                                    break;
                                } break; // break needed or else if will go through all cases
                            }
                        }
                        
                        // if the above strategy did not work, try to play the non-trump color with the least points
                        // this is a long priority list
                        if (finalChoice==0){
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "discarding a color went wrong, try to discard low card");
                            
                            if (diam7.positionInGame==1 && trumpcolor!=4){finalChoice=diam7.positionOnHand;}
                            else if (heart7.positionInGame==1 && trumpcolor!=3){finalChoice=heart7.positionOnHand;}
                            else if (spade7.positionInGame==1 && trumpcolor!=2){finalChoice=spade7.positionOnHand;}
                            else if (club7.positionInGame==1 && trumpcolor!=1){finalChoice=club7.positionOnHand;}
                            else if (diam8.positionInGame==1 && trumpcolor!=4){finalChoice=diam8.positionOnHand;}
                            else if (heart8.positionInGame==1 && trumpcolor!=3){finalChoice=heart8.positionOnHand;}
                            else if (spade8.positionInGame==1 && trumpcolor!=2){finalChoice=spade8.positionOnHand;}
                            else if (club8.positionInGame==1 && trumpcolor!=1){finalChoice=club8.positionOnHand;}
                            else if (diam9.positionInGame==1 && trumpcolor!=4){finalChoice=diam9.positionOnHand;}
                            else if (heart9.positionInGame==1 && trumpcolor!=3){finalChoice=heart9.positionOnHand;}
                            else if (spade9.positionInGame==1 && trumpcolor!=2){finalChoice=spade9.positionOnHand;}
                            else if (club9.positionInGame==1 && trumpcolor!=1){finalChoice=club9.positionOnHand;}
                            else if (diamQ.positionInGame==1 && trumpcolor!=4){finalChoice=diamQ.positionOnHand;}
                            else if (heartQ.positionInGame==1 && trumpcolor!=3){finalChoice=heartQ.positionOnHand;}
                            else if (spadeQ.positionInGame==1 && trumpcolor!=2){finalChoice=spadeQ.positionOnHand;}
                            else if (clubQ.positionInGame==1 && trumpcolor!=1){finalChoice=clubQ.positionOnHand;}
                            else if (diamK.positionInGame==1 && trumpcolor!=4){finalChoice=diamK.positionOnHand;}
                            else if (heartK.positionInGame==1 && trumpcolor!=3){finalChoice=heartK.positionOnHand;}
                            else if (spadeK.positionInGame==1 && trumpcolor!=2){finalChoice=spadeK.positionOnHand;}
                            else if (clubK.positionInGame==1 && trumpcolor!=1){finalChoice=clubK.positionOnHand;}
                            // if everything else breaks down, unfortunately discard a valuable card
                            else if (diam10.positionInGame==1 && trumpcolor!=4){finalChoice=diam10.positionOnHand;}
                            else if (heart10.positionInGame==1 && trumpcolor!=3){finalChoice=heart10.positionOnHand;}
                            else if (spade10.positionInGame==1 && trumpcolor!=2){finalChoice=spade10.positionOnHand;}
                            else if (club10.positionInGame==1 && trumpcolor!=1){finalChoice=club10.positionOnHand;}
                            else if (diamA.positionInGame==1 && trumpcolor!=4){finalChoice=diamA.positionOnHand;}
                            else if (heartA.positionInGame==1 && trumpcolor!=3){finalChoice=heartA.positionOnHand;}
                            else if (spadeA.positionInGame==1 && trumpcolor!=2){finalChoice=spadeA.positionOnHand;}
                            else if (clubA.positionInGame==1 && trumpcolor!=1){finalChoice=clubA.positionOnHand;}
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
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "answer an ace or 10");
                            
                            switch (currentColor) {
                                case 1: // if clubs is played
                                    if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;}
                                    else if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is played
                                    if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;}
                                    else if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is played
                                    if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;}
                                    else if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is played
                                    if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;} // if it's a 10, get it with the ace (if it's the ace, this case will not happen)
                                    else if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;}
                                    else if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==4 || playedCardPlayer.value==5) { // if it is a king or queen
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "answer a king or queen");
                            
                            switch (currentColor) {
                                case 1: // if clubs is played
                                    if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is played
                                    if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is played
                                    if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is played
                                    if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;} // if it's a queen, get it with the king (no problem: if it was a king, this case will not happen)
                                    else if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                            }
                        }
                        else if (playedCardPlayer.value==6 || playedCardPlayer.value==7 || playedCardPlayer.value==8) { // if it is a low card (7, 8, 9)
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "answer a low card");
                            
                            switch (currentColor) {
                                case 1: // if clubs is played
                                    if (club7.positionInGame==1){finalChoice=club7.positionOnHand; break;}
                                    else if (club8.positionInGame==1){finalChoice=club8.positionOnHand; break;}
                                    else if (club9.positionInGame==1){finalChoice=club9.positionOnHand; break;}
                                    else if (clubQ.positionInGame==1){finalChoice=clubQ.positionOnHand; break;}
                                    else if (clubK.positionInGame==1){finalChoice=clubK.positionOnHand; break;}
                                    else if (club10.positionInGame==1){finalChoice=club10.positionOnHand; break;}
                                    else if (clubA.positionInGame==1){finalChoice=clubA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 2: // if spades is played
                                    if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand; break;}
                                    else if (spade8.positionInGame==1){finalChoice=spade8.positionOnHand; break;}
                                    else if (spade9.positionInGame==1){finalChoice=spade9.positionOnHand; break;}
                                    else if (spadeQ.positionInGame==1){finalChoice=spadeQ.positionOnHand; break;}
                                    else if (spadeK.positionInGame==1){finalChoice=spadeK.positionOnHand; break;}
                                    else if (spade10.positionInGame==1){finalChoice=spade10.positionOnHand; break;}
                                    else if (spadeA.positionInGame==1){finalChoice=spadeA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 3: // if hearts is played
                                    if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand; break;}
                                    else if (heart8.positionInGame==1){finalChoice=heart8.positionOnHand; break;}
                                    else if (heart9.positionInGame==1){finalChoice=heart9.positionOnHand; break;}
                                    else if (heartQ.positionInGame==1){finalChoice=heartQ.positionOnHand; break;}
                                    else if (heartK.positionInGame==1){finalChoice=heartK.positionOnHand; break;}
                                    else if (heart10.positionInGame==1){finalChoice=heart10.positionOnHand; break;}
                                    else if (heartA.positionInGame==1){finalChoice=heartA.positionOnHand; break;}
                                    // another case does not exist! (so here it will definitely be over)
                                case 4: // if diamonds is played
                                    if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand; break;}
                                    else if (diam8.positionInGame==1){finalChoice=diam8.positionOnHand; break;}
                                    else if (diam9.positionInGame==1){finalChoice=diam9.positionOnHand; break;}
                                    else if (diamQ.positionInGame==1){finalChoice=diamQ.positionOnHand; break;}
                                    else if (diamK.positionInGame==1){finalChoice=diamK.positionOnHand; break;}
                                    else if (diam10.positionInGame==1){finalChoice=diam10.positionOnHand; break;}
                                    else if (diamA.positionInGame==1){finalChoice=diamA.positionOnHand; break;}
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
                            // This strategy is good. It happens relatively often.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "answer: try to trump the high card");
                            
                            // try to take it with high-value trump
                            if (clubA.positionInGame==1 && trumpcolor==1){finalChoice=clubA.positionOnHand;}
                            else if (spadeA.positionInGame==1 && trumpcolor==2){finalChoice=spadeA.positionOnHand;}
                            else if (heartA.positionInGame==1 && trumpcolor==3){finalChoice=heartA.positionOnHand;}
                            else if (diamA.positionInGame==1 && trumpcolor==4){finalChoice=diamA.positionOnHand;}
                            else if (club10.positionInGame==1 && trumpcolor==1){finalChoice=club10.positionOnHand;}
                            else if (spade10.positionInGame==1 && trumpcolor==2){finalChoice=spade10.positionOnHand;}
                            else if (heart10.positionInGame==1 && trumpcolor==3){finalChoice=heart10.positionOnHand;}
                            else if (diam10.positionInGame==1 && trumpcolor==4){finalChoice=diam10.positionOnHand;}
                            else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand;}
                            else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand;}
                            else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand;}
                            else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand;}
                            // try to get it with low value trump
                            else if (clubK.positionInGame==1 && trumpcolor==1){finalChoice=clubK.positionOnHand;}
                            else if (spadeK.positionInGame==1 && trumpcolor==2){finalChoice=spadeK.positionOnHand;}
                            else if (heartK.positionInGame==1 && trumpcolor==3){finalChoice=heartK.positionOnHand;}
                            else if (diamK.positionInGame==1 && trumpcolor==4){finalChoice=diamK.positionOnHand;}
                            else if (clubQ.positionInGame==1 && trumpcolor==1){finalChoice=clubQ.positionOnHand;}
                            else if (spadeQ.positionInGame==1 && trumpcolor==2){finalChoice=spadeQ.positionOnHand;}
                            else if (heartQ.positionInGame==1 && trumpcolor==3){finalChoice=heartQ.positionOnHand;}
                            else if (diamQ.positionInGame==1 && trumpcolor==4){finalChoice=diamQ.positionOnHand;}
                            else if (diam7.positionInGame==1 && trumpcolor==4){finalChoice=diam7.positionOnHand;}
                            else if (heart7.positionInGame==1 && trumpcolor==3){finalChoice=heart7.positionOnHand;}
                            else if (spade7.positionInGame==1 && trumpcolor==2){finalChoice=spade7.positionOnHand;}
                            else if (club7.positionInGame==1 && trumpcolor==1){finalChoice=club7.positionOnHand;}
                            else if (diam8.positionInGame==1 && trumpcolor==4){finalChoice=diam8.positionOnHand;}
                            else if (heart8.positionInGame==1 && trumpcolor==3){finalChoice=heart8.positionOnHand;}
                            else if (spade8.positionInGame==1 && trumpcolor==2){finalChoice=spade8.positionOnHand;}
                            else if (club8.positionInGame==1 && trumpcolor==1){finalChoice=club8.positionOnHand;}
                            else if (diam9.positionInGame==1 && trumpcolor==4){finalChoice=diam9.positionOnHand;}
                            else if (heart9.positionInGame==1 && trumpcolor==3){finalChoice=heart9.positionOnHand;}
                            else if (spade9.positionInGame==1 && trumpcolor==2){finalChoice=spade9.positionOnHand;}
                            else if (club9.positionInGame==1 && trumpcolor==1){finalChoice=club9.positionOnHand;}
                            // discard low value card
                            else if (diam7.positionInGame==1 && trumpcolor!=4){finalChoice=diam7.positionOnHand;}
                            else if (heart7.positionInGame==1 && trumpcolor!=3){finalChoice=heart7.positionOnHand;}
                            else if (spade7.positionInGame==1 && trumpcolor!=2){finalChoice=spade7.positionOnHand;}
                            else if (club7.positionInGame==1 && trumpcolor!=1){finalChoice=club7.positionOnHand;}
                            else if (diam8.positionInGame==1 && trumpcolor!=4){finalChoice=diam8.positionOnHand;}
                            else if (heart8.positionInGame==1 && trumpcolor!=3){finalChoice=heart8.positionOnHand;}
                            else if (spade8.positionInGame==1 && trumpcolor!=2){finalChoice=spade8.positionOnHand;}
                            else if (club8.positionInGame==1 && trumpcolor!=1){finalChoice=club8.positionOnHand;}
                            else if (diam9.positionInGame==1 && trumpcolor!=4){finalChoice=diam9.positionOnHand;}
                            else if (heart9.positionInGame==1 && trumpcolor!=3){finalChoice=heart9.positionOnHand;}
                            else if (spade9.positionInGame==1 && trumpcolor!=2){finalChoice=spade9.positionOnHand;}
                            else if (club9.positionInGame==1 && trumpcolor!=1){finalChoice=club9.positionOnHand;}
                            else if (diamQ.positionInGame==1 && trumpcolor!=4){finalChoice=diamQ.positionOnHand;}
                            else if (heartQ.positionInGame==1 && trumpcolor!=3){finalChoice=heartQ.positionOnHand;}
                            else if (spadeQ.positionInGame==1 && trumpcolor!=2){finalChoice=spadeQ.positionOnHand;}
                            else if (clubQ.positionInGame==1 && trumpcolor!=1){finalChoice=clubQ.positionOnHand;}
                            else if (diamK.positionInGame==1 && trumpcolor!=4){finalChoice=diamK.positionOnHand;}
                            else if (heartK.positionInGame==1 && trumpcolor!=3){finalChoice=heartK.positionOnHand;}
                            else if (spadeK.positionInGame==1 && trumpcolor!=2){finalChoice=spadeK.positionOnHand;}
                            else if (clubK.positionInGame==1 && trumpcolor!=1){finalChoice=clubK.positionOnHand;}
                            // if everything else breaks down, unfortunately discard a valuable card
                            else if (diam10.positionInGame==1 && trumpcolor!=4){finalChoice=diam10.positionOnHand;}
                            else if (heart10.positionInGame==1 && trumpcolor!=3){finalChoice=heart10.positionOnHand;}
                            else if (spade10.positionInGame==1 && trumpcolor!=2){finalChoice=spade10.positionOnHand;}
                            else if (club10.positionInGame==1 && trumpcolor!=1){finalChoice=club10.positionOnHand;}
                            else if (diamA.positionInGame==1 && trumpcolor!=4){finalChoice=diamA.positionOnHand;}
                            else if (heartA.positionInGame==1 && trumpcolor!=3){finalChoice=heartA.positionOnHand;}
                            else if (spadeA.positionInGame==1 && trumpcolor!=2){finalChoice=spadeA.positionOnHand;}
                            else if (clubA.positionInGame==1 && trumpcolor!=1){finalChoice=clubA.positionOnHand;}
                            // another case does not exist! (so here it will definitely be over)
                        }
                        else {
                            
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "answer: try to discard a low card to the low card");
                            
                            // discard low value card
                            if (diam7.positionInGame==1 && trumpcolor!=4){finalChoice=diam7.positionOnHand;}
                            else if (heart7.positionInGame==1 && trumpcolor!=3){finalChoice=heart7.positionOnHand;}
                            else if (spade7.positionInGame==1 && trumpcolor!=2){finalChoice=spade7.positionOnHand;}
                            else if (club7.positionInGame==1 && trumpcolor!=1){finalChoice=club7.positionOnHand;}
                            else if (diam8.positionInGame==1 && trumpcolor!=4){finalChoice=diam8.positionOnHand;}
                            else if (heart8.positionInGame==1 && trumpcolor!=3){finalChoice=heart8.positionOnHand;}
                            else if (spade8.positionInGame==1 && trumpcolor!=2){finalChoice=spade8.positionOnHand;}
                            else if (club8.positionInGame==1 && trumpcolor!=1){finalChoice=club8.positionOnHand;}
                            else if (diam9.positionInGame==1 && trumpcolor!=4){finalChoice=diam9.positionOnHand;}
                            else if (heart9.positionInGame==1 && trumpcolor!=3){finalChoice=heart9.positionOnHand;}
                            else if (spade9.positionInGame==1 && trumpcolor!=2){finalChoice=spade9.positionOnHand;}
                            else if (club9.positionInGame==1 && trumpcolor!=1){finalChoice=club9.positionOnHand;}
                            // take it with a semi high trump
                            else if (clubK.positionInGame==1 && trumpcolor==1){finalChoice=clubK.positionOnHand;}
                            else if (spadeK.positionInGame==1 && trumpcolor==2){finalChoice=spadeK.positionOnHand;}
                            else if (heartK.positionInGame==1 && trumpcolor==3){finalChoice=heartK.positionOnHand;}
                            else if (diamK.positionInGame==1 && trumpcolor==4){finalChoice=diamK.positionOnHand;}
                            else if (clubQ.positionInGame==1 && trumpcolor==1){finalChoice=clubQ.positionOnHand;}
                            else if (spadeQ.positionInGame==1 && trumpcolor==2){finalChoice=spadeQ.positionOnHand;}
                            else if (heartQ.positionInGame==1 && trumpcolor==3){finalChoice=heartQ.positionOnHand;}
                            else if (diamQ.positionInGame==1 && trumpcolor==4){finalChoice=diamQ.positionOnHand;}
                            // take it with a high trump
                            else if (club10.positionInGame==1 && trumpcolor==1){finalChoice=club10.positionOnHand;}
                            else if (spade10.positionInGame==1 && trumpcolor==2){finalChoice=spade10.positionOnHand;}
                            else if (heart10.positionInGame==1 && trumpcolor==3){finalChoice=heart10.positionOnHand;}
                            else if (diam10.positionInGame==1 && trumpcolor==4){finalChoice=diam10.positionOnHand;}
                            else if (clubA.positionInGame==1 && trumpcolor==1){finalChoice=clubA.positionOnHand;}
                            else if (spadeA.positionInGame==1 && trumpcolor==2){finalChoice=spadeA.positionOnHand;}
                            else if (heartA.positionInGame==1 && trumpcolor==3){finalChoice=heartA.positionOnHand;}
                            else if (diamA.positionInGame==1 && trumpcolor==4){finalChoice=diamA.positionOnHand;}
                            // discard a semi-valuable card
                            else if (diamQ.positionInGame==1 && trumpcolor!=4){finalChoice=diamQ.positionOnHand;}
                            else if (heartQ.positionInGame==1 && trumpcolor!=3){finalChoice=heartQ.positionOnHand;}
                            else if (spadeQ.positionInGame==1 && trumpcolor!=2){finalChoice=spadeQ.positionOnHand;}
                            else if (clubQ.positionInGame==1 && trumpcolor!=1){finalChoice=clubQ.positionOnHand;}
                            else if (diamK.positionInGame==1 && trumpcolor!=4){finalChoice=diamK.positionOnHand;}
                            else if (heartK.positionInGame==1 && trumpcolor!=3){finalChoice=heartK.positionOnHand;}
                            else if (spadeK.positionInGame==1 && trumpcolor!=2){finalChoice=spadeK.positionOnHand;}
                            else if (clubK.positionInGame==1 && trumpcolor!=1){finalChoice=clubK.positionOnHand;}
                            // take it with a low trump card
                            else if (diam7.positionInGame==1 && trumpcolor==4){finalChoice=diam7.positionOnHand;}
                            else if (heart7.positionInGame==1 && trumpcolor==3){finalChoice=heart7.positionOnHand;}
                            else if (spade7.positionInGame==1 && trumpcolor==2){finalChoice=spade7.positionOnHand;}
                            else if (club7.positionInGame==1 && trumpcolor==1){finalChoice=club7.positionOnHand;}
                            else if (diam8.positionInGame==1 && trumpcolor==4){finalChoice=diam8.positionOnHand;}
                            else if (heart8.positionInGame==1 && trumpcolor==3){finalChoice=heart8.positionOnHand;}
                            else if (spade8.positionInGame==1 && trumpcolor==2){finalChoice=spade8.positionOnHand;}
                            else if (club8.positionInGame==1 && trumpcolor==1){finalChoice=club8.positionOnHand;}
                            else if (diam9.positionInGame==1 && trumpcolor==4){finalChoice=diam9.positionOnHand;}
                            else if (heart9.positionInGame==1 && trumpcolor==3){finalChoice=heart9.positionOnHand;}
                            else if (spade9.positionInGame==1 && trumpcolor==2){finalChoice=spade9.positionOnHand;}
                            else if (club9.positionInGame==1 && trumpcolor==1){finalChoice=club9.positionOnHand;}
                            // take it with a jack
                            else if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand;}
                            else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand;}
                            else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand;}
                            else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand;}
                            // if everything else breaks down, unfortunately discard a valuable card
                            else if (diam10.positionInGame==1 && trumpcolor!=4){finalChoice=diam10.positionOnHand;}
                            else if (heart10.positionInGame==1 && trumpcolor!=3){finalChoice=heart10.positionOnHand;}
                            else if (spade10.positionInGame==1 && trumpcolor!=2){finalChoice=spade10.positionOnHand;}
                            else if (club10.positionInGame==1 && trumpcolor!=1){finalChoice=club10.positionOnHand;}
                            else if (diamA.positionInGame==1 && trumpcolor!=4){finalChoice=diamA.positionOnHand;}
                            else if (heartA.positionInGame==1 && trumpcolor!=3){finalChoice=heartA.positionOnHand;}
                            else if (spadeA.positionInGame==1 && trumpcolor!=2){finalChoice=spadeA.positionOnHand;}
                            else if (clubA.positionInGame==1 && trumpcolor!=1){finalChoice=clubA.positionOnHand;}
                            // another case does not exist! (so here it will definitely be over)
                        }
                    }
                }
                // here the AI finally leaves this method on highest difficulty, when starting to play
                
                //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "answer: finally decide on playing a card");
                
                return finalChoice;
            
        }
        
         // here the computer has to start playing a card
        else {
            
                // priority list:
                // #1: Look if the CPU has a non-trump ace and the player has only the 10 of the same color. ["BEST MOVE"] If yes, play it. (go through all 3 non-trump colors to do that)
                // #2: Look if the CPU has the trump ace and the player has the trump 10 and no other trump card. If yes, play it.
                // #3: Look if the CPU has a jack and the player has only a trump ace or trump 10 or both and no other trump. If yes, play it.
                // #4: Look if the CPU has a non-trump ace and the player can serve. If yes, play it.
                // #5: Look if the CPU has a (non-trump) 10 and the player has to serve a lower card. If yes, play it.
                // #6: Look if the CPU has the trump ace and the player has to serve with a lower trump. If yes, play it.
                // #7: Look if the CPU has a non-trump king or a non-trump queen and the player can serve it and has only lower cards of that color. If yes, play it. (Of all these strategies, one could have eft out the part with the queen.)
                // #8: Look if the CPU has a trump low card (7, 8 or 9) and the player has only jacks as trump. If yes, play it.
                // #9: Look if the CPU has 9, 8 or 7 being not trump. If yes, play it. (this is this "when in doubt"-case)
                //#10: If none of the above happen, play a random existing card. (If everything else breaks down, the CPU does essentially the same as on the lowest difficulty.)
                finalChoice=0; // this is important for look up, if the CPU has already decided on playing a card
                // #1: try to pull off the "best move" (look first, if CPU has an ace and the player has the corresponding 10)
                if (trumpcolor!=1 && clubA.positionInGame==1 && club10.positionInGame==4) {
                    if (club7.positionInGame!=4 && club8.positionInGame!=4 && club9.positionInGame!=4 && clubQ.positionInGame!=4 && clubK.positionInGame!=4) { // only proceed, if the player has no other cards of the same color
                        finalChoice=clubA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=2 && spadeA.positionInGame==1 && spade10.positionInGame==4) {
                    if (spade7.positionInGame!=4 && spade8.positionInGame!=4 && spade9.positionInGame!=4 && spadeQ.positionInGame!=4 && spadeK.positionInGame!=4) { // only proceed, if the player has no other cards of the same color
                        finalChoice=spadeA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=3 && heartA.positionInGame==1 && heart10.positionInGame==4) {
                    if (heart7.positionInGame!=4 && heart8.positionInGame!=4 && heart9.positionInGame!=4 && heartQ.positionInGame!=4 && heartK.positionInGame!=4) { // only proceed, if the player has no other cards of the same color
                        finalChoice=heartA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=4 && diamA.positionInGame==1 && diam10.positionInGame==4) {
                    if (diam7.positionInGame!=4 && diam8.positionInGame!=4 && diam9.positionInGame!=4 && diamQ.positionInGame!=4 && diamK.positionInGame!=4) { // only proceed, if the player has no other cards of the same color
                        finalChoice=diamA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move");
                        
                    }
                }
                // #2: if the best move does not work, try to do the best move with trump
                if (finalChoice==0 && trumpcolor==1 && clubA.positionInGame==1 && club10.positionInGame==4) {
                    if (club7.positionInGame!=4 && club8.positionInGame!=4 && club9.positionInGame!=4 && clubQ.positionInGame!=4 && clubK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) { // only proceed, if the player has no other trump
                        finalChoice=clubA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move with trump");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor==2 && spadeA.positionInGame==1 && spade10.positionInGame==4) {
                    if (spade7.positionInGame!=4 && spade8.positionInGame!=4 && spade9.positionInGame!=4 && spadeQ.positionInGame!=4 && spadeK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) { // only proceed, if the player has no other trump
                        finalChoice=clubA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move with trump");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor==3 && heartA.positionInGame==1 && heart10.positionInGame==4) {
                    if (heart7.positionInGame!=4 && heart8.positionInGame!=4 && heart9.positionInGame!=4 && heartQ.positionInGame!=4 && heartK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) { // only proceed, if the player has no other trump
                        finalChoice=heartA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move with trump");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor==4 && diamA.positionInGame==1 && diam10.positionInGame==4) {
                    if (diam7.positionInGame!=4 && diam8.positionInGame!=4 && diam9.positionInGame!=4 && diamQ.positionInGame!=4 && diamK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) { // only proceed, if the player has no other trump
                        finalChoice=diamA.positionOnHand;
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "pull off best move with trump");
                        
                    }
                }
                // #3: try to get the trump ace or 10 with a jack
                if (finalChoice==0 && (clubJ.positionInGame==1 || spadeJ.positionInGame==1 || heartJ.positionInGame==1 || diamJ.positionInGame==1)) {
                    if (trumpcolor==1 && (clubA.positionInGame==4 || club10.positionInGame==4) && club7.positionInGame!=4 && club8.positionInGame!=4 && club9.positionInGame!=4 && clubQ.positionInGame!=4 && clubK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            // This strategy is good enough. It happened often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "tries to steal trump ace or 10 with a jack");
                            
                            if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand;}
                            else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand;}
                            else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand;}
                            else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand;}
                    }
                    else if (trumpcolor==2 && (spadeA.positionInGame==4 || spade10.positionInGame==4) && spade7.positionInGame!=4 && spade8.positionInGame!=4 && spade9.positionInGame!=4 && spadeQ.positionInGame!=4 && spadeK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            // This strategy is good enough. It happened often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "tries to steal trump ace or 10 with a jack");
                            
                            if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand;}
                            else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand;}
                            else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand;}
                            else if (clubJ.positionInGame==1){ finalChoice=clubJ.positionOnHand;}
                    }
                    if (trumpcolor==3 && (heartA.positionInGame==4 || heart10.positionInGame==4) && heart7.positionInGame!=4 && heart8.positionInGame!=4 && heart9.positionInGame!=4 && heartQ.positionInGame!=4 && heartK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            // This strategy is good enough. It happened often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "tries to steal trump ace or 10 with a jack");
                            
                            if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand;}
                            else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand;}
                            else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand;}
                            else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand;}
                    }
                    if (trumpcolor==4 && (diamA.positionInGame==4 || diam10.positionInGame==4) && diam7.positionInGame!=4 && diam8.positionInGame!=4 && diam9.positionInGame!=4 && diamQ.positionInGame!=4 && diamK.positionInGame!=4 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            // This strategy is good enough. It happened often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "tries to steal trump ace or 10 with a jack");
                            
                            if (diamJ.positionInGame==1){finalChoice=diamJ.positionOnHand;}
                            else if (heartJ.positionInGame==1){finalChoice=heartJ.positionOnHand;}
                            else if (spadeJ.positionInGame==1){finalChoice=spadeJ.positionOnHand;}
                            else if (clubJ.positionInGame==1){finalChoice=clubJ.positionOnHand;}
                    }
                }
                // #4: look if player has to serve a non-trump ace
                if (finalChoice==0) {
                    if (trumpcolor==1) {
                        if (spadeA.positionInGame==1 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4 || spadeK.positionInGame==4)) {
                            finalChoice=spadeA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && heartA.positionInGame==1 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4 || heartK.positionInGame==4)) {
                            finalChoice=heartA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && diamA.positionInGame==1 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4 || diamK.positionInGame==4)) {
                            finalChoice=diamA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }     
                    }
                    else if (trumpcolor==2) {
                        if (clubA.positionInGame==1 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4 || clubK.positionInGame==4)) {
                            finalChoice=clubA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && heartA.positionInGame==1 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4 || heartK.positionInGame==4)) {
                            finalChoice=heartA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && diamA.positionInGame==1 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4 || diamK.positionInGame==4)) {
                            finalChoice=diamA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }  
                    }
                    else if (trumpcolor==3) {
                        if (clubA.positionInGame==1 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4 || clubK.positionInGame==4)) {
                            finalChoice=clubA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && heartA.positionInGame==1 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4 || heartK.positionInGame==4)) {
                            finalChoice=heartA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && diamA.positionInGame==1 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4 || diamK.positionInGame==4)) {
                            finalChoice=diamA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                    }
                    else if  (trumpcolor==4) {
                        if (clubA.positionInGame==1 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4 || clubK.positionInGame==4)) {
                            finalChoice=clubA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && spadeA.positionInGame==1 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4 || spadeK.positionInGame==4)) {
                            finalChoice=spadeA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                        if (finalChoice==0 && heartA.positionInGame==1 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4 || heartK.positionInGame==4)) {
                            finalChoice=heartA.positionOnHand;
                            // This is the de facto best strategy on this difficulty level, since it happens a lot more often then the actual "best move". This strategy happens very often!
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable non-trump ace");
                            
                        }
                    }
                }
                // #5: try to play a (non-trump) 10, such that the player has to serve a lower card
                if (finalChoice==0 && club10.positionInGame==1 && clubA.positionInGame!=4 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4 || clubK.positionInGame==4)) {
                    if (trumpcolor==1) { // in case it is trump color, only proceed, if the player has no jacks
                        if (clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            finalChoice=club10.positionOnHand;
                            // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                        }
                    }
                    else {
                        finalChoice=club10.positionOnHand;
                        // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                    }
                }
                if (finalChoice==0 && spade10.positionInGame==1 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4 || spadeK.positionInGame==4)) {
                    if (trumpcolor==2) { // in case it is trump color, only proceed, if the player has no jacks
                        if (clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            finalChoice=spade10.positionOnHand;
                            // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                        }
                    }
                    else {
                        finalChoice=spade10.positionOnHand;
                        // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                    }
                }
                if (finalChoice==0 && heart10.positionInGame==1 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4 || heartK.positionInGame==4)) {
                    if (trumpcolor==3) { // in case it is trump color, only proceed, if the player has no jacks
                        if (clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            finalChoice=heart10.positionOnHand;
                            // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                        }
                    }
                    else {
                        finalChoice=heart10.positionOnHand;
                        // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                    }
                }
                if (finalChoice==0 && diam10.positionInGame==1 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4 || diamK.positionInGame==4)) {
                    if (trumpcolor==4) { // in case it is trump color, only proceed, if the player has no jacks
                        if (clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4) {
                            finalChoice=diam10.positionOnHand;
                            // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                        }
                    }
                    else {
                        finalChoice=diam10.positionOnHand;
                        // This is the de facto 2nd best strategy on this difficulty level. It happens quite often.
                        //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable 10");
                            
                    }
                }
                // #6: bring the own trump ace home, if the player has to serve it with a lower trump card
                if (finalChoice==0) {
                    if (trumpcolor==1) {
                        if (clubA.positionInGame==1 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4  && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4 || clubK.positionInGame==4)) {
                            finalChoice=clubA.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable trump ace");
                            
                        } 
                    }
                    else if (trumpcolor==2) {
                        if (spadeA.positionInGame==1 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4  && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4 || spadeK.positionInGame==4)) {
                            finalChoice=spadeA.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable trump ace");
                            
                        }
                    }
                    else if (trumpcolor==3) {
                        if (heartA.positionInGame==1 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4  && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4 || heartK.positionInGame==4)) {
                            finalChoice=heartA.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable trump ace");
                            
                        }
                    }
                    else if (trumpcolor==4) {
                        if (diamA.positionInGame==1 && clubJ.positionInGame!=4 && spadeJ.positionInGame!=4 && heartJ.positionInGame!=4 && diamJ.positionInGame!=4  && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4 || diamK.positionInGame==4)) {
                            finalChoice=diamA.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable trump ace");
                            
                        }
                    }
                }
                // #7.1 bring home a non-trump king, if the player has to serve it with a lower card
                if (finalChoice==0) {
                    if (trumpcolor==1) {
                        if (spadeK.positionInGame==1 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4)) {
                            finalChoice=spadeK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (heartK.positionInGame==1 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4)) {
                            finalChoice=heartK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (diamK.positionInGame==1 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4)) {
                            finalChoice=diamK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                    }
                    else if (trumpcolor==2) {
                        if (clubK.positionInGame==1 && club10.positionInGame!=4 && clubA.positionInGame!=4 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4)) {
                            finalChoice=clubK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (heartK.positionInGame==1 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4)) {
                            finalChoice=heartK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (diamK.positionInGame==1 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4)) {
                            finalChoice=diamK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                    }
                    else if (trumpcolor==3) {
                        if (spadeK.positionInGame==1 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4)) {
                            finalChoice=spadeK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (heartK.positionInGame==1 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4)) {
                            finalChoice=heartK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (diamK.positionInGame==1 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4 || diamQ.positionInGame==4)) {
                            finalChoice=diamK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                    }
                    else if (trumpcolor==4) {
                        if (clubK.positionInGame==1 && club10.positionInGame!=4 && clubA.positionInGame!=4 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4 || clubQ.positionInGame==4)) {
                            finalChoice=clubK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (spadeK.positionInGame==1 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4 || spadeQ.positionInGame==4)) {
                            finalChoice=spadeK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                        else if (heartK.positionInGame==1 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4 || heartQ.positionInGame==4)) {
                            finalChoice=heartK.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable king");
                            
                        }
                    }
                }
                // #7.2 bring home a non-trump queen, if the player has to serve it with a lower card
                if (finalChoice==0) {
                    if (trumpcolor==1) {
                        if (spadeQ.positionInGame==1 && spadeK.positionInGame!=4 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4)) {
                            finalChoice=spadeQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (heartQ.positionInGame==1 && heartK.positionInGame!=4 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4)) {
                            finalChoice=heartQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (diamQ.positionInGame==1 && diamK.positionInGame!=4 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4)) {
                            finalChoice=diamQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                    }
                    else if (trumpcolor==2) {
                        if (clubQ.positionInGame==1 && clubK.positionInGame!=4 && club10.positionInGame!=4 && clubA.positionInGame!=4 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4)) {
                            finalChoice=clubQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (heartQ.positionInGame==1 && heartK.positionInGame!=4 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4)) {
                            finalChoice=heartQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (diamQ.positionInGame==1 && diamK.positionInGame!=4 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4)) {
                            finalChoice=diamQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                    }
                    else if (trumpcolor==3) {
                        if (spadeQ.positionInGame==1&& spadeK.positionInGame!=4 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4)) {
                            finalChoice=spadeQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (heartQ.positionInGame==1 && heartK.positionInGame!=4 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4)) {
                            finalChoice=heartQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (diamQ.positionInGame==1 && diamK.positionInGame!=4 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diam7.positionInGame==4 || diam8.positionInGame==4 || diam9.positionInGame==4)) {
                            finalChoice=diamQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                    }
                    else if (trumpcolor==4) {
                        if (clubQ.positionInGame==1 && clubK.positionInGame!=4 && club10.positionInGame!=4 && clubA.positionInGame!=4 && (club7.positionInGame==4 || club8.positionInGame==4 || club9.positionInGame==4)) {
                                finalChoice=clubQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (spadeQ.positionInGame==1&& spadeK.positionInGame!=4 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (spade7.positionInGame==4 || spade8.positionInGame==4 || spade9.positionInGame==4)) {
                            finalChoice=spadeQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                        else if (heartQ.positionInGame==1 && heartK.positionInGame!=4 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (heart7.positionInGame==4 || heart8.positionInGame==4 || heart9.positionInGame==4)) {
                            finalChoice=heartQ.positionOnHand;
                            // This is a good strategy. It happens often enough.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "servable queen");
                            
                        }
                    }
                }
                // #8: try to lure out the player's jacks with trump low cards
                if (finalChoice==0) {
                    if (trumpcolor==1) {
                        if ((club7.positionInGame==1 || club8.positionInGame==1 || club9.positionInGame==1) && clubQ.positionInGame!=4 && clubK.positionInGame!=4 && club10.positionInGame!=4 && clubA.positionInGame!=4 && (diamJ.positionInGame==4 || heartJ.positionInGame==4 || spadeJ.positionInGame==4 || clubJ.positionInGame==4)) {
                            if (club7.positionInGame==1){finalChoice=club7.positionOnHand;}
                            if (finalChoice==0 && club8.positionInGame==1){finalChoice=club8.positionOnHand;}
                            if (finalChoice==0 && club9.positionInGame==1){finalChoice=club9.positionOnHand;}
                            // This is a good strategy. It happens relatively often and is completing the usual aggressive techniques quite well.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                    else if (trumpcolor==2) {
                        if ((spade7.positionInGame==1 || spade8.positionInGame==1 || spade9.positionInGame==1) && spadeQ.positionInGame!=4 && spadeK.positionInGame!=4 && spade10.positionInGame!=4 && spadeA.positionInGame!=4 && (diamJ.positionInGame==4 || heartJ.positionInGame==4 || spadeJ.positionInGame==4 || clubJ.positionInGame==4)) {
                            if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand;}
                            if (finalChoice==0 && spade8.positionInGame==1){finalChoice=spade8.positionOnHand;}
                            if (finalChoice==0 && spade9.positionInGame==1){finalChoice=spade9.positionOnHand;}
                            // This is a good strategy. It happens relatively often and is completing the usual aggressive techniques quite well.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                    else if (trumpcolor==3) {
                        if ((heart7.positionInGame==1 || heart8.positionInGame==1 || heart9.positionInGame==1) && heartQ.positionInGame!=4 && heartK.positionInGame!=4 && heart10.positionInGame!=4 && heartA.positionInGame!=4 && (diamJ.positionInGame==4 || heartJ.positionInGame==4 || spadeJ.positionInGame==4 || clubJ.positionInGame==4)) {
                            if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand;}
                            if (finalChoice==0 && heart8.positionInGame==1){finalChoice=heart8.positionOnHand;}
                            if (finalChoice==0 && heart9.positionInGame==1){finalChoice=heart9.positionOnHand;}
                            // This is a good strategy. It happens relatively often and is completing the usual aggressive techniques quite well.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                    else if (trumpcolor==4) {
                        if ((diam7.positionInGame==1 || diam8.positionInGame==1 || diam9.positionInGame==1) && diamQ.positionInGame!=4 && diamK.positionInGame!=4 && diam10.positionInGame!=4 && diamA.positionInGame!=4 && (diamJ.positionInGame==4 || heartJ.positionInGame==4 || spadeJ.positionInGame==4 || clubJ.positionInGame==4)) {
                            if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand;}
                            if (finalChoice==0 && diam8.positionInGame==1){finalChoice=diam8.positionOnHand;}
                            if (finalChoice==0 && diam9.positionInGame==1){finalChoice=diam9.positionOnHand;}
                            // This is a good strategy. It happens relatively often and is completing the usual aggressive techniques quite well.
                            //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                // #9: when in doubt, play a low non-trump card (7, 8, 9), maybe even try to play a queen or a king before using random choices 
                if (finalChoice==0) {
                    // This is pretty much the standard strategy. It happens most often.
                    //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "try to play a low card");
                    
                    if (diam7.positionInGame==1 && trumpcolor!=4){finalChoice=diam7.positionOnHand;}
                    else if (heart7.positionInGame==1 && trumpcolor!=3){finalChoice=heart7.positionOnHand;}
                    else if (spade7.positionInGame==1 && trumpcolor!=2){finalChoice=spade7.positionOnHand;}
                    else if (club7.positionInGame==1 && trumpcolor!=1){finalChoice=club7.positionOnHand;}
                    else if (diam8.positionInGame==1 && trumpcolor!=4){finalChoice=diam8.positionOnHand;}
                    else if (heart8.positionInGame==1 && trumpcolor!=3){finalChoice=heart8.positionOnHand;}
                    else if (spade8.positionInGame==1 && trumpcolor!=2){finalChoice=spade8.positionOnHand;}
                    else if (club8.positionInGame==1 && trumpcolor!=1){finalChoice=club8.positionOnHand;}
                    else if (diam9.positionInGame==1 && trumpcolor!=4){finalChoice=diam9.positionOnHand;}
                    else if (heart9.positionInGame==1 && trumpcolor!=3){finalChoice=heart9.positionOnHand;}
                    else if (spade9.positionInGame==1 && trumpcolor!=2){finalChoice=spade9.positionOnHand;}
                    else if (club9.positionInGame==1 && trumpcolor!=1){finalChoice=club9.positionOnHand;}
                    // a semi-valuable card
                    else if (diamQ.positionInGame==1 && trumpcolor!=4){finalChoice=diamQ.positionOnHand;}
                    else if (heartQ.positionInGame==1 && trumpcolor!=3){finalChoice=heartQ.positionOnHand;}
                    else if (spadeQ.positionInGame==1 && trumpcolor!=2){finalChoice=spadeQ.positionOnHand;}
                    else if (clubQ.positionInGame==1 && trumpcolor!=1){finalChoice=clubQ.positionOnHand;}
                    else if (diamK.positionInGame==1 && trumpcolor!=4){finalChoice=diamK.positionOnHand;}
                    else if (heartK.positionInGame==1 && trumpcolor!=3){finalChoice=heartK.positionOnHand;}
                    else if (spadeK.positionInGame==1 && trumpcolor!=2){finalChoice=spadeK.positionOnHand;}
                    else if (clubK.positionInGame==1 && trumpcolor!=1){finalChoice=clubK.positionOnHand;}
                }
                // #10: if everything breaks down, play a random card
                // source code cpied from lowest difficulty
                if (finalChoice==0) {
                    // This is unlikely, but still needed. This typically happens towards the end of the game.
                    //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "play a random card");
                    
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
                    int option=optionArray[AIEasyNormal.chooseRandomOption(numberOfOptions)-1]; // -1 needed, or else program goes in dead end
                    finalChoice=option;
                }
                // here the AI finally leaves this method on highest difficulty, when starting to play
                
                //AIEasyNormal.debugStrategyDialog(550, 300, 500, 100, "finally decide on playing a card");
                
                return finalChoice;
            
        }
        
    }
    

}
