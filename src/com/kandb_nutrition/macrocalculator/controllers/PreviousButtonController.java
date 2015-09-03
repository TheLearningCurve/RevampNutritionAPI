package com.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;



public class PreviousButtonController extends Button implements Initializable
{
	public static PreviousButtonController controller;
	
	public PreviousButtonController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/macrocalculator/fxml/ButtonPrevious.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (PreviousButtonController) fxmlLoader.getController();
		
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
