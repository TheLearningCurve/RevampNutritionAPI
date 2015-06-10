package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.ResourceBundle;

import nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class FrameBottomLeftController implements Initializable
{
	@FXML
	private AnchorPane leftPanel;
	
	@FXML
	ScrollPane buttonList;
	
	Group buttonGroup = new Group();
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		buttonList.vvalueProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue)
			{
				if(newValue.intValue() == 1)
				{
					finishedScroll();
				}
			}		
		});
	}

	public void finishedScroll() // Change to your liking.
	{
		if (buttonList.getVvalue() == 1)
		{
			QueryVariables.setOffset(10);
			//requestSearchData();
		}
	}
	
	public void updateButtonList(ItemButton but) 
	{
		
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				//buttonGroup.getChildren().add(but);
				buttonList.setContent(buttonGroup);
			}
		});
	}
}
