package controllers;

import models.*;
import play.mvc.*;

public class wenti extends Controller {
	
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
	
}
