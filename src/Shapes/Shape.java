package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

abstract public class Shape {

	protected Point upperLeft;
	protected Point dimensions;
	protected Color color;
	private Point pivot;
	protected Point start;
	protected Point end;
	
	Shape(int x, int y, Color color, int width, int height){
		upperLeft = new Point(x,y);
		dimensions = new Point(width, height);
		this.color = color;
	}
	
	Shape(int startX, int startY, int endX, int endY, Color color){
		this.start = new Point(startX, startY);
		this.end = new Point(endX, endY);
		this.color = color;
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
	protected Point getStart(){
		return start;
	}
	protected Point getEnd(){
		return end;
	}
	
	public abstract void draw(Graphics g);
}
