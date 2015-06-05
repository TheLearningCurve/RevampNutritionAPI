package nutritionAPIV2_controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import retrofit.client.Response;
import nutritionAPIV2_model.TypeAHead;
import nutritionAPIV2_service.Adapter;
import nutritionAPIV2_service.QueryVariables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
	
	@FXML
	ScrollPane scrollPane;
	
	
	/* LeftPanel Variables */
	@FXML
	AnchorPane leftPanel;
	
	@FXML
	ScrollPane buttonList;
	
	
	/* RightPanel Variables */
	@FXML
	AnchorPane rightPanel;
	
	@FXML
	WebView webViewControl;

	/* Top Panel Code */
	
	public static ObservableList<String> typeaHeadtext =FXCollections.observableArrayList ();
    public static StringBuffer sb = new StringBuffer();
    public static String x;
    public static int success;
    public Adapter adapter = new Adapter();
    public static Task<Object> task;

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
		            task = new Task<Object>() {
						
						@Override
						protected Object call() throws Exception {

				            adapter.typeAhead();
				            
				           	if(adapter.istORf() == false)
				           	{
				           		task.cancel();
				           	}
				           	
				           
							return null;
						}
					};
					
					task.run();
					task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
						
						@Override
						public void handle(WorkerStateEvent event) {				
							scrollPane.setVisible(true);
							listView.setVisible(true);
							listView.setItems(getTypeAheadText());						
						}
					});
		        }
			}
		});			
	}
	
	
	public static void setTypeAheadText(ObservableList<String> typeAhead)
	{
		typeaHeadtext = typeAhead;
	}
	
	public ObservableList<String> getTypeAheadText()
	{
		return typeaHeadtext;
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
            }
        }
	}

	@FXML
	public void addButtons()
	{		
		Group g = new Group();
		for (int i = 0; i < 20; i++)
		{
			Button but = new Button(String.valueOf(i));
			but.setLayoutY(i*40);
			g.getChildren().add(but);
			
		}
		
		buttonList.setContent(g);
	}
	
	/* Left Panel Code */
	

	@FXML
	public void finishedScroll()
	{
		System.out.println("V value: " + buttonList.getVvalue());
		System.out.println("V max: " + buttonList.getVmax());
	}
	
	
	/* Right Panel Code */	
	
}
