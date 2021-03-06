package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The Image objects for drawing the Images in the {@link DrawingArea}.
 * @author Trevor, Jason
 *
 */

public class Image extends Shape{

	BufferedImage image;
	
	/**
	 * Image constructor for drawing an Image.
	 * @param x - The x coordinate of the upper left corner of the shape.
	 * @param y - The y coordinate of the upper left corner of the shape.
	 * @param color - The current color selected for the shape using {@link JColorChooser}.
	 * @param width - The width of the shape.
	 * @param height - The height of the shape.
	 */
	
	public Image(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, null, width, height);
		this.image = image;
	}
	
	/**
	 * Abstract method to draw an Image in a specific way.
	 * @param g -  The graphics that is passed into the draw function.
	 */

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image,(int)upperLeft.getX(), (int)upperLeft.getY(),
				(int)dimensions.getX(), (int)dimensions.getY(),null);
	}

}
