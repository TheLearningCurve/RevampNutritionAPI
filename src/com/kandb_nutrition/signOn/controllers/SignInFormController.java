package com.kandb_nutrition.signOn.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.signOn.service.FireBase;
import com.kandb_nutrition.signOn.service.GetUser;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class SignInFormController extends AnchorPane implements Initializable {
	
	@FXML
	TextField EmailField, PasswordField;
	
	@FXML
	Button SignInButton, CreateAccountButton;
	
	@FXML
	Label errorLabel;
	
	@FXML
	ImageView signIn_loading_icon;
	
	public Strings string;
	public FireBase fireBase;
	public GetUser getUser;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public SignInFormController() {
		
		string = new Strings();
		fireBase = new FireBase();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getSignInForm_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
			
		try
		{
			fxmlLoader.load();
			getUser = new GetUser();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		SignInButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {	
				
				if(!(EmailField.getText().isEmpty() || PasswordField.getText().isEmpty()))
				{
					setLoadingIconVisible();
					getUser.getUser(fireBase.getFireBaseUrl(), EmailField.getText(), PasswordField.getText());
				}
				else {
					
					if(EmailField.getText().isEmpty() && PasswordField.getText().isEmpty())
					{
						textFieldError(string.getEmptySignInField_Message());
					}
					else if(EmailField.getText().isEmpty())
					{
						textFieldError(string.getEmptyEmailField_Message());
					}
					else if(PasswordField.getText().isEmpty())
					{
						textFieldError(string.getEmptyPasswordField_Message());
					}
				}
			}
		});
		
		CreateAccountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ScreenManager sm = ScreenManager.getInstance();
				sm.createAccount();
			}
		});
		
	}
	
	public void textFieldError(String errorMessage)
	{
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				setLoadingIconNonVisible();
				errorLabel.setVisible(true);
				errorLabel.setText(errorMessage);
			}
		});
	}
	
	public void setLoadingIconVisible() {
		signIn_loading_icon.setVisible(true);
	}
	
	public void setLoadingIconNonVisible() {
		signIn_loading_icon.setVisible(false);
	}
	
}
