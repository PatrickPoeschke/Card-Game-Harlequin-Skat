package harlekinskat4;

/**
 * This is a video game called "Harlekin Skat".
 * It is a variation of skat, in which each player knows the cards that the opponent can use later.
 * One can play against the cpu on 3 different difficulty levels.
 * 
 * -- Version Info --
 * started progamming in Java at 1Jul2018
 * started programming Harlequin Skat at 10Sep2018
 * 
 * version 1.0 info: 10-24Sep2018
 * final game practically finsihed, running
 * actually, I thought I was finished here, but it followed a lot of debugging of minor bugs
 * 
 * version 1.1 info: 22oct2018
 * debugging
 * 
 * version 1.2 info: 18dec2018
 * debugging
 * 
 * version 2.0 info: 22-26dec2018
 * did a complete rewriting of half of the whole source code for better readability
 * major debugging
 * added a more likely way of making 'best move' on normal level
 * (Here I considered this game complete, and didn't want to improve it any more.)
 * 
 * version 2.1 info: 7Jan2019
 * just added the "close-on-exit"-line (way too late: in future programs add it right from the start!)
 * 
 * version 3.0 info: 8-9Jan2019
 * new feature: full support of both languages: English and German (added language chooser option)
 * this required quite some rewriting
 *  -- I consider this game as complete for now. --
 * Originally, I wanted to translate it to other languages as well.
 * However, terms like 'jack of clubs' are too tricky for the translation machines nowadays.
 * So, unless some (native) speaker wants to translate it, it will stay like this.
 * Either way, the way it is written now, will make it fairly easy to add more languages:
 * One just has to translate the file English.JAVA (ca. 175lines of text),
 * add it as extra file and add a button (+listener).
 * 
 * version 4.0 info: 19-25April2019
 * used new programming skills obtained while programming other, more complex card game to 
 * add new feature: make all windows scalable (including full screen mode)
 * completely rewrote the code using more setter/getter methods (again, way too late)
 * found minor bugs in AI
 * slightly improoved AI
 * improoved looks of jacks such that they stand out more
 * (hopefully helps beginners not to mix them up with other cards so easily)
 * added feature: option of letting computer tell its current attempted strategies
 * (hopefully helps beginners to learn the game)
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

// This class contains all the visualizations and the most basic mechanics of the game.
public class Harlekinskat4 extends JFrame implements ActionListener, ComponentListener
{
    // declaring all fields here
    // those used in other classes make static imports of the fields
    public static Harlekinskat4 windowl;
    public static Languages window2;
    public static int languageAsInt=1;
    
    public static int initialFrameWidth=1010;
    public static int initialFrameHeight=780;
    public static int currentFrameWidth=1010;
    public static int currentFrameHeight=780;
    
    public JLayeredPane layeredPane;
    
    public static String stringWinCards="<html><body><blockquote>You win both cards.</blockquote></body></html>";
    public static String stringLoseCards="<html><body><blockquote>Computer wins both cards.</blockquote></body></html>";
    
    public static String stringPlayedJ1="<html><body><blockquote>You played the highest jack.</blockquote></body></html>";
    public static String stringPlayedJ2="<html><body><blockquote>You played the 2nd highest jack.</blockquote></body></html>";
    public static String stringPlayedJ3="<html><body><blockquote>You played the 3rd highest jack.</blockquote></body></html>";
    public static String stringPlayedJ4="<html><body><blockquote>You played the lowest jack.</blockquote></body></html>";
    
    public static String stringPlayedTA="<html><body><blockquote>You played the trump ace.</blockquote></body></html>";
    public static String stringPlayedT10="<html><body><blockquote>You played the trump 10.</blockquote></body></html>";
    public static String stringPlayedTK="<html><body><blockquote>You played the trump king.</blockquote></body></html>";
    public static String stringPlayedTQ="<html><body><blockquote>You played the trump queen.</blockquote></body></html>";
    public static String stringPlayedT9="<html><body><blockquote>You played the trump 9.</blockquote></body></html>";
    public static String stringPlayedT8="<html><body><blockquote>You played the trump 8.</blockquote></body></html>";
    public static String stringPlayedT7="<html><body><blockquote>You played the trump 7.</blockquote></body></html>";
    
    public static String stringPlayedCA="<html><body><blockquote>You played the ace of clubs.</blockquote></body></html>";
    public static String stringPlayedC10="<html><body><blockquote>You played the 10 of clubs.</blockquote></body></html>";
    public static String stringPlayedCK="<html><body><blockquote>You played the king of clubs.</blockquote></body></html>";
    public static String stringPlayedCQ="<html><body><blockquote>You played the queen of clubs.</blockquote></body></html>";
    public static String stringPlayedC9="<html><body><blockquote>You played the 9 of clubs.</blockquote></body></html>";
    public static String stringPlayedC8="<html><body><blockquote>You played the 8 of clubs.</blockquote></body></html>";
    public static String stringPlayedC7="<html><body><blockquote>You played the 7 of clubs.</blockquote></body></html>";
    
    public static String stringPlayedSA="<html><body><blockquote>You played the ace of spades.</blockquote></body></html>";
    public static String stringPlayedS10="<html><body><blockquote>You played the 10 of spades.</blockquote></body></html>";
    public static String stringPlayedSK="<html><body><blockquote>You played the king of spades.</blockquote></body></html>";
    public static String stringPlayedSQ="<html><body><blockquote>You played the queen of spades.</blockquote></body></html>";
    public static String stringPlayedS9="<html><body><blockquote>You played the 9 of spades.</blockquote></body></html>";
    public static String stringPlayedS8="<html><body><blockquote>You played the 8 of spades.</blockquote></body></html>";
    public static String stringPlayedS7="<html><body><blockquote>You played the 7 of spades.</blockquote></body></html>";
    
    public static String stringPlayedHA="<html><body><blockquote>You played the ace of hearts.</blockquote></body></html>";
    public static String stringPlayedH10="<html><body><blockquote>You played the 10 of hearts.</blockquote></body></html>";
    public static String stringPlayedHK="<html><body><blockquote>You played the king of hearts.</blockquote></body></html>";
    public static String stringPlayedHQ="<html><body><blockquote>You played the queen of hearts.</blockquote></body></html>";
    public static String stringPlayedH9="<html><body><blockquote>You played the 9 of hearts.</blockquote></body></html>";
    public static String stringPlayedH8="<html><body><blockquote>You played the 8 of hearts.</blockquote></body></html>";
    public static String stringPlayedH7="<html><body><blockquote>You played the 7 of hearts.</blockquote></body></html>";
    
    public static String stringPlayedDA="<html><body><blockquote>You played the ace of diamonds.</blockquote></body></html>";
    public static String stringPlayedD10="<html><body><blockquote>You played the 10 of diamonds.</blockquote></body></html>";
    public static String stringPlayedDK="<html><body><blockquote>You played the king of diamonds.</blockquote></body></html>";
    public static String stringPlayedDQ="<html><body><blockquote>You played the queen of diamonds.</blockquote></body></html>";
    public static String stringPlayedD9="<html><body><blockquote>You played the 9 of diamonds.</blockquote></body></html>";
    public static String stringPlayedD8="<html><body><blockquote>You played the 8 of diamonds.</blockquote></body></html>";
    public static String stringPlayedD7="<html><body><blockquote>You played the 7 of diamonds.</blockquote></body></html>";
    
    public static String stringJ1Text="J jack of clubs (2)";
    public static String stringJ2Text="J jack of spades (2)";
    public static String stringJ3Text="J jack of hearts (2)";
    public static String stringJ4Text="J jack of diamonds (2)";
    
    public static String stringCAText="A ace of clubs (11)";
    public static String stringC10Text="10 of clubs (10)";
    public static String stringCKText="K king of clubs (4)";
    public static String stringCQText="Q queen of clubs (3)";
    public static String stringC9Text="9 of clubs (0)";
    public static String stringC8Text="8 of clubs (0)";
    public static String stringC7Text="7 of clubs (0)";
    
    public static String stringSAText="A ace of spades (11)";
    public static String stringS10Text="10 of spades (10)";
    public static String stringSKText="K king of spades (4)";
    public static String stringSQText="Q queen of spades (3)";
    public static String stringS9Text="9 of spades (0)";
    public static String stringS8Text="8 of spades (0)";
    public static String stringS7Text="7 of spades (0)";
    
    public static String stringHAText="A ace of hearts (11)";
    public static String stringH10Text="10 of hearts (10)";
    public static String stringHKText="K king of hearts (4)";
    public static String stringHQText="Q queen of hearts (3)";
    public static String stringH9Text="9 of hearts (0)";
    public static String stringH8Text="8 of hearts (0)";
    public static String stringH7Text="7 of hearts (0)";
    
    public static String stringDAText="A ace of diamonds (11)";
    public static String stringD10Text="10 of diamonds (10)";
    public static String stringDKText="K king of diamonds (4)";
    public static String stringDQText="Q queen of diamonds (3)";
    public static String stringD9Text="9 of diamonds (0)";
    public static String stringD8Text="8 of diamonds (0)";
    public static String stringD7Text="7 of diamonds (0)";
    
    public static String stringGameOver="GAME OVER";
    public static String stringDraw="<br> 60 points. <br><br> Draw!<br><br>";
    public static String stringWin=" points. <br><br> You Win!<br><br>";
    public static String stringLose=" points. <br><br> You Lose!<br><br>";
    public static String stringPointCounting="<html><body><blockquote> counting the obtained points:<br><br>";
    public static String stringRestartMessage="Start a new game by pressing the start/reset button.";
    
    public static String stringDifficultyDialog1="Choose difficulty level";
    public static String stringDifficultyDialog2="set CPU level";
    public static String stringEasy="easy/random";
    public static String stringNormal="normal";
    public static String stringHard="hard/cheating";
    public static String stringEasyAffirm="<html><body><blockquote>Difficulty level is easy.</blockquote></body></html>";
    public static String stringNormalAffirm="<html><body><blockquote>Difficulty level is normal.</blockquote></body></html>";
    public static String stringHardAffirm="<html><body><blockquote>Difficulty level is hard.</blockquote></body></html>";
    
    public static String stringDealing="<html><body><blockquote>dealing</blockquote></body></html>";
    public static String stringCoin="<html><body><blockquote>coin flip</blockquote></body></html>";
    
    public static String stringTrumpDialog="Choose trump suit/color";
    public static String stringClubs="<html><body>clubs/black &clubs;</body></html>";
    public static String stringSpades="<html><body>spades/green &spades;</body></html>";
    public static String stringHearts="<html><body>hearts/red &hearts;</body></html>";
    public static String stringDiamonds="<html><body>diamonds/yellow &diams;</body></html>";
    
    public static String stringClubsAffirm1="<html><body><blockquote>Clubs (black &clubs;) is trump. Computer begins to play.</blockquote></body></html>";
    public static String stringSpadesAffirm1="<html><body><blockquote>Spades (green &spades;) is trump. Computer begins to play.</blockquote></body></html>";
    public static String stringHeartsAffirm1="<html><body><blockquote>Hearts (red &hearts;) is trump. Computer begins to play.</blockquote></body></html>";
    public static String stringDiamondsAffirm1="<html><body><blockquote>Diamonds (yellow &diams;) is trump. Computer begins to play.</blockquote></body></html>";
    public static String stringClubsAffirm2="<html><body><blockquote>Computer chooses clubs (black &clubs;) as trump. Your turn.</blockquote></body></html>";
    public static String stringSpadesAffirm2="<html><body><blockquote>Computer chooses spades (green &spades;) as trump. Your turn.</blockquote></body></html>";
    public static String stringHeartsAffirm2="<html><body><blockquote>Computer chooses hearts (red &hearts;) as trump. Your turn.</blockquote></body></html>";
    public static String stringDiamondsAffirm2="<html><body><blockquote>Computer chooses diamonds (yellow &diams;) as trump. Your turn.</blockquote></body></html>";
    
    public static String stringShowStrategies="show strategies";
    public static String stringShowStrategiesDialog="If switched on, the computer will tell its attempted strategies, when playing.";
    public static String stringYes="yes";
    public static String stringNoDefault="no (default)";
    
    public static String stringServeRandomCard="<html><body><blockquote>can serve, play random card</blockquote></body></html>";
    public static String stringPlayRandomCard="<html><body><blockquote>play random card</blockquote></body></html>";
    
    public static String stringServeJack="<html><body><blockquote>can serve a jack</blockquote></body></html>";
    public static String stringServeTrumpHigh="<html><body><blockquote>can serve trump ace or 10</blockquote></body></html>";
    public static String stringServeHigh="<html><body><blockquote>can serve ace or 10</blockquote></body></html>";
    public static String stringServeTrumpMid="<html><body><blockquote>can serve trump king or queen</blockquote></body></html>";
    public static String stringServeMid="<html><body><blockquote>can serve king or queen</blockquote></body></html>";
    public static String stringServeTrumpLow="<html><body><blockquote>can serve trump low card</blockquote></body></html>";
    public static String stringServeLow="<html><body><blockquote>can serve low card</blockquote></body></html>";
    public static String stringDiscardLow="<html><body><blockquote>try to discard low card</blockquote></body></html>";
    public static String stringDiscardColor="<html><body><blockquote>can't serve, try to discard color</blockquote></body></html>";
    public static String stringTrumpHigh="<html><body><blockquote>try to trump the high card</blockquote></body></html>";
    public static String stringWasteLeast="<html><body><blockquote>try wasting least points or good cards</blockquote></body></html>";
    public static String stringTryBestMove="<html><body><blockquote>try to pull off best move</blockquote></body></html>";
    public static String stringTryBestMoveWithCounting="<html><body><blockquote>pull off best move by counting cards</blockquote></body></html>";
    public static String stringTryBestMoveTrump="<html><body><blockquote>try best move with trump</blockquote></body></html>";
    public static String stringBestMoveTrumpWithCounting="<html><body><blockquote>make best move with trump by counting cards</blockquote></body></html>";
    public static String stringDefServeAce="<html><body><blockquote>definitely servable ace</blockquote></body></html>";
    public static String stringDefServeTen="<html><body><blockquote>definitely servable 10</blockquote></body></html>";
    public static String stringLikelyAce="<html><body><blockquote>likely servable ace</blockquote></body></html>";
    public static String stringLikelyTen="<html><body><blockquote>likely servable 10</blockquote></body></html>";
    public static String stringLikelyKing="<html><body><blockquote>likely servable king</blockquote></body></html>";
    public static String stringLikelyQueen="<html><body><blockquote>likely servable queen</blockquote></body></html>";
    public static String stringLureJack="<html><body><blockquote>try to lure out a jack</blockquote></body></html>";
    public static String stringTryDefUnserveLow="<html><body><blockquote>try definitely unservable low card</blockquote></body></html>";
    public static String stringLikelyUnserveLow="<html><body><blockquote>likely unservable low card</blockquote></body></html>";
    public static String stringTryLow="<html><body><blockquote>try to play a low card</blockquote></body></html>";
    
    public static String stringBestMove="<html><body><blockquote>pull off best move</blockquote></body></html>";
    public static String stringBestMoveTrump="<html><body><blockquote>pull off best move with trump</blockquote></body></html>";
    public static String stringTrumpHighWithJack="<html><body><blockquote>try getting trump ace or 10 with a jack</blockquote></body></html>";
    public static String stringServeAce="<html><body><blockquote>servable non-trump ace</blockquote></body></html>";
    public static String stringServeTen="<html><body><blockquote>servable 10</blockquote></body></html>";
    public static String stringServeTrumpAce="<html><body><blockquote>servable trump ace</blockquote></body></html>";
    public static String stringServeKing="<html><body><blockquote>servable king</blockquote></body></html>";
    public static String stringServeQueen="<html><body><blockquote>servable queen</blockquote></body></html>";
    public static String stringUnserveNoTrump="<html><body><blockquote>try unservable card, when player has no trump</blockquote></body></html>";
    
    public static String stringRuleTitle="rules of the game";
    public static String stringRuleText; // defined in class English (and set at beginning of game)
    
    // non-interactable buttons for facedown/invisible cards
    public static JButton buttonFacedown1CPU;
    public static JButton buttonFacedown2CPU;
    public static JButton buttonFacedown3CPU;
    public static JButton buttonFacedown4CPU;
    public static JButton buttonFacedown5CPU;
    public static JButton buttonFacedown6CPU;
    public static JButton buttonFacedown7CPU;
    public static JButton buttonFacedown8CPU;
    public static JButton buttonFacedown9CPU;
    
    public static JButton buttonFacedown1Player;
    public static JButton buttonFacedown2Player;
    public static JButton buttonFacedown3Player;
    public static JButton buttonFacedown4Player;
    public static JButton buttonFacedown5Player;
    public static JButton buttonFacedown6Player;
    public static JButton buttonFacedown7Player;
    public static JButton buttonFacedown8Player;
    public static JButton buttonFacedown9Player;
    
    // buttons of visible cards of CPU and player
    public static JButton buttonCard1CPU;
    public static JButton buttonCard2CPU;
    public static JButton buttonCard3CPU;
    public static JButton buttonCard4CPU;
    public static JButton buttonCard5CPU;
    public static JButton buttonCard6CPU;
    public static JButton buttonCard7CPU;
    public static JButton buttonCard8CPU;
    
    public static JButton buttonCard1Player;
    public static JButton buttonCard2Player;
    public static JButton buttonCard3Player;
    public static JButton buttonCard4Player;
    public static JButton buttonCard5Player;
    public static JButton buttonCard6Player;
    public static JButton buttonCard7Player;
    public static JButton buttonCard8Player;
    
    // menu buttons
    public static JButton buttonRules;
    public static JButton buttonStart;
    public static JButton buttonDifficulty;
    public static JButton buttonShowStrategies;
    public static JButton buttonChooseLanguage;
    
    // buttons for 2 cards played and put in the middle of the screen
    public static JButton buttonCardPlayedCPU;
    public static JButton buttonCardPlayedPlayer;
    
    // a few variables
    public static int difficulty=2; // difficulty level 1=easy, 2=normal, 3=hard
    public static int trumpColor=1; // number of the trump suit (1=clubs, 2=spades, 3=hearts, 4=diamonds)
    
    // by setting this value to to true, one gets the chance of setting the CPU such, that you can see the strategies
    public static boolean isAddingSetShowAIStrategyMenuOption=true;
    public static boolean isShowingAIStrategies;
    
    public static int counter1; // for counting things (like number of cards)
    public static int counter2;
    public static int counter3;
    public static int counter4;
    
    public static boolean playerWinsCards; // for deciding who takes the two cards home
    public static boolean islockedFromPlaying=true; // just for preventing the player from accidently playing several cards at once
    
    // if the number of played turns reached 16, the endgame is initiated and the obtained points are counted 
    public static int numberOfTurns=0;
    public static boolean cpuReacts; // if true, it is the player's turn, if false, it is the CPU's turn
    
    public static int numberOfGames=-1; // number of "played" games (for deciding who chooses trump) [one can skip game by resetting it]
    public static int coinflip;
    
    public static int currentColor; // the color/suit of played card
    public static boolean trumpPlayed; // if played card is a trump card
    public static boolean servable; // if the player can "serve" the color played by the computer
    
    // for emulating memory (remembers if opponent could not serve a color)
    public static boolean couldntServeClubsBefore=false;
    public static boolean couldntServeSpadesBefore=false;
    public static boolean couldntServeHeartsBefore=false;
    public static boolean couldntServeDiamondsBefore=false;
    public static boolean couldntServeTrumpBefore=false;
    // for emulating memory (remembers how many cards of that color have been turned around since opponent could not serve it)
    public static int numberOfClubCardsSinceThen=0;
    public static int numberOfSpadeCardsSinceThen=0;
    public static int numberOfHeartCardsSinceThen=0;
    public static int numberOfDiamondCardsSinceThen=0;
    public static int numberOfTrumpCardsSinceThen=0;
    
    // declare all kinds of objects already
    public Skatcard clubJ;
    public Skatcard spadeJ;
    public Skatcard heartJ;
    public Skatcard diamJ;
    public Skatcard clubA;
    public Skatcard club10;
    public Skatcard clubK;
    public Skatcard clubQ;
    public Skatcard club9;
    public Skatcard club8;
    public Skatcard club7;
    public Skatcard spadeA;
    public Skatcard spade10;
    public Skatcard spadeK;
    public Skatcard spadeQ;
    public Skatcard spade9;
    public Skatcard spade8;
    public Skatcard spade7;
    public Skatcard heartA;
    public Skatcard heart10;
    public Skatcard heartK;
    public Skatcard heartQ;
    public Skatcard heart9;
    public Skatcard heart8;
    public Skatcard heart7;
    public Skatcard diamA;
    public Skatcard diam10;
    public Skatcard diamK;
    public Skatcard diamQ;
    public Skatcard diam9;
    public Skatcard diam8;
    public Skatcard diam7;
    public Skatcard placeholder;
    public Skatcard[] cards=new Skatcard[33];
    
    public String imagePathClubJ="/images/kreuzB.png";
    public String imagePathSpadeJ="/images/pikB.png";
    public String imagePathHeartJ="/images/herzB.png";
    public String imagePathDiamJ="/images/karoB.png";
    public String imagePathClubA="/images/kreuzA.png";
    public String imagePathClub10="/images/kreuz10.png";
    public String imagePathClubK="/images/kreuzK.png";
    public String imagePathClubQ="/images/kreuzD.png";
    public String imagePathClub9="/images/kreuz9.png";
    public String imagePathClub8="/images/kreuz8.png";
    public String imagePathClub7="/images/kreuz7.png";
    public String imagePathSpadeA="/images/pikA.png";
    public String imagePathSpade10="/images/pik10.png";
    public String imagePathSpadeK="/images/pikK.png";
    public String imagePathSpadeQ="/images/pikD.png";
    public String imagePathSpade9="/images/pik9.png";
    public String imagePathSpade8="/images/pik8.png";
    public String imagePathSpade7="/images/pik7.png";
    public String imagePathHeartA="/images/herzA.png";
    public String imagePathHeart10="/images/herz10.png";
    public String imagePathHeartK="/images/herzK.png";
    public String imagePathHeartQ="/images/herzD.png";
    public String imagePathHeart9="/images/herz9.png";
    public String imagePathHeart8="/images/herz8.png";
    public String imagePathHeart7="/images/herz7.png";
    public String imagePathDiamA="/images/karoA.png";
    public String imagePathDiam10="/images/karo10.png";
    public String imagePathDiamK="/images/karoK.png";
    public String imagePathDiamQ="/images/karoD.png";
    public String imagePathDiam9="/images/karo9.png";
    public String imagePathDiam8="/images/karo8.png";
    public String imagePathDiam7="/images/karo7.png";
    public String imagePathPlaceholder="/images/facedown.png";
    
    public SkatcardDeck Deck;
    
    public Skatcard playedCardPlayer; // just saves the card played by the player
    public Skatcard playedCardCPU; // just saves the card played by the computer
        
    // declare the hands
    public Hand playableCPU;
    public Hand backhandCPU;
    public Hand playable;
    public Hand backhandPlayer;
        
    public static Integer[] array32;
    
    public static String wonCards;
    public static int wonPoints;
        
    // constructor
    public Harlekinskat4(){
        this.setTitle("Harlequin Skat");
        this.setSize(initialFrameWidth, initialFrameHeight); // window for the whole game
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().addComponentListener(this);
        
        // create structure for all buttons already
        layeredPane = new JLayeredPane();

        // first all facedown cards (later only visibility must be changed)
        buttonFacedown1CPU = new JButton();
        buttonFacedown1CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown1CPU, 0, 0, 100, 200);
        layeredPane.add(buttonFacedown1CPU, new Integer(201));
        
        buttonFacedown2CPU = new JButton();
        buttonFacedown2CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown2CPU, 125, 0, 100, 200);
        layeredPane.add(buttonFacedown2CPU, new Integer(202));
        
        buttonFacedown3CPU = new JButton();
        buttonFacedown3CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown3CPU, 250, 0, 100, 200);
        layeredPane.add(buttonFacedown3CPU, new Integer(203));
        
        buttonFacedown4CPU = new JButton();
        buttonFacedown4CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown4CPU, 375, 0, 100, 200);
        layeredPane.add(buttonFacedown4CPU, new Integer(204));
        
        buttonFacedown5CPU = new JButton();
        buttonFacedown5CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown5CPU, 500, 0, 100, 200);
        layeredPane.add(buttonFacedown5CPU, new Integer(205));
        
        buttonFacedown6CPU = new JButton();
        buttonFacedown6CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown6CPU, 625, 0, 100, 200);
        layeredPane.add(buttonFacedown6CPU, new Integer(206));
        
        buttonFacedown7CPU = new JButton();
        buttonFacedown7CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown7CPU, 750, 0, 100, 200);
        layeredPane.add(buttonFacedown7CPU, new Integer(207));
        
        buttonFacedown8CPU = new JButton();
        buttonFacedown8CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown8CPU, 875, 0, 100, 200);
        layeredPane.add(buttonFacedown8CPU, new Integer(208));
        
        buttonFacedown9CPU = new JButton();
        buttonFacedown9CPU.setIcon(new RescaledIcon(this.getClass().getResource("/images/facedownSide.png")));
        setButtonProperties(buttonFacedown9CPU, 775, 250, 200, 100);
        layeredPane.add(buttonFacedown9CPU);
        
        
        buttonFacedown1Player = new JButton();
        buttonFacedown1Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown1Player, 0, 525, 100, 200);
        layeredPane.add(buttonFacedown1Player, new Integer(211));
        
        buttonFacedown2Player = new JButton();
        buttonFacedown2Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown2Player, 125, 525, 100, 200);
        layeredPane.add(buttonFacedown2Player, new Integer(212));
        
        buttonFacedown3Player = new JButton();
        buttonFacedown3Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown3Player, 250, 525, 100, 200);
        layeredPane.add(buttonFacedown3Player, new Integer(213));
        
        buttonFacedown4Player = new JButton();
        buttonFacedown4Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown4Player, 375, 525, 100, 200);
        layeredPane.add(buttonFacedown4Player, new Integer(214));
        
        buttonFacedown5Player = new JButton();
        buttonFacedown5Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown5Player, 500, 525, 100, 200);
        layeredPane.add(buttonFacedown5Player, new Integer(215));
        
        buttonFacedown6Player = new JButton();
        buttonFacedown6Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown6Player, 625, 525, 100, 200);
        layeredPane.add(buttonFacedown6Player, new Integer(216));
        
        buttonFacedown7Player = new JButton();
        buttonFacedown7Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown7Player, 750, 525, 100, 200);
        layeredPane.add(buttonFacedown7Player, new Integer(217));
        
        buttonFacedown8Player = new JButton();
        buttonFacedown8Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonFacedown8Player, 875, 525, 100, 200);
        layeredPane.add(buttonFacedown8Player, new Integer(218));
        
        buttonFacedown9Player = new JButton();
        buttonFacedown9Player.setIcon(new RescaledIcon(this.getClass().getResource("/images/facedownSide.png")));
        setButtonProperties(buttonFacedown9Player, 775, 400, 200, 100);
        layeredPane.add(buttonFacedown9Player);
        
        buttonRules = new JButton("rules");
        buttonRules.setBounds(50, 250, 200, 50);
        buttonRules.addActionListener(this);
        layeredPane.add(buttonRules);
        
        buttonStart = new JButton("start/reset");
        buttonStart.setBounds(50, 300, 200, 50);
        buttonStart.addActionListener(this);
        layeredPane.add(buttonStart);
        
        buttonDifficulty = new JButton("set CPU level");
        buttonDifficulty.setBounds(50, 350, 200, 50);
        buttonDifficulty.addActionListener(this);
        layeredPane.add(buttonDifficulty);
        
        buttonShowStrategies = new JButton("show strategies");
        buttonShowStrategies.setBounds(50, 400, 200, 50);
        buttonShowStrategies.addActionListener(this);
        layeredPane.add(buttonShowStrategies);
        
        buttonChooseLanguage = new JButton("choose language");
        buttonChooseLanguage.setBounds(50, 450, 200, 50);
        buttonChooseLanguage.addActionListener(this);
        layeredPane.add(buttonChooseLanguage);
        
        // now visible cards
        buttonCard1CPU = new JButton();
        buttonCard1CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard1CPU, 25, 25, 100, 200);
        layeredPane.add(buttonCard1CPU, new Integer(1010));
        
        buttonCard2CPU = new JButton();
        buttonCard2CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard2CPU, 150, 25, 100, 200);
        layeredPane.add(buttonCard2CPU, new Integer(1020));
        
        buttonCard3CPU = new JButton();
        buttonCard3CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard3CPU, 275, 25, 100, 200);
        layeredPane.add(buttonCard3CPU, new Integer(1030));
        
        buttonCard4CPU = new JButton();
        buttonCard4CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard4CPU, 400, 25, 100, 200);
        layeredPane.add(buttonCard4CPU, new Integer(1040));
        
        buttonCard5CPU = new JButton();
        buttonCard5CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard5CPU, 525, 25, 100, 200);
        layeredPane.add(buttonCard5CPU, new Integer(1050));
        
        buttonCard6CPU = new JButton();
        buttonCard6CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard6CPU, 650, 25, 100, 200);
        layeredPane.add(buttonCard6CPU, new Integer(1060));
        
        buttonCard7CPU = new JButton();
        buttonCard7CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard7CPU, 775, 25, 100, 200);
        layeredPane.add(buttonCard7CPU, new Integer(1070));
        
        buttonCard8CPU = new JButton();
        buttonCard8CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard8CPU, 900, 25, 100, 200);
        layeredPane.add(buttonCard8CPU, new Integer(1080));
        
        buttonCard1Player = new JButton();
        buttonCard1Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard1Player, 25, 550, 100, 200);
        buttonCard1Player.addActionListener(this);
        layeredPane.add(buttonCard1Player, new Integer(1110));
        
        buttonCard2Player = new JButton();
        buttonCard2Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard2Player, 150, 550, 100, 200);
        buttonCard2Player.addActionListener(this);
        layeredPane.add(buttonCard2Player, new Integer(1120));
        
        buttonCard3Player = new JButton();
        buttonCard3Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard3Player, 275, 550, 100, 200);
        buttonCard3Player.addActionListener(this);
        layeredPane.add(buttonCard3Player, new Integer(1130));
        
        buttonCard4Player = new JButton();
        buttonCard4Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard4Player, 400, 550, 100, 200);
        buttonCard4Player.addActionListener(this);
        layeredPane.add(buttonCard4Player, new Integer(1140));
        
        buttonCard5Player = new JButton();
        buttonCard5Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard5Player, 525, 550, 100, 200);
        buttonCard5Player.addActionListener(this);
        layeredPane.add(buttonCard5Player, new Integer(1150));
        
        buttonCard6Player = new JButton();
        buttonCard6Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard6Player, 650, 550, 100, 200);
        buttonCard6Player.addActionListener(this);
        layeredPane.add(buttonCard6Player, new Integer(1160));
        
        buttonCard7Player = new JButton();
        buttonCard7Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard7Player, 775, 550, 100, 200);
        buttonCard7Player.addActionListener(this);
        layeredPane.add(buttonCard7Player, new Integer(1170));
        
        buttonCard8Player = new JButton();
        buttonCard8Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCard8Player, 900, 550, 100, 200);
        buttonCard8Player.addActionListener(this);
        layeredPane.add(buttonCard8Player, new Integer(1180));
        
        
        buttonCardPlayedCPU = new JButton();
        buttonCardPlayedCPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCardPlayedCPU, 375, 250, 100, 200);
        layeredPane.add(buttonCardPlayedCPU);
        
        buttonCardPlayedPlayer = new JButton();
        buttonCardPlayedPlayer.setIcon(new RescaledIcon(this.getClass().getResource(imagePathPlaceholder)));
        setButtonProperties(buttonCardPlayedPlayer, 525, 300, 100, 200);
        layeredPane.add(buttonCardPlayedPlayer);
        
        // The objects have to be created here (not in main method somehow)
        // because otherwise the actionlisteners will not find them!
        
        // create all 32 cards with all their properties (last two properties set to 1 first, they change later on)
        placeholder = new Skatcard (0, "no card", imagePathPlaceholder, 0, 0, 0, 0, 0); // placeholder for the part of the hand that is missing because already played
        clubJ = new Skatcard (1, stringJ1Text, imagePathClubJ, 1, 1, 2, 1, 1);
        spadeJ = new Skatcard (2, stringJ2Text, imagePathSpadeJ, 1, 2, 2, 1, 1);
        heartJ = new Skatcard (3, stringJ3Text, imagePathHeartJ, 1, 3, 2, 1, 1);
        diamJ = new Skatcard (4, stringJ4Text, imagePathDiamJ, 1, 4, 2, 1, 1);
        clubA = new Skatcard (5, stringCAText, imagePathClubA, 2, 1, 11, 1, 1);
        club10 = new Skatcard (6, stringC10Text, imagePathClub10, 3, 1, 10, 1, 1);
        clubK = new Skatcard (7, stringCKText, imagePathClubK, 4, 1, 4, 1, 1);
        clubQ = new Skatcard (8, stringCQText, imagePathClubQ, 5, 1, 3, 1, 1);
        club9 = new Skatcard (9, stringC9Text, imagePathClub9, 6, 1, 0, 1, 1);
        club8 = new Skatcard (10, stringC8Text, imagePathClub8, 7, 1, 0, 1, 1);
        club7 = new Skatcard (11, stringC7Text, imagePathClub7, 8, 1, 0, 1, 1);
        spadeA = new Skatcard (12, stringSAText, imagePathSpadeA, 2, 2, 11, 1, 1);
        spade10 = new Skatcard (13, stringS10Text, imagePathSpade10, 3, 2, 10, 1, 1);
        spadeK = new Skatcard (14, stringSKText, imagePathSpadeK, 4, 2, 4, 1, 1);
        spadeQ = new Skatcard (15, stringSQText, imagePathSpadeQ, 5, 2, 3, 1, 1);
        spade9 = new Skatcard (16, stringS9Text, imagePathSpade9, 6, 2, 0, 1, 1);
        spade8 = new Skatcard (17, stringS8Text, imagePathSpade8, 7, 2, 0, 1, 1);
        spade7 = new Skatcard (18, stringS7Text, imagePathSpade7, 8, 2, 0, 1, 1);
        heartA = new Skatcard (19, stringHAText, imagePathHeartA, 2, 3, 11, 1, 1);
        heart10 = new Skatcard (20, stringH10Text, imagePathHeart10, 3, 3, 10, 1, 1);
        heartK = new Skatcard (21, stringHKText, imagePathHeartK, 4, 3, 4, 1, 1);
        heartQ = new Skatcard (22, stringHQText, imagePathHeartQ, 5, 3, 3, 1, 1);
        heart9 = new Skatcard (23, stringH9Text, imagePathHeart9, 6, 3, 0, 1, 1);
        heart8 = new Skatcard (24, stringH8Text, imagePathHeart8, 7, 3, 0, 1, 1);
        heart7 = new Skatcard (25, stringH7Text, imagePathHeart7, 8, 3, 0, 1, 1);
        diamA = new Skatcard (26, stringDAText, imagePathDiamA, 2, 4, 11, 1, 1);
        diam10 = new Skatcard (27, stringD10Text, imagePathDiam10, 3, 4, 10, 1, 1);
        diamK = new Skatcard (28, stringDKText, imagePathDiamK, 4, 4, 4, 1, 1);
        diamQ = new Skatcard (29, stringDQText, imagePathDiamQ, 5, 4, 3, 1, 1);
        diam9 = new Skatcard (30, stringD9Text, imagePathDiam9, 6, 4, 0, 1, 1);
        diam8 = new Skatcard (31, stringD8Text, imagePathDiam8, 7, 4, 0, 1, 1);
        diam7 = new Skatcard (32, stringD7Text, imagePathDiam7, 8, 4, 0, 1, 1);
        
        // some information about the properties of the cards that can be also looked up in the class Skatcard
        // positionInGame:
        // 1 playable for CPU
        // 2 on the back of CPU's hand
        // 3 on the back of player's hand
        // 4 playable (for the player)
        // 5 won by the CPU
        // 6 played by the CPU
        // 7 won by player
        // 8 played by player
        // even numbers are visible for the player (odd numbers are represented by face down cards)
    
        // If this was not object oriented, one would have saved the parameter values of all objects in arrays.
        // the next 2 arrays would only make sense together
        // the cards are ordered according to how high they are valued
        // J clubs, J spades, J hearts, J diamonds
        // clubs: J, A, 10, K, Q, 9, 8, 7, same for spades, then hearts, then diamonds
        // colors stand for 1=clubs, 2=spades, 3=hearts, 4=diamons
        // values stand for 1=J, 2=A, 3=10, 4=K, 5=Q, 6=9, 7=8, 8=7
        // also there would be an array with paths for the card pictures
        //static int[] color = {1, 2, 3, 4, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4};
        //static int[] value = {1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 5, 6, 7, 8};
        
        // fill array of cards elementwise
        cards[0] = placeholder;
        cards[1] = clubJ;
        cards[2] = spadeJ;
        cards[3] = heartJ;
        cards[4] = diamJ;
        cards[5] = clubA;
        cards[6] = club10;
        cards[7] = clubK;
        cards[8] = clubQ;
        cards[9] = club9;
        cards[10] = club8;
        cards[11] = club7;
        cards[12] = spadeA;
        cards[13] = spade10;
        cards[14] = spadeK;
        cards[15] = spadeQ;
        cards[16] = spade9;
        cards[17] = spade8;
        cards[18] = spade7;
        cards[19] = heartA;
        cards[20] = heart10;
        cards[21] = heartK;
        cards[22] = heartQ;
        cards[23] = heart9;
        cards[24] = heart8;
        cards[25] = heart7;
        cards[26] = diamA;
        cards[27] = diam10;
        cards[28] = diamK;
        cards[29] = diamQ;
        cards[30] = diam9;
        cards[31] = diam8;
        cards[32] = diam7;
        
        // create hands with deterministic and wrong cards already (later the right cards are assigned)
        playableCPU = new Hand (cards[1], cards[2], cards[3], cards[4], cards[5], cards[6], cards[7], cards[8]);
        backhandCPU = new Hand (cards[9], cards[10], cards[11], cards[12], cards[13], cards[14], cards[15], cards[16]);
        backhandPlayer = new Hand (cards[17], cards[18], cards[19], cards[20], cards[21], cards[22], cards[23], cards[24]);
        playable = new Hand (cards[25], cards[26], cards[27], cards[28], cards[29], cards[30], cards[31], cards[32]);
        
        // for passing all cards (including the empty card placeholder) as one argument in methods
        Deck = new SkatcardDeck (placeholder, clubJ, spadeJ, heartJ, diamJ, clubA, club10, clubK, clubQ, club9, club8, club7, spadeA, spade10, spadeK, spadeQ, spade9, spade8, spade7, heartA, heart10, heartK, heartQ, heart9, heart8, heart7, diamA, diam10, diamK, diamQ, diam9, diam8, diam7);
        
        //this.add(panel);
        this.add(layeredPane);
        
    }
    
    // --- methods ---
    
    public static void main(String[] args) throws InterruptedException {
        
        // create main window for game
        windowl = new Harlekinskat4();
        windowl.setVisible(true);
        windowl.setExtendedState(windowl.MAXIMIZED_BOTH); // maximize frame
        
        // set everything on English
        English.setLanguage();
        languageAsInt=1;
        
        TimeUnit.MILLISECONDS.sleep(250); // just there, so that the language window is scaled correctly
        
        // calling a method that opens another window for choosing the language
        window2 = new Languages("languages");
        window2.setVisible(true);
        Languages.centerAndStrechLanguagesWindow();
    }
    
    public void componentHidden(ComponentEvent ce) {};
    public void componentShown(ComponentEvent ce) {};
    public void componentMoved(ComponentEvent ce) {};
    public void componentResized(ComponentEvent ce) {
        currentFrameWidth = this.getWidth();
        currentFrameHeight = this.getHeight();
        rescaleEverything();
    };
    
    // calculates rescaling of horizontal coordinates (and widths)
    public static int rescaleX (int x) {
        int newValue = (int) Math.round(x*currentFrameWidth/initialFrameWidth);
        return newValue;
    }
    
    // calculates rescaling of vertical coordinates (and heights)
    public static int rescaleY (int y) {
        int newValue = (int) Math.round(y*currentFrameHeight/initialFrameHeight);
        return newValue;
    }
    
    // rescales a given button (uses current window size saved by global variables)
    public static void rescaleButton (JButton buttonName, int intialPosX, int intialPosY, int intialButtonWidth, int intialButtonHeight) {
        buttonName.setBounds(rescaleX(intialPosX), rescaleY(intialPosY), rescaleX(intialButtonWidth), rescaleY(intialButtonHeight));
    }
    
    // simply rescales all graphical components, i.e. all buttons and labels, one by one
    public static void rescaleEverything()
    {
        // rescale all buttons (must be same values as defined above, or else they will scale in a weird way!)
        rescaleButton(buttonFacedown1CPU, 0, 0, 100, 200);
        rescaleButton(buttonFacedown2CPU, 125, 0, 100, 200);
        rescaleButton(buttonFacedown3CPU, 250, 0, 100, 200);
        rescaleButton(buttonFacedown4CPU, 375, 0, 100, 200);
        rescaleButton(buttonFacedown5CPU, 500, 0, 100, 200);
        rescaleButton(buttonFacedown6CPU, 625, 0, 100, 200);
        rescaleButton(buttonFacedown7CPU, 750, 0, 100, 200);
        rescaleButton(buttonFacedown8CPU, 875, 0, 100, 200);
        rescaleButton(buttonFacedown9CPU, 775, 250, 200, 100);
        rescaleButton(buttonFacedown1Player, 0, 525, 100, 200);
        rescaleButton(buttonFacedown2Player, 125, 525, 100, 200);
        rescaleButton(buttonFacedown3Player, 250, 525, 100, 200);
        rescaleButton(buttonFacedown4Player, 375, 525, 100, 200);
        rescaleButton(buttonFacedown5Player, 500, 525, 100, 200);
        rescaleButton(buttonFacedown6Player, 625, 525, 100, 200);
        rescaleButton(buttonFacedown7Player, 750, 525, 100, 200);
        rescaleButton(buttonFacedown8Player, 875, 525, 100, 200);
        rescaleButton(buttonFacedown9Player, 775, 400, 200, 100);
        rescaleButton(buttonRules, 50, 250, 200, 50);
        rescaleButton(buttonStart, 50, 300, 200, 50);
        rescaleButton(buttonDifficulty, 50, 350, 200, 50);
        rescaleButton(buttonShowStrategies, 50, 400, 200, 50);
        rescaleButton(buttonChooseLanguage, 50, 450, 200, 50);
        rescaleButton(buttonCard1CPU, 25, 25, 100, 200);
        rescaleButton(buttonCard2CPU, 150, 25, 100, 200);
        rescaleButton(buttonCard3CPU, 275, 25, 100, 200);
        rescaleButton(buttonCard4CPU, 400, 25, 100, 200);
        rescaleButton(buttonCard5CPU, 525, 25, 100, 200);
        rescaleButton(buttonCard6CPU, 650, 25, 100, 200);
        rescaleButton(buttonCard7CPU, 775, 25, 100, 200);
        rescaleButton(buttonCard8CPU, 900, 25, 100, 200);
        rescaleButton(buttonCard1Player, 25, 550, 100, 200);
        rescaleButton(buttonCard2Player, 150, 550, 100, 200);
        rescaleButton(buttonCard3Player, 275, 550, 100, 200);
        rescaleButton(buttonCard4Player, 400, 550, 100, 200);
        rescaleButton(buttonCard5Player, 525, 550, 100, 200);
        rescaleButton(buttonCard6Player, 650, 550, 100, 200);
        rescaleButton(buttonCard7Player, 775, 550, 100, 200);
        rescaleButton(buttonCard8Player, 900, 550, 100, 200);
        rescaleButton(buttonCardPlayedCPU, 375, 250, 100, 200);
        rescaleButton(buttonCardPlayedPlayer, 525, 300, 100, 200);
    }
    
    // method for setting button properties using fewer lines (for readability)
    public static void setButtonProperties (JButton buttonName, int x, int y, int width, int height)
    {
        // e.g. use like: setButtonProperties(buttonCard1Player, 50, 450, 100, 200);
        buttonName.setBounds(x, y, width, height);
        buttonName.setBorder(BorderFactory.createEmptyBorder());
        buttonName.setContentAreaFilled(false);
        buttonName.setFocusable(false);
        buttonName.setVisible(false);
    }
    
    // returns the nth playable card of the hand of the computer
    public Skatcard getNthCardOfHandCPU (int n) {
        return playableCPU.getNthCardOfHand(n);
    }
    
    // sets the nth card of the hand of the CPU to a given card
    public void setNthCardOfHandCPU (int n, Skatcard Card) {
        playableCPU.setNthCardOfHand(n, Card);
    }
    
    // returns the nth playable card of the hand of the player
    public Skatcard getNthCardOfHandPlayer (int n) {
        return playable.getNthCardOfHand(n);
    }
    // sets the nth card of the hand of the player to a given card
    public void setNthCardOfHandPlayer (int n, Skatcard Card) {
        playable.setNthCardOfHand(n, Card);
    }
    
    // returns the nth card on the back side of the hand of the computer
    public Skatcard getNthCardOfBackhandCPU (int n) {
        return backhandCPU.getNthCardOfHand(n);
    }
    
    // sets the nth card of the back side of the hand of the CPU to a given card
    public void setNthCardOfBackhandCPU (int n, Skatcard Card) {
        backhandCPU.setNthCardOfHand(n, Card);
    }
    
    // returns the nth card on the back side of the hand of the player
    public Skatcard getNthCardOfBackhandPlayer (int n) {
        return backhandPlayer.getNthCardOfHand(n);
    }
    
    // sets the nth card of the back side of the hand of the player to a given card
    public void setNthCardOfBackhandPlayer (int n, Skatcard Card) {
        backhandPlayer.setNthCardOfHand(n, Card);
    }
    
    // computer AI called here
    // this function just calls the several difficulty levels, because of readability
    public void cpuDecision(){
        int finalChoice=0;
        switch (difficulty) {
            case 1: finalChoice = AIEasy.aiLevelEasy(playedCardPlayer, playableCPU, placeholder);
                break;
            case 2: finalChoice = AINormal.aiLevelNormal(playedCardPlayer, playableCPU, backhandPlayer, playable, Deck);
                break;
            case 3: finalChoice = AIHard.aiLevelHard(playedCardPlayer, playableCPU, backhandCPU, Deck);
                break;
        }
        // no matter the difficulty level, cpu plays the card it has decided on
        cpuPlaysCard(finalChoice);
    }
    
    // checks, if the nth card of the back of the hand of the CPU exists (if yes, flips it)
    public void potentiallyFlipNthCardCPU (int n)
    {
        if (getNthCardOfBackhandCPU(n)==placeholder) { // if there is no additional card
            setNthCardOfHandCPU(n, placeholder); // then lost the last one at that position
        }
        else { // new cards gets available to CPU
            setNthCardOfHandCPU(n, getNthCardOfBackhandCPU(n));
            getNthCardOfHandCPU(n).positionInGame=1;
            setNthCardButtonIconPlayableCPU(n, true); // playable card for CPU appears
            // card on computer's backhand disappears
            setNthCardOfBackhandCPU(n, placeholder);
            setNthCardButtonIconBackhandCPU(n, placeholder.imagePath, false);
        }
    }
    
    // checks, if the nth card of the back of the hand of the player exists (if yes, flips it)
    public void potentiallyFlipNthCardPlayer (int n)
    {
        if (getNthCardOfBackhandPlayer(n)==placeholder) { // if there is no additional card
            setNthCardOfHandPlayer(n, placeholder); // then lost the last one at that position
        }
        else { // new cards gets available to player
            setNthCardOfHandPlayer(n, getNthCardOfBackhandPlayer(n));
            getNthCardOfHandPlayer(n).positionInGame=4;
            setNthCardButtonIconPlayable(n, getNthCardOfHandPlayer(n).imagePath, true); // playable card for player appears
            // card on player's backhand disappears
            setNthCardOfBackhandPlayer(n, placeholder);
            setNthCardButtonIconBackhandPlayer(n, false);
             
            memoryPlayerCanServeColorAgain(n); // only occurance of this method, because CPU only lerns this, when player turns around a card
        }
    }
    
    // used for the memory (CPU normal difficulty): if player turns around a card of a certain color, CPU now knows that the player has a card of that color on the hand
    public void memoryPlayerCanServeColorAgain (int numberOfFlippedCard)
    {
        Skatcard Card = getNthCardOfHandPlayer(numberOfFlippedCard);
        int playerCardColor = Card.color;
        if (Card.isTrump()) {
            if (couldntServeTrumpBefore) {numberOfTrumpCardsSinceThen++;}
        }
        else {
            if (playerCardColor==1 && !Card.isJack()) {
                if (couldntServeClubsBefore) {numberOfClubCardsSinceThen++;}
            }
            if (playerCardColor==2 && !Card.isJack()) {
                if (couldntServeSpadesBefore) {numberOfSpadeCardsSinceThen++;}
            }
            if (playerCardColor==3 && !Card.isJack()) {
                if (couldntServeHeartsBefore) {numberOfHeartCardsSinceThen++;}
            }
            if (playerCardColor==4 && !Card.isJack()) {
                if (couldntServeDiamondsBefore) {numberOfDiamondCardsSinceThen++;}
            }
        }
    }
    
    // computer plays card number (1 to 8)
    // This is just the technical aspect of the computer playing a card.
    // The decision is already been made by method cpuDecision.
    public void cpuPlaysCard (int cardNo)
    {
        // depending on card number it is one of the possible 8 cards on the computer's hand
        setNthCardButtonIconPlayableCPU(cardNo, false); // the "face down" card gets invisible (for now)
        playedCardCPU=getNthCardOfHandCPU(cardNo); // because being played
        // visualise the card played by the CPU
        playedCardCPU.positionInGame=6;
        buttonCardPlayedCPU.setIcon(new RescaledIcon(this.getClass().getResource(playedCardCPU.imagePath)));
        buttonCardPlayedCPU.setVisible(true);
        
        // if the computer is reacting, then this turn closes
        // otherwise nothing else happens and the player has to react
        if (cpuReacts) {
            
            // decide which card is higher (in case of a jack easy by looking at card number)
            if ((playedCardPlayer.isJack()) || (playedCardCPU.isJack())){ // if a jack was played by any player
                playerWinsCards = (playedCardPlayer.cardId < playedCardCPU.cardId); // if cpu played lower card (higher cardID)
            }
            else { // in case noone played a jack (either CPU played trump and player did not or CPU played higher trump or CPU served the color and played higher one)
                boolean cpuPlayedTrumpContraryToPlayer = (playedCardCPU.isTrump() && playedCardPlayer.isNotTrump());
                boolean cpuPlayedHigherTrump = (playedCardCPU.isTrump() && playedCardPlayer.isTrump() && playedCardCPU.value < playedCardPlayer.value);
                boolean cpuPlayedHigherColor = (playedCardCPU.color==playedCardPlayer.color && playedCardCPU.value < playedCardPlayer.value);
                playerWinsCards = !(cpuPlayedTrumpContraryToPlayer || cpuPlayedHigherTrump || cpuPlayedHigherColor);
            }
            theWinnerTakesItAll();
            checkForTurnAroundCards(cardNo);
            endTurn();
        }
        else {
            islockedFromPlaying=false; // unlock the game for the player again
        }
    }
    
    // in order not to repeat oneself, out-source here what happens, when it is decided who takes both cards home
    public void theWinnerTakesItAll()
    {
        if (playerWinsCards){ // player takes cards home
            playedCardPlayer.positionInGame=7;
            playedCardCPU.positionInGame=7;
            infoDialog(stringWinCards); // alternative dialog format // JOptionPane.showOptionDialog(null, stringWinCards, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
            buttonFacedown9Player.setVisible(true);
        }
        else { // computer takes cards home (next turn has to start playing)
            playedCardPlayer.positionInGame=5;
            playedCardCPU.positionInGame=5;
            infoDialog(stringLoseCards); // alternative dialog format // JOptionPane.showOptionDialog(null, stringLoseCards, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
            buttonFacedown9CPU.setVisible(true);
        }
        buttonCardPlayedCPU.setVisible(false); // make played cards disappear
        buttonCardPlayedPlayer.setVisible(false);
    }
    
    // in order not to repeat oneself, out-source here the test, if players have to turn around cards (and if so, do it) 
    public void checkForTurnAroundCards (int playedCardNumber)
    {
        if (cpuReacts) {
            potentiallyFlipNthCardCPU(playedCardNumber);
            potentiallyFlipNthCardPlayer(playedCardPlayer.positionOnHand);
        }
        else {
            potentiallyFlipNthCardPlayer(playedCardNumber);
            potentiallyFlipNthCardCPU(playedCardCPU.positionOnHand);
        }
    }
    
    // in order not to repeat oneself, out-source here what happens at the end of a turn
    public void endTurn()
    {
        numberOfTurns++;
        // if it was the last turn, end the game
        if (numberOfTurns==16){
            islockedFromPlaying=true;
            endgame();
        }
        else { // if it is not the last turn
            if (playerWinsCards){
                cpuReacts=true;
                islockedFromPlaying=false; // unlock the game for the player
            }
            else {
                cpuReacts=false;
                islockedFromPlaying=true;
                cpuDecision(); // let computer make another decision
            }
        }
    }
    
    // player starts playing nth card, if it exists (returns true, if it is played)
    public boolean playerPotentiallyPlaysNthCard (int n)
    {
        Skatcard ChosenCard = getNthCardOfHandPlayer(n);
        boolean isAttemptingToPlayACard = ChosenCard!=placeholder;
        if (isAttemptingToPlayACard) { // if there is still a card
            setNthCardButtonIconPlayable(n, placeholder.imagePath, false); // playable card gets invisible (for now)
            playedCardPlayer=ChosenCard; // because being played
            // visualise the card played by the CPU
            ChosenCard.positionInGame=8;
            buttonCardPlayedPlayer.setIcon(new RescaledIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
            buttonCardPlayedPlayer.setVisible(true);
        }
        return isAttemptingToPlayACard;
    }
    
    // returns true, if the player has a playable card on the hand that is a trump card
    public boolean playerCanServeTrump()
    {
        for (int index = 1; index <= 8; index++){
            Skatcard Card = getNthCardOfHandPlayer(index);
            if (Card.isTrump()) {return true;}
        }
        return false;
    }
    
    // returns true, if the player has a playable card on the hand that has the currently played non-trump color/suit
    public boolean playerCanServeColor()
    {
        for (int index = 1; index <= 8; index++){
            Skatcard Card = getNthCardOfHandPlayer(index);
            if ((Card.color==currentColor && !Card.isJack())) {return true;}
        }
        return false;
    }
    
    // used for the memory (CPU normal difficulty): if player does not have a certain color on the hand, because plays a different one
    public void memoryPlayerCanNotServeColor()
    {
        if (trumpPlayed) {
            couldntServeTrumpBefore=true;
        }
        else {
            if (currentColor==1){couldntServeClubsBefore=true;}
            if (currentColor==2){couldntServeSpadesBefore=true;}
            if (currentColor==3){couldntServeHeartsBefore=true;}
            if (currentColor==4){couldntServeDiamondsBefore=true;}
        }
    }
    
    // used for the memory (CPU normal difficulty): if the player could serve a certain color again, but gets rid of a card of the color
    public void memoryPlayerPotentiallyDiscardsServableColor()
    {
        if (playedCardPlayer.color==trumpColor) {
            if (couldntServeTrumpBefore) {numberOfTrumpCardsSinceThen--;}
        }
        else {
            if (couldntServeClubsBefore && playedCardPlayer.color==1){numberOfClubCardsSinceThen--;}
            if (couldntServeSpadesBefore && playedCardPlayer.color==2){numberOfSpadeCardsSinceThen--;}
            if (couldntServeHeartsBefore && playedCardPlayer.color==3){numberOfHeartCardsSinceThen--;}
            if (couldntServeDiamondsBefore && playedCardPlayer.color==4){numberOfDiamondCardsSinceThen--;}
        }
    }
    
    // player plays card number (1 to 8), if the attempted card is valid for playing.
    public void playerPlaysCard (int cardNo)
    {
        Skatcard Card = getNthCardOfHandPlayer(cardNo);
        if (!islockedFromPlaying && Card!=placeholder){ // only do something at all, if the game is unlocked to the player and actually clicked on a card that is still there
            playedCardPlayer=placeholder; // set the card played by the player to the placeholder (so the closing of the turn can only occur, if this has been changed)
            if (cpuReacts){// first decide if the player starts playing or not
                // player may play any card (if it still exists)
                boolean isAttemptingToPlayACard = playerPotentiallyPlaysNthCard(cardNo);
                if (isAttemptingToPlayACard){ // if the player actually played a card
                    currentColor=playedCardPlayer.color;
                    islockedFromPlaying=true;
                    memoryPlayerPotentiallyDiscardsServableColor();
                    String text = Skatcard.playedCardText(playedCardPlayer); // since computer is too fast, wait here and display what has been played
                    JOptionPane.showOptionDialog(null, text, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
                    cpuDecision(); // ask the computer to play a card
                }
            }
            else { // first determine what has been played and if player has the same "color"
                if (playedCardCPU.isTrump()){ // if a jack or other trump has been played
                    trumpPlayed=true;
                    currentColor=trumpColor;
                    servable = playerCanServeTrump(); // look if player has also a trump card
                }
                else {
                    trumpPlayed=false;
                    currentColor=playedCardCPU.color;
                    servable = playerCanServeColor(); // look if player has a card of the same non-trump color
                }
                // if player has not a card of the same "color", just play the attempted card
                if (!servable){
                    boolean isAttemptingToPlayACard = playerPotentiallyPlaysNthCard(cardNo);
                    if (isAttemptingToPlayACard) {
                        memoryPlayerCanNotServeColor();
                        playerWinsCards = (playedCardPlayer.isTrump()); // player only wins the card by playing trump
                    }
                }
                else { // if the player can serve, then only do something, if a valid card has been played
                    if (trumpPlayed){
                        if (Card.isTrump()){ // only another trump is valid here
                            playerPotentiallyPlaysNthCard(cardNo); // here the player plays the selected card
                            playerWinsCards = (playedCardPlayer.cardId < playedCardCPU.cardId); // player can only get the card with a higher trump card
                        }
                    }
                    else {
                        if (Card.isNotTrump()) { // only valid cards of the same non-trump color are valid here
                            playerPotentiallyPlaysNthCard(cardNo); // here the player plays the selected card
                            playerWinsCards = (playedCardPlayer.value < playedCardCPU.value); // player can only get the card with a higher card of the same color
                        }
                    }
                }
                if (playedCardPlayer!=placeholder){ // if a card actually has been played (if it was valid when chosen)
                    memoryPlayerPotentiallyDiscardsServableColor();
                    theWinnerTakesItAll();
                    checkForTurnAroundCards(cardNo);
                    endTurn();
                }
            }
        }
    }
    
    public void setNthCardButtonIconPlayableCPU (int n, boolean isVisible) {
        switch (n) {
            case 1: buttonFacedown1CPU.setVisible(isVisible); break;
            case 2: buttonFacedown2CPU.setVisible(isVisible); break;
            case 3: buttonFacedown3CPU.setVisible(isVisible); break;
            case 4: buttonFacedown4CPU.setVisible(isVisible); break;
            case 5: buttonFacedown5CPU.setVisible(isVisible); break;
            case 6: buttonFacedown6CPU.setVisible(isVisible); break;
            case 7: buttonFacedown7CPU.setVisible(isVisible); break;
            case 8: buttonFacedown8CPU.setVisible(isVisible); break;
            default: infoDialog("<html><body>Error: out of bounds in setNthCardButtonIconPlayableCPU(...); attempted N: " + n + "</body></html>"); break;
        }
    }
    
    public void setNthCardButtonIconBackhandCPU (int n, String imagePath, boolean isVisible) {
        switch (n) {
            case 1: buttonCard1CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard1CPU.setVisible(isVisible); break;
            case 2: buttonCard2CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard2CPU.setVisible(isVisible); break;
            case 3: buttonCard3CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard3CPU.setVisible(isVisible); break;
            case 4: buttonCard4CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard4CPU.setVisible(isVisible); break;
            case 5: buttonCard5CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard5CPU.setVisible(isVisible); break;
            case 6: buttonCard6CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard6CPU.setVisible(isVisible); break;
            case 7: buttonCard7CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard7CPU.setVisible(isVisible); break;
            case 8: buttonCard8CPU.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard8CPU.setVisible(isVisible); break;
            default: infoDialog("<html><body>Error: out of bounds in setNthCardButtonIconBackhandCPU(...); attempted N: " + n + "</body></html>"); break;
        }
    }
    
    public void setNthCardButtonIconPlayable (int n, String imagePath, boolean isVisible) {
        switch (n) {
            case 1: buttonCard1Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard1Player.setVisible(isVisible); break;
            case 2: buttonCard2Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard2Player.setVisible(isVisible); break;
            case 3: buttonCard3Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard3Player.setVisible(isVisible); break;
            case 4: buttonCard4Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard4Player.setVisible(isVisible); break;
            case 5: buttonCard5Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard5Player.setVisible(isVisible); break;
            case 6: buttonCard6Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard6Player.setVisible(isVisible); break;
            case 7: buttonCard7Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard7Player.setVisible(isVisible); break;
            case 8: buttonCard8Player.setIcon(new RescaledIcon(this.getClass().getResource(imagePath)));
            buttonCard8Player.setVisible(isVisible); break;
            default: infoDialog("<html><body>Error: out of bounds in setNthCardButtonIconPlayable(...); attempted N: " + n + "</body></html>"); break;
        }
    }
    
    public void setNthCardButtonIconBackhandPlayer (int n, boolean isVisible) {
        switch (n) {
            case 1: buttonFacedown1Player.setVisible(isVisible); break;
            case 2: buttonFacedown2Player.setVisible(isVisible); break;
            case 3: buttonFacedown3Player.setVisible(isVisible); break;
            case 4: buttonFacedown4Player.setVisible(isVisible); break;
            case 5: buttonFacedown5Player.setVisible(isVisible); break;
            case 6: buttonFacedown6Player.setVisible(isVisible); break;
            case 7: buttonFacedown7Player.setVisible(isVisible); break;
            case 8: buttonFacedown8Player.setVisible(isVisible); break;
            default: infoDialog("<html><body>Error: out of bounds in setNthCardButtonIconBackhandPlayer(...); attempted N: " + n + "</body></html>"); break;
        }
    }
    
    // standard dialog for displaying current game info, placed such that one can see all the cards well
    // useful, if player needs to see, what the computer played
    public static void infoDialog (String dialogText)
    {
        JDialog decideJDialog = new JDialog();
        int width = (int) Math.round(currentFrameWidth/5);
        int height = (int) Math.round(currentFrameHeight/5);
        int posX = (int) Math.round(2*currentFrameWidth/3);
        int posY = (int) Math.round(currentFrameHeight/3);
        decideJDialog.setBounds(posX, posY, width, height);
        decideJDialog.add(new JLabel("" + dialogText));
        decideJDialog.setModal(true);
        decideJDialog.setVisible(true);
    }
    
    // translates the card names that are used at the end of the game when calculating the won points
    // (in case one has changed the language during the game)
    public void translateCardNames(){
        clubJ.displayName=stringJ1Text;
        spadeJ.displayName=stringJ2Text;
        heartJ.displayName=stringJ3Text;
        diamJ.displayName=stringJ4Text;
        clubA.displayName=stringCAText;
        club10.displayName=stringC10Text;
        clubK.displayName=stringCKText;
        clubQ.displayName=stringCQText;
        club9.displayName=stringC9Text;
        club8.displayName=stringC8Text;
        club7.displayName=stringC7Text;
        spadeA.displayName=stringSAText;
        spade10.displayName=stringS10Text;
        spadeK.displayName=stringSKText;
        spadeQ.displayName=stringSQText;
        spade9.displayName=stringS9Text;
        spade8.displayName=stringS8Text;
        spade7.displayName=stringS7Text;
        heartA.displayName=stringHAText;
        heart10.displayName=stringH10Text;
        heartK.displayName=stringHKText;
        heartQ.displayName=stringHQText;
        heart9.displayName=stringH9Text;
        heart8.displayName=stringH8Text;
        heart7.displayName=stringH7Text;
        diamA.displayName=stringDAText;
        diam10.displayName=stringD10Text;
        diamK.displayName=stringDKText;
        diamQ.displayName=stringDQText;
        diam9.displayName=stringD9Text;
        diam8.displayName=stringD8Text;
        diam7.displayName=stringD7Text;
    }
    
    // adds up the points of the card, if the player has won it (also adds the card to list of won cards)
    public void potentiallyAddUpCard (Skatcard Card)
    {
        if (Card.positionInGame == 7){
            wonCards = wonCards + Card.displayName + "<br>";
            wonPoints = wonPoints + Card.points;
        }
    }
    
    // if 16 turns have been completed (thus all cards have been played)
    // add up the obtained points
    public void endgame()
    {
        translateCardNames(); // translates the card names into the right language (this is doing it every Game, because Java is not allowing collision of static & non-static stuff: one could make all objects static and completely rewrite the code, but objects are not supposed to be static)
        JScrollPane scrollPane = new JScrollPane();
        JDialog endJDialog = new JDialog();
        endJDialog.setTitle(stringGameOver);
        int width = (int) Math.round(currentFrameWidth/3);
        int height = (int) Math.round(currentFrameHeight/3);
        int posX = (int) Math.round((currentFrameWidth - width)/2);
        int posY = (int) Math.round((currentFrameHeight - height)/2);
        endJDialog.setBounds(posX, posY, width, height); // x, y (measured from upper left), width, height
        //endJDialog.setModal(true); // uncomment, if dialog has to be closed before anything else can happen (however, as a service the player may leave it open to compare to the results of later games)
        endJDialog.getContentPane().setLayout(new BorderLayout(0, 0));
        endJDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        endJDialog.setVisible(true);
        // look up, if card has been won by player: begin with aces and tens, then the "images/pictures" (king, queen, jack), then list the won low cards
        wonCards = new String();
        wonPoints = 0;
        potentiallyAddUpCard(clubA);
        potentiallyAddUpCard(spadeA);
        potentiallyAddUpCard(heartA);
        potentiallyAddUpCard(diamA);
        potentiallyAddUpCard(club10);
        potentiallyAddUpCard(spade10);
        potentiallyAddUpCard(heart10);
        potentiallyAddUpCard(diam10);
        potentiallyAddUpCard(clubK);
        potentiallyAddUpCard(spadeK);
        potentiallyAddUpCard(heartK);
        potentiallyAddUpCard(diamK);
        potentiallyAddUpCard(clubQ);
        potentiallyAddUpCard(spadeQ);
        potentiallyAddUpCard(heartQ);
        potentiallyAddUpCard(diamQ);
        potentiallyAddUpCard(clubJ);
        potentiallyAddUpCard(spadeJ);
        potentiallyAddUpCard(heartJ);
        potentiallyAddUpCard(diamJ);
        // beginning from here one could leave out the adding up of points, since they are zero (but one still has to list them!)
        potentiallyAddUpCard(club9);
        potentiallyAddUpCard(spade9);
        potentiallyAddUpCard(heart9);
        potentiallyAddUpCard(diam9);
        potentiallyAddUpCard(club8);
        potentiallyAddUpCard(spade8);
        potentiallyAddUpCard(heart8);
        potentiallyAddUpCard(diam8);
        potentiallyAddUpCard(club7);
        potentiallyAddUpCard(spade7);
        potentiallyAddUpCard(heart7);
        potentiallyAddUpCard(diam7);
        if (wonPoints == 60) {
            wonCards = wonCards + stringDraw;
        }
        else if(wonPoints > 60){
            wonCards = wonCards + "<br> " + wonPoints + stringWin;
        }
        else if(wonPoints < 60){
            wonCards = wonCards + "<br> " + wonPoints + stringLose;
        }
        JLabel endtext = new JLabel(stringPointCounting
            + wonCards
            + stringRestartMessage 
            + "</blockquote></body></html>");
        scrollPane.setViewportView(endtext);
    }
    
    // makes all cards invisible
    public void letAllCardsDisappear()
    {
        // make the taken home cards & played cards invisible
        buttonFacedown9Player.setVisible(false);
        buttonFacedown9CPU.setVisible(false);
        buttonCardPlayedCPU.setVisible(false);
        buttonCardPlayedPlayer.setVisible(false);
        // make all cards on hands invisible
        for (int index = 1; index <= 8; index++){
            setNthCardButtonIconPlayableCPU(index, false);
            setNthCardButtonIconBackhandCPU(index, placeholder.imagePath, false);
            setNthCardButtonIconPlayable(index, placeholder.imagePath, false);
            setNthCardButtonIconBackhandPlayer(index, false);
        }
    }
    
    // erases the memory of the CPU (about what cards the player has)
    public void resetMemory()
    {
        couldntServeClubsBefore=false;
        couldntServeSpadesBefore=false;
        couldntServeHeartsBefore=false;
        couldntServeDiamondsBefore=false;
        couldntServeTrumpBefore=false;
        numberOfClubCardsSinceThen=0;
        numberOfSpadeCardsSinceThen=0;
        numberOfHeartCardsSinceThen=0;
        numberOfDiamondCardsSinceThen=0;
        numberOfTrumpCardsSinceThen=0;
    }
    
    public void shuffleAndDealAllCards()
    {
        array32 = Skatcard.shuffleDeck();
        JOptionPane.showOptionDialog(null, stringDealing, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
        for (int index = 1; index <= 8; index++){// deal cards and make them visible
            // playable cards of CPU
            setNthCardOfHandCPU(index, cards[array32[index-1]]);
            getNthCardOfHandCPU(index).positionOnHand=index;
            getNthCardOfHandCPU(index).positionInGame=1;
            setNthCardButtonIconPlayableCPU(index, true);
            // on back hand of CPU
            setNthCardOfBackhandCPU(index, cards[array32[index+8-1]]);
            getNthCardOfBackhandCPU(index).positionOnHand=index;
            getNthCardOfBackhandCPU(index).positionInGame=2;
            setNthCardButtonIconBackhandCPU(index, cards[array32[index+8-1]].imagePath, true);
            // on back hand player
            setNthCardOfBackhandPlayer(index, cards[array32[index+16-1]]);
            getNthCardOfBackhandPlayer(index).positionOnHand=index;
            getNthCardOfBackhandPlayer(index).positionInGame=3;
            setNthCardButtonIconBackhandPlayer(index, true);
            // cards playable for player
            setNthCardOfHandPlayer(index, cards[array32[index+24-1]]);
            getNthCardOfHandPlayer(index).positionOnHand=index;
            getNthCardOfHandPlayer(index).positionInGame=4;
            setNthCardButtonIconPlayable(index, cards[array32[index+24-1]].imagePath, true);
        }
    }
    
    // resetting varibles, cards (first disappear, then shuffled and dealt again)
    public void resetEverything()
    {
        letAllCardsDisappear(); // make everything invisible first
        numberOfTurns=0; // reset the counter for the number of already played turns (important for triggering the end game)
        resetMemory();
        shuffleAndDealAllCards();
    }
    
    // uses a coin flip in order to decide who gets to choose trump in first game (in consequtive games alternate)
    public int determineTrumpChooser()
    {
        if (numberOfGames==0) { // make a coin flip in order to decide who gets to choose the trump color (player or CPU)
            coinflip = AIEasy.chooseRandomOption(2)-1; // a number either zero or unity
            JOptionPane.showOptionDialog(null, stringCoin, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
            return coinflip;
        }
        else { // this will yield: in 1st game a coinflip decides who chooses trump, after that the two opponents/players alternate in choosing trump
            return (coinflip+numberOfGames)%2; // % is the modulo function
        }
    }
    
    public void playerChoosesTrump()
    {
        int suit = JOptionPane.showOptionDialog(null, stringTrumpDialog, "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{stringClubs, stringSpades, stringHearts, stringDiamonds}, stringClubs);
        if (suit == -1) { // in case one accidently just closed the dialog window
            suit=0; // will eventually also result in standard trump suit clubs (because one is still supposed to be able to play the game, even if one refused to choose a trump suit)
        }
        trumpColor = suit +1; // the trump color is set here
        // dialog about what color has been chosen
        String text="";
        switch (trumpColor) {
            case 1: text=stringClubsAffirm1; break;
            case 2: text=stringSpadesAffirm1; break;
            case 3: text=stringHeartsAffirm1; break;
            case 4: text=stringDiamondsAffirm1; break;
        }
        JOptionPane.showOptionDialog(null, text, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
    }
    
    public void computerStartsGame(){
        cpuReacts=false;
        islockedFromPlaying=true; // lock the player for now from playing
        cpuDecision(); // trigger computer decision making here
    }
    
    public void computerChoosesTrump()
    {
        switch (difficulty) { // depending on the CPU-level the computer chooses the trump color in one of different ways
            case 1: trumpColor = AIEasy.cpuChoosesTrump(); break;
            case 2: trumpColor = AINormal.cpuChoosesTrump(playableCPU); break;
            case 3: trumpColor = AIHard.cpuChoosesTrump(playableCPU, backhandCPU); break;
        }
        // dialog about what color has been chosen
        String text="";
        switch (trumpColor) {
            case 1: text=stringClubsAffirm2; break;
            case 2: text=stringSpadesAffirm2; break;
            case 3: text=stringHeartsAffirm2; break;
            case 4: text=stringDiamondsAffirm2; break;
        }
        JOptionPane.showOptionDialog(null, text, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
    }
    
    public void playerStartsGame(){
        cpuReacts=true;
        islockedFromPlaying=false; // here the player would just click on (and thus play) a card
    }
    
    public void setCPULevel()
    {
        int cpulevel = JOptionPane.showOptionDialog(null, stringDifficultyDialog1, stringDifficultyDialog2, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{stringEasy, stringNormal, stringHard}, stringNormal);
        // returns 0, 1, 2 for the three buttons
        if (cpulevel == -1) { // in case one accidently just closed the dialog window
            cpulevel=1; // will eventually also result in standard difficulty level
        }
        difficulty = cpulevel +1; // difficulty 1=easy, 2=normal, 3=hard
        // feedback dialog
        String text="";
        switch (difficulty) {
            case 1: text=stringEasyAffirm; break;
            case 2: text=stringNormalAffirm; break;
            case 3: text=stringHardAffirm; break;
        }
        JOptionPane.showOptionDialog(null, text, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"ok"}, "ok");
    }
    
    public void setCPUStrategyVisibility()
    { // on english one gets the chance to display the AI strategy debug dialogs, if additional option switch on
        if (isAddingSetShowAIStrategyMenuOption) {
            int dialogResult = JOptionPane.showOptionDialog(null, stringShowStrategiesDialog, stringShowStrategies+"?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{stringYes, stringNoDefault}, stringNoDefault);
            isShowingAIStrategies = dialogResult==0;
        }
    }
    
    public void displayRules()
    { // opens a window, showing the rules of the game
        JScrollPane scrollPane = new JScrollPane();
        JDialog rulesDialog = new JDialog();
        rulesDialog.setTitle(stringRuleTitle);
        int width = (int) Math.round(currentFrameWidth*0.6);
        int height = (int) Math.round(currentFrameHeight*0.9);
        int posX = (int) Math.round((currentFrameWidth - width)/2);
        int posY = (int) Math.round((currentFrameHeight - height)/2);
        rulesDialog.setBounds(posX, posY, width, height); // x, y (measured from upper left), width, height
        rulesDialog.getContentPane().setLayout(new BorderLayout(0, 0));
        rulesDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        rulesDialog.setVisible(true);
        JLabel ruletext = new JLabel(stringRuleText);
        scrollPane.setViewportView(ruletext);
    }
    
    // --- reactions of the buttons ---
    
    public void actionPerformed (ActionEvent ae){
        
        if(ae.getSource() == this.buttonDifficulty){
            setCPULevel();
        }
        
        if(ae.getSource() == this.buttonShowStrategies){
            setCPUStrategyVisibility();
        }
        
        if(ae.getSource() == this.buttonStart){
            resetEverything();
            numberOfGames++; // if one resets the game, the other player starts choosing trump
            // This allows players who always want to choose trump (or never) to do so by skipping every 2nd game.
            int trumpChooser = determineTrumpChooser();
            if (trumpChooser == 0){
                playerChoosesTrump();
                computerStartsGame();
            }
            else if(trumpChooser == 1){
                computerChoosesTrump();
                playerStartsGame();
            }
        }
        
        if(ae.getSource() == this.buttonChooseLanguage){
            if (languageAsInt==1){
                window2 = new Languages("languages");
                window2.setVisible(true);
            }
            else if (languageAsInt==2) {
                window2 = new Languages("Sprachen");
                window2.setVisible(true);
            }
        }
        
        if(ae.getSource() == this.buttonRules){
            displayRules();
        }
        
        if(ae.getSource() == this.buttonCard1Player){
            playerPlaysCard(1); // player plays 1st card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard2Player){
            playerPlaysCard(2); // player plays 2nd card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard3Player){
            playerPlaysCard(3); // player plays 3rd card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard4Player){
            playerPlaysCard(4); // player plays 4th card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard5Player){
            playerPlaysCard(5); // player plays 5th card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard6Player){
            playerPlaysCard(6); // player plays 6th card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard7Player){
            playerPlaysCard(7); // player plays 7th card (if something happens is decided by method)
        }
        if(ae.getSource() == this.buttonCard8Player){
            playerPlaysCard(8); // player plays 8th card (if something happens is decided by method)
        }
        
    }

    
}
