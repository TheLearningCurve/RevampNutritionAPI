package com.kandb_nutrition.signOn.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.resource.Strings;
import com.kandb_nutrition.signOn.service.CreateUser;
import com.kandb_nutrition.signOn.service.CreateValidation;
import com.kandb_nutrition.signOn.service.FireBase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CreateAccountController extends AnchorPane implements Initializable {
	
	@FXML 
	TextField firstName_Field, lastName_Field, email_Field, email_confirm_Field, password_Field, confirm_Password;
	
	@FXML
	ProgressBar password_Progress;
	
	@FXML
	Button createAccount_Button, backButton;
	
	@FXML 
	ImageView email_Error_Icon, password_Error_Icon, confirm_Email_Icon, confirm_Password_Icon, loggingIn_gif;
	
	@FXML
	Label error_Password_Label, confirm_Password_Label;
	
	public CreateUser createUser;
	public FireBase fireBase;
	public Strings string;
	public CreateValidation createValidation;
	public int enableCreateAccount;
	public boolean valid_Email;
	public double confirmLabelY, confirmPasswordY, createAccountButtonY;
	
	public static CreateAccountController controller;
	

	public CreateAccountController()
	{		
		string = new Strings();
		createValidation = new CreateValidation();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(string.getCreatAccountForm_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (CreateAccountController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
			confirmLabelY = confirm_Password_Label.getLayoutY();
			confirmPasswordY = confirm_Password.getLayoutY();
			createAccountButtonY = createAccount_Button.getLayoutY();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		createUser = new CreateUser();
		fireBase = new FireBase();
		
		firstName_Field.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
				if(newValue == true)
				{
					CreateAccountController.controller.createValidation.setObject(firstName_Field);
					firstName_Field.textProperty().addListener(createValidation);
				}
			}	
		});
		
		lastName_Field.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
				if(newValue == true)
				{
					CreateAccountController.controller.createValidation.setObject(lastName_Field);
					lastName_Field.textProperty().addListener(createValidation);
				}
			}		
		});
		
		
		email_Field.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
				if(newValue == true)
				{
					CreateAccountController.controller.createValidation.setObject(email_Field);
					email_Field.textProperty().addListener(createValidation);
				}
			}
		});
		
		email_confirm_Field.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
				if(newValue == true)
				{
					CreateAccountController.controller.createValidation.setObject(email_confirm_Field);
					email_confirm_Field.textProperty().addListener(createValidation);
				}
			}
		});
		
		password_Field.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
				if(newValue == true)
				{
					CreateAccountController.controller.createValidation.setObject(password_Field);
					password_Field.textProperty().addListener(createValidation);
				}
			}
			
		});
		
		confirm_Password.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
				if(newValue == true)
				{
					CreateAccountController.controller.createValidation.setObject(confirm_Password);
					confirm_Password.textProperty().addListener(createValidation);
				}
			}
		});
				
		createAccount_Button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				
				createUser.Create(fireBase.getFireBaseUrl(), firstName_Field.getText(), lastName_Field.getText(), email_confirm_Field.getText(), password_Field.getText());
				setLoggingIn_gif_Visible();
			}
		});
		
		backButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				resetCreateAccount();
				
				ScreenManager sm = ScreenManager.getInstance();
				sm.initialLogin();
			}
		});
	}
	
	public void updateProgressBar(double d)
	{
		password_Progress.setProgress(d);
		
		if(d == .25)
		{
			password_Progress.setStyle(string.getFx_accent_red());
			password_Progress.setProgress(d);
		}
		else if(d == .5)
		{
			password_Progress.setStyle(string.getFx_accent_orange());
			password_Progress.setProgress(d);
		}
		else if(d == 1)
		{
			password_Progress.setStyle(string.getFx_accent_green());
			password_Progress.setProgress(d);
		}
		else if(d == 1.5)
		{
			password_Progress.setStyle(string.getFx_accent_red());
			password_Progress.setProgress(d);
		}
	}
	
	public void seterror_Password_Label_Visible(String errorMessage) {
		
		error_Password_Label.setText(errorMessage);
		if(error_Password_Label.getText().length() >= 70) {
			error_Password_Label.setLayoutY(210);
			
			confirm_Password.setLayoutY(250);
			confirm_Password_Label.setLayoutY(245);
			createAccount_Button.setLayoutY(280);
		}
		else if(error_Password_Label.getText().length() < 70) {
			error_Password_Label.setLayoutY(200);
			
			confirm_Password.setLayoutY(confirmPasswordY + 5);
			confirm_Password_Label.setLayoutY(confirmLabelY + 5);
			createAccount_Button.setLayoutY(createAccountButtonY + 5);
		}	
		
		error_Password_Label.setVisible(true);	
	}
	
	public void seterror_Password_Label_NonVisible() {
		
		error_Password_Label.setVisible(false);
		
		confirm_Password.setLayoutY(confirmPasswordY);
		confirm_Password_Label.setLayoutY(confirmLabelY);
		createAccount_Button.setLayoutY(createAccountButtonY);
	}
	
	public void create_Button_Enable()
	{
		createAccount_Button.setDisable(false);
	}
	
	public void create_Button_Disable()
	{
		createAccount_Button.setDisable(true);
	}
	
	public void setEmail_error() {
		email_Error_Icon.setVisible(true);
	}
	
	public void setEmail_error_nonvisible() {
		email_Error_Icon.setVisible(false);
	}
	
	public void setPassword_Error_Icon() {
		password_Error_Icon.setVisible(true);
	}
	
	public void setPassword_Error_nonVisible() {
		password_Error_Icon.setVisible(false);
	}
	
	public void setConfirm_Email_error() {
		confirm_Email_Icon.setVisible(true);
	}
	
	public void setConfirm_Password_error() {
		confirm_Password_Icon.setVisible(true);
	}
	
	public void setConfirmEmail_Error_nonVisible() {
		confirm_Email_Icon.setVisible(false);
	}
	
	public void setConfirmPassword_Error_nonVisible() {
		confirm_Password_Icon.setVisible(false);
	}
	
	public void setLoggingIn_gif_Visible() {
		loggingIn_gif.setVisible(true);
	}
	
	public void setLoggingIn_gif_NonVisible() {
		loggingIn_gif.setVisible(false);
	}
	
	public void resetCreateAccount() {
		firstName_Field.clear();
		lastName_Field.clear();
		email_Field.clear();
		email_confirm_Field.clear();
		password_Field.clear();
		confirm_Password.clear();
	}
}
