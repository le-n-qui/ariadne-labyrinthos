package irawanle.cs146.project3;

import java.util.ArrayList;
import java.io.*;

/**
 * The DisplayMaze class creates an ASCII 
 * view of the maze.
 * @author Ashley Irawan & Andy Qui Le
 */
public class DisplayMaze {
	// 2-D view of the maze
	String [][] display;
	// size of the maze 
	int size;

	/**
	 * Constructor - create a view of the maze in ASCII
	 * @param theMaze
	 */
	public DisplayMaze(Grid theMaze) {
		size = ((theMaze.getLimitOfGrid()+1)*2)-1;
		display = new String[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == 0 || x % 2 == 0) {
					if (y == 0 || y % 2 == 0) display[x][y] = "+";
					else display[x][y] = "-";
				}
				else {
					if (y == 0 || y % 2 == 0) display[x][y] = "|";
					else display[x][y] = " ";
				}

			}		
		}
		display[0][1] = " ";
		display[size-1][size-2] = " ";
		int i = 1;
		int j = 1;
		int x = 0;
		int y = 0;
		while (i < size-1) {
			j = 1;
			y = 0;
			while (j < size-1) {
				Vertex curr = theMaze.getCell(x, y);
				if (curr.getWallStatus()[0] == false) display[i-1][j] = " "; 
				if (curr.getWallStatus()[1] == false) display[i][j+1] = " ";
				if (curr.getWallStatus()[2] == false) display[i+1][j] = " ";
				if (curr.getWallStatus()[3] == false) display[i][j-1] = " ";
				y++;
				j += 2;
			}
			x++;
			i += 2;
		}
	}
	
	/**
	 * showPath method takes an array list
	 * of the vertices. It adds a hash mark wherever
	 * the vertex is in the solution path.
	 * @param solution array list of vertices in the solution path
	 */
	public void showPath(ArrayList<Vertex> solution) {
		for (Vertex v: solution) {
			int i = (v.getCoordX()*2) + 1;
			int j = (v.getCoordY()*2) + 1;
			display[i][j] = "#";
		}
	}
	
	/**
	 * showAllPaths method takes an array list of the vertices.
	 * It adds the rightmost digit of the 
	 * actual number in the order in which 
	 * the vertex was visited.
	 * @param solution array list of vertices in the explore path
	 */
	public void showAllPaths(ArrayList<Vertex> solution) {
		int count = 0;
		for (Vertex v: solution) {
			int i = (v.getCoordX()*2) + 1;
			int j = (v.getCoordY()*2) + 1;
			display[i][j] = "" + count%10 + "";
			count++;
		}
	}

	/**
	 * getSize method retrieves the size of the display view.
	 * @return the size of the display view
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * getDisplay method retrieves the 2D String array
	 * representing the display view.
	 * @return the 2D String array
	 */
	public String[][] getDisplay(){
		return display;
	}
	
	/**
	 * @Override toString method to use System.out to print DisplayMaze object
	 */
	public String toString() {
		String output = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				output += display[i][j];
			}
			output += "\n";
		}
		output += "\n";
		return output;
	}
	
	/**
	 * createFile method creates a text file
	 * from a string produced from the 
	 * transformation of the 2D String array.
	 * @param output
	 * @param name
	 */
	public void createFile(String output, String name) {
		try {
           FileOutputStream fileOut
                = new FileOutputStream(name + ".txt");
           
           int gridSize = (size - 1)/2;
           String number = "" + gridSize ;
           fileOut.write(number.getBytes());
           fileOut.write(" ".getBytes());
           fileOut.write(number.getBytes());
           fileOut.write("\n".getBytes());

           // converting string into byte array
           byte b[] = output.getBytes();
           fileOut.write(b);
           fileOut.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
	
	}
}

	
//	public static void main(String [] args) {
//		Grid g = new Grid(3);
//		DisplayMaze m = new DisplayMaze(g);
//		for (int i = 0; i < m.size; i++) {
//			for (int j = 0; j < m.size; j++) {
//				System.out.print(m.display[i][j]);
//			}
//			System.out.print("\n");
//		}
//	}

