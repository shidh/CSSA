package controllers;

import play.mvc.Controller;

public class Application extends Controller {

	/**
	 * index
	 */
	public static void index(boolean promote_login) {
		// see if login
		String email = session.get("username");
		System.out.println(session.get("userId"));
		if(email != null){
			// has login
			// redirect to home
			// set flags and emails
			boolean flag_login = true;
			boolean login = true;
			render(flag_login, login, email);
		}else{
			// not login
			render(promote_login);
		}
	}
	
	public static void index() {
		// see if login
		String email = session.get("username");
		System.out.println(session.get("userId"));
		if(email != null){
			// has login
			// redirect to home
			// set flags and emails
			boolean flag_login = true;
			boolean login = true;
			render(flag_login, login, email);
		}else{
			// not login
			render();
		}
	}
}