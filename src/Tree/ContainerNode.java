package Tree;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ContainerNode extends Node{

	@Override
	public Rectangle getBound() {
		if(childrens.size() == 0) return null;
		Rectangle rect = new Rectangle(childrens.get(0).getBound());
		for(int i=1;i<childrens.size();i++){
			double x1 = Math.min(childrens.get(i).getBound().getMinX(),rect.getMinX());
			double y1 = Math.min(childrens.get(i).getBound().getMinY(),rect.getMinY());
			double x2 = Math.max(childrens.get(i).getBound().getMaxX(),rect.getMaxX());
			double y2 = Math.max(childrens.get(i).getBound().getMaxY(),rect.getMaxY());
			rect = new Rectangle((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
		}
		return rect;
	}

	@Override
	public void draw(Graphics2D g2) {}

}
