package irawanle.cs146.project3;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests using
 * JUnit Testing Framework.
 * @author Andy Qui Le & Ashley Irawan
 */
class TestMazeGenerator {
	static MazeReader read;
	
	/**
	 * This method will prepare objects.
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Create a maze reader object
		read = new MazeReader();
		
		// Maze sizes to be tested
		int[] sizeList = {6, 7};
		// Create each text file with given size in list
		for (int size : sizeList) {
			// Create grid
			Grid grid = new Grid(size);
			// Create maze
			MazeModel maze = new MazeModel(grid);
			// Build maze
			maze.buildMaze();
			// Find solution
			maze.findEscapeRouteDeeply();
			// Create a maze view
			DisplayMaze view = new DisplayMaze(grid);
			// Mark solution path on maze
			view.showPath(maze.getDFSSolution());
			// create text file containing the expected result
			view.createFile(view.toString(), ("Ateam_maze"+size));
		}
	}

	
	@BeforeEach
	void setUp() throws Exception {

	}
	
	
	@Test
	void test1() {
		
		
	}
	
	@Test 
	void test2() {
		
	}

}
