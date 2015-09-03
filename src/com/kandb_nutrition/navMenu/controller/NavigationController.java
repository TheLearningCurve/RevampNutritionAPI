package com.kandb_nutrition.navMenu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.searchFeature.controllers.FrameController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class NavigationController extends AnchorPane implements Initializable{
	
	public static NavigationController controller;
	
	@FXML
	HBox SearchIconContainer, macroCalculatorContainer, FitTrackerContainer;
	
	@FXML
	ImageView searchImageView, macorCalcImageView, fitTrackerImageView;
	
	@FXML
	Label SearchIconLabel, MacroCalcLabel, FitTrackerLabel;
	
	public Strings string;
	
	public Image SearchactiveImage;
	public Image SearchstandardImage;
	
	public Image MacroactiveImage;
	public Image MacrostandardImage;
	
	public Image FitactiveImage;
	public Image FitstandardImage;

	
	
	public NavigationController() {

		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getNavigationMenu_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (NavigationController) fxmlLoader.getController();
			
		try
		{
			fxmlLoader.load();
			SearchactiveImage = new Image(string.getSearchActive_Image());
			SearchstandardImage = new Image(string.getSearchStandard_Image());
			
			MacroactiveImage = new Image(string.getMacroActive_Image());
			MacrostandardImage = new Image(string.getMacroStandard_Image());
			
			FitactiveImage = new Image(string.getFitActive_Image());
			FitstandardImage = new Image(string.getFitStandard_Image());

		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@FXML
	public void changeToIconActive(MouseEvent mouseevent) throws IOException
	{
		ScreenManager sm = ScreenManager.getInstance();

		
		if(mouseevent.getSource().equals(SearchIconContainer))
		{
			searchImageView.setImage(SearchactiveImage);
			SearchIconLabel.setStyle(string.getFx_text_fill_98FF42());
			sm.searchFeature();
			sm.resetSearchField();
			FrameController.controller.closeMenu();

		}
		else if(mouseevent.getSource().equals(macroCalculatorContainer))
		{
			macorCalcImageView.setImage(MacroactiveImage);
			MacroCalcLabel.setStyle(string.getFx_text_fill_98FF42());
			sm.macroCalc();
		}
		else if(mouseevent.getSource().equals(FitTrackerContainer))
		{
			fitTrackerImageView.setImage(FitactiveImage);
			FitTrackerLabel.setStyle(string.getFx_text_fill_98FF42());
			sm.fitTracker();
		}
	}

	@FXML
	public void changeToIconStandard(MouseEvent mouseevent) throws IOException
	{
		
		if(mouseevent.getSource().equals(SearchIconContainer))
		{
			searchImageView.setImage(SearchstandardImage);			
			SearchIconLabel.setStyle(string.getFx_text_fill_black());

		}
		else if(mouseevent.getSource().equals(macroCalculatorContainer))
		{
			macorCalcImageView.setImage(MacrostandardImage);
			MacroCalcLabel.setStyle(string.getFx_text_fill_black());

		}
		else if(mouseevent.getSource().equals(FitTrackerContainer))
		{
			fitTrackerImageView.setImage(FitstandardImage);
			FitTrackerLabel.setStyle(string.getFx_text_fill_black());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
