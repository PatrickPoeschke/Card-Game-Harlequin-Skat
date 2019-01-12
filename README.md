# Card-Game-Harlequin-Skat
2D video game (in JAVA): rare variation of famous card game Skat using a 32 card deck

-- DESCRIPTION OF THE PROGRAM --
Skat is by far the most famous card game in Germany. It uses a 32 card deck, i.e. a poker deck without the cards lower than the 7s.
There are many variations. This variation, "Harlequin Skat" or "Harlekinskat", is my favorite one, because here the player has an advantage, if at the beginning of the game one remembers the cards that the opponent will have on the hand later in game.

The files in this repository are the source code for the game written in JAVA as well as the image files for representing the cards.

This game is a 2D single player offline game.

FEATURES:
* full support for the languages English and German
* displays its full rules within the game
* "French deck with German colors" for good visibilty of cards (spades are green, diamonds are yellow)
* 3 difficulty levels for CPU:
On standard difficulty the computer is practically indistinguishable from a real human player. + an easier + a harder difficulty

REQUIREMENTS:
* Java (if it does not work, update java)
min. resolution: 1200x700
max. resolution: There is no maximum screen resultion per se. However, if the resolution is far greater than the minimum, at some point one probably wants to decrease it, because the game elements might become too small. Unfortunately, the window does not scale.

-- HELP IMPROVE THIS PROGRAM --
You can help improving this program. If you are a (native) speaker of a language the game does not support yet, you may translate the values of all string variables in the file scr/harlekinskat3/English.java (this includes the long text about the rules as well as some simple phrases and single words) and copy it as another file (e.g. French.java). I can do the rest myself, which would be: adding another button in the file Languages.java (and making the window a bit larger to make the button fit in) as well as adding code (very analog to what is already there) for the action listeners in the same file and at the end of the main class file Harlekinskat3.java.
Please note, that term like "jack of clubs" is too difficult for the tranlating machines nowadays. Also they are too inconsistent when it comes to these words.

Making the main window of the game (and all its graphical elemts) scale with the screen resolution would probably require a lot of adding of code.
Similarly, adding an online versus mode would probably require adding a lot code as well as major rewriting of existing code.


-- QUICK VERSION INFO --

version 1.0 (24Sep2018)
full game finished will all core features (rules, 3 cpu levels)

version 2.0 (26Dec2018)
major rewriting of code for better readability
many bugs corrected

version 3.0 (9Jan2019)
new feature: language chooser
full game also playable in German now

upload to GitHub (12Jan2019)

