package irawanle.cs146.project3;

import java.util.ArrayList;

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


	public DisplayMaze(Grid theMaze){
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
//				x = curr.getCoordX();
//				y = curr.getCoordY();
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
	
	public void showPath(ArrayList<Vertex> solution) {
		for (Vertex v: solution) {
			int i = (v.getCoordX()*2) + 1;
			int j = (v.getCoordY()*2) + 1;
			display[i][j] = "#";
		}
	}
	
	public void showAllPaths(ArrayList<Vertex> solution) {
		int count = 0;
		for (Vertex v: solution) {
			int i = (v.getCoordX()*2) + 1;
			int j = (v.getCoordY()*2) + 1;
			display[i][j] = "" + count%10 + "";
			count++;
		}
	}

	
	public int getSize() {
		return size;
	}
	
	public String[][] getDisplay(){
		return display;
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
}