package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXUIFactory;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.RequestInterceptor.RequestFacade;
import retrofit.client.Response;
import nutritionAPIV2_model.Results;
import nutritionAPIV2_model.SearchData;
import nutritionAPIV2_model.TypeAHead;
import nutritionAPIV2_service.Adapter;
import nutritionAPIV2_service.Config;
import nutritionAPIV2_service.ErrorHandling;
import nutritionAPIV2_service.GetAPICalls;
import nutritionAPIV2_service.QueryVariables;
import nutritionAPIV2_view.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class FrameController implements Initializable
{	

	@FXML
	AnchorPane mainPanel;
	
	/* Top Panel Variables */
	@FXML
	TextField searchField;	
	
	@FXML
	ListView<String> listView;
	
	/* LeftPanel Variables */
	@FXML
	AnchorPane leftPanel;
	
	@FXML
	ScrollPane buttonList;
	
	int numberOfButtons = 0;
	static int buttonNumberPressed = -1;
	
	Group buttonGroup = new Group();
	
	static List<ItemButton> buttons = new ArrayList<ItemButton>();
	
	
	/* RightPanel Variables */
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;

	/* Top Panel Code */
	
	public ObservableList<String> typeaHeadtext = FXCollections.observableArrayList();
    public static StringBuffer sb = new StringBuffer();
    public static String x;
    public Adapter adapter = new Adapter();
    
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{ 
		searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent e) {
				getText(e.getCode());
		        x = String.valueOf(sb);

		        if(x.length() >= 1)
		        {
		            QueryVariables.setText(x);
		            requestTypeAHeadData();		                                
                }
			};	       
		});
		
		listView.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				QueryVariables.setSearchTerm(listView.getSelectionModel().getSelectedItem());
				requestSearchData();
			}
		});
	 }
	
	public void requestTypeAHeadData()
	{
		
     adapter.getapicalls.typeAhead(QueryVariables.text, new Callback<List<TypeAHead>>() {
		
		@Override
		public void success(List<TypeAHead> typeAhead, Response response) {
			
			ObservableList<String> typeAheadText = FXCollections.observableArrayList();
			
			for(TypeAHead h : typeAhead)
			{
				typeAheadText.add(h.text);
			}	
			
			if(!typeAheadText.isEmpty())
			{
				settypeaHeadtext(typeAheadText);
			}
			else 
			{
				settypeaHeadtext(null);
			}
			
			updateUI();
		}
		
		@Override
		public void failure(RetrofitError arg0) {
			
		}
	});
      
	}
	
	protected void updateUI() {
		
		new JFXPanel();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				listView.setVisible(true);
				listView.setItems(gettypeaHeadtext());	
			}
		});
	}

	private void getText(KeyCode keyCode) {
		   
        if(keyCode.isLetterKey())
        {
            sb.append(keyCode);
        }
        else if(keyCode.getName().equals("Space"))
        {
        	sb.append(" ");
        }
        else if (keyCode.getName().equals("Backspace"))
        {
            if(sb.length() != 0)
            {
                sb.setLength(sb.length() - 1);
               if(sb.length() == 0)
               {
            	   listView.setVisible(false);
               }
            }
        }     
	}
	
	public ObservableList<String> gettypeaHeadtext()
	{
		return typeaHeadtext;	
	}
	    
	public void settypeaHeadtext(ObservableList<String> typeaHeadtext)
	{
    	if(this.typeaHeadtext.equals(typeaHeadtext))
    	{
        	
    	}
    	else if(typeaHeadtext == null)
    	{
    		listView.setVisible(false);
    	}
    	else
    	{
    		this.typeaHeadtext = typeaHeadtext;
    	}
	}
	
	public void requestSearchData()
	{
		adapter.getapicalls.searchFood(QueryVariables.searchTerm, 50, 0, new Callback<SearchData>() {

			@Override
			public void success(SearchData searchData, Response response) {
						
				for(Results s : searchData.results )
				{
					
				}
			}
			
			@Override
			public void failure(RetrofitError retrofitError) {
				
			}
		});
	}

	@FXML
	public void addButtons() // Change to your liking.
	{
			for (int i = 0; i < 10; i++)
			{
				ItemButton but = new ItemButton(numberOfButtons);
				but.setLayoutY(numberOfButtons * 60);
				buttons.add(but);
				buttonGroup.getChildren().add(but);
				numberOfButtons++;
			}
	
			buttonList.setContent(buttonGroup);
	}

	@FXML
	public void finishedScroll() // Change to your liking.
	{
		if (buttonList.getVvalue() == 1)
			addButtons();
	}
	
	/* Right Panel Code */	
}
