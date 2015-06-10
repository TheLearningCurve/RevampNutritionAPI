package nutritionAPIV2_controllers;

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

public class FrameController implements Initializable
{	
	@FXML
	private AnchorPane mainPanel;
	
	/* TopPanel Variables */
	
	@FXML
	private FrameTopController frameTopController;
	
	/* LeftPanel Variables */
	

	
	static int numberOfButtons = 0;
	static int buttonNumberPressed = -1;
	static List<ItemButton> buttons = new ArrayList<ItemButton>();
	
	
	
	/* RightPanel Variables */

    
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{		

	}
	



}
