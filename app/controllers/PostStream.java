package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Post;
import models.User;
import play.modules.paginate.ModelPaginator;
import play.mvc.Controller;

public class PostStream extends Controller{
	public static void page(int size, int page)
	{
		String userId = session.get("userId");
		
		System.out.println("From PostStream#current username: "+userId);
		boolean flag_login = false;
		// 'size' is the number of elements displayed per page
	    // 'page' is the current page index, starting from 1.
	    int start = (page-1) * size;
	    Post.find("byPostType", "news");
		List<Post> posts = Post.find("postType = ? order by postingDate desc", "news").from(start).fetch(size);
		if (userId != null)
		{
			User user = User.findById(Long.parseLong(userId));
			//User user = User.find("byEmail", username).first();
			if (user != null)
			{
				flag_login = true;
				String email = session.get("username");
				
				//render(user, posts);
				render(posts, flag_login, email);
			}

	
		}
		render(posts, flag_login, page, size);
		//Application.index();
	}
}
