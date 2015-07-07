package macroCalculator.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import searchFeature.nutritionAPIV2_controllers.DoubleTransition;
import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MacroFrameController extends AnchorPane implements Initializable {
	
	public static MacroFrameController controller; 
	
	@FXML
	Pane dimPane;
	
	@FXML
	Button menuButton;
	
	@FXML
	SplitPane navMenuPane;
	
	@FXML
	AnchorPane navMenu;
	
	public double opacity;
	
	public Image standardButton = new Image("/searchFeature/resources/menuButton.png");
	public Image buttonClicked = new Image("/searchFeature/resources/menuButtonClicked.png");
	ImageInput image = new ImageInput();

	
	public MacroFrameController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/macroCalculator/view/MacroCalculatorFrame.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (MacroFrameController) fxmlLoader.getController();
			
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		opacity = menuButton.getOpacity();
		
		navMenu.setMaxWidth(158);
		
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
		
		dimPane.toFront();
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), dimPane);
		ft.setFromValue(0.0);
		ft.setToValue(.45);
		ft.play();
		
	}
	
	public void closeMenu()
	{
		DoubleProperty doubleProperty = navMenuPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(1000), doubleProperty);
		dt.setToValue(0); dt.play();
				
		FadeTransition ft = new FadeTransition(Duration.millis(1000), dimPane);
		ft.setFromValue(.45);
		ft.setToValue(0.0);
		ft.play();
		
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				dimPane.toBack();
				
			}
		});
	}
	
	

}
