package nutritionAPIV2_controllers;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class FrameController implements Initializable
{	

	@FXML
	AnchorPane mainPanel;
	
	/* Top Panel Variables */
	@FXML
	TextField searchField;	
	
	@FXML
	ListView<String> listView;
	
	@FXML
	ScrollPane scrollPane;
	
	
	/* LeftPanel Variables */
	@FXML
	AnchorPane leftPanel;
	
	@FXML
	ScrollPane buttonList;
	
	
	/* RightPanel Variables */
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;
	

	
	/* Top Panel Code */
	
	ObservableList<String> items =FXCollections.observableArrayList ( 
		    "Single", "Double", "Suite", "Single", "Double", "Suite",
		    "Single", "Double", "Suite", "Single", "Double", "Suite");
		

	
	@FXML
	public void getTypeAHead()
	{
		scrollPane.setVisible(true);
		listView.setVisible(true);
		listView.setItems(items);
		
	}
	
	@FXML
	public void addButtons()
	{
		
		Group g = new Group();
		for (int i = 0; i < 20; i++)
		{
			Button but = new Button(String.valueOf(i));
			but.setLayoutY(i*40);
			g.getChildren().add(but);
			
		}
		
		buttonList.setContent(g);
	}
	
	/* Left Panel Code */
	

	@FXML
	public void finishedScroll()
	{
		System.out.println("V value: " + buttonList.getVvalue());
		System.out.println("V max: " + buttonList.getVmax());
	}
	
	
	/* Right Panel Code */
	

	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { }
}
