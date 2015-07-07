package searchFeature.nutritionAPIV2_controllers;

import com.sun.javafx.css.Style;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ItemButton extends StackPane
{
	private String brandLogoUrl;
	private String brandName;
	private String itemName;
	private boolean pressed = false;
	private int number;
	
	static Image normalButton = new Image(ItemButton.class.getResource("/searchFeature/resources/ItemContainer.jpg").toString());
	static Image hoverButton = new Image(ItemButton.class.getResource("/searchFeature/resources/ItemHover.jpg").toString());
	static Image clickButton = new Image(ItemButton.class.getResource("/searchFeature/resources/ItemContainer.jpg").toString());
	
	private ImageView backgroundView = new ImageView();
	
	public ItemButton(int buttonNumber, String brandNameIn, String itemNameIn, String thumbnail) // Instantiate after properties are set (?).
	{
		String bevelStyle = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6) ,linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),"
				+ "linear-gradient(#dddddd 0%, #f6f6f6 50%); "
				+ "-fx-background-radius: 8,7,6;"
				+ "-fx-background-insets: 0,1,2;"
				+ "-fx-text-fill: black;"
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ) ";
		
		this.number = buttonNumber;
		this.brandName = brandNameIn;
		this.itemName = itemNameIn;
		this.brandLogoUrl = thumbnail;
		
		backgroundView.setImage(normalButton);
			
		Label brandNameLabel = new Label("from " +brandName);
		Text itemNameLabel = new Text(itemName);
		
		Image image = new Image(brandLogoUrl);
		
		Insets insets = new Insets(25);
		
		VBox vbox = new VBox();
		vbox.setPadding(insets);
		vbox.setAlignment(Pos.CENTER_RIGHT);
		vbox.spacingProperty().add(2);
		
	
		
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(75); 
		imageView.setFitHeight(65);
		imageView.setPreserveRatio(true);
		
		Insets inset = new Insets(20);
		
		HBox hbox = new HBox(1);
		hbox.setPadding(inset);
		
				
		hbox.getChildren().add(imageView);
		vbox.getChildren().add(itemNameLabel);
		

		if(itemNameLabel.getText().length() > 30)
		{
			itemNameLabel.setWrappingWidth(150);
		}
		
		
		if(brandName.length() > 48)
		{
			Label newBrandNameLabel = new Label("from " + brandName.substring(0, 36) + "...");
			vbox.getChildren().add(newBrandNameLabel);		
		}
		else 
		{
			vbox.getChildren().add(brandNameLabel);
		}
		
	
		this.getChildren().add(backgroundView);
		this.getChildren().add(vbox);
		this.getChildren().add(hbox);

		
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0)
			{
						
				if (pressed == false)
				{
					backgroundView.setImage(normalButton);
					pressed = true;
				}					
				
				if (pressed == true)
				{
					
					if (FrameBottomLeftController.buttonNumberPressed != number && FrameBottomLeftController.buttonNumberPressed != -1)
					{
						FrameBottomLeftController.buttons.get(FrameBottomLeftController.buttonNumberPressed).setButtonBackgound(normalButton);
					}
				
					
					FrameBottomLeftController.buttonNumberPressed = number;
					FrameBottomLeftController.buttons.get(FrameBottomLeftController.buttonNumberPressed).backgroundView.setEffect(null);
					FrameBottomLeftController.buttons.get(FrameBottomLeftController.buttonNumberPressed).backgroundView.setStyle(bevelStyle);
							
					pressed = false;
					
					for(int i = 0; i < FrameBottomLeftController.buttons.size(); i++)
					{
						
							FrameBottomLeftController.buttons.get(i).setOpacity(.35);
							FrameBottomLeftController.buttons.get(FrameBottomLeftController.buttonNumberPressed).setOpacity(1);
						
					}
				}
			}
		});
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				for(int i = 0; i < FrameBottomLeftController.buttons.size(); i++)
				{
					if(FrameBottomLeftController.buttons.get(i).getOpacity() < 1)
					{
						backgroundView.setEffect(null);
					}
					else
					{
						if (pressed == false)
						{
							backgroundView.setEffect(new DropShadow(3d, 2d, 8d, Color.BLACK));
						}
					}
					
				}
				
			}
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				if (pressed == false)
				{
					backgroundView.setEffect(null);
				}
				
			}
		});
	}
	
	public void setButtonBackgound(Image image)
	{
		backgroundView.setImage(image);
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public String getBrandName()
	{
		return brandName;
	}

	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}

	public String getBrandLogoUrl()
	{
		return brandLogoUrl;
	}

	public void setBrandLogoUrl(String brandLogoUrl)
	{
		this.brandLogoUrl = brandLogoUrl;
	}
}
