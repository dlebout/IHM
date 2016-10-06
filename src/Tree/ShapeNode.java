package Tree;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class ShapeNode extends Node{
	
	protected Shape shape;
	
	public ShapeNode(Shape s, int x, int y){
		super();
		this.shape = s;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Rectangle getBound() {
		return shape.getBounds();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setTransform(affTransform);
		g2.setFont(font);
		g2.setStroke(stk);
		
		g2.setColor(colorFill);
		g2.fill(shape);
		g2.setColor(colorStk);
		g2.draw(shape);
		
		for(int i=0; i<childrens.size();i++){
			childrens.get(i).draw(g2);
		}
	}
	
}
