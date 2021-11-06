package irawanle.cs146.project3;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

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
		end_coord_x = theMaze.getLimitOfGrid();
		// also, y coordinate is n - 1
		end_coord_y = theMaze.getLimitOfGrid();
	}
	
	/**
	 * buildMaze method
	 * creates accessible cells
	 * i.e. connecting vertices to
	 * form a path from starting and 
	 * ending vertices. 
	 */
	public void buildMaze() {
		// Create stack to keep track of cells as they are visited
		Deque<Vertex> cellStack = new ArrayDeque<Vertex>();
		// Total number of cells in maze
		int totalCells = theMaze.numOfCells();
		// Cell at (START_COORD_X, START_COORD_Y) will be the starting point
		Vertex currentCell = theMaze.getCell(START_COORD_X,START_COORD_Y); 
		// Track number of visited cells
		int numVisitedCells = 0;
		// Create a Random generator
		Random randSelect = new Random();
		// Set seed to produce the same result every time
		randSelect.setSeed(totalCells);
		// A while loop
		while (numVisitedCells < totalCells) {
			// find all neighbors of currentCell with all walls intact
			int currLocX = currentCell.getCoordX();
			int currLocY = currentCell.getCoordY();
			ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
			
			System.out.printf("\nNumber of Visited Cells: %d", numVisitedCells);
			System.out.printf("\nCurrent Cell\nX-coordinate: %d\tY-coordinate: %d", currentCell.getCoordX(), currentCell.getCoordY());
			// Determine if there is a neighbor
			if (currLocX - 1 >= 0) {
				// current cell has a neighbor to the North
				Vertex northernNeighbor = theMaze.getCell(currLocX-1, currLocY);
				if (isNeighborVisited(northernNeighbor))
					neighbors.add(northernNeighbor);
			}
			if (currLocX + 1 < end_coord_x) {
				// current cell has a neighbor to the South
				Vertex southernNeighbor = theMaze.getCell(currLocX+1, currLocY);
				if (isNeighborVisited(southernNeighbor))
					neighbors.add(southernNeighbor);
			}
			if (currLocY + 1 < end_coord_y) {
				// current cell has a neighbor to the East
				Vertex easternNeighbor = theMaze.getCell(currLocX, currLocY+1);
				if (isNeighborVisited(easternNeighbor))
					neighbors.add(easternNeighbor);
			}
			if (currLocY - 1 >= 0) {
				// current cell has a neighbor to the West
				Vertex westernNeighbor = theMaze.getCell(currLocX, currLocY-1);
				if (isNeighborVisited(westernNeighbor))
					neighbors.add(westernNeighbor);
			}
			
			
			
			// if one or more neighbors found, choose one at random
			if (neighbors.size() > 0) {
				// Randomly choose an index
				int randNeighbor = randSelect.nextInt(neighbors.size());
				// Pick a neighbor random with the above index
				Vertex chosenNeighbor = neighbors.get(randNeighbor);
				
				// Keep debug print statement; comment it out
				System.out.println("\nStart checking if neighbor is visited");
				
				// Verify if there is already a path connecting 
				// currentCell and this chosenNeighbor
				while (!isNeighborVisited(chosenNeighbor)) {
					//neighbors.remove(randNeighbor); // remove neighbor
					randNeighbor = randSelect.nextInt(neighbors.size());
					chosenNeighbor = neighbors.get(randNeighbor);
				}
				System.out.println("Done checking");
				
				// remove wall between this selected neighbor and currentCell
				removeWall(currentCell, chosenNeighbor);
				// push currentCell into cellStack
				cellStack.addFirst(currentCell);
				// update currentCell with this new neighbor
				currentCell = chosenNeighbor;
				// increment numVisitedCells by 1
				numVisitedCells += 1;
					
			}
			else { // otherwise
				// pop the most recent cell entry off the cellStack
				if (cellStack.peekFirst() != null)
					currentCell = cellStack.removeFirst(); // make it currentCell
				else // empty stack (visited all cells)
					numVisitedCells += 1;
			}	
		}
	}
	
	/**
	 * Determine if neighbor has all its walls intact
	 * @param neighbor neighboring cell
	 * @return true if all walls have not been broken down
	 * 			false otherwise
	 */
	private boolean isNeighborVisited(Vertex neighbor) {
		
		return neighbor.wallStatus();
	}
	
	private void removeWall(Vertex currCell, Vertex neighbor) {
		int currXLoc = currCell.getCoordX();
		int currYLoc = currCell.getCoordY();
		int neighborPos = -1; // cell at invalid position
		int currCellPos = -1;
		if (currXLoc - 1 == neighbor.getCoordX()) {
			neighborPos = 0; // North of current cell
			currCellPos = 2; // South of neighbor
		}
		if (currXLoc + 1 == neighbor.getCoordX()) {
			neighborPos = 2; // South of current cell
			currCellPos = 0; // North of neighbor
		}
		if (currYLoc + 1 == neighbor.getCoordY()) {
			neighborPos = 1; // East
			currCellPos = 3; // West
		}
		if (currYLoc - 1 == neighbor.getCoordY()) {
			neighborPos = 3; // West
			currCellPos = 1; // East
		}
		// Remove walls on both instances of vertices (cells)
		currCell.neighborPresentAt(neighborPos);
		neighbor.neighborPresentAt(currCellPos);
	}
	
	public static void main(String[] args) {
		Grid g = new Grid(5);
		MazeModel m = new MazeModel(g);
		m.buildMaze();
		System.out.println("\nEnd of Test");
	}
}
