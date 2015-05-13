package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.ResourceBundle;

import nutritionAPIV2_service.Adapter;
import nutritionAPIV2_service.QueryVariables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

/* 
 * Created by Kyle Wolff May 13 2015
 */
public class SearchController implements Initializable{

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
	
}
