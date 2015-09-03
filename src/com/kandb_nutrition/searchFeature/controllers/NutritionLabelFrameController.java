package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import resource.Strings;
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
import leHTML.HTMLBuilder;

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
	public Image close_Black = new Image("searchFeature/resources/close_Black.png");
	public Image close_White = new Image("searchFeature/resources/close_White.png");
	public Strings strings;

	public NutritionLabelFrameController()
	{
		strings = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(strings.getNutritionLabel_fxml()));
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
				//FrameBottomLeftController.controller.clearItemOpacity();	
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
				Close_Icon.setImage(close_Black);
			}
		});
		
		Close_Icon.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				Close_Icon.setImage(close_White);
				NutritionLabelFrameController.controller.setVisible(false);
				FrameController.controller.dim_Pane_Container_SetOpacityZero();
			}
		});
	}
}
