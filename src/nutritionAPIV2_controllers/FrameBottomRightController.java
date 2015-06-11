package nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class FrameBottomRightController extends AnchorPane implements Initializable
{
	
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;
	
	public static FrameBottomRightController controller;
	
	public FrameBottomRightController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nutritionAPIV2_view/FrameBottomRight.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (FrameBottomRightController) fxmlLoader.getController();
		
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
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
}
