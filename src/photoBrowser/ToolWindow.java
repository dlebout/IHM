package photoBrowser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import Tree.Node;

public class ToolWindow extends JToolBar {
	JButton colorFill;
	JButton colorStroke;
	JSpinner strokeSize;
	SpinnerModel modelStroke;
	JSpinner fontSize;
	SpinnerModel modelFont;
	
	JButton s_colorFill;
	JButton s_colorStroke;
	JSpinner s_strokeSize;
	SpinnerModel s_modelStroke;
	JSpinner s_fontSize;
	SpinnerModel s_modelFont;
	
	Node selectedItem;
	
	Container cont;
	BorderLayout bl;
	
	public ToolWindow(Container c, BorderLayout b){
		super();
		this.setMinimumSize(new Dimension(100,100));
		initWidget();
		initWidgetSelect();
		initVisualLine();
		this.setVisible(true);
		
		cont = c;
		bl = b;
		this.addAncestorListener(new AncestorListener(){	
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
			}	
			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				if ( bl.getLayoutComponent(cont, BorderLayout.NORTH) == ToolWindow.this ){
					initVisualLine();
				}else if ( bl.getLayoutComponent(cont, BorderLayout.SOUTH) == ToolWindow.this ){
					initVisualLine();
				}else if ( bl.getLayoutComponent(cont, BorderLayout.EAST) == ToolWindow.this ){
					initVisualVertical();
				}else if ( bl.getLayoutComponent(cont, BorderLayout.WEST) == ToolWindow.this ){
					initVisualVertical();
				}else{
					initVisualFloatable();
				}
				revalidate();
				repaint();
			}	
			@Override
			public void ancestorAdded(AncestorEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void initWidget(){
		colorFill = new JButton();
		colorFill.setBackground(Color.yellow);
		
		colorStroke = new JButton();
		colorStroke.setBackground(Color.yellow);
		
		modelStroke =new SpinnerNumberModel(10,4,48,1); 
		strokeSize = new JSpinner();
		modelFont =new SpinnerNumberModel(10,4,48,1);
		fontSize = new JSpinner();
	}
	
	public void initWidgetSelect(){
		s_colorFill = new JButton();
		s_colorFill.setBackground(Color.yellow);
		
		s_colorStroke = new JButton();
		s_colorStroke.setBackground(Color.yellow);
		
		s_modelStroke =new SpinnerNumberModel(10,4,48,1); 
		s_strokeSize = new JSpinner();
		s_modelFont =new SpinnerNumberModel(10,4,48,1);
		s_fontSize = new JSpinner();
	}
	
	public void initVisualFloatable(){
		this.removeAll();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
		pan1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan1.add(new JLabel("111111a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan1.add(colorFill,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pan1.add(new JLabel("222222a"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		pan1.add(colorStroke,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		pan1.add(new JLabel("333333a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		pan1.add(strokeSize,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		pan1.add(new JLabel("44444a"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		pan1.add(fontSize,gbc);
		
		pan2.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan2.add(new JLabel("111111z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan2.add(s_colorFill,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pan2.add(new JLabel("222222z"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		pan2.add(s_colorStroke,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		pan2.add(new JLabel("333333z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		pan2.add(s_strokeSize,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		pan2.add(new JLabel("44444z"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		pan2.add(s_fontSize,gbc);
		
		this.setLayout(new GridLayout(0,2));
		this.add(pan1);
		this.add(pan2);
	}
	
	public void initVisualLine(){
		this.removeAll();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
		pan1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan1.add(new JLabel("111111a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan1.add(colorFill,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pan1.add(new JLabel("222222a"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		pan1.add(colorStroke,gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		pan1.add(new JLabel("333333a"),gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		pan1.add(strokeSize,gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		pan1.add(new JLabel("44444a"),gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		pan1.add(fontSize,gbc);
		
		pan2.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan2.add(new JLabel("111111z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan2.add(s_colorFill,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pan2.add(new JLabel("222222z"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		pan2.add(s_colorStroke,gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		pan2.add(new JLabel("333333z"),gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		pan2.add(s_strokeSize,gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		pan2.add(new JLabel("44444z"),gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		pan2.add(s_fontSize,gbc);
		
		this.setLayout(new GridLayout(0,2));
		this.add(pan1);
		this.add(pan2);
	}
	
	public void initVisualVertical(){
		this.removeAll();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
		pan1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan1.add(new JLabel("111111a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan1.add(colorFill,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		pan1.add(new JLabel("222222a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		pan1.add(colorStroke,gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		pan1.add(new JLabel("333333a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		pan1.add(strokeSize,gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		pan1.add(new JLabel("44444a"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		pan1.add(fontSize,gbc);
		
		pan2.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		pan2.add(new JLabel("111111z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		pan2.add(s_colorFill,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		pan2.add(new JLabel("222222z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		pan2.add(s_colorStroke,gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		pan2.add(new JLabel("333333z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		pan2.add(s_strokeSize,gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		pan2.add(new JLabel("44444z"),gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		pan2.add(s_fontSize,gbc);
		
		this.setLayout(new GridLayout(2,0));
		this.add(pan1);
		this.add(pan2);
	}
	
	
}
