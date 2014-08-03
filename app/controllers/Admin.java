package controllers;

import java.util.Date;

import models.Post;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Admin extends Controller {
	
	public static void index() {
		// get username
		String username = session.get("username");
		render(username);
	}
	
	public static void savePost(String title, String editor, String username){
		// find user
		User user = User.find("byEmail", username).first();
		
		Post post =new Post(title, new Date(), editor, null, user);
		// save
		post.save();
		
		Post p=Post.find("byTitle", title).first();
		System.out.println(p.postContent);
	}
}
