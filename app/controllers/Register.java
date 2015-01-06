package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

import models.*;

public class Register extends Controller {
	public static void register(String email, String password, String password2) throws EmailException {
		boolean register = true;
		boolean flag_register = false;
		boolean flag_twice = false;
		if (password.equals(password2))
			try {
				if (User.find("byEmail", email).first() == null) {
					flag_register = true;
					new User(email, password).save();
					
					String subject = "欢迎加入慕尼黑中国学生学者联合会!";
					String msg = "恭喜您成功注册，成为CSSAM慕尼黑中国学生学者联合会的一员。";
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
