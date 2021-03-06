package irawanle.cs146.project3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * The MazeModel class uses Grid object
 * to build a maze and find a path solution.
 * @author Andy Qui Le & Ashley Irawan
 */
public class MazeModel {
	// define constant for x- and y-coordinate for starting cell
	private final int START_COORD_X = 0; // also a boundary limit (lower end)
	private final int START_COORD_Y = 0; // boundary limit (lower end)
	private int end_coord_x; // x coordinate for finishing cell
	private int end_coord_y; // y coordinate for finishing cell
	private Grid theMaze;
	private ArrayList<Vertex> DFSSolution;
	private ArrayList<Vertex> BFSSolution;
	
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
	 * getGrid method retrieves the internal grid object.
	 * @return the maze grid
	 */
	public Grid getGrid() {
		return theMaze;
	}
	
	/**
	 * getDFSSolution method returns an
	 * array list of vertices using DFS
	 * @return solution path found by DFS search method
	 */
	public ArrayList<Vertex> getDFSSolution(){
		return DFSSolution;
	}
	
	/**
	 * getBFSSolution method returns an
	 * array list of vertices using BFS
	 * @return solution path found by BFS search method
	 */
	public ArrayList<Vertex> getBFSSolution(){
		return BFSSolution;
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
//			System.out.printf("\nNumber of Visited Cells: %d", numVisitedCells);
//			System.out.printf("\nCurrent Cell\tX-coordinate: %d\tY-coordinate: %d", currentCell.getCoordX(), currentCell.getCoordY());
			
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
				//System.out.println("\nStart checking if neighbor is visited");
				
				// Verify if there is already a path connecting 
				// currentCell and this chosenNeighbor
				while (!isNeighborNotVisited(chosenNeighbor)) {
					//neighbors.remove(randNeighbor); // remove neighbor
					randNeighbor = randSelect.nextInt(neighbors.size());
					chosenNeighbor = neighbors.get(randNeighbor);
				}
				//System.out.println("Done checking");
				
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
	 * findEscapeRouteDeeply method
	 * seeks a path from the starting cell
	 * to the finishing cell in a depth-first manner.
	 * @return a list of cells that forms a path
	 */
	public ArrayList<Vertex> findEscapeRouteDeeply() {
		// a list containing the final solution
		ArrayList<Vertex> routeList = new ArrayList<Vertex>();
		// a list containing all explored cells
		ArrayList<Vertex> exploreList = new ArrayList<Vertex>();
		// stack storing cells that have been visited
		Deque<Vertex> cellStack = new ArrayDeque<Vertex>();
		// Random generator
		Random randSelect = new Random();
		randSelect.setSeed(theMaze.numOfCells());
		// reset each cell's attributes to defaults
		theMaze.resetGrid(); 
		// currentCell is where we start the path
		Vertex currentCell = theMaze.getCell(START_COORD_X, START_COORD_Y);
		
		// current cell x coordinate
		int currCoordX = currentCell.getCoordX();
		// current cell y coordinate 
		int currCoordY = currentCell.getCoordY();
		// a boolean flag that indicates we get to the finishing cell
		boolean theEnd = false;
		
//		// Keep debug print statement, comment it out when needed
//		System.out.println(); // newline
//		System.out.println("\nTracing the maze");
		
		// Visit neighbors of current cell
		while (!theEnd && (currCoordX != end_coord_x - 1 || currCoordY != end_coord_y - 1)) {
			
			// get wall statuses for current cell
			boolean[] cellWalls = currentCell.getWallStatus();
			// a list containing accessible neighbors
			ArrayList<Vertex> openNeighbors = new ArrayList<Vertex>();
			
			// Keep debug print statement, comment it out when needed
//			System.out.println(); // newline
//			System.out.printf("\nCurrent Cell\tX-coordinate: %d\tY-coordinate: %d", currCoordX, currCoordY);
//			
			// Find accessible neighbors of current cell
			for (int direction = 0; direction < 4; direction++) { // 4 directions
				if (!cellWalls[direction]) { // if the wall is not present at this direction
					switch (direction) {
						case 0: 
							Vertex northNeighbor = theMaze.getCell(currCoordX-1, currCoordY);
							if (northNeighbor.getColor() == ColorCode.WHITE)
								openNeighbors.add(northNeighbor);
							break;
						case 1: 
							Vertex eastNeighbor = theMaze.getCell(currCoordX, currCoordY+1);
							if (eastNeighbor.getColor() == ColorCode.WHITE)
								openNeighbors.add(eastNeighbor);
							break;
						case 2: 
							Vertex southNeighbor = theMaze.getCell(currCoordX+1, currCoordY);
							if (southNeighbor.getColor() == ColorCode.WHITE)
								openNeighbors.add(southNeighbor);
							break;
						case 3: 
							Vertex westNeighbor = theMaze.getCell(currCoordX, currCoordY-1);
							if (westNeighbor.getColor() == ColorCode.WHITE)
								openNeighbors.add(westNeighbor);
							break;
						default: 
							break;
					}
				}
			}
			
			// look through neighbors of current cell
			if (openNeighbors.size() > 0) { // current cell has at least 1 neighbor
				// Generate a random index
				int randIndex = randSelect.nextInt(openNeighbors.size());
				// Keep debug print statement, comment it out when needed
				// System.out.println("\nNeighbors List Size:" + openNeighbors.size() + "\tRandom Index: " + randIndex);
				// Retrieve neighbor at this random index
				Vertex chosenNeighbor = openNeighbors.get(randIndex);
				// Keep debug print statement, comment it out when needed
				// System.out.printf("Neighbor Cell\tX-coordinate: %d\tY-coordinate: %d", chosenNeighbor.getCoordX(), chosenNeighbor.getCoordY());
				
				// Include condition to avoid adding a vertex twice in explore list
				if (currentCell.getColor() == ColorCode.WHITE)
					exploreList.add(currentCell);
				
				// Explore current cell; change cell's color to grey
				currentCell.setColor(ColorCode.GREY);
				
				// Keep debug print statement, comment it out when needed
				// System.out.println("\nPush current cell to Stack");
				
				// Push current cell into stack
				cellStack.addFirst(currentCell);
				
				// Keep debug print statement, comment it out when needed
				// System.out.println("Done pushing");
				
				// Set current cell as parent to chosen neighbor
				chosenNeighbor.setParent(currentCell);
				// Update distance from chosen neighbor to starting cell
				chosenNeighbor.updateDistance(currentCell.getDistance());
				
				// Keep debug print statement, comment it out when needed
				//System.out.println("Current path length: " + chosenNeighbor.getDistance());
				
				// current cell is now chosen neighbor after assignment
				currentCell = chosenNeighbor;
				// Update current x coordinate
				currCoordX = currentCell.getCoordX();
				// Update current y coordinate
				currCoordY = currentCell.getCoordY();
				// if we are at the finishing cell
				if (currCoordX == end_coord_x && currCoordY == end_coord_y)
					// set our flag to true
					theEnd = true;
				
			} else { // if current cell's list of neighbors is zero
				
				// Include condition to avoid adding a vertex twice in explore list
				if (currentCell.getColor() == ColorCode.WHITE)
					exploreList.add(currentCell);
				
				// Fully visited current cell; change cell's color to black
				currentCell.setColor(ColorCode.BLACK);
				
				// verify stack is not empty
				if (cellStack.peekFirst() != null) {
					// Keep debug print statement, comment it out when needed
					//System.out.println("\nPop the stack");
					
					// Pop the stack and assign what's at top of stack to be current cell
					currentCell = cellStack.removeFirst();
					// Update current x and y coordinates
					currCoordX = currentCell.getCoordX();
					currCoordY = currentCell.getCoordY();
					
					// Keep debug print statement, comment it out when needed
					//System.out.printf("Current Cell\nX-coordinate: %d\tY-coordinate: %d", currCoordX, currCoordY);
					//System.out.println("\nDone popping");
				}
				else {
					theEnd = true;
				}
			}
		}
		
		
		// Keep debug print statement; comment it out when needed
	//	System.out.println("\nFinish while loop");
		// Move cells from bottom of the stack to solution route list
		while (cellStack.peekFirst() != null)
			routeList.add(cellStack.removeLast());	
		//System.out.printf("\nCurrent Cell\nX-coordinate: %d\tY-coordinate: %d", currentCell.getCoordX(), currentCell.getCoordY());
		// note: add the finishing cell (we stopped here)
		routeList.add(currentCell); 
		exploreList.add(currentCell);
		//System.out.println("\nShortest path length: " + routeList.size());
		
		DFSSolution = routeList;
		return exploreList;
	}
	
	/**
	 * findEscapeRouteBroadly method
	 * seeks a path from the starting cell 
	 * to the finishing cell in a breadth-first manner.
	 * @return a list of cells that forms a path
	 */
	public ArrayList<Vertex> findEscapeRouteBroadly() {
		ArrayList<Vertex> routeList = new ArrayList<Vertex>();
		theMaze.resetGrid(); // reset each cell's attributes to defaults
		Queue<Vertex> queue = new LinkedList<Vertex>();
		int x = START_COORD_X;
		int y = START_COORD_Y;
		Vertex curr = theMaze.getCell(x, y);
		queue.add(curr);
		
		while (queue.size() != 0) {
			curr = queue.poll();
			routeList.add(curr);
			x = curr.getCoordX();
			y = curr.getCoordY();
			if (curr.getCoordX() == theMaze.getLimitOfGrid()-1 && curr.getCoordY() == theMaze.getLimitOfGrid()-1) {
				BFSSolution = findShortestPath(routeList);
				return routeList;
				}
			
			ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
			if (curr.getWallStatus()[0] == false) neighbors.add(theMaze.getCell(x-1, y)); 
			if (curr.getWallStatus()[1] == false) neighbors.add(theMaze.getCell(x, y+1));
			if (curr.getWallStatus()[2] == false) neighbors.add(theMaze.getCell(x+1, y));
			if (curr.getWallStatus()[3] == false) neighbors.add(theMaze.getCell(x, y-1));
			
			for (Vertex v : neighbors) {
				if (v.getColor() == ColorCode.WHITE) {
					v.setColor(ColorCode.GREY);
					v.updateDistance(curr.getDistance());
					v.setParent(curr);
					queue.add(v);
				}
			}
			curr.setColor(ColorCode.BLACK);
			}
		
		return routeList;
		}
	
	/*
	 * Find Shortest Path 
	 * @param path array list of Vertex objects that maps all the explored paths to reach end point
	 * @return array list of Vertex objects with the route that has the shortest distance
	 */
	public ArrayList<Vertex> findShortestPath(ArrayList<Vertex> path){
		ArrayList<Vertex> shortestPath = new ArrayList<Vertex>();
		Vertex curr = path.get(path.size()-1);
		shortestPath.add(curr);
		while(curr.getParent() != null) {
			shortestPath.add(curr.getParent());
			curr = curr.getParent();
		}
		Collections.reverse(shortestPath);
		return shortestPath;
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
	
	public void showResults() {
		ArrayList<Vertex> DFSExplore = this.findEscapeRouteDeeply();
		 
		
		
		DisplayMaze display2 = new DisplayMaze(this.getGrid());
		// Maze problem
		System.out.println("MAZE:");
		System.out.println(display2);
		
		// DFS Solution
		System.out.println("\nDFS: \n");
		display2.showPath(this.getDFSSolution());
		System.out.print(display2);
		display2.createFile(display2.toString(), ("DFS" + this.getGrid().getLimitOfGrid()));
			
		DisplayMaze display3 = new DisplayMaze(this.getGrid());
		display3.showAllPaths(DFSExplore);
		System.out.print(display3);
			
		System.out.print("\nPath: ");
		for (Vertex v : this.getDFSSolution())
			System.out.print("(" + v.getCoordX() + "," + v.getCoordY() + ") ");
		System.out.println("\n");
		System.out.println("Length of Path: " + (this.getDFSSolution().get(this.getDFSSolution().size()-1).getDistance()+1) + "\n");

		System.out.println("Visited Cells: " + DFSExplore.size() + "\n");
			
			
		 
		ArrayList<Vertex> BFSExplore = this.findEscapeRouteBroadly();
		
		// BFS Solution
		System.out.println("\nBFS: \n");
		DisplayMaze display4 = new DisplayMaze(this.getGrid());
		display4.showPath(this.getBFSSolution());
		System.out.print(display4);
		display4.createFile(display4.toString(), ("BFS"+ this.getGrid().getLimitOfGrid()));
	
			
		DisplayMaze display5 = new DisplayMaze(this.getGrid());
		display5.showAllPaths(BFSExplore);
		System.out.print(display5);
			
		System.out.print("\nPath: ");
		for (Vertex i: this.getBFSSolution()) System.out.print("(" + i.getCoordX() + "," + i.getCoordY() + ") ");
		System.out.println("\n");
		System.out.println("Length of Path: " + (this.getBFSSolution().get(this.getBFSSolution().size()-1).getDistance()+1) + "\n");
		
		System.out.println("Visited Cells: " + BFSExplore.size() + "\n");
		
		System.out.println("=================================");
		System.out.println("\tProgram Completed!");
		System.out.println("=================================");
		
	}
	
	/**
	 * This main method is used to test whether 
	 * various methods do what they are supposed to do.
	 * @param args
	 */
	public static void main(String[] args) {
		Grid g = new Grid(4);
		MazeModel m = new MazeModel(g);
		m.buildMaze();
		DisplayMaze display = new DisplayMaze(g);
		display.createFile(display.toString(), ("OwnMaze"+ g.getLimitOfGrid()));
		m.showResults();
	}
}
