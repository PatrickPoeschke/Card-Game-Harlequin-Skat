package harlekinskat3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static harlekinskat3.Harlekinskat3.window2;
import static harlekinskat3.Harlekinskat3.languageAsInt;

/**
 * This is a class that allows one to choose a language from a popup window.
 * 
 */
public class Languages extends JFrame implements ActionListener {
    
    public JPanel panel2;
    public JButton buttonEnglish;
    public JButton buttonGerman;
    public String textContent;
        
    // constructor
    public Languages(String windowTitle) {
            
        this.setTitle(windowTitle);
        this.setSize(300, 225); // window for the whole game
        this.setLocation(500, 200);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Don't use this line,, or else the game might shutdown when changing the lnguage during the game!
        panel2 = new JPanel();
        panel2.setLayout(null);
        
        // create buttons for the languages
        
        buttonEnglish = new JButton(" English");
        buttonEnglish.setBounds(45, 25, 200, 50);
        // connect buttons to listeners
        buttonEnglish.addActionListener(this);
        panel2.add(buttonEnglish);
        
        buttonGerman = new JButton(" Deutsch");
        buttonGerman.setBounds(45, 100, 200, 50);
        // connect buttons to listeners
        buttonGerman.addActionListener(this);
        panel2.add(buttonGerman);
        
        this.add(panel2);
        
        }
    
    // from here on:
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

