package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
	
	int numberOfButtons = 0;
	static int buttonNumberPressed = -1;
	
	Group buttonGroup = new Group();
	
	static List<ItemButton> buttons = new ArrayList<ItemButton>();
	
	@FXML
	public void getTypeAHead()
	{
		scrollPane.setVisible(true);
		listView.setVisible(true);
		listView.setItems(items);
		
	}
	
	/* Left Panel Code */	
	
	@FXML
	public void addButtons() // Change to your liking.
	{
			for (int i = 0; i < 10; i++)
			{
				ItemButton but = new ItemButton(numberOfButtons);
				but.setLayoutY(numberOfButtons * 60);
				buttons.add(but);
				buttonGroup.getChildren().add(but);
				numberOfButtons++;
			}
	
			buttonList.setContent(buttonGroup);
	}

	@FXML
	public void finishedScroll() // Change to your liking.
	{
		if (buttonList.getVvalue() == 1)
			addButtons();
	}
	
	
	
	
	/* Right Panel Code */
	

	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { }
}
