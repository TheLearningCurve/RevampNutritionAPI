package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class FrameController implements Initializable
{
	/* Top Panel */
	
	@FXML
	TextField searchField;	
	@FXML
	ListView<String> listView;	
	@FXML
	ScrollPane scrollPane;
	

	ObservableList<String> items =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Single", "Double", "Suite",
		    "Single", "Double", "Suite", "Single", "Double", "Suite");
		
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
	}
	
	@FXML
	public void getTypeAHead(){
		scrollPane.setVisible(true);
		listView.setVisible(true);
		listView.setItems(items);
		
	}
	
	/* Left Panel */
	
	
	
	/* Right Panel */
}
