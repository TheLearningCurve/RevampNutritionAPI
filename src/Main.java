//	Created by Kyle Wolff and Brandon VanderMey 6/9/2015



import Manager.ScreenManager;
import javafx.application.Application;
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
		ScreenManager sm = ScreenManager.getInstance();
		sm.setStage(stage);
		sm.instaniateControllers();
		sm.initialLogin();
	}
}
