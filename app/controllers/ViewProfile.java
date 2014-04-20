package controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import models.Image;
import models.Post;
import models.User;
import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class ViewProfile extends Controller {

	public static void page(Long Id) {
		Long userId = Long.parseLong(session.get("userId"));
		boolean followed = false;
		if (userId == Id) {
			MyProfile.page();
		}
		if (Id != null) {
			User user = User.findById(Id);
			User me = User.findById(userId);
			if (user != null) {
				if (me.followed.contains(user)) {
					followed = true;
					System.out.println(user.fullname + " has been followed ");

				}
				render(user, followed);
			}
		}
		// MainPage.index();
		MyProfile.page();
	}

}
