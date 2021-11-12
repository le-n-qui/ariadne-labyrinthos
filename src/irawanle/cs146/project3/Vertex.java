package irawanle.cs146.project3;

/**
 * The Vertex class makes individual
 * vertices to be placed into a grid.
 * @author Andy Qui Le & Ashley Irawan
 */
public class Vertex {
	// boolean array indicating availability of walls surrounding vertex
	private boolean[] walls = {true, true, true, true};
	// vertex color
	private ColorCode color;
	// parent vertex 
	private Vertex parent;
	// X coordinate
    private int x_coord;
    // Y coordinate
    private int y_coord;
    // shortest distance from a source vertex
    private int distance;
	
	/**
	 * No-arg Constructor
	 */
	public Vertex() {
		parent = null;
		color = ColorCode.WHITE;
		distance = 0;
	}
	
	/**
	 * Constructor with x and y coordinates
	 * @param x x_coordinate
	 * @param y y_coordinate
	 */
	public Vertex(int x, int y) {
		parent = null;
		color = ColorCode.WHITE;
		x_coord = x;
		y_coord = y;
		distance = 0;
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
	 * determines whether a vertex
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
	 * getWallStatus method retrieves 
	 * the boolean array that tells the 
	 * statuses of the four walls at each vertex
	 * @return the statuses of the walls
	 */
	public boolean[] getWallStatus() {
		return walls;
	}
	
	public void breakDownWall(int index) {
		walls[index] = false;
	}
	
	/**
	 * setColor method assigns color to vertex
	 * white - unexplored
	 * grey - discovered
     * black - explored
     * @param c color of vertex
	 */
	public void setColor(ColorCode c){
		color = c;
	}
	
	/**
	 * getColor method retrieves a vertex's color
	 * @return color 
	 */
	public ColorCode getColor() {
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
	
	/**
	 * resetDistance method sets distance to zero
	 */
	public void resetDistance() {
		distance = 0;
	}
	
	/**
	 * getDistance method retrieves distance accumulated at vertex
	 * @return distance between source vertex and this vertex
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * updateDistance method sets a new distance amount to current vertex
	 * @param amount distance between current vertex's parent and source
	 */
	public void updateDistance(int amount) {
		distance = amount + 1; // 1: accounting for current vertex
	}
}
