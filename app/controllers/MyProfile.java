package controllers;

import java.util.Date;

import models.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class MyProfile extends Controller {

	public static void submit(String email, String password, String fullname,
			String country, String city, String gender, String religion,
			Date birthday, Blob photoData, String tags, String folloUserid,
			String unFollowUserid, String delTag) {
		
		
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
				if (city != null) {
					user.city = city;
				}
				if (gender != null) {
					user.gender = gender;
				}
				if (birthday != null) {
					user.birthday = birthday;
				}

				//System.out.println("Photo :" + photoData);
				if (photoData != null) {
					user.image = new Image(photoData);
					user.image.save();
				}

				if (folloUserid != null) {
					User folloUser = User.findById(Long.parseLong(folloUserid));
					if (!user.followed.contains(folloUser)) {
						user.followed.add(folloUser);
					}
				}

				if (unFollowUserid != null) {
					User unFollowUser = User.findById(Long
							.parseLong(unFollowUserid));
					if (user.followed.contains(unFollowUser)) {
						user.followed.remove(unFollowUser);
					}
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
