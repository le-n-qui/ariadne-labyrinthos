package irawanle.cs146.project3;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests using
 * JUnit Testing Framework.
 * @author Andy Qui Le & Ashley Irawan
 */
class TestMazeGenerator {
	static MazeReader read;
	static Grid grid;
	
	/**
	 * This method will prepare objects.
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Create a maze reader object
		read = new MazeReader();
		
		// Maze sizes to be tested
		int[] sizeList = {6, 7, 10, 15, 20};
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
		grid = new Grid();
	}
	
	
	@Test
	void test1() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("Ateam_maze6.txt");
		// maze size
		int mazeSize = 6;
		// set up new grid
		grid.setUpGrid(mazeSize);
		// Create maze
		MazeModel maze = new MazeModel(grid);
		// Build maze
		maze.buildMaze();
		// Find DFS solution
		maze.findEscapeRouteDeeply();
		
		// Create view 1
		DisplayMaze view1 = new DisplayMaze(grid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view1.getSize(); i++) {
			for (int j = 0; j < view1.getSize(); j++) {
				assertEquals(testMaze[i][j], dfsView[i][j]);
			}
		}
		
		// Find BFS solution
		maze.findEscapeRouteBroadly();
		// Create view 2
		DisplayMaze view2 = new DisplayMaze(grid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(testMaze[i][j], bfsView[i][j]);
			}
		}
		
		maze.showResults();
		
	}
	
	@Test 
	void test2() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("Ateam_maze7.txt");
		// maze size
		int mazeSize = 7;
		// set up new grid
		grid.setUpGrid(mazeSize);
		// Create maze
		MazeModel maze = new MazeModel(grid);
		// Build maze
		maze.buildMaze();
		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(grid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view1.getSize(); i++) {
			for (int j = 0; j < view1.getSize(); j++) {
				assertEquals(testMaze[i][j], dfsView[i][j]);
			}
		}

		// Find BFS solution
		maze.findEscapeRouteBroadly();
		// Create view 2
		DisplayMaze view2 = new DisplayMaze(grid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(testMaze[i][j], bfsView[i][j]);
			}
		}
		
		maze.showResults();
				
	}
	
	@Test 
	void test3() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("Ateam_maze10.txt");
		// maze size
		int mazeSize = 10;
		// set up new grid
		grid.setUpGrid(mazeSize);
		// Create maze
		MazeModel maze = new MazeModel(grid);
		// Build maze
		maze.buildMaze();
		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(grid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view1.getSize(); i++) {
			for (int j = 0; j < view1.getSize(); j++) {
				assertEquals(testMaze[i][j], dfsView[i][j]);
			}
		}

		// Find BFS solution
		maze.findEscapeRouteBroadly();
		// Create view 2
		DisplayMaze view2 = new DisplayMaze(grid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(testMaze[i][j], bfsView[i][j]);
			}
		}
		
		maze.showResults();
				
	}
	@Test 
	void test4() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("Ateam_maze15.txt");
		// maze size
		int mazeSize = 15;
		// set up new grid
		grid.setUpGrid(mazeSize);
		// Create maze
		MazeModel maze = new MazeModel(grid);
		// Build maze
		maze.buildMaze();
		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(grid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view1.getSize(); i++) {
			for (int j = 0; j < view1.getSize(); j++) {
				assertEquals(testMaze[i][j], dfsView[i][j]);
			}
		}

		// Find BFS solution
		maze.findEscapeRouteBroadly();
		// Create view 2
		DisplayMaze view2 = new DisplayMaze(grid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(testMaze[i][j], bfsView[i][j]);
			}
		}
		
		maze.showResults();
				
	}
	
	@Test 
	void test5() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("Ateam_maze20.txt");
		// maze size
		int mazeSize = 20;
		// set up new grid
		grid.setUpGrid(mazeSize);
		// Create maze
		MazeModel maze = new MazeModel(grid);
		// Build maze
		maze.buildMaze();
		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(grid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view1.getSize(); i++) {
			for (int j = 0; j < view1.getSize(); j++) {
				assertEquals(testMaze[i][j], dfsView[i][j]);
			}
		}

		// Find BFS solution
		maze.findEscapeRouteBroadly();
		// Create view 2
		DisplayMaze view2 = new DisplayMaze(grid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// Compare two 2D String arrays
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(testMaze[i][j], bfsView[i][j]);
			}
		}
		
		maze.showResults();
				
	}
	
	/**
	 * Testing results of DFS and BFS
	 * with text file (maze4.txt) from Dr. Potika
	 */
	@Test
	void testFile1() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("maze4.txt");
		// Get grid
		Grid testGrid = read.createMaze(testMaze);
		// Create maze
		MazeModel maze = new MazeModel(testGrid);

		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();


