package harlekinskat3;

/**
 * This class does not do anything.
 * It just has one method for tranlating every text in the game into German.
 * 
 */

import static harlekinskat3.Harlekinskat3.windowl;
import static harlekinskat3.Harlekinskat3.buttonRules;
import static harlekinskat3.Harlekinskat3.buttonStart;
import static harlekinskat3.Harlekinskat3.buttonDifficulty;
import static harlekinskat3.Harlekinskat3.buttonChooseLanguage;
import static harlekinskat3.Harlekinskat3.stringWinCards;
import static harlekinskat3.Harlekinskat3.stringLoseCards;
import static harlekinskat3.Harlekinskat3.stringPlayedJ1;
import static harlekinskat3.Harlekinskat3.stringPlayedJ2;
import static harlekinskat3.Harlekinskat3.stringPlayedJ3;
import static harlekinskat3.Harlekinskat3.stringPlayedJ4;
import static harlekinskat3.Harlekinskat3.stringPlayedTA;
import static harlekinskat3.Harlekinskat3.stringPlayedT10;
import static harlekinskat3.Harlekinskat3.stringPlayedTK;
import static harlekinskat3.Harlekinskat3.stringPlayedTQ;
import static harlekinskat3.Harlekinskat3.stringPlayedT9;
import static harlekinskat3.Harlekinskat3.stringPlayedT8;
import static harlekinskat3.Harlekinskat3.stringPlayedT7;
import static harlekinskat3.Harlekinskat3.stringPlayedCA;
import static harlekinskat3.Harlekinskat3.stringPlayedC10;
import static harlekinskat3.Harlekinskat3.stringPlayedCK;
import static harlekinskat3.Harlekinskat3.stringPlayedCQ;
import static harlekinskat3.Harlekinskat3.stringPlayedC9;
import static harlekinskat3.Harlekinskat3.stringPlayedC8;
import static harlekinskat3.Harlekinskat3.stringPlayedC7;
import static harlekinskat3.Harlekinskat3.stringPlayedSA;
import static harlekinskat3.Harlekinskat3.stringPlayedS10;
import static harlekinskat3.Harlekinskat3.stringPlayedSK;
import static harlekinskat3.Harlekinskat3.stringPlayedSQ;
import static harlekinskat3.Harlekinskat3.stringPlayedS9;
import static harlekinskat3.Harlekinskat3.stringPlayedS8;
import static harlekinskat3.Harlekinskat3.stringPlayedS7;
import static harlekinskat3.Harlekinskat3.stringPlayedHA;
import static harlekinskat3.Harlekinskat3.stringPlayedH10;
import static harlekinskat3.Harlekinskat3.stringPlayedHK;
import static harlekinskat3.Harlekinskat3.stringPlayedHQ;
import static harlekinskat3.Harlekinskat3.stringPlayedH9;
import static harlekinskat3.Harlekinskat3.stringPlayedH8;
import static harlekinskat3.Harlekinskat3.stringPlayedH7;
import static harlekinskat3.Harlekinskat3.stringPlayedDA;
import static harlekinskat3.Harlekinskat3.stringPlayedD10;
import static harlekinskat3.Harlekinskat3.stringPlayedDK;
import static harlekinskat3.Harlekinskat3.stringPlayedDQ;
import static harlekinskat3.Harlekinskat3.stringPlayedD9;
import static harlekinskat3.Harlekinskat3.stringPlayedD8;
import static harlekinskat3.Harlekinskat3.stringPlayedD7;
import static harlekinskat3.Harlekinskat3.stringJ1Text;
import static harlekinskat3.Harlekinskat3.stringJ2Text;
import static harlekinskat3.Harlekinskat3.stringJ3Text;
import static harlekinskat3.Harlekinskat3.stringJ4Text;
import static harlekinskat3.Harlekinskat3.stringCAText;
import static harlekinskat3.Harlekinskat3.stringC10Text;
import static harlekinskat3.Harlekinskat3.stringCKText;
import static harlekinskat3.Harlekinskat3.stringCQText;
import static harlekinskat3.Harlekinskat3.stringC9Text;
import static harlekinskat3.Harlekinskat3.stringC8Text;
import static harlekinskat3.Harlekinskat3.stringC7Text;
import static harlekinskat3.Harlekinskat3.stringSAText;
import static harlekinskat3.Harlekinskat3.stringS10Text;
import static harlekinskat3.Harlekinskat3.stringSKText;
import static harlekinskat3.Harlekinskat3.stringSQText;
import static harlekinskat3.Harlekinskat3.stringS9Text;
import static harlekinskat3.Harlekinskat3.stringS8Text;
import static harlekinskat3.Harlekinskat3.stringS7Text;
import static harlekinskat3.Harlekinskat3.stringHAText;
import static harlekinskat3.Harlekinskat3.stringH10Text;
import static harlekinskat3.Harlekinskat3.stringHKText;
import static harlekinskat3.Harlekinskat3.stringHQText;
import static harlekinskat3.Harlekinskat3.stringH9Text;
import static harlekinskat3.Harlekinskat3.stringH8Text;
import static harlekinskat3.Harlekinskat3.stringH7Text;
import static harlekinskat3.Harlekinskat3.stringDAText;
import static harlekinskat3.Harlekinskat3.stringD10Text;
import static harlekinskat3.Harlekinskat3.stringDKText;
import static harlekinskat3.Harlekinskat3.stringDQText;
import static harlekinskat3.Harlekinskat3.stringD9Text;
import static harlekinskat3.Harlekinskat3.stringD8Text;
import static harlekinskat3.Harlekinskat3.stringD7Text;
import static harlekinskat3.Harlekinskat3.stringGameOver;
import static harlekinskat3.Harlekinskat3.stringDraw;
import static harlekinskat3.Harlekinskat3.stringWin;
import static harlekinskat3.Harlekinskat3.stringLose;
import static harlekinskat3.Harlekinskat3.stringPointCounting;
import static harlekinskat3.Harlekinskat3.stringRestartMessage;
import static harlekinskat3.Harlekinskat3.stringDifficultyDialog1;
import static harlekinskat3.Harlekinskat3.stringDifficultyDialog2;
import static harlekinskat3.Harlekinskat3.stringEasy;
import static harlekinskat3.Harlekinskat3.stringNormal;
import static harlekinskat3.Harlekinskat3.stringHard;
import static harlekinskat3.Harlekinskat3.stringEasyAffirm;
import static harlekinskat3.Harlekinskat3.stringNormalAffirm;
import static harlekinskat3.Harlekinskat3.stringHardAffirm;
import static harlekinskat3.Harlekinskat3.stringDealing;
import static harlekinskat3.Harlekinskat3.stringCoin;
import static harlekinskat3.Harlekinskat3.stringTrumpDialog;
import static harlekinskat3.Harlekinskat3.stringClubs;
import static harlekinskat3.Harlekinskat3.stringSpades;
import static harlekinskat3.Harlekinskat3.stringHearts;
import static harlekinskat3.Harlekinskat3.stringDiamonds;
import static harlekinskat3.Harlekinskat3.stringClubsAffirm1;
import static harlekinskat3.Harlekinskat3.stringSpadesAffirm1;
import static harlekinskat3.Harlekinskat3.stringHeartsAffirm1;
import static harlekinskat3.Harlekinskat3.stringDiamondsAffirm1;
import static harlekinskat3.Harlekinskat3.stringClubsAffirm2;
import static harlekinskat3.Harlekinskat3.stringSpadesAffirm2;
import static harlekinskat3.Harlekinskat3.stringHeartsAffirm2;
import static harlekinskat3.Harlekinskat3.stringDiamondsAffirm2;
import static harlekinskat3.Harlekinskat3.stringRuleTitle;
import static harlekinskat3.Harlekinskat3.stringRuleText;

