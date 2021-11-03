package irawanle.cs146.project3;

import java.util.Random;
import java.util.Deque;

public class MazeModel {
	private final int START_COORD_X = 0;
	private final int START_COORD_Y = 0;
	private int end_coord_x;
	private int end_coord_y;
	private Grid theMaze;
	
	/**
	 * Constructor
	 * @param maze
	 */
	public MazeModel(Grid maze) {
		theMaze = maze;
		// the last cell in grid has
		// x coordinate to be n - 1
		end_coord_x = theMaze.getLimitOfGrid() - 1;
		// also, y coordinate is n - 1
		end_coord_y = theMaze.getLimitOfGrid() - 1;
	}
	
	/**
	 * buildMaze method
	 * creates accessible cells
	 * i.e. connecting vertices to
	 * form a path from starting and 
	 * ending vertices. 
	 */
	private void buildMaze() {
		// Create stack to keep track of cells as they are visited
		Deque<Vertex> cellStack = new Deque<Vertex>();
		// Total number of cells in maze
		int totalCells = theMaze.numOfCells();
		// Cell at (START_COORD_X, START_COORD_Y) will be the starting point
		Vertex currentCell = theMaze.getCell(START_COORD_X,START_COORD_Y); 
		// Track number of visited cells
		int numVisitedCells = 1
	}
}
