package com.xtel.vngolf.api.common.mail;

import java.util.regex.Pattern;

import com.tbv.utils.textbase.StringUtil;

public class MailValidation {

	public MailValidation() {
		
	}
	
	public static boolean isValidEmail(String email) {
		
		if(StringUtil.isNullOrEmpty(email)) {
			return false;
		}
		
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
    			+ "[a-zA-Z0-9_+&*-]+)*@" 
        		+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" 
        		+ "A-Z]{2,7}$"; 
                              
        Pattern pattern = Pattern.compile(emailRegex);
        
        return pattern.matcher(email).matches(); 
    }
}
