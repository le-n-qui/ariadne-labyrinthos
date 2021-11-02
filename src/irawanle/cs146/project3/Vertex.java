package irawanle.cs146.project3;

public class Vertex {
	private boolean [] direction = {false, false, false, false};
	private String color;
	private Vertex parent;
    //private int [] coordinates = new int[2];
	
	/*
	 * Constructor
	 */
	public Vertex() {
		parent = null;
		color = "white";
	}
	
	/*
	 * setDirection method
	 * 0 - North
	 * 1 - East
	 * 2 - South
	 * 3 - West
	 * @param index direction
	 */
	public void setDirection(int index){
		direction[index] = true;
	}
	
	/*
	 * setColor method
	 * white - unexplored
	 * grey - discovered
     * black - explored
     * @param c color of vertex
	 */
	public void setColor(String c){
		color = c;
	}
	
	/*
	 * getColor method
	 * @return color 
	 */
	public String getColor() {
		return color;
	}
	
	/*
	 * setParent method
	 * @param p parent vertex
	 */
	public void setParent(Vertex p) {
		parent = p;
	}
	
	/*
	 * getParent method
	 * @return parent vertex
	 */
	public Vertex getParent() {
		return parent;
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
