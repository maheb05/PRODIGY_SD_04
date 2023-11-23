package com.prodigyInfotech.sudukusolver;

public class SudokuSolver {

	public static void main(String[] args) {
		int[][] sudokuBoard =
				{ { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
				{ 6, 0, 0, 1, 9, 5, 0, 0, 0 },
				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, 
				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
				{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, 
				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, 
				{ 0, 0, 0, 4, 1, 9, 0, 0, 5 },
				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

		if (solveSudoku(sudokuBoard)) {
			System.out.println("Sudoku Solved:");
			printBoard(sudokuBoard);
		} else {
			System.out.println("No solution exists.");
		}
	}

	private static boolean solveSudoku(int[][] board) {
		int[] empty = findEmpty(board);
		if (empty == null) {
			return true;
		}

		int row = empty[0];
		int col = empty[1];

		for (int num = 1; num <= 9; num++) {
			if (isValid(board, row, col, num)) {
				board[row][col] = num;

				if (solveSudoku(board)) {
					return true;
				}

				board[row][col] = 0;
			}
		}

		return false;
	}

	private static boolean isValid(int[][] board, int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num || board[i][col] == num) {
				return false;
			}
		}

		int startRow = 3 * (row / 3);
		int startCol = 3 * (col / 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[startRow + i][startCol + j] == num) {
					return false;
				}
			}
		}

		return true;
	}

	private static int[] findEmpty(int[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	private static void printBoard(int[][] board) {
		for (int[] row : board) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}
