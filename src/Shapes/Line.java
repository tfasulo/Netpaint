package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape{

	private Point start;
	private Point end;
	private Color color;
	
	public Line(int startX, int startY, int endX, int endY, Color color) {
		super(startX, startY, endX, endY, color);
		start = new Point(startX,startY);
		end = new Point(endX,endY);
		this.color=color;
	}
	
	public Point getStart(){
		return super.getStart();
	}
	public Point getEnd(){
		return super.getEnd();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
	}
}
