package photoBrowser;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Browser extends JPanel{
	

	public Browser(int row, int column){
		super();
		this.setLayout(new GridLayout(column,row));
		for(int i=0; i< row*column; i++){
			JPanel jp = new JPanel();
			jp.setBackground(new Color(i*10,i*5,i*3));
			this.add(jp);
		}
	}
}
