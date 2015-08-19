package com.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class MainController extends AnchorPane implements Initializable
{
	public static MainController controller;
	
	ScrollPane sbScrollBarOne;
	
	Button btnNext;
	Button btnPrevious;
	
	int animationDurationMillis = 1000;
	double animationDurationSecs = (double)animationDurationMillis / 1000.0;
	
	double timeElapsedMillis;
	double currentScrollPos = 0.0;
	
	int pageNumber = 0;		//	Starting from zero
	int numberOfPages = 3;	//	Starting from zero
	
	public MainController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/macrocalculator/fxml/MainFrame.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (MainController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
		StackPane stackPane = new StackPane();
		HBox navBarHBox = new HBox();
		
		btnNext = new NextButtonController();
		btnPrevious = new PreviousButtonController();
		
		navBarHBox.getChildren().add(btnPrevious);
		navBarHBox.getChildren().add(new PageNumberBarController());
		navBarHBox.getChildren().add(btnNext);
		
		sbScrollBarOne = new AnimatedScrollPaneController(this.getPrefWidth(), this.getPrefHeight());
		
		stackPane.getChildren().add(sbScrollBarOne);
		stackPane.getChildren().add(navBarHBox);
		
		this.getChildren().add(stackPane);
		
		double scrollPaneWidth = sbScrollBarOne.getPrefWidth() - 2;
		double scrollPaneHeight = sbScrollBarOne.getPrefHeight() - 2;
		
		//	Used for math, change 'numberOfPanes' if you add another pane.
		int numberOfPanes = 4;
		double multiplier = (1.0 / (double)(numberOfPanes - 1));
		
		EquationCreator equation = new EquationCreator(animationDurationMillis, multiplier);
		
		Timeline timelineNext = new Timeline();
		Timeline timelinePrevious = new Timeline();
		
		KeyFrame nextKeyFrame = new KeyFrame(Duration.millis(10.0), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				double tempPos = (currentScrollPos + equation.getYMultiplierInverse(timeElapsedMillis));
				
				if (tempPos <= (multiplier * (double)pageNumber))
				{
					sbScrollBarOne.setHvalue(tempPos);
					timeElapsedMillis += 10.0;
				}
				
				else
				{
					timelineNext.stop();
					sbScrollBarOne.setHvalue((double)pageNumber * multiplier);
					setCurrentScrollPos(sbScrollBarOne.getHvalue());
				}
			}
		});
		
		KeyFrame previousKeyFrame = new KeyFrame(Duration.millis(10.0), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				double tempPos = (currentScrollPos - equation.getYMultiplierInverse(timeElapsedMillis));
				
				if (tempPos >= (multiplier * (double)pageNumber))
				{
					sbScrollBarOne.setHvalue(tempPos);
					timeElapsedMillis += 10.0;
				}
				
				else
				{
					timelinePrevious.stop();
					sbScrollBarOne.setHvalue((double)pageNumber * multiplier);
					setCurrentScrollPos(tempPos);
					
				}
			}
		});
		
		timelineNext.setCycleCount(Animation.INDEFINITE);
		timelineNext.getKeyFrames().add(nextKeyFrame);
    	timelinePrevious.setCycleCount(Animation.INDEFINITE);
    	timelinePrevious.getKeyFrames().add(previousKeyFrame);
		
		FirstPanelController firstPanelController = new FirstPanelController(scrollPaneWidth, scrollPaneHeight);
		SecondPanelController secondPanelController = new SecondPanelController(scrollPaneWidth, scrollPaneHeight);
		ThirdPanelController thirdPanelController = new ThirdPanelController(scrollPaneWidth, scrollPaneHeight);
		FourthPanelController fourthPanelController = new FourthPanelController(scrollPaneWidth, scrollPaneHeight);
		
		HBox hBox = new HBox();
		hBox.getChildren().add(firstPanelController);
		hBox.getChildren().add(secondPanelController);
		hBox.getChildren().add(thirdPanelController);
		hBox.getChildren().add(fourthPanelController);
		
		sbScrollBarOne.setContent(hBox);
		
		btnPrevious.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) 
		    {
		    	timelinePrevious.stop();
		    	timelineNext.stop();
		    	
		    	timeElapsedMillis = 0.0;
		    	
		    	sbScrollBarOne.setHvalue(pageNumber * multiplier);
		    	
		    	currentScrollPos = sbScrollBarOne.getHvalue();
		    	
		    	decrementPageNumber();
		    	
		    	timelinePrevious.play();
		    }
		});
		
		btnNext.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent)
			{
				timelineNext.stop();
				timelinePrevious.stop();
				
				timeElapsedMillis = 0.0;
				
				sbScrollBarOne.setHvalue(pageNumber * multiplier);
				
				currentScrollPos = sbScrollBarOne.getHvalue();
				
				incrementPageNumber();
				
				timelineNext.play();
			}
		});
		
	}
	
	private void setCurrentScrollPos(double scrollPos)
	{
		currentScrollPos = scrollPos;
	}
	
	private void incrementPageNumber()
	{
		if (!(pageNumber + 1 > numberOfPages))
			pageNumber++;
		
		System.out.println(pageNumber);
	}
	
	private void decrementPageNumber()
	{
		if (!(pageNumber - 1 < 0))
			pageNumber--;
		
		System.out.println(pageNumber);
	}
}
