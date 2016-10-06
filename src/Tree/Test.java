package Tree;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

public class Test extends JFrame{
	protected Container cont;
	protected RootNode rn;
	
	public Test(){
		super("window");
		this.setSize(new Dimension(800,800));
		this.setMinimumSize(new Dimension(800,800));
		
		init();
		
		pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	public void init(){
		this.add(new NodePanel(this.getSize()));
	}
	
	public static void main(String[] args){
		new Test();
	}
}
