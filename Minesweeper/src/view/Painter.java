package view;

import model.Cell;
import model.Minesweeper;

public class Painter {

	private Minesweeper minesweeper;

	public Painter() {
		
	}

	/*
	* Show the game in the console
	*/
	
	public void showMinesweeper() {
		Cell[][] celda = minesweeper.getCell();
		for (int i = 0; i < celda.length; i++) {
			for (int j = 0; j < celda[i].length; j++) {
				if (celda[i][j].isCovered()) {
					if (celda[i][j].isMark()) {
						System.out.print("P");
					} else {
						System.out.print(".");
					}
				} else if (celda[i][j].isDisableCell()) {
					System.out.print("-");
				} else {
					System.out.print(celda[i][j].getNumberAdjacent());
				}

				System.out.print(" ");
			}
			System.out.println(" \n");
		}
	}

	/*
	* Show in console all the mines in the Game
	*/
	
	public void showMines() {
		Cell[][] celda = minesweeper.getCell();
		for (int i = 0; i < celda.length; i++) {
			for (int j = 0; j < celda[i].length; j++) {
				if (celda[i][j].isMine()) {
					System.out.print("*");
				} else {
					if (celda[i][j].isCovered()) {
						if (!(celda[i][j].isMark())) {
							System.out.print(".");
						}
					} else if (celda[i][j].isDisableCell()) {
						System.out.print("-");
					} else {
						System.out.print(celda[i][j].getNumberAdjacent());
					}
				}
				System.out.print(" ");
			}
			System.out.println(" \n");

		}
	}
	
	public void messageWin() {
		System.out.println("YOU WON :D");
	}

	public void messageGameOver() {
		System.out.println("GAME OVER :(");
	}
	
	public void messageErrorNumberMines() {
		System.out.println("You cannot enter more mines than number of Cells");
	}
	
	public void messageWrongInput() {
		System.out.println("Wrong input");
	}
	
	public void messageInputBeginGame() {
		System.out.println("| Instructions: To win the game you have to uncover all the cells and to mark (flag) all the mines  |");
		MessageAuthor();
		System.out.println(
				"Write the board’s height, width, and number of mines separate with blank space each one e.g. 8 15 10");

	}

	public void messageInputInstruction() {
		System.out.println("Select one cell e.g if you want to Uncover \"3 6 U\", if you want to Mark \"3 6 M\"  ");
	}
	
	public void messageInput() {
		System.out.println("Select one cell");
	}
	
	public void messageErrorReadingInput() {
		System.out.println("Error reading input");
	}
	
	public void messageTheyAreNoNumbers() {
		System.out.println("They are not numbers");
	}
	
	public void MessageAuthor() {
		System.out.println("|------------------ Minesweeper ------------------- Made by Daniel Steveen Ocampo ------------------| ");
	}

	public Minesweeper getMinesweeper() {
		return minesweeper;
	}

	public void setMinesweeper(Minesweeper minesweeper) {
		this.minesweeper = minesweeper;
	}
	
	
	
}
