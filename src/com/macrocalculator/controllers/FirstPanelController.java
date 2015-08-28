package com.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;



public class FirstPanelController extends Pane implements Initializable
{
	public static FirstPanelController controller;
	
	private double width;
	private double height;
	
	public FirstPanelController(double width, double height)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/macrocalculator/fxml/FirstPanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (FirstPanelController) fxmlLoader.getController();
		
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