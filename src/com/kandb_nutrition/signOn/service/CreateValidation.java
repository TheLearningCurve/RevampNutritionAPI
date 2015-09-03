package signOn.service;

/*
 * This is the createValidation Class. This class is implemented in the CreateAccountController.
 * We need to verify the user has all the information necessary for an account. 
 * We want the EMAIL ADDRESS to be like this "example@example.com". In coding terms ---> string@string.(2 characters minimum) 
 * The PASSWORD cannot be the email address and it has to have 1 upper case letter, 1 lower case letter, and 1 number. Must be 6 - 15 characters long. 
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import resource.Strings;
import signOn.controllers.CreateAccountController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class CreateValidation implements ChangeListener<String> {
	
	public TextField t;
	public Strings strings;
	public String first_name, last_name, emailAddress, confirm_emailAddress, password, confirm_Password;
	
	public boolean firstName_Filled, lastName_Filled, password_Filled, email_Filled, allFieldsFilled, containsDigit;

	private Pattern email_Pattern, password_Pattern, upperCase_Pattern, lowerCase_Pattern, digit_Pattern;

	private Matcher email_Matcher, password_Matcher, upperCase_Matcher, lowerCase_Matcher, digit_Matcher;

	
	public CreateValidation()
	{
		strings = new Strings();
		EmailValidator();
		PasswordValidator();
	}
	
	public String upperCase;
	public boolean upperCase_Passed;
	
	public String lowerCase;
	public boolean lowerCase_Passed;
	
	private static final String UPPERCASE_PATTERN = "^(?=.*[A-Z])[A-Za-z0-9]+$";
	private static final String LOWERCASE_PATTERN = "^(?=.*[a-z])[A-Za-z0-9]+$";
	private static final String DIGIT_PATTERN = "^(?=.*[0-9])[A-Za-z0-9]+$";


	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String PASSWORD_PATTERN = 
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15})";
	/*
	 * 		(				#   Start of group
  			(?=.*\d)		#   must contains one digit from 0-9
  			(?=.*[a-z])		#   must contains one lowercase characters
  			(?=.*[A-Z])		#   must contains one uppercase characters
              .				#   match anything with previous condition checking
             {6,20}			#   length at least 6 characters and maximum of 20	
			)			# End of group
	 */

	public void EmailValidator() {
		email_Pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public void PasswordValidator() {
		password_Pattern = Pattern.compile(PASSWORD_PATTERN);
		upperCase_Pattern = Pattern.compile(UPPERCASE_PATTERN);
		lowerCase_Pattern = Pattern.compile(LOWERCASE_PATTERN);
		digit_Pattern = Pattern.compile(DIGIT_PATTERN);
	}

	public boolean validateEmail(String email) {
		email_Matcher = email_Pattern.matcher(email);
		return email_Matcher.matches();
	}
	
	public boolean validatePassword(String password) {
		password_Matcher = password_Pattern.matcher(password);
		return password_Matcher.matches();
	}
	
	public boolean validateUpperCase(String password) {
		upperCase_Matcher = upperCase_Pattern.matcher(password);
		return upperCase_Matcher.matches();
	}
	
	public boolean validateLowerCase(String password) {
		lowerCase_Matcher= lowerCase_Pattern.matcher(password);
		return lowerCase_Matcher.matches();
	}
	
	public boolean validateDigit(String password) {
		digit_Matcher= digit_Pattern.matcher(password);
		return digit_Matcher.matches();
	}
	

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		
		if(t.getId().toString().compareTo(strings.getFirst_name_id()) == 0) // First Name Check 
		{
			if(newValue.hashCode() == 0) // White Space
			{
				firstName_Filled = false; // Set Boolean to false because we do not want users with "Empty" names
				getallfieldsFilled(); // We make this call every time to decide whether we need to disable the create account button
			}
			else if(newValue != null && newValue.hashCode() != 0) 
			{
				first_name = newValue; // This is just a check there may be no need for this. 
				firstName_Filled = true; // Set to true when the characters are not empty
				getallfieldsFilled();
			}
		}
		else if(t.getId().toString().compareTo(strings.getLast_name_id()) == 0) // Last Name Check
		{
			if(newValue.hashCode() == 0) // White Space
			{
				lastName_Filled = false;
				getallfieldsFilled();
			}
			else if(newValue != null && newValue.hashCode() != 0)
			{
				last_name = newValue;
				lastName_Filled = true;
				getallfieldsFilled();
			}
		}
		else if(t.getId().toString().compareTo(strings.getEmail_textField_id()) == 0) // First Email Check
		{
			emailAddress = newValue; // We set the emailAddress so we can check and confirm in the confirm field check below
	
			if(validateEmail(newValue) == true) // If the Email Passes the REGEX 
			{
				CreateAccountController.controller.setEmail_error_nonvisible(); // Do not show the error icon
				
				if(confirm_emailAddress != null) // If the confirm email is not null. (The user may have enter in the confirm first.)
				{
					comPareConfrimField(newValue); // Call the method to check if the email and confirm field are the same. 
					getallfieldsFilled();
				}
			}
			else if(validateEmail(newValue) == false)
			{
				CreateAccountController.controller.setEmail_error(); // Show the error icon 
				
				if(confirm_emailAddress != null)
				{
					comPareConfrimField(newValue);
					getallfieldsFilled();
				}
			}
		}
		else if(t.getId().toString().compareTo(strings.getEmail_confirm_id()) == 0) // Email Confirm Check
		{
			confirm_emailAddress = newValue;
			
			if(emailAddress != null && newValue.compareTo(emailAddress) == 0)
			{
				CreateAccountController.controller.setConfirmEmail_Error_nonVisible();
				email_Filled = true;
				getallfieldsFilled();
			}
			else 
			{
				CreateAccountController.controller.setConfirm_Email_error();
				email_Filled = false;
				getallfieldsFilled();
			}
		}
		else if(t.getId().toString().compareTo(strings.getPassword_Field_id()) == 0) // First Password Check
		{
			password = newValue;
			
			upperCase = password.toLowerCase();
			upperCase_Passed = !password.equals(upperCase);
			
			lowerCase = password.toUpperCase();
			lowerCase_Passed = !password.equals(lowerCase);
			
			if(emailAddress == null && validatePassword(newValue) == true)
			{
				CreateAccountController.controller.setPassword_Error_nonVisible();
				getallfieldsFilled();
				passwordProgessBar(1);
				
				if(newValue != null)
				{
					comParePasswordField(newValue);
				}
			}
			else if(validatePassword(newValue) == true && newValue.compareTo(emailAddress) != 0)
			{
				CreateAccountController.controller.setPassword_Error_nonVisible();
				getallfieldsFilled();
				passwordProgessBar(1);
				
				if(newValue != null)
				{
					comParePasswordField(newValue);
				}
			}
			else if(validatePassword(newValue) == false)
			{
				CreateAccountController.controller.setPassword_Error_Icon();
				password_Filled = false;
				containsDigit(password);
				
				if(validateUpperCase(newValue) == true && validateLowerCase(newValue) == true || 
						validateLowerCase(newValue) == true && validateDigit(newValue) == true || 
						validateUpperCase(newValue) == true && validateDigit(newValue) == true)
				{
					passwordProgessBar(.50);
				}
				else if(validateUpperCase(newValue) == true || validateLowerCase(newValue) == true || validateDigit(newValue) == true)
				{
					passwordProgessBar(.25);
					
					if(validateUpperCase(newValue) == true)
					{
						CreateAccountController.controller.seterror_Password_Label_Visible("Must contain a lower case letter and a digit");
					}
					else if(validateLowerCase(newValue) == true)
					{
						CreateAccountController.controller.seterror_Password_Label_Visible("Must contain an upper case letter and a digit");
					}
					else if(validateDigit(newValue) == true)
					{
						CreateAccountController.controller.seterror_Password_Label_Visible("Must contain a lower case letter and upper case letter");
					}
				}
				
				getallfieldsFilled();
				
				if(newValue != null)
				{
					comParePasswordField(newValue);
				}
			}
		}
		else if(t.getId().toString().compareTo(strings.getPassword_confirm_id()) == 0) // Password Confirm Check
		{
			confirm_Password = newValue;
			
			if(password != null && newValue.compareTo(password) == 0)
			{
				CreateAccountController.controller.setConfirmPassword_Error_nonVisible();
				password_Filled = true;
				getallfieldsFilled();
			}
			else 
			{
				CreateAccountController.controller.setConfirm_Password_error();
				password_Filled = false;
				getallfieldsFilled();
			}
		}
		
	}
	
	public final boolean containsDigit(String string) {

	    if (string != null) {
	        for (char character : string.toCharArray()) {
	            if (containsDigit = Character.isDigit(character)) {
	                break;
	            }
	        }
	    }

	    return containsDigit;
	}
	
	public void passwordProgessBar(double d)
	{
		CreateAccountController.controller.updateProgressBar(d);
	}
	
	public void comPareConfrimField(String string)
	{
		if(confirm_emailAddress != null && string.compareTo(confirm_emailAddress) == 0)
		{
			CreateAccountController.controller.setConfirmEmail_Error_nonVisible();
			email_Filled = true;
		}
		else 
		{
			CreateAccountController.controller.setConfirm_Email_error();
			email_Filled = false;
		}
	}
	
	public void comParePasswordField(String string)
	{
		if(confirm_Password != null && string.compareTo(confirm_Password) == 0)
		{
			CreateAccountController.controller.setConfirmPassword_Error_nonVisible();
			password_Filled = true;
		}
		else if(confirm_Password == null)
		{
			CreateAccountController.controller.setConfirmPassword_Error_nonVisible();
		}
		else
		{
			CreateAccountController.controller.setConfirm_Password_error();
		}
	}
	
	public void getallfieldsFilled()
	{
		if(firstName_Filled == true && lastName_Filled == true && password_Filled == true && email_Filled ==true)
		{
			CreateAccountController.controller.create_Button_Enable();
		}
		else
		{
			CreateAccountController.controller.create_Button_Disable();
		}
	}
	
	public void setObject(TextField t)
	{
		this.t = t;
	}

}
