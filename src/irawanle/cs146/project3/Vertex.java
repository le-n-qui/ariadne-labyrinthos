package irawanle.cs146.project3;

public class Vertex {
	private boolean [] direction = {false, false, false, false};
	private String color;
	private Vertex parent;
//	private int [] coordinates = new int[2];
	
	public Vertex() {
		parent = null;
		color = "white";
	}
	
//	0 - North
//	1 - East
//	2 - South
//	3 - West
	public void setDirection(int index){
		direction[index] = true;
	}
	
//	white - unexplored
//	grey - discovered
//	black - explored
	public void setColor(String c){
		color = c;
	}
	public String getColor() {
		return color;
	}
	
//	Link to parent
	public void setParent(Vertex p) {
		parent = p;
	}
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
