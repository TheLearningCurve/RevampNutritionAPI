package com.kandb_nutrition.macrocalculator.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.resource.Strings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class PageNumberBarController extends AnchorPane implements Initializable
{
	public static PageNumberBarController controller;
	
	@FXML
	AnchorPane ancBar;
	
	private double width;
	private double height;
	public Strings string;
	
	public PageNumberBarController()
	{
		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getPageNumberBar_fxml()));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		controller = (PageNumberBarController) fxmlLoader.getController();
		
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
