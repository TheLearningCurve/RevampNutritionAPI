package navMenu.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Manager.ScreenManager;
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
	
	
	public Image SearchactiveImage = new Image("navMenu/resource/searchActiveIcon.png");
	public Image SearchstandardImage = new Image("navMenu/resource/searchIcon.png");
	
	public Image MacroactiveImage = new Image("navMenu/resource/macroActiveIcon.png");
	public Image MacrostandardImage = new Image("navMenu/resource/macroIcon.png");
	
	public Image FitactiveImage = new Image("navMenu/resource/fitTrackerActiveIcon.png");
	public Image FitstandardImage = new Image("navMenu/resource/fitTrackerIcon.png");

	
	
	public NavigationController() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/navMenu/view/NavigationMenu.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (NavigationController) fxmlLoader.getController();
			
		try
		{
			fxmlLoader.load();
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
			SearchIconLabel.setStyle("-fx-text-fill: #98FF42");
			sm.searchFeature();

		}
		else if(mouseevent.getSource().equals(macroCalculatorContainer))
		{
			macorCalcImageView.setImage(MacroactiveImage);
			MacroCalcLabel.setStyle("-fx-text-fill: #98FF42");
			sm.macroCalc();
		}
		else if(mouseevent.getSource().equals(FitTrackerContainer))
		{
			fitTrackerImageView.setImage(FitactiveImage);
			FitTrackerLabel.setStyle("-fx-text-fill: #98FF42");
			sm.fitTracker();
		}
	}

	@FXML
	public void changeToIconStandard(MouseEvent mouseevent) throws IOException
	{
		
		if(mouseevent.getSource().equals(SearchIconContainer))
		{
			searchImageView.setImage(SearchstandardImage);			
			SearchIconLabel.setStyle("-fx-text-fill: black");

		}
		else if(mouseevent.getSource().equals(macroCalculatorContainer))
		{
			macorCalcImageView.setImage(MacrostandardImage);
			MacroCalcLabel.setStyle("-fx-text-fill: black");

		}
		else if(mouseevent.getSource().equals(FitTrackerContainer))
		{
			fitTrackerImageView.setImage(FitstandardImage);
			FitTrackerLabel.setStyle("-fx-text-fill: black");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
