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
	
	public ItemButton(int buttonNumber) // Instantiate after properties are set (?).
	{
		this.number = buttonNumber;
		
		backgroundView.setImage(normalButton);
		
		Label buttonText = new Label(brandName + " " + itemName);	//	Just for demonstrations
		
		this.getChildren().add(backgroundView);
		this.getChildren().add(buttonText);
		
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
					
					if (FrameController.buttonNumberPressed != number && FrameController.buttonNumberPressed != -1)
					{
						FrameController.buttons.get(FrameController.buttonNumberPressed).setButtonBackgound(normalButton);
					}
						
					FrameController.buttonNumberPressed = number;
					
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