		// Find BFS solution
		maze.findEscapeRouteBroadly();
		
		// Create view 2
		DisplayMaze view2 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// By comparing two 2D String arrays
		// we verify that DFS and BFS return the same solution
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(dfsView[i][j], bfsView[i][j]);
			}
		}
	}
	
	/**
	 * Testing results of DFS and BFS
	 * with text file (maze6.txt) from Dr. Potika
	 */
	@Test
	void testFile2() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("maze6.txt");
		// Get grid
		Grid testGrid = read.createMaze(testMaze);
		// Create maze
		MazeModel maze = new MazeModel(testGrid);

		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();


		// Find BFS solution
		maze.findEscapeRouteBroadly();

		// Create view 2
		DisplayMaze view2 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// By comparing two 2D String arrays
		// we verify that DFS and BFS return the same solution
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(dfsView[i][j], bfsView[i][j]);
			}
		}
	}
	
	/**
	 * Testing results of DFS and BFS
	 * with text file (maze8.txt) from Dr. Potika
	 */
	@Test
	void testFile3() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("maze8.txt");
		// Get grid
		Grid testGrid = read.createMaze(testMaze);
		// Create maze
		MazeModel maze = new MazeModel(testGrid);

		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();


		// Find BFS solution
		maze.findEscapeRouteBroadly();

		// Create view 2
		DisplayMaze view2 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// By comparing two 2D String arrays
		// we verify that DFS and BFS return the same solution
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				assertEquals(dfsView[i][j], bfsView[i][j]);
			}
		}
	}
	
	/**
	 * Testing results of DFS and BFS
	 * with text file (maze10.txt) from Dr. Potika
	 */
	@Disabled
	@Test
	void testFile4() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("maze10.txt");
		// Get grid
		Grid testGrid = read.createMaze(testMaze);
		// Create maze
		MazeModel maze = new MazeModel(testGrid);

		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();


		// Find BFS solution
		maze.findEscapeRouteBroadly();

		// Create view 2
		DisplayMaze view2 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// By comparing two 2D String arrays
		// we verify that DFS and BFS return the same solution
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				System.out.println("i: " + i + " j: " + j);
				assertEquals(dfsView[i][j], bfsView[i][j]);
			}
		}
	}
	
	/**
	 * Testing results of DFS and BFS
	 * with text file (maze20.txt) from Dr. Potika
	 */
	@Disabled
	@Test
	void testFile5() {
		// Read in data from local text file
		String[][] testMaze = read.readFile("maze20.txt");
		// Get grid
		Grid testGrid = read.createMaze(testMaze);
		// Create maze
		MazeModel maze = new MazeModel(testGrid);

		// Find DFS solution
		maze.findEscapeRouteDeeply();

		// Create view 1
		DisplayMaze view1 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view1.showPath(maze.getDFSSolution());
		// Get 2D String array with dfs solution
		String[][] dfsView = view1.getDisplay();


		// Find BFS solution
		maze.findEscapeRouteBroadly();

		// Create view 2
		DisplayMaze view2 = new DisplayMaze(testGrid);
		// Mark solution path on maze
		view2.showPath(maze.getBFSSolution());
		// 2D string array with bfs solution
		String[][] bfsView = view2.getDisplay();
		// By comparing two 2D String arrays
		// we verify that DFS and BFS return the same solution
		for (int i = 0; i < view2.getSize(); i++) {
			for (int j = 0; j < view2.getSize(); j++) {
				System.out.println("i: " + i + " j: " + j);
				assertEquals(dfsView[i][j], bfsView[i][j]);
			}
		}
	}
}
