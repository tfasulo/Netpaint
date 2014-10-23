package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Image extends Shape{

	BufferedImage image;
	
	public Image(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, null, width, height);
		this.image = image;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image,(int)upperLeft.getX(), (int)upperLeft.getY(),
				(int)dimensions.getX(), (int)dimensions.getY(),null);
	}

}
