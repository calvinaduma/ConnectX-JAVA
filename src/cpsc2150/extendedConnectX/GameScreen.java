package cpsc2150.extendedConnectX;

import java.util.*;
import java.lang.*;

public class GameScreen {

    public static void main(String[] args){

// variables to control game flow
    int MAX_ROW = 100;
    int MAX_COLUMN = 100;
    int MAX_WIN = 25;
    boolean loop, continueGame, newGame = true;
    List<Character> playerTokens = new ArrayList<Character>();
    char currentPlayer, playAgain, marker, gameType;
    int column, numberOfPlayers, playerCounter=0;
    String whiteSpace;
    Scanner input = new Scanner(System.in);
// start of new game
    while (newGame) {
        continueGame= true;
        System.out.println("How many players are playing: ");
        numberOfPlayers = input.nextInt();
        whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        while (numberOfPlayers < 2 || numberOfPlayers > 10){
            System.out.println("There has to be 2 - 10 players playing. Re-enter number: ");
            numberOfPlayers = input.nextInt();
            whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        }
// create player tokens
        for (int i=1; i<=numberOfPlayers; i++){
            System.out.format("Player %d, type your marker in: \n", i);
            marker = input.nextLine().charAt(0);
            marker = Character.toUpperCase(marker);
            while (playerTokens.contains(marker)){
                System.out.format("ERROR: %s is already a marker. Player %d type in another marker: \n", marker, i);
                marker = input.nextLine().charAt(0);
                marker = Character.toUpperCase(marker);
            }
            playerTokens.add(marker);
        }
// GameBoard design
        System.out.println("How many rows will the GameBoard have: ");
        int rowBoard = input.nextInt();
        whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        while (rowBoard > MAX_ROW || rowBoard <= 0){
            System.out.println("Error: 0 < rows <= 100! Re-enter number: ");
            rowBoard = input.nextInt();
            whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        }
        System.out.println("How many columns will the GameBoard have: ");
        int columnBoard = input.nextInt();
        whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        while (columnBoard > MAX_COLUMN || columnBoard <= 0){
            System.out.println("Error: 0 < columns <= 100! Re-enter number: ");
            columnBoard = input.nextInt();
            whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        }
        System.out.println("Number of tokens in a row to win: ");
        int tokenBoard = input.nextInt();
        whiteSpace = input.nextLine(); // gets rid of white space after reading an int
        while (tokenBoard > MAX_WIN || tokenBoard <= 0 || tokenBoard > rowBoard || tokenBoard > columnBoard){
            if (tokenBoard > columnBoard && tokenBoard < 25){
                System.out.format("Error: 0 < tokens <= %d! Re-enter number: \n", columnBoard);
                tokenBoard = input.nextInt();
                whiteSpace = input.nextLine(); // gets rid of white space after reading an int
            } else if (tokenBoard > rowBoard && tokenBoard < 25){
                System.out.format("Error: 0 < tokens <= %d! Re-enter number: \n", rowBoard);
                tokenBoard = input.nextInt();
                whiteSpace = input.nextLine(); // gets rid of white space after reading an int
            } else {
                System.out.print("Error: 0 < tokens <= 25! Re-enter number: \n");
                tokenBoard = input.nextInt();
                whiteSpace = input.nextLine(); // gets rid of white space after reading an int
            }
        }
// game type selection
        System.out.println("Choose a game mode! (F/f) Fast or (M/m) Memory efficient: ");
        gameType = input.nextLine().charAt(0);
        loop = true;
        while (loop){
            if (gameType == 'f' || gameType == 'F' || gameType == 'm' || gameType == 'M') {
                break;
            } else {
                System.out.println("Choose a game mode! (F/f) Fast or (M/m) Memory efficient: ");
                gameType = input.nextLine().charAt(0);
            }
        }
// creation of GameBoard
        IGameBoard game;
        if (gameType == 'F' || gameType == 'f') { game = new GameBoard(rowBoard,columnBoard,tokenBoard); }
        else { game = new GameBoardMem(rowBoard,columnBoard,tokenBoard); }

// start of player turns
        while (continueGame) {
            System.out.println(game.toString());
            currentPlayer = playerTokens.get(playerCounter);
            System.out.format("Player %s place marker in column: \n",currentPlayer);
            column = input.nextInt();
            whiteSpace = input.nextLine(); // gets rid of white space after reading an int
// checks player input
            while (column < 0 || column >= game.getNumColumns() || !game.checkIfFree(column)) {
                if (column < 0 || column >= game.getNumColumns()){
                    System.out.format("ERROR: You can only place your marker in columns 0 - %d! Enter new column: \n", game.getNumColumns());
                } else {
                    System.out.print("ERROR: That column is already full! Enter new column: \n");
                }
                column = input.nextInt();
                whiteSpace = input.nextLine(); // gets rid of white space after reading an int
            }
// passed check input
            game.placeToken(currentPlayer,column);
// checks for win
            if (game.checkForWin(column, currentPlayer)) {
                System.out.println(game.toString());
                System.out.format("Player %s won!\nWould you like to play again? Y/N: \n", currentPlayer);
                playAgain = input.nextLine().charAt(0);
                playerTokens.clear();
// checks if players want to play again
                loop = true;
                while (loop) {
                    if (playAgain == 'Y' || playAgain == 'N' || playAgain == 'n' || playAgain == 'y') {
                        break;
                    } else {
                        System.out.print("Would you like to play again? Y/N: \n");
                        playAgain = input.nextLine().charAt(0);
                    }
                }
// if play again, breaks out loop and starts at 'continueGame'
                if (playAgain == 'y' || playAgain == 'Y') {
                    break;
                } else {
                    newGame=false;
                    break;
                }
// if there is no win, increase player counter to set it to next player's turn
            } else if (game.checkTie()) {
                System.out.print(game.toString());
                System.out.print("It's a TIE!\nWould you like to play again? Y/N: \n");
                playAgain = input.nextLine().charAt(0);
                playerTokens.clear();
                loop = true;
                while (loop) {
                    if (playAgain == 'Y' || playAgain == 'N' || playAgain == 'n' || playAgain == 'y') {
                        break;
                    } else {
                        System.out.println("Would you like to play again? Y/N: ");
                        playAgain = input.nextLine().charAt(0);
                    }
                }
                if (playAgain == 'y' || playAgain == 'Y') {
                    break;
                } else {
                    newGame=false;
                    break;
                }
            } else {
                if (playerCounter == playerTokens.size()-1){
                    playerCounter = 0;
                } else {
                    playerCounter++;
                }
            }
        }
    }
}

