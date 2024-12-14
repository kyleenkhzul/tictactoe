package com.csc;

import java.util.Random;
import java.util.Scanner;
public class TicTacToe {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            System.out.println("Welcome to Tic-Tac-Toe!");
            int gameMode = mainMenu(scanner);

            char playerOneMark = getCustomMark(scanner, "Player One");
            char playerTwoMark = (gameMode == 2) ? getCustomMark(scanner, "Computer") : getCustomMark(scanner, "Player Two");
            char[][] board = {{'1', '2', '3'},
                              {'4', '5', '6'},
                              {'7', '8', '9'}};

            printBoard(board);
            boolean isComputerOpponent = (gameMode == 2);

            while (true) {
                playerTurn(board, scanner, playerOneMark, playerOneMark, playerTwoMark);
                if (isGameFinished(board, playerOneMark, playerTwoMark)) {
                    break;
                }
                printBoard(board);

                if (isComputerOpponent) {
                    computerTurn(board, playerTwoMark, playerOneMark, playerTwoMark);
                } else {
                    playerTurn(board, scanner, playerTwoMark, playerOneMark, playerTwoMark);
                }

                if (isGameFinished(board, playerOneMark, playerTwoMark)) {
                    break;
                }
                printBoard(board);
            }
            playAgain = exitMenu(scanner);
        } while (playAgain);

        System.out.println("Thanks for playing Tic-Tac-Toe! Goodbye!");
        scanner.close();
    }

    /*
     * This function prompts the main menu to select type of game mode
     * @param Scanner, scanner object to take input
     * @return int, game choice
     */
    public static int mainMenu(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Human vs Human");
            System.out.println("2. Human vs Computer");
            System.out.print("Enter choice (1 or 2): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 2) {
                return choice;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    /*
     * This function prompts the exit menu for when a game is finished
     * @param Scanner, scanner object to take input
     * @return boolean, to determine yes or no
     */
    public static boolean exitMenu(Scanner scanner) {
        String choice;
        while (true) {
            System.out.println("Would you like to play again? (yes or no): ");
            choice = scanner.next().toLowerCase();

            if (choice.equals("yes")) {
                return true;
            } else if (choice.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /*
     * This function gets the custom mark from the user
     * @param Scanner, scanner object to take input
     * @String playerName, name of player
     * @return string, custom player mark
     */
    public static char getCustomMark(Scanner scanner, String playerName) {
        String mark;
        while (true) {
            System.out.println(playerName + ", please choose a custom mark (one character only, no spaces): ");
            mark = scanner.nextLine().trim();

            if (mark.length() == 1 && !Character.isWhitespace(mark.charAt(0))) {
                return mark.charAt(0);
            } else {
                System.out.println("Invalid mark. The mark must be exactly one character and cannot be a whitespace. Please try again.");
            }
        }
    }

    /*
     * This function makes a random Computer move
     * @param board, 2D char matrix of game
     * @param playerOneMark, custom mark for player one
     * @param playerTwoMark, custom mark for player two
     */
    public static void computerTurn(char[][] board, char symbol, char playerOneMark, char playerTwoMark) {
        Random random = new Random();
        int move;
        while (true) {
            move = random.nextInt(9) + 1;
            if (validateInput(move, board, playerOneMark, playerTwoMark)) {
                System.out.println("Computer chooses spot " + move);
                placeMove(board, move, symbol);
                break;
            }
        }
    }

    /*
     * This function prints out the board
     * @param board, 2D char matrix of game
     */
    public static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-----");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-----");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

    /*
     * This function does a player's turn
     * @param board, 2D char matrix of game
     * @param scanner, user input
     * @param playerOneMark, custom mark for player one
     * @param playerTwoMark, custom mark for player two
     */
    public static void playerTurn(char[][] board, Scanner scanner, char symbol, char playerOneMark, char playerTwoMark) {
        int userInput;
        while(true) {
            System.out.println("Which spot on the board do you select? (1-9)");

            while(!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
                scanner.nextLine();
            }

            userInput = scanner.nextInt();
    
            if(validateInput(userInput, board, playerOneMark, playerTwoMark)) {
                break;
            }
            else {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        placeMove(board, userInput, symbol); 
    }

    /*
     * This function checks if anyone has won or tied.
     * @param board, 2D char matrix of game
     * @param playerOneMark, custom mark for player one
     * @param playerTwoMark, custom mark for player two
     * @return Who won or tied
     */
    public static boolean isGameFinished(char[][] board, char playerOneMark, char playerTwoMark) {
        if(hasContestantWon(board, playerOneMark)) {
            printBoard(board);
            System.out.println("Player One Wins!");
            System.out.println("Have a good day!");
            return true;
        }

        if(hasContestantWon(board, playerTwoMark)) {
            printBoard(board);
            System.out.println("Player Two Wins!");
            System.out.println("Have a good day!");
            return true;
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != playerOneMark && board[i][j] != playerTwoMark) { 
                    return false;
                }
            }
        }
        printBoard(board);
        System.out.println("Cat's game. Tie!");
        System.out.println("Have a good day!");
        return true;
    }

    /*
     * This function maps the position specified by the user to coordinates on the board.
     * @param position, int
     * @return row and column, int[]
     */
    public static int[] mapPositionToCoordinates(int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return new int[] {row, col};
    }
    
    /*
     * This function validates userInput
     * @param userInput, String
     * @param board, 2D char matrix of game
     * @param playerOneMark, custom mark for player one
     * @param playerTwoMark, custom mark for player two
     * @return boolean, true or false depending on input
     */
    public static boolean validateInput(int userInput, char[][] board, char playerOneMark, char playerTwoMark) {
        if (userInput < 1 || userInput > 9) {
            return false;
        }
        int[] coordinates = mapPositionToCoordinates(userInput);

        return board[coordinates[0]][coordinates[1]] != playerOneMark && board[coordinates[0]][coordinates[1]] != playerTwoMark;
    }

    /*
     * This function checks if someone has won by checking if either symbols form three across or diagonal
     * @param board, 2D char matrix of game
     * @param char, symbol to check
     * @return true or false, if three in a row of a symbol was found
     */
    public static boolean hasContestantWon(char[][] board, char symbol) {
		if ((board[0][0] == symbol && board [0][1] == symbol && board [0][2] == symbol) ||
			(board[1][0] == symbol && board [1][1] == symbol && board [1][2] == symbol) ||
			(board[2][0] == symbol && board [2][1] == symbol && board [2][2] == symbol) ||
			
			(board[0][0] == symbol && board [1][0] == symbol && board [2][0] == symbol) ||
			(board[0][1] == symbol && board [1][1] == symbol && board [2][1] == symbol) ||
			(board[0][2] == symbol && board [1][2] == symbol && board [2][2] == symbol) ||
			
			(board[0][0] == symbol && board [1][1] == symbol && board [2][2] == symbol) ||
			(board[0][2] == symbol && board [1][1] == symbol && board [2][0] == symbol) ) {
			return true;
		}
		return false;
	}

    /*
     * This function places the move of a symbol at a position
     * @param board, 2D char matrix of game
     * @param position, position to be placed
     * @param symbol, symbol to place at the position
     */
    public static void placeMove(char[][] board, int position, char symbol) {
        int[] coordinates = mapPositionToCoordinates(position);
        board[coordinates[0]][coordinates[1]] = symbol;
    }
}