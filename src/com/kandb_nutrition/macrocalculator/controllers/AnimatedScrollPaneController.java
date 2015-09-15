package com.kandb_nutrition.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.resource.Strings;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;


public class AnimatedScrollPaneController extends ScrollPane implements Initializable
{
	public static AnimatedScrollPaneController controller;
	
	private double width;
	private double height;
	public Strings string;
	
	public AnimatedScrollPaneController(double width, double height)
	{
		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getAnimatedScrollPane_fxml()));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (AnimatedScrollPaneController) fxmlLoader.getController();
		
		this.width = width;
		this.height = height;
		
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
		setPrefWidth(width);
		setPrefHeight(height);
	}
}
