package irawanle.cs146.project3;

public class Vertex {
	private boolean[] walls = {false, false, false, false};
	private String color;
	private Vertex parent;
    private int x_coord;
    private int y_coord;
	
	/**
	 * No-arg Constructor
	 */
	public Vertex() {
		parent = null;
		color = "white";
	}
	
	/**
	 * Constructor
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
	 * neighborPresentAt() method
	 * 0 - North
	 * 1 - East
	 * 2 - South
	 * 3 - West
	 * @param index direction
	 */
	public void neighborPresentAt(int index){
		walls[index] = true;
	}
	
	/**
	 * setColor method
	 * white - unexplored
	 * grey - discovered
     * black - explored
     * @param c color of vertex
	 */
	public void setColor(String c){
		color = c;
	}
	
	/**
	 * getColor method
	 * @return color 
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * setParent method
	 * @param p parent vertex
	 */
	public void setParent(Vertex p) {
		parent = p;
	}
	
	/**
	 * getParent method
	 * @return parent vertex
	 */
	public Vertex getParent() {
		return parent;
	}
	
	/**
	 * getCoordX method
	 * @return x coordinate of vertex
	 */
	public int getCoordX() {
		return x_coord;
	}
	
	/**
	 * getCoordY method
	 * @return y coordinate of vertex
	 */
	public int getCoordY() {
		return y_coord;
	}
	
//	public void setCoordinates(int x, int y) {
//		coordinates[0] = x;
//		coordinates[1] = y;
//	}
//	
//	public int [] getCoordinates() {
//		return coordinates;
//	}
}
