package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import models.Post;
import models.User;
import play.mvc.Controller;

public class PostStream extends Controller{
	public static void page()
	{
		String userId = session.get("userId");
		
		if (userId != null)
		{
			User user = User.findById(Long.parseLong(userId));
			
			if (user != null)
			{
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
				
				render(user, posts);
			}
		}
		
		Application.index();
	}
}
