package nutritionAPIV2_controllers;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ItemButton extends StackPane
{
	private String brandLogoUrl;
	private String brandName;
	private String itemName;
	private boolean pressed = false;
	private int number;
	
	static Image normalButton = new Image(ItemButton.class.getResource("/resources/LeftPanelItemButtons_Normal.png").toString());
	static Image hoverButton = new Image(ItemButton.class.getResource("/resources/LeftPanelItemButtons_Hover.png").toString());
	static Image clickButton = new Image(ItemButton.class.getResource("/resources/LeftPanelItemButtons_Click.png").toString());
	
	private ImageView backgroundView = new ImageView();
	
	public ItemButton(int buttonNumber, String brandNameIn, String itemNameIn, String thumbnail) // Instantiate after properties are set (?).
	{
		this.number = buttonNumber;
		this.brandName = brandNameIn;
		this.itemName = itemNameIn;
		this.brandLogoUrl = thumbnail;
		
		
		backgroundView.setImage(normalButton);
		
		Label brandNameLabel = new Label("from " +brandName);
		Label itemNameLabel = new Label(itemName);
		brandNameLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 11.0px; -fx-text-fill: gray;"
				+ "-fx-font-style: italic");
		
		Image image = new Image(brandLogoUrl);
		
		VBox vbox = new VBox();
		vbox.alignmentProperty().set(Pos.CENTER_RIGHT);
		
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(256 / 2);
		imageView.setFitHeight(50);
		imageView.setPreserveRatio(true);
		
		HBox hbox = new HBox();
		HBox.setHgrow(imageView, Priority.ALWAYS);
		
		hbox.getChildren().add(imageView);
		
		if(itemName.length() > 48)
		{
			Label newItemNameLabel = new Label(itemName.substring(0, 36) + "...");
			vbox.getChildren().add(newItemNameLabel);
		}
		else
		{
			vbox.getChildren().add(itemNameLabel);
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
				if (pressed)
				{
					backgroundView.setImage(normalButton);
					pressed = false;
				}					
				
				if (!pressed)
				{
					backgroundView.setImage(clickButton);
					
					if (FrameBottomLeftController.buttonNumberPressed != number && FrameBottomLeftController.buttonNumberPressed != -1)
					{
						FrameBottomLeftController.buttons.get(FrameBottomLeftController.buttonNumberPressed).setButtonBackgound(normalButton);
					}
						
					FrameBottomLeftController.buttonNumberPressed = number;
					
					pressed = true;
				}
			}
		});
		
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				if (!pressed)
					backgroundView.setImage(hoverButton);
			}
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				if (!pressed)
					backgroundView.setImage(normalButton);
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
