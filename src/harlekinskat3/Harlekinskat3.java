package harlekinskat3;

// This is a video game called "Harlekin Skat".
// It is a variation of skat,
// in which each player knows the cards that the opponent can use later.
// One can play against the cpu on 3 different difficulty levels.
//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
// the sleeping ethod in order to wait did not work (it waited first all combined times and then dealed the cards all together anyway => leace this feature out)
//import java.util.concurrent.TimeUnit; // try {TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException e) {e.printStackTrace();} // don't ask, but one has to try/catch an exception, or else the compiler is complaining
import javax.swing.*;

// about "redundant" if-statements:
// a construction of the kind
// if (boolean1) {
//     boolean2=true;
// }
// else {
//     boolean2=false
// }
// can be abbreviated to the one-liner
// boolean2=boolean1;
// However, if boolean1 is a very complex statement, then it is not necessarily more readable!

// -- Version Info --
// started progamming in Java at 1Jul2018
// started programming Harlequin Skat at 10Sep2018
// 
// version 1.0 info: 10-24Sep2018
// final game practically finsihed, running pretty smoothly
// actually, I thought I was finished here, but it followed a lot of debugging of minor bugs
//
// version 1.1 info: 22oct2018
// did some debugging
//
// version 1.2 info: 18dec2018
// did some debugging
//
// version 2.0 info: 22-26dec2018
// did a complete rewriting of half of the whole source code for better readability
// did major debugging
// added a more likely way of making 'best move' on normal level
// -- Actually, I consider this game as complete, and I don't want to improve it any more. --
// version 2.1 info: 7Jan2019
// just added the "close-on-exit"-line
// (way too late: in future programs add it right from the start!)
//
// version 3.0 info: 8-9Jan2019
// new feature: full support of both languages: English and German (added language chooser option)
// this required quite some rewriting
// -- I consider this game as complete for now. --
// Originally, I wanted to translate it to other languages as well.
// However, terms like 'jack of clubs' are too tricky for the translation machines nowadays.
// So, unless some (native) speaker wants to translate it, it will stay like this.
// Either way, the way it is written now, will make it fairly easy to add more languages:
// One just has to translate the file English.JAVA (ca. 175lines of text),
// add it as extra file and add a button (+listener).
//

// This class contains all the visualizations and the most basic mechanics of the game.
public class Harlekinskat3 extends JFrame implements ActionListener
{
    // declaring all fields here
    // those used in other classes make static imports of the fields
    public static Harlekinskat3 windowl;
    public static Languages window2;
    public static int languageAsInt=1;
    
    public JPanel panel; // declaration whole screen
    
    // strings
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
    
    public static String stringJ1Text="jack of clubs (2)";
    public static String stringJ2Text="jack of spades (2)";
    public static String stringJ3Text="jack of hearts (2)";
    public static String stringJ4Text="jack of diamonds (2)";
    
    public static String stringCAText="ace of clubs (11)";
    public static String stringC10Text="10 of clubs (10)";
    public static String stringCKText="king of clubs (4)";
    public static String stringCQText="queen of clubs (3)";
    public static String stringC9Text="9 of clubs (0)";
    public static String stringC8Text="8 of clubs (0)";
    public static String stringC7Text="7 of clubs (0)";
    
    public static String stringSAText="ace of spades (11)";
    public static String stringS10Text="10 of spades (10)";
    public static String stringSKText="king of spades (4)";
    public static String stringSQText="queen of spades (3)";
    public static String stringS9Text="9 of spades (0)";
    public static String stringS8Text="8 of spades (0)";
    public static String stringS7Text="7 of spades (0)";
    
    public static String stringHAText="ace of hearts (11)";
    public static String stringH10Text="10 of hearts (10)";
    public static String stringHKText="king of hearts (4)";
    public static String stringHQText="queen of hearts (3)";
    public static String stringH9Text="9 of hearts (0)";
    public static String stringH8Text="8 of hearts (0)";
    public static String stringH7Text="7 of hearts (0)";
    
    public static String stringDAText="ace of diamonds (11)";
    public static String stringD10Text="10 of diamonds (10)";
    public static String stringDKText="king of diamonds (4)";
    public static String stringDQText="queen of diamonds (3)";
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
    
    public static String stringRuleTitle="rules of the game";
    public static String stringRuleText; // defined in class English (and set at beginning of game)
        
    // buttons of visible cards of CPU and player
    public JButton buttonCard1CPU;
    public JButton buttonCard2CPU;
    public JButton buttonCard3CPU;
    public JButton buttonCard4CPU;
    public JButton buttonCard5CPU;
    public JButton buttonCard6CPU;
    public JButton buttonCard7CPU;
    public JButton buttonCard8CPU;
    
    public JButton buttonCard1Player;
    public JButton buttonCard2Player;
    public JButton buttonCard3Player;
    public JButton buttonCard4Player;
    public JButton buttonCard5Player;
    public JButton buttonCard6Player;
    public JButton buttonCard7Player;
    public JButton buttonCard8Player;
    
    // menu buttons
    public static JButton buttonRules;
    public static JButton buttonStart;
    public static JButton buttonDifficulty;
    public static JButton buttonChooseLanguage;
    
    // buttons for 2 cards played and put in the middle of the screen
    public JButton buttonCardPlayedCPU;
    public JButton buttonCardPlayedPlayer;
    
    // noninteractable buttons for facedown/invisible cards
    public JButton buttonFacedown1CPU;
    public JButton buttonFacedown2CPU;
    public JButton buttonFacedown3CPU;
    public JButton buttonFacedown4CPU;
    public JButton buttonFacedown5CPU;
    public JButton buttonFacedown6CPU;
    public JButton buttonFacedown7CPU;
    public JButton buttonFacedown8CPU;
    public JButton buttonFacedown9CPU;
    
    public JButton buttonFacedown1Player;
    public JButton buttonFacedown2Player;
    public JButton buttonFacedown3Player;
    public JButton buttonFacedown4Player;
    public JButton buttonFacedown5Player;
    public JButton buttonFacedown6Player;
    public JButton buttonFacedown7Player;
    public JButton buttonFacedown8Player;
    public JButton buttonFacedown9Player;
    
    // a few variables
    public static int difficulty=2; // difficulty level 1=easy, 2=normal, 3=hard
    public static int trumpcolor=1; // number of the trump suit (1=clubs, 2=spades, 3=hearts, 4=diamonds)
    
    public static int counter; // for counting things(like number of cards)
    public static int counter1;
    public static int counter2;
    public static int counter3;
    public static int counter4;
    
    public static int finalChoice=0; // integer value of card to be played by cpu after it finally decided on a card
    
    public static int[] optionArray = new int[8]; // for counting the options for cpu decisions
    public static int numberOfOptions;
    
    public static boolean playerWinsCards; // for deciding who takes the two cards home
    public static boolean lock=true; // just for preventing the player from accidently playing several cards at once
    // if lock==true then the player is locked from playing (if false then can play)
    
    // if the number of played turns reached 16, the endgame is initiated and the obtained points are counted 
    public static int numberOfTurns=0;
    public static boolean cpuReacts; // if true, it is the player's turn, if false, it is the CPU's turn
    
    public static int numberOfGames=-1; // number of "played" games (for deciding who chooses trump) [one can skip game by resetting it]
    public static int coinflip;
    public static int trumpChooser;
    
    public static int currentColor; // the color/suit of played card
    public static boolean trumpPlayed; // if played card is a trump card
    public static boolean servable; // if the player can "serve" the colo played by the computer
    
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
    public Harlekinskat3(){
        this.setTitle("Harlequin Skat");
        this.setSize(1230, 700); // window for the whole game
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        
        // create structure for all buttons already
        
        // first all facedown cards (later only visibility must be changed)
        buttonFacedown1CPU = new JButton();
        buttonFacedown1CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown1CPU, 0, 0, 50, 100);
        panel.add(buttonFacedown1CPU);
        
