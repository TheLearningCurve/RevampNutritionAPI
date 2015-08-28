package Manager;

import fitTracker.controllers.FitTrack_FrameController;
import macroCalculator.controllers.MacroCalculatorController;
import searchFeature.nutritionAPIV2_controllers.FrameController;
import signOn.controllers.CreateAccountController;
import signOn.controllers.SignInFormController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScreenManager {
	
	private static ScreenManager screenManager = null;
	
	public Stage primaryStage;
	public Scene signOnScene, loggedInScene, createAccountScene, searchScene, macroCalcScene, fitTrackScene;
	public SignInFormController signInFormController;
	public FrameController frameController;
	public CreateAccountController createAccountController;
	public MacroCalculatorController macroCalculatorController;
	public FitTrack_FrameController fitTrack_FrameController;
	
	// Makes sure the ScreenManager class cannot be instantiated 
	protected ScreenManager() {}
	
	// Creates the controller objects. We call this once on the Main
	public void instaniateControllers()
	{
		signInFormController = new SignInFormController();
		createAccountController = new CreateAccountController();
		frameController = new FrameController();
		macroCalculatorController = new MacroCalculatorController();
		fitTrack_FrameController = new FitTrack_FrameController();
		
		
		signOnScene = new Scene(signInFormController);
		createAccountScene = new Scene(createAccountController);
		searchScene = new Scene(frameController);
		macroCalcScene = new Scene(macroCalculatorController);
		fitTrackScene = new Scene(fitTrack_FrameController);
		
	}
	
	// Gets the instance so we are only creating the screenManager once
	public static ScreenManager getInstance()
	{
		if(screenManager == null)
		{
			screenManager = new ScreenManager();
		}
		
		return screenManager;
	}
	
	// Initial Login 
	public void initialLogin()
	{		
		primaryStage.setScene(signOnScene);
		primaryStage.show();
	}
	
	// When the user is authenticated
	public void loggedIn()
	{
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            					
				primaryStage.setScene(searchScene);
				primaryStage.centerOnScreen();
				primaryStage.show();
            }
		});
	}
	
	// Create Account
	public void createAccount()
	{		
		primaryStage.setScene(createAccountScene);
		primaryStage.show();	
	}
	
	public void fitTracker()
	{
		primaryStage.setScene(fitTrackScene);
		primaryStage.show();
	}
	
	public void searchFeature()
	{
		primaryStage.setScene(searchScene);
		primaryStage.show();
	}
	
	public void macroCalc()
	{
		primaryStage.setScene(macroCalcScene);
		primaryStage.show();
	}
	
	// This method will get the stage from the Main class. Called once
	public void setStage(Stage stage)
	{
		primaryStage = stage;
	}

}
