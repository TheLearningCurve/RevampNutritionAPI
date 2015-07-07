package macroCalculator.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MacroCalculatorController extends AnchorPane implements Initializable{

	public MacroCalculatorController controller;
	
	public MacroCalculatorController()
	{

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/macroCalculator/view/MacroCalculator.fxml"));
			fxmlLoader.setController(this);
			fxmlLoader.setRoot(this);
			controller = (MacroCalculatorController) fxmlLoader.getController();
				
			try
			{
				fxmlLoader.load();
			}
			
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
			
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
