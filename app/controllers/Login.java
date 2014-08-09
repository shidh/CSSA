package controllers;

import models.User;
import play.data.validation.Required;
import controllers.Secure.Security;

public class Login extends Security {
	public static boolean authenticate(@Required String username, String password, boolean remember) {
		System.out.println("authenticate again");
		System.out.println(session.get("username"));
		boolean flag_login = false;
		boolean login = true;
		String email = username;
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
