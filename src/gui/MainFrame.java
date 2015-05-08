package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	/*
	 * 	Don't set width or height with this.  We will let the JFrame figure out the proper size when we 'pack()' it.
	 * 	Only the JPanels inside this JFrame will have absolute width and height.
	 * 
	 * 	
	 */
	
	public MainFrame()
	{
		setLayout(new BorderLayout());
		
		//	Pseudo-ish Code:
		//	LeftPanel leftPanel = new LeftPanel();
		//	RightPanel rightPanel = new RightPanel();
		//	TopPanel topPanel = new TopPanel();
		//	add(leftPanel);
		//	add(rightPanel);
		//	add(topPanel);
		//	pack();
		//	setVisible();
	}
}
