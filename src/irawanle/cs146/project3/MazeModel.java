package irawanle.cs146.project3;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class MazeModel {
	// define constant for x- and y-coordinate for starting cell
	private final int START_COORD_X = 0; // also a boundary limit (lower end)
	private final int START_COORD_Y = 0; // boundary limit (lower end)
	private int end_coord_x; // x coordinate for finishing cell
	private int end_coord_y; // y coordinate for finishing cell
	private Grid theMaze;
	
	/**
	 * Constructor creates a virtual maze 
	 * with cells whose four walls are present.
	 * @param maze
	 */
	public MazeModel(Grid maze) {
		theMaze = maze;
		// the last cell in grid has
		// x coordinate to be n - 1
		end_coord_x = theMaze.getLimitOfGrid(); // though this is the boundary limit n (upper end)
		// also, y coordinate is n - 1
		end_coord_y = theMaze.getLimitOfGrid(); // similarly limit is n (upper end)
	}
	
	/**
	 * buildMaze method
	 * creates accessible cells
	 * i.e. connecting cells to
	 * each other without removing 
	 * too many walls.
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
		
		// Continue to find cells and break down walls as long as 
		// number of visited cells is fewer than total number of cells
		while (numVisitedCells < totalCells) {
			// find all neighbors of currentCell with all walls intact
			int currLocX = currentCell.getCoordX(); // x coordinate of current cell
			int currLocY = currentCell.getCoordY(); // y coordinate of current cell
			// here is array list to keep all valid neighbors (with walls intact)
			ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
			
			// Keep debug print statement; comment it out when not needed
			System.out.printf("\nNumber of Visited Cells: %d", numVisitedCells);
			System.out.printf("\nCurrent Cell\nX-coordinate: %d\tY-coordinate: %d", currentCell.getCoordX(), currentCell.getCoordY());
			
			// Determine if there are neighbors
			if (currLocX - 1 >= START_COORD_X) {
				// current cell has a neighbor to the North
				Vertex northernNeighbor = theMaze.getCell(currLocX-1, currLocY);
				if (isNeighborNotVisited(northernNeighbor)) // is this neighbor visited?
					neighbors.add(northernNeighbor); // true, add it to list 
			}
			if (currLocX + 1 < end_coord_x) {
				// current cell has a neighbor to the South
				Vertex southernNeighbor = theMaze.getCell(currLocX+1, currLocY);
				if (isNeighborNotVisited(southernNeighbor))
					neighbors.add(southernNeighbor);
			}
			if (currLocY + 1 < end_coord_y) {
				// current cell has a neighbor to the East
				Vertex easternNeighbor = theMaze.getCell(currLocX, currLocY+1);
				if (isNeighborNotVisited(easternNeighbor))
					neighbors.add(easternNeighbor);
			}
			if (currLocY - 1 >= START_COORD_Y) {
				// current cell has a neighbor to the West
				Vertex westernNeighbor = theMaze.getCell(currLocX, currLocY-1);
				if (isNeighborNotVisited(westernNeighbor))
					neighbors.add(westernNeighbor);
			}
			
			
			// if one or more neighbors found, choose one at random
			if (neighbors.size() > 0) {
				// Randomly choose an index
				int randNeighbor = randSelect.nextInt(neighbors.size());
				// Pick a random neighbor with the above index
				Vertex chosenNeighbor = neighbors.get(randNeighbor);
				
				// Keep debug print statement; comment it out when not needed
				System.out.println("\nStart checking if neighbor is visited");
				
				// Verify if there is already a path connecting 
				// currentCell and this chosenNeighbor
				while (!isNeighborNotVisited(chosenNeighbor)) {
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
				// pop the most recent cell entry off stack
				if (cellStack.peekFirst() != null) // check if stack is empty
					currentCell = cellStack.removeFirst(); // true: make it the currentCell
				else // empty stack (visited all cells)
					numVisitedCells += 1; // help end while loop
			}	
		}
	}
	
	/**
	 * Determine if neighbor has all its walls intact
	 * @param neighbor neighboring cell
	 * @return true if all walls have not been broken down
	 * 			false otherwise
	 */
	private boolean isNeighborNotVisited(Vertex neighbor) {
		
		return neighbor.wallStatus();
	}
	
	/**
	 * This removeWall helper method will determine
	 * where the neighboring cell is, relative to
	 * the current cell.
	 * @param currCell
	 * @param neighbor
	 */
	private void removeWall(Vertex currCell, Vertex neighbor) {
		int currXLoc = currCell.getCoordX(); // x coordinate of current cell
		int currYLoc = currCell.getCoordY(); // y coordinate of current cell
		int neighborPos = -1; // cell at invalid position
		int currCellPos = -1; // give initial value to avoid compilation error
		
		// Examine where the neighboring cell is
		if (currXLoc - 1 == neighbor.getCoordX()) { 
			neighborPos = 0; // North of current cell
			currCellPos = 2; // then, current cell is South of neighboring cell
		}
		if (currXLoc + 1 == neighbor.getCoordX()) {
			neighborPos = 2; // South of current cell
			currCellPos = 0; // current cell is North of neighboring cell
		}
		if (currYLoc + 1 == neighbor.getCoordY()) {
			neighborPos = 1; // East of current cell
			currCellPos = 3; // current cell is West of neighboring cell
		}
		if (currYLoc - 1 == neighbor.getCoordY()) {
			neighborPos = 3; // West of current cell
			currCellPos = 1; // current cell is East of neighboring cell
		}
		// Remove walls and reflect this change in both cells
		currCell.neighborPresentAt(neighborPos);
		neighbor.neighborPresentAt(currCellPos);
	}
	
	/**
	 * This main method is used to test whether 
	 * buildMaze method does what it is supposed to.
	 * @param args
	 */
	public static void main(String[] args) {
		Grid g = new Grid(3);
		MazeModel m = new MazeModel(g);
		m.buildMaze();
		System.out.println("\nEnd of Test");
	}
}
