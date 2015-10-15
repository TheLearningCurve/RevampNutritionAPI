package com.kandb_nutrition.signOn.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.signOn.service.FireBase;
import com.kandb_nutrition.signOn.service.GetUser;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


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
	private Pattern email_Pattern;
	private Matcher email_Matcher;
	private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		email_Pattern = Pattern.compile(EMAIL_PATTERN);
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
		
		// Created a Key Listener to know handle the TAB key to the Password field 
		EmailField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				
				if(keyEvent.getCode() == KeyCode.TAB) {
					
					//Sets the Password field to focused 
					PasswordField.setFocusTraversable(true);
					
					if(validateEmail(EmailField.getText()) == false) {
						textFieldError(string.getEmailError_Message());
					}
				}
			}
		});
		
		
		/* When the user clicks through the tab field starting at the Email field it would
		 * go in a loop from password field to create account button and never touch the email field. 
		 * 
		 * This listener is to make the loop go back to the email field. 
		 */
		CreateAccountButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				
				if(keyEvent.getCode() == KeyCode.TAB) {
					EmailField.setFocusTraversable(true);
				}
			}
		});
		
		
		// Added this listener for the Enter Key. If the user presses Enter then can make the login call
		PasswordField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				
				if(keyEvent.getCode() == KeyCode.ENTER) {
					
					loginValidation();
				}
			}
		});
				
		SignInButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {	
				
					loginValidation();
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
	
	/* Need the new runnable because some of the errors possible come back from the service.
	 * If the error comes back from the service we cannot update the UI on the service thread. 
	 */
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
	
	public void setTextFieldError_NonVisible() {
		
		setLoadingIconNonVisible();
		errorLabel.setVisible(false);
		errorLabel.setText("");
	}
	
	public void loginValidation() {
		
		if(EmailField.getText().isEmpty() && PasswordField.getText().isEmpty()) {
			textFieldError(string.getEmptySignInField_Message());
		}
		else if(EmailField.getText().isEmpty()) {
			textFieldError(string.getEmptyEmailField_Message());
		}
		else if(PasswordField.getText().isEmpty()) {
			textFieldError(string.getEmptyPasswordField_Message());
		}
		else if(validateEmail(EmailField.getText()) == false) {
			textFieldError(string.getEmailError_Message());
		}
		else {
			login();
		}
	}
	
	public Boolean validateEmail(String email) {
		email_Matcher = email_Pattern.matcher(email);
		return email_Matcher.matches();
	}
	
	public void login() {
		setLoadingIconVisible();
		getUser.getUser(fireBase.getFireBaseUrl(), EmailField.getText(), PasswordField.getText());
	}
	
	public void setLoadingIconVisible() {
		signIn_loading_icon.setVisible(true);
	}
	
	public void setLoadingIconNonVisible() {
		signIn_loading_icon.setVisible(false);
	}
	
}
