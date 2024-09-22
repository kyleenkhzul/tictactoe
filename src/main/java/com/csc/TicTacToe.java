package com.csc;

import java.util.Scanner;

public class TicTacToe {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = {{' ', ' ', ' '},
                          {' ', ' ', ' '},
                          {' ', ' ', ' '}};

        printBoard(board);
        
        while (true) {
			playerOneTurn(board, scanner);
			if (isGameFinished(board)){
				break;
			}
			printBoard(board);
			
			playerTwoTurn(board, scanner);
			if (isGameFinished(board)){
				break;
			}
			printBoard(board);
		}
		scanner.close();
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
     * This function validates userInput
     * @param userInput, String
     * @param board, 2D char matrix of game
     * @return boolean, true or false depending on input
     */
    public static boolean validateInput(String userInput, char[][] board) {
        switch(userInput) {
            case "1":
                return(board[0][0] == ' ');
            case "2":
                return(board[0][1] == ' ');    
            case "3":
                return(board[0][2] == ' ');    
            case "4":
                return(board[1][0] == ' ');
            case "5":
                return(board[1][1] == ' ');
            case "6":
                return(board[1][2] == ' ');
            case "7":
                return(board[2][0] == ' ');
            case "8":
                return(board[2][1] == ' ');
            case "9":
                return(board[2][2] == ' ');
            default:
                return false;
        }
    }

    /*
     * This function does PlayerOne's turn
     * @param board, 2D char matrix of game
     * @param scanner, user input
     */
    public static void playerOneTurn(char[][] board, Scanner scanner) {
        String userInput;
        while(true) {
            System.out.println("Which spot on the board do you select? (1-9)");
            userInput = scanner.nextLine();
            if(validateInput(userInput, board)) {
                break;
            }
            else {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        placeMove(board, userInput, 'X'); 
    }

    /*
     * This function does PlayerTwo's turn
     * @param board, 2D char matrix of game
     * @param scanner, user input
     */
    public static void playerTwoTurn(char[][] board, Scanner scanner) {
        String userInput;
        while(true) {
            System.out.println("Which spot on the board do you select? (1-9)");
            userInput = scanner.nextLine();
            if(validateInput(userInput, board)) {
                break;
            }
            else {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        placeMove(board, userInput, 'O'); 
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
                if(board[i][j] == ' ') { 
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
     * This function checks if someone has won by checking if either symbols form three across
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
    public static void placeMove(char[][] board, String position, char symbol) {
        switch(position) {
			case "1":
				board[0][0] = symbol;
				break;
			case "2":
				board[0][1] = symbol;
				break;
			case "3":
				board[0][2] = symbol;
				break;
			case "4":
				board[1][0] = symbol;
				break;
			case "5":
				board[1][1] = symbol;
				break;
			case "6":
				board[1][2] = symbol;
				break;
			case "7":
				board[2][0] = symbol;
				break;
			case "8":
				board[2][1] = symbol;
				break;
			case "9":
				board[2][2] = symbol;
				break;
			default:
				System.out.println("Invalid input, try again.");
		}
    }
}