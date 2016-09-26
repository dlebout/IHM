package newComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class PhotoComponent extends JComponent{

	protected String path;
	protected Image photo;
	protected boolean flipped;
	protected ArrayList<Annotation> listAnnotations;
	
	protected Dimension size = new Dimension(600,400);
	protected Dimension preferredSize = new Dimension(600,400);;
	
	public PhotoComponent(){
		super();
	}
	public PhotoComponent(String p){
		super();
		try {
			path = p;
			photo = ImageIO.read(new File(path));
			updateSize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSize(){
		size = new Dimension(photo.getWidth(this),photo.getHeight(this));
		preferredSize = new Dimension(photo.getWidth(this),photo.getHeight(this));
	}
	
	public void loadPhoto(String p){
		try {
			path = p;
			photo = ImageIO.read(new File(path));
			updateSize();
			revalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void paintComponent(Graphics g){
    	if ( !flipped ){
	    	g.setColor(Color.BLUE);
	    	g.fillRect(0, 0, getWidth(), getHeight());
	    	g.drawImage(photo, 0, 0, this);
    	}else{
    		g.setColor(Color.BLUE);
	    	g.fillRect(0, 0, getWidth(), getHeight());
    		g.setColor(Color.WHITE);
    		g.fillRect(0, 0, photo.getWidth(this), photo.getHeight(this));
    		for (Annotation an: listAnnotations){
    			an.draw(g);
    		}
    	}
    }
    
    public class Annotation{
    	public Annotation(){
    		
    	}
    	
    	public void draw(Graphics g){
    		
    	}
    }

    public class DoubleClickListener implements MouseListener{
    	protected boolean clicked = false;
    	protected Timer dclick;
    	
		@Override
		public void mouseClicked(MouseEvent e) {
			if ( clicked ){
				
			}else{
				
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
}
