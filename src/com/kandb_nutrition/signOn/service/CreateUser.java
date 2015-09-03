package signOn.service;

import java.util.HashMap;
import java.util.Map;

import signOn.controllers.SignInFormController;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class CreateUser {
	
	public GetUser getUser;
	
	public CreateUser() {
		
		getUser = new GetUser();
	}
	
	
	public void Create(Firebase firebase, String first_name, String last_name, String email, String password)
	{
		firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
			
		    @Override
		    public void onSuccess(Map<String, Object> result) {		    	    			    	

		    	Firebase user = firebase.child("users/accounts/" + result.get("uid").toString());
		    	
		    	Map<String, String> accountMap = new HashMap<String, String>();
		    	accountMap.put("first_name", first_name);
		    	accountMap.put("last_name", last_name);
		    	accountMap.put("email", email);
		    	
		    	user.setValue(accountMap);   
		    	
		    	getUser.getUser(firebase, email, password, SignInFormController.controller);
		    	
		    }
		    
		    @Override
		    public void onError(FirebaseError firebaseError) {
		    	
		    	firebaseError.getMessage();
		    }
		});
	}
	

}