public class German {
    
    public static void setLanguage ()
    {
        
        // translate the titles for graphic elements
        windowl.setTitle("Harlekinskat");
        buttonRules.setText("Regeln");
        buttonStart.setText("Start/Neustart");
        buttonDifficulty.setText("<html><body>Schwierigkeitsgrad w&auml;hlen</body></html>");
        buttonChooseLanguage.setText("<html><body>Sprache w&auml;hlen</body></html>");
    
        // translate the strings
        stringWinCards="<html><body><blockquote>Beide Karten gewonnen.</blockquote></body></html>";
        stringLoseCards="<html><body><blockquote>Computer gewinnt beide Karten.</blockquote></body></html>";
    
        stringPlayedJ1="<html><body><blockquote>H&ouml;chster Bube gespielt.</blockquote></body></html>";
        stringPlayedJ2="<html><body><blockquote>Zweith&ouml;chster Bube gespielt.</blockquote></body></html>";
        stringPlayedJ3="<html><body><blockquote>Dritth&ouml;chster Bube gespielt.</blockquote></body></html>";
        stringPlayedJ4="<html><body><blockquote>Niedrigster Bube gespielt.</blockquote></body></html>";
    
        stringPlayedTA="<html><body><blockquote>Trumpf-Ass gespielt.</blockquote></body></html>";
        stringPlayedT10="<html><body><blockquote>Trumpf 10 gespielt.</blockquote></body></html>";
        stringPlayedTK="<html><body><blockquote>Trumpf-K&ouml;nig gespielt.</blockquote></body></html>";
        stringPlayedTQ="<html><body><blockquote>Trumpf-Dame gespielt.</blockquote></body></html>";
        stringPlayedT9="<html><body><blockquote>Trumpf 9 gespielt.</blockquote></body></html>";
        stringPlayedT8="<html><body><blockquote>Trumpf 8 gespielt.</blockquote></body></html>";
        stringPlayedT7="<html><body><blockquote>Trumpf 7 gespielt.</blockquote></body></html>";
    
        stringPlayedCA="<html><body><blockquote>Kreuzass gespielt.</blockquote></body></html>";
        stringPlayedC10="<html><body><blockquote>Kreuz 10 gespielt.</blockquote></body></html>";
        stringPlayedCK="<html><body><blockquote>Kreuzk&ouml;nig gespielt.</blockquote></body></html>";
        stringPlayedCQ="<html><body><blockquote>Kreuzdame gespielt.</blockquote></body></html>";
        stringPlayedC9="<html><body><blockquote>Kreuz 9 gespielt.</blockquote></body></html>";
        stringPlayedC8="<html><body><blockquote>Kreuz 8 gespielt.</blockquote></body></html>";
        stringPlayedC7="<html><body><blockquote>Kreuz 7 gespielt.</blockquote></body></html>";
    
        stringPlayedSA="<html><body><blockquote>Pikass gespielt.</blockquote></body></html>";
        stringPlayedS10="<html><body><blockquote>Pik 10 gespielt.</blockquote></body></html>";
        stringPlayedSK="<html><body><blockquote>Pikk&ouml;nig gespielt.</blockquote></body></html>";
        stringPlayedSQ="<html><body><blockquote>Pikdame gespielt.</blockquote></body></html>";
        stringPlayedS9="<html><body><blockquote>Pik 9 gespielt.</blockquote></body></html>";
        stringPlayedS8="<html><body><blockquote>Pik 8 gespielt.</blockquote></body></html>";
        stringPlayedS7="<html><body><blockquote>Pik 7 gespielt.</blockquote></body></html>";
    
        stringPlayedHA="<html><body><blockquote>Herzass gespielt.</blockquote></body></html>";
        stringPlayedH10="<html><body><blockquote>Herz 10 gespielt.</blockquote></body></html>";
        stringPlayedHK="<html><body><blockquote>Herzk&ouml;nig gespielt.</blockquote></body></html>";
        stringPlayedHQ="<html><body><blockquote>Herzdame gespielt.</blockquote></body></html>";
        stringPlayedH9="<html><body><blockquote>Herz 9 gespielt.</blockquote></body></html>";
        stringPlayedH8="<html><body><blockquote>Herz 8 gespielt.</blockquote></body></html>";
        stringPlayedH7="<html><body><blockquote>Herz 7 gespielt.</blockquote></body></html>";
    
        stringPlayedDA="<html><body><blockquote>Karoass gespielt.</blockquote></body></html>";
        stringPlayedD10="<html><body><blockquote>Karo 10 gespielt.</blockquote></body></html>";
        stringPlayedDK="<html><body><blockquote>Karok&ouml;nig gespielt.</blockquote></body></html>";
        stringPlayedDQ="<html><body><blockquote>Karodame gespielt.</blockquote></body></html>";
        stringPlayedD9="<html><body><blockquote>Karo 9 gespielt.</blockquote></body></html>";
        stringPlayedD8="<html><body><blockquote>Karo 8 gespielt.</blockquote></body></html>";
        stringPlayedD7="<html><body><blockquote>Karo 7 gespielt.</blockquote></body></html>";
    
        stringJ1Text="Kreuzbube (2)";
        stringJ2Text="Pikbube (2)";
        stringJ3Text="Herzbube (2)";
        stringJ4Text="Karobube (2)";
        stringCAText="Kreuzass (11)";
        stringC10Text="Kreuz 10 (10)";
        stringCKText="Kreuzk&ouml;nig (4)";
        stringCQText="Kreuzdame (3)";
        stringC9Text="Kreuz 9 (0)";
        stringC8Text="Kreuz 8 (0)";
        stringC7Text="Kreuz 7 (0)";
        stringSAText="Pikass (11)";
        stringS10Text="Pik 10 (10)";
        stringSKText="Pikk&ouml;nig (4)";
        stringSQText="Pikdame (3)";
        stringS9Text="Pik 9 (0)";
        stringS8Text="Pik 8 (0)";
        stringS7Text="Pik 7 (0)";
        stringHAText="Herzass (11)";
        stringH10Text="Herz 10 (10)";
        stringHKText="Herzk&ouml;nig (4)";
        stringHQText="Herzdame (3)";
        stringH9Text="Herz 9 (0)";
        stringH8Text="Herz 8 (0)";
        stringH7Text="Herz 7 (0)";
        stringDAText="Karoass (11)";
        stringD10Text="Karo 10 (10)";
        stringDKText="Karok&ouml;nig (4)";
        stringDQText="Karodame (3)";
        stringD9Text="Karo 9 (0)";
        stringD8Text="Karo 8 (0)";
        stringD7Text="Karo 7 (0)";
        
        stringGameOver="Spiel zu Ende";
        stringDraw="<br> 60 Punkte. <br><br> Unentschieden!<br><br>";
        stringWin=" Punkte <br><br>Sieg!<br><br>";
        stringLose=" Punkte. <br><br> Niederlage!<br><br>";
        stringPointCounting="<html><body><blockquote> z&auml;hle gewonnene Punkte:<br><br>";
        stringRestartMessage="Start/Neustart-Taste dr&uuml;cken f&uuml;r Neues Spiel.";
    
        stringDifficultyDialog1="<html><body>Schwierigkeitsgrad w&auml;hlen</body></html>";
        stringDifficultyDialog2="Stufe einstellen";
        stringEasy="<html><body>leicht/zuf&auml;llig</body></html>";
        stringNormal="normal";
        stringHard="schwer/schummelnd";
        stringEasyAffirm="<html><body><blockquote>Schwierigkeit ist einfach.</blockquote></body></html>";
        stringNormalAffirm="<html><body><blockquote>Schwierigkeit ist normal.</blockquote></body></html>";
        stringHardAffirm="<html><body><blockquote>Schwierigkeit ist schwer.</blockquote></body></html>";
    
        stringDealing="<html><body><blockquote>teile aus</blockquote></body></html>";
        stringCoin="<html><body><blockquote>M&uuml;nzwurf</blockquote></body></html>";
    
        stringTrumpDialog="<html><body>W&auml;hlen Sie eine Trumpffarbe.</body></html>";
        stringClubs="<html><body>Kreuz/schwarz &clubs;</body></html>";
        stringSpades="<html><body>Pik/gr&uuml;n &spades;</body></html>";
        stringHearts="<html><body>Herz/rot &hearts;</body></html>";
        stringDiamonds="<html><body>Karo/gelb &diams;</body></html>";
    
        stringClubsAffirm1="<html><body><blockquote>Kreuz (schwarz &clubs;) ist Trumpf. Computer beginnt.</blockquote></body></html>";
        stringSpadesAffirm1="<html><body><blockquote>Pik (gr&uuml;n &spades;) ist Trumpf. Computer beginnt.</blockquote></body></html>";
        stringHeartsAffirm1="<html><body><blockquote>Herz (rot &hearts;) ist Trumpf. Computer beginnt.</blockquote></body></html>";
        stringDiamondsAffirm1="<html><body><blockquote>Karo (gelb &diams;) ist Trumpf. Computer beginnt.</blockquote></body></html>";
        stringClubsAffirm2="<html><body><blockquote>Computer w&auml;hlt Kreuz (schwarz &clubs;) als Trumpf. Ihr Zug.</blockquote></body></html>";
        stringSpadesAffirm2="<html><body><blockquote>Computer w&auml;hlt Pik (gr&uuml;n &spades;) als Trumpf. Ihr Zug.</blockquote></body></html>";
        stringHeartsAffirm2="<html><body><blockquote>Computer w&auml;hlt Herz (rot &hearts;) als Trumpf. Ihr Zug.</blockquote></body></html>";
        stringDiamondsAffirm2="<html><body><blockquote>Computer w&auml;hlt Karo (gelb &diams;) als Trumpf. Ihr Zug.</blockquote></body></html>";
    
        stringRuleTitle="Spielregeln";
        stringRuleText="<html><body><blockquote>Harlekinskat ist ein einfaches Kartenspiel für zwei Spieler, das ein Standard-Kartenspiel mit 32 Karten verwendet. Im Spiel geht es darum, Karten zu spielen und dann taktisch zu antworten, indem entweder eine höhere Karte gespielt wird und beide Karten und deren Wert für sich beansprucht werden oder indem eine niedrigere Karte gespielt wird und der Gegner beide Karten erh&auml;lt. Wenn alle 32 Karten gespielt wurden, gewinnt der Spieler, der mehr Kartenpunkte erhalten hat.<br><br>"
                + "&uuml;ber das Austeilen der Karten:<br><br>"
                + "Das Spiel ist so aufgebaut, dass jeder Spieler nach dem Mischen aller 32 Karten 8 Karten in der Hand h&auml;lt, so dass man sie nur selbst sehen kann. Außerdem hat jeder Spieler 8 weitere Karten hinter den anderen. Sie werden so gedreht, dass nur der Gegner diese Karten sehen kann.<br>"
                + "Das bedeutet, dass jeder Spieler die H&auml;lfte der eigenen Karten sowie die H&auml;lfte der Karten des Gegners sehen kann. Wenn man sich die 8 Karten auf der Hand des Gegners merkt, die man am Anfang sieht, wei&szlig; man, welche Karten der Gegner im Endspiel haben wird.<br><br>"
                + "&uuml;ber die Wahl der Trumpffarbe:<br><br>"
                + "Eine der 4 Farben (Kreuz/schwarz &clubs;, Pik/ gr&uuml;n &spades;, Herz/rot &hearts;, and Karo/gelb &diams;) wird als Trumpffarbe gew&auml;hlt. Die Trumpfkarten gelten als h&ouml;here Karten als die anderen.<br>"
                + "Es gibt verschiedene Variationen bei der Wahl dieser Trumpffarbe. Bei dieser Variante entscheidet im ersten Spiel ein M&uuml;nzwurf zufällig, wer die Trumpffarbe entscheidet (und damit auch indirekt, wer anf&auml;ngt zu spielen). In den folgenden Spielen wechseln sich die beiden Spieler dabei ab Trumpf zu w&auml;hlen.<br>"
                + "Derjenige, der die Trumpffarbe entscheidet, tut dies, nachdem alle 32 Karten ausgeteilt wurden. Die Idee ist, eine Farbe zu w&auml;hlen, von der man viele Karten hat. Man trifft diese Entscheidung jedoch nur, wenn man nur die H&auml;lfte der Karten im Spiel sieht. Der Spieler, der die Trumpffarbe nicht gew&auml;hlt hat, darf die erste Karte spielen.<br>"
                + "(Wenn man immer die Trumpffarbe festlegen m&ouml;chte oder immer mit dem Spielen anfangen m&ouml;chte, kann man jedes zweite Spiel &uuml;berspringen, indem man nach jedem Spiel die Neustart-Taste drückt.)<br><br>"
                + "&uuml;ber die H&ouml;he der Karten:<br><br>"
                + "Im Allgemeinen gewinnen h&ouml;here Karten gegenüber niedrigeren Karten. Die vier h&ouml;chsten Karten im Spiel sind die vier Buben (J). Sie gelten IMMER als Trumpfkarten! Das bedeutet auch, dass sie nicht zu der tats&auml;chlich aufgedruckten Farbe geh&ouml;ren. Ein typischer Anf&auml;ngerfehler besteht darin, diese Tatsache zu vergessen.<br>"
                + "Die h&ouml;chste Karte im Spiel ist immer der Kreuzbube. Die zweith&ouml;chste Karte ist der Pikbube. Nach ihnen kommen der Herzbube und der Karobube.<br>"
                + "Die n&auml;chsth&ouml;heren Karten nach den Buben sind die verbleibenden Trumpfkarten in der folgenden Reihenfolge: Die h&ouml;chste Karte nach den Buben ist das Trumpf-Ass. Es folgt die Trumpf-10. Dann kommen K&ouml;nig (K), Dame (Q), 9, 8 und 7. Damit ist die Trumpf 7 die niedrigste Trumpfkarte. Sie ist aber immer noch h&ouml;her als jede andere Karte.<br>"
                + "Die restlichen 3 Farben werden als gleich hoch betrachtet. Für jede Farbe ist die Reihenfolge der Karten dieselbe wie für die Trumpfkarten. Das hei&szlig;t, die h&ouml;chste Nicht-Trumpkarte ist das Ass. Es folgen 10, K&ouml;nig, Dame, 9, 8 und 7.<br><br>"
                + "wie die Karten gespielt werden und wer die Karten gewinnt:<br><br>"
                + "Der Spieler, der die Trumpffarbe nicht gew&auml;hlt hat, darf die erste Karte spielen. Der Gegner muss mit einer anderen Karte antworten. Jeder Spieler kann nur die Karten spielen, die er selbst auf der Hand hat und die er sehen kann. Nachdem eine Karte gespielt wurde, wird die Karte dahinter (falls vorhanden) umgedreht und wird zu einer spielbaren Karte f&uuml;r die n&auml;chsten Spielz&uuml;ge.<br>"
                + "Nachdem beide Karten in einer Runde gespielt wurden, wird nach den Regeln (unten in diesem Abschnitt) entschieden, wer beide Karten gewinnt und aus dem Spiel nimmt. Diese beiden Karten tragen zur Anzahl der Punkte bei, die der Spieler w&auml;hrend des Spiels gewonnen hat. Der Spieler, der die beiden Karten gewonnen hat, darf die n&auml;chste Karte spielen. Damit startet eine neue Runde.<br>"
                + "(Wenn man mit echten Karten spielt, sollte man nicht vergessen, Karten umzudrehen. Hier erfolgt dies automatisch.)<br>"
                + "In jeder Runde gelten die folgenden Regeln:<br>"
                + "Regel Nr. 1: Jeder Spieler kann nur die Karten spielen, die er selbst auf der Hand hat und die er sehen kann.<br>"
                + "Regel Nr. 2: Der Spieler, der in der Runde anf&auml;ngt eine Karte zu spielen, darf eine beliebige dieser Karten spielen.<br>"
                + "Regel Nr. 3: Der antwortende Spieler muss, wenn m&ouml;glich, eine Karte derselben Farbe spielen. Die Buben geh&ouml;ren immer zur Trumpffarbe. Es gibt 4 Farben: Die Buben und die anderen Trumpfkarten bilden eine Farbe sowie die restlichen 3 Farben.<br>"
                + "Regel Nr. 4: Wenn man mit einer Karte derselben Farbe antwortet, dann gewinnt der antwortende Spieler beide gespielten Karten, indem man eine h&ouml;here Karte gespielt hat, beziehungsweise verliert beide Karten an den Gegner, wenn man eine niedrigere Karte gespielt hat. (siehe vorherigen Abschnitt)<br>"
                + "Regel Nr. 5: Wenn man keine Karte derselben Farbe spielen kann (weil man in diesem Zug keine davon hat), antwortet man, indem man eine beliebige Karte spielt (gem&auml;&szlig; \"Regel Nr. 1\").<br>"
                + "Regel Nr. 6: Im Fall von \"Regel Nr. 5\": Wenn die gespielte Karte eine Trumpfkarte war, wird der antwortende Spieler beide Karten verlieren.<br>"
                + "Regel Nr. 7: Im Fall von \"Regel Nr. 5\": Wenn die gespielte Karte keine Trumpfkarte war, kann der antwortende Spieler entweder beide gespielten Karten durch Ausspielen einer Trumpfkarte gewinnen oder beide gespielten Karten durch Ausspielen einer Nicht-Trumpfkarte verlieren.<br>"
                + "(In diesem Spiel kann man nicht schummeln. Wenn man versucht, eine Karte zu spielen, die in einer Situation nicht erlaubt ist, passiert nichts.)<br><br>"
                + "&uuml;ber den Wert der Karten / Endspiel:<br><br>"
                + "Nach 16 Runden sind alle Karten ausgespielt und kein Spieler hat noch Karten auf der Hand. Dann schauen sich beide Spieler die gewonnenen Karten an und z&auml;hlen die gesammelten Punkte nach folgenden Regeln zusammen:<br>"
                + "Jedes Ass z&auml;hlt 11 Punkte. Jede 10 ist 10 Punkte wert.<br>"
                + "Jeder K&ouml;nig, jede Dame und jeder Bube sind jeweils 4, 3 und 2 Punkte wert.<br>"
                + "Die restlichen Karten (9, 8, 7) werden ignoriert. Sie sind nichts wert.<br>"
                + "Es gibt insgesamt 120 Punkte in diesem Spiel. Das hei&szlig;t, wenn ein Spieler 60 Punkte hat, ist es ein Unentschieden.<br>"
                + "Hat man mehr als 60 Punkte, hat man das Spiel gewonnen. Hat man weniger als 60 Punkte, hat man das Spiel verloren.<br>"
                + "(Das hei&szlig;t, man kann zum Beispiel gewinnen, indem man nur 6 der wertvollsten Karten erh&auml;lt: 6 Karten, die entweder Asse oder Zehnen sind, sind bereits 62 bis 64 Punkte.)<br><br><br>"
                + "&uuml;ber den Schwierigkeitsgrad des Computergegners:<br><br>"
                + "Es gibt drei Schwierigkeitsgrade für den Computer: leicht/zuf&auml;llig, normal und schwer/schummelnd. Der normale Schwierigkeitsgrad ist Standard.<br>"
                + "Beim niedrigsten Schwierigkeitsgrad wird der Computer gem&auml;&szlig; den Regeln spielen, ansonsten werden jedoch zuf&auml;llige Karten gespielt.<br>"
                + "Auf normal wird der Computer fair spielen und sich erinnern, wenn Sie keine bestimmte Farbe hatten und Sie danach eine 10 dieser Farbe umgedreht haben. Dann wird er versuchen, diese 10 mit dem Ass derselben Farbe mitzunehmen. (Das ist der beste Zug im Spiel.) Au&szlig;erdem spielt der Computer im Zweifelsfall eine niedrige Karte, die nicht Trumpf ist.<br>"
                + "Auf schwer spielt der Computer nach den Regeln, au&szlig;er dass er die Karten kennt, die man auf der eigenen Hand sehen kann. Unter Ausnutzung dieses Wissen versucht der Computer dann eine Karte zu spielen, die man mit einer niedrigeren Karte bedienen muss, um auf diese Weise m&ouml;glichst viele Punkte zu machen.<br>"
                + "Wenn der Computer Trumpf w&auml;hlt, passiert Folgendes: Auf dem niedrigsten Schwierigkeitsgrad w&auml;hlt er zuf&auml;llig eine Farbe.<br>"
                + "Auf normal w&auml;hlt er die Farbe, die er am meisten auf der Starthand hat.<br>" //  In diesem Fall hat er mindestens 2 Trumpfkarten auf der Starthand.
                + "Auf dem h&ouml;chsten Schwierigkeitsgrad schummelt der Computer, indem er wei&szlig;, welche Kartenfarbe er am meisten im gesamten Spiel haben wird.<br><br>" // In diesem Fall hat der Computer insgesamt mindestens 5 Trumpfkarten.
                + "&uuml;ber die Tasten:<br><br>"
                + "Mit der \"Start/Neustart\"-Taste kann ein neues Spiel gestartet werden. Man kann auch aufgeben und ein neues Spiel spielen. Dann werden die Karten neu gemischt.<br>"
                + "Mit der \"Schwierigkeitsgrad w&auml;hlen\"-Taste kann der Schwierigkeitsgrad des Computers eingestellt werden. Die normale Stufe ist Standard.<br>"
                + "Man kann eine Karte spielen, indem man darauf klickt. (Dies funktioniert nat&uuml;rlich nur bei Karten, die zu einem bestimmten Zeitpunkt gespielt werden d&uuml;rfen.) Zu jeder Zeit gibt es nicht mehr als 8 m&ouml;gliche Karten, aus denen eine Karte ausgew&auml;hlt werden kann. Sie befinden sich am unteren Ende des Fensters.<br>"
                + "Karten, die man nicht sehen kann, werden durch kleine verdeckte Karten dargestellt. Die gespielten Karten erscheinen in der Mitte des Fensters.<br>"
                + "Die Stapel bereits mitgenommener Karten werden rechts angezeigt und jeweils mit einer verdeckten Karte dargestellt.<br><br>"
                + "Genie&szlig;en Sie das Spiel!<br><br><br>"
                + "Dieses Spiel wurde programmiert von Patrick P&ouml;schke.<br><br>" 
                + "</blockquote></body></html>";
        
    }
    
}
