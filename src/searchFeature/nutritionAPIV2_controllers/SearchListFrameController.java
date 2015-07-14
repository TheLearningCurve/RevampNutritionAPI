package searchFeature.nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.annotations.SerializedName;
import com.sun.prism.paint.Color;

import searchFeature.nutritionAPIV2_model.FoodItem;
import searchFeature.nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;


public class SearchListFrameController extends AnchorPane implements Initializable
{
	@FXML
	AnchorPane leftPanel;
	
	@FXML
	VBox ButtonListContainer;
	
	@FXML
	Label resultLabel;
	
	@FXML
	Button buttonLabel;
	
	@FXML 
	Pane dimPane;
	
	@FXML 
	ScrollPane ListContainerScrollPane;
	
	@FXML
	ImageView progressIndicatorImageView;
	
	public static SearchListFrameController controller;
	public int previousIndex = -1;
	public Node previousItem;

	
	public SearchListFrameController()
	{
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/searchFeature/nutritionAPIV2_view/SearchListFrame.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (SearchListFrameController) fxmlLoader.getController();
		
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		/*
		buttonList.vvalueProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue)
			{
			
				if(newValue.intValue() == 1)
				{
					finishedScroll();
				}
			}		
		});*/
		
		
		  
	}
	
	private void setItem(int previousIndex, Node previousItem) {
		ButtonListContainer.getChildren().set(previousIndex, previousItem);	
	}
	
	int getIndexFromEvent(MouseEvent event) {
	     return ButtonListContainer.getChildren().indexOf(event.getSource());
	}
	
	Node getItem(int index) {
		   return ButtonListContainer.getChildren().get(index);
	}
	
	public void createList(String itemname, String brandname, String nutrientName, float nValue, String nUoM, float sQty, String sUoM, String id, String thumbI)
	{	
		
		new Thread(()->{
		Platform.runLater(new Runnable()
		{
            @Override public void run() 
            {
	        	SingleLabelController singleLabelController = new SingleLabelController(itemname);
	    	    ExpandedLabelController expandedLabelController = new ExpandedLabelController();
        			
				ButtonListContainer.getChildren().add(singleLabelController);
				singleLabelController.setOnMousePressed(new EventHandler<MouseEvent>() {

        		    @Override
        		    public void handle(MouseEvent  event) {
        		    	
        		    	if(previousIndex != -1 ) 
        		        {
        		        	setItem( previousIndex, previousItem );
        		        }
        		    	
        		    	  int currentIndex = getIndexFromEvent(event);
        			      Node currentItem = getItem(currentIndex);
        			      
        			      expandedLabelController.updateExpandableCellData(itemname, brandname, nutrientName ,nValue,thumbI,id);
        			      setItem( currentIndex, expandedLabelController );

        		       previousIndex = currentIndex;
        		       previousItem = currentItem;     
        		       
        		       }
        		    });
        		   }
            });
			        try 
			        {
			        	Thread.sleep(1000);
					}
			        catch (Exception e) 
			        {
						e.printStackTrace();
					}
			        
		}).start();
	}
	
	public void setListContainerScrollPaneVisible()
	{
		ListContainerScrollPane.setVisible(true);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources){}
	
	
	/*
	public void finishedScroll() // Change to your liking.
	{
		if (buttonList.getVvalue() == 1)
		{
			QueryVariables.setOffset(10);
			FrameTopController.controller.requestSearchData();
		}
	}*/
	
	
	public void getResultLabel(int total, String searchTerm)
	{
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				DecimalFormat df = new DecimalFormat("#,###");

					resultLabel.setText(String.valueOf(df.format(total)) + " Results for " + searchTerm);
			}
		});
	}
	
	public void setprogressIndicatorImageViewVisible()
	{
		progressIndicatorImageView.setVisible(true);
	}
	
	public void setprogressIndicatorImageView_NotVisible()
	{
		progressIndicatorImageView.setVisible(false);
	}
}
