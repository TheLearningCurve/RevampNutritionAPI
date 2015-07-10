package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import leHTML.HTMLBuilder;

public class FrameBottomRightController extends AnchorPane implements Initializable
{
	
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;
	
	public static FrameBottomRightController controller;
	
	public FrameBottomRightController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/searchFeature/nutritionAPIV2_view/FrameBottomRight.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (FrameBottomRightController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		HTMLBuilder html = new HTMLBuilder();
		html.setTitle("Poop Sauce");
		html.setServing("1", "pizza", "853g");
		html.setBar1();
		html.setAmountPerServing();
		html.setCalories("2270", "740");
		html.setBar2();
		html.setDailyValue();
		html.setLineBold("Total fat", "83", "g", "128");
		html.setLineIndent("Saturated Fat", "83", "g", "190");
		
		html.finishDocument();
		
		WebEngine engine = webViewControl.getEngine();
		//engine.loadContent(html.getHTMLString());
		engine.load("https://jsfiddle.net/");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
		rightPanel.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				FrameBottomLeftController.controller.clearItemOpacity();	
			}
		});
		
		webViewControl.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				FrameBottomLeftController.controller.clearItemOpacity();	
			}
		});
		
		
	}
}
