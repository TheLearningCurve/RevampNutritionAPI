package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
	
	@FXML
	Label label;
	
	public SingleLabelController(String x)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/searchFeature/nutritionAPIV2_view/SingleTextCell.fxml"));
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
