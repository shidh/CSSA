package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import models.Image;
import models.Post;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Admin extends Controller {
	
	public static void index() {
		newPost();
	} 
	
	public static void newPost(){
		// get username
		String username = session.get("username");
		
		int selectedIndex = 0;// selected index
		render(username,selectedIndex);
	}
	
	public static void allPosts(){
		int selectedIndex = 1;// selected index
		render(selectedIndex);
	}
	
	public static void savePost(String title, String editor, String username){
		// find user
		User user = User.find("byEmail", username).first();
		
		Post post =new Post(title, new Date(), editor, null, user);
		// save
		post.save();
		
		render("Admin/allPosts.html");
	}
	
	public static void imageBrowseUrl(){
		// find all images
		List<Image> images = Image.findAll();
		render(images);
	}
	
	public static void uploadUrl(File upload){
		// stores image
		Image image = new Image(null);
		image.setUrl(upload.getPath());
		image.save();
		
		render();
	}
}