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
		String username = session.get("username");
		
		System.out.println("From PostStream#current username: "+username);
		boolean flag_login = false;
		if (username != null)
		{
			//User user = User.findById(Long.parseLong(userId));
			User user = User.find("byEmail", username).first();
			if (user != null)
			{
				flag_login = true;
				String email = session.get("username");
				//get all posts of all users
				//LinkedList<Post> posts = new LinkedList<Post>();
				List posts = Post.findAll();

				
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
		
		//Application.index();
	}
}
