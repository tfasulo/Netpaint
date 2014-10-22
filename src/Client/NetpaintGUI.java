package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Shapes.Oval;
import Shapes.Rectangle;

public class NetpaintGUI extends JFrame{
	
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
	
	public static void main(String[] args){
		
		new NetpaintGUI();
	}

	public NetpaintGUI(){
		
		colorChooser = new JColorChooser();
		
		drawingArea = new DrawingArea();
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
		
		private int oldY;
		private int oldX;
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
		
		private class ListenToMouse implements MouseMotionListener, MouseListener {

			@Override
			public void mouseDragged(MouseEvent arg0) {}

			@Override
			public void mouseMoved(MouseEvent arg0) {

				newX = arg0.getX();
				newY = arg0.getY();
				
				
				if(isDrawing){
					if(rectangle.isSelected()){
						if(newX-pivotX<=0 && newY-pivotY<=0){
							Graphics2D g2 = (Graphics2D) getGraphics();
							Rectangle2D.Double rect = new Rectangle2D.Double(newX, newY, pivotX-newX, pivotY-newY);
							g2.fill(rect);
						}
						else if (newX-pivotX<0 && newY-pivotY>0){
							Graphics2D g2 = (Graphics2D) getGraphics();
							Rectangle2D.Double rect = new Rectangle2D.Double(newX, pivotY, pivotX-newX, newY-pivotY);
							g2.fill(rect);
						}
						
						else if (newX-pivotX>0 && newY-pivotY<0){
							Graphics2D g2 = (Graphics2D) getGraphics();
							Rectangle2D.Double rect = new Rectangle2D.Double(pivotX, newY, newX-pivotX, pivotY-newY);
							g2.fill(rect);
						}
						
						else{
							Graphics2D g2 = (Graphics2D) getGraphics();
							Rectangle2D.Double rect = new Rectangle2D.Double(pivotX, pivotY, newX-pivotX, newY-pivotY);
							g2.fill(rect);
						}
					}
					else if(oval.isSelected()){
						if(newX-pivotX<=0 && newY-pivotY<=0){

							width = pivotX-newX;
							height = pivotY-newY;
							upperX = newX;
							upperY = newY;
//							Graphics2D g2 = (Graphics2D) getGraphics();
//							g2.fillOval(newX, newY, pivotX-newX, pivotY-newY);
						}
						else if (newX-pivotX<0 && newY-pivotY>0){
							width = pivotX-newX;
							height = newY-pivotY;
							upperX = newX;
							upperY = pivotY;
//							Graphics2D g2 = (Graphics2D) getGraphics();
//							g2.fillOval(newX, pivotY, pivotX-newX, newY-pivotY);
						}
						
						else if (newX-pivotX>0 && newY-pivotY<0){
							width = newX-pivotX;
							height = pivotY-newY;
							upperX = pivotX;
							upperY = newY;
//							Graphics2D g2 = (Graphics2D) getGraphics();
//							g2.fillOval(pivotX, newY, newX-pivotX, pivotY-newY);
						}
						
						else{
							width = newX-pivotX;
							height = newY-pivotY;
							upperX = pivotX;
							upperY = pivotY;
//							Graphics2D g2 = (Graphics2D) getGraphics();
//							g2.fillOval(pivotX, pivotY, newX-pivotX, newY-pivotY);
						}	
							Graphics2D g2 = (Graphics2D) getGraphics();
							g2.fillOval(upperX,upperY, width, height);
						
					}
					else if(image.isSelected()){
						
						
					}
					else if(line.isSelected()){
						
							Graphics2D g2 = (Graphics2D) getGraphics();
							Line2D.Double line = new Line2D.Double(pivotX, pivotY, newX, newY);
							g2.draw(line);
					}
					else{
						System.out.println("Select a Shape first.");
					}
				}
				oldX = newX;
				oldY = newY;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				if(!isDrawing){
					pivotX = arg0.getX();
					pivotY = arg0.getY();
				}

				newX = arg0.getX();
				newY = arg0.getY();
				
				isDrawing = !isDrawing;
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		}
	}
}
