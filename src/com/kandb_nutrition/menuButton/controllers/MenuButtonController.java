package com.kandb_nutrition.menuButton.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuButtonController extends AnchorPane implements Initializable {

	public Strings strings;
	private ScreenManager sm;
	
	@FXML
	Button menuButton;
	
	public MenuButtonController()
	{
		strings = new Strings();
		sm = ScreenManager.getInstance();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(strings.getMenuButton_fxml()));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

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
	public void initialize(URL arg0, ResourceBundle arg1) {

		menuButton.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(sm.isSearchFeature()) 
				{
					sm.getFrameController().checkDividerPosition();
				}
				else if(sm.isFitTracker())
				{
					sm.getFitTrack_FrameController().checkDividerPosition();
				}
			}
		});
	}
}
