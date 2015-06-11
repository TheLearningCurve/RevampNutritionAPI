package nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.AnchorPane;

public class FrameController extends AnchorPane implements Initializable
{	
	@FXML
	private AnchorPane mainPanel;
	
	/* TopPanel Variables */
	
	@FXML
	FrameTopController frameTopController;
	
	@FXML
	FrameBottomLeftController frameBottomLeftController;
	
	@FXML
	FrameBottomRightController frameBottomRightController;
	
	public static FrameController controller;

    
	public FrameController()
	{
		frameTopController = new FrameTopController();
		frameBottomLeftController = new FrameBottomLeftController();
		frameBottomRightController = new FrameBottomRightController();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nutritionAPIV2_view/Frame.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		controller = (FrameController) fxmlLoader.getController();

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
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{		

	}
}
