package harlekinskat4;

/**
 * This class translates the game into English.
 * 
 */

import static harlekinskat4.Harlekinskat4.windowl;
import static harlekinskat4.Harlekinskat4.buttonRules;
import static harlekinskat4.Harlekinskat4.buttonStart;
import static harlekinskat4.Harlekinskat4.buttonDifficulty;
import static harlekinskat4.Harlekinskat4.buttonChooseLanguage;
import static harlekinskat4.Harlekinskat4.stringWinCards;
import static harlekinskat4.Harlekinskat4.stringLoseCards;
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
import static harlekinskat4.Harlekinskat4.stringJ1Text;
import static harlekinskat4.Harlekinskat4.stringJ2Text;
import static harlekinskat4.Harlekinskat4.stringJ3Text;
import static harlekinskat4.Harlekinskat4.stringJ4Text;
import static harlekinskat4.Harlekinskat4.stringCAText;
import static harlekinskat4.Harlekinskat4.stringC10Text;
import static harlekinskat4.Harlekinskat4.stringCKText;
import static harlekinskat4.Harlekinskat4.stringCQText;
import static harlekinskat4.Harlekinskat4.stringC9Text;
import static harlekinskat4.Harlekinskat4.stringC8Text;
import static harlekinskat4.Harlekinskat4.stringC7Text;
import static harlekinskat4.Harlekinskat4.stringSAText;
import static harlekinskat4.Harlekinskat4.stringS10Text;
import static harlekinskat4.Harlekinskat4.stringSKText;
import static harlekinskat4.Harlekinskat4.stringSQText;
import static harlekinskat4.Harlekinskat4.stringS9Text;
import static harlekinskat4.Harlekinskat4.stringS8Text;
import static harlekinskat4.Harlekinskat4.stringS7Text;
import static harlekinskat4.Harlekinskat4.stringHAText;
import static harlekinskat4.Harlekinskat4.stringH10Text;
import static harlekinskat4.Harlekinskat4.stringHKText;
import static harlekinskat4.Harlekinskat4.stringHQText;
import static harlekinskat4.Harlekinskat4.stringH9Text;
import static harlekinskat4.Harlekinskat4.stringH8Text;
import static harlekinskat4.Harlekinskat4.stringH7Text;
import static harlekinskat4.Harlekinskat4.stringDAText;
import static harlekinskat4.Harlekinskat4.stringD10Text;
import static harlekinskat4.Harlekinskat4.stringDKText;
import static harlekinskat4.Harlekinskat4.stringDQText;
import static harlekinskat4.Harlekinskat4.stringD9Text;
import static harlekinskat4.Harlekinskat4.stringD8Text;
import static harlekinskat4.Harlekinskat4.stringD7Text;
import static harlekinskat4.Harlekinskat4.stringGameOver;
import static harlekinskat4.Harlekinskat4.stringDraw;
import static harlekinskat4.Harlekinskat4.stringWin;
import static harlekinskat4.Harlekinskat4.stringLose;
import static harlekinskat4.Harlekinskat4.stringPointCounting;
import static harlekinskat4.Harlekinskat4.stringRestartMessage;
import static harlekinskat4.Harlekinskat4.stringDifficultyDialog1;
import static harlekinskat4.Harlekinskat4.stringDifficultyDialog2;
import static harlekinskat4.Harlekinskat4.stringEasy;
import static harlekinskat4.Harlekinskat4.stringNormal;
import static harlekinskat4.Harlekinskat4.stringHard;
import static harlekinskat4.Harlekinskat4.stringEasyAffirm;
import static harlekinskat4.Harlekinskat4.stringNormalAffirm;
import static harlekinskat4.Harlekinskat4.stringHardAffirm;
import static harlekinskat4.Harlekinskat4.stringDealing;
import static harlekinskat4.Harlekinskat4.stringCoin;
import static harlekinskat4.Harlekinskat4.stringTrumpDialog;
import static harlekinskat4.Harlekinskat4.stringClubs;
import static harlekinskat4.Harlekinskat4.stringSpades;
import static harlekinskat4.Harlekinskat4.stringHearts;
import static harlekinskat4.Harlekinskat4.stringDiamonds;
import static harlekinskat4.Harlekinskat4.stringClubsAffirm1;
import static harlekinskat4.Harlekinskat4.stringSpadesAffirm1;
import static harlekinskat4.Harlekinskat4.stringHeartsAffirm1;
import static harlekinskat4.Harlekinskat4.stringDiamondsAffirm1;
import static harlekinskat4.Harlekinskat4.stringClubsAffirm2;
import static harlekinskat4.Harlekinskat4.stringSpadesAffirm2;
import static harlekinskat4.Harlekinskat4.stringHeartsAffirm2;
import static harlekinskat4.Harlekinskat4.stringDiamondsAffirm2;
import static harlekinskat4.Harlekinskat4.stringRuleTitle;
import static harlekinskat4.Harlekinskat4.stringRuleText;
// since version 4:
import static harlekinskat4.Harlekinskat4.buttonShowStrategies;
import static harlekinskat4.Harlekinskat4.stringShowStrategies;
import static harlekinskat4.Harlekinskat4.stringShowStrategiesDialog;
import static harlekinskat4.Harlekinskat4.stringYes;
import static harlekinskat4.Harlekinskat4.stringNoDefault;
import static harlekinskat4.Harlekinskat4.stringServeRandomCard;
import static harlekinskat4.Harlekinskat4.stringPlayRandomCard;
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
import static harlekinskat4.Harlekinskat4.stringBestMove;
import static harlekinskat4.Harlekinskat4.stringBestMoveTrump;
import static harlekinskat4.Harlekinskat4.stringTrumpHighWithJack;
import static harlekinskat4.Harlekinskat4.stringServeAce;
import static harlekinskat4.Harlekinskat4.stringServeTen;
import static harlekinskat4.Harlekinskat4.stringServeTrumpAce;
import static harlekinskat4.Harlekinskat4.stringServeKing;
import static harlekinskat4.Harlekinskat4.stringServeQueen;
import static harlekinskat4.Harlekinskat4.stringUnserveNoTrump;

