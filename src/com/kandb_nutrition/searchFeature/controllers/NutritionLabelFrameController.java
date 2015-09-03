package com.kandb_nutrition.searchFeature.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.leHTML.HTMLBuilder;
import com.kandb_nutrition.resource.Strings;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NutritionLabelFrameController extends AnchorPane implements Initializable
{
	
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;
	
	@FXML
	ImageView Close_Icon;
	
	public static NutritionLabelFrameController controller;
	public WebEngine engine;
	public Image xButton_black;
	public Image xButton_white;
	public Strings string;

	public NutritionLabelFrameController()
	{
		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getNutritionLabel_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (NutritionLabelFrameController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
			xButton_black = new Image(string.getxButton_Black_Image());
			xButton_white = new Image(string.getxButton_White_Image());

		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}	
		
		engine = webViewControl.getEngine();	
	}
	
	public void sendHtml(HTMLBuilder html)
	{
		engine.loadContent(html.getHTMLString());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
		rightPanel.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}
		});
		
		webViewControl.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}
		});
		
		Close_Icon.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				Close_Icon.setImage(xButton_black);
			}
		});
		
		Close_Icon.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				Close_Icon.setImage(xButton_white);
				NutritionLabelFrameController.controller.setVisible(false);
				FrameController.controller.dim_Pane_Container_SetOpacityZero();
			}
		});
	}
}
