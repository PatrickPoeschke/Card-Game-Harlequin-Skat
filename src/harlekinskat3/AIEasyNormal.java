/*
 * This class does not create anything.
 * It exists only to have the very long method in it
 * that models the AI for making a decision on normal cpu level.
 * Also it has the relatively short method for cpu level easy at the beginning,
 * which often uses a method that makes a random choise (also contained in this class).
 * Furthermore, it contain a short message to shorten
 * the debug dialogs for displaying the current strategy to single lines.
 * This is done in order to increase the readability of the overall program.
 */
package harlekinskat3;

import static harlekinskat3.Harlekinskat3.couldntServeClubsBefore;
import static harlekinskat3.Harlekinskat3.couldntServeDiamondsBefore;
import static harlekinskat3.Harlekinskat3.couldntServeHeartsBefore;
import static harlekinskat3.Harlekinskat3.couldntServeSpadesBefore;
import static harlekinskat3.Harlekinskat3.couldntServeTrumpBefore;
import static harlekinskat3.Harlekinskat3.counter;
import static harlekinskat3.Harlekinskat3.counter1;
import static harlekinskat3.Harlekinskat3.counter2;
import static harlekinskat3.Harlekinskat3.counter3;
import static harlekinskat3.Harlekinskat3.counter4;
import static harlekinskat3.Harlekinskat3.cpuReacts;
import static harlekinskat3.Harlekinskat3.currentColor;
import static harlekinskat3.Harlekinskat3.finalChoice;
import static harlekinskat3.Harlekinskat3.numberOfClubCardsSinceThen;
import static harlekinskat3.Harlekinskat3.numberOfDiamondCardsSinceThen;
import static harlekinskat3.Harlekinskat3.numberOfHeartCardsSinceThen;
import static harlekinskat3.Harlekinskat3.numberOfOptions;
import static harlekinskat3.Harlekinskat3.numberOfSpadeCardsSinceThen;
import static harlekinskat3.Harlekinskat3.numberOfTrumpCardsSinceThen;
import static harlekinskat3.Harlekinskat3.optionArray;
import static harlekinskat3.Harlekinskat3.servable;
import static harlekinskat3.Harlekinskat3.trumpPlayed;
import static harlekinskat3.Harlekinskat3.trumpcolor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class AIEasyNormal {
    // doesn't create anything (rest is just 2 short methods and one giant method)
    
    
    // method for choosing one random option out n given ones
    // usefull for coin flips, randomly choosing a trump color, (dice rolls)
    // or for letting the computer play a random card out a given number of allowed ones
    public static int chooseRandomOption(int numberOfOptions){
        // creates an array with the first n intergers
        // shuffles it
        // and returns the first element of that array
        Integer[] options = new Integer[numberOfOptions];
        for (int index = 1; index <= numberOfOptions; index++){
            options[index-1]=index;
        }
        List<Integer> optionsaslist = Arrays.asList(options);
        Collections.shuffle(optionsaslist);
        optionsaslist.toArray(options);
        return options[0];
    }
    
    // method used for making dialogs that tell the current strategy of cpu
    // (for debugging) to be able to write dialog commands as single lines
    public static void debugStrategyDialog(int x, int y, int width, int height, String dialogText){
        JDialog decideJDialog = new JDialog();
        decideJDialog.setBounds(x, y, width, height);
        decideJDialog.add(new JLabel(" " + dialogText));
        decideJDialog.setModal(true);
        decideJDialog.setVisible(true);
        // use like: debugStrategyDialog(550, 300, 500, 100, "text");
    }
    
    // --- lowest difficulty level here ---
    
    // my experience with this difficulty level:
    // As expected, this difficulty level is just something for beginners.
    // By playing random cards the CPU will make extremely bad moves from time to time.
    // These moves include, discarding an ace or 10 without reason or taking a low card with a jack.
    // No ordinary player would make such bad moves.
    // If these moves happen twice or three times in one game,
    // that can already decide the game.
    // On the bright side, this difficulty level is already so weak
    // that one does not need to program a cheating CPU that tries to make the worst moves on purpose.
    
    public static int aiLevelEasy(Skatcard playedCardPlayer, Hand playableCPU){
        // here the player has played a card and the computer reacts
        if (cpuReacts == true){
                // just count the number of allowed existing cards on the hand of the CPU
                // take a random one of them
                // and play it
                optionArray[0]=0; optionArray[1]=0; optionArray[2]=0; optionArray[3]=0; optionArray[4]=0; optionArray[5]=0; optionArray[6]=0; optionArray[7]=0;
                numberOfOptions=0;
                // first decide what color the player has played
                if ((playedCardPlayer.color==trumpcolor) || (playedCardPlayer.cardId < 5)){ // if a jack or other trump has been played
                    trumpPlayed=true;
                    currentColor=trumpcolor;
                    // go through all cards and look if it is also a trump card
                    if ((playableCPU.card1.color==currentColor && playableCPU.card1.cardId > 4) || playableCPU.card1.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=1;
                    }
                    if ((playableCPU.card2.color==currentColor && playableCPU.card2.cardId > 4) || playableCPU.card2.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=2;
                    }
                    if ((playableCPU.card3.color==currentColor && playableCPU.card3.cardId > 4) || playableCPU.card3.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=3;
                    }
                    if ((playableCPU.card4.color==currentColor && playableCPU.card4.cardId > 4) || playableCPU.card4.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=4;
                    }
                    if ((playableCPU.card5.color==currentColor && playableCPU.card5.cardId > 4) || playableCPU.card5.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=5;
                    }
                    if ((playableCPU.card6.color==currentColor && playableCPU.card6.cardId > 4) || playableCPU.card6.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=6;
                    }
                    if ((playableCPU.card7.color==currentColor && playableCPU.card7.cardId > 4) || playableCPU.card7.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=7;
                    }
                    if ((playableCPU.card8.color==currentColor && playableCPU.card8.cardId > 4) || playableCPU.card8.cardId < 5){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=8;
                    }
                }
                else {
                    trumpPlayed=false;
                    currentColor=playedCardPlayer.color;
                    // go through all cards and look if it is a card of the same "color"
                    if (playableCPU.card1.color==currentColor && playableCPU.card1.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=1;
                    }
                    if (playableCPU.card2.color==currentColor && playableCPU.card2.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=2;
                    }
                    if (playableCPU.card3.color==currentColor && playableCPU.card3.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=3;
                    }
                    if (playableCPU.card4.color==currentColor && playableCPU.card4.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=4;
                    }
                    if (playableCPU.card5.color==currentColor && playableCPU.card5.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=5;
                    }
                    if (playableCPU.card6.color==currentColor && playableCPU.card6.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=6;
                    }
                    if (playableCPU.card7.color==currentColor && playableCPU.card7.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=7;
                    }
                    if (playableCPU.card8.color==currentColor && playableCPU.card8.cardId > 4){
                        numberOfOptions++;
                        optionArray[numberOfOptions-1]=8;
                    }
                }
                // after checking the options, play a valid or if possible
                // otherwise just play any existing card
                if (numberOfOptions>0){
                    finalChoice=optionArray[chooseRandomOption(numberOfOptions)-1]; // -1 needed, or else program goes in dead end
                    
                    //debugStrategyDialog(550, 300, 500, 100, "Computer decides for card " + finalChoice + " and can serve.");
                    
                    return finalChoice;
                }
                else { // go again through all cards, count the existing ones and choose one randomly
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
                    finalChoice=optionArray[chooseRandomOption(numberOfOptions)-1]; // -1 needed, or else program goes in dead end
                    
                    //debugStrategyDialog(550, 300, 500, 100, "Computer decides for card " + finalChoice + ", because it can not serve.");
                    
                    return finalChoice;
                }
        }
        else { // here the CPU begins with playing a card
                // just count the number of existing cards on the hand of the CPU
                // take a random one of them
                // and play it
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
                finalChoice=optionArray[chooseRandomOption(numberOfOptions)-1]; // -1 needed, or else program goes in dead end
                
                //debugStrategyDialog(550, 300, 500, 100, "Computer starts with card " + finalChoice + ".");
                
                return finalChoice;
        }
    }
    
    
    
    // --- medium difficulty here ---
    
    // my experience with this difficulty level:
    // The CPU responds almost as well as on the highest difficulty level.
    // So in that respect it does not make much difference.
    // This opponent is way better in reacting than in acting.
    // When starting to play a card,
    // the CPU on this level usually has not enough information to make a really good move.
    // So typically the CPU here does the same that I usually do:
    // playing a low non-trump card (7, 8 or 9).
    // Also the CPU seems to choose the trump color relatively wisely
    // such that it has at least 2, but usually at least 3 trump card on the starting hand.
    // That shows that even with perfect memory, the rule-of-thumb-heuristics are not better than for human players.
    // This is somehow a bit disappointing.
    // However, this also actually simulates a typical human player very well!
    
    // So the actual difference to a human player is
    // that on one hand the CPU remembers perfectly,
    // however on the other hand does not go through all possible moves in the last few turns.
    // This seems to somehow balance out very well.
    
    public static int aiLevelNormal(Skatcard playedCardPlayer, Hand playableCPU, Hand backhandPlayer, Hand playable, Skatcard clubJ, Skatcard spadeJ, Skatcard heartJ, Skatcard diamJ, Skatcard clubA, Skatcard club10, Skatcard clubK, Skatcard clubQ, Skatcard club9, Skatcard club8, Skatcard club7, Skatcard spadeA, Skatcard spade10, Skatcard spadeK, Skatcard spadeQ, Skatcard spade9, Skatcard spade8, Skatcard spade7, Skatcard heartA, Skatcard heart10, Skatcard heartK, Skatcard heartQ, Skatcard heart9, Skatcard heart8, Skatcard heart7, Skatcard diamA, Skatcard diam10, Skatcard diamK, Skatcard diamQ, Skatcard diam9, Skatcard diam8, Skatcard diam7)
    {
        // here the player has played a card and the computer reacts
        if (cpuReacts == true){
        
                // same as difficulty 3 just without cheating, i.e. leave out all conditions containing backhandCPU
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "can serve trump ace or 10");
                    
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "can serve trump king or queen");
                    
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "can serve trump low card");
                    
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "can serve a jack");
                    
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
                        // try to discard a low card (but try to get rid of a whole color)
                        // look if CPU has only one card of a color
                        
                        //debugStrategyDialog(550, 300, 500, 100, "can't serve trump, try to discard color");
                        
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "discarding a color went wrong, try to discard low card");
                            
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "answer an ace or 10");
                            
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "answer a king or queen");
                            
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "answer a low card");
                            
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
                            // This strategy is good enough. It happened often enough.
                            //debugStrategyDialog(550, 300, 500, 100, "answer: try to trump the high card");
                            
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
                            
                            //debugStrategyDialog(550, 300, 500, 100, "answer: try to discard a low card to the low card");
                            
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
                // here the AI finally leaves this method on medium difficulty, when starting to play
                
                //debugStrategyDialog(550, 300, 500, 100, "answer: finally decide on playing a card");
                
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
                if (trumpcolor!=1){
                    if (couldntServeClubsBefore==true && numberOfClubCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (clubA.positionInGame==1 && club10.positionInGame==4 && ((club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=clubA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=2) {
                    if (couldntServeSpadesBefore==true && numberOfSpadeCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (spadeA.positionInGame==1 && spade10.positionInGame==4 && ((spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=spadeA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=3) {
                    if (couldntServeHeartsBefore==true && numberOfHeartCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (heartA.positionInGame==1 && heart10.positionInGame==4 && ((heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=heartA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=4) {
                    if (couldntServeDiamondsBefore==true && numberOfDiamondCardsSinceThen==1) {
                        // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                        //debugStrategyDialog(550, 300, 500, 100, "try to pull off best move");
                        
                        if (diamA.positionInGame==1 && diam10.positionInGame==4 && ((diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=diamA.positionOnHand;
                        }
                    }
                }
                // 1.2 Try to pull off best move, by counting the visible cards (good for making it possible in the first few turns!)
                if (finalChoice==0 && trumpcolor!=1){
                    if (clubA.positionInGame==1 && club10.positionInGame==4 && ((club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (clubK.positionInGame%2==1 && clubQ.positionInGame%2==1 && club9.positionInGame%2==1 && club8.positionInGame%2==1 && club7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=clubA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=2){
                    if (spadeA.positionInGame==1 && spade10.positionInGame==4 && ((spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (spadeK.positionInGame%2==1 && spadeQ.positionInGame%2==1 && spade9.positionInGame%2==1 && spade8.positionInGame%2==1 && spade7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=spadeA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=3){
                    if (heartA.positionInGame==1 && heart10.positionInGame==4 && ((heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (heartK.positionInGame%2==1 && heartQ.positionInGame%2==1 && heart9.positionInGame%2==1 && heart8.positionInGame%2==1 && heart7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=heartA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=4){
                    if (diamA.positionInGame==1 && diam10.positionInGame==4 && ((diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                         // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (diamK.positionInGame%2==1 && diamQ.positionInGame%2==1 && diam9.positionInGame%2==1 && diam8.positionInGame%2==1 && diam7.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "pull off best move by counting visible cards"); 
                        
                            finalChoice=diamA.positionOnHand;
                        }
                    }
                }
                // 1.3 now best move with trump
                if (finalChoice==0 && couldntServeTrumpBefore==true && numberOfTrumpCardsSinceThen==1){
                    // This strategy is very unlikely to happen. But when it happens, it might decide the game.
                    //debugStrategyDialog(550, 300, 500, 100, "try best move with trump");
                    
                    if (trumpcolor==1) {
                        if (clubA.positionInGame==1 && club10.positionInGame==4 && ((club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=clubA.positionOnHand;
                        }
                    }
                    else if (trumpcolor==2) {
                        if (spadeA.positionInGame==1 && spade10.positionInGame==4 && ((spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=spadeA.positionOnHand;
                        }
                    }
                    else if (trumpcolor==3) {
                        if (heartA.positionInGame==1 && heart10.positionInGame==4 && ((heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=heartA.positionOnHand;
                        }
                    }
                    else if (trumpcolor==4) {
                        if (diamA.positionInGame==1 && diam10.positionInGame==4 && ((diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                            finalChoice=diamA.positionOnHand;
                        }
                    }
                }
                // 1.4 Try to pull off best move, by counting the visible cards (good for making it possible in the first few turns!)
                if (finalChoice==0 && trumpcolor==1){
                    if (clubA.positionInGame==1 && club10.positionInGame==4 && ((club10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (club10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (club10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (club10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (club10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (club10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (club10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (club10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (clubK.positionInGame%2==1 && clubQ.positionInGame%2==1 && club9.positionInGame%2==1 && club8.positionInGame%2==1 && club7.positionInGame%2==1 && clubJ.positionInGame%2==1 && spadeJ.positionInGame%2==1 && heartJ.positionInGame%2==1 && diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=clubA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor==2) {
                    if (spadeA.positionInGame==1 && spade10.positionInGame==4 && ((spade10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (spade10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (spade10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (spade10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (spade10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (spade10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (spade10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (spade10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (spadeK.positionInGame%2==1 && spadeQ.positionInGame%2==1 && spade9.positionInGame%2==1 && spade8.positionInGame%2==1 && spade7.positionInGame%2==1 && clubJ.positionInGame%2==1 && spadeJ.positionInGame%2==1 && heartJ.positionInGame%2==1 && diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=spadeA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor==3) {
                    if (heartA.positionInGame==1 && heart10.positionInGame==4 && ((heart10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (heart10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (heart10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (heart10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (heart10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (heart10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (heart10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (heart10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (heartK.positionInGame%2==1 && heartQ.positionInGame%2==1 && heart9.positionInGame%2==1 && heart8.positionInGame%2==1 && heart7.positionInGame%2==1 && clubJ.positionInGame%2==1 && spadeJ.positionInGame%2==1 && heartJ.positionInGame%2==1 && diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=heartA.positionOnHand;
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor==4) {
                    if (diamA.positionInGame==1 && diam10.positionInGame==4 && ((diam10.positionOnHand==1 && backhandPlayer.card1.cardId>32) || (diam10.positionOnHand==2 && backhandPlayer.card2.cardId>32) || (diam10.positionOnHand==3 && backhandPlayer.card3.cardId>32) || (diam10.positionOnHand==4 && backhandPlayer.card4.cardId>32) || (diam10.positionOnHand==5 && backhandPlayer.card5.cardId>32) || (diam10.positionOnHand==6 && backhandPlayer.card6.cardId>32) || (diam10.positionOnHand==7 && backhandPlayer.card7.cardId>32) || (diam10.positionOnHand==8 && backhandPlayer.card8.cardId>32))) {
                        if (diamK.positionInGame%2==1 && diamQ.positionInGame%2==1 && diam9.positionInGame%2==1 && diam8.positionInGame%2==1 && diam7.positionInGame%2==1 && clubJ.positionInGame%2==1 && spadeJ.positionInGame%2==1 && heartJ.positionInGame%2==1 && diamJ.positionInGame%2==1){
                            // This strategy might be a bit more likely than the usual way of doing it. Especially at the beginning.
                            //debugStrategyDialog(550, 300, 500, 100, "make best move with trump by counting visible cards");
                            
                            finalChoice=diamA.positionOnHand;
                        }
                    }
                }
                // #2.1: like best move just with any lower-than-10-card instead of 10
                if (finalChoice==0 && trumpcolor!=1 && clubA.positionInGame==1 && club10.positionInGame!=3) {
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
                        finalChoice=clubA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=2 && spadeA.positionInGame==1 && spade10.positionInGame!=3) {
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
                        finalChoice=spadeA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=3 && heartA.positionInGame==1 && heart10.positionInGame!=3) {
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
                        finalChoice=heartA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=4 && diamA.positionInGame==1 && diam10.positionInGame!=3) {
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
                        finalChoice=diamA.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable ace");
                        
                    }
                }
                // #2.2: definitly servable 10 (good on first turn, but write it such, that it is valid foor all turns)
                // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                if (finalChoice==0 && trumpcolor!=1 && club10.positionInGame==1 && clubA.positionInGame%2==1) {
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
                        finalChoice=club10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=2 && spade10.positionInGame==1 && spadeA.positionInGame%2==1) {
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
                        finalChoice=spade10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=3 && heart10.positionInGame==1 && heartA.positionInGame%2==1) {
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
                        finalChoice=heart10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
                    }
                }
                if (finalChoice==0 && trumpcolor!=4 && diam10.positionInGame==1 && diamA.positionInGame%2==1) {
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
                        finalChoice=diam10.positionOnHand;
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely servable 10");
                        
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
                    if (trumpcolor!=1 && clubA.positionInGame==1 && club10.positionInGame!=3) {
                        counter1=6; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (club10.positionInGame%2==1){counter1--;}
                        if (clubK.positionInGame%2==1){counter1--;}
                        if (clubQ.positionInGame%2==1){counter1--;}
                        if (club9.positionInGame%2==1){counter1--;}
                        if (club8.positionInGame%2==1){counter1--;}
                        if (club7.positionInGame%2==1){counter1--;}
                    }
                    if (trumpcolor!=2 && spadeA.positionInGame==1 && spade10.positionInGame!=3) {
                        counter2=6;
                        if (spade10.positionInGame%2==1){counter2--;}
                        if (spadeK.positionInGame%2==1){counter2--;}
                        if (spadeQ.positionInGame%2==1){counter2--;}
                        if (spade9.positionInGame%2==1){counter2--;}
                        if (spade8.positionInGame%2==1){counter2--;}
                        if (spade7.positionInGame%2==1){counter2--;}
                    }
                    if (trumpcolor!=3 && heartA.positionInGame==1 && heart10.positionInGame!=3) {
                        counter3=6;
                        if (heart10.positionInGame%2==1){counter3--;}
                        if (heartK.positionInGame%2==1){counter3--;}
                        if (heartQ.positionInGame%2==1){counter3--;}
                        if (heart9.positionInGame%2==1){counter3--;}
                        if (heart8.positionInGame%2==1){counter3--;}
                        if (heart7.positionInGame%2==1){counter3--;}
                    }
                    if (trumpcolor!=4 && diamA.positionInGame==1 && diam10.positionInGame!=3) {
                        counter4=6;
                        if (diam10.positionInGame%2==1){counter4--;}
                        if (diamK.positionInGame%2==1){counter4--;}
                        if (diamQ.positionInGame%2==1){counter4--;}
                        if (diam9.positionInGame%2==1){counter4--;}
                        if (diam8.positionInGame%2==1){counter4--;}
                        if (diam7.positionInGame%2==1){counter4--;}
                    }
                    counter=counter1;
                    if (counter2>counter){counter=counter2;}
                    if (counter3>counter){counter=counter3;}
                    if (counter4>counter){counter=counter4;}
                    if (counter>2){
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "likely servable ace");
                        
                        if (counter==counter1){finalChoice=clubA.positionOnHand;}
                        if (finalChoice!=0 && counter==counter2){finalChoice=spadeA.positionOnHand;}
                        if (finalChoice!=0 && counter==counter3){finalChoice=heartA.positionOnHand;}
                        if (finalChoice!=0 && counter==counter4){finalChoice=diamA.positionOnHand;}
                    }
                }
                // #4: similar to #3 but with 10 instead of ace (but it is so unlikely that the player does not have the ace for sure, go through all cases separately)
                if (finalChoice==0) {
                    if (trumpcolor!=1 && club10.positionInGame==1 && clubA.positionInGame%2==1) {
                        counter=5; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (clubK.positionInGame%2==1){counter--;}
                        if (clubQ.positionInGame%2==1){counter--;}
                        if (club9.positionInGame%2==1){counter--;}
                        if (club8.positionInGame%2==1){counter--;}
                        if (club7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=club10.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                    if (trumpcolor!=2 && spade10.positionInGame==1 && spadeA.positionInGame%2==1) {
                        counter=5;
                        if (spadeK.positionInGame%2==1){counter--;}
                        if (spadeQ.positionInGame%2==1){counter--;}
                        if (spade9.positionInGame%2==1){counter--;}
                        if (spade8.positionInGame%2==1){counter--;}
                        if (spade7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=spade10.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                    if (trumpcolor!=3 && heart10.positionInGame==1 && heartA.positionInGame%2==1) {
                        counter=5;
                        if (heartK.positionInGame%2==1){counter--;}
                        if (heartQ.positionInGame%2==1){counter--;}
                        if (heart9.positionInGame%2==1){counter--;}
                        if (heart8.positionInGame%2==1){counter--;}
                        if (heart7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=heart10.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                    if (trumpcolor!=4 && diam10.positionInGame==1 && diamA.positionInGame%2==1) {
                        counter=5;
                        if (diamK.positionInGame%2==1){counter--;}
                        if (diamQ.positionInGame%2==1){counter--;}
                        if (diam9.positionInGame%2==1){counter--;}
                        if (diam8.positionInGame%2==1){counter--;}
                        if (diam7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=diam10.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable 10");
                            
                        }
                    }
                }
                // #5: similar to #4 (but with king)
                if (finalChoice==0) {
                    if (trumpcolor!=1 && clubK.positionInGame==1 && club10.positionInGame%2==1 && clubA.positionInGame%2==1) {
                        counter=4; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (clubQ.positionInGame%2==1){counter--;}
                        if (club9.positionInGame%2==1){counter--;}
                        if (club8.positionInGame%2==1){counter--;}
                        if (club7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=clubK.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                    if (trumpcolor!=2 && spadeK.positionInGame==1 && spade10.positionInGame%2==1 && spadeA.positionInGame%2==1) {
                        counter=4;
                        if (spadeQ.positionInGame%2==1){counter--;}
                        if (spade9.positionInGame%2==1){counter--;}
                        if (spade8.positionInGame%2==1){counter--;}
                        if (spade7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=spadeK.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                    if (trumpcolor!=3 && heartK.positionInGame==1 && heart10.positionInGame%2==1 && heartA.positionInGame%2==1) {
                        counter=4;
                        if (heartQ.positionInGame%2==1){counter--;}
                        if (heart9.positionInGame%2==1){counter--;}
                        if (heart8.positionInGame%2==1){counter--;}
                        if (heart7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=heartK.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                    if (trumpcolor!=4 && diamK.positionInGame==1 && diam10.positionInGame%2==1 && diamA.positionInGame%2==1) {
                        counter=4;
                        if (diamQ.positionInGame%2==1){counter--;}
                        if (diam9.positionInGame%2==1){counter--;}
                        if (diam8.positionInGame%2==1){counter--;}
                        if (diam7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=diamK.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable king");
                            
                        }
                    }
                }
                // #6: similar to #4 & #5 (but with queen) (it is very unlikely that this happens, but still better than just playing a low card)
                if (finalChoice==0) {
                    if (trumpcolor!=1 && clubQ.positionInGame==1 && clubK.positionInGame%2==1 && club10.positionInGame%2==1 && clubA.positionInGame%2==1) {
                        counter=3; // (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                        if (club9.positionInGame%2==1){counter--;}
                        if (club8.positionInGame%2==1){counter--;}
                        if (club7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=clubQ.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                    if (trumpcolor!=2 && spadeQ.positionInGame==1 && spadeK.positionInGame%2==1 && spade10.positionInGame%2==1 && spadeA.positionInGame%2==1) {
                        counter=3;
                        if (spade9.positionInGame%2==1){counter--;}
                        if (spade8.positionInGame%2==1){counter--;}
                        if (spade7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=spadeQ.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                    if (trumpcolor!=3 && heartQ.positionInGame==1 && heartK.positionInGame%2==1 && heart10.positionInGame%2==1 && heartA.positionInGame%2==1) {
                        counter=3;
                        if (heart9.positionInGame%2==1){counter--;}
                        if (heart8.positionInGame%2==1){counter--;}
                        if (heart7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=heartQ.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                    if (trumpcolor!=4 && diamQ.positionInGame==1 && diamK.positionInGame%2==1 && diam10.positionInGame%2==1 && diamA.positionInGame%2==1) {
                        counter=3;
                        if (diam9.positionInGame%2==1){counter--;}
                        if (diam8.positionInGame%2==1){counter--;}
                        if (diam7.positionInGame%2==1){counter--;}
                        if (counter>2){
                            finalChoice=diamQ.positionOnHand;
                            
                            //debugStrategyDialog(550, 300, 500, 100, "likely servable queen");
                            
                        }
                    }
                }
                // #7: try to lure out the jacks (position in game modulo 2 is odd, if the CPU knows, it is not on the player's hand, but somewhere else)
                if (finalChoice==0 && trumpcolor==1 && (club7.positionInGame==1 || club8.positionInGame==1 || club9.positionInGame==1) && (clubQ.positionInGame==1 || clubK.positionInGame==1 || club10.positionInGame==1 || clubA.positionInGame==1 || clubJ.positionInGame==1 || spadeJ.positionInGame==1 || heartJ.positionInGame==1 || diamJ.positionInGame==1)) {
                    if (clubA.positionInGame%2==1 && club10.positionInGame%2==1 && clubK.positionInGame%2==1 && clubQ.positionInGame%2==1) {
                        // look here, if player has at least one jack on hand (go through all 8 positions on hand)
                        if ((clubJ.positionInGame==4 || spadeJ.positionInGame==4 || heartJ.positionInGame==4 || diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (clubJ.positionOnHand==1 || spadeJ.positionOnHand==1 || heartJ.positionOnHand==1 || diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (clubJ.positionOnHand==2 || spadeJ.positionOnHand==2 || heartJ.positionOnHand==2 || diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (clubJ.positionOnHand==3 || spadeJ.positionOnHand==3 || heartJ.positionOnHand==3 || diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (clubJ.positionOnHand==4 || spadeJ.positionOnHand==4 || heartJ.positionOnHand==4 || diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (clubJ.positionOnHand==5 || spadeJ.positionOnHand==5 || heartJ.positionOnHand==5 || diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (clubJ.positionOnHand==6 || spadeJ.positionOnHand==6 || heartJ.positionOnHand==6 || diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (clubJ.positionOnHand==7 || spadeJ.positionOnHand==7 || heartJ.positionOnHand==7 || diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (clubJ.positionOnHand==8 || spadeJ.positionOnHand==8 || heartJ.positionOnHand==8 || diamJ.positionOnHand==8)) )) {
                            if (club7.positionInGame==1) {finalChoice=club7.positionOnHand;}
                            else if (club8.positionInGame==1) {finalChoice=club8.positionOnHand;}
                            else if (club9.positionInGame==1) {finalChoice=club9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor==2 && (spade7.positionInGame==1 || spade8.positionInGame==1 || spade9.positionInGame==1) && (spadeQ.positionInGame==1 || spadeK.positionInGame==1 || spade10.positionInGame==1 || spadeA.positionInGame==1 || clubJ.positionInGame==1 || spadeJ.positionInGame==1 || heartJ.positionInGame==1 || diamJ.positionInGame==1)) {
                    if (spadeA.positionInGame%2==1 && spade10.positionInGame%2==1 && spadeK.positionInGame%2==1 && spadeQ.positionInGame%2==1) {
                        // look here, if player has at leats one jack on hand (go through all 8 positions on hand)
                        if ((clubJ.positionInGame==4 || spadeJ.positionInGame==4 || heartJ.positionInGame==4 || diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (clubJ.positionOnHand==1 || spadeJ.positionOnHand==1 || heartJ.positionOnHand==1 || diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (clubJ.positionOnHand==2 || spadeJ.positionOnHand==2 || heartJ.positionOnHand==2 || diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (clubJ.positionOnHand==3 || spadeJ.positionOnHand==3 || heartJ.positionOnHand==3 || diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (clubJ.positionOnHand==4 || spadeJ.positionOnHand==4 || heartJ.positionOnHand==4 || diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (clubJ.positionOnHand==5 || spadeJ.positionOnHand==5 || heartJ.positionOnHand==5 || diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (clubJ.positionOnHand==6 || spadeJ.positionOnHand==6 || heartJ.positionOnHand==6 || diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (clubJ.positionOnHand==7 || spadeJ.positionOnHand==7 || heartJ.positionOnHand==7 || diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (clubJ.positionOnHand==8 || spadeJ.positionOnHand==8 || heartJ.positionOnHand==8 || diamJ.positionOnHand==8)) )) {
                            if (spade7.positionInGame==1) {finalChoice=spade7.positionOnHand;}
                            else if (spade8.positionInGame==1) {finalChoice=spade8.positionOnHand;}
                            else if (spade9.positionInGame==1) {finalChoice=spade9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor==3 && (heart7.positionInGame==1 || heart8.positionInGame==1 || heart9.positionInGame==1) && (heartQ.positionInGame==1 || heartK.positionInGame==1 || heart10.positionInGame==1 || heartA.positionInGame==1 || clubJ.positionInGame==1 || spadeJ.positionInGame==1 || heartJ.positionInGame==1 || diamJ.positionInGame==1)) {
                    if (heartA.positionInGame%2==1 && heart10.positionInGame%2==1 && heartK.positionInGame%2==1 && heartQ.positionInGame%2==1) {
                        // look here, if player has at leats one jack on hand (go through all 8 positions on hand)
                        if ((clubJ.positionInGame==4 || spadeJ.positionInGame==4 || heartJ.positionInGame==4 || diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (clubJ.positionOnHand==1 || spadeJ.positionOnHand==1 || heartJ.positionOnHand==1 || diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (clubJ.positionOnHand==2 || spadeJ.positionOnHand==2 || heartJ.positionOnHand==2 || diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (clubJ.positionOnHand==3 || spadeJ.positionOnHand==3 || heartJ.positionOnHand==3 || diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (clubJ.positionOnHand==4 || spadeJ.positionOnHand==4 || heartJ.positionOnHand==4 || diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (clubJ.positionOnHand==5 || spadeJ.positionOnHand==5 || heartJ.positionOnHand==5 || diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (clubJ.positionOnHand==6 || spadeJ.positionOnHand==6 || heartJ.positionOnHand==6 || diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (clubJ.positionOnHand==7 || spadeJ.positionOnHand==7 || heartJ.positionOnHand==7 || diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (clubJ.positionOnHand==8 || spadeJ.positionOnHand==8 || heartJ.positionOnHand==8 || diamJ.positionOnHand==8)) )) {
                            if (heart7.positionInGame==1) {finalChoice=heart7.positionOnHand;}
                            else if (heart8.positionInGame==1) {finalChoice=heart8.positionOnHand;}
                            else if (heart9.positionInGame==1) {finalChoice=heart9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor==4 && (diam7.positionInGame==1 || diam8.positionInGame==1 || diam9.positionInGame==1) && (diamQ.positionInGame==1 || diamK.positionInGame==1 || diam10.positionInGame==1 || diamA.positionInGame==1 || clubJ.positionInGame==1 || spadeJ.positionInGame==1 || heartJ.positionInGame==1 || diamJ.positionInGame==1)) {
                    if (diamA.positionInGame%2==1 && diam10.positionInGame%2==1 && diamK.positionInGame%2==1 && diamQ.positionInGame%2==1) {
                        // look here, if player has at leats one jack on hand (go through all 8 positions on hand)
                        if ((clubJ.positionInGame==4 || spadeJ.positionInGame==4 || heartJ.positionInGame==4 || diamJ.positionInGame==4) && ( (backhandPlayer.card1.cardId>32 && (clubJ.positionOnHand==1 || spadeJ.positionOnHand==1 || heartJ.positionOnHand==1 || diamJ.positionOnHand==1)) || (backhandPlayer.card2.cardId>32 && (clubJ.positionOnHand==2 || spadeJ.positionOnHand==2 || heartJ.positionOnHand==2 || diamJ.positionOnHand==2)) || (backhandPlayer.card3.cardId>32 && (clubJ.positionOnHand==3 || spadeJ.positionOnHand==3 || heartJ.positionOnHand==3 || diamJ.positionOnHand==3)) || (backhandPlayer.card4.cardId>32 && (clubJ.positionOnHand==4 || spadeJ.positionOnHand==4 || heartJ.positionOnHand==4 || diamJ.positionOnHand==4)) || (backhandPlayer.card5.cardId>32 && (clubJ.positionOnHand==5 || spadeJ.positionOnHand==5 || heartJ.positionOnHand==5 || diamJ.positionOnHand==5)) || (backhandPlayer.card6.cardId>32 && (clubJ.positionOnHand==6 || spadeJ.positionOnHand==6 || heartJ.positionOnHand==6 || diamJ.positionOnHand==6)) || (backhandPlayer.card7.cardId>32 && (clubJ.positionOnHand==7 || spadeJ.positionOnHand==7 || heartJ.positionOnHand==7 || diamJ.positionOnHand==7)) || (backhandPlayer.card8.cardId>32 && (clubJ.positionOnHand==8 || spadeJ.positionOnHand==8 || heartJ.positionOnHand==8 || diamJ.positionOnHand==8)) )) {
                            if (diam7.positionInGame==1) {finalChoice=diam7.positionOnHand;}
                            else if (diam8.positionInGame==1) {finalChoice=diam8.positionOnHand;}
                            else if (diam9.positionInGame==1) {finalChoice=diam9.positionOnHand;}
                            // This is a good strategy. It happens not often, but is completing the usual aggressive techniques quite well.
                            //debugStrategyDialog(550, 300, 500, 100, "try to lure out a jack");
                            
                        }
                    }
                }
                // #8: try to play a low non-trump card that the player can not serve
                if (finalChoice==0 && trumpcolor!=1) {
                    if ((club7.positionInGame==1 || club8.positionInGame==1 || club9.positionInGame==1) && club7.positionInGame%2==1 && club8.positionInGame%2==1 && club9.positionInGame%2==1 && clubQ.positionInGame%2==1 && clubK.positionInGame%2==1 && club10.positionInGame%2==1 && clubA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (club7.positionInGame==1){finalChoice=club7.positionOnHand;}
                        if (finalChoice==0 && club8.positionInGame==1){finalChoice=club8.positionOnHand;}
                        if (finalChoice==0 && club9.positionInGame==1){finalChoice=club9.positionOnHand;}
                    }
                }
                if (finalChoice==0 && trumpcolor!=2) {
                    if ((spade7.positionInGame==1 || spade8.positionInGame==1 || spade9.positionInGame==1) && spade7.positionInGame%2==1 && spade8.positionInGame%2==1 && spade9.positionInGame%2==1 && spadeQ.positionInGame%2==1 && spadeK.positionInGame%2==1 && spade10.positionInGame%2==1 && spadeA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand;}
                        if (finalChoice==0 && spade8.positionInGame==1){finalChoice=spade8.positionOnHand;}
                        if (finalChoice==0 && spade9.positionInGame==1){finalChoice=spade9.positionOnHand;}
                    }
                }
                if (finalChoice==0 && trumpcolor!=3) {
                    if ((heart7.positionInGame==1 || heart8.positionInGame==1 || heart9.positionInGame==1) && heart7.positionInGame%2==1 && heart8.positionInGame%2==1 && heart9.positionInGame%2==1 && heartQ.positionInGame%2==1 && heartK.positionInGame%2==1 && heart10.positionInGame%2==1 && heartA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand;}
                        if (finalChoice==0 && heart8.positionInGame==1){finalChoice=heart8.positionOnHand;}
                        if (finalChoice==0 && heart9.positionInGame==1){finalChoice=heart9.positionOnHand;}
                    }
                }
                if (finalChoice==0 && trumpcolor!=4) {
                    if ((diam7.positionInGame==1 || diam8.positionInGame==1 || diam9.positionInGame==1) && diam7.positionInGame%2==1 && diam8.positionInGame%2==1 && diam9.positionInGame%2==1 && diamQ.positionInGame%2==1 && diamK.positionInGame%2==1 && diam10.positionInGame%2==1 && diamA.positionInGame%2==1) {
                        // This strategy is good enough. It happened often enough.
                        //debugStrategyDialog(550, 300, 500, 100, "definitely unservable low card");
                        
                        if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand;}
                        if (finalChoice==0 && diam8.positionInGame==1){finalChoice=diam8.positionOnHand;}
                        if (finalChoice==0 && diam9.positionInGame==1){finalChoice=diam9.positionOnHand;}
                    }
                }
                // #9: try to play a low card that the player probably can not serve, becauuse could only have maximally 2 others of that color
                if (finalChoice==0 && trumpcolor!=1) {
                    if (club7.positionInGame==1 || club8.positionInGame==1 || club9.positionInGame==1) {
                        counter=7;
                        if (clubA.positionInGame%2==1){counter--;}
                        if (club10.positionInGame%2==1){counter--;}
                        if (clubK.positionInGame%2==1){counter--;}
                        if (clubQ.positionInGame%2==1){counter--;}
                        if (club9.positionInGame%2==1){counter--;}
                        if (club8.positionInGame%2==1){counter--;}
                        if (club7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (club7.positionInGame==1){finalChoice=club7.positionOnHand;}
                            if (finalChoice==0 && club8.positionInGame==1){finalChoice=club8.positionOnHand;}
                            if (finalChoice==0 && club9.positionInGame==1){finalChoice=club9.positionOnHand;}
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=2) {
                    if (spade7.positionInGame==1 || spade8.positionInGame==1 || spade9.positionInGame==1) {
                        counter=7;
                        if (spadeA.positionInGame%2==1){counter--;}
                        if (spade10.positionInGame%2==1){counter--;}
                        if (spadeK.positionInGame%2==1){counter--;}
                        if (spadeQ.positionInGame%2==1){counter--;}
                        if (spade9.positionInGame%2==1){counter--;}
                        if (spade8.positionInGame%2==1){counter--;}
                        if (spade7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (spade7.positionInGame==1){finalChoice=spade7.positionOnHand;}
                            if (finalChoice==0 && spade8.positionInGame==1){finalChoice=spade8.positionOnHand;}
                            if (finalChoice==0 && spade9.positionInGame==1){finalChoice=spade9.positionOnHand;}
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=3) {
                    if (heart7.positionInGame==1 || heart8.positionInGame==1 || heart9.positionInGame==1) {
                        counter=7;
                        if (heartA.positionInGame%2==1){counter--;}
                        if (heart10.positionInGame%2==1){counter--;}
                        if (heartK.positionInGame%2==1){counter--;}
                        if (heartQ.positionInGame%2==1){counter--;}
                        if (heart9.positionInGame%2==1){counter--;}
                        if (heart8.positionInGame%2==1){counter--;}
                        if (heart7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (heart7.positionInGame==1){finalChoice=heart7.positionOnHand;}
                            if (finalChoice==0 && heart8.positionInGame==1){finalChoice=heart8.positionOnHand;}
                            if (finalChoice==0 && heart9.positionInGame==1){finalChoice=heart9.positionOnHand;}
                        }
                    }
                }
                if (finalChoice==0 && trumpcolor!=4) {
                    if (diam7.positionInGame==1 || diam8.positionInGame==1 || diam9.positionInGame==1) {
                        counter=7;
                        if (diamA.positionInGame%2==1){counter--;}
                        if (diam10.positionInGame%2==1){counter--;}
                        if (diamK.positionInGame%2==1){counter--;}
                        if (diamQ.positionInGame%2==1){counter--;}
                        if (diam9.positionInGame%2==1){counter--;}
                        if (diam8.positionInGame%2==1){counter--;}
                        if (diam7.positionInGame%2==1){counter--;}
                        if (counter<3) { // play the low card, if the player can only have two or less of that color
                            // This seems to be one of the most often used strategies.
                            //debugStrategyDialog(550, 300, 500, 100, "likely unservable low card");
                        
                            if (diam7.positionInGame==1){finalChoice=diam7.positionOnHand;}
                            if (finalChoice==0 && diam8.positionInGame==1){finalChoice=diam8.positionOnHand;}
                            if (finalChoice==0 && diam9.positionInGame==1){finalChoice=diam9.positionOnHand;}
                        }
                    }
                }
                // #10: when in doubt, play a low non-trump card (7, 8, 9), maybe even try to play a queen or a king before using random choices 
                if (finalChoice==0) {
                    // This was supposed to be the standard strategy. However it seems that the slightly better strategy "likely unservable low card" happens as likely.
                    //debugStrategyDialog(550, 300, 500, 100, "try to play a low card");
                    
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
                    // discard a semi-valuable card
                    else if (diamQ.positionInGame==1 && trumpcolor!=4){finalChoice=diamQ.positionOnHand;}
                    else if (heartQ.positionInGame==1 && trumpcolor!=3){finalChoice=heartQ.positionOnHand;}
                    else if (spadeQ.positionInGame==1 && trumpcolor!=2){finalChoice=spadeQ.positionOnHand;}
                    else if (clubQ.positionInGame==1 && trumpcolor!=1){finalChoice=clubQ.positionOnHand;}
                    else if (diamK.positionInGame==1 && trumpcolor!=4){finalChoice=diamK.positionOnHand;}
                    else if (heartK.positionInGame==1 && trumpcolor!=3){finalChoice=heartK.positionOnHand;}
                    else if (spadeK.positionInGame==1 && trumpcolor!=2){finalChoice=spadeK.positionOnHand;}
                    else if (clubK.positionInGame==1 && trumpcolor!=1){finalChoice=clubK.positionOnHand;}
                }
                // #11: if everything breaks down, play a random card
                // source code copied from lowest difficulty
                if (finalChoice==0) {
                    // This is a very often used strategy. Especially towards the end.
                    //debugStrategyDialog(550, 300, 500, 100, "play a random card");
                    
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
                    finalChoice=optionArray[chooseRandomOption(numberOfOptions)-1]; // -1 needed, or else program goes in dead end
                }
                // here the AI finally leaves this method on medium difficulty, when starting to play
                
                //debugStrategyDialog(550, 300, 500, 100, "finally decide on playing a card");
                
                return finalChoice;
             
            
        }
        
    }
    
    
    
    
    
}
