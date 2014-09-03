package controllers;

import models.Comment;
import models.Event;
import models.User;
import play.mvc.Controller;

public class ViewEvent extends Controller {
	
	public static void page(Long eventId) {
		boolean flag_login = false;
		String email = null;

		if (eventId != null) {
			Event post = Event.findById(eventId);
			
			if (post != null) {
				User user = null;
				String username = session.get("username");
				System.out.println("From ViewEvent#current username: "+username);

				if (username != null) {
					//user = User.findById(Long.parseLong(userId));
				    user = User.find("byEmail", username).first();
				    flag_login = true;
					email = session.get("username");
				}
				//System.out.println("From ViewPost#current postContent: "+post.postContent);
				//System.out.println("From ViewPost#current description: "+post.description);

				render(post, user, flag_login, email);

			}
		}

		//TODO with alerts to say "you need to login to see an event"
		Application.index();
	}

	public static void postComment(Long eventId, String commentText,
			Integer commentRating) {
		if (eventId != null) {
			Event post = Event.findById(eventId);

			if (post != null) {
				String username = session.get("username");
				User user = User.find("byEmail", username).first();

				if (username != null) {
					User sender = User.find("byEmail", username).first();

					if (sender != null) {
						Comment comment = new Comment(commentText,
								commentRating, sender, post);
						comment.save();

						post.refresh();
						post.rating = new Double(post.rating.doubleValue()
								+ commentRating.intValue());
						post.save();
					}
				}
			}
		}

		ViewEvent.page(eventId);
	}
}
