package signOn.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import resource.Strings;
import signOn.service.CreateUser;
import signOn.service.FireBase;
import Manager.ScreenManager;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAccountController extends AnchorPane implements Initializable {
	
	@FXML 
	TextField FirstName_Field, LastName_Field, email_Field, email_confirm_Field, password_Field, confirm_Password;
	
	@FXML
	ProgressBar password_Progress;
	
	@FXML
	Button createAccount_Button, backButton;
	
	public CreateUser createUser;
	public FireBase fireBase;
	public Strings strings;
	public CreateAccountController controller;
	public boolean all_Fields_filled;
	

	public CreateAccountController()
	{		
		strings = new Strings();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(strings.getCreatAccountForm_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (CreateAccountController) fxmlLoader.getController();
		
		try
		{
			fxmlLoader.load();
		}
		
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		all_Fields_filled	= (!(FirstName_Field.getText().isEmpty() && LastName_Field.getText().isEmpty() && email_Field.getText().isEmpty()
				&& email_confirm_Field.getText().isEmpty() && password_Field.getText().isEmpty() && confirm_Password.getText().isEmpty()));
		
		createUser = new CreateUser();
		fireBase = new FireBase();
		
		createAccount_Button.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				
				
					createUser.Create(fireBase.getFireBaseUrl(), FirstName_Field.getText(), LastName_Field.getText(), 
						email_confirm_Field.getText(), confirm_Password.getText());
				
				
			}
		});
		
		backButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ScreenManager sm = ScreenManager.getInstance();
				sm.initialLogin();
			}
		});
		
	}
	
	
	@FXML
	public void NameCheck(KeyEvent keyEvent)
	{
		
	}
	
	@FXML 
	public void FormValidation(ActionEvent event)
	{		
		
	}
	
}
