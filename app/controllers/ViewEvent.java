package controllers;

import models.Comment;
import models.Event;
import models.User;
import play.mvc.Controller;

public class ViewEvent extends Controller {
	
	public static void page(Long eventId, boolean isFull, boolean isSigned) {
		boolean flag_login = false;
		String email = null;

		if (eventId != null) {
			Event post = Event.findById(eventId);
			System.out.println("From ViewEvent#confirmedUsers username: "+post.confirmedUsers);
			//System.out.println("From ViewEvent#capacity: "+post.capacity);

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

				render(post, user, flag_login, email, isFull, isSigned);

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
		EventStream.page();
	}
	
	public static void signUp(Long eventId) {
		if (eventId != null) {
			boolean isFull = isFull(eventId);
			boolean isSigned = false;
			Event post = Event.findById(eventId);
			if (post != null) {
				String username = session.get("username");
				User user = User.find("byEmail", username).first();
				if (user != null) {
					if(isFull){
						System.out.println("###event is already full");
						//System.out.println("###1: "+post.onWaitingListUsers.size());
						post.onWaitingListUsers.add(user);
						user.waitingEvent = post;
						post.save();
						user.save();
						isSigned = true;
						//System.out.println("###2: "+post.onWaitingListUsers.size());
						//Event is full
						ViewEvent.page(eventId, isFull, isSigned);
					}else{
						//check is signuped or not
						if(post.confirmedUsers.contains(user) || post.onWaitingListUsers.contains(user)){
							ViewEvent.page(eventId, isFull, isSigned);
							System.out.println("###already signed up");
						}else{
							//System.out.println("##1: "+post.confirmedUsers.size());
							post.confirmedUsers.add(user);
							user.confirmedEvent = post;
							post.save();
							user.save();
							isSigned = true;
							System.out.println("###confirmed user: "+username);
							System.out.println("##2: "+post.confirmedUsers.size());

							ViewEvent.page(eventId, isFull, isSigned);
						}

					}
				}
			}
		}
		EventStream.page();

	}
	
	static boolean isFull(Long eventId){
		boolean isFull = false;
		if (eventId != null) {
			Event post = Event.findById(eventId);
			if (post != null) {
				if (post.confirmedUsers.size() < post.capacity){
					isFull = false;
					//System.out.println("#not full");
				}else{
					isFull = true;
				}
			}	
		}

		return isFull;
	}
}

