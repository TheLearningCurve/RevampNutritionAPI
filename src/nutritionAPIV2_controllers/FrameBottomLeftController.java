package nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class FrameBottomLeftController extends AnchorPane implements Initializable
{
	@FXML
	AnchorPane leftPanel;
	
	@FXML
	ScrollPane buttonList;
	
	Group buttonGroup = new Group();
	
	public static List<ItemButton> buttons = new ArrayList<ItemButton>();
	public static int numberOfButtons = 0;
	public static int buttonNumberPressed = -1;
	
	public static FrameBottomLeftController controller;
	
	public FrameBottomLeftController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nutritionAPIV2_view/FrameBottomLeft.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (FrameBottomLeftController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public void finishedScroll() // Change to your liking.
	{
		if (buttonList.getVvalue() == 1)
		{
			QueryVariables.setOffset(10);
			FrameTopController.controller.requestSearchData();
		}
	}
	
	public void updateButtonGroup(ItemButton but) 
	{	
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				buttonGroup.getChildren().add(but);
			}
		});
	}
	
	public void updateButtonList() 
	{	
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				buttonList.setContent(buttonGroup);
			}
		});
	}
	
	
	public void createButton(String brandName, String itemName)
	{
		ItemButton but = new ItemButton(numberOfButtons, brandName, itemName);
		but.setLayoutY(numberOfButtons * 60);
		buttons.add(but);	
		updateButtonGroup(but);
		numberOfButtons++;
	}
}
