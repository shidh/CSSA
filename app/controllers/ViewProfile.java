package controllers;


import models.*;
import play.mvc.*;

public class ViewProfile extends Controller {
	
	public static void page(String userName) {
		
		String myUsername = session.get("username");
		
		System.out.println("From MyProfile submit#current username: "+myUsername);
		// System.out.println("follo:" + folloUserid);
		
		boolean followed = false;
		if (myUsername == userName) {
			MyProfile.page();
		}
		
		if (userName != null) {
			User user = User.find("byEmail", userName).first();
			User me = User.find("byEmail", myUsername).first();

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
