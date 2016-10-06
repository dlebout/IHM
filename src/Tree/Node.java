package Tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import newComponent.PhotoComponent.Annotation;

public abstract class Node {
	protected Node parent;
	protected ArrayList<Node> childrens = new ArrayList<Node>();
	
	protected boolean visibility = true;
	
	protected AffineTransform affTransform;
	protected Color colorFill;
	protected Color colorStk;
	protected Font font;
	protected Stroke stk;
	
	protected int x;
	protected int y;
	
	protected boolean isLimited = false;
	protected int limitX;
	protected int limitY;
	protected int limitWidth;
	protected int limitHeight;
	
	public Node(){
		affTransform = new AffineTransform();
		affTransform.setToIdentity();
		colorFill = Color.black;
		colorStk = Color.yellow;
		font = new Font("Helvetica", Font.BOLD, 16);;
		stk = new BasicStroke(10.0f);
	}
	
	public void removeChild(Node child){
		childrens.remove(child);
	}
	
	public void addChild(Node child){
		childrens.add(child);
	}
	
	public void setAffTransform(AffineTransform at){
		affTransform = at;
	}
	public void setcolorFill(Color c){
		colorFill = c;
	}
	public void setcolorStk(Color c){
		colorStk = c;
	}
	public void font(Font f){
		font = f;
	}
	public void setstk(Stroke s){
		stk = s;
	}
	
    public void paintComponent(Graphics g){
    	System.out.println("paint");
    	draw((Graphics2D)g);
    	for(int i=0; i<childrens.size();i++){
    		childrens.get(i).draw((Graphics2D)g);
    	}
    	
    }
    
    public abstract Rectangle getBound();
    public abstract void draw(Graphics2D g2);
}
