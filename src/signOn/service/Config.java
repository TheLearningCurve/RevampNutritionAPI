package signOn.service;

public class Config {

	 private static final String BASE_URL = "https://nutrition-app.firebaseio.com/";
	 private static final String CLIENT_SECRECT = "SeNjzIyrIm4Aj9mHdb5scx1ZrQoar40B6jbxfdsk";
	 
	 public String getClient_Secret() {
		return CLIENT_SECRECT;
	}
	public String getBaseUrl() {
		return BASE_URL;
	}

}
