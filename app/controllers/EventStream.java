package controllers;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.*;
import play.mvc.Controller;

public class EventStream extends Controller{

	public static void page(int size, int page)
	{
		String username = session.get("username");
		
		System.out.println("From EventStream#current username: "+username);
		boolean flag_login = false;
		if (username != null)
		{
			//User user = User.findById(Long.parseLong(userId));
			User user = User.find("byEmail", username).first();
			if (user != null)
			{
				flag_login = true;
				String email = session.get("username");

				// 'size' is the number of elements displayed per page
			    // 'page' is the current page index, starting from 1.
			    int start = (page-1) * size;
				List<Event> posts = Event.find("order by postingDate desc").from(start).fetch(size);

				//render(user, posts);
				render(posts, flag_login, email, size, page);
			}

	
		}
		boolean promote_login = true;
		Application.index(promote_login);
	}
}
