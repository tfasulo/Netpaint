package Shapes;

import java.awt.Color;
import java.awt.Point;

abstract public class Shape {

	private Point upperLeft;
	private Point dimensions;
	private Color color;
	private Point pivot;
	
	Shape(int x, int y, Color color, int width, int height){
		upperLeft = new Point(x,y);
		dimensions = new Point(width, height);
		
		this.color = color;
	}
	
	Shape(int x, int y, int pivotX, int pivotY, Color color){
		
	}
	
	public Point getUpperLeft(){
		return upperLeft;
	}
	
	public Point getDimensions(){
		return dimensions;
	}
	
	public Color getColor(){
		return color;
	}
	
	protected Point getPivot(){
		return pivot;
	}
}
