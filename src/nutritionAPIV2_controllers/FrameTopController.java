package nutritionAPIV2_controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class FrameTopController extends AnchorPane implements Initializable
{
	@FXML
	TextField searchField;	
	
	@FXML
	ListView<String> listView;
	
	@FXML
	ImageView ImageButton;
	
	public ObservableList<String> typeaHeadtext = FXCollections.observableArrayList();
    public Adapter adapter = new Adapter();
    public static FrameTopController controller;
    public int buttonPress = 0;
    public String searchFieldText = "Empty String";
	
	public FrameTopController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nutritionAPIV2_view/FrameTop.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (FrameTopController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
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
				else if(event.getCode() == KeyCode.ENTER)
				{
					searchFieldButtonPress();
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
				
				if(FrameBottomLeftController.controller.buttonList.getContent() == null)
				{
					requestSearchData();
				}
				else
				{
					FrameBottomLeftController.controller.buttonList.setContent(null);
					FrameBottomLeftController.controller.buttonGroup.getChildren().clear();
					requestSearchData();
				}
				
				listView.setVisible(false);
			}
		});
		
		ImageButton.setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) 
			{
				searchFieldButtonPress();
			}
		});			
	}
	
	public void searchFieldButtonPress()
	{
		QueryVariables.setSearchTerm(searchField.getText());
		QueryVariables.clearOffset();
		// This stops the user from clicking multiple times when the text is the same
		if(searchField.getText().isEmpty())
		{
			// Do not allow another search with the same term
		}
		else if(searchField.getText().matches(searchFieldText))
		{
			// Do not allow another search with the same term
		}
		else if(FrameBottomLeftController.controller.buttonList.getContent() == null)
		{
			requestSearchData();
		}		
		else if(FrameBottomLeftController.controller.buttonList.getContent() != null || FrameBottomLeftController.controller.buttonList.getVvalue() < 1)
		{
			FrameBottomLeftController.controller.buttonList.setVvalue(0.0);
			FrameBottomLeftController.controller.buttonList.setContent(null);
			FrameBottomLeftController.controller.buttonGroup.getChildren().clear();
			requestSearchData();
		}
		
		listView.setVisible(false);
		rememberTextField(searchField.getText());			
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
				FrameBottomLeftController.controller.getResultLabel(10,QueryVariables.offset,searchData.total);
				
				for(Results results : searchData.results )
				{
					FrameBottomLeftController.controller.createButton(results.brandName, results.itemName, results.thumbnail);
				}	
				
			}
			
			@Override
			public void failure(RetrofitError retrofitError) 
			{
				System.out.println(retrofitError);
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
					listView.scrollTo(0);

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// XXX Auto-generated method stub
		
	}
}
