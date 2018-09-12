package model;

public class Cell {

	private boolean covered;
	private int numberAdjacent;
	private boolean disableCell;
	private boolean mark;
	private boolean mine;
	private boolean visited;

	public Cell() {
		covered = true;
		numberAdjacent = 0;
		disableCell = false;
		mark = false;
		visited = false;
	}

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	public int getNumberAdjacent() {
		return numberAdjacent;
	}

	public void setNumberAdjacent(int numberAdjacent) {
		this.numberAdjacent = numberAdjacent;
	}

	public boolean isDisableCell() {
		return disableCell;
	}

	public void setDisableCell(boolean disableCell) {
		this.disableCell = disableCell;
	}

	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
