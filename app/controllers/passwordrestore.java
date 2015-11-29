package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import models.User;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.google.gson.Gson;

import play.libs.Mail;
import play.mvc.Controller;

import models.*;
import play.mvc.*;
	
public class passwordrestore extends Controller {

	public static void page() {
		
		String userId = session.get("userId");
		if (userId != null) {
			User user = User.findById(Long.parseLong(userId));
			boolean flag_login = true;
			String email = session.get("username");
			if (user != null) {
				render(user, flag_login, email);
			}
		}else{
	       render();
		}

	}
	
	/**
	 * reset password for a given email
	 */
	public static void resetPass(String email) {
		Gson gson = new Gson();
		boolean isRJobDone = false;
		User user = User.find("byEmail", email).first();
		if (user != null){		
			String newPass = getRandomString(12); // 生成12位随机密码

			user.password = newPass;
			user.save();
			
			String targetEmail = email+";";
			isRJobDone = sendEmail(targetEmail, "CSSA 重置密码", "亲爱的学联用户您好，您的学联账号密码已重置，请使用此密码登陆:"+newPass); // 邮箱地址，主题，正文
			System.out.println("isRJobStarted: " + isRJobDone);
		}
		
			renderJSON(gson.toJson(isRJobDone));
	}
	
	public static boolean sendEmail(String targetEmail, String subject,
			String emailEditor) {

		HtmlEmail email = new HtmlEmail();
		try {
			String[] split = targetEmail.split(";");
			for (int i = 0; i < split.length; i++) {
				email.addTo(split[i]);
			}
			email.setFrom("info@cssa-munich.de", "CSSAMunich Admin"); //显示邮箱地址和名字
			email.setSubject(subject);
			// set the html message
			email.setHtmlMsg(emailEditor);
			// // set the alternative message
			// email.setTextMsg("Your email client does not support HTML, too bad :(");

			email.setCharset("UTF-8");// 中文乱码
			Mail.send(email);
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}
	/** 
     * get ramdom pass
     * @param length
     * @return
     */
    public static String getRandomString(int length) {  //生成随机密码模块
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz"); 
        StringBuffer sb = new StringBuffer(); 
        Random r = new Random(); 
        int range = buffer.length(); 
        for (int i = 0; i < length; i ++) { 
            sb.append(buffer.charAt(r.nextInt(range))); 
        } 
        return sb.toString(); 
    } 

	
}