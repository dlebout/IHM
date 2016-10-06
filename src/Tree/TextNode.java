package Tree;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;

public class TextNode extends Node{
	protected String str = "";
	
	public Dimension size;
	
	public TextNode(int x,int y, Dimension s){
		this.x = x;
		this.y = y;
		this.size = s;
	}
	
	@Override
	public Rectangle getBound() {
		return null;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(colorFill);
		g2.setFont(font);
		
		String[] list;
		String inter = "";
		int posX = x;
		int posY = y;
		
		for (int i=0; i< str.length(); i++){
			double taille =  font.getStringBounds(inter+str.charAt(i), 
    				new FontRenderContext(font.getTransform(), false, false)).getBounds().getWidth();
			if (posX + taille > size.getWidth() ){
				int last = inter.lastIndexOf(" ");
				if ( last != -1){
					g2.drawString(inter.substring(0, last), posX, posY);
					inter = inter.substring(last+1, inter.length()-1);
				}else{
					g2.drawString(inter, posX, posY);
					inter = "";
				}
				posX = 10;
				posY+= font.getSize()*1.5;
				if ( posY+10 > size.getHeight() ) return;
			}
			inter += str.charAt(i);
		}
		g2.drawString(inter, posX, posY);
	}
	
}
