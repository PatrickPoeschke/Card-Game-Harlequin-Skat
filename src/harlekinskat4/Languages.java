package harlekinskat4;

/**
 * This is a class that allows one to choose a language from a popup window.
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static harlekinskat4.Harlekinskat4.window2;
import static harlekinskat4.Harlekinskat4.languageAsInt;

public class Languages extends JFrame implements ActionListener, ComponentListener {
    
    public JPanel panel2;
    public static JButton buttonEnglish;
    public static JButton buttonGerman;
    public String textContent;
    
    public static int initialLanguagesWidth=300;
    public static int initialLanguagesHeight=225;
    public static int currentLanguagesWidth=300;
    public static int currentLanguagesHeight=225;
        
    // constructor
    public Languages(String windowTitle) {
            
        this.setTitle(windowTitle);
        this.setSize(initialLanguagesWidth, initialLanguagesHeight); // window for the whole game
        int posX = (int) Math.round((Harlekinskat4.currentFrameWidth - initialLanguagesWidth)/2);
        int posY = (int) Math.round((Harlekinskat4.currentFrameHeight - initialLanguagesHeight)/2);
        this.setLocation(posX, posY);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Don't use this line, or else the game might shutdown when changing the lnguage during the game!
        getContentPane().addComponentListener(this);
        panel2 = new JPanel();
        panel2.setLayout(null);
        
        // create buttons for the languages
        
        buttonEnglish = new JButton(" English");
        buttonEnglish.setBounds(45, 25, 200, 50);
        buttonEnglish.addActionListener(this);
        panel2.add(buttonEnglish);
        
        buttonGerman = new JButton(" Deutsch");
        buttonGerman.setBounds(45, 100, 200, 50);
        buttonGerman.addActionListener(this);
        panel2.add(buttonGerman);
        
        this.add(panel2);
        
    }
    
    public void componentHidden(ComponentEvent ce) {};
    public void componentShown(ComponentEvent ce) {};
    public void componentMoved(ComponentEvent ce) {};
    public void componentResized(ComponentEvent ce) {
        currentLanguagesWidth = this.getWidth();
        currentLanguagesHeight = this.getHeight();
        rescaleEverything();
    };
    
    // --- methods ---
    
    // calculates rescaling of horizontal coordinates (and widths)
    public static int rescaleX (int x) {
        int newValue = (int) Math.round(x*currentLanguagesWidth/initialLanguagesWidth);
        return newValue;
    }
    
    // calculates rescaling of vertical coordinates (and heights)
    public static int rescaleY (int y) {
        int newValue = (int) Math.round(y*currentLanguagesHeight/initialLanguagesHeight);
        return newValue;
    }
    
    // rescales a given button (uses current window size saved by global variables)
    public static void rescaleButton (JButton buttonName, int intialPosX, int intialPosY, int intialButtonWidth, int intialButtonHeight) {
        buttonName.setBounds(rescaleX(intialPosX), rescaleY(intialPosY), rescaleX(intialButtonWidth), rescaleY(intialButtonHeight));
    }
    
    // simply rescales all graphical components, i.e. all buttons and labels, one by one
    public static void rescaleEverything() {
        rescaleButton(buttonEnglish, 45, 25, 200, 50);
        rescaleButton(buttonGerman, 45, 100, 200, 50);
    }
    
    // centers and streches the choose language dialog at the beginning of the game, such that it looks good
    public static void centerAndStrechLanguagesWindow() {
        int width = (int) Math.round(Harlekinskat4.currentFrameWidth/4);
        int height = (int) Math.round(Harlekinskat4.currentFrameHeight/3);
        window2.setSize(width, height);
        int posX = (int) Math.round((Harlekinskat4.currentFrameWidth - initialLanguagesWidth)/2);
        int posY = (int) Math.round((Harlekinskat4.currentFrameHeight - initialLanguagesHeight)/2);
        window2.setLocation(posX, posY);
    }
    
    // --- reactions of the buttons ---
    
    public void actionPerformed (ActionEvent ae){
        
        if (ae.getSource() == this.buttonEnglish){
            English.setLanguage();
            languageAsInt=1;
            //window2.setVisible(false);
            window2.dispose();
        }
        else if (ae.getSource() == this.buttonGerman){
            German.setLanguage();
            languageAsInt=2;
            //window2.setVisible(false);
            window2.dispose();
        }
        
    }
    
}
