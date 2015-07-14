package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import searchFeature.nutritionAPIV2_model.FoodItem;
import searchFeature.nutritionAPIV2_model.ItemData;
import searchFeature.nutritionAPIV2_model.Nutrients;
import searchFeature.nutritionAPIV2_service.Adapter;
import searchFeature.nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import leHTML.HTMLBuilder;

class ExpandedLabelController extends AnchorPane implements Initializable {

	public static ExpandedLabelController controller;
	
	@FXML
	Label calorieLabel;
	
	@FXML
	Label ItemNameLabel;
	
	@FXML
	Label BrandNameLabel;
	
	@FXML
	ImageView Thumbnail;
	
	@FXML
	ImageView NutritionLabelIcon;
	
	public Tooltip tooltip = new Tooltip();
    public Adapter adapter = new Adapter();
	
	public ExpandedLabelController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/searchFeature/nutritionAPIV2_view/ExpandableTextCell.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (ExpandedLabelController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		ItemNameLabel.setTooltip(tooltip);
		
		NutritionLabelIcon.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mousevent) {
				
				NutritionLabelFrameController.controller.setVisible(true);
				SearchListFrameController.controller.dimPane.setVisible(true);
				SearchListFrameController.controller.dimPane.setOpacity(.50);
				
			}
		});
		
	}
	
	public void updateExpandableCellData(String ItemNameLabel, String BrandNameLabel, String nutrientName, Float calorieLabel, String thumbNail, String resourceId)
	{
		// Making the call to get the Item Label so the label is populated quicker
		QueryVariables.setItemId(resourceId);
		getItem();
		 
		createHtml();
		
		this.calorieLabel.setText(String.valueOf(nutrientName + " " + calorieLabel.intValue()));
		this.ItemNameLabel.setText(ItemNameLabel);
		this.BrandNameLabel.setText("From " + BrandNameLabel);
				
		
		Image i = new Image(thumbNail);
		if(i.exceptionProperty().getValue() == null)
		{
			Thumbnail.setImage(i);
		}
		
		tooltip.setText(ItemNameLabel);
		
	}
	
	public void getItem()
	{
		adapter.getapicalls.itemFacts(QueryVariables.itemId, new Callback<ItemData>() {

			@Override
			public void success(ItemData itemData, Response response)
			{
				if(itemData.label.nutrients != null)
				{
					for(Nutrients n : itemData.label.nutrients)
					{
						System.out.println(n.name + ": " + n.value + n.unit);
					}
				}			
			}

			@Override
			public void failure(RetrofitError retrofitError)
			{
				System.out.println(retrofitError.getMessage());	
			}
		});	
	}
	
	public void createHtml()
	{
		Platform.runLater(new Runnable() {
			@Override public void run() 
			{
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
				
				NutritionLabelFrameController.controller.sendHtml(html);
			}
		});
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
}


