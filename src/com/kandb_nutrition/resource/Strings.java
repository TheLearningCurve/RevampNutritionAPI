package com.kandb_nutrition.resource;

public class Strings {

	/*
	 * FXML Strings
	 */
	
	private String signInForm_fxml = "/com/kandb_nutrition/signOn/view/SignInForm.fxml";
	private String creatAccountForm_fxml = "/com/kandb_nutrition/signOn/view/CreateAccountForm.fxml";
	private String macroCalculator_fxml = "/com/kandb_nutrition/macrocalculator/fxml/MainFrame.fxml";
	private String fitTracker_fxml = "/com/kandb_nutrition/fitTracker/view/Tracker_Frame.fxml";
	private String expandedLabel_fxml = "/com/kandb_nutrition/searchFeature/view/ExpandableTextCell.fxml";
	private String nutritionLabel_fxml = "/com/kandb_nutrition/searchFeature/view/NutritionLabelFrame.fxml";
	private String searchFieldFrame_fxml = "/com/kandb_nutrition/searchFeature/view/SearchFieldFrame.fxml";
	private String searchListFrame_fxml = "/com/kandb_nutrition/searchFeature/view/SearchListFrame.fxml";
	private String frame_fxml = "/com/kandb_nutrition/searchFeature/view/Frame.fxml";
	private String singleTextCell_fxml = "/com/kandb_nutrition/searchFeature/view/SingleTextCell.fxml";
	private String navigationMenu_fxml = "/com/kandb_nutrition//navMenu/view/NavigationMenu.fxml";
	private String animatedScrollPane_fxml = "/com/kandb_nutrition/macrocalculator/fxml/AnimatedScrollPane.fxml";
	private String firstPanel_fxml = "/com/kandb_nutrition/macrocalculator/fxml/FirstPanel.fxml";
	private String fourthPanel_fxml = "/com/kandb_nutrition/macrocalculator/fxml/FourthPanel.fxml";
	private String buttonNext_fxml = "/com/kandb_nutrition/macrocalculator/fxml/ButtonNext.fxml";
	private String pageNumberBar_fxml = "/com/kandb_nutrition/macrocalculator/fxml/PageNumberBar.fxml";
	private String previousButton_fxml = "/com/kandb_nutrition/macrocalculator/fxml/ButtonPrevious.fxml";
	private String secondPanel_fxml = "/com/kandb_nutrition/macrocalculator/fxml/SecondPanel.fxml";
	private String thirPanel_fxml = "/com/kandb_nutrition/macrocalculator/fxml/ThirdPanel.fxml";
	private String menuButton_fxml = "/com/kandb_nutrition/menuButton/view/menuButton.fxml";
	
	/*
	 * Image Strings
	 */


	private String signOn_gif = "@../../../resource/images/sign_in_loader.gif";
	private String standardButton = "/com/kandb_nutrition/resource/images/menuButton.png";
	private String buttonCicked = "/com/kandb_nutrition/searchFeature/resources/menuButtonClicked.png";
	private String backButton = "/com/kandb_nutrition/searchFeature/resources/MenuBackButton.png";
	private String searchActive_Image = "/com/kandb_nutrition/resource/images/searchActiveIcon.png";
	private String searchStandard_Image = "/com/kandb_nutrition/resource/images/searchIcon.png";
	private String macroActive_Image = "/com/kandb_nutrition/resource/images/macroActiveIcon.png";
	private String macroStandard_Image = "/com/kandb_nutrition/resource/images/macroIcon.png";
	private String fitActive_Image = "/com/kandb_nutrition/resource/images/fitTrackerActiveIcon.png";
	private String fitStandard_Image = "/com/kandb_nutrition/resource/images/fitTrackerIcon.png";
	private String xButton_Black_Image = "/com/kandb_nutrition/searchFeature/resources/close_Black.png";
	private String xButton_White_Image = "/com/kandb_nutrition/searchFeature/resources/close_White.png";
	private String searchButtonClear_Image = "/com/kandb_nutrition/searchFeature/resources/SearchButton_Black_Clear.png";
	private String searchButtonStandard_Image = "/com/kandb_nutrition/searchFeature/resources/SearchButton_Black.png";
	
	/* 
	 * CSS Files
	 */

	private String SignIn_CSS = "/com/kandb_nutrition/resource/styleSheets/SignIn_1366_X_768.css";
	private String Master_CSS = "/com/kandb_nutrition/resource/styleSheets/MasterCSS.css";


	/*
	 * Sign in ID Strings
	 */
	
	private String email_textField_id = "email_Field";
	private String password_Field_id = "password_Field";
	private String email_confirm_id = "email_confirm_Field";
	private String password_confirm_id = "confirm_Password";
	private String first_name_id = "firstName_Field";
	private String last_name_id = "lastName_Field";
	
	/*
	 * Error Messages
	 */
	
	private String emptySignInField_Message= "Please fill in both fields";
	private String emptyEmailField_Message = "Email field cannot be empty";
	private String emptyPasswordField_Message = "Password field cannot be empty";
	private String networkError_Message = " Error. Please connect to the Internet";
	
