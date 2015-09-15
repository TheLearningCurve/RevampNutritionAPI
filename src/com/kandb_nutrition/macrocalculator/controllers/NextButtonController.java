package com.kandb_nutrition.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.resource.Strings;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;



public class NextButtonController extends Button implements Initializable
{
	public static NextButtonController controller;
	
	public Strings string;
	
	public NextButtonController()
	{
		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getButtonNext_fxml()));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (NextButtonController) fxmlLoader.getController();
		
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
		
	}
}
