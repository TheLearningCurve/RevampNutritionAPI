package nutritionAPIV2_controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.*;


public class FrameController extends AnchorPane implements Initializable
{	
	@FXML
	private AnchorPane mainPanel;
	
	/* TopPanel Variables */
	
	@FXML
	Button menuButton;
	
	@FXML
	SplitPane navMenuPane;
	
	@FXML
	Pane navMenu;
	
	@FXML
	AnchorPane LeftandRightAnchor;
	
	@FXML
	HBox LeftAndRightPanel;
	
	public double opacity;
	
	public static FrameController controller;
	
	public Image standardButton = new Image("resources/menuButton.png");
	public Image buttonClicked = new Image("resources/menuButtonClicked.png");
	ImageInput image = new ImageInput();
	
	public FrameController()
	{
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nutritionAPIV2_view/Frame.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		controller = (FrameController) fxmlLoader.getController();

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
		
		opacity = menuButton.getOpacity();
	
		
		menuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				menuButton.setOpacity(1.0);
			}
		});
		
		menuButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				menuButton.setOpacity(opacity);
			}
		});
		
		menuButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event)
			{
				
				image.setSource(buttonClicked);
				menuButton.setEffect(image);
				checkDividerPosition();

			}
		});
		
		menuButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event)
			{
				image.setSource(standardButton);
				menuButton.setEffect(image);
			}
		});
		
		
		
	}
	
	public void checkDividerPosition()
	{
		DoubleProperty doubleProperty = navMenuPane.getDividers().get(0).positionProperty();
		System.out.println(doubleProperty);
		
		if(doubleProperty.getValue() == 0.0036144578313253013)
		{
			openMenu();
		}
		else if(doubleProperty.getValue() > 0.0036144578313253013)
		{
			closeMenu();
		}
		
	}
	
	public void openMenu()
	{
		DoubleProperty doubleProperty = navMenuPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(1000), doubleProperty);
		dt.setToValue(0.1469); dt.play();
			
	}
	
	public void closeMenu()
	{
		DoubleProperty doubleProperty = navMenuPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(1000), doubleProperty);
		dt.setToValue(0); dt.play();
	
	}
	

}
