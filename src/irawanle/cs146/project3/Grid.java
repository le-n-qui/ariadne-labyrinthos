package irawanle.cs146.project3;

public class Grid {
	private Vertex [][] mazeGrid;
	private int size;
	
	/**
	 * Create a grid
	 * @param gridSize
	 */
	public Grid(int gridSize) {
		mazeGrid = new Vertex[gridSize][gridSize];
		size = gridSize;
	}
	
	/**
	 * Fill grid with cells (vertices)
	 */
	private void populateGrid() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mazeGrid[i][j] = new Vertex();
			}
		}
	}
	
	
}
