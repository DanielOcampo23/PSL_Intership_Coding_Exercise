package model;


public class Minesweeper {

	public final int CONTINUE = 0;
	public final int WIN = 1;
	public final int LOSE = 2;

	private int row;
	private int column;
	private int mines;
	private Cell[][] cell;
	private int numberOfCells;
	private int numberOfMarks;

	public Minesweeper(int row, int column, int mines) {

		cell = new Cell[row][column];
		this.row = row;
		this.column = column;
		this.mines = mines;

		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				cell[i][j] = new Cell();
			}
		}

		numberOfCells = row * column;
	}

	/*
	 * Generate amount of random mines that the user required
	 */

	public void generateRandomMines() {
		int i = 0;
		while (i < mines) {
			int rowRandomNumber = randomNumber(row);
			int columnRandomNumber = randomNumber(column);

			if (!cell[rowRandomNumber][columnRandomNumber].isMine()) {
				cell[rowRandomNumber][columnRandomNumber].setMine(true);
				i++;
			}
		}
		System.out.println(" \n");
	}

	/*
	 * Generate a randomNumber according to the size of the matrix
	 */

	public int randomNumber(int size) {
		double numberRandom = Math.random() * size;
		int numeroInt = (int) numberRandom;

		return numeroInt;
	}

	/*
	 * It calculates the number of mines around the cell
	 */

	public void writeAdjacentNumber() {
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				boolean isMine = cell[i][j].isMine();
				if (!isMine) {

					int numberOfMines = 0;
					for (int k = -1; k < 2; k++) {
						for (int k2 = -1; k2 < 2; k2++) {
							if ((i + k) >= 0 && (i + k) < row && (j + k2) >= 0 && (j + k2) < column) {
								if (cell[i + k][j + k2].isMine()) {
									numberOfMines++;
								}
							}
						}
					}
					if (numberOfMines == 0) {
						cell[i][j].setDisableCell(true);
					} else {
						cell[i][j].setNumberAdjacent(numberOfMines);
					}

				}
			}
		}
	}

	/*
	 * Uncover all the disable cells around the first cell that was clicked
	 */

	public void unCoveredDisableCellsAround(int rowClicked, int columnClicked) {
		cell[rowClicked][columnClicked].setVisited(true);
		if (cell[rowClicked][columnClicked].isCovered()) {
			cell[rowClicked][columnClicked].setCovered(false);
			numberOfCells--;
		}
		for (int k = -1; k < 2; k++) {
			for (int k2 = -1; k2 < 2; k2++) {
				if ((rowClicked + k) >= 0 && (rowClicked + k) < row && (columnClicked + k2) >= 0
						&& (columnClicked + k2) < column) {
					if (!(cell[rowClicked + k][columnClicked + k2].isVisited())) {
						if (cell[rowClicked + k][columnClicked + k2].isDisableCell()) {
							unCoveredDisableCellsAround(rowClicked + k, columnClicked + k2);
						} else if (cell[rowClicked + k][columnClicked + k2].getNumberAdjacent() > 0) {
							if (cell[rowClicked + k][columnClicked + k2].isCovered()) {
								cell[rowClicked + k][columnClicked + k2].setCovered(false);
								numberOfCells--;
							}
						}
					}
				}
			}
		}

	}

	/*
	* Returns 0 to continue playing
	* returns 1 if the player win
	* and returns 2 if the player lose uncovering a mine
	*/
	
	public int cellClicked(int rowClicked, int columnClicked, String action) {
		if (cell[rowClicked][columnClicked].isCovered()) {
			if (action.equals("U")) {
				if (cell[rowClicked][columnClicked].isMark()) {
					cell[rowClicked][columnClicked].setMark(false);
					numberOfMarks--;
				}
				if (cell[rowClicked][columnClicked].isMine()) {
					return LOSE;
				} else if (cell[rowClicked][columnClicked].isDisableCell()) {
					unCoveredDisableCellsAround(rowClicked, columnClicked);
				} else if (cell[rowClicked][columnClicked].isCovered()) {
					cell[rowClicked][columnClicked].setCovered(false);
					numberOfCells--;
				}

			} else if (action.equals("M")) {
				if (!(cell[rowClicked][columnClicked].isMark())) {
					cell[rowClicked][columnClicked].setMark(true);
					numberOfMarks++;
				}
			} else {
				System.out.println("You wrote a bad action, these are the actions (U=Uncovered, M=Mark)");
			}

			if (numberOfCells == mines && mines == numberOfMarks) {
				return WIN;
			}
		}
		return CONTINUE;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getMines() {
		return mines;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}

	public Cell[][] getCell() {
		return cell;
	}

	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}

	public int getNumberOfCells() {
		return numberOfCells;
	}

	public void setNumberOfCells(int numberOfCells) {
		this.numberOfCells = numberOfCells;
	}

	public int getNumberOfMarks() {
		return numberOfMarks;
	}

	public void setNumberOfMarks(int numberOfMarks) {
		this.numberOfMarks = numberOfMarks;
	}

}
