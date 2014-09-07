package controllers;

import models.*;
import play.mvc.*;

public class About extends Controller {
	
	public static void page() {
		
		String userId = session.get("userId");
		
		if (userId != null) {
			User user = User.findById(Long.parseLong(userId));

			if (user != null) {
				render(user);
			}
		}else{
	       Application.index();
		}

	}
	
}
