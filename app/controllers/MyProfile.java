package controllers;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import models.Image;
import models.Post;
import models.User;
import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class MyProfile extends Controller {

	public static void submit(String email, String password, String fullname,
			String country, String city, String gender, String religion,
			Date birthday, Blob photoData, String tags, String folloUserid,
			String unFollowUserid, String delTag) {
		Long userId = Long.parseLong(session.get("userId"));
		// System.out.println("follo:" + folloUserid);
		if (userId != null) {
			User user = User.findById(userId);
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
		String userId = session.get("userId");
		if (userId != null) {
			User user = User.findById(Long.parseLong(userId));
			if (user != null) {
				// renderArgs.put("ajax", "true");
				// renderTemplate("tags/ajax.html");
				//String tagString = getTags(user.tags);
				//render(user, tagString);
			}
		}
		Application.index();
	}

	public static void forwardToMyProfile() {
		MyProfile.page();
	}

}
