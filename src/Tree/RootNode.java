package Tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

public class RootNode extends Node{

	@Override
	public Rectangle getBound() {
		return null;
	}

	@Override
	public void draw(Graphics2D g2) {
		affTransform = new AffineTransform();
		affTransform.setToIdentity();
		colorFill = Color.black;
		colorStk = Color.yellow;
		font = new Font("Helvetica", Font.BOLD, 16);;
		stk = new BasicStroke(10.0f);
		
		for(int i=0; i<childrens.size();i++){
			childrens.get(i).draw(g2);
		}
	}

}
