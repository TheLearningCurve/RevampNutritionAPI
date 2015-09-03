package com.kandb_nutrition.signOn.service;

import com.firebase.client.*;
import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.signOn.controllers.CreateAccountController;
import com.kandb_nutrition.signOn.controllers.SignInFormController;
import com.kandb_nutrition.signOn.model.authData;

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
