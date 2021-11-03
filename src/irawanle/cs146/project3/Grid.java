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
	
	/**
	 * numOfCells method
	 * @return total number of cells
	 */
	public int numOfCells() {
		return size*size;
	}
	
	/**
	 * getLimitOfGrid method
	 * @return the size of the n x n  grid
	 */
	public int getLimitOfGrid() {
		return size;
	}
	
	/**
	 * getCell method
	 * @return reference to the cell
	 */
	public Vertex getCell(int x_loc, int y_loc) {
		return mazeGrid[x_loc][y_loc];
	}
	
}
