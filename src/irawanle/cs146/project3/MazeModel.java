package irawanle.cs146.project3;

import java.util.Random;
import java.util.ArrayDeque;
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
		Deque<Vertex> cellStack = new ArrayDeque<Vertex>();
		// Total number of cells in maze
		int totalCells = theMaze.numOfCells();
		// Cell at (START_COORD_X, START_COORD_Y) will be the starting point
		Vertex currentCell = theMaze.getCell(START_COORD_X,START_COORD_Y); 
		// Track number of visited cells
		int numVisitedCells = 1;
		// Create a Random generator
		Random randSelect = new Random();
		// Set seed to produce the same result every time
		randSelect.setSeed(totalCells);
		// A while loop
		while (numVisitedCells < totalCells) {
			// find all neighbors of currentCell with all walls intact
			int currLocX = currentCell.getCoordX();
			int currLocY = currentCell.getCoordY();
			if (currLocX - 1 >= 0) {
				// current cell has a neighbor to the North
			}
			if (currLocX + 1 < theMaze.getLimitOfGrid()) {
				// current cell has a neighbor to the South
			}
			if (currLocY + 1 < theMaze.getLimitOfGrid()) {
				// current cell has a neighbor to the East
			}
			if (currLocY - 1 >= 0) {
				// current cell has a neighbor to the West
			}
			// if one or more neighbors found, choose one at random
				// remove wall between this selected neighbor and currentCell
				// push currentCell into cellStack
				// update currentCell with this new neighbor
				// increment numVisitedCells by 1
			// otherwise
				// pop the most recent cell entry off the cellStack
				// make it currentCell
		}
	}
}
