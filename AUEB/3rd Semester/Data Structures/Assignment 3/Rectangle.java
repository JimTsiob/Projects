import java.util.*;
import java.io.*;
import java.lang.Math;
 public class Rectangle{
	public int xmin,xmax,ymin,ymax;
	
	Rectangle(){
		xmin = (int)(Math.random() * ((100 - 0) + 1)) + 0;
		ymin = (int)(Math.random() * ((100 - 0) + 1)) + 0;
		xmax = (int)(Math.random() * ((100 - 0) + 1)) + 0;
		if (xmax < xmin) {
			xmax = (int)(Math.random() * ((100 - 0) + 1)) + 0; // xanapairnei arithmo sthn periptwsh pou to max < min.
		}
		ymax = (int)(Math.random() * ((100 - 0) + 1)) + 0;
		if (ymax < ymin){
			ymax = (int)(Math.random() * ((100 - 0) + 1)) + 0; // xanapairnei arithmo sthn periptwsh pou to max < min.
		}
	}
	
	Rectangle(int xmin,int ymin,int xmax,int ymax){
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}
	
	public int xmin(){
		return xmin;
	}
	
	public int ymin(){
		return ymin;
	}
	
	public int xmax(){
		return xmax;
	}
	
	public int ymax(){
		return ymax;
	}
	
	public boolean contains(Point p){
		if (p.x <= xmax && p.x >= xmin && p.y <= ymax && p.y >= ymin){
			return true;
		}
		else{
			return false;
		}	
	}
	
	
	public boolean intersects(Rectangle that){
		if (this.ymax < that.ymin || this.ymin > that.ymax){
			return false; // an to ena orthogwnio einai panw h katw ap to allo return false.
		}
		if (this.xmax < that.xmin || this.xmin > that.xmax){
			return false; // an to ena orthogwnio einai aristera h dexia apo to allo return false.
		}
		return true; // alliws to ena orthogwnio diatemnei to allo.
	}
	
	
	public double distanceTo(Point p){
		double dx = 0;
		double dy = 0;
		if (p.x < xmin){
			dx = p.x - xmin;
		}
		else if (p.x > xmax){
			dx = p.x - xmax;
		}
		if (p.y < ymin){
			dy = p.y - ymin;
		}
		else if (p.y > ymax){
			dy = p.y - ymax;
		}
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public int squareDistanceTo(Point p){ // tetragwno ths eyklideias apostashs.
		return (int)distanceTo(p)*(int)distanceTo(p);
	}
	
	public String toString(){
		return "[" + xmin + "," + xmax + "] X [" + ymin + "," + ymax + "]";
	}
 }