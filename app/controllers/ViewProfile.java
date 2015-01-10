package controllers;


import models.*;
import play.mvc.*;

public class ViewProfile extends Controller {
	
	public static void page(Long Id) {
		
		String myUsername = session.get("username");
		String userId = session.get("userId");

		
		System.out.println("From MyProfile submit#current username: "+myUsername);
		// System.out.println("follo:" + folloUserid);
		
		boolean followed = false;
		if (Long.parseLong(userId) == Id) {
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
