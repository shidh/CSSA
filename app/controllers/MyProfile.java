package controllers;

import java.util.Date;

import models.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class MyProfile extends Controller {

	public static void submit(String email, String password, String fullname,
			String major, String address, String gender,
			Date birthday, String cssaID, String currentDegree,
			String mobileNo, String organization, String lastDegree) {
		
		
		String username = session.get("username");
		
		System.out.println("From MyProfile submit#current username: "+username);
		// System.out.println("follo:" + folloUserid);
		if (username != null) {
			User user = User.find("byEmail", username).first();
			if (user != null) {
				//System.out.println("Now name is:" + fullname);
				if (fullname != null) {
					user.fullname = fullname;
				}
				if (address != null) {
					user.address = address;
				}
				if (gender != null) {
					user.gender = gender;
				}
				if (birthday != null) {
					user.birthday = birthday;
				}
				if (major != null) {
					user.major = major;
				}
				if (cssaID != null) {
					user.cssaID = cssaID;
				}
				if (currentDegree != null) {
					user.currentDegree = currentDegree;
				}
				if (mobileNo != null) {
					user.mobileNo = mobileNo;
				}
				if (organization != null) {
					user.organization = organization;
				}
				if (lastDegree != null) {
					user.lastDegree = lastDegree;
				}
				user.save();
			}

			MyProfile.page();
		}
	}

	public static void page() {
		boolean flag_login = false;
		String email = null;

		String username = session.get("username");
		
		System.out.println("From MyProfile page#current username: "+username);
		if (username != null) {
			User user = User.find("byEmail", username).first();
			if (user != null) {
				email = username;
				flag_login = true;
				// renderArgs.put("ajax", "true");
				// renderTemplate("tags/ajax.html");
				//String tagString = getTags(user.tags);
				render(user, flag_login, email);
			}
		}
		Application.index();
	}

	public static void forwardToMyProfile() {
		MyProfile.page();
	}

}
