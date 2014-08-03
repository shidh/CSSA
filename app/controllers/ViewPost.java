package controllers;

import models.Comment;
import models.Post;
import models.User;
import play.mvc.Controller;

public class ViewPost extends Controller {
	
	public static void page(Long postId) {
		if (postId != null) {
			Post post = Post.findById(postId);
			
			if (post != null) {
				User user = null;
				String userId = session.get("userId");
			
				if (userId != null) {
					user = User.findById(Long.parseLong(userId));
				}

				render(post, user);
			}
		}

		Application.index();
	}

	public static void postComment(Long postId, String commentText,
			Integer commentRating) {
		if (postId != null) {
			Post post = Post.findById(postId);

			if (post != null) {
				String userId = session.get("userId");
				if (userId != null) {
					User sender = User.findById(Long.parseLong(userId));

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

		ViewPost.page(postId);
	}
}
