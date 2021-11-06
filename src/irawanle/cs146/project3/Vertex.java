package irawanle.cs146.project3;

public class Vertex {
	// boolean array indicating availability of walls surrounding vertex
	private boolean[] walls = {true, true, true, true};
	// vertex color
	private String color;
	// parent vertex 
	private Vertex parent;
	// X coordinate
    private int x_coord;
    // Y coordinate
    private int y_coord;
	
	/**
	 * No-arg Constructor
	 */
	public Vertex() {
		parent = null;
		color = "white";
	}
	
	/**
	 * Constructor with x and y coordinates
	 * @param x x_coordinate
	 * @param y y_coordinate
	 */
	public Vertex(int x, int y) {
		parent = null;
		color = "white";
		x_coord = x;
		y_coord = y;
	}
	
	/**
	 * neighborPresentAt method breaks down a wall at a particular direction
	 * 0 - North
	 * 1 - East
	 * 2 - South
	 * 3 - West
	 * @param index direction
	 */
	public void neighborPresentAt(int index){
		walls[index] = false;
	}
	
	/**
	 * wallStatus method
	 * determines whether a cell vertex
	 * has all its walls intact
	 * @return true if all walls are present
	 * 			false otherwise
	 */
	public boolean wallStatus() {
		for (boolean wall : walls) {
			if (wall)
				continue;
			else
				return false;
		}
		return true;		
	}
	
	/**
	 * setColor method assigns color to vertex
	 * white - unexplored
	 * grey - discovered
     * black - explored
     * @param c color of vertex
	 */
	public void setColor(String c){
		color = c;
	}
	
	/**
	 * getColor method retrieves vertex's color
	 * @return color 
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * setParent method assigns this object a parent vertex
	 * @param p parent vertex
	 */
	public void setParent(Vertex p) {
		parent = p;
	}
	
	/**
	 * getParent method retrieves parent vertex
	 * @return parent vertex
	 */
	public Vertex getParent() {
		return parent;
	}
	
	/**
	 * getCoordX method retrieves x coordinate of vertex
	 * @return x coordinate of vertex
	 */
	public int getCoordX() {
		return x_coord;
	}
	
	/**
	 * getCoordY method retrieves y coordinate of vertex
	 * @return y coordinate of vertex
	 */
	public int getCoordY() {
		return y_coord;
	}
}
