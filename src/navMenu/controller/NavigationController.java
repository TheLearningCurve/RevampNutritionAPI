package navMenu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import macroCalculator.controllers.MacroFrameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NavigationController extends AnchorPane implements Initializable{
	
	public static NavigationController controller;
	
	@FXML
	Button newSceneButton;
	
	
	public NavigationController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/navMenu/NavigationMenu.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (NavigationController) fxmlLoader.getController();
			
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void changeScenes() throws IOException{
		Stage stage;
		
		stage = (Stage) this.getScene().getWindow();
		MacroFrameController macroController = new MacroFrameController();
		
		
		Scene scene = new Scene(macroController);
	    stage.setScene(scene);
	    stage.show();
	}
}
