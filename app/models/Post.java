
/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;


/**
 * @author allen
 *
 */
@Entity
public class Post extends Model {
	public String title;
	public String description;
	public Date postingDate;
	
	@Column(length=5000)
	public String postContent;

	public Double rating;

	@OneToOne
	public MapLocation mapLocation;

	@OneToOne
	public PostContent content;

	@ManyToOne
	public User sender;

	@ElementCollection
	public List<String> tags;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	public List<Comment> comments;
	

	/**
	 * 
	 * @param title
	 * @param postingDate
	 * @param postContent
	 * @param content
	 * @param sender
	 */
	public Post(String title, Date postingDate, String postContent,
			PostContent content, User sender) {
		super();
		this.title = title;
		this.postingDate = postingDate;
		this.postContent = postContent;
		this.content = content;
		this.sender = sender;
	}

	public Post(String title, String description, String postContent, MapLocation mapLocation,
			Date postingDate, User sender,
			PostContent content, List<String> tags) {
		super();
		this.title = title;
		this.description = description;
		this.mapLocation = mapLocation;
		this.postingDate = postingDate;
		this.sender = sender;
		this.content = content;
		this.tags = tags;
		this.rating = 0.0;
		this.postContent =postContent;
	}

	public static Post getMostRecent() {
		List<Post> posts = Post.findAll();

		if (posts.size() > 0) {
			return posts.get(posts.size() - 1);
		}

		return null;
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
