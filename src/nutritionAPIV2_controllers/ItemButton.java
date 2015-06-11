package nutritionAPIV2_controllers;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

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
	
	public ItemButton(int buttonNumber, String brandNameIn, String itemNameIn) // Instantiate after properties are set (?).
	{
		this.number = buttonNumber;
		this.brandName = brandNameIn;
		this.itemName = itemNameIn;
		
		backgroundView.setImage(normalButton);
		
		Label buttonText = new Label(brandName + " " + itemName);
		this.getChildren().add(backgroundView);
		
		if((buttonText.getText().length()) > 41)
		{	
			Label newText = new Label(buttonText.getText().substring(0, 35).trim() + "...");		
			this.getChildren().add(newText);
		}
		else
		{
			this.getChildren().add(buttonText);
		}
		
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
