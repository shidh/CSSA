package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Post;
import models.User;
import play.mvc.Controller;

public class PostStream extends Controller{
	public static void page()
	{
		String userId = session.get("userId");
		
		System.out.println("From PostStream#current username: "+userId);
		boolean flag_login = false;
		if (userId != null)
		{
			User user = User.findById(Long.parseLong(userId));
			//User user = User.find("byEmail", username).first();
			if (user != null)
			{
				flag_login = true;
				String email = session.get("username");
				//get all posts of all users
				//List<Post> posts = Post.findAll();
				List<Post> posts = Post.find("byPostType", "news").fetch();

				//sort according to date
				Collections.sort(posts, new Comparator<Post>(){

					@Override
					public int compare(Post post1, Post post2)
					{
						return post1.postingDate.compareTo(post2.postingDate);
					}
				});
				
				//render(user, posts);
				render(posts, flag_login, email);
			}

	
		}
		
		Application.index();
	}
}
