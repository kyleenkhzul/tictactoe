package com.csc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTicTacToe {
    TicTacToe ticTacToe;

    @BeforeEach
    void setUp() {
        ticTacToe = new TicTacToe();
    }

     // Test for validating input
    @Test
    public void testValidateInput_ValidMove() {
        char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        assertTrue(TicTacToe.validateInput(1, board));
        assertTrue(TicTacToe.validateInput(9, board));
    }

    @Test
    public void testValidateInput_InvalidMove() {
        char[][] board = {{'X', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        assertFalse(TicTacToe.validateInput(1, board));  // Already taken spot
        assertFalse(TicTacToe.validateInput(10, board)); // Invalid input
    }

    // Test for placing a move
    @Test
    public void testPlaceMove() {
        char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        TicTacToe.placeMove(board, 1, 'X');
        assertEquals('X', board[0][0]);

        TicTacToe.placeMove(board, 9, 'O');
        assertEquals('O', board[2][2]);
    }

    // Test for checking game outcome
    @Test
    public void testHasContestantWon_HorizontalWin() {
        char[][] board = {{'X', 'X', 'X'}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        assertTrue(TicTacToe.hasContestantWon(board, 'X'));
    }

    @Test
    public void testHasContestantWon_VerticalWin() {
        char[][] board = {{'O', ' ', ' '}, {'O', ' ', ' '}, {'O', ' ', ' '}};
        assertTrue(TicTacToe.hasContestantWon(board, 'O'));
    }

    @Test
    public void testHasContestantWon_DiagonalWin() {
        char[][] board = {{'X', ' ', ' '}, {' ', 'X', ' '}, {' ', ' ', 'X'}};
        assertTrue(TicTacToe.hasContestantWon(board, 'X'));
    }

    @Test
    public void testHasContestantWon_NoWin() {
        char[][] board = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'O', 'X', 'O'}};
        assertFalse(TicTacToe.hasContestantWon(board, 'X'));
        assertFalse(TicTacToe.hasContestantWon(board, 'O'));
    }

    // Test for checking if the game is finished (tie)
    @Test
    public void testIsGameFinished_Tie() {
        char[][] board = {{'X', 'O', 'X'}, {'X', 'O', 'O'}, {'O', 'X', 'X'}};
        assertTrue(TicTacToe.isGameFinished(board));  // Tie
    }

    @Test
    public void testIsGameFinished_NotFinished() {
        char[][] board = {{'X', 'O', ' '}, {'X', ' ', 'O'}, {'O', 'X', ' '}};
        assertFalse(TicTacToe.isGameFinished(board));  // Game still ongoing
    }
}
