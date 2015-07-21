//	Created by Kyle Wolff and Brandon VanderMey 6/9/2015



import searchFeature.nutritionAPIV2_controllers.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public FrameController frameController;
	public Scene one; 
	
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
	
		one = new Scene(frameController);
		
		primaryStage.setScene(one);
		primaryStage.show();
		
	}
}
