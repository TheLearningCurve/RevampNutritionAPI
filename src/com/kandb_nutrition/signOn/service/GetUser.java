package signOn.service;

import Manager.ScreenManager;
import signOn.controllers.CreateAccountController;
import signOn.controllers.SignInFormController;
import signOn.model.authData;

import com.firebase.client.*;

public class GetUser {

	public authData auth_Data;
	
	public GetUser() {}

	public void getUser(Firebase firebase, String email, String password, SignInFormController controller)
	{
		firebase.authWithPassword(email, password,  new Firebase.AuthResultHandler() {

			@Override
			public void onAuthenticated(AuthData authData) {							
				ScreenManager sm = ScreenManager.getInstance();
				sm.loggedIn();
				
				CreateAccountController.controller.setLoggingIn_gif_NonVisible();
			}

			@Override
			public void onAuthenticationError(FirebaseError firebaseError) {			
				controller.textFieldError(firebaseError.getMessage());
			}				
		});
	}
}
