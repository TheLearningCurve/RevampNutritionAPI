package com.kandb_nutrition.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.resource.Strings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;



public class SecondPanelController extends Pane implements Initializable
{
	public static SecondPanelController controller;
	
	private double width;
	private double height;
	
	public Strings string;
	
	public SecondPanelController(double width, double height)
	{
		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getSecondPanel_fxml()));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (SecondPanelController) fxmlLoader.getController();
		
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