public class English {
    
    public static void setLanguage ()
    {
        
        // translate the titles for graphic elements
        windowl.setTitle("Harlequin Skat");
        buttonRules.setText("rules");
        buttonStart.setText("start/reset");
        buttonDifficulty.setText("set CPU level");
        buttonChooseLanguage.setText("choose language");
    
        // translate the strings
        stringWinCards="<html><body><blockquote>You win both cards.</blockquote></body></html>";
        stringLoseCards="<html><body><blockquote>Computer wins both cards.</blockquote></body></html>";
    
        stringPlayedJ1="<html><body><blockquote>You played the highest jack.</blockquote></body></html>";
        stringPlayedJ2="<html><body><blockquote>You played the 2nd highest jack.</blockquote></body></html>";
        stringPlayedJ3="<html><body><blockquote>You played the 3rd highest jack.</blockquote></body></html>";
        stringPlayedJ4="<html><body><blockquote>You played the lowest jack.</blockquote></body></html>";
    
        stringPlayedTA="<html><body><blockquote>You played the trump ace.</blockquote></body></html>";
        stringPlayedT10="<html><body><blockquote>You played the trump 10.</blockquote></body></html>";
        stringPlayedTK="<html><body><blockquote>You played the trump king.</blockquote></body></html>";
        stringPlayedTQ="<html><body><blockquote>You played the trump queen.</blockquote></body></html>";
        stringPlayedT9="<html><body><blockquote>You played the trump 9.</blockquote></body></html>";
        stringPlayedT8="<html><body><blockquote>You played the trump 8.</blockquote></body></html>";
        stringPlayedT7="<html><body><blockquote>You played the trump 7.</blockquote></body></html>";
    
        stringPlayedCA="<html><body><blockquote>You played the ace of clubs.</blockquote></body></html>";
        stringPlayedC10="<html><body><blockquote>You played the 10 of clubs.</blockquote></body></html>";
        stringPlayedCK="<html><body><blockquote>You played the king of clubs.</blockquote></body></html>";
        stringPlayedCQ="<html><body><blockquote>You played the queen of clubs.</blockquote></body></html>";
        stringPlayedC9="<html><body><blockquote>You played the 9 of clubs.</blockquote></body></html>";
        stringPlayedC8="<html><body><blockquote>You played the 8 of clubs.</blockquote></body></html>";
        stringPlayedC7="<html><body><blockquote>You played the 7 of clubs.</blockquote></body></html>";
    
        stringPlayedSA="<html><body><blockquote>You played the ace of spades.</blockquote></body></html>";
        stringPlayedS10="<html><body><blockquote>You played the 10 of spades.</blockquote></body></html>";
        stringPlayedSK="<html><body><blockquote>You played the king of spades.</blockquote></body></html>";
        stringPlayedSQ="<html><body><blockquote>You played the queen of spades.</blockquote></body></html>";
        stringPlayedS9="<html><body><blockquote>You played the 9 of spades.</blockquote></body></html>";
        stringPlayedS8="<html><body><blockquote>You played the 8 of spades.</blockquote></body></html>";
        stringPlayedS7="<html><body><blockquote>You played the 7 of spades.</blockquote></body></html>";
    
        stringPlayedHA="<html><body><blockquote>You played the ace of hearts.</blockquote></body></html>";
        stringPlayedH10="<html><body><blockquote>You played the 10 of hearts.</blockquote></body></html>";
        stringPlayedHK="<html><body><blockquote>You played the king of hearts.</blockquote></body></html>";
        stringPlayedHQ="<html><body><blockquote>You played the queen of hearts.</blockquote></body></html>";
        stringPlayedH9="<html><body><blockquote>You played the 9 of hearts.</blockquote></body></html>";
        stringPlayedH8="<html><body><blockquote>You played the 8 of hearts.</blockquote></body></html>";
        stringPlayedH7="<html><body><blockquote>You played the 7 of hearts.</blockquote></body></html>";
    
        stringPlayedDA="<html><body><blockquote>You played the ace of diamonds.</blockquote></body></html>";
        stringPlayedD10="<html><body><blockquote>You played the 10 of diamonds.</blockquote></body></html>";
        stringPlayedDK="<html><body><blockquote>You played the king of diamonds.</blockquote></body></html>";
        stringPlayedDQ="<html><body><blockquote>You played the queen of diamonds.</blockquote></body></html>";
        stringPlayedD9="<html><body><blockquote>You played the 9 of diamonds.</blockquote></body></html>";
        stringPlayedD8="<html><body><blockquote>You played the 8 of diamonds.</blockquote></body></html>";
        stringPlayedD7="<html><body><blockquote>You played the 7 of diamonds.</blockquote></body></html>";
    
        stringJ1Text="J jack of clubs (2)";
        stringJ2Text="J jack of spades (2)";
        stringJ3Text="J jack of hearts (2)";
        stringJ4Text="J jack of diamonds (2)";
        stringCAText="A ace of clubs (11)";
        stringC10Text="10 of clubs (10)";
        stringCKText="K king of clubs (4)";
        stringCQText="Q queen of clubs (3)";
        stringC9Text="9 of clubs (0)";
        stringC8Text="8 of clubs (0)";
        stringC7Text="7 of clubs (0)";
        stringSAText="A ace of spades (11)";
        stringS10Text="10 of spades (10)";
        stringSKText="K king of spades (4)";
        stringSQText="Q queen of spades (3)";
        stringS9Text="9 of spades (0)";
        stringS8Text="8 of spades (0)";
        stringS7Text="7 of spades (0)";
        stringHAText="A ace of hearts (11)";
        stringH10Text="10 of hearts (10)";
        stringHKText="K king of hearts (4)";
        stringHQText="Q queen of hearts (3)";
        stringH9Text="9 of hearts (0)";
        stringH8Text="8 of hearts (0)";
        stringH7Text="7 of hearts (0)";
        stringDAText="A ace of diamonds (11)";
        stringD10Text="10 of diamonds (10)";
        stringDKText="K king of diamonds (4)";
        stringDQText="Q queen of diamonds (3)";
        stringD9Text="9 of diamonds (0)";
        stringD8Text="8 of diamonds (0)";
        stringD7Text="7 of diamonds (0)";
        
        stringGameOver="GAME OVER";
        stringDraw="<br> 60 points. <br><br> Draw!<br><br>";
        stringWin=" points. <br><br> You Win!<br><br>";
        stringLose=" points. <br><br> You Lose!<br><br>";
        stringPointCounting="<html><body><blockquote> counting the obtained points:<br><br>";
        stringRestartMessage="Start a new game by pressing the start/reset button.";
    
        stringDifficultyDialog1="Choose difficulty level";
        stringDifficultyDialog2="set CPU level";
        stringEasy="easy/random";
        stringNormal="normal";
        stringHard="hard/cheating";
        stringEasyAffirm="<html><body><blockquote>Difficulty level is easy.</blockquote></body></html>";
        stringNormalAffirm="<html><body><blockquote>Difficulty level is normal.</blockquote></body></html>";
        stringHardAffirm="<html><body><blockquote>Difficulty level is hard.</blockquote></body></html>";
    
        stringDealing="<html><body><blockquote>dealing</blockquote></body></html>";
        stringCoin="<html><body><blockquote>coin flip</blockquote></body></html>";
    
        stringTrumpDialog="Choose trump suit/color";
        stringClubs="<html><body>clubs/black &clubs;</body></html>";
        stringSpades="<html><body>spades/green &spades;</body></html>";
        stringHearts="<html><body>hearts/red &hearts;</body></html>";
        stringDiamonds="<html><body>diamonds/yellow &diams;</body></html>";
    
        stringClubsAffirm1="<html><body><blockquote>Clubs (black &clubs;) is trump. Computer begins to play.</blockquote></body></html>";
        stringSpadesAffirm1="<html><body><blockquote>Spades (green &spades;) is trump. Computer begins to play.</blockquote></body></html>";
        stringHeartsAffirm1="<html><body><blockquote>Hearts (red &hearts;) is trump. Computer begins to play.</blockquote></body></html>";
        stringDiamondsAffirm1="<html><body><blockquote>Diamonds (yellow &diams;) is trump. Computer begins to play.</blockquote></body></html>";
        stringClubsAffirm2="<html><body><blockquote>Computer chooses clubs (black &clubs;) as trump. Your turn.</blockquote></body></html>";
        stringSpadesAffirm2="<html><body><blockquote>Computer chooses spades (green &spades;) as trump. Your turn.</blockquote></body></html>";
        stringHeartsAffirm2="<html><body><blockquote>Computer chooses hearts (red &hearts;) as trump. Your turn.</blockquote></body></html>";
        stringDiamondsAffirm2="<html><body><blockquote>Computer chooses diamonds (yellow &diams;) as trump. Your turn.</blockquote></body></html>";
    
        stringRuleTitle="rules of the game";
        stringRuleText="<html><body><blockquote>Harlequin Skat is a simple card game for two players using a standard 32 card deck. The game evolves around playing cards and then tactically answering by either playing a higher card and claiming both cards and their worth for oneself or by playing a lower card and letting the opponent have both cards. When all 32 cards have been played, the player who obtained more card points wins.<br><br>"
                + "about dealing the cards:<br><br>"
                + "The game is set up such, that after shuffling all 32 cards, each player holds 8 cards in the hand so that one can only see them oneself. Furthermore each player has 8 more cards on the hand behind the other ones. They are turned around so that only the opponent can see these cards.<br>"
                + "That means each player can see half of the own cards as well as half of the opponent's cards. If one remembers the 8 cards on the opponent's hand that one can see at the beginning, one knows what cards the opponent will have in the end game.<br><br>"
                + "about choosing trump suit / trump color:<br><br>"
                + "One of the 4 suits or colors (clubs/black &clubs;, spades/green &spades;, hearts/red &hearts;, and diamonds/yellow &diams;) is chosen to be the trump suit or trump color. The trump cards are considered higher cards then the others.<br>"
                + "There are different variations on how to choose this trump color. In this variation, in the first game a coin flip decides randomly who gets to decide the trump color (and thus also indirectly who gets to start playing). In the following games the two players alternate in choosing trump.<br>"
                + "The one who gets to decide the trump color does so after all 32 cards have been dealt. The idea is to choose a color one has many cards of. However one makes this decision based on seeing only half of the cards in the game. The player who did not choose the trump color gets to start playing the first card.<br>"
                + "(If one always wants to decide the trump color, or always wants to start playing, then one can skip every second game by pressing the reset button after each game.)<br><br>"
                + "about the order of the cards:<br><br>"
                + "In general higher cards win over lower cards. The four highest cards in the game are the four jacks. They are ALWAYS considered to be trump cards! That also means they do not belong to the color actually printed on them. A typical beginner's mistake is to forget about that fact.<br>"
                + "The highest card in the game is always the jack of clubs. The second highest one is the jack of spades. They are followed by the jack of hearts and the jack of diamonds.<br>"
                + "The next highest cards after the jacks are the remaining trump cards in the following order: The highest card after the jacks is the trump ace. It is followed by the trump 10. Then come king, queen, 9, 8, and 7. This makes the trump 7 the lowest trump card. But it is still higher than any non-trump card.<br>"
                + "The remaining 3 colors are considered equal. For each color the order of the cards is the same as for the trump cards. That means the highest non-trump color card is the ace. It is followed by 10, king, queen, 9, 8, and 7.<br><br>"
                + "about how the cards are played and who wins the cards:<br><br>"
                + "The player who did not choose the trump color gets to play the first card. The opponent has to answer by playing another card. Each player can only play the cards they have on their own hand and that they can see. After a card is played, the card behind it (if there is one) gets turned around and becomes a playable card for the next turns.<br>"
                + "After both cards have been played in a turn, it is decided according to the rules (below in this section) who wins both cards and takes them out of the game. These two cards contribute to the number of points the player gained during the game. The player who has won the two cards gets to play the next card. This starts a new turn.<br>"
                + "(In reality one should not forget to turn around cards. Here it is done automatically.)<br>"
                + "In each turn the following rules apply:<br>"
                + "Rule #1: Each player can only play the cards they have on their own hand and that they can see.<br>"
                + "Rule #2: The player who starts to play a card in the turn may play any of those cards.<br>"
                + "Rule #3: The answering player has to play a card of the same color, if possible. The jacks always belong to the trump color. There are 4 colors: the jacks and the other trump cards forming one color as well as the remaining 3 colors.<br>"
                + "Rule #4: If one answers by playing a card of the same color, then the answering player wins both played cards, when playing a higher card of the same color respectively loses both played cards to the opponent when playing a lower card of the same color. (see previous section)<br>"
                + "Rule #5: If one can not play a card of the same color (because one does not have any of them in that turn), then one answers by playing any card (in accordance with \"Rule #1\").<br>"
                + "Rule #6: In case of \"Rule #5\": If the played card was a trump card, then the answering player will lose both cards.<br>"
                + "Rule #7: In case of \"Rule #5\": If the played card was not a trump card, the answering player may either win both played cards by playing any trump card or lose both played cards by playing any other card.<br>"
                + "(One can not cheat in this game. If one tries to play a card that is not allowed in a situation, then nothing happens.)<br><br>"
                + "about the worth of the cards / end game:<br><br>"
                + "After 16 turns are over, all cards have been played and no player has any cards left on their hands. Then both players look at the cards they have won and add up the points they gained according to the following rules:<br>"
                + "Each ace is worth 11 points. Each 10 is worth 10 points.<br>"
                + "Each king, queen, and jack are worth 4, 3, and 2 points respectively.<br>"
                + "The remaining cards (9, 8, 7) are ignored. They are worth nothing.<br>"
                + "There are 120 points in this game in total. That means if a player has 60 points, it is a draw.<br>"
                + "If one has more than 60 points, one is the winner. If one has less than 60 points, one has lost the game.<br>"
                + "(This means one can for example win by only getting 6 of the most valuable cards: 6 cards either being aces or tens, making already 62 to 64 points.)<br><br><br>"
                + "about the difficulty level of the CPU:<br><br>"
                + "There are 3 difficulty levels for the computer: easy/random, normal and hard/cheating. The normal level is default.<br>"
                + "On easy the computer will play according to the rules but otherwise play random cards.<br>"
                + "On normal the computer will play fair and remember if you did not have a certain color, then you turned around a 10 of said color and tries to get it with an ace of said color. (This is the best move in the game.) Also, when in doubt, the computer plays a low non-trump card.<br>"
                + "On hard the computer plays according to the rules, except that it knows the cards that you can see on your hand. Using this knowledge it will try to play a card that you have to answer with a lower card and tries to get as much points as possible this way.<br>"
                + "If the computer chooses trump, the following happens: On the lowest difficulty it will choose a color randomly.<br>"
                + "On normal it chooses the color it has most of on the starting hand.<br>" //  In this case it will have at least 2 trump cards on the starting hand.
                + "On the highest difficulty it is cheating by knowing what color it has most of in total during the whole game.<br><br>" // In this case the computer will have at least 4 trump cards in total.
                + "about the buttons:<br><br>"
                + "With the \"start/reset\" button, one can start a new game. One can also give up and play a new game. Then the cards are being shuffled again.<br>"
                + "With the \"set CPU level\" button, one can set the difficulty level of the computer. The normal level is default. One may change the difficulty level at any time.<br>"
                + "With the  \"show strategies\" button, one can set, if the computer shall reveal its strategies during the game.<br>"
                + "One can play a card by clicking on it. (Of course this only works on cards one is allowed to play at a given moment.) At each time, there are no more than 8 possible cards one can choose from. They are at the lower end of the window.<br>"
                + "Cards that one can not see are depicted by small face down cards. The played cards appear in the middle of the window.<br>"
                + "The stacks of already taken cards appear at the right and are depicted by a face down card each.<br><br>"
                + "Enjoy the game!<br><br><br>"
                + "This game has been programmed by Patrick P&ouml;schke.<br><br>"
                + "Version: 4.0<br>"
                + "Look for the latest version or other games on:<br>" 
                + "https://patrick-poeschke.itch.io/<br><br>"
                + "</blockquote></body></html>";
        
        
        stringShowStrategies="show strategies";
        buttonShowStrategies.setText(stringShowStrategies);
        stringShowStrategiesDialog="If switched on, the computer will tell its attempted strategies, when playing.";
        stringYes="yes";
        stringNoDefault="no (default)";
        stringServeRandomCard="<html><body><blockquote>can serve, play random card</blockquote></body></html>";
        stringPlayRandomCard="<html><body><blockquote>play random card</blockquote></body></html>";

        stringServeJack="<html><body><blockquote>can serve a jack</blockquote></body></html>";
        stringServeTrumpHigh="<html><body><blockquote>can serve trump ace or 10</blockquote></body></html>";
        stringServeHigh="<html><body><blockquote>can serve ace or 10</blockquote></body></html>";
        stringServeTrumpMid="<html><body><blockquote>can serve trump king or queen</blockquote></body></html>";
        stringServeMid="<html><body><blockquote>can serve king or queen</blockquote></body></html>";
        stringServeTrumpLow="<html><body><blockquote>can serve trump low card</blockquote></body></html>";
        stringServeLow="<html><body><blockquote>can serve low card</blockquote></body></html>";
        stringDiscardLow="<html><body><blockquote>try to discard low card</blockquote></body></html>";
        stringDiscardColor="<html><body><blockquote>can't serve, try to discard color</blockquote></body></html>";
        stringTrumpHigh="<html><body><blockquote>try to trump the high card</blockquote></body></html>";
        stringWasteLeast="<html><body><blockquote>try wasting least points or good cards</blockquote></body></html>";
        stringTryBestMove="<html><body><blockquote>try to pull off best move</blockquote></body></html>";
        stringTryBestMoveWithCounting="<html><body><blockquote>pull off best move by counting cards</blockquote></body></html>";
        stringTryBestMoveTrump="<html><body><blockquote>try best move with trump</blockquote></body></html>";
        stringBestMoveTrumpWithCounting="<html><body><blockquote>make best move with trump by counting cards</blockquote></body></html>";
        stringDefServeAce="<html><body><blockquote>definitely servable ace</blockquote></body></html>";
        stringDefServeTen="<html><body><blockquote>definitely servable 10</blockquote></body></html>";
        stringLikelyAce="<html><body><blockquote>likely servable ace</blockquote></body></html>";
        stringLikelyTen="<html><body><blockquote>likely servable 10</blockquote></body></html>";
        stringLikelyKing="<html><body><blockquote>likely servable king</blockquote></body></html>";
        stringLikelyQueen="<html><body><blockquote>likely servable queen</blockquote></body></html>";
        stringLureJack="<html><body><blockquote>try to lure out a jack</blockquote></body></html>";
        stringTryDefUnserveLow="<html><body><blockquote>try definitely unservable low card</blockquote></body></html>";
        stringLikelyUnserveLow="<html><body><blockquote>likely unservable low card</blockquote></body></html>";
        stringTryLow="<html><body><blockquote>try to play a low card</blockquote></body></html>";

        stringBestMove="<html><body><blockquote>pull off best move</blockquote></body></html>";
        stringBestMoveTrump="<html><body><blockquote>pull off best move with trump</blockquote></body></html>";
        stringTrumpHighWithJack="<html><body><blockquote>try getting trump ace or 10 with a jack</blockquote></body></html>";
        stringServeAce="<html><body><blockquote>servable non-trump ace</blockquote></body></html>";
        stringServeTen="<html><body><blockquote>servable 10</blockquote></body></html>";
        stringServeTrumpAce="<html><body><blockquote>servable trump ace</blockquote></body></html>";
        stringServeKing="<html><body><blockquote>servable king</blockquote></body></html>";
        stringServeQueen="<html><body><blockquote>servable queen</blockquote></body></html>";
        stringUnserveNoTrump="<html><body><blockquote>try unservable card, when player has no trump</blockquote></body></html>";
        
    }
    
    
}
