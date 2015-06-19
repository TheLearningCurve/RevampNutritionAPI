//	Created by Kyle Wolff and Brandon VanderMey 6/9/2015

package nutritionAPIV2_view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nutritionAPIV2_controllers.*;

public class Main extends Application
{
	public FrameController frameController;
	
	Stage primaryStage;
	
	public static void main(String[] args) 
	{
        launch(args);
    }

	@Override
	public void start(Stage stage) 
	{		
		primaryStage = stage;
		
		FrameController frameController = new FrameController();
	
		Scene one = new Scene(frameController);
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		one.getStylesheets().add(getClass().getResource("Frame.css").toExternalForm());
		primaryStage.setScene(one);
		primaryStage.show();
		
	}
}
