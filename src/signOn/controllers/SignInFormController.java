package signOn.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Manager.ScreenManager;
import resource.Strings;
import signOn.service.FireBase;
import signOn.service.GetUser;
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
import javafx.stage.Stage;


public class SignInFormController extends AnchorPane implements Initializable {
	
	@FXML
	TextField EmailField, PasswordField;
	
	@FXML
	Button SignInButton, CreateAccountButton;
	
	@FXML
	Label errorLabel;
	
	@FXML
	ImageView signIn_loading_icon;
	
	public static SignInFormController controller;
	public Strings strings;
	public FireBase fireBase;
	public GetUser getUser;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public SignInFormController() {
		
		strings = new Strings();
		fireBase = new FireBase();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(strings.getSignInForm_fxml()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		controller = (SignInFormController) fxmlLoader.getController();
			
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
					display_Loading_Icon();
					getUser.getUser(fireBase.getFireBaseUrl(), EmailField.getText(), PasswordField.getText(), controller);
				}
				else {
					
					if(EmailField.getText().isEmpty() && PasswordField.getText().isEmpty())
					{
						textFieldError("Please fill in both fields");
					}
					else if(EmailField.getText().isEmpty())
					{
						textFieldError("Email field cannot be empty");
					}
					else if(PasswordField.getText().isEmpty())
					{
						textFieldError("Password field cannot be empty");
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
				
				errorLabel.setVisible(true);
				errorLabel.setText(errorMessage);
			}
		});
	}
	
	public void display_Loading_Icon()
	{
		signIn_loading_icon.setVisible(true);
	}
	
}
