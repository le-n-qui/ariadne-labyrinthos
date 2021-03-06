package irawanle.cs146.project3;

/**
 * The Grid class creates a virtual 2-D array 
 * structure that a maze can be built on later.
 * @author Andy Qui Le & Ashley Irawan
 */
public class Grid {
	// 2-D representation of a grid containing vertices
	private Vertex [][] mazeGrid;
	// grid size n  in an n x n grid
	private int size;
	
	/**
	 * No-arg constructor
	 */
	public Grid() {
		mazeGrid = null;
		size = 0;
	}
	
	/**
	 * Constructor: creating a grid given an input size
	 * @param gridSize
	 */
	public Grid(int gridSize) {
		mazeGrid = new Vertex[gridSize][gridSize];
		size = gridSize;
		populateGrid(); // invoke method to fill grid with vertices
	}
	
	/**
	 * populateGrid method
	 * fills grid with cells (or vertices)
	 */
	private void populateGrid() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mazeGrid[i][j] = new Vertex(i, j);
			}
		}
	}
	
	/**
	 * resetGrid method
	 * ensures that each cell (vertex)
	 * has default value of null for 
	 * parent attribute and value of WHITE 
	 * for color attribute
	 * 
	 */
	public void resetGrid() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				mazeGrid[row][col].setColor(ColorCode.WHITE);
				mazeGrid[row][col].setParent(null);
				mazeGrid[row][col].resetDistance();
			}
		}
	}
	
	/**
	 * setUpGrid method sets the size for grid
	 * resize the grid with new and fills in new vertices.
	 * @param gridSize size of new grid
	 */
	public void setUpGrid(int gridSize) {
		size = gridSize;
		mazeGrid = new Vertex[gridSize][gridSize];
		populateGrid();
	}
	
	/**
	 * numOfCells method retrieves the total number of cells (vertices)
	 * @return total number of cells
	 */
	public int numOfCells() {
		return size*size;
	}
	
	/**
	 * getLimitOfGrid method retrieves the size of grid
	 * @return the size of the n x n  grid
	 */
	public int getLimitOfGrid() {
		return size;
	}
	
	/**
	 * getCell method retrieves a reference to a cell (vertex) at coordinate (x_loc, y_loc)
	 * @return reference to the cell
	 */
	public Vertex getCell(int x_loc, int y_loc) {
		return mazeGrid[x_loc][y_loc];
	}
	
}
