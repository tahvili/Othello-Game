# Othello (Reversi) Board Game :black_circle: :white_circle:
A strategy board game for two players, played on an 8×8 uncheckered board, built with Java and JavaFX.

<img src="https://raw.github.com/RohanPoojary1107/Othello-Game/master/Screenshots/othello-ui.png" alt="othello home page" height="400" width="400" title="Home Page"> <img src="https://raw.github.com/RohanPoojary1107/Othello-Game/master/Screenshots/othello-board-ui.png" alt="othello board" height="400" width="400" title="Game Board">

# Features:
- The Game had 2 modes: 
  - Human vs Human
  - Human vs Computer (with both Greedy and Random strategies)
- AI Hint: Highlighting the position on the board using either greedy, random or better (most strategic) strategy where the user can play the next move.
- Ability to go 1st or 2nd, restart the game and undo past moves.
- All available (valid) user moves are highlighted on the board
- Alert boxes to alert user when an invalid action is attempted.

# Requirements
- Oracle Java SE Development Kit 8u101: Download [here](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) (Note: It already has JavaFX bundled inside it.)
- An IDE: I have used Eclipse which can be downloaded [here](https://www.eclipse.org/downloads/)

# Getting Started
### 1. Clone
  - Clone this repo to your local machine using: https://github.com/RohanPoojary1107/Othello-Game.git

### 2. Open the folder in the IDE of your choice
### 3. Run
- Run class OthelloApplication.java found in Othello-Game/src/ca.utoronto.utm.othello.viewcontroller/

# Troubleshooting

### Is your $JAVA_HOME environment variable set properly?
- Windows: Follow the steps in this [tutorial](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html)
- Mac: Follow the steps mentioned [here](https://stackoverflow.com/questions/22842743/how-to-set-java-home-environment-variable-on-mac-os-x-10-9)
- Linux: Follow the steps mentioned [here](https://stackoverflow.com/questions/24641536/how-to-set-java-home-in-linux-for-all-users)

### Java.lang.UnsupportedClassVersionError
  - Follow the steps in this [tutorial](https://www.baeldung.com/java-lang-unsupportedclassversion)
