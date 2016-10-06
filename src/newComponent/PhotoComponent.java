package newComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Tree.Node;
import Tree.PathNode;
import Tree.RootNode;
import Tree.TextNode;

public class PhotoComponent extends JComponent{

	protected String path;
	protected Image photo;
	protected boolean flipped;
	
	protected Stroke stroke = new BasicStroke(10.0f);
	protected Color color = Color.BLACK;
	protected Font font = new Font("Helvetica", Font.BOLD, 16);
	
	protected RootNode rd = new RootNode();
	protected PathNode lastPNode;
	protected TextNode lastTNode;
	protected ArrayList<Annotation> listAnnotations = new ArrayList<Annotation>();
	protected DrawAnno currentAnot;
	protected TypedAno typano;
	protected boolean click;
	
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
			System.out.println(path);
			updateSize();
			this.addMouseListener(new CustomMouseListener());
			this.addMouseMotionListener(new CustomMouseDrag());
			this.addKeyListener(new CustomKeyboardListener());
			this.setFocusable(true);
		} catch (IOException e) {
		}
	}
	
	public void updateSize(){
		size = new Dimension(photo.getWidth(this),photo.getHeight(this));
		preferredSize = new Dimension(photo.getWidth(this),photo.getHeight(this));
		this.setSize(preferredSize);
		this.setPreferredSize(preferredSize);
		System.out.println(size);
		System.out.println(preferredSize);
		System.out.println(this.getSize());
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
    	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
    	Graphics2D g2 = (Graphics2D) g;
    	if ( !flipped ){
	    	g.setColor(Color.BLUE);
	    	g.fillRect(0, 0, getWidth(), getHeight());
	    	g.drawImage(photo, 0, 0, this);
    	}else{
    		g.setColor(Color.BLUE);
	    	g.fillRect(0, 0, getWidth(), getHeight());
    		g.setColor(Color.WHITE);
    		g.fillRect(0, 0, photo.getWidth(this), photo.getHeight(this));
    		/*for (Annotation an: listAnnotations){
    			an.draw(g);
    		}
    		if (currentAnot != null) currentAnot.draw(g);
    		if (typano != null) typano.draw(g);*/
    		rd.draw(g2);
    		if ( lastPNode != null ) { lastPNode.draw(g2); }
    	}
    }
    
    public abstract class Annotation{
    	protected Stroke stk;
    	protected Color color;
    	
    	public abstract void draw(Graphics g);
    }
    
	public class DrawAnno extends Annotation{
    	protected Stroke stk;
    	protected Color color;
		protected ArrayList<int[]> listPoint = new ArrayList<int[]>();
		
		public DrawAnno(Stroke s, Color c){
			stk = s;
    		color = c;
    	}
		
    	public int getSize(){
    		return listPoint.size();
    	}
    	
    	public void addPoint(int x, int y){
    		System.out.println("add point:" + x + " " + y);
    		int[] pos = {x,y};
    		listPoint.add(pos);
    	}
    	
    	public void draw(Graphics g){
    		Graphics2D g2 = (Graphics2D)g;
    		System.out.println("draw anot");
    		for(int i=1; i<listPoint.size(); i++){
    			g2.setStroke(stk);
    			g2.setColor(Color.black);
    			g2.drawLine(listPoint.get(i-1)[0], listPoint.get(i-1)[1], listPoint.get(i)[0], listPoint.get(i)[1]);
    		}
    	}
	}
	
	public class TypedAno extends Annotation{
		protected String str = "";
		protected Color color;
		protected Font font;
		protected int x = 0;
		protected int y = 0;
		
		public TypedAno(Font f, Color c, int x, int y){
			color = c;
			font = f;
			this.x = x;
			this.y = y;
    	}
		
    	public void addChar(char c){
    		str += c;
    	}
    	
    	public void draw(Graphics g){
    		Graphics2D g2 = (Graphics2D)g;
    		g2.setColor(color);
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
    
	public class CustomMouseDrag extends MouseMotionAdapter{
		public CustomMouseDrag(){}
		public void mouseDragged(MouseEvent evt) {
			System.out.println("drag");
			if (flipped){
				if (click){
					click = false;
					if ( lastTNode != null){ 
						rd.addChild(lastTNode);
						lastTNode = null;
					}
					if (lastPNode != null ){
						rd.addChild(lastPNode);
						lastPNode = null;
					}
					lastPNode = new PathNode(0,0,(int)size.getWidth(),(int)size.getHeight());
					lastPNode.init(evt.getX(),evt.getY());
				}
				lastPNode.addLine(evt.getX(),evt.getY());
				repaint();
			}
		}
	}

    public class CustomMouseListener implements MouseListener{
    	protected boolean clicked = false;
    	protected Timer dclick;
    	
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("click");
			if ( clicked ){
				flipped = !flipped;
				repaint();
				clicked = false;
			}else{
				System.out.println("time start");
				clicked = true;
				dclick = new Timer();
				dclick.schedule(new Routine(), 400);
			}
		}
		
		class Routine extends TimerTask {
			public void run() {
				clicked = false;
				dclick.cancel();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("press");
			if (flipped){
				click = true;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if ( typano != null){ 
				listAnnotations.add(typano);
				typano = null;
			}
			if (lastPNode != null ){
				rd.addChild(lastPNode);
				lastPNode = null;
			}
			if ( click){
				typano = new TypedAno(font,color,e.getX(),e.getY());
				requestFocus();
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
    }
    
    public class CustomKeyboardListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println(e.getKeyChar());
			if ( flipped && typano != null){
				typano.addChar(e.getKeyChar());
				repaint();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
    	
    }
}
