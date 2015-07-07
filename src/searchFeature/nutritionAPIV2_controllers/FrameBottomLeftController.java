package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import searchFeature.nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FrameBottomLeftController extends AnchorPane implements Initializable
{
	@FXML
	AnchorPane leftPanel;
	
	@FXML
	ScrollPane buttonList;
	
	@FXML
	Label resultLabel;
	
	
	Group buttonGroup = new Group();
	
	public static List<ItemButton> buttons = new ArrayList<ItemButton>();
	public static int numberOfButtons = 0;
	public static int buttonNumberPressed = -1;
	
	public static FrameBottomLeftController controller;
	
	public FrameBottomLeftController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/searchFeature/nutritionAPIV2_view/FrameBottomLeft.fxml"));
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
	
	public void getResultLabel(final int limit, final int offset, final int total)
	{
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				
				if(total < 10)
				{
					resultLabel.setText("Showing results " + total + " out of " + total);
				}
				else if(offset == 0)
				{
					resultLabel.setText("Showing results " + limit + " out of " + total);
				}
				else
				{
					resultLabel.setText("Showing results " + (limit + offset) + " out of " + total);
				}
				
			}
		});
	}
	
	public void updateButtonGroup(final ItemButton but) 
	{	
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				buttonGroup.getChildren().add(but);
				buttonList.setContent(buttonGroup);
			}
		});
	}
	
	public void createButton(String brandName, String itemName, String thumbnail)
	{
		Insets inset = new Insets(30);
		
		ItemButton but = new ItemButton(numberOfButtons, brandName, itemName, thumbnail);
		but.setLayoutY(numberOfButtons * 150);
		but.setPadding(inset);
		buttons.add(but);	
		updateButtonGroup(but);
		numberOfButtons++;
	}
	
	public void clearItemOpacity()
	{
		for(int i = 0; i < FrameBottomLeftController.buttons.size(); i++)
		{
			
				FrameBottomLeftController.buttons.get(i).setOpacity(1);
			
		}
	}
}
