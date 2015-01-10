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
			System.out.println("From ViewEvent#confirmedUsers username: "+post.confirmedUsers);
			//System.out.println("From ViewEvent#capacity: "+post.capacity);

			//boolean isFull = ViewEvent.isFull(eventId);
			if (post != null) {
				String username = session.get("username");
				System.out.println("From ViewEvent#current username: "+username);
				User user = null;

				if (username != null) {
					//user = User.findById(Long.parseLong(userId));
					User user1 = User.find("byEmail", username).first();
					User user2 = User.find("byUsername", username).first();
					
					if(user1 == null){
						user = user2; 
					}else if(user2 == null){
						user = user1;
					}
				    
				    flag_login = true;
					email = session.get("username");
				}
				System.out.println("confirm list: "+post.confirmedUsers);

				if(post.onWaitingListUsers.size()>0){
					System.out.println("person on the 1st of waiting list: "+post.onWaitingListUsers.get(0).fullname);
				}else{
					System.out.println("no one on the waiting list");
				}
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
//						user.waitingEvent = post; //我改了数据库 --林
						post.save();
						user.save();
						isSigned = true;
						//System.out.println("###2: "+post.onWaitingListUsers.size());
						//Event is full
						ViewEvent.page(eventId);
					}else{
						//check is signuped or not
						if(post.confirmedUsers.contains(user) || post.onWaitingListUsers.contains(user)){
							ViewEvent.page(eventId);
							System.out.println("###already signed up");
						}else{
							//System.out.println("##1: "+post.confirmedUsers.size());
							post.confirmedUsers.add(user);
//							user.confirmedEvent = post; // 我改了数据库 -- 林
							post.save();
							user.save();
							isSigned = true;
							System.out.println("###confirmed user: "+username);
							System.out.println("##2: "+post.confirmedUsers.size());

							ViewEvent.page(eventId);
						}

					}
				}
			}
		}
		EventStream.page(10,1);

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

