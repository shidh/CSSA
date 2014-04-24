package controllers;

import java.util.Date;

import play.Play;
import play.data.validation.Required;
import play.libs.Crypto;
import play.libs.Time;
import play.mvc.Before;
import play.mvc.Http;
import models.User;
import controllers.Secure.Security;

public class Login extends Security {
	public static boolean authenticate(String email, String password) {
		boolean flag_login = false;
		boolean login = true;
    	if (User.connect(email, password) != null)
    	{
    		flag_login = true;
    		render("Application/index.html", flag_login, login, email);
    	} else {
    		render("Application/index.html", flag_login, login, email);
    	}
		return User.connect(email, password) != null;
    }
}
