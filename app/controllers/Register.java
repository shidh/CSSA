package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import java.text.SimpleDateFormat; //添加java时间模块
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail; // 增加html mail模块
import play.libs.Mail; //增加mail模块，mail.send

import models.*; //报名后自动发送确认邮件，邮件名称为活动title，回执内容需要在数据库新建一栏，并在编辑活动页面增加输入活动回执内容的编辑器。

public class Register extends Controller {    
	public static void	register(String email, String password, String gender, String password2, String registerDate, String fullname, String organization, String major, String mobileNo) throws EmailException
{         boolean register = true;         boolean flag_register = false;
boolean flag_twice = false;         if (password.equals(password2))
try {                 if (User.find("byEmail", email).first() == null) {
					flag_register = true;  
					SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
					registerDate = format.format(new Date());
		            new User(email, password, gender, registerDate, fullname, organization, major, mobileNo).save();   //增加数据时，请注意添加user models里对应的数据
					
		            							//自动回执模块开始
							String emailadress = email;
							String subject = "欢迎加入慕尼黑中国学生学者联合会! Welcome to CSSA Munich!";
							String msg = "<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">感谢您注册慕尼黑中国学生学者联合会（CSSA Munich）会员。</span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">Thank you for registering.</span></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>此邮件由系统自动生成，请不要直接回复此邮件。</p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">Please note: This email was sent from an auto-notification system that cannot accept incoming email. Please do not reply to this message. </span></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">祝好</span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">Best Regards </span></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">慕尼黑中国学生学者联合会</span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">CSSA Munich (</span>Chinese Students and Scholars Association Munich e.V.<span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">) </span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\"><img alt=\"\" src=\"www.cssa-munich.de/public/images/upload/19232_334771059624_4683914_n.jpg\" style=\"height:113px; width:204px\" /></span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">------------------------------</span><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">------------------------------------------------------------</span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">微信公众号 /&nbsp;Wechat: cssamunich</span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">微博 / Weibo:&nbsp;</span>慕尼黑学联CSSA</p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">脸书 / Facebook: CSSA Munich</span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">公共邮箱 / Info:</span><a href=\"mailto:chinese.muenchen@gmail.com\" style=\"color: rgb(17, 85, 204); font-family: arial, sans-serif; font-size: 12.8000001907349px; line-height: normal;\" target=\"_blank\">chinese.muenchen@gmail.com</a><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">; </span></p>\r\n\r\n<p><span style=\"color:rgb(34, 34, 34); font-family:arial,sans-serif; font-size:12.8000001907349px\">遇到问题？请联系 Support:&nbsp;</span><a href=\"mailto:support@cssa-munich.de\" style=\"color: rgb(17, 85, 204); font-family: arial, sans-serif; font-size: 12.8000001907349px; line-height: normal;\" target=\"_blank\">support@cssa-munich.de</a></p>\r\n";

							HtmlEmail autoemail = new HtmlEmail();	
							try {
								autoemail.addTo(emailadress);
								autoemail.setFrom("info@cssa-munich.de", "CSSA Munich");
								autoemail.setSubject(subject);
								// // embed the image and get the content id
								// URL url = null;
								// url = new URL(
								// "https://www.playframework.com/assets/images/logos/normal.png");
								// String cid = null;
								// cid = email.embed(url, "play logo");
								// set the html message
								autoemail.setHtmlMsg(msg);
								// // set the alternative message
								// email.setTextMsg("Your email client does not support HTML, too bad :(");
							} catch (EmailException e) {
								e.printStackTrace();
							}
							autoemail.setCharset("UTF-8");// 中文乱码
							Mail.send(autoemail);

							//自动回执模块结束	

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
