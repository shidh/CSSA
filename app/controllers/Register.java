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
					
					String subject = "欢迎加入慕尼黑中国学生学者联合会! Welcome to CSSA Munich!";
					String msg = "Thank you for registering.\n\nPlease note: This email was sent from an auto-notification system that cannot accept incoming email. Please do not reply to this message. \n\nBest Regards\n\nCSSA Munich\n(Verein der Chinesischen Studenten & Wissenschaftler e. V.)\n-----------------------------------\nGoogle mailing list: http://www.cssa-munich.de/public/images/mail.html ;\nWechat: cssamunich;\nFacebook: CSSA Munich.\n\nInfo: chinese.muenchen@gmail.com;\nSupport: support@cssa-munich.de\n\n";

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
