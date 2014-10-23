package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Shapes.Image;
import Shapes.Line;
import Shapes.Oval;
import Shapes.Rectangle;
import Shapes.Shape;

/**
 * 
 * @author Jason, Trevor
 *
 */

public class NetpaintGUI extends JFrame {

	private JColorChooser colorChooser;
	private JPanel drawingPanel;
	private JPanel radioButtonPanel;
	private JPanel drawingArea;
	private JScrollPane drawingPane;
	private ButtonGroup radioGroup;
	private JRadioButton line;
	private JRadioButton rectangle;
	private JRadioButton oval;
	private JRadioButton image;
	private BufferedImage doge=null;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public static void main(String[] args) {

		new NetpaintGUI();
	}

	public NetpaintGUI() {

		colorChooser = new JColorChooser(Color.BLACK);

		drawingArea = new DrawingArea();
		drawingArea.setPreferredSize(new Dimension(800, 1000));
		
		drawingPane = new JScrollPane(drawingArea);
		drawingPane.setWheelScrollingEnabled(true);

		radioButtonPanel = new JPanel();
		radioGroup = new ButtonGroup();

		line = new JRadioButton("Line");
		rectangle = new JRadioButton("Rectangle");
		oval = new JRadioButton("Oval");
		image = new JRadioButton("Image");

		radioGroup.add(line);
		radioGroup.add(rectangle);
		radioGroup.add(oval);
		radioGroup.add(image);
		radioButtonPanel.add(line);
		radioButtonPanel.add(rectangle);
		radioButtonPanel.add(oval);
		radioButtonPanel.add(image);

		drawingPanel = new JPanel();
		drawingPanel.setLayout(new BorderLayout());
		drawingPanel.setSize(800, 1000);
		drawingPanel.add(drawingPane, BorderLayout.CENTER);
		drawingPanel.add(radioButtonPanel, BorderLayout.SOUTH);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(colorChooser, BorderLayout.PAGE_END);
		this.setTitle("Netpaint Client");
		this.setSize(d.width - 250, d.height - 150);
		this.setLocation(100, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private class DrawingArea extends JPanel {

		private int newX;
		private int newY;
		private int pivotX;
		private int pivotY;
		private boolean isDrawing;
		int width;
		int height;
		int upperX;
		int upperY;

		public DrawingArea() {

			this.setBackground(Color.WHITE);

			isDrawing = false;

			MouseListener listener = new ListenToMouse();
			MouseMotionListener motionListener = new ListenToMouse();

			this.addMouseMotionListener(motionListener);
			this.addMouseListener(listener);
		}

		private class ListenToMouse implements MouseMotionListener,
				MouseListener {

			@Override
			public void mouseDragged(MouseEvent arg0) {
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {

				newX = arg0.getX();
				newY = arg0.getY();
				repaint();

				// oldX = newX;
				// oldY = newY;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

				if (rectangle.isSelected() || oval.isSelected()
						|| line.isSelected() || image.isSelected()) {

					if (!isDrawing) {
						pivotX = arg0.getX();
						pivotY = arg0.getY();
					} else {
						if (rectangle.isSelected()) {
							shapes.add(new Rectangle(upperX, upperY,
									colorChooser.getColor(), width, height));
						} 
						
						else if (oval.isSelected()) {
							shapes.add(new Oval(upperX, upperY, colorChooser
									.getColor(), width, height));
						} 
						
						else if (line.isSelected()) {
							shapes.add(new Line(pivotX, pivotY, newX, newY,
									colorChooser.getColor()));
						}
						
						else if (image.isSelected()){
							shapes.add(new Image(upperX, upperY, width, height, doge));
						}
						

					}

					isDrawing = !isDrawing;
				}

				repaint();

				// newX = arg0.getX();
				// newY = arg0.getY();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		}

		@Override
		public void paintComponent(Graphics g) {
			
			try{
				doge = ImageIO.read(new File("doge.jpeg"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			Rectangle2D.Double rect;

			if (shapes.isEmpty()) {
			} else {
				// Go through all the shapes saved and paint them.
				for (Shape shape : shapes) {
					shape.draw(g);

				}

			}
			g2.setColor(colorChooser.getColor());
			if (isDrawing) {
				if (rectangle.isSelected()) {
					if (newX - pivotX <= 0 && newY - pivotY <= 0) {
						upperX = newX;
						upperY = newY;
						width = pivotX - newX;
						height = pivotY - newY;
						rect = new Rectangle2D.Double(newX, newY,
								pivotX - newX, pivotY - newY);
					} else if (newX - pivotX < 0 && newY - pivotY > 0) {
						upperX = newX;
						upperY = pivotY;
						width = pivotX - newX;
						height = newY - pivotY;
						rect = new Rectangle2D.Double(newX, pivotY, pivotX
								- newX, newY - pivotY);
					}

					else if (newX - pivotX > 0 && newY - pivotY < 0) {
						upperX = pivotX;
						upperY = newY;
						width = newX - pivotX;
						height = pivotY - newY;
						rect = new Rectangle2D.Double(pivotX, newY, newX
								- pivotX, pivotY - newY);
					}

					else {
						upperX = pivotX;
						upperY = pivotY;
						width = newX - pivotX;
						height = newY - pivotY;
						rect = new Rectangle2D.Double(pivotX, pivotY, newX
								- pivotX, newY - pivotY);
					}
					g2.fill(rect);
				} 
				
				else if (oval.isSelected()) {
					if (newX - pivotX <= 0 && newY - pivotY <= 0) {

						width = pivotX - newX;
						height = pivotY - newY;
						upperX = newX;
						upperY = newY;
					} else if (newX - pivotX < 0 && newY - pivotY > 0) {
						width = pivotX - newX;
						height = newY - pivotY;
						upperX = newX;
						upperY = pivotY;
					}

					else if (newX - pivotX > 0 && newY - pivotY < 0) {
						width = newX - pivotX;
						height = pivotY - newY;
						upperX = pivotX;
						upperY = newY;
					}

					else {
						width = newX - pivotX;
						height = newY - pivotY;
						upperX = pivotX;
						upperY = pivotY;
					}
					g2.fillOval(upperX, upperY, width, height);

				} 
				
				else if (image.isSelected()) {
					if (newX - pivotX <= 0 && newY - pivotY <= 0) {
						upperX = newX;
						upperY = newY;
						width = pivotX - newX;
						height = pivotY - newY;
					} else if (newX - pivotX < 0 && newY - pivotY > 0) {
						upperX = newX;
						upperY = pivotY;
						width = pivotX - newX;
						height = newY - pivotY;
					}

					else if (newX - pivotX > 0 && newY - pivotY < 0) {
						upperX = pivotX;
						upperY = newY;
						width = newX - pivotX;
						height = pivotY - newY;
					}

					else {
						upperX = pivotX;
						upperY = pivotY;
						width = newX - pivotX;
						height = newY - pivotY;
					}
					g2.drawImage(doge, upperX, upperY, width, height,null);

				} 
				
				else if (line.isSelected()) {

					Line2D.Double line = new Line2D.Double(pivotX, pivotY,
							newX, newY);
					g2.draw(line);
				} 
				
				else {
					System.out.println("Select a Shape first.");
				}
			}
		}
	}
}
