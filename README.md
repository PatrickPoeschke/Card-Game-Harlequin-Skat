# Card-Game-Harlequin-Skat
2D video game (in JAVA): rare variation of famous card game Skat using a 32 card deck

-- DOWNLOAD LINK --

If you want to play the game without compiling any files, you can download the game here:

https://patrick-poeschke.itch.io/harlequin-skat-4-harlekinskat-4

for older versions look here:

https://patrick-poeschke.itch.io/

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

NEW FEATURES OF VERSION 4.0:
* full screen mode (scalable to all screen resolutions)
* new look: most important cards, the jacks, stand out more
* improved AI
* option: let the computer tell its current strategies (and thus learn from it)

REQUIREMENTS:
* Java (if it does not work, update java)
* 1MB free disk space (basically almost nothing)

-- HELP IMPROVE THIS PROGRAM --

You can help improving this program. If you are a (native) speaker of a language the game does not support yet, you may translate the values of all string variables in the file scr/harlekinskat4/English.java (this includes the long text about the rules as well as some simple phrases and single words) and copy it as another file (e.g. French.java). I can do the rest myself, which would be: adding another button in the file Languages.java (and making the window a bit larger to make the button fit in) as well as adding code (very analog to what is already there) for the action listeners in the same file and at the end of the main class file Harlekinskat4.java.
Please note, that terms like "jack of clubs"are too difficult for the translating machines nowadays. Also, they are too inconsistent, when it comes to these words.

Adding an online versus mode would probably require adding a lot code as well as major rewriting of existing code.


-- QUICK VERSION INFO --

version 1.0 (24Sep2018):
* full game finished with all core features (rules, 3 cpu levels)

version 2.0 (26Dec2018):
* major rewriting of code for better readability,
* many bugs corrected

version 3.0 (9Jan2019):
* new feature: language chooser,
* full game also playable in German now

upload to GitHub (12Jan2019)

version 4.0 (25April2019)
* major rewriting of code for better readability,
* minor bugs in AI corrected
* improved AI: added strategy on hard difficulty
* new feature: scalable windows, including full screen option
* new feature: option for letting computer reveal its strategies

upload to GitHub (26April2019)
