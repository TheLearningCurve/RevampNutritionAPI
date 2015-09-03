package com.kandb_nutrition.signOn.service;

import com.firebase.client.Firebase;


public class FireBase {
	
	Config config = new Config();

	Firebase myFirebaseRef = new Firebase(config.getBaseUrl());
	
	public Firebase getFireBaseUrl()
	{
		return myFirebaseRef;
	}
	
}
