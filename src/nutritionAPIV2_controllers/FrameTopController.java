package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import nutritionAPIV2_model.Results;
import nutritionAPIV2_model.SearchData;
import nutritionAPIV2_model.TypeAHead;
import nutritionAPIV2_service.Adapter;
import nutritionAPIV2_service.QueryVariables;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FrameTopController implements Initializable
{
	@FXML
	TextField searchField;	
	
	@FXML
	ListView<String> listView;
	
	@FXML
	ImageView ImageButton;
	
	public ObservableList<String> typeaHeadtext = FXCollections.observableArrayList();
    public Adapter adapter = new Adapter();
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		searchField.setFocusTraversable(false);
		searchField.setOnKeyReleased(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent event) 
			{	
				if(event.getCode() != KeyCode.ENTER)
				{
					if(searchField.getText().length() == 0)
					{
						listView.setVisible(false);
					}
					
					if(searchField.getText().length() != 0)
					{
						QueryVariables.setText(searchField.getText());
						requestTypeAHeadData();
					}
				}
		    } 
	    });
		
		listView.setOnMousePressed(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				QueryVariables.setSearchTerm(listView.getSelectionModel().getSelectedItem());
				searchField.setText(listView.getSelectionModel().getSelectedItem());
				requestSearchData();
				listView.setVisible(false);
			}
		});
		
		ImageButton.setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) 
			{
				QueryVariables.setSearchTerm(searchField.getText());
				requestSearchData();
				listView.setVisible(false);
			}
		});
		
		
	}
	
	public void requestTypeAHeadData()
	{
		adapter.getapicalls.typeAhead(QueryVariables.text, new Callback<List<TypeAHead>>() {
		
			@Override
			public void success(List<TypeAHead> typeAhead, Response response) {
				
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
						updateUI();
					}
					
					if(typeAhead.isEmpty())
					{
						settypeaHeadtext(typeAheadText);
						updateUI();
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
		adapter.getapicalls.searchFood(QueryVariables.searchTerm, 10, QueryVariables.offset, new Callback<SearchData>() {

			@Override
			public void success(SearchData searchData, Response response) 
			{			
				for(Results results : searchData.results )
				{
					//ItemButton but = new ItemButton(numberOfButtons, results.brandName, results.itemName);
					//but.setLayoutY(numberOfButtons * 60);
					//buttons.add(but);
					//updateButtonList(but);				
					//numberOfButtons++;
				}					
			}
			
			@Override
			public void failure(RetrofitError retrofitError) 
			{
				
			}
		});
	}
	
	protected void updateUI() 
	{
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				
				if(gettypeaHeadtext() != null && !gettypeaHeadtext().isEmpty())
				{
					listView.setVisible(true);
					listView.setItems(gettypeaHeadtext());	
				}
				else 
				{
					listView.setVisible(false);
				}	
			}
		});
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
}
