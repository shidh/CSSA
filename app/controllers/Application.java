package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void register(String email, String password1, String password2) {
		boolean register = true;
		boolean flag_register = false;
		boolean flag_twice = false;
		if (password1.equals(password2)) {
			if (User.find("byEmail", email).first() == null) {
				flag_register = true;
				new User(email, password1).save();
				render("Application/index.html", flag_register, register);
			} else {
				
				render("Application/index.html", flag_register, register);
			}
		} else {
			flag_twice = true;
			render("Application/index.html", flag_twice, register);
		}
	}
}