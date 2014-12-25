package controllers;

import java.util.List;

import models.*;
import play.mvc.*;

public class Download extends Controller {
	
	public static void page() {
		
		String userId = session.get("userId");
		List<DownloadFile> downloadFiles = DownloadFile.find("").fetch();
		if (userId != null) {
			User user = User.findById(Long.parseLong(userId));
			boolean flag_login = true;
			String email = session.get("username");
			if (user != null) {
				render(user, flag_login, email, downloadFiles);
			}
		}else{
	       render(downloadFiles);
		}

	}
	
}

