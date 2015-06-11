//	Created by Kyle Wolff and Brandon VanderMey 6/9/2015

package nutritionAPIV2_view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nutritionAPIV2_controllers.*;

public class Main extends Application
{
	public FrameController frameController;
	public FrameTopController frameTopController;
	
	public static void main(String[] args) 
	{
        launch(args);
    }

	@Override
	public void start(Stage stage) 
	{
		FrameController frameController = new FrameController();
		
		Scene scene = new Scene(frameController);
		scene.getStylesheets().add(getClass().getResource("Frame.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
}
