package com.kandb_nutrition.searchFeature.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.searchFeature.model.Results;
import com.kandb_nutrition.searchFeature.model.SearchData;
import com.kandb_nutrition.searchFeature.model.TypeAHead;
import com.kandb_nutrition.searchFeature.service.Adapter;
import com.kandb_nutrition.searchFeature.service.QueryVariables;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SearchFieldFrame extends AnchorPane implements Initializable
{
	@FXML
	TextField searchField, SearchField_ErrorMessgae;	
	
	@FXML
	ListView<String> listView;
	
	@FXML
	ImageView ImageButton;
	
	@FXML
	HBox HBoxContainerForListView;
	
	public ObservableList<String> typeaHeadtext = FXCollections.observableArrayList();
    public Adapter adapter = new Adapter();
    public static SearchFieldFrame controller;
    public int buttonPress = 0;
    public String searchFieldText = "Empty String";
    public Strings string;
    Image SearchButtonClearImage;
    Image SearchButtonStandardImage;

	
	public SearchFieldFrame()
	{
		string = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getSearchFieldFrame_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (SearchFieldFrame) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
			SearchButtonClearImage = new Image(string.getSearchButtonClear_Image());
			SearchButtonStandardImage = new Image(string.getSearchButtonStandard_Image());
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		
		/*This is the Key Listener for the search field*/
		searchField.setOnKeyReleased(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{	
				if(event.getCode() != KeyCode.ENTER)
				{			
					if(searchField.getText().length() != 0 && !searchField.getText().trim().isEmpty()) // If the text is at least on Char long we want to search for type a head
					{
						QueryVariables.setText(searchField.getText());
						requestTypeAHeadData();
						SearchField_ErrorMessgae.setVisible(false);			
					}
					else if(searchField.getText().length() == 0) // If the text in the search field is empty we do not want the listView to display
					{
						setListViewVisibleFalse(); 
					}
				}
				else if(event.getCode() == KeyCode.ENTER)
				{
					if(searchField.getText().length() != 0 && !searchField.getText().trim().isEmpty()) // If the text is at least on Char long we want to search for type a head
					{
						searchingLogicForSearchField();
					    // If the enter key is pressed we want to search for Item Results
					}
					else
					{
						SearchField_ErrorMessgae.setVisible(true);
					}
				}
		    } 
	    });	
	}

	@FXML 
	public void searchMouseListener(MouseEvent event)
	{

		if(event.getSource().equals(listView))
		{
			QueryVariables.setSearchTerm(listView.getSelectionModel().getSelectedItem());
			searchField.setText(listView.getSelectionModel().getSelectedItem());
			
			if(SearchListFrameController.controller.ButtonListContainer.getChildren().isEmpty() && searchField.getText().length() != 0 && !searchField.getText().trim().isEmpty())
			{
				FirstSearchCall();
			}
			else if(!SearchListFrameController.controller.ButtonListContainer.getChildren().isEmpty() && 
					listView.getSelectionModel().getSelectedItem().compareTo(searchFieldText) != 0)
			{
				ClearListSearchCall();
			}
			else if(listView.getSelectionModel().getSelectedItem().compareTo(searchFieldText) == 0)
			{
				setListViewVisibleFalse();
			}
		}
		else if(event.getSource().equals(ImageButton))
		{
	       searchingLogicForSearchField();
		}	
	}
	
	public void searchingLogicForSearchField()
	{
		QueryVariables.setSearchTerm(searchField.getText());
		
		if(SearchListFrameController.controller.ButtonListContainer.getChildren().isEmpty() && searchField.getText().length() != 0 && !searchField.getText().trim().isEmpty())
		{
			FirstSearchCall();
		}
		else if(!SearchListFrameController.controller.ButtonListContainer.getChildren().isEmpty() && searchField.getText().compareTo(searchFieldText) != 0
				&& searchField.getText().length() != 0 && !searchField.getText().trim().isEmpty())
		{
			ClearListSearchCall();

		}
		else if(searchField.getText().compareTo(searchFieldText) == 0)
		{
			setListViewVisibleFalse();
		}	
		else
		{
			SearchField_ErrorMessgae.setVisible(true);
		}
	}
	
	public void FirstSearchCall()
	{
		SearchListFrameController.controller.setErrorMessageUI_NotVisible();
		requestSearchData();
		FrameController.controller.set_LargeLogo_Non_Visible();
		rememberTextField(searchField.getText());	
		SearchListFrameController.controller.setprogressIndicatorImageViewVisible();
		setListViewVisibleFalse();
	}
	
	public void ClearListSearchCall()
	{
		QueryVariables.clearOffset();
		FrameController.controller.set_LargeLogo_Non_Visible();
		SearchListFrameController.controller.setErrorMessageUI_NotVisible();
		SearchListFrameController.controller.ButtonListContainer.getChildren().clear();
		requestSearchData();
		setListViewVisibleFalse();
		rememberTextField(searchField.getText());
		SearchListFrameController.controller.setprogressIndicatorImageViewVisible();
	}
	
	protected void rememberTextField(String string) {
		
		searchFieldText = string;
	}

	public void requestTypeAHeadData()
	{
		adapter.getapicalls.typeAhead(QueryVariables.text, new Callback<List<TypeAHead>>() {
		
			@Override
			public void success(List<TypeAHead> typeAhead, Response response) 
			{
				
				ObservableList<String> typeAheadText = FXCollections.observableArrayList();
				
				if(!searchField.getText().isEmpty())
				{
					
					if(!typeAhead.isEmpty())
					{
						for(TypeAHead h : typeAhead)
						{
							typeAheadText.add(h.text);
						}	
						
						settypeaHeadtext(typeAheadText);
						setListViewVisibleUITrue(); // Display the ListView
					}
					
					if(typeAhead.isEmpty())
					{
						settypeaHeadtext(typeAheadText);
						setListViewVisibleUITrue(); // Display the ListView
					}
				}
			}
			
			@Override
			public void failure(RetrofitError retrofitError) 
			{
				
			}
		
		});
	}
	
	public void requestSearchData()
	{				
		adapter.getapicalls.searchFoodLimitAndOffset(QueryVariables.searchTerm, 50, QueryVariables.offset, new Callback<SearchData>() {
						
			@Override
			public void success(final SearchData searchData, Response response) 
			{				
				SearchListFrameController.controller.setPreviousIndex(-1);
				SearchListFrameController.controller.getResultLabel(searchData.total,QueryVariables.searchTerm);
				SearchListFrameController.controller.setResponseListSize(searchData.results.size());
				
				if(!searchData.results.isEmpty())
				{
					createList(searchData);	
				}
				else 
				{
					SearchListFrameController.controller.setprogressIndicatorImageView_NotVisible();
				}

				
				setListViewVisibleFalse();
			}
			
			@Override
			public void failure(RetrofitError retrofitError) 
			{
				if(retrofitError.getKind().name() == "NETWORK")
				{
					SearchListFrameController.controller.updateErrorMessageUI(retrofitError.getKind().name());
				}
									
			}
		});		
	}
	
	protected void setListViewVisibleUITrue() 
	{
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				
				if(gettypeaHeadtext() != null && !gettypeaHeadtext().isEmpty())
				{
					HBoxContainerForListView.setPrefHeight(140);
					listView.setVisible(true);
					listView.setItems(gettypeaHeadtext());
					listView.scrollTo(0);
				}
				
			}
		});
	}
	
	protected void setListViewVisibleFalse()
	{
		HBoxContainerForListView.setPrefHeight(40);
		listView.setVisible(false);	
	}
	
	public void resetSearchScene()
	{
		HBoxContainerForListView.setPrefHeight(40);
		searchField.clear();
		listView.setVisible(false);	
		SearchListFrameController.controller.resetSearchScene();
		FrameController.controller.set_LargeLogo_Visible();
	}
	
	public ObservableList<String> gettypeaHeadtext()
	{
		return typeaHeadtext;	
	}
	
	public void settypeaHeadtext(ObservableList<String> typeaHeadtext)
	{
		if(this.typeaHeadtext.equals(typeaHeadtext))
    	{
			this.typeaHeadtext = typeaHeadtext;
    	}
		else if(typeaHeadtext.isEmpty())
		{
			this.typeaHeadtext = typeaHeadtext;
		}
    	else 
    	{
    		this.typeaHeadtext = typeaHeadtext;
    	}
	}
	
	public void changeSearchFieldImageClear()
	{
		   ImageButton.setOpacity(.05);
	       ImageButton.setImage(SearchButtonClearImage);
	}
	
	public void changeSearchFieldImageStandard()
	{
		   ImageButton.setOpacity(1);
	       ImageButton.setImage(SearchButtonStandardImage);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1){}

	private void createList(final SearchData searchData) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for(Results results : searchData.results )
				{			
					SearchListFrameController.controller.createListItem(results.itemName, results.brandName, results.nutruentName,
							results.nutrientValue, results.nutrientUom, results.servingQty, results.servingUom, results.resourceId, results.thumbnail);
				}	
			}
		});
	}
}
