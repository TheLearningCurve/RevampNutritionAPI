package com.kandb_nutrition.signOn.model;

public class authData {
	
	private String email;
	private String first_name;
	private String last_name;
	private String uid;;
	
	public authData(String email, String first_name, String last_name, String uid)
	{
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
