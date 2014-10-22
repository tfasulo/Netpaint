package Shapes;

import java.awt.Color;
import java.awt.Point;

abstract public class Shape {

	private Point upperLeft;
	private Color color;
	
	Shape(int x, int y, Color color){
		upperLeft = new Point(x,y);
		this.color = color;
	}
	
	protected Point getUpperLeft(){
		return upperLeft;
	}
	
	protected Color getColor(){
		return color;
	}
}
