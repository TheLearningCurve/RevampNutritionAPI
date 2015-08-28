package fitTracker.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import searchFeature.nutritionAPIV2_controllers.DoubleTransition;
import searchFeature.nutritionAPIV2_controllers.SearchFieldFrame;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.*;

public class FitTrack_FrameController extends AnchorPane implements Initializable{
	
		@FXML
		private AnchorPane mainPanel;
		
		/* TopPanel Variables */
		
		@FXML
		Pane dimPane, Pane_Controller_Container, NutritionLabel_DimPane;
		
		@FXML
		Button menuButton;
		
		@FXML
		SplitPane navMenuPane;
		
		@FXML
		AnchorPane navMenu, LeftandRightAnchor;
		
		@FXML
		HBox LeftAndRightPanel;
		
		@FXML
		ProgressIndicator ItemListProgressIndicator;
		
		@FXML
		ImageView LargeLogo;
		
		public double opacity;
		
		public static FitTrack_FrameController controller; 
		
		public Image standardButton = new Image("searchFeature/resources/menuButton.png");
		public Image buttonClicked = new Image("searchFeature/resources/menuButtonClicked.png");
		public Image BackButton = new Image("searchFeature/resources/MenuBackButton.png");
		ImageInput image = new ImageInput();
		public final double imageX = image.getX();
		public final double imageY = image.getY();
		
		public boolean open = false;
		
		
		public FitTrack_FrameController()
		{
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fitTracker/fitracker_view/Tracker_Frame.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
			controller = (FitTrack_FrameController) fxmlLoader.getController();

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
					if(open == false)
					{
						image.setSource(BackButton);
						image.setX(8.0);
						image.setY(4.0);
						
					}
					else 
					{
						image.setSource(standardButton);
						image.setX(imageX);
						image.setY(imageY);
					}
					
					menuButton.setEffect(image);
					checkDividerPosition();
				}
			});		
		}
		
		public void checkDividerPosition()
		{		
			if(open == false)
			{
				open = true;
				openMenu();		
			}
			else if(open == true)
			{
				closeMenu();
				open = false;
			}
		}
		
		public void openMenu()
		{				
			DoubleProperty doubleProperty = navMenuPane.getDividers().get(0).positionProperty();
			DoubleTransition dt = new DoubleTransition(Duration.millis(1000), doubleProperty);
			dt.setToValue(0.19); dt.play();	
			
			FadeTransition ft = new FadeTransition(Duration.millis(1000), dimPane);
			ft.setFromValue(0.0);
			ft.setToValue(.45);
			ft.play();		
			
			ft.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					dimPane.setMouseTransparent(false);
				}
				
			});
			
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
					SearchFieldFrame.controller.changeSearchFieldImageStandard();
					dimPane.setMouseTransparent(true);
				}
			});
		}
		
		public void keepMenuOpen()
		{
			navMenuPane.setDividerPositions(0.19);
			dimPane.toFront();
			dimPane.setOpacity(.45);
			open = true;
		}
		
		public void dim_Pane_ContainerSetOpacity()
		{
			NutritionLabel_DimPane.setOpacity(.50);
		}
		
		public void dim_Pane_Container_SetOpacityZero()
		{
			NutritionLabel_DimPane.setOpacity(0);
		}
		
		public void set_LargeLogo_Visible()
		{
			LargeLogo.setVisible(true);
		}
		
		public void set_LargeLogo_Non_Visible()
		{
			LargeLogo.setVisible(false);
		}
	}


