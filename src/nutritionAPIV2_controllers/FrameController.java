package nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import nutritionAPIV2_model.Results;
import nutritionAPIV2_model.SearchData;
import nutritionAPIV2_model.TypeAHead;
import nutritionAPIV2_service.Adapter;
import nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

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
	
	/* LeftPanel Variables */
	

	

	
	
	
	/* RightPanel Variables */

    
	public FrameController()
	{
		frameTopController = new FrameTopController();
		frameBottomLeftController = new FrameBottomLeftController();
		frameBottomRightController = new FrameBottomRightController();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nutritionAPIV2_view/Frame.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		

		
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
