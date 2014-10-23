package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape{

	public Rectangle(int x, int y, Color color, int width, int height) {
		super(x, y, color, width,height);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		Rectangle2D.Double rect = new Rectangle2D.Double(
				upperLeft.getX(), upperLeft.getY(),
				dimensions.getX(), dimensions.getY());
		g2.fill(rect);
		
	}
	
	

}
