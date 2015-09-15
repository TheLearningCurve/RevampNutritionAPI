package com.kandb_nutrition.fitTracker.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.searchFeature.controllers.DoubleTransition;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
				
		public Image standardButton;
		public Image buttonClicked;
		public Image backButton;
		public ImageInput image;
		public Strings string;
		public ScreenManager sm;
		public final double imageX;
		public final double imageY;
		
		public boolean open = false;
		
		
		public FitTrack_FrameController()
		{
			string = new Strings();
			sm = ScreenManager.getInstance();
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getFitTracker_fxml()));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);

			try
			{
				fxmlLoader.load();
				standardButton = new Image(string.getStandardButton_Image());
				buttonClicked = new Image(string.getButtonClicked_Image());
				backButton = new Image(string.getBackButton_Image());
				
				image = new ImageInput();
				imageX = image.getX();
				imageY = image.getY();
			}
			
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) 
		{	
			navMenu.setMaxWidth(158);
			
			
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
					open = true;
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
					
					dimPane.setMouseTransparent(true);
					open = false;
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

		public void resetFitTrackScene() {

		}

	}


