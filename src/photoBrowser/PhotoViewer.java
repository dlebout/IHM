package photoBrowser;

import java.awt.Color;

import javax.swing.JPanel;

import newComponent.PhotoComponent;

public class PhotoViewer extends JPanel {
	
	public PhotoViewer(){
		super();
		this.add(new PhotoComponent("./pays.png"));
	}

}
