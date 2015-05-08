package gui;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Create by Kyle WOlff May 8 2015
 */
@SuppressWarnings("serial")
public class SearchPanel extends JPanel {
	
	public static JTextField searchField;
	public static JLabel searchButton;
	
	
	public SearchPanel() {
		this.setLayout(null);
		this.setBackground(Color.red);
		
		
	
		
	
		add(searchField);
		add(searchButton);
	}

}
