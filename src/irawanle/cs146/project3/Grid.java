package irawanle.cs146.project3;

public class Grid {
	private Vertex [][] mazeGrid;
	private int rowSize;
	private int colSize;
	
	public Grid(int row, int col) {
		mazeGrid = new Vertex[row][col];
		rowSize = row;
		colSize = col;
	}
	
	private void populateGrid() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				mazeGrid[i][j] = new Vertex();
			}
		}
	}
	
	
}
