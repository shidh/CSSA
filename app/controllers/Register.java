package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

import models.*;

public class Register extends Controller {
	public static void register(String email, String password1, String password2) throws EmailException {
		boolean register = true;
		boolean flag_register = false;
		boolean flag_twice = false;
		if (password1.equals(password2))
			try {
				if (User.find("byEmail", email).first() == null) {
					flag_register = true;
					new User(email, password1).save();
					
					String subject = "Welcome to join Munich CSSA!";
					String msg = "Congratulations! Register successfully!";
					Mails.sendEmail(email, subject, msg);
					
					render("Application/index.html", flag_register, register);
				} else {
					render("Application/index.html", flag_register, register);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			flag_twice = true;
			render("Application/index.html", flag_twice, register);
		}
	}
}
