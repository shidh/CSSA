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
		// 'size' is the number of elements displayed per page
	    // 'page' is the current page index, starting from 1.
		// pageTotal is the total page number
	    int start = (page-1) * size;
		List<Event> posts = Event.find("order by postingDate desc").from(start).fetch(size);
		int pageTotal=(int) Math.ceil((double)Event.count()/size);
		if (username != null)
		{
			//User user = User.findById(Long.parseLong(userId));
			User user = User.find("byEmail", username).first();

			
			if (user != null)
			{
				flag_login = true;
				String email = session.get("username");

				//render(user, posts);
				render(posts, flag_login, email, size, page,pageTotal);
			}

	
		}
		//set true to ask user login firstly
		//boolean promote_login = true;
		//Application.index(promote_login, false);
		render(posts, flag_login, size, page,pageTotal);


	}
}
