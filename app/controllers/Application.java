package controllers;

import play.mvc.Controller;

public class Application extends Controller {

	/**
	 * index
	 */
	public static void index() {
		// see if login
		String email = session.get("username");
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
	
	public static void logout(){
		// clean up
		
	}
	
//	public static void register(@Email String email, String password,
//			String password_retyped) {
//
//		boolean register = true;
//		boolean flag_register = false;
//		boolean flag_twice = false;
//
//		String userId = session.get("userId");
//		System.out.println("###before register userID: " + userId);
//		if (userId != null) {
//			User user = User.findById(Long.parseLong(userId));
//			if (user != null) {
//				index();
//			}
//		}
//
//		User user = User.find("byEmailAndPassword", email, password).first();
//		if (user != null) {
//			flash.error("Your are already registered. Please go to Login.");
//			index();
//		}
//
//		if (validation.email(email).ok == false) {
//			flash.error("Invalid e-mail address. Please check the right spelling and try to register again.");
//			index();
//		}
//
//		if (user == null) {
//
//			if (password.equals(password_retyped) == false) {
//				
//				flag_twice = true;
//				flash.error("Password mismatch. Please try to register again.");
//				// index();
//				render("Application/index.html", flag_twice, register);
//
//			} else {
//					flag_register = true;
//					User newUser = new User(email, password);
//					newUser.save();
//					session.put("userId", newUser.id);
//					// index();
//					render("Application/index.html", flag_register, register);
//			}
//		}
//	}
//
//	public static void index() {
//		String userId = session.get("userId");
//
//		if (userId != null) {
//			User user = User.findById(Long.parseLong(userId));
//			if (user != null) {
//				List<Post> posts = user.posts;
//				List<Post> allPostsOrderedByLikes = Post.getAllOrderedByLikes();
//				List<Post> allPostsOrderedByTags = Post.findAll();
//				render(user, posts, allPostsOrderedByLikes,
//						allPostsOrderedByTags);
//			}
//		}
//
//		// if no (valid) user authenticated
//
//		Post mostLikedPost = Post.getMostLikedWithImages();
//		Post recentlyFinishedPost = Post.getMostRecentWithImages();
//
//		render(mostLikedPost, recentlyFinishedPost);
//	}
//
//	// Login with e-mail address and password.
//	public static void login(String email, String password) {
//
//		boolean flag_login = false;
//		boolean login = false;
//
//		 //if user already login
//		 String userId = session.get("userId");
//		 if (userId != null) {
//		 User user = User.findById(Long.parseLong(userId));
//		 if (user != null) {
//		 MyProfile.page();
//		 }
//		 }
//
//		User user = User.find("byEmail", email).first();
//
//		if (user != null) {
//			if (user.password.equals(password) == true) {
//				session.put("userId", user.id);
//				flag_login = true;
//				render("Application/index.html", flag_login, login, email);
//			} else {
//				flash.error("Password mismatch. Please try to login again.");
//				render("Application/index.html", flag_login, login, email);
//			}
//		} else {
//			flash.error(email + " does not exist. Please go to Register.");
//		}
//
//		index();
//		render("Application/index.html", flag_login, login, email);
//
//	}
//
//	public static void logout() {
//
//		if (session.contains("googleAccessToken")) {
//			String token = session.get("googleAccessToken");
//			System.out.println("logout: Google access token :" + token);
//			try {
//				new URL("https://accounts.google.com/o/oauth2/revoke?token="
//						+ token).openConnection().getInputStream();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		// session.put("userId", null);
//		session.clear();
//		index();
//	}
//
//	public static void pageNotAuthenticated(int error) {
//		if (error == 1) {
//			renderArgs.put("passError", "true");
//		} else if (error == 2) {
//			renderArgs.put("userExists", "true");
//		} else if (error == 3) {
//			renderArgs.put("loginError", "true");
//		} else if (error == 4) {
//			renderArgs.put("emailError", "true");
//		}
//
//		renderArgs.put("authenticated", "false");
//
//		render();
//	}

}