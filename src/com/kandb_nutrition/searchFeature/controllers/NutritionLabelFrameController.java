package com.kandb_nutrition.searchFeature.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.leHTML.HTMLBuilder;
import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NutritionLabelFrameController extends AnchorPane implements Initializable {
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;
	
	@FXML
	Button close_icon;
	
	private WebEngine engine;
	private Strings string;
	private ScreenManager sm;
	
	public static NutritionLabelFrameController controller;

	public NutritionLabelFrameController()
	{
		string = new Strings();
		sm = ScreenManager.getInstance();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getNutritionLabel_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (NutritionLabelFrameController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}	
		
		engine = webViewControl.getEngine();	
		
		close_icon.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				sm.getNutritionLabelFrameController().setVisible(false);
				sm.getFrameController().dim_Pane_Container_SetOpacityZero();
			}
		});
	}
	
	public void sendHtml(HTMLBuilder html)
	{
		engine.loadContent(html.getHTMLString());
	}
	
	public NutritionLabelFrameController getController() {
		return controller;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
