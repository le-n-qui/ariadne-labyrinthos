package irawanle.cs146.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class MazeReader {
	 public String[][] readFile(String filename) {
			int counter = 0;
			String[][] maze = null;
			//Reads the file and builds student array.
			try {
			FileReader file = new FileReader(filename); 		//Open the file using FileReader Object.
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			String sizeString = buff.readLine();
			int size = Integer.parseInt(sizeString.substring(0, 1));
			int displaySize = (size*2)+1;
			maze = new String[displaySize][displaySize];

			while (!eof)										//In a loop read a line using readLine method.
			{ 
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else
				{
					for (int i= 0; i < line.length(); i++) 
					{
						maze[counter][i] = Character.toString(line.charAt(i));
					}
					counter++;
				}
			}
			buff.close();
			} 
			
			catch (IOException e) {
				System.out.println("Error -- " + e.toString());
			}
			return maze;
	 }
	 
	 public Grid createMaze(String [][] mazeRead) {
		 int gridSize = (mazeRead.length-1)/2;
		 Grid maze = new Grid(gridSize);
		 int i = 1;
		 int j = 1;
		 for (int x = 0; x < gridSize; x++) {
			 j = 1;
			 for (int y = 0; y < gridSize; y++) {
				Vertex curr = maze.getCell(x, y);
				if (mazeRead[i-1][j].equals(" ")) {
					if (x != 0) curr.breakDownWall(0);
				}
				if (mazeRead[i][j+1].equals(" ")) curr.breakDownWall(1);
				if (mazeRead[i+1][j].equals(" ")) {
					if (x != gridSize-1) curr.breakDownWall(2);
				}
				if (mazeRead[i][j-1].equals(" ")) curr.breakDownWall(3);
				j+=2;
			}
			i+=2;
		}
		 return maze;
	 }
	 
//	 public static void main( String [] args) {
//		 MazeReader read = new MazeReader();
//		 String [][] m = read.readFile("maze4.txt");
//		 for (int i = 0; i < 9; i++) {
//				for (int j = 0; j < 9; j++) {
//					System.out.print(m
//							[i][j]);
//				}
//				System.out.print("\n");
//			}
//		 Grid output = read.createMaze(m);
//		 
//		 MazeModel solveMaze = new MazeModel(output);
//		 ArrayList<Vertex> DFSExplore = solveMaze.findEscapeRouteDeeply();
//		 
//		 System.out.println("\nDFS Solution Path:");
//			for (Vertex v : solveMaze.getDFSSolution())
//				System.out.print("(" + v.getCoordX() + "," + v.getCoordY() + ") ");
//			System.out.println("\n");
//			
//			System.out.println("\nExplored Vertices Path:");
//			for (Vertex v : DFSExplore)
//				System.out.print("(" + v.getCoordX() + "," + v.getCoordY() + ") ");
//			System.out.println("\n");
//			
//			DisplayMaze display2 = new DisplayMaze(solveMaze.getGrid());
//			display2.showPath(solveMaze.getDFSSolution());
//			System.out.print(display2);
//			
//		 
//		 ArrayList<Vertex> BFSExplore = solveMaze.findEscapeRouteBroadly();
//		 System.out.println("\nBFS Solution Path: ");
//			for (Vertex i: solveMaze.getBFSSolution()) System.out.print("(" + i.getCoordX() + "," + i.getCoordY() + ") ");
//			System.out.print("\n");
//			System.out.print("Length of Path: " + (solveMaze.getBFSSolution().get(solveMaze.getBFSSolution().size()-1).getDistance()+1) + "\n");
//			
//			DisplayMaze display4 = new DisplayMaze(solveMaze.getGrid());
//			display4.showPath(solveMaze.getBFSSolution());
//			System.out.print(display4);
//	
//			
//			DisplayMaze display5 = new DisplayMaze(solveMaze.getGrid());
//			display5.showAllPaths(BFSExplore);
//			System.out.print(display5);
//		 
////		 DisplayMaze n = new DisplayMaze(output);
////			for (int i = 0; i < n.size; i++) {
////				for (int j = 0; j < n.size; j++) {
////					System.out.print(n.getDisplay()[i][j]);
////				}
////				System.out.print("\n");
////			}
//		 
//		 
//	 }

}
