package irawanle.cs146.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class MazeReader {
	 public static String[][] readFile(String filename) {
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
	 
	 public static Grid createMaze(String [][] mazeRead) {
		 int gridSize = (mazeRead.length-1)/2;
		 Grid maze = new Grid(gridSize);
		 int i = 1;
		 int j = 1;
		 for (int x = 0; x < gridSize; x++) {
			 if (i > mazeRead.length-1) break;
			 j = 1;
			 for (int y = 0; y < gridSize; y++) {
				if (j > mazeRead.length-1) break;
				Vertex curr = maze.getCell(x, y);
				if (mazeRead[i-1][j] == " ") curr.getWallStatus()[0] = false;
				if (mazeRead[i][j+1] == " ") curr.getWallStatus()[1] = false;
				if (mazeRead[i+1][j] == " ") curr.getWallStatus()[2] = false;
				if (mazeRead[i][j-1] == " ") curr.getWallStatus()[3] = false;
				j+=2;
			}
			i+=2;
		}
		 return maze;
	 }
	 
	 public static void main( String [] args) {
		 String [][] m = MazeReader.readFile("maze4.txt");
		 for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(m
							[i][j]);
				}
				System.out.print("\n");
			}
		 Grid output = MazeReader.createMaze(m);
		 DisplayMaze n = new DisplayMaze(output);
			for (int i = 0; i < n.size; i++) {
				for (int j = 0; j < n.size; j++) {
					System.out.print(n.display[i][j]);
				}
				System.out.print("\n");
			}
		 
	 }

}
