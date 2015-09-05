package com.kandb_nutrition.searchFeature.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.resource.Strings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SingleLabelController extends AnchorPane implements Initializable {
	
	public Strings strings;
	
	@FXML
	Label label;
	
	public SingleLabelController(String x)
	{
		strings = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(strings.getSingleTextCell_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		label.setText(x);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

}
