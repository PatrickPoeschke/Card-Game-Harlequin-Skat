package harlekinskat4;

/**
 * This class models the decision making of the CPU on easy difficulty.
 * Since the computer on lowest difficulty uses random choices,
 * here are also methods for shuffling a deck and coin flips.
 * 
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static harlekinskat4.Harlekinskat4.cpuReacts;
import static harlekinskat4.Harlekinskat4.currentColor;
import static harlekinskat4.Harlekinskat4.trumpPlayed;
import static harlekinskat4.Harlekinskat4.trumpColor;
import static harlekinskat4.Harlekinskat4.isShowingAIStrategies;
import static harlekinskat4.Harlekinskat4.infoDialog;
import static harlekinskat4.Harlekinskat4.stringServeRandomCard;
import static harlekinskat4.Harlekinskat4.stringPlayRandomCard;

public class AIEasy {
    public static int[] optionArray = new int[8]; // for counting the options for cpu decisions
    public static int numberOfOptions;
    public static int finalChoice=0; // integer value of card to be played by cpu after it finally decided on it
    
    // method for choosing one random option out n given ones
    // usefull for coin flips, randomly choosing a trump color, (dice rolls)
    // or for letting the computer play a random card out a given number of allowed ones
    public static int chooseRandomOption(int numberOfOptions){
        // creates an array with the first n intergers, shuffles it,
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
    
    // --- lowest difficulty level here ---
    
    // my experience with this difficulty level:
    // As expected, this difficulty level is just something for beginners.
    // By playing random cards the CPU will make extremely bad moves from time to time.
    // These moves include, discarding an ace or 10 without reason or wasting a valuable jack on a low card.
    // No ordinary player would make such bad moves.
    // If these moves happen twice or three times in one game, that can already decide the game.
    // On the bright side, this difficulty level is already so weak
    // that one does not need to program a cheating CPU that tries to make the worst possible moves on purpose.
    
    public static int aiLevelEasy(Skatcard playedCardPlayer, Hand playableCPU, Skatcard placeholder){
        if (cpuReacts){ // here the player has played a card and the computer reacts
            // just count the number of allowed existing cards on the hand of the CPU, take a random one of them, and play it
            if (playedCardPlayer.isTrump()){
                trumpPlayed=true;
                currentColor=trumpColor;
                setTrumpCardsAsOptions(playableCPU);
            }
            else { // a non-trump color has been played
                trumpPlayed=false;
                currentColor=playedCardPlayer.color;
                setCardsOfAGivenColorAsOptions(playableCPU);
            }
            // after checking the options, play a valid card if possible, otherwise just play any existing card
            if (numberOfOptions>0){
                finalChoice=optionArray[chooseRandomOption(numberOfOptions)-1]; // -1 needed, because arrays in almost every programming language are always off by one
                if (isShowingAIStrategies) {infoDialog(stringServeRandomCard);}
                return finalChoice;
            }
            else {
                finalChoice=playRandomCard(playableCPU, placeholder);
                return finalChoice;
            }
        }
        else {
            finalChoice=playRandomCard(playableCPU, placeholder);
            return finalChoice;
        }
    }
    
    // erases the memory about playable options
    public static void resetOptionArray() {
        for (int index = 1; index <= 8; index++){
            optionArray[index-1]=0;
        }
    }
    
    // remembers positions of all playable cards of a given non-trump color
    public static void setCardsOfAGivenColorAsOptions (Hand playableCPU) {
        resetOptionArray();
        numberOfOptions=0;
        Skatcard Card;
        for (int index = 1; index <= 8; index++){
            Card = playableCPU.getNthCardOfHand(index);
            if (Card.color==currentColor && !Card.isJack()){
                numberOfOptions++;
                optionArray[numberOfOptions-1]=index;
            }
        }
    }
    
    // remembers positions of all playable trump cards
    public static void setTrumpCardsAsOptions (Hand playableCPU) {
        resetOptionArray();
        numberOfOptions=0;
        Skatcard Card;
        for (int index = 1; index <= 8; index++){
            Card = playableCPU.getNthCardOfHand(index);
            if (Card.isTrump()){
                numberOfOptions++;
                optionArray[numberOfOptions-1]=index;
            }
        }
    }
    
    // makes CPU play a random card (out of existing ones) on the hand
    public static int playRandomCard(Hand playableCPU, Skatcard placeholder) {
        if (isShowingAIStrategies) {infoDialog(stringPlayRandomCard);}
        resetOptionArray();
        numberOfOptions=0;
        for (int index = 1; index <= 8; index++){
            if (playableCPU.getNthCardOfHand(index)!=placeholder) {
                numberOfOptions++; optionArray[numberOfOptions-1]=index;
            }
        }
        return optionArray[chooseRandomOption(numberOfOptions)-1];
    }
    
    // computer chooses trump by just rolling a 4-sided die
    // (returns the trump color as int)
    public static int cpuChoosesTrump()
    {
        return chooseRandomOption(4);
    }
    
}
