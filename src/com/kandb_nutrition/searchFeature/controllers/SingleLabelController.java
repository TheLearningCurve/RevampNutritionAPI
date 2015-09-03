package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import resource.Strings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SingleLabelController extends AnchorPane implements Initializable{
	
	public static SingleLabelController controller;
	public Strings strings;
	
	@FXML
	Label label;
	
	public SingleLabelController(String x)
	{
		strings = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(strings.getSingleTextCell_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (SingleLabelController) fxmlLoader.getController();
		
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
