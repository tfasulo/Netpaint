package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Oval extends Shape{

	public Oval(int x, int y, Color color, int width, int height) {
		super(x, y, color, width, height);

	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillOval((int)upperLeft.getX(), (int)upperLeft.getY(), (int)dimensions.getX(), (int)dimensions.getY());
		
	}
}
