package controllers;

import java.util.List;

import com.google.gson.Gson;

import models.Post;
import models.User;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Controller;

public class PostStream extends Controller{
	public static void page(int size, int page)
	{
		String userId = session.get("userId");
		
		System.out.println("From PostStream#current username: "+userId);
		boolean flag_login = false;
		// 'size' is the number of elements displayed per page
	    // 'page' is the current page index, starting from 1.
		// pageTotal is the total page number
	    int start = (page-1) * size;
	    List<Post> allPost=Post.find("byPostType", "news").fetch();
		List<Post> posts = Post.find("postType = ? order by postingDate desc", "news").from(start).fetch(size);
		int pageTotal=(int) Math.ceil((double)allPost.size()/size);
		if (userId != null)
		{
			User user = User.findById(Long.parseLong(userId));
			//User user = User.find("byEmail", username).first();
			if (user != null)
			{
				flag_login = true;
				String email = session.get("username");
				
				//render(user, posts);
				render(posts, flag_login, email, page, size,pageTotal);
			}

	
		}
//		System.out.println(posts.get(0).getContent().getPictures().get(0).getUrl());
		render(posts, flag_login, page, size,pageTotal);
		//Application.index();
	}
}
