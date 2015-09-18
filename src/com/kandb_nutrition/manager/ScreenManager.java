package com.kandb_nutrition.manager;

import com.kandb_nutrition.fitTracker.controllers.FitTrack_FrameController;
import com.kandb_nutrition.macrocalculator.controllers.MainController;
import com.kandb_nutrition.navMenu.controller.NavigationController;
import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.searchFeature.controllers.*;
import com.kandb_nutrition.signOn.controllers.CreateAccountController;
import com.kandb_nutrition.signOn.controllers.SignInFormController;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScreenManager {
	
	private static ScreenManager screenManager = null;
	
	private Stage primaryStage;
	private Scene signOnScene, createAccountScene, searchScene, macroCalcScene, fitTrackScene;
	private SignInFormController signInFormController;
	private FrameController frameController;
	private CreateAccountController createAccountController;
	private MainController macroCalculatorController;
	private FitTrack_FrameController fitTrack_FrameController;
	private NavigationController navigationController;
	private NutritionLabelFrameController nutritionLabelFrameController;

	private SearchFieldFrame searchFieldFrame;
	private SearchListFrameController searchListFrameController;
	
	public boolean searchFeature, fitTracker, initialLogin;

	public Strings strings;
	
	// Makes sure the ScreenManager class cannot be instantiated 
	protected ScreenManager() {}
	
	// Creates the controller objects. We call this once on the Main
	public void instaniateControllers() 
	{
		strings = new Strings();

		nutritionLabelFrameController = new NutritionLabelFrameController();
		searchFieldFrame = new SearchFieldFrame();
		searchListFrameController = new SearchListFrameController();
		navigationController = new NavigationController();
		
		// Scene Controllers
		signInFormController = new SignInFormController();
		createAccountController = new CreateAccountController();
		frameController = new FrameController();
		macroCalculatorController = new MainController();
		fitTrack_FrameController = new FitTrack_FrameController();
			
		signOnScene = new Scene(signInFormController);
		searchScene = new Scene(frameController);
		createAccountScene = new Scene(createAccountController);
		macroCalcScene = new Scene(macroCalculatorController);
		fitTrackScene = new Scene(fitTrack_FrameController);		
	}
	
	public void setResponsiveStyle(Double maxWidth, Double maxHeight) {
		
		if(maxHeight >= 768 && maxWidth >= 1366) {
			signOnScene.getStylesheets().addAll(strings.getSignIn_CSS(), strings.getMaster_CSS());
		}
		else if(maxHeight >= 768 && maxWidth >= 1280) {
			signOnScene.getStylesheets().addAll(strings.getSignIn_CSS(), strings.getMaster_CSS());
		}
		else if(maxHeight >= 720 && maxWidth >= 1280) {
			signOnScene.getStylesheets().addAll(strings.getSignIn_CSS(), strings.getMaster_CSS());
		}
		else if(maxHeight >= 600 && maxWidth >= 1280) {
			signOnScene.getStylesheets().addAll(strings.getSignIn_CSS(), strings.getMaster_CSS());
		}
		else if(maxHeight >= 768 && maxWidth >= 1024) {
			signOnScene.getStylesheets().addAll(strings.getSignIn_CSS(), strings.getMaster_CSS());
		}
		else if(maxHeight >= 600 && maxWidth >= 800) {
			signOnScene.getStylesheets().addAll(strings.getSignIn_CSS(), strings.getMaster_CSS());
		}
		
	}
	
	// Controller Getters
	
	public FrameController getFrameController() {
		return frameController;
	}
	
	public FitTrack_FrameController getFitTrack_FrameController() {
		return fitTrack_FrameController;
	}

	public NavigationController getNavigationController() {
		return navigationController;
	}

	public SignInFormController getSignInFormController() {
		return signInFormController;
	}

	public CreateAccountController getCreateAccountController() {
		return createAccountController;
	}
	
	public NutritionLabelFrameController getNutritionLabelFrameController() {
		return nutritionLabelFrameController.getController();
	}

	/*
	 * I am not sure why but the list and the nutrient label do not show if we do not have the controller variable. 
	 */
	public SearchListFrameController getSearchListFrameController() {		
		return searchListFrameController.getController();
	}

	public SearchFieldFrame getSearchFieldFrame() {
		return searchFieldFrame.getController();
	}

	// Gets the instance so we are only creating the screenManager once
	public static ScreenManager getInstance() {
		
		if(screenManager == null)
		{
			screenManager = new ScreenManager();
		}
		
		return screenManager;
	}
	
	// Initial Login 
	public void initialLogin() {		
		
		primaryStage.setScene(signOnScene);
		primaryStage.show();
	}
	
	// When the user is authenticated
	public void loggedIn() {
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
           					
            	initialLogin = true;
            	searchFeature = true;
            	searchFeature();
            }
		});
	}
	
	// Create Account
	public void createAccount() {
		
		primaryStage.setScene(createAccountScene);
		primaryStage.show();	
	}
	
	public void fitTracker() {
		
		searchFeature = false;
		fitTracker = true;
		primaryStage.setScene(fitTrackScene);
		primaryStage.show();
	}
	
	public void searchFeature() {
		
		searchFeature = true;
		fitTracker = false;
		primaryStage.setScene(searchScene);	
		
		if(initialLogin == true)
		{
			primaryStage.centerOnScreen();
			initialLogin = false;
		}
	
		primaryStage.show();
	}
	
	public void macroCalc() {
		
		primaryStage.setScene(macroCalcScene);
		primaryStage.show();
	}
	
	// This method will get the stage from the Main class. Called once
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
	
	// Booleans
	
	public boolean isSearchFeature() {
		return searchFeature;
	}

	public boolean isFitTracker() {
		return fitTracker;
	}

}
