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

            char[][] board = {{'1', '2', '3'},
                              {'4', '5', '6'},
                              {'7', '8', '9'}};

            printBoard(board);
            boolean isComputerOpponent = (gameMode == 2);

            while (true) {
                playerTurn(board, scanner, 'X');
                if (isGameFinished(board)) {
                    break;
                }
                printBoard(board);

                if (isComputerOpponent) {
                    computerTurn(board);
                } else {
                    playerTurn(board, scanner, 'O');
                }

                if (isGameFinished(board)) {
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
     * This function makes a random Computer move
     * @param board, 2D char matrix of game
     */
     public static void computerTurn(char[][] board) {
        Random random = new Random();
        int move;
        while (true) {
            move = random.nextInt(9) + 1;
            if (validateInput(move, board)) {
                System.out.println("Computer chooses spot " + move);
                placeMove(board, move, 'O');
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
     */
    public static void playerTurn(char[][] board, Scanner scanner, char symbol) {
        int userInput;
        while(true) {
            System.out.println("Which spot on the board do you select? (1-9)");

            // Validates and accounts for non-integer input.
            while(!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }

            userInput = scanner.nextInt();
    
            if(validateInput(userInput, board)) {
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
     * @return Who won or tied
     */
    public static boolean isGameFinished(char[][] board) {
        
        if(hasContestantWon(board, 'X')) {
            printBoard(board);
            System.out.println("Player One Wins!");
            System.out.println("Have a good day!");
            return true;
        }

        if(hasContestantWon(board, 'O')) {
            printBoard(board);
            System.out.println("Player Two Wins!");
            System.out.println("Have a good day!");
            return true;
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != 'X' && board[i][j] != 'O') { 
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
     * @return boolean, true or false depending on input
     */
    public static boolean validateInput(int userInput, char[][] board) {
        if (userInput < 1 || userInput > 9) {
            return false;
        }
        int[] coordinates = mapPositionToCoordinates(userInput);

        return board[coordinates[0]][coordinates[1]] != 'X' && board[coordinates[0]][coordinates[1]] != 'O';
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