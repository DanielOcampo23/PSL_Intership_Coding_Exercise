package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Minesweeper;
import view.Painter;

public class Main {

	public static void main(String[] args) {
		
		Painter painter = new Painter();
		painter.MessageAuthor();
		boolean out = true;
		int minesInt = 0;
		int columnInt = 0;
		int rowInt = 0;
		while (out) {
			painter.messageInputBeginGame();
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String linea = in.readLine();
	
				String[] input = linea.split(" ");
				if(input.length == 3) {
					String row = input[0];
					String column = input[1];
					String mines = input[2];
		
					rowInt = Integer.parseInt(row);
					columnInt = Integer.parseInt(column);
					minesInt = Integer.parseInt(mines);
					
					if(minesInt < (rowInt * columnInt)) {
						out = false;
					}else {
						painter.messageErrorNumberMines();
					}					
				}else {
					painter.messageWrongInput();
				}
			}catch (IOException e) {
				painter.messageErrorReadingInput();
			}catch (NumberFormatException e) {
				painter.messageTheyAreNoNumbers();
			}
		}
		Minesweeper buscaminas = null;

		buscaminas = new Minesweeper(rowInt, columnInt, minesInt);
		
		painter.setMinesweeper(buscaminas);

		buscaminas.generateRandomMines();
		buscaminas.writeAdjacentNumber();
		painter.showMinesweeper();

		boolean gameOver = false;
		while (!gameOver) {
			painter.messageInputInstruction();
			
			try {
				BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
				String linea1 = in1.readLine();
	
				String[] cellSelected = linea1.split(" ");
				
				if(cellSelected.length == 3) {
					String rowSelected = cellSelected[0];
					String columnSelected = cellSelected[1];
					String action = cellSelected[2];
						
					int rowClicked = Integer.parseInt(rowSelected);
					int columnClicked = Integer.parseInt(columnSelected);
		
					int result = buscaminas.cellClicked(rowClicked, columnClicked, action);
					if(result == buscaminas.CONTINUE) {
						gameOver = false;
						painter.showMinesweeper();
					}
					if(result == buscaminas.WIN) {
						gameOver = true;
						painter.messageWin();
						painter.showMinesweeper();
						painter.messageWin();
					}
					if(result == buscaminas.LOSE) {
						gameOver = true;
						painter.messageGameOver();
						painter.showMines();
						painter.messageGameOver();
					}
				}else {
					painter.messageWrongInput();
				}
			
			}catch (IOException e) {
				painter.messageErrorReadingInput();
			}catch (NumberFormatException e) {
				painter.messageTheyAreNoNumbers();
			}

		}

	}

}
