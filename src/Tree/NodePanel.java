package Tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import newComponent.PhotoComponent.Annotation;

public class NodePanel extends JPanel {

	protected RootNode rootNode = new RootNode();
	
	public NodePanel(Dimension dim){
		this.setSize(dim);
		System.out.println(dim);
		ShapeNode s1 = new ShapeNode(new Rectangle(200,380,50,50),200,380);
		ShapeNode s2 = new ShapeNode(new Rectangle(100,180,50,50),100,180);
		ShapeNode s3 = new ShapeNode(new Rectangle(600,500,50,50),600,500);
		
		ShapeNode s4 = new ShapeNode(new Ellipse2D.Double(280,480,50,50),280,480);
		
		s3.addChild(s4);
		s1.addChild(s2);
		s1.addChild(s3);
		rootNode.addChild(s1);
		
		revalidate();
		repaint();
	}
	
    @Override
    public void paintComponent(Graphics g){
    	rootNode.draw(((Graphics2D) g));
    }
}
