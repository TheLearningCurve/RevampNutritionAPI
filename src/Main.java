import gui.MainFrame;



public class Main
{
	/*
	 * 	This is where we instantiate the MainFrame class. 
	 */
	
	public static void main(String[] args)
	{
		/*
		 * 	The only code that should be in here:
		 */
		
		//	MainFrame mainFrame = new MainFrame();
		QueryVariables.setSearchTerm("hamburger");
		QueryVariables.setItemId("MdAwfYyOO");	
		QueryVariables.setText("ap");
		
		Adapter a = new Adapter();
		a.typeAhead();
	}
}