	/*
	 * Style Strings
	 */
	
	private String fx_accent_red = "-fx-accent: red";
	private String fx_accent_orange = "-fx-accent: orange";
	private String fx_accent_green = "-fx-accent: green";
	private String fx_text_fill_98FF42 = "-fx-text-fill: #98FF42";
	private String fx_text_fill_black = "-fx-text-fill: black";



	/*
	 * FXML METHODS
	 */

	public String getSignInForm_fxml() {
		return signInForm_fxml;
	}
	
	public String getCreatAccountForm_fxml() {
		return creatAccountForm_fxml;
	}

	public String getMacroCalculator_fxml() {
		return macroCalculator_fxml;
	}
	
	public String getFitTracker_fxml() {
		return fitTracker_fxml;
	}
	
	public String getExpandedLabel_fxml() {
		return expandedLabel_fxml;
	}
	
	public String getNutritionLabel_fxml() {
		return nutritionLabel_fxml;
	}
	
	public String getSearchFieldFrame_fxml() {
		return searchFieldFrame_fxml;
	}
	
	public String getSearchListFrame_fxml() {
		return searchListFrame_fxml;
	}
	
	public String getFrame_fxml() {
		return frame_fxml;
	}
	
	public String getSingleTextCell_fxml() {
		return singleTextCell_fxml;
	}
	
	public String getNavigationMenu_fxml() {
		return navigationMenu_fxml;
	}
	
	public String getAnimatedScrollPane_fxml() {
		return animatedScrollPane_fxml;
	}

	public String getFirstPanel_fxml() {
		return firstPanel_fxml;
	}
	
	public String getFourthPanel_fxml() {
		return fourthPanel_fxml;
	}

	public String getButtonNext_fxml() {
		return buttonNext_fxml;
	}

	public String getPageNumberBar_fxml() {
		return pageNumberBar_fxml;
	}

	public String getPreviousButton_fxml() {
		return previousButton_fxml;
	}

	public String getSecondPanel_fxml() {
		return secondPanel_fxml;
	}

	public String getThirPanel_fxml() {
		return thirPanel_fxml;
	}
	
	public String getMenuButton_fxml() {
		return menuButton_fxml;
	}
	
	/* End of the FXML Methods*/
	
	
	/*
	 * Image Methods
	 */
	
	public String getSignOn_gif() {
		return signOn_gif;
	}
		
	public String getStandardButton_Image() {
		return standardButton;
	}
	
	public String getButtonClicked_Image() {
		return buttonCicked;
	}
	
	public String getBackButton_Image() {
		return backButton;
	}
	
	public String getSearchActive_Image() {
		return searchActive_Image;
	}

	public String getSearchStandard_Image() {
		return searchStandard_Image;
	}

	public String getMacroActive_Image() {
		return macroActive_Image;
	}

	public String getMacroStandard_Image() {
		return macroStandard_Image;
	}

	public String getFitActive_Image() {
		return fitActive_Image;
	}

	public String getFitStandard_Image() {
		return fitStandard_Image;
	}
	
	public String getxButton_Black_Image() {
		return xButton_Black_Image;
	}

	public String getxButton_White_Image() {
		return xButton_White_Image;
	}

	public String getSearchButtonClear_Image() {
		return searchButtonClear_Image;
	}

	public String getSearchButtonStandard_Image() {
		return searchButtonStandard_Image;
	}
	
	/* End of the Image Methods*/

	
	/*
	 * Sign in ID Methods
	 */
	
	public String getEmail_textField_id() {
		return email_textField_id;
	}
	
	public String getPassword_Field_id() {
		return password_Field_id;
	}
	
	public String getEmail_confirm_id() {
		return email_confirm_id;
	}
	
	public String getPassword_confirm_id() {
		return password_confirm_id;
	}
	
	public String getFirst_name_id() {
		return first_name_id;
	}
	
	public String getLast_name_id() {
		return last_name_id;
	}
	
	/* End of the Sign in ID Methods*/

	
	/*
	 * Error Methods
	 */
	
	public String getEmptySignInField_Message() {
		return emptySignInField_Message;
	}

	public String getEmptyEmailField_Message() {
		return emptyEmailField_Message;
	}

	public String getEmptyPasswordField_Message() {
		return emptyPasswordField_Message;
	}
	
	public String getNetworkError_Message() {
		return networkError_Message;
	}
	
	/* End of the Error Methods*/

	
	/*
	 * Style Methods
	 */
	
	public String getFx_accent_red() {
		return fx_accent_red;
	}

	public String getFx_accent_orange() {
		return fx_accent_orange;
	}

	public String getFx_accent_green() {
		return fx_accent_green;
	}
	
	public String getFx_text_fill_98FF42() {
		return fx_text_fill_98FF42;
	}

	public String getFx_text_fill_black() {
		return fx_text_fill_black;
	}
	
	/*
	 * CSS Methods
	 */

	public String getSignIn_CSS() {
		return SignIn_CSS;
	}

	public String getMaster_CSS() {
		return Master_CSS;
	}
}