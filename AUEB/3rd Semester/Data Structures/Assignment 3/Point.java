import java.util.*;
import java.io.*;
import java.lang.Math;
public class Point{
	int x,y; // syntetagmenes.
	
	Point(){
		x = (int)(Math.random() * ((100 - 0) + 1)) + 0; // tyxaios int sto [0,100].
		y = (int)(Math.random() * ((100 - 0) + 1)) + 0; // tyxaios int sto [0,100].
	}
	
	Point (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x(){
		return x;
	}
	
	public int y(){
		return y;
	}
	
	public double distanceTo(Point z){
		double dx = x - z.x;
		double dy = y - z.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public int squareDistanceTo(Point z){
		return (int)distanceTo(z)*(int)distanceTo(z);
	}
	
	public String toString(){
		return "(" + x + "," + y + ")";
	}
}	