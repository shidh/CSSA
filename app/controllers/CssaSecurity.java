package controllers;

import java.util.Date;

import controllers.Secure.Security;
import models.User;
import play.Play;
import play.data.validation.Required;
import play.libs.Crypto;
import play.libs.Time;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.utils.Java;


public class CssaSecurity extends Secure.Security {
	
    static boolean authenticate(String username, String password) {
			User user = User.find("byEmail", username).first();
			User user2 = User.find("byUsername", username).first();
			
			boolean isSuccess = false;
			if(user != null && user.password.equals(password)){ 
				session.put("userId", user.getId());
				isSuccess = true;
			}else if(user2 != null && user2.password.equals(password)){
				session.put("userId", user2.getId());
				isSuccess = true;
			}
			return isSuccess;
    }
    
   static void onDisconnected() {
		Application.index();
	}
   
   static boolean check(String profile) {
       User user = User.find("byEmail", connected()).first();
       if ("administrator".equals(profile)) {
           return user.isAdmin;
       }
       else {
           return false;
       }
   } 
    
}