        buttonFacedown2CPU = new JButton();
        buttonFacedown2CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown2CPU, 150, 0, 50, 100);
        panel.add(buttonFacedown2CPU);
        
        buttonFacedown3CPU = new JButton();
        buttonFacedown3CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown3CPU, 300, 0, 50, 100);
        panel.add(buttonFacedown3CPU);
        
        buttonFacedown4CPU = new JButton();
        buttonFacedown4CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown4CPU, 450, 0, 50, 100);
        panel.add(buttonFacedown4CPU);
        
        buttonFacedown5CPU = new JButton();
        buttonFacedown5CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown5CPU, 600, 0, 50, 100);
        panel.add(buttonFacedown5CPU);
        
        buttonFacedown6CPU = new JButton();
        buttonFacedown6CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown6CPU, 750, 0, 50, 100);
        panel.add(buttonFacedown6CPU);
        
        buttonFacedown7CPU = new JButton();
        buttonFacedown7CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown7CPU, 900, 0, 50, 100);
        panel.add(buttonFacedown7CPU);
        
        buttonFacedown8CPU = new JButton();
        buttonFacedown8CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown8CPU, 1050, 0, 50, 100);
        panel.add(buttonFacedown8CPU);
        
        buttonFacedown9CPU = new JButton();
        buttonFacedown9CPU.setIcon(new ImageIcon(this.getClass().getResource("/images/facedownSide.png")));
        setButtonProperties(buttonFacedown9CPU, 1050, 250, 100, 50);
        panel.add(buttonFacedown9CPU);
        
        
        buttonFacedown1Player = new JButton();
        buttonFacedown1Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown1Player, 0, 450, 50, 100);
        panel.add(buttonFacedown1Player);
        
        buttonFacedown2Player = new JButton();
        buttonFacedown2Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown2Player, 150, 450, 50, 100);
        panel.add(buttonFacedown2Player);
        
        buttonFacedown3Player = new JButton();
        buttonFacedown3Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown3Player, 300, 450, 50, 100);
        panel.add(buttonFacedown3Player);
        
        buttonFacedown4Player = new JButton();
        buttonFacedown4Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown4Player, 450, 450, 50, 100);
        panel.add(buttonFacedown4Player);
        
        buttonFacedown5Player = new JButton();
        buttonFacedown5Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown5Player, 600, 450, 50, 100);
        panel.add(buttonFacedown5Player);
        
        buttonFacedown6Player = new JButton();
        buttonFacedown6Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown6Player, 750, 450, 50, 100);
        panel.add(buttonFacedown6Player);
        
        buttonFacedown7Player = new JButton();
        buttonFacedown7Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown7Player, 900, 450, 50, 100);
        panel.add(buttonFacedown7Player);
        
        buttonFacedown8Player = new JButton();
        buttonFacedown8Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedown.png")));
        setButtonProperties(buttonFacedown8Player, 1050, 450, 50, 100);
        panel.add(buttonFacedown8Player);
        
        buttonFacedown9Player = new JButton();
        buttonFacedown9Player.setIcon(new ImageIcon(this.getClass().getResource("/images/facedownSide.png")));
        setButtonProperties(buttonFacedown9Player, 1050, 350, 100, 50);
        panel.add(buttonFacedown9Player);
        
        // silver buttons
        buttonRules = new JButton("rules");
        buttonRules.setBounds(50, 225, 200, 25);
        // connect buttons to listeners
        buttonRules.addActionListener(this);
        panel.add(buttonRules);
        
        buttonStart = new JButton("start/reset");
        buttonStart.setBounds(50, 275, 200, 25);
        // connect buttons to listeners
        buttonStart.addActionListener(this);
        panel.add(buttonStart);
        
        buttonDifficulty = new JButton("set CPU level");
        buttonDifficulty.setBounds(50, 325, 200, 25);
        // connect buttons to listeners
        buttonDifficulty.addActionListener(this);
        panel.add(buttonDifficulty);
        
        buttonChooseLanguage = new JButton("choose language");
        buttonChooseLanguage.setBounds(50, 375, 200, 25);
        // connect buttons to listeners
        buttonChooseLanguage.addActionListener(this);
        panel.add(buttonChooseLanguage);
        
        // now visible cards
        buttonCard1CPU = new JButton();
        setButtonProperties(buttonCard1CPU, 50, 0, 100, 200);
        panel.add(buttonCard1CPU);
        
        buttonCard2CPU = new JButton();
        setButtonProperties(buttonCard2CPU, 200, 0, 100, 200);
        panel.add(buttonCard2CPU);
        
        buttonCard3CPU = new JButton();
        setButtonProperties(buttonCard3CPU, 350, 0, 100, 200);
        panel.add(buttonCard3CPU);
        
        buttonCard4CPU = new JButton();
        setButtonProperties(buttonCard4CPU, 500, 0, 100, 200);
        panel.add(buttonCard4CPU);
        
        buttonCard5CPU = new JButton();
        setButtonProperties(buttonCard5CPU, 650, 0, 100, 200);
        panel.add(buttonCard5CPU);
        
        buttonCard6CPU = new JButton();
        setButtonProperties(buttonCard6CPU, 800, 0, 100, 200);
        panel.add(buttonCard6CPU);
        
        buttonCard7CPU = new JButton();
        setButtonProperties(buttonCard7CPU, 950, 0, 100, 200);
        panel.add(buttonCard7CPU);
        
        buttonCard8CPU = new JButton();
        setButtonProperties(buttonCard8CPU, 1100, 0, 100, 200);
        panel.add(buttonCard8CPU);
        
        buttonCard1Player = new JButton();
        setButtonProperties(buttonCard1Player, 50, 450, 100, 200);
        // connect buttons to listeners
        buttonCard1Player.addActionListener(this);
        panel.add(buttonCard1Player);
        
        buttonCard2Player = new JButton();
        setButtonProperties(buttonCard2Player, 200, 450, 100, 200);
        // connect buttons to listeners
        buttonCard2Player.addActionListener(this);
        panel.add(buttonCard2Player);
        
        buttonCard3Player = new JButton();
        setButtonProperties(buttonCard3Player, 350, 450, 100, 200);
        // connect buttons to listeners
        buttonCard3Player.addActionListener(this);
        panel.add(buttonCard3Player);
        
        buttonCard4Player = new JButton();
        setButtonProperties(buttonCard4Player, 500, 450, 100, 200);
        // connect buttons to listeners
        buttonCard4Player.addActionListener(this);
        panel.add(buttonCard4Player);
        
        buttonCard5Player = new JButton();
        setButtonProperties(buttonCard5Player, 650, 450, 100, 200);
        // connect buttons to listeners
        buttonCard5Player.addActionListener(this);
        panel.add(buttonCard5Player);
        
        buttonCard6Player = new JButton();
        setButtonProperties(buttonCard6Player, 800, 450, 100, 200);
        // connect buttons to listeners
        buttonCard6Player.addActionListener(this);
        panel.add(buttonCard6Player);
        
        buttonCard7Player = new JButton();
        setButtonProperties(buttonCard7Player, 950, 450, 100, 200);
        // connect buttons to listeners
        buttonCard7Player.addActionListener(this);
        panel.add(buttonCard7Player);
        
        buttonCard8Player = new JButton();
        setButtonProperties(buttonCard8Player, 1100, 450, 100, 200);
        // connect buttons to listeners
        buttonCard8Player.addActionListener(this);
        panel.add(buttonCard8Player);
        
        
        buttonCardPlayedCPU = new JButton();
        setButtonProperties(buttonCardPlayedCPU, 500, 200, 100, 200);
        panel.add(buttonCardPlayedCPU);
        
        buttonCardPlayedPlayer = new JButton();
        setButtonProperties(buttonCardPlayedPlayer, 650, 250, 100, 200);
        panel.add(buttonCardPlayedPlayer);
        
        // The objects have to be created here (not in main method somehow)
        // because otherwise the actionlisteners will not find them!
        
        // create all 32 cards with all their properties (last two properties set to 1 first, they change later on)
        clubJ = new Skatcard (1, stringJ1Text, "/images/kreuzB.png", 1, 1, 2, 1, 1);
        spadeJ = new Skatcard (2, stringJ2Text, "/images/pikB.png", 1, 2, 2, 1, 1);
        heartJ = new Skatcard (3, stringJ3Text, "/images/herzB.png", 1, 3, 2, 1, 1);
        diamJ = new Skatcard (4, stringJ4Text, "/images/karoB.png", 1, 4, 2, 1, 1);
        clubA = new Skatcard (5, stringCAText, "/images/kreuzA.png", 2, 1, 11, 1, 1);
        club10 = new Skatcard (6, stringC10Text, "/images/kreuz10.png", 3, 1, 10, 1, 1);
        clubK = new Skatcard (7, stringCKText, "/images/kreuzK.png", 4, 1, 4, 1, 1);
        clubQ = new Skatcard (8, stringCQText, "/images/kreuzD.png", 5, 1, 3, 1, 1);
        club9 = new Skatcard (9, stringC9Text, "/images/kreuz9.png", 6, 1, 0, 1, 1);
        club8 = new Skatcard (10, stringC8Text, "/images/kreuz8.png", 7, 1, 0, 1, 1);
        club7 = new Skatcard (11, stringC7Text, "/images/kreuz7.png", 8, 1, 0, 1, 1);
        spadeA = new Skatcard (12, stringSAText, "/images/pikA.png", 2, 2, 11, 1, 1);
        spade10 = new Skatcard (13, stringS10Text, "/images/pik10.png", 3, 2, 10, 1, 1);
        spadeK = new Skatcard (14, stringSKText, "/images/pikK.png", 4, 2, 4, 1, 1);
        spadeQ = new Skatcard (15, stringSQText, "/images/pikD.png", 5, 2, 3, 1, 1);
        spade9 = new Skatcard (16, stringS9Text, "/images/pik9.png", 6, 2, 0, 1, 1);
        spade8 = new Skatcard (17, stringS8Text, "/images/pik8.png", 7, 2, 0, 1, 1);
        spade7 = new Skatcard (18, stringS7Text, "/images/pik7.png", 8, 2, 0, 1, 1);
        heartA = new Skatcard (19, stringHAText, "/images/herzA.png", 2, 3, 11, 1, 1);
        heart10 = new Skatcard (20, stringH10Text, "/images/herz10.png", 3, 3, 10, 1, 1);
        heartK = new Skatcard (21, stringHKText, "/images/herzK.png", 4, 3, 4, 1, 1);
        heartQ = new Skatcard (22, stringHQText, "/images/herzD.png", 5, 3, 3, 1, 1);
        heart9 = new Skatcard (23, stringH9Text, "/images/herz9.png", 6, 3, 0, 1, 1);
        heart8 = new Skatcard (24, stringH8Text, "/images/herz8.png", 7, 3, 0, 1, 1);
        heart7 = new Skatcard (25, stringH7Text, "/images/herz7.png", 8, 3, 0, 1, 1);
        diamA = new Skatcard (26, stringDAText, "/images/karoA.png", 2, 4, 11, 1, 1);
        diam10 = new Skatcard (27, stringD10Text, "/images/karo10.png", 3, 4, 10, 1, 1);
        diamK = new Skatcard (28, stringDKText, "/images/karoK.png", 4, 4, 4, 1, 1);
        diamQ = new Skatcard (29, stringDQText, "/images/karoD.png", 5, 4, 3, 1, 1);
        diam9 = new Skatcard (30, stringD9Text, "/images/karo9.png", 6, 4, 0, 1, 1);
        diam8 = new Skatcard (31, stringD8Text, "/images/karo8.png", 7, 4, 0, 1, 1);
        diam7 = new Skatcard (32, stringD7Text, "/images/karo7.png", 8, 4, 0, 1, 1);
        // still need a card as a placeholder for the part of the hand that is missing because already played
        placeholder = new Skatcard (33, "placeholder", "/images/facedown.png", 0, 0, 0, 0, 0);

        // some information about the properties of the cards that can be also looked up in the class Skatcard
        // 1 playable for CPU
        // 2 on the back of CPU's hand
        // 3 on the back of player's hand
        // 4 playable (for the player)
        // 5 won by the CPU
        // 6 played by the CPU
        // 7 won by player
        // 8 played by player
        // even numbers are visible for the player (odd numbers are represented by face down cards)
    
        // the next 2 arrays would only make sense together
        // the cards are ordered according to how high they are
        // J clubs, J spades, J hearts, J, diamonds
        // clubs: J, A, 10, K, Q, 9, 8, 7, same for spades, then hearts, then diamonds
        // colors stand for 1=clubs, 2=spades, 3=hearts, 4=diamons
        // values stand for 1=J, 2=A, 3=10, 4=K, 5=Q, 6=9, 7=8, 8=7
        // also there is an array with paths for the card pictures
        //static int[] color = {1, 2, 3, 4, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4};
        //static int[] value = {1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 5, 6, 7, 8};
        
        // fill array of cards elementwise (because the oneliner did not work, or I did not make it right)
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
        
        // this oneliner did not work => therefore the elementwise definition above 
        // //cards = new Skatcard {placeholder, clubJ, spadeJ, heartJ, diamJ, clubA, club10, clubK, clubQ, club9, club8, club7, spadeA, spade10, spadeK, spadeQ, spade9, spade8, spade7, heartA, heart10, heartK, heartQ, heart9, heart8, heart7, diamA, diam10, diamK, diamQ, diam9, diam8, diam7};
        
        // create hands with deterministic and wrong cards already (later the right cards are assigned)
        playableCPU = new Hand (cards[1], cards[2], cards[3], cards[4], cards[5], cards[6], cards[7], cards[8]);
        backhandCPU = new Hand (cards[9], cards[10], cards[11], cards[12], cards[13], cards[14], cards[15], cards[16]);
        backhandPlayer = new Hand (cards[17], cards[18], cards[19], cards[20], cards[21], cards[22], cards[23], cards[24]);
        playable = new Hand (cards[25], cards[26], cards[27], cards[28], cards[29], cards[30], cards[31], cards[32]);
        
        this.add(panel);
    }
    
    public static void main(String[] args) {
        
        // create main window for game
        windowl = new Harlekinskat3();
        windowl.setVisible(true);
        
        // set everything on English
        English.setLanguage();
        languageAsInt=1;
        
        // calling a method that opens another window for choosing the language
        window2 = new Languages("languages");
        window2.setVisible(true);
        
    }
    
    // --- methods ---
    
    // method for setting button properties using fewer lines (for readability)
    public static void setButtonProperties(JButton buttonName, int x, int y, int width, int height){
        // e.g. use like: setButtonProperties(buttonCard1Player, 50, 450, 100, 200);
        buttonName.setBounds(x, y, width, height);
        buttonName.setBorder(BorderFactory.createEmptyBorder());
        buttonName.setContentAreaFilled(false);
        buttonName.setFocusable(false);
        buttonName.setVisible(false);
    }
    
    // enter ID of the card, returns the card object
    public Skatcard cardIdToObject (int cardId)
    {
        if (cardId==1){ return clubJ;} // 1 clubJ "jack of clubs"
        if (cardId==2){ return spadeJ;} // 2 spadeJ "jack of spades"
        if (cardId==3){ return heartJ;} // 3 heartJ "jack of hearts"
        if (cardId==4){ return diamJ;} // 4 diamJ "jack of diamonds"
        if (cardId==5){ return clubA;} // 5 clubA "ace of clubs"
        if (cardId==6){ return club10;} // 6 club10 "10 of clubs"
        if (cardId==7){ return clubK;} // 7 clubK "king of clubs"
        if (cardId==8){ return clubQ;} // 8 clubQ "queen of clubs"
        if (cardId==9){ return club9;} // 9 club9 "9 of clubs"
        if (cardId==10){ return club8;} // 10 club8 "8 of clubs"
        if (cardId==11){ return club7;} // 11 club7 "7 of clubs"
        if (cardId==12){ return spadeA;} // 12 spadeA "ace of spades"
        if (cardId==13){ return spade10;} // 13 spade10 "10 of spades"
        if (cardId==14){ return spadeK;} // 14 spadeK "king of spades"
        if (cardId==15){ return spadeQ;} // 15 spadeQ "queen of spades"
        if (cardId==16){ return spade9;} // 16 spade9 "9 of spades"
        if (cardId==17){ return spade8;} // 17 spade8 "8 of spades"
        if (cardId==18){ return spade7;} // 18 spade7 "7 of spades"
        if (cardId==19){ return heartA;} // 19 heartA "ace of hearts"
        if (cardId==20){ return heart10;} // 20 heart10 "10 of hearts"
        if (cardId==21){ return heartK;} // 21 heartK "king of hearts"
        if (cardId==22){ return heartQ;} // 22 heartQ "queen of hearts"
        if (cardId==23){ return heart9;} // 23 heart9 "9 of hearts"
        if (cardId==24){ return heart8;} // 24 heart8 "8 of hearts"
        if (cardId==25){ return heart7;} // 25 heart7 "7 of hearts"
        if (cardId==26){ return diamA;} // 26 diamA "ace of diamonds"
        if (cardId==27){ return diam10;} // 27 diam10 "10 of diamonds"
        if (cardId==28){ return diamK;} // 28 diamK "king of diamonds"
        if (cardId==29){ return diamQ;} // 29 diamQ "queen of diamonds"
        if (cardId==30){ return diam9;} // 30 diam9 "9 of diamonds"
        if (cardId==31){ return diam8;} // 31 diam8 "8 of diamonds"
        if (cardId==32){ return diam7;} // 32 diam7 "7 of diamonds"
        
        if (cardId==33){ return placeholder;} // 32 diam7 "7 of diamonds"
        // this should never happen
        // (just there so compiler does not complain about missing default case)
        else {
            return placeholder;
        }
    }
    
    
    // computer AI called here
    // this function just calls the several difficulty levels, because of readibility
    // also compiler complains otherwise (because too large function)
    
    public void cpuDecision(){
        if (difficulty==1){
            finalChoice = AIEasyNormal.aiLevelEasy(playedCardPlayer, playableCPU);
        }
        else if (difficulty==2){
            finalChoice = AIEasyNormal.aiLevelNormal(playedCardPlayer, playableCPU, backhandPlayer, playable, clubJ, spadeJ, heartJ, diamJ, clubA, club10, clubK, clubQ, club9, club8, club7, spadeA, spade10, spadeK, spadeQ, spade9, spade8, spade7, heartA, heart10, heartK, heartQ, heart9, heart8, heart7, diamA, diam10, diamK, diamQ, diam9, diam8, diam7);
        }
        else if (difficulty==3){
            finalChoice = AIHard.aiLevelHard(playedCardPlayer, playableCPU, backhandCPU, clubJ, spadeJ, heartJ, diamJ, clubA, club10, clubK, clubQ, club9, club8, club7, spadeA, spade10, spadeK, spadeQ, spade9, spade8, spade7, heartA, heart10, heartK, heartQ, heart9, heart8, heart7, diamA, diam10, diamK, diamQ, diam9, diam8, diam7);
        }
        
        // no matter the difficulty level, cpu plays the card it has decided on
        cpuPlaysCard(finalChoice);
    }
    
    
    // computer plays card number (1 to 8)
    // This is just the technical aspect of the computer playing a card.
    // The decision is already been made by method cpuDecision.
    // Here the optics of the game just behaves accordingly.
    public void cpuPlaysCard(int cardNo){
        
        // depending on card number it is one of the possible 8 crads on the computer's hand
        switch (cardNo) {
            case 1: // the "face down" card gets invisible (for now)
                buttonFacedown1CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card1;
                break;
            case 2: // the "face down" card gets invisible (for now)
                buttonFacedown2CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card2;
                break;
            case 3: // the "face down" card gets invisible (for now)
                buttonFacedown3CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card3;
                break;
            case 4: // the "face down" card gets invisible (for now)
                buttonFacedown4CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card4;
                break;
            case 5: // the "face down" card gets invisible (for now)
                buttonFacedown5CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card5;
                break;
            case 6: // the "face down" card gets invisible (for now)
                buttonFacedown6CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card6;
                break;
            case 7: // the "face down" card gets invisible (for now)
                buttonFacedown7CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card7;
                break;
            case 8: // the "face down" card gets invisible (for now)
                buttonFacedown8CPU.setVisible(false);
                //  because being played
                playedCardCPU=playableCPU.card8;
                break;
        }
        // visualise the card played by the CPU
        cardIdToObject(playedCardCPU.cardId).positionInGame=6;
        buttonCardPlayedCPU.setIcon(new ImageIcon(this.getClass().getResource(playedCardCPU.imagePath)));
        buttonCardPlayedCPU.setVisible(true);
        
        // if the computer is reacting, then this turn closes
        // otherwise nothing else happens and the player has to react
        if (cpuReacts == true) {
            
            // decide which card is higher (in case of a jack easy by looking at card number)
            if ((playedCardPlayer.cardId < 5) || (playedCardCPU.cardId < 5)){ // if a jack was played by any player
                if (playedCardPlayer.cardId < playedCardCPU.cardId){ // if cpu played lower card (higher cardID)
                    playerWinsCards=true;
                }
                else {
                    playerWinsCards=false;
                }
            }
            else { // in case noone played a jack (either CPU played trump and player did not or CPU played higher trump or CPU served the color and played higher one)
                if ((playedCardCPU.color==trumpcolor && playedCardPlayer.color!=trumpcolor) || (playedCardCPU.color==trumpcolor && playedCardPlayer.color==trumpcolor && playedCardCPU.value < playedCardPlayer.value) || (playedCardCPU.color==playedCardPlayer.color && playedCardCPU.value < playedCardPlayer.value)){
                    playerWinsCards=false;
                }
                else {
                    playerWinsCards=true;
                }
            }
            
            // dialog who took the cards home
            JDialog takeJDialog = new JDialog();
            takeJDialog.setBounds(550, 300, 200, 100); // x, y (measured from upper left), width, height
            
            // close the turn, take cards home
            if (playerWinsCards==true){ // player takes cards home
                cardIdToObject(playedCardPlayer.cardId).positionInGame=7;
                cardIdToObject(playedCardCPU.cardId).positionInGame=7;
                // show dialog
                takeJDialog.add(new JLabel(stringWinCards));
                takeJDialog.setModal(true);
                takeJDialog.setVisible(true);
                // and visualise it
                buttonFacedown9Player.setVisible(true);
                // cpuReacts=true; // CPU still has to react in the next turn (is set at the end of this function)
            }
            else { // computer takes cards home
                cardIdToObject(playedCardPlayer.cardId).positionInGame=5;
                cardIdToObject(playedCardCPU.cardId).positionInGame=5;
                // show dialog
                takeJDialog.add(new JLabel(stringLoseCards));
                takeJDialog.setModal(true);
                takeJDialog.setVisible(true);
                // and visualise it
                buttonFacedown9CPU.setVisible(true);
                // cpuReacts=false; // since CPU wins cards, the CPU has to start making a move, but is set at the end of this function
            }
            // make played cards disappear
            buttonCardPlayedCPU.setVisible(false);
            buttonCardPlayedPlayer.setVisible(false);
            
            // -- check if cards have to be turned around --
            
            // check if CPU has to turn around a card
            switch (cardNo) {
                case 1: if (backhandCPU.card1==placeholder){ // if there is no additional card
                    playableCPU.card1=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card1=backhandCPU.card1;
                    cardIdToObject(playableCPU.card1.cardId).positionInGame=1;
                    buttonFacedown1CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card1=placeholder;
                    buttonCard1CPU.setVisible(false);
                } break;
                case 2: if (backhandCPU.card2==placeholder){ // if there is no additional card
                    playableCPU.card2=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card2=backhandCPU.card2;
                    cardIdToObject(playableCPU.card2.cardId).positionInGame=1;
                    buttonFacedown2CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card2=placeholder;
                    buttonCard2CPU.setVisible(false);
                } break;
                case 3: if (backhandCPU.card3==placeholder){ // if there is no additional card
                    playableCPU.card3=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card3=backhandCPU.card3;
                    cardIdToObject(playableCPU.card3.cardId).positionInGame=1;
                    buttonFacedown3CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card3=placeholder;
                    buttonCard3CPU.setVisible(false);
                } break;
                case 4: if (backhandCPU.card4==placeholder){ // if there is no additional card
                    playableCPU.card4=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card4=backhandCPU.card4;
                    cardIdToObject(playableCPU.card4.cardId).positionInGame=1;
                    buttonFacedown4CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card4=placeholder;
                    buttonCard4CPU.setVisible(false);
                } break;
                case 5: if (backhandCPU.card5==placeholder){ // if there is no additional card
                    playableCPU.card5=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card5=backhandCPU.card5;
                    cardIdToObject(playableCPU.card5.cardId).positionInGame=1;
                    buttonFacedown5CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card5=placeholder;
                    buttonCard5CPU.setVisible(false);
                } break;
                case 6: if (backhandCPU.card6==placeholder){ // if there is no additional card
                    playableCPU.card6=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card6=backhandCPU.card6;
                    cardIdToObject(playableCPU.card6.cardId).positionInGame=1;
                    buttonFacedown6CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card6=placeholder;
                    buttonCard6CPU.setVisible(false);
                } break;
                case 7: if (backhandCPU.card7==placeholder){ // if there is no additional card
                    playableCPU.card7=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card7=backhandCPU.card7;
                    cardIdToObject(playableCPU.card7.cardId).positionInGame=1;
                    buttonFacedown7CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card7=placeholder;
                    buttonCard7CPU.setVisible(false);
                } break;
                case 8: if (backhandCPU.card8==placeholder){ // if there is no additional card
                    playableCPU.card8=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to CPU
                    playableCPU.card8=backhandCPU.card8;
                    cardIdToObject(playableCPU.card8.cardId).positionInGame=1;
                    buttonFacedown8CPU.setVisible(true);
                    // card on computer's backchand disappears
                    backhandCPU.card8=placeholder;
                    buttonCard8CPU.setVisible(false);
                } break;
            }
            
            // same for player
            // check if player has to turn around a card
            int slot=cardIdToObject(playedCardPlayer.cardId).positionOnHand;
            switch (slot) {
                case 1: if (backhandPlayer.card1==placeholder){ // if there is no additional card
                    playable.card1=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card1=backhandPlayer.card1;
                    cardIdToObject(playable.card1.cardId).positionInGame=4;
                    buttonCard1Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card1.imagePath)));
                    buttonCard1Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card1=placeholder;
                    buttonFacedown1Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card1.color==trumpcolor && playable.card1.cardId>4) || playable.card1.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card1.color==1 && playable.card1.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card1.color==2 && playable.card1.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card1.color==3 && playable.card1.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card1.color==4 && playable.card1.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 2: if (backhandPlayer.card2==placeholder){ // if there is no additional card
                    playable.card2=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card2=backhandPlayer.card2;
                    cardIdToObject(playable.card2.cardId).positionInGame=4;
                    buttonCard2Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card2.imagePath)));
                    buttonCard2Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card2=placeholder;
                    buttonFacedown2Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card2.color==trumpcolor && playable.card2.cardId>4) || playable.card2.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card2.color==1 && playable.card2.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card2.color==2 && playable.card2.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card2.color==3 && playable.card2.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card2.color==4 && playable.card2.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 3: if (backhandPlayer.card3==placeholder){ // if there is no additional card
                    playable.card3=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card3=backhandPlayer.card3;
                    cardIdToObject(playable.card3.cardId).positionInGame=4;
                    buttonCard3Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card3.imagePath)));
                    buttonCard3Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card3=placeholder;
                    buttonFacedown3Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card3.color==trumpcolor && playable.card3.cardId>4) || playable.card3.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card3.color==1 && playable.card3.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card3.color==2 && playable.card3.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card3.color==3 && playable.card3.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card3.color==4 && playable.card3.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 4: if (backhandPlayer.card4==placeholder){ // if there is no additional card
                    playable.card4=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card4=backhandPlayer.card4;
                    cardIdToObject(playable.card4.cardId).positionInGame=4;
                    buttonCard4Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card4.imagePath)));
                    buttonCard4Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card4=placeholder;
                    buttonFacedown4Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card4.color==trumpcolor && playable.card4.cardId>4) || playable.card4.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card4.color==1 && playable.card4.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card4.color==2 && playable.card4.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card4.color==3 && playable.card4.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card4.color==4 && playable.card4.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 5: if (backhandPlayer.card5==placeholder){ // if there is no additional card
                    playable.card5=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card5=backhandPlayer.card5;
                    cardIdToObject(playable.card5.cardId).positionInGame=4;
                    buttonCard5Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card5.imagePath)));
                    buttonCard5Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card5=placeholder;
                    buttonFacedown5Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card5.color==trumpcolor && playable.card5.cardId>4) || playable.card5.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card5.color==1 && playable.card5.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card5.color==2 && playable.card5.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card5.color==3 && playable.card5.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card5.color==4 && playable.card5.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 6: if (backhandPlayer.card6==placeholder){ // if there is no additional card
                    playable.card6=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card6=backhandPlayer.card6;
                    cardIdToObject(playable.card6.cardId).positionInGame=4;
                    buttonCard6Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card6.imagePath)));
                    buttonCard6Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card6=placeholder;
                    buttonFacedown6Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card6.color==trumpcolor && playable.card6.cardId>4) || playable.card6.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card6.color==1 && playable.card6.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card6.color==2 && playable.card6.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card6.color==3 && playable.card6.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card6.color==4 && playable.card6.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 7: if (backhandPlayer.card7==placeholder){ // if there is no additional card
                    playable.card7=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card7=backhandPlayer.card7;
                    cardIdToObject(playable.card7.cardId).positionInGame=4;
                    buttonCard7Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card7.imagePath)));
                    buttonCard7Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card7=placeholder;
                    buttonFacedown7Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card7.color==trumpcolor && playable.card7.cardId>4) || playable.card7.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card7.color==1 && playable.card7.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card7.color==2 && playable.card7.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card7.color==3 && playable.card7.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card7.color==4 && playable.card7.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
                case 8: if (backhandPlayer.card8==placeholder){ // if there is no additional card
                    playable.card8=placeholder; // then lost the last one at that position
                }
                else {// new cards gets available to player
                    playable.card8=backhandPlayer.card8;
                    cardIdToObject(playable.card8.cardId).positionInGame=4;
                    buttonCard8Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card8.imagePath)));
                    buttonCard8Player.setVisible(true);
                    // card on player's backchand disappears
                    backhandPlayer.card8=placeholder;
                    buttonFacedown8Player.setVisible(false);
                            
                    // memory part (when player can serve again)
                    if ((playable.card8.color==trumpcolor && playable.card8.cardId>4) || playable.card8.cardId<5) {
                        if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen=numberOfTrumpCardsSinceThen+1;}
                        }
                    else {
                        if (playable.card8.color==1 && playable.card8.cardId>4) {
                            if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen=numberOfClubCardsSinceThen+1;}
                        }
                        if (playable.card8.color==2 && playable.card8.cardId>4) {
                            if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen=numberOfSpadeCardsSinceThen+1;}
                        }
                        if (playable.card8.color==3 && playable.card8.cardId>4) {
                            if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen=numberOfHeartCardsSinceThen+1;}
                        }
                        if (playable.card8.color==4 && playable.card8.cardId>4) {
                            if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen=numberOfDiamondCardsSinceThen+1;}
                        }
                    } // memory part over
                    
                } break;
            }
            
            // finish turn
            numberOfTurns=numberOfTurns+1;
            
            // if it was the last turn, end the game
            if (numberOfTurns==16){
                lock=true;
                endgame();
            }
            else { // if it is not the last turn
                if (playerWinsCards==true){
                    cpuReacts=true;
                    lock=false; // unlock the game for the player
                }
                else {
                    cpuReacts=false;
                    lock=true;
                    cpuDecision(); // let computer make another decision
                }
            }
            
        }
        else {
            lock=false; // unlock the game for the player again
        }
    }
    
    // player plays card number (1 to 8)
    // This is the technical aspect of the player playing a card.
    // But it is also checked, if the attempted card is valid for playing.
    // Here the optics of the game just behaves accordingly.
    public void playerPlaysCard(int cardNo){
        
        // only do something at all, if the game is unlocked to the player
        if (lock==false){
            
            // set the card played by the player to the placeholder (so the closing of the turn can only occur, if this has been changed)
            playedCardPlayer=placeholder;
            
            // first decide if the player starts playing or not
            if (cpuReacts==true){ // player may play any card (if it still exists)
                // make only branches for which the card in question is not the placeholder (for a non-existing card)
            
                switch (cardNo) {
                    case 1: if (playable.card1.cardId < 33){ // if there is still a card
                        buttonCard1Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card1; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 2: if (playable.card2.cardId < 33){ // if there is still a card
                        buttonCard2Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card2; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 3: if (playable.card3.cardId < 33){ // if there is still a card
                        buttonCard3Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card3; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 4: if (playable.card4.cardId < 33){ // if there is still a card
                        buttonCard4Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card4; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 5: if (playable.card5.cardId < 33){ // if there is still a card
                        buttonCard5Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card5; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 6: if (playable.card6.cardId < 33){ // if there is still a card
                        buttonCard6Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card6; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 7: if (playable.card7.cardId < 33){ // if there is still a card
                        buttonCard7Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card7; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                    case 8: if (playable.card8.cardId < 33){ // if there is still a card
                        buttonCard8Player.setVisible(false); // the "face down" card gets invisible (for now)
                        playedCardPlayer=playable.card8; // because being played
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                        break;
                    } break; // additional breaks needed in case one played invalid card
                }
                // if the player actually played a card
                if (playedCardPlayer.cardId < 33){
                    // visualise the card played by the player
                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                    buttonCardPlayedPlayer.setVisible(true);
                    // ask the computer to play a card
                    lock=true;
                    
                    // memory part (used by CPU difficulty 2)
                    // (if the player could serve again, but gets rid of a card of the color)
                    if (playedCardPlayer.color==trumpcolor) {
                        if (couldntServeTrumpBefore=true) {numberOfTrumpCardsSinceThen--;}
                    }
                    else {
                        if (couldntServeClubsBefore==true && playedCardPlayer.color==1){numberOfClubCardsSinceThen--;}
                        if (couldntServeSpadesBefore==true && playedCardPlayer.color==2){numberOfSpadeCardsSinceThen--;}
                        if (couldntServeHeartsBefore==true && playedCardPlayer.color==3){numberOfHeartCardsSinceThen--;}
                        if (couldntServeDiamondsBefore==true && playedCardPlayer.color==4){numberOfDiamondCardsSinceThen--;}
                    } // memory part over
                    
                    // Since the computer is reacting too fast,
                    // just make a small dialog telling is a trump 7, 8, ... A whatever
                    // or whatever color 7, 8, ... A has been played
                    // (or simply use the displayName attribute if being lazy)
                    // decideJDialog.add(new JLabel(" You played the " + playedCardPlayer.displayName + "."));
                    
                    // for making the cpu wait until you are ready to see its reaction
                    JDialog decideJDialog = new JDialog();
                    decideJDialog.setBounds(550, 300, 250, 100);
                    if (playedCardPlayer.cardId==1){
                        decideJDialog.add(new JLabel(stringPlayedJ1));
                    }
                    else if (playedCardPlayer.cardId==2){
                        decideJDialog.add(new JLabel(stringPlayedJ2));
                    }
                    else if (playedCardPlayer.cardId==3){
                        decideJDialog.add(new JLabel(stringPlayedJ3));
                    }
                    else if (playedCardPlayer.cardId==4){
                        decideJDialog.add(new JLabel(stringPlayedJ4));
                    }
                    else if (playedCardPlayer.cardId==5){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedTA));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedCA));
                        }
                    }
                    else if (playedCardPlayer.cardId==6){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedT10));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedC10));
                        }
                    }
                    else if (playedCardPlayer.cardId==7){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedTK));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedCK));
                        }
                    }
                    else if (playedCardPlayer.cardId==8){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedTQ));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedCQ));
                        }
                    }
                    else if (playedCardPlayer.cardId==9){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedT9));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedC9));
                        }
                    }
                    else if (playedCardPlayer.cardId==10){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedT8));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedC8));
                        }
                    }
                    else if (playedCardPlayer.cardId==11){
                        if (trumpcolor==1){
                            decideJDialog.add(new JLabel(stringPlayedT7));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedC7));
                        }
                    }
                    else if (playedCardPlayer.cardId==12){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedTA));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedSA));
                        }
                    }
                    else if (playedCardPlayer.cardId==13){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedT10));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedS10));
                        }
                    }
                    else if (playedCardPlayer.cardId==14){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedTK));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedSK));
                        }
                    }
                    else if (playedCardPlayer.cardId==15){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedTQ));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedSQ));
                        }
                    }
                    else if (playedCardPlayer.cardId==16){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedT9));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedS9));
                        }
                    }
                    else if (playedCardPlayer.cardId==17){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedT8));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedS8));
                        }
                    }
                    else if (playedCardPlayer.cardId==18){
                        if (trumpcolor==2){
                            decideJDialog.add(new JLabel(stringPlayedT7));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedS7));
                        }
                    }
                    else if (playedCardPlayer.cardId==19){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedTA));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedHA));
                        }
                    }
                    else if (playedCardPlayer.cardId==20){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedT10));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedH10));
                        }
                    }
                    else if (playedCardPlayer.cardId==21){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedTK));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedHK));
                        }
                    }
                    else if (playedCardPlayer.cardId==22){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedTQ));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedHQ));
                        }
                    }
                    else if (playedCardPlayer.cardId==23){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedT9));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedH9));
                        }
                    }
                    else if (playedCardPlayer.cardId==24){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedT8));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedH8));
                        }
                    }
                    else if (playedCardPlayer.cardId==25){
                        if (trumpcolor==3){
                            decideJDialog.add(new JLabel(stringPlayedT7));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedH7));
                        }
                    }
                    else if (playedCardPlayer.cardId==26){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedTA));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedDA));
                        }
                    }
                    else if (playedCardPlayer.cardId==27){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedT10));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedD10));
                        }
                    }
                    else if (playedCardPlayer.cardId==28){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedTK));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedDK));
                        }
                    }
                    else if (playedCardPlayer.cardId==29){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedTQ));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedDQ));
                        }
                    }
                    else if (playedCardPlayer.cardId==30){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedT9));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedD9));
                        }
                    }
                    else if (playedCardPlayer.cardId==31){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedT8));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedD8));
                        }
                    }
                    else if (playedCardPlayer.cardId==32){
                        if (trumpcolor==4){
                            decideJDialog.add(new JLabel(stringPlayedT7));
                        }
                        else {
                            decideJDialog.add(new JLabel(stringPlayedD7));
                        }
                    }
                    decideJDialog.setModal(true);
                    decideJDialog.setVisible(true);
                    
                    // or try waiting function first (before relying on a modall dialog) [1.5sec should be alright as CPU-"reaction time"]
                    //try {TimeUnit.MILLISECONDS.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();} // don't ask, but one has to try/catch an exception, or else the compiler is complaining
                    // No, this "waiting" only waits first and after 1.5sec suddenly lets both players play the cards instantly.
                    // This is not, what I want!
                    
                    cpuDecision();
                }
                
            }
            else { // first determine what has been played and if player has the same "color"
                if ((playedCardCPU.color==trumpcolor) || (playedCardCPU.cardId < 5)){ // if a jack or other trump has been played
                    trumpPlayed=true;
                    currentColor=trumpcolor;
                    // look if player has also a trump card
                    if ((playable.card1.color==trumpcolor && playable.card1.cardId > 4) || playable.card1.cardId < 5 || (playable.card2.color==trumpcolor && playable.card2.cardId > 4) || playable.card2.cardId < 5 || (playable.card3.color==trumpcolor && playable.card3.cardId > 4) || playable.card3.cardId < 5 || (playable.card4.color==trumpcolor && playable.card4.cardId > 4) || playable.card4.cardId < 5 || (playable.card5.color==trumpcolor && playable.card5.cardId > 4) || playable.card5.cardId < 5 || (playable.card6.color==trumpcolor && playable.card6.cardId > 4) || playable.card6.cardId < 5 || (playable.card7.color==trumpcolor && playable.card7.cardId > 4) || playable.card7.cardId < 5 || (playable.card8.color==trumpcolor && playable.card8.cardId > 4) || playable.card8.cardId < 5){
                        servable=true;
                    }
                    else {
                        servable=false;
                    }
                }
                else {
                    trumpPlayed=false;
                    currentColor=playedCardCPU.color;
                    // look if player has a card of the same non-trump color
                    if ((playable.card1.color==currentColor && playable.card1.cardId > 4) || (playable.card2.color==currentColor && playable.card2.cardId > 4) || (playable.card3.color==currentColor && playable.card3.cardId > 4) || (playable.card4.color==currentColor && playable.card4.cardId > 4) || (playable.card5.color==currentColor && playable.card5.cardId > 4) || (playable.card6.color==currentColor && playable.card6.cardId > 4) || (playable.card7.color==currentColor && playable.card7.cardId > 4) || (playable.card8.color==currentColor && playable.card8.cardId > 4)){
                        servable=true;
                    }
                    else {
                        servable=false;
                    }
                }
                // if player has not a card of the same "color", just play the attempted card
                if (servable==false){
                    
                    switch (cardNo) {
                        case 1: if (playable.card1.cardId < 33){ // if there is still a card
                            buttonCard1Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card1; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 2: if (playable.card2.cardId < 33){ // if there is still a card
                            buttonCard2Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card2; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 3: if (playable.card3.cardId < 33){ // if there is still a card
                            buttonCard3Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card3; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 4: if (playable.card4.cardId < 33){ // if there is still a card
                            buttonCard4Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card4; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 5: if (playable.card5.cardId < 33){ // if there is still a card
                            buttonCard5Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card5; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 6: if (playable.card6.cardId < 33){ // if there is still a card
                            buttonCard6Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card6; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 7: if (playable.card7.cardId < 33){ // if there is still a card
                            buttonCard7Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card7; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                        case 8: if (playable.card8.cardId < 33){ // if there is still a card
                            buttonCard8Player.setVisible(false); // the "face down" card gets invisible (for now)
                            playedCardPlayer=playable.card8; // because being played
                            cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                            // visualise the card played by the Player
                            buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                            buttonCardPlayedPlayer.setVisible(true); // closing part at end of method
                            
                            // memory part (used by CPU difficulty 2)
                            if (trumpPlayed==true) {
                                couldntServeTrumpBefore=true;
                            }
                            else {
                                if (currentColor==1){couldntServeClubsBefore=true;}
                                if (currentColor==2){couldntServeSpadesBefore=true;}
                                if (currentColor==3){couldntServeHeartsBefore=true;}
                                if (currentColor==4){couldntServeDiamondsBefore=true;}
                            } // memory part over
                            
                            if (playedCardPlayer.color==trumpcolor || playedCardPlayer.cardId <5){ // one only wins the card by playing trump
                                playerWinsCards=true;
                            }
                            else {
                                playerWinsCards=false;
                            }
                            break;
                        } break; // additional breaks needed in case one played invalid card
                    }
                }
                else { // if the player can serve the color, then only do something, if a valid card has been played
                    
                    switch (cardNo) {
                        case 1: if (playable.card1.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card1.cardId < 5 || playable.card1.color==trumpcolor){
                                    buttonCard1Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card1; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card1.color==currentColor && playable.card1.cardId > 4){
                                    buttonCard1Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card1; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 2: if (playable.card2.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card2.cardId < 5 || playable.card2.color==trumpcolor){
                                    buttonCard2Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card2; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card2.color==currentColor && playable.card2.cardId > 4){
                                    buttonCard2Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card2; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 3: if (playable.card3.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card3.cardId < 5 || playable.card3.color==trumpcolor){
                                    buttonCard3Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card3; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card3.color==currentColor && playable.card3.cardId > 4){
                                    buttonCard3Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card3; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 4: if (playable.card4.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card4.cardId < 5 || playable.card4.color==trumpcolor){
                                    buttonCard4Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card4; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card4.color==currentColor && playable.card4.cardId > 4){
                                    buttonCard4Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card4; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 5: if (playable.card5.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card5.cardId < 5 || playable.card5.color==trumpcolor){
                                    buttonCard5Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card5; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card5.color==currentColor && playable.card5.cardId > 4){
                                    buttonCard5Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card5; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 6: if (playable.card6.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card6.cardId < 5 || playable.card6.color==trumpcolor){
                                    buttonCard6Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card6; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card6.color==currentColor && playable.card6.cardId > 4){
                                    buttonCard6Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card6; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 7: if (playable.card7.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card7.cardId < 5 || playable.card7.color==trumpcolor){
                                    buttonCard7Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card7; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card7.color==currentColor && playable.card7.cardId > 4){
                                    buttonCard7Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card7; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                        case 8: if (playable.card8.cardId < 33){ // if there is still a card
                            if (trumpPlayed==true){
                                if (playable.card8.cardId < 5 || playable.card8.color==trumpcolor){
                                    buttonCard8Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card8; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.cardId < playedCardCPU.cardId){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                            else {
                                if (playable.card8.color==currentColor && playable.card8.cardId > 4){
                                    buttonCard8Player.setVisible(false); // the "face down" card gets invisible (for now)
                                    playedCardPlayer=playable.card8; //  because being played
                                    cardIdToObject(playedCardPlayer.cardId).positionInGame=8;
                                    buttonCardPlayedPlayer.setIcon(new ImageIcon(this.getClass().getResource(playedCardPlayer.imagePath)));
                                    buttonCardPlayedPlayer.setVisible(true); // visualise the card played by the Player
                                    if (playedCardPlayer.value < playedCardCPU.value){
                                        playerWinsCards=true;
                                    }
                                    else {
                                        playerWinsCards=false;
                                    }
                                    break;
                                }
                                break; // additional breaks needed in case one played invalid card
                            }
                        } break; // additional breaks needed in case one played invalid card
                    }
                }
                // from here on only something happens, if a card actually has been played (if it was valid when chosen)
                if (playedCardPlayer.cardId < 33){
                    
                    // memory part (used by CPU difficulty 2)
                    // (if the player could serve again, but gets rid of a card of the color)
                    if (playedCardPlayer.color==trumpcolor) {
                        if (couldntServeTrumpBefore=true) {numberOfTrumpCardsSinceThen--;}
                    }
                    else {
                        if (couldntServeClubsBefore==true && playedCardPlayer.color==1){numberOfClubCardsSinceThen--;}
                        if (couldntServeSpadesBefore==true && playedCardPlayer.color==2){numberOfSpadeCardsSinceThen--;}
                        if (couldntServeHeartsBefore==true && playedCardPlayer.color==3){numberOfHeartCardsSinceThen--;}
                        if (couldntServeDiamondsBefore==true && playedCardPlayer.color==4){numberOfDiamondCardsSinceThen--;}
                    } // memory part over
                    
                    // just as feedback (works)
                    //JDialog decideJDialog = new JDialog();
                    //decideJDialog.setBounds(550, 300, 500, 100);
                    //decideJDialog.add(new JLabel(" reacted by having played card " + playedCardPlayer.positionOnHand + "."));
                    //decideJDialog.setModal(true);
                    //decideJDialog.setVisible(true);
                    
                    
                    // since the winner of the two cards has already been decided, if a valid card has been played:
                    // dialog who took the cards home
                    JDialog takeJDialog = new JDialog();
                    takeJDialog.setBounds(550, 300, 200, 100); // x, y (measured from upper left), width, height
            
                    // close the turn, take cards home
                    if (playerWinsCards==true){ // player takes cards home
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=7;
                        cardIdToObject(playedCardCPU.cardId).positionInGame=7;
                        // show dialog
                        takeJDialog.add(new JLabel(stringWinCards));
                        takeJDialog.setModal(true);
                        takeJDialog.setVisible(true);
                        // and visualise it
                        buttonFacedown9Player.setVisible(true);
                        // cpuReacts=true; // CPU still has to react in the next turn (is set at the end)
                    }
                    else { // computer takes cards home
                        cardIdToObject(playedCardPlayer.cardId).positionInGame=5;
                        cardIdToObject(playedCardCPU.cardId).positionInGame=5;
                        // show dialog
                        takeJDialog.add(new JLabel(stringLoseCards));
                        takeJDialog.setModal(true);
                        takeJDialog.setVisible(true);
                        // and visualise it
                        buttonFacedown9CPU.setVisible(true);
                        // cpuReacts=false; // since CPU wins cards, the CPU has to start making a move, but it is set at the end of this method
                    }
                    // make played cards disappear
                    buttonCardPlayedCPU.setVisible(false);
                    buttonCardPlayedPlayer.setVisible(false);
            
                    // -- check if cards have to be turned around --
                    
                    // check if player has to turn around a card
                    switch (cardNo) {
                        case 1: if (backhandPlayer.card1.cardId == 33){ // if there is no additional card
                            playable.card1=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card1=backhandPlayer.card1;
                            cardIdToObject(playable.card1.cardId).positionInGame=4;
                            buttonCard1Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card1.imagePath)));
                            buttonCard1Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card1=placeholder;
                            buttonFacedown1Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card1.color==trumpcolor && playable.card1.cardId>4) || playable.card1.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card1.color==1 && playable.card1.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card1.color==2 && playable.card1.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card1.color==3 && playable.card1.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card1.color==4 && playable.card1.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 2: if (backhandPlayer.card2.cardId == 33){ // if there is no additional card
                            playable.card2=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card2=backhandPlayer.card2;
                            cardIdToObject(playable.card2.cardId).positionInGame=4;
                            buttonCard2Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card2.imagePath)));
                            buttonCard2Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card2=placeholder;
                            buttonFacedown2Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card2.color==trumpcolor && playable.card2.cardId>4) || playable.card2.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card2.color==1 && playable.card2.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card2.color==2 && playable.card2.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card2.color==3 && playable.card2.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card2.color==4 && playable.card2.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 3: if (backhandPlayer.card3.cardId == 33){ // if there is no additional card
                            playable.card3=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card3=backhandPlayer.card3;
                            cardIdToObject(playable.card3.cardId).positionInGame=4;
                            buttonCard3Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card3.imagePath)));
                            buttonCard3Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card3=placeholder;
                            buttonFacedown3Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card3.color==trumpcolor && playable.card3.cardId>4) || playable.card3.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card3.color==1 && playable.card3.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card3.color==2 && playable.card3.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card3.color==3 && playable.card3.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card3.color==4 && playable.card3.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 4: if (backhandPlayer.card4.cardId == 33){ // if there is no additional card
                            playable.card4=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card4=backhandPlayer.card4;
                            cardIdToObject(playable.card4.cardId).positionInGame=4;
                            buttonCard4Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card4.imagePath)));
                            buttonCard4Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card4=placeholder;
                            buttonFacedown4Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card4.color==trumpcolor && playable.card4.cardId>4) || playable.card4.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card4.color==1 && playable.card4.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card4.color==2 && playable.card4.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card4.color==3 && playable.card4.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card4.color==4 && playable.card4.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 5: if (backhandPlayer.card5.cardId == 33){ // if there is no additional card
                            playable.card5=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card5=backhandPlayer.card5;
                            cardIdToObject(playable.card5.cardId).positionInGame=4;
                            buttonCard5Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card5.imagePath)));
                            buttonCard5Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card5=placeholder;
                            buttonFacedown5Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card5.color==trumpcolor && playable.card5.cardId>4) || playable.card5.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card5.color==1 && playable.card5.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card5.color==2 && playable.card5.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card5.color==3 && playable.card5.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card5.color==4 && playable.card5.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 6: if (backhandPlayer.card6.cardId == 33){ // if there is no additional card
                            playable.card6=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card6=backhandPlayer.card6;
                            cardIdToObject(playable.card6.cardId).positionInGame=4;
                            buttonCard6Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card6.imagePath)));
                            buttonCard6Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card6=placeholder;
                            buttonFacedown6Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card6.color==trumpcolor && playable.card6.cardId>4) || playable.card6.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card6.color==1 && playable.card6.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card6.color==2 && playable.card6.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card6.color==3 && playable.card6.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card6.color==4 && playable.card6.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 7: if (backhandPlayer.card7.cardId == 33){ // if there is no additional card
                            playable.card7=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card7=backhandPlayer.card7;
                            cardIdToObject(playable.card7.cardId).positionInGame=4;
                            buttonCard7Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card7.imagePath)));
                            buttonCard7Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card7=placeholder;
                            buttonFacedown7Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card7.color==trumpcolor && playable.card7.cardId>4) || playable.card7.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card7.color==1 && playable.card7.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card7.color==2 && playable.card7.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card7.color==3 && playable.card7.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card7.color==4 && playable.card7.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                        case 8: if (backhandPlayer.card8.cardId == 33){ // if there is no additional card
                            playable.card8=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to player
                            playable.card8=backhandPlayer.card8;
                            cardIdToObject(playable.card8.cardId).positionInGame=4;
                            buttonCard8Player.setIcon(new ImageIcon(this.getClass().getResource(playable.card8.imagePath)));
                            buttonCard8Player.setVisible(true);
                            // card on player's backchand disappears
                            backhandPlayer.card8=placeholder;
                            buttonFacedown8Player.setVisible(false);
                            
                            // memory part (when player can serve again)
                            if ((playable.card8.color==trumpcolor && playable.card8.cardId>4) || playable.card8.cardId<5) {
                                if (couldntServeTrumpBefore==true) {numberOfTrumpCardsSinceThen++;}
                            }
                            else {
                                if (playable.card8.color==1 && playable.card8.cardId>4) {
                                    if (couldntServeClubsBefore==true) {numberOfClubCardsSinceThen++;}
                                }
                                if (playable.card8.color==2 && playable.card8.cardId>4) {
                                    if (couldntServeSpadesBefore==true) {numberOfSpadeCardsSinceThen++;}
                                }
                                if (playable.card8.color==3 && playable.card8.cardId>4) {
                                    if (couldntServeHeartsBefore==true) {numberOfHeartCardsSinceThen++;}
                                }
                                if (playable.card8.color==4 && playable.card8.cardId>4) {
                                    if (couldntServeDiamondsBefore==true) {numberOfDiamondCardsSinceThen++;}
                                }
                            } // memory part over
                            
                        } break;
                    }
            
                    // same for computer
                    // check if CPU has to turn around a card
                    int slot=cardIdToObject(playedCardCPU.cardId).positionOnHand;
                    switch (slot) {
                        case 1: if (backhandCPU.card1.cardId == 33){ // if there is no additional card
                            playableCPU.card1=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card1=backhandCPU.card1;
                            cardIdToObject(playableCPU.card1.cardId).positionInGame=1;
                            buttonFacedown1CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card1=placeholder;
                            buttonCard1CPU.setVisible(false);
                        } break;
                        case 2: if (backhandCPU.card2.cardId == 33){ // if there is no additional card
                            playableCPU.card2=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card2=backhandCPU.card2;
                            cardIdToObject(playableCPU.card2.cardId).positionInGame=1;
                            buttonFacedown2CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card2=placeholder;
                            buttonCard2CPU.setVisible(false);
                        } break;
                        case 3: if (backhandCPU.card3.cardId == 33){ // if there is no additional card
                            playableCPU.card3=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card3=backhandCPU.card3;
                            cardIdToObject(playableCPU.card3.cardId).positionInGame=1;
                            buttonFacedown3CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card3=placeholder;
                            buttonCard3CPU.setVisible(false);
                        } break;
                        case 4: if (backhandCPU.card4.cardId == 33){ // if there is no additional card
                            playableCPU.card4=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card4=backhandCPU.card4;
                            cardIdToObject(playableCPU.card4.cardId).positionInGame=1;
                            buttonFacedown4CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card4=placeholder;
                            buttonCard4CPU.setVisible(false);
                        } break;
                        case 5: if (backhandCPU.card5.cardId == 33){ // if there is no additional card
                            playableCPU.card5=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card5=backhandCPU.card5;
                            cardIdToObject(playableCPU.card5.cardId).positionInGame=1;
                            buttonFacedown5CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card5=placeholder;
                            buttonCard5CPU.setVisible(false);
                        } break;
                        case 6: if (backhandCPU.card6.cardId == 33){ // if there is no additional card
                            playableCPU.card6=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card6=backhandCPU.card6;
                            cardIdToObject(playableCPU.card6.cardId).positionInGame=1;
                            buttonFacedown6CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card6=placeholder;
                            buttonCard6CPU.setVisible(false);
                        } break;
                        case 7: if (backhandCPU.card7.cardId == 33){ // if there is no additional card
                            playableCPU.card7=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card7=backhandCPU.card7;
                            cardIdToObject(playableCPU.card7.cardId).positionInGame=1;
                            buttonFacedown7CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card7=placeholder;
                            buttonCard7CPU.setVisible(false);
                        } break;
                        case 8: if (backhandCPU.card8.cardId == 33){ // if there is no additional card
                            playableCPU.card8=placeholder; // then lost the last one at that position
                        }
                        else {// new cards gets available to CPU
                            playableCPU.card8=backhandCPU.card8;
                            cardIdToObject(playableCPU.card8.cardId).positionInGame=1;
                            buttonFacedown8CPU.setVisible(true);
                            // card on computer's backchand disappears
                            backhandCPU.card8=placeholder;
                            buttonCard8CPU.setVisible(false);
                        } break;
                    }
            
                    // finish turn
                    numberOfTurns=numberOfTurns+1;
                    
                    // if it was the last turn, end the game
                    if (numberOfTurns==16){
                        lock=true;
                        endgame();
                    }
                    else { // if it is not the last turn
                        if (playerWinsCards==true){
                            cpuReacts=true;
                            lock=false; // unlock the game for the player
                        }
                        else {
                            cpuReacts=false;
                            lock=true;
                            cpuDecision(); // let computer make another decision
                        }
                    }
                    
                }
            }
        }
    }
    
    
    // method for tranlating the card names that are used at the end of the game when calculating the points
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
    
    // if 16 turns have been completed (thus all cards have been played)
    // add up the obtained points
    public void endgame(){
        translateCardNames(); // translates the card names into the right language (this is doing it every Game, because Java is not allowing collision of static & non-static stuff)
        JScrollPane scrollPane = new JScrollPane();
        JDialog endJDialog = new JDialog();
        endJDialog.setTitle(stringGameOver);
        endJDialog.setBounds(400, 200, 400, 300); // x, y (measured from upper left), width, height
        //endJDialog.setModal(true); // dialog has to be answered before anything else can happen (but it has to have an ok-button to work as modal?)
        endJDialog.getContentPane().setLayout(new BorderLayout(0, 0));
        endJDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
        endJDialog.setVisible(true);
        
        wonCards = new String();
        wonPoints = 0;
        // look up, if card has been won by player (if so, then add it points)
        // begin with aces and tens, then the "images/pictures" (king, queen, jack)
        // then list the won low cards
        if (clubA.positionInGame == 7){
            wonCards = wonCards + "A " + clubA.displayName + "<br>";
            wonPoints = wonPoints + clubA.points;
        }
        if (spadeA.positionInGame == 7){
            wonCards = wonCards + "A " + spadeA.displayName + "<br>";
            wonPoints = wonPoints + spadeA.points;
        }
        if (heartA.positionInGame == 7){
            wonCards = wonCards + "A " + heartA.displayName + "<br>";
            wonPoints = wonPoints + heartA.points;
        }
        if (diamA.positionInGame == 7){
            wonCards = wonCards + "A " + diamA.displayName + "<br>";
            wonPoints = wonPoints + diamA.points;
        }
        if (club10.positionInGame == 7){
            wonCards = wonCards + club10.displayName + "<br>";
            wonPoints = wonPoints + club10.points;
        }
        if (spade10.positionInGame == 7){
            wonCards = wonCards + spade10.displayName + "<br>";
            wonPoints = wonPoints + spade10.points;
        }
        if (heart10.positionInGame == 7){
            wonCards = wonCards + heart10.displayName + "<br>";
            wonPoints = wonPoints + heart10.points;
        }
        if (diam10.positionInGame == 7){
            wonCards = wonCards + diam10.displayName + "<br>";
            wonPoints = wonPoints + diam10.points;
        }
        if (clubK.positionInGame == 7){
            wonCards = wonCards + "K " + clubK.displayName + "<br>";
            wonPoints = wonPoints + clubK.points;
        }
        if (spadeK.positionInGame == 7){
            wonCards = wonCards + "K " + spadeK.displayName + "<br>";
            wonPoints = wonPoints + spadeK.points;
        }
        if (heartK.positionInGame == 7){
            wonCards = wonCards + "K " + heartK.displayName + "<br>";
            wonPoints = wonPoints + heartK.points;
        }
        if (diamK.positionInGame == 7){
            wonCards = wonCards + "K " + diamK.displayName + "<br>";
            wonPoints = wonPoints + diamK.points;
        }
        if (clubQ.positionInGame == 7){
            wonCards = wonCards + "Q " + clubQ.displayName + "<br>";
            wonPoints = wonPoints + clubQ.points;
        }
        if (spadeQ.positionInGame == 7){
            wonCards = wonCards + "Q " + spadeQ.displayName + "<br>";
            wonPoints = wonPoints + spadeQ.points;
        }
        if (heartQ.positionInGame == 7){
            wonCards = wonCards + "Q " + heartQ.displayName + "<br>";
            wonPoints = wonPoints + heartQ.points;
        }
        if (diamQ.positionInGame == 7){
            wonCards = wonCards + "Q " + diamQ.displayName + "<br>";
            wonPoints = wonPoints + diamQ.points;
        }
        if (clubJ.positionInGame == 7){
            wonCards = wonCards + "J " + clubJ.displayName + "<br>";
            wonPoints = wonPoints + clubJ.points;
        }
        if (spadeJ.positionInGame == 7){
            wonCards = wonCards + "J " + spadeJ.displayName + "<br>";
            wonPoints = wonPoints + spadeJ.points;
        }
        if (heartJ.positionInGame == 7){
            wonCards = wonCards + "J " + heartJ.displayName + "<br>";
            wonPoints = wonPoints + heartJ.points;
        }
        if (diamJ.positionInGame == 7){
            wonCards = wonCards + "J " + diamJ.displayName + "<br>";
            wonPoints = wonPoints + diamJ.points;
        }
        // beginning from here one could leave out the adding up of points, since they are zero
        if (club9.positionInGame == 7){
            wonCards = wonCards + club9.displayName + "<br>";
            wonPoints = wonPoints + club9.points;
        }
        if (spade9.positionInGame == 7){
            wonCards = wonCards + spade9.displayName + "<br>";
            wonPoints = wonPoints + spade9.points;
        }
        if (heart9.positionInGame == 7){
            wonCards = wonCards + heart9.displayName + "<br>";
            wonPoints = wonPoints + heart9.points;
        }
        if (diam9.positionInGame == 7){
            wonCards = wonCards + diam9.displayName + "<br>";
            wonPoints = wonPoints + diam9.points;
        }
        if (club8.positionInGame == 7){
            wonCards = wonCards + club8.displayName + "<br>";
            wonPoints = wonPoints + club8.points;
        }
        if (spade8.positionInGame == 7){
            wonCards = wonCards + spade8.displayName + "<br>";
            wonPoints = wonPoints + spade8.points;
        }
        if (heart8.positionInGame == 7){
            wonCards = wonCards + heart8.displayName + "<br>";
            wonPoints = wonPoints + heart8.points;
        }
        if (diam8.positionInGame == 7){
            wonCards = wonCards + diam8.displayName + "<br>";
            wonPoints = wonPoints + diam8.points;
        }
        if (club7.positionInGame == 7){
            wonCards = wonCards + club7.displayName + "<br>";
            wonPoints = wonPoints + club7.points;
        }
        if (spade7.positionInGame == 7){
            wonCards = wonCards + spade7.displayName + "<br>";
            wonPoints = wonPoints + spade7.points;
        }
        if (heart7.positionInGame == 7){
            wonCards = wonCards + heart7.displayName + "<br>";
            wonPoints = wonPoints + heart7.points;
        }
        if (diam7.positionInGame == 7){
            wonCards = wonCards + diam7.displayName + "<br>";
            wonPoints = wonPoints + diam7.points;
        }
        
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
    
    
    // from here on:
    // --- reactions of the buttons ---
    
    public void actionPerformed (ActionEvent ae){
        
        // button that sets the difficulty
        if(ae.getSource() == this.buttonDifficulty){
            int cpulevel = JOptionPane.showOptionDialog(null, stringDifficultyDialog1, stringDifficultyDialog2, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{stringEasy, stringNormal, stringHard}, stringNormal);
            // returns 0, 1, 2 for the three buttons
            if (cpulevel == -1) { // in case one accidently just closed the dialog window
                cpulevel=1; // will eventually also result in standard difficulty level
            }
            difficulty = cpulevel +1; // difficulty 1=easy, 2=normal, 3=hard
            // feedback dialog
            JDialog difficultyDialog = new JDialog();
            difficultyDialog.setBounds(550, 300, 200, 100); // x, y (measured from upper left), width, height
            difficultyDialog.setModal(true);
            switch (difficulty) {
                case 1: difficultyDialog.add(new JLabel(stringEasyAffirm));
                    break;
                case 2: difficultyDialog.add(new JLabel(stringNormalAffirm));
                    break;
                case 3: difficultyDialog.add(new JLabel(stringHardAffirm));
                    break;
            }
            difficultyDialog.setVisible(true);
            
        }
        
        // button that (re)starts the game
        if(ae.getSource() == this.buttonStart){
            // make everything invisible first 
            buttonCard1CPU.setVisible(false);
            buttonCard2CPU.setVisible(false);
            buttonCard3CPU.setVisible(false);
            buttonCard4CPU.setVisible(false);
            buttonCard5CPU.setVisible(false);
            buttonCard6CPU.setVisible(false);
            buttonCard7CPU.setVisible(false);
            buttonCard8CPU.setVisible(false);
            buttonCard1Player.setVisible(false);
            buttonCard2Player.setVisible(false);
            buttonCard3Player.setVisible(false);
            buttonCard4Player.setVisible(false);
            buttonCard5Player.setVisible(false);
            buttonCard6Player.setVisible(false);
            buttonCard7Player.setVisible(false);
            buttonCard8Player.setVisible(false);
            
            buttonFacedown1CPU.setVisible(false);
            buttonFacedown2CPU.setVisible(false);
            buttonFacedown3CPU.setVisible(false);
            buttonFacedown4CPU.setVisible(false);
            buttonFacedown5CPU.setVisible(false);
            buttonFacedown6CPU.setVisible(false);
            buttonFacedown7CPU.setVisible(false);
            buttonFacedown8CPU.setVisible(false);
            buttonFacedown1Player.setVisible(false);
            buttonFacedown2Player.setVisible(false);
            buttonFacedown3Player.setVisible(false);
            buttonFacedown4Player.setVisible(false);
            buttonFacedown5Player.setVisible(false);
            buttonFacedown6Player.setVisible(false);
            buttonFacedown7Player.setVisible(false);
            buttonFacedown8Player.setVisible(false);
            
            // turn the taken home cards & played cards invisible
            buttonFacedown9Player.setVisible(false);
            buttonFacedown9CPU.setVisible(false);
            buttonCardPlayedCPU.setVisible(false);
            buttonCardPlayedPlayer.setVisible(false);
            // reset the counter for the number of already played turns (important for triggering the end game)
            numberOfTurns=0;
            
            // reset/erase memory of CPU (important, otherwise the CPU might play weird on normal difficulty)
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
            
            array32 = Skatcard.shuffleDeck(); // everything static seems to work so far
            
            // deal all cards
            
            // assign the right cards to the hands of the players 
            // change the variables for positions on hand and in game accordingly
            playableCPU.card1 = cards[array32[0]]; cardIdToObject(playableCPU.card1.cardId).positionOnHand=1; cardIdToObject(playableCPU.card1.cardId).positionInGame=1;
            playableCPU.card2 = cards[array32[1]]; cardIdToObject(playableCPU.card2.cardId).positionOnHand=2; cardIdToObject(playableCPU.card2.cardId).positionInGame=1;
            playableCPU.card3 = cards[array32[2]]; cardIdToObject(playableCPU.card3.cardId).positionOnHand=3; cardIdToObject(playableCPU.card3.cardId).positionInGame=1;
            playableCPU.card4 = cards[array32[3]]; cardIdToObject(playableCPU.card4.cardId).positionOnHand=4; cardIdToObject(playableCPU.card4.cardId).positionInGame=1;
            playableCPU.card5 = cards[array32[4]]; cardIdToObject(playableCPU.card5.cardId).positionOnHand=5; cardIdToObject(playableCPU.card5.cardId).positionInGame=1;
            playableCPU.card6 = cards[array32[5]]; cardIdToObject(playableCPU.card6.cardId).positionOnHand=6; cardIdToObject(playableCPU.card6.cardId).positionInGame=1;
            playableCPU.card7 = cards[array32[6]]; cardIdToObject(playableCPU.card7.cardId).positionOnHand=7; cardIdToObject(playableCPU.card7.cardId).positionInGame=1;
            playableCPU.card8 = cards[array32[7]]; cardIdToObject(playableCPU.card8.cardId).positionOnHand=8; cardIdToObject(playableCPU.card8.cardId).positionInGame=1;
            
            backhandCPU.card1 = cards[array32[8]]; cardIdToObject(backhandCPU.card1.cardId).positionOnHand=1; cardIdToObject(backhandCPU.card1.cardId).positionInGame=2;
            backhandCPU.card2 = cards[array32[9]]; cardIdToObject(backhandCPU.card2.cardId).positionOnHand=2; cardIdToObject(backhandCPU.card2.cardId).positionInGame=2;
            backhandCPU.card3 = cards[array32[10]]; cardIdToObject(backhandCPU.card3.cardId).positionOnHand=3; cardIdToObject(backhandCPU.card3.cardId).positionInGame=2;
            backhandCPU.card4 = cards[array32[11]]; cardIdToObject(backhandCPU.card4.cardId).positionOnHand=4; cardIdToObject(backhandCPU.card4.cardId).positionInGame=2;
            backhandCPU.card5 = cards[array32[12]]; cardIdToObject(backhandCPU.card5.cardId).positionOnHand=5; cardIdToObject(backhandCPU.card5.cardId).positionInGame=2;
            backhandCPU.card6 = cards[array32[13]]; cardIdToObject(backhandCPU.card6.cardId).positionOnHand=6; cardIdToObject(backhandCPU.card6.cardId).positionInGame=2;
            backhandCPU.card7 = cards[array32[14]]; cardIdToObject(backhandCPU.card7.cardId).positionOnHand=7; cardIdToObject(backhandCPU.card7.cardId).positionInGame=2;
            backhandCPU.card8 = cards[array32[15]]; cardIdToObject(backhandCPU.card8.cardId).positionOnHand=8; cardIdToObject(backhandCPU.card8.cardId).positionInGame=2;
            
            backhandPlayer.card1 = cards[array32[16]]; cardIdToObject(backhandPlayer.card1.cardId).positionOnHand=1; cardIdToObject(backhandPlayer.card1.cardId).positionInGame=3;
            backhandPlayer.card2 = cards[array32[17]]; cardIdToObject(backhandPlayer.card2.cardId).positionOnHand=2; cardIdToObject(backhandPlayer.card2.cardId).positionInGame=3;
            backhandPlayer.card3 = cards[array32[18]]; cardIdToObject(backhandPlayer.card3.cardId).positionOnHand=3; cardIdToObject(backhandPlayer.card3.cardId).positionInGame=3;
            backhandPlayer.card4 = cards[array32[19]]; cardIdToObject(backhandPlayer.card4.cardId).positionOnHand=4; cardIdToObject(backhandPlayer.card4.cardId).positionInGame=3;
            backhandPlayer.card5 = cards[array32[20]]; cardIdToObject(backhandPlayer.card5.cardId).positionOnHand=5; cardIdToObject(backhandPlayer.card5.cardId).positionInGame=3;
            backhandPlayer.card6 = cards[array32[21]]; cardIdToObject(backhandPlayer.card6.cardId).positionOnHand=6; cardIdToObject(backhandPlayer.card6.cardId).positionInGame=3;
            backhandPlayer.card7 = cards[array32[22]]; cardIdToObject(backhandPlayer.card7.cardId).positionOnHand=7; cardIdToObject(backhandPlayer.card7.cardId).positionInGame=3;
            backhandPlayer.card8 = cards[array32[23]]; cardIdToObject(backhandPlayer.card8.cardId).positionOnHand=8; cardIdToObject(backhandPlayer.card8.cardId).positionInGame=3;
            
            playable.card1 = cards[array32[24]]; cardIdToObject(playable.card1.cardId).positionOnHand=1; cardIdToObject(playable.card1.cardId).positionInGame=4;
            playable.card2 = cards[array32[25]]; cardIdToObject(playable.card2.cardId).positionOnHand=2; cardIdToObject(playable.card2.cardId).positionInGame=4;
            playable.card3 = cards[array32[26]]; cardIdToObject(playable.card3.cardId).positionOnHand=3; cardIdToObject(playable.card3.cardId).positionInGame=4;
            playable.card4 = cards[array32[27]]; cardIdToObject(playable.card4.cardId).positionOnHand=4; cardIdToObject(playable.card4.cardId).positionInGame=4;
            playable.card5 = cards[array32[28]]; cardIdToObject(playable.card5.cardId).positionOnHand=5; cardIdToObject(playable.card5.cardId).positionInGame=4;
            playable.card6 = cards[array32[29]]; cardIdToObject(playable.card6.cardId).positionOnHand=6; cardIdToObject(playable.card6.cardId).positionInGame=4;
            playable.card7 = cards[array32[30]]; cardIdToObject(playable.card7.cardId).positionOnHand=7; cardIdToObject(playable.card7.cardId).positionInGame=4;
            playable.card8 = cards[array32[31]]; cardIdToObject(playable.card8.cardId).positionOnHand=8; cardIdToObject(playable.card8.cardId).positionInGame=4;
            
            // make cards visible/invisible and stuff
            // set the correct image paths to each card
            // 1st the visible backhand of the computer
            buttonCard1CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[8]].imagePath)));
            buttonCard2CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[9]].imagePath)));
            buttonCard3CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[10]].imagePath)));
            buttonCard4CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[11]].imagePath)));
            buttonCard5CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[12]].imagePath)));
            buttonCard6CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[13]].imagePath)));
            buttonCard7CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[14]].imagePath)));
            buttonCard8CPU.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[15]].imagePath)));
            // then the cards visible to the player
            buttonCard1Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[24]].imagePath)));
            buttonCard2Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[25]].imagePath)));
            buttonCard3Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[26]].imagePath)));
            buttonCard4Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[27]].imagePath)));
            buttonCard5Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[28]].imagePath)));
            buttonCard6Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[29]].imagePath)));
            buttonCard7Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[30]].imagePath)));
            buttonCard8Player.setIcon(new ImageIcon(this.getClass().getResource(cards[array32[31]].imagePath)));
            
            JDialog dealingJDialog = new JDialog();
            dealingJDialog.setBounds(550, 300, 50, 100); // x, y (measured from upper left), width, height
            dealingJDialog.setModal(true); // dialog has to be answered before anything else can happen
            // display the full array, the first and the last element
            dealingJDialog.add(new JLabel(stringDealing));
            dealingJDialog.setVisible(true); // actually show dialog window  
            
            // set all "faceup" cards visible
            // by doing so create the illusion of dealing the cards by waiting 0.1sec between dealing each card
            buttonCard1CPU.setVisible(true);
            buttonCard2CPU.setVisible(true);
            buttonCard3CPU.setVisible(true);
            buttonCard4CPU.setVisible(true);
            buttonCard5CPU.setVisible(true);
            buttonCard6CPU.setVisible(true);
            buttonCard7CPU.setVisible(true);
            buttonCard8CPU.setVisible(true);
            buttonCard1Player.setVisible(true);
            buttonCard2Player.setVisible(true);
            buttonCard3Player.setVisible(true);
            buttonCard4Player.setVisible(true);
            buttonCard5Player.setVisible(true);
            buttonCard6Player.setVisible(true);
            buttonCard7Player.setVisible(true);
            buttonCard8Player.setVisible(true);
            
            // set the facedown visible
            buttonFacedown1CPU.setVisible(true);
            buttonFacedown2CPU.setVisible(true);
            buttonFacedown3CPU.setVisible(true);
            buttonFacedown4CPU.setVisible(true);
            buttonFacedown5CPU.setVisible(true);
            buttonFacedown6CPU.setVisible(true);
            buttonFacedown7CPU.setVisible(true);
            buttonFacedown8CPU.setVisible(true);
            buttonFacedown1Player.setVisible(true);
            buttonFacedown2Player.setVisible(true);
            buttonFacedown3Player.setVisible(true);
            buttonFacedown4Player.setVisible(true);
            buttonFacedown5Player.setVisible(true);
            buttonFacedown6Player.setVisible(true);
            buttonFacedown7Player.setVisible(true);
            buttonFacedown8Player.setVisible(true);
            
            numberOfGames=numberOfGames+1; // if one resets the game, the other player starts choosing trump
            // This allows players who always want to choose trump (or never) to do so by skipping every 2nd game.
            
            if (numberOfGames==0) {
                // make a coin flip in order to decide who gets to choose the trump color (player or CPU)
                coinflip = AIEasyNormal.chooseRandomOption(2)-1; // a number either zero or unity
                trumpChooser=coinflip;
                JDialog coinflipDialog = new JDialog();
                coinflipDialog.setBounds(550, 300, 50, 100); // x, y (measured from upper left), width, height
                coinflipDialog.setModal(true);
                coinflipDialog.add(new JLabel(stringCoin));
                coinflipDialog.setVisible(true);
            }
            else { // this will yield: in 1st game a coinflip decides who chooses trump, after that the two opponents/player alternate in choosing trump
                trumpChooser=(coinflip+numberOfGames)%2; // % is the modulo function
            }
            
            if (trumpChooser == 0){
                int suit = JOptionPane.showOptionDialog(null, stringTrumpDialog, "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{stringClubs, stringSpades, stringHearts, stringDiamonds}, stringClubs);
                if (suit == -1) { // in case one accidently just closed the dialog window
                    suit=0; // will eventually also result in standard trump suit clubs (because one is still supposed to be able to play the game, even if one refused to choose a trump suit)
                }
                trumpcolor = suit +1; // the trump color is set here
                JDialog trumpDialog = new JDialog();
                trumpDialog.setBounds(550, 300, 325, 100); // x, y (measured from upper left), width, height
                trumpDialog.setModal(true);
                switch (trumpcolor) {
                    case 1: trumpDialog.add(new JLabel(stringClubsAffirm1));
                            break;
                    case 2: trumpDialog.add(new JLabel(stringSpadesAffirm1));
                            break;
                    case 3: trumpDialog.add(new JLabel(stringHeartsAffirm1));
                            break;
                    case 4: trumpDialog.add(new JLabel(stringDiamondsAffirm1));
                            break;
                }
                trumpDialog.setVisible(true);
                
                cpuReacts=false;
                lock=true; // lock the player for now from playing
                // trigger computer decision here!
                cpuDecision();
                
            }
            else if(trumpChooser == 1){
                
                // depending on the CPU-level the computer chooses the trump color in one of different ways
                switch (difficulty) {
                    case 1: // on lowest difficulty the computer chooses one color randomly as trump
                        trumpcolor = AIEasyNormal.chooseRandomOption(4); // the trump color is set here
                        break;
                    case 2: // on normal: count the colors of each card the CPU can play at beginning (almost same source code as for next difficulty level)
                            // Here the CPU will decide such that it will have at least 3 trump cards on the starting hand!
                        counter1=0;
                        if (clubA.positionInGame==1){counter1++;}
                        if (club10.positionInGame==1){counter1++;}
                        if (clubK.positionInGame==1){counter1++;}
                        if (clubQ.positionInGame==1){counter1++;}
                        if (club9.positionInGame==1){counter1++;}
                        if (club8.positionInGame==1){counter1++;}
                        if (club7.positionInGame==1){counter1++;}
                        if (counter1>2){ // in worst case scenario, CPU has 3 cards of each color
                            trumpcolor=1; // if it has at least 2 clubs, take it as trump for now
                        }
                        counter2=0;
                        if (spadeA.positionInGame==1){counter2++;}
                        if (spade10.positionInGame==1){counter2++;}
                        if (spadeK.positionInGame==1){counter2++;}
                        if (spadeQ.positionInGame==1){counter2++;}
                        if (spade9.positionInGame==1){counter2++;}
                        if (spade8.positionInGame==1){counter2++;}
                        if (spade7.positionInGame==1){counter2++;}
                        if (counter2>counter1){
                            trumpcolor=2;
                        }
                        counter3=0;
                        if (heartA.positionInGame==1){counter3++;}
                        if (heart10.positionInGame==1){counter3++;}
                        if (heartK.positionInGame==1){counter3++;}
                        if (heartQ.positionInGame==1){counter3++;}
                        if (heart9.positionInGame==1){counter3++;}
                        if (heart8.positionInGame==1){counter3++;}
                        if (heart7.positionInGame==1){counter3++;}
                        if (counter3>counter1 || counter3>counter2){
                            trumpcolor=3;
                        }
                        counter4=0;
                        if (diamA.positionInGame==1){counter4++;}
                        if (diam10.positionInGame==1){counter4++;}
                        if (diamK.positionInGame==1){counter4++;}
                        if (diamQ.positionInGame==1){counter4++;}
                        if (diam9.positionInGame==1){counter4++;}
                        if (diam8.positionInGame==1){counter4++;}
                        if (diam7.positionInGame==1){counter4++;}
                        if (counter4>counter1 || counter4>counter2 || counter4>counter3){
                            trumpcolor=4;
                        }
                        break;
                    case 3: // on highest difficulty the CPU is cheating,
                            // counting the number of cards of each color it possesses sooner or later
                            // and chooses the color that the computer has most cards of in total
                            // Here the CPU will decide such that it will have at least 4 trump cards in total during the game! (If you are unlucky, then it will have all eleven trump cards!)
                        trumpcolor=1; // take clubs as trump for now
                        counter1=0;
                        if (clubA.positionInGame==1 || clubA.positionInGame==2){counter1++;}
                        if (club10.positionInGame==1 || club10.positionInGame==2){counter1++;}
                        if (clubK.positionInGame==1 || clubK.positionInGame==2){counter1++;}
                        if (clubQ.positionInGame==1 || clubQ.positionInGame==2){counter1++;}
                        if (club9.positionInGame==1 || club9.positionInGame==2){counter1++;}
                        if (club8.positionInGame==1 || club8.positionInGame==2){counter1++;}
                        if (club7.positionInGame==1 || club7.positionInGame==2){counter1++;}
                        if (counter1<7){ // only look for a better color, if CPU does not have all clubs
                            counter2=0;
                            if (spadeA.positionInGame==1 || spadeA.positionInGame==2){counter2++;}
                            if (spade10.positionInGame==1 || spade10.positionInGame==2){counter2++;}
                            if (spadeK.positionInGame==1 || spadeK.positionInGame==2){counter2++;}
                            if (spadeQ.positionInGame==1 || spadeQ.positionInGame==2){counter2++;}
                            if (spade9.positionInGame==1 || spade9.positionInGame==2){counter2++;}
                            if (spade8.positionInGame==1 || spade8.positionInGame==2){counter2++;}
                            if (spade7.positionInGame==1 || spade7.positionInGame==2){counter2++;}
                            if (counter2>counter1){
                                trumpcolor=2;
                            }
                            if (counter2<7){ // only look for a better color, if CPU does not have all spades
                                counter3=0;
                                if (heartA.positionInGame==1 || heartA.positionInGame==2){counter3++;}
                                if (heart10.positionInGame==1 || heart10.positionInGame==2){counter3++;}
                                if (heartK.positionInGame==1 || heartK.positionInGame==2){counter3++;}
                                if (heartQ.positionInGame==1 || heartQ.positionInGame==2){counter3++;}
                                if (heart9.positionInGame==1 || heart9.positionInGame==2){counter3++;}
                                if (heart8.positionInGame==1 || heart8.positionInGame==2){counter3++;}
                                if (heart7.positionInGame==1 || heart7.positionInGame==2){counter3++;}
                                if (counter3>counter1 || counter3>counter2){
                                    trumpcolor=3;
                                }
                                if (counter3<7){ // only look for a better color, if CPU does not have all hearts
                                    counter4=0;
                                    if (diamA.positionInGame==1 || diamA.positionInGame==2){counter4++;}
                                    if (diam10.positionInGame==1 || diam10.positionInGame==2){counter4++;}
                                    if (diamK.positionInGame==1 || diamK.positionInGame==2){counter4++;}
                                    if (diamQ.positionInGame==1 || diamQ.positionInGame==2){counter4++;}
                                    if (diam9.positionInGame==1 || diam9.positionInGame==2){counter4++;}
                                    if (diam8.positionInGame==1 || diam8.positionInGame==2){counter4++;}
                                    if (diam7.positionInGame==1 || diam7.positionInGame==2){counter4++;}
                                    if (counter4>counter1 || counter4>counter2 || counter4>counter3){
                                        trumpcolor=4;
                                    }
                                }
                            }
                        }   
                        break;
                }
                
                JDialog trumpDialog = new JDialog();
                //meinJDialog.setTitle("You lose coinflip.");
                trumpDialog.setBounds(550, 300, 325, 100); // x, y (measured from upper left), width, height
                trumpDialog.setModal(true);
                switch (trumpcolor) {
                    case 1: trumpDialog.add(new JLabel(stringClubsAffirm2));
                            break;
                    case 2: trumpDialog.add(new JLabel(stringSpadesAffirm2));
                            break;
                    case 3: trumpDialog.add(new JLabel(stringHeartsAffirm2));
                            break;
                    case 4: trumpDialog.add(new JLabel(stringDiamondsAffirm2));
                            break;
                }
                trumpDialog.setVisible(true);
                
                cpuReacts=true;
                lock=false;
                // here the player would just click (and thus play) a card
            }
        }
        
        if(ae.getSource() == this.buttonChooseLanguage){
            // set the language of the whole game
            if (languageAsInt==1){
                window2 = new Languages("languages");
                window2.setVisible(true);
            }
            else if (languageAsInt==2) {
                window2 = new Languages("Sprachen");
                window2.setVisible(true);
            }
        }
        
        // button that diplays the rules of the game
        if(ae.getSource() == this.buttonRules){
            JScrollPane scrollPane = new JScrollPane();
            JDialog rulesDialog = new JDialog();
            rulesDialog.setTitle(stringRuleTitle);
            rulesDialog.setBounds(200, 20, 800, 700); // x, y (measured from upper left), width, height
            rulesDialog.getContentPane().setLayout(new BorderLayout(0, 0));
            rulesDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
            rulesDialog.setVisible(true);
            JLabel ruletext = new JLabel(stringRuleText);
            scrollPane.setViewportView(ruletext);
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

    // --- about future versions: ---
    // About the optics: One could paint more beautiful cards for the four jacks.
    // But that would also mean to increase the file size of the game.
    //
    
}


