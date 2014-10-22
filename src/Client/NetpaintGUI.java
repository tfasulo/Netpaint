package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class NetpaintGUI extends JFrame{
	
	private JPanel drawingPanel;
	private JPanel radioButtonPanel;
	private JPanel drawingArea;
	private JPanel previewPanel;
	private JScrollPane drawingPane;
	private JTabbedPane panel;
	private ButtonGroup radioGroup;
	//private JPanel swatchesView = new SwatchesView();
	//private JPanel HsvView = new HsvView();
	//private JPanel HslView = new HslView();
	//private JPanel RgbView = new RgbView();
	//private JPanel CmykView = new CmykView();
	private JPanel swatchesView = new JPanel();
	private JPanel HsvView = new JPanel();
	private JPanel HslView = new JPanel();
	private JPanel RgbView = new JPanel();
	private JPanel CmykView = new JPanel();
	
	public static void main(String[] args){
		
		new NetpaintGUI();
	}

	public NetpaintGUI(){
		
		drawingArea = new JPanel();
		drawingArea.setBackground(Color.WHITE);
		drawingArea.setSize(800, 1000);
		
		drawingPane = new JScrollPane(drawingArea);
		//drawingPane.setWheelScrollingEnabled(true);
		
		radioButtonPanel = new JPanel();
		radioGroup = new ButtonGroup();
		
		JRadioButton line = new JRadioButton("Line");
		JRadioButton rectangle = new JRadioButton("Rectangle");
		JRadioButton oval = new JRadioButton("Oval");
		JRadioButton image = new JRadioButton("Image");
		
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
		
		panel = new JTabbedPane();
		panel.add(swatchesView, "Swatches");
		panel.add(HsvView, "HSV");
		panel.add(HslView, "HSL");
		panel.add(RgbView, "RGB");
		panel.add(CmykView, "CMYK");
		
		previewPanel = new JPanel();
		previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
		
		this.setLayout(new GridLayout(3, 0));
		this.add(drawingPanel);
		this.add(panel);
		this.add(previewPanel);
		this.setTitle("Netpaint Client");
		this.setSize(800,800);
		this.setLocation(400, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
