package irawanle.cs146.project3;

public class DisplayMaze {
	String [][] display;
	int size;


	DisplayMaze(Grid theMaze){
		size = ((theMaze.getLimitOfGrid()+1)*2)-1;
		display = new String[size][size];
		for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
					if (x == 0 || x % 2 == 0) {
						if (y == 0 || y % 2 == 0) display[x][y] = " + ";
						else display[x][y] = " - ";
					}
					else {
						if (y == 0 || y % 2 == 0) display[x][y] = " | ";
						else display[x][y] = "   ";
					}
					
				}
					
			}
	}
	
	public static void main(String [] args) {
		Grid g = new Grid(3);
		DisplayMaze m = new DisplayMaze(g);
		for (int i = 0; i < m.size; i++) {
			for (int j = 0; j < m.size; j++) {
				System.out.print(m.display[i][j]);
			}
			System.out.print("\n");
		}
	}
}