//	Created by Kyle Wolff and Brandon VanderMey 6/9/2015



import com.kandb_nutrition.manager.ScreenManager;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application
{
	public ScreenManager screenManager;
	
	public static void main(String[] args) 
	{
        launch(args);
    }

	@Override
	public void start(Stage stage) 
	{		
		stage.setResizable(false);
		
		ScreenManager sm = ScreenManager.getInstance();
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
		
		sm.setStage(stage);
		sm.instaniateControllers();
		
		sm.setResponsiveStyle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		sm.initialLogin();
	}
}
