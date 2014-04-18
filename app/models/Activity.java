/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

/**
 * @author allen
 * 
 */
public class Activity extends Post {
	public boolean isClosed;
	
	@ElementCollection
	public List<String> tags;

	@OneToMany(mappedBy = "Activity", cascade = CascadeType.ALL)
	public List<User> confirmedUsers;
	
	@OneToMany(mappedBy = "Activity", cascade = CascadeType.ALL)
	public List<User> onWaitingListUsers;

	
	public Activity(String title, String description,
			MapLocation mapLocation, Date postingDate, User sender,
			PostContent content, List<String> tags) {
		super(title, description, mapLocation, postingDate, sender, content,
				tags);
		// TODO Auto-generated constructor stub
	}

	public static Post getMostLiked() {

		List<Post> chronologicalPosts = Post.findAll();

		if (chronologicalPosts.size() > 1) {

			List<Post> posts = Post.find("order by rating desc").fetch();

			return posts.get(0);
		}

		return null;
	}

}
