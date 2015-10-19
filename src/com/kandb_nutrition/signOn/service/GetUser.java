package com.kandb_nutrition.signOn.service;

import com.firebase.client.*;
import com.kandb_nutrition.manager.ScreenManager;
import com.kandb_nutrition.signOn.model.authData;

public class GetUser {

	public authData auth_Data;
	private ScreenManager sm;
	
	public GetUser() {
		sm = ScreenManager.getInstance();
	}

	public void getUser(Firebase firebase, String email, String password)
	{
		firebase.authWithPassword(email, password,  new Firebase.AuthResultHandler() {

			@Override
			public void onAuthenticated(AuthData authData) {							
				sm = ScreenManager.getInstance();
				sm.loggedIn();
				
				
				sm.getCreateAccountController().setLoggingIn_gif_NonVisible();
			}

			@Override
			public void onAuthenticationError(FirebaseError firebaseError) {
				sm.getSignInFormController().textFieldError(firebaseError.getMessage());
			}				
		});
	}
}
