package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.beans.util.Cache;

import retrofit.android.MainThreadExecutor;
import nutritionAPIV2_service.Adapter;
import nutritionAPIV2_service.QueryVariables;
import nutritionAPIV2_view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
	
	public static ObservableList<String> items =FXCollections.observableArrayList();
	public static StringBuilder sb = new StringBuilder();
	public static String x;

	Adapter adapter = new Adapter();
		
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
	}
	
	@FXML
	public void getTypeAHead(KeyEvent e){
		
		getText(e.getCode());
		x = String.valueOf(sb);
		System.out.println(x);
		
		if(x.length() >= 2)
		{
			QueryVariables.setText(x);
			adapter.typeAhead();
			scrollPane.setVisible(true);
			listView.setVisible(true);
			clearItems();
		}			
	}
	
	private void clearItems() {		
		listView.getItems().clear();
		listView.setItems(items);	
	}

	public static void getText(KeyCode keyCode)
	{
		if(keyCode.isLetterKey())
		{
			sb.append(keyCode);
		}
		else if (keyCode.getName().equals("Backspace"))
		{
			if(sb.length() != 0)
			{
				sb.setLength(sb.length() - 1);
			}
		}
		
	}
	
	public static void results(String text)
	{
		SearchController.items.add(text);
	}
	

}
