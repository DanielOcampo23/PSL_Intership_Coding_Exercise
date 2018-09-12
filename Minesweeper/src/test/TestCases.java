package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Minesweeper;

public class TestCases {

	@Test
	public void testFailGame() {		
		int height = 100;
		int width = 100;
		int mines = 500;
		Minesweeper buscaminas = new Minesweeper(height, width, mines);
		
		buscaminas.generateRandomMines();
		buscaminas.writeAdjacentNumber();
		
		boolean fail = false;
		for (int i = 0; i < height && !fail; i++) {
			for (int j = 0; j < width && !fail; j++) {
				if(buscaminas.cellClicked(i, j, "U") == 2) {
					fail = true;					
				}				
			}
		}
		assertTrue(fail);
	}
	
	@Test
	public void testWinCondition() {		
		int height = 100;
		int width = 100;
		int mines = 300;
		Minesweeper buscaminas = new Minesweeper(height, width, mines);
		
		buscaminas.generateRandomMines();
		buscaminas.writeAdjacentNumber();
		
		int result = 0;
		for (int i = 0; i < height && result == 0; i++) {
			for (int j = 0; j < width && result == 0; j++) {
				if(buscaminas.getCell()[i][j].isMine()) {
					result = buscaminas.cellClicked(i, j, "M");
				}else {
					result = buscaminas.cellClicked(i, j, "U");
				}			
			}
		}
				
		assertTrue(result == 1);
		

	}
	

}
