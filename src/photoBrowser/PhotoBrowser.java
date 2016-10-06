package photoBrowser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import newComponent.PhotoComponent;

public class PhotoBrowser extends JFrame{
	
	JMenuBar menuBar;
	JMenu file;
	JMenuItem impor;
	JMenuItem delete;
	JMenuItem quit;
	JMenu view;
	JRadioButtonMenuItem photo;
	JRadioButtonMenuItem browser;
	JRadioButtonMenuItem split;
	
	
	JToolBar toolBar;
	JToggleButton family;
	JToggleButton vacation;
	JToggleButton school;
	JButton leftArrow;
	JButton rightArrow;
	
	ToolWindow toolWindow;
	
	JScrollPane scrollPane;
	JComponent middleScreen;
	JLabel bottomScreen = new JLabel();
	
	Container container;
	

	public static void main(String args[]){
		PhotoBrowser pb = new PhotoBrowser();
		pb.setVisible(true);
	}
	
	public PhotoBrowser(){
		super("window");
		container = this.getContentPane();
		initWidget();
		initListener();
		pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));
	}
	
	public void initWidget(){
		// INIT MENUBAR ************************
		
		menuBar = new JMenuBar();
		file = new JMenu("File");
		impor = new JMenuItem("Import");
		delete = new JMenuItem("Delete");
		quit = new JMenuItem("Quit");
		
		
		view = new JMenu("View");
		ButtonGroup group = new ButtonGroup();
		
		photo = new JRadioButtonMenuItem("Photo");
		photo.setSelected(true);
		group.add(photo);

		browser = new JRadioButtonMenuItem("Browser");
		group.add(browser);
		
		split = new JRadioButtonMenuItem("Split");
		group.add(split);
		
		menuBar.add(file);
		menuBar.add(view);
		
		file.add(impor);
		file.add(delete);
		file.add(quit);
		
		view.add(photo);
		view.add(browser);
		view.add(split);
		
		this.setJMenuBar(menuBar);
		
		// INIT MIDDLESCREEN *************************
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		toolWindow = new ToolWindow(this.getContentPane(), bl);
		this.add(toolWindow, BorderLayout.NORTH);
		bottomScreen = new JLabel("status bar");
		initToolBar();
		
		this.add(bottomScreen, BorderLayout.SOUTH);
		
	}
	
	public void initToolBar(){
		toolBar = new JToolBar("toolBAr");
		family = new JToggleButton("Family");
		vacation  = new JToggleButton("Vacation");
		school  = new JToggleButton("School");
		
		toolBar.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		toolBar.add(family, gbc);
		gbc.gridy = 1;
		toolBar.add(vacation, gbc);
		gbc.gridy = 2;
		toolBar.add(school, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.weighty = 80;
		gbc.weightx = 50;
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		rightArrow = new JButton("D");
		toolBar.add(rightArrow, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		leftArrow = new JButton("G");
		toolBar.add(leftArrow, gbc);
		
		
		this.add(toolBar,BorderLayout.EAST);
	}
	
	public void initListener(){
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bottomScreen.setText("exiting the app");
				System.exit(1);
			}
		});
		
		impor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bottomScreen.setText("import photo");
				JFileChooser jfc = new JFileChooser();
				int response = jfc.showOpenDialog(impor);
				String path = jfc.getSelectedFile().getAbsolutePath();
				middleScreen = new PhotoComponent(path);
				scrollPane = new JScrollPane(middleScreen);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				add(scrollPane, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bottomScreen.setText("photo deleted");
				if ( middleScreen != null){ remove(scrollPane);}
			}
		});
		
		photo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bottomScreen.setText("now in photo mode");
				//remove(middleScreen);
				//revalidate();
			}
		});
		
		browser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				/*bottomScreen.setText("now in browser mode");
				remove(middleScreen);
				middleScreen = new Browser(3,3);
				add(middleScreen, BorderLayout.CENTER);
				revalidate();*/
			}
		});
		
		split.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				/*bottomScreen.setText("now in split mode");
				remove(middleScreen);
				middleScreen = new SplitMode();
				add(middleScreen, BorderLayout.CENTER);
				revalidate();*/
			}
		});
		
		family.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				classifyStatut();
			}
		});
		
		vacation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				classifyStatut();
			}
		});
		
		school.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				classifyStatut();
			}
		});
	}
	
	public void classifyStatut(){
		String str = "classy by: ";
		if ( family.isSelected()){
			str += "family, ";
		}
		if ( vacation.isSelected()){
			str += "vacation, ";
		}
		if ( school.isSelected()){
			str += "school, ";
		}
		bottomScreen.setText(str + "options");
		
	}
}
