package Tree;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;

public class PathNode extends Node{
	protected GeneralPath path = new GeneralPath();

	public PathNode(){}
	public PathNode(int x, int y, int width, int height){
		isLimited = true;
		limitX = x;
		limitY = y;
		limitWidth = width;
		limitHeight = height;
	}
	
	@Override
	public Rectangle getBound() {
		return path.getBounds();
	}
	
	public void init(int x, int y){
		path.moveTo(x, y);
	}

	public void addLine(int x, int y){
		path.lineTo(x, y);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setTransform(affTransform);
		g2.setFont(font);
		g2.setStroke(stk);
		g2.setColor(colorStk);
		if (isLimited){
			double[] coords = new double[6];
			double[] save = new double[6];
			for ( PathIterator pi = path.getPathIterator(null); !pi.isDone(); pi.next()){
				switch(pi.currentSegment(coords)){
					case PathIterator.SEG_MOVETO:
						if ( coords[0] > limitX && coords[0] < limitX+limitWidth){
							if ( coords[1] > limitY && coords[1] < limitY+limitHeight){
								System.out.println("verif:" + coords[0] + " " +  coords[1]+ " " + save[0]+ " " + save[1]);
								g2.drawLine((int)coords[0], (int)coords[1], (int)coords[0], (int)coords[1]);
								save[0] = coords[0];
								save[1] = coords[1];
							}
						}
						break;
					case PathIterator.SEG_LINETO:
						if ( coords[0] > limitX && coords[0] < limitX+limitWidth){
							if ( coords[1] > limitY && coords[1] < limitY+limitHeight){
								if ( save == null){
									g2.drawLine((int)coords[0], (int)coords[1], (int)coords[0], (int)coords[1]);
									save = new double[6];
								}else{
									g2.drawLine((int)save[0], (int)save[1], (int)coords[0], (int)coords[1]);
								}
								save[0] = coords[0];
								save[1] = coords[1];
							}else{
								save = null;
							}
						}
						break;
				}
			}
		}else{
			g2.draw(path);
		}
	}
}
