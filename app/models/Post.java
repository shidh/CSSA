
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
import com.google.gson.Gson;

/**
 * @author allen
 *
 */
@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) 
public class Post extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.TABLE)
	//private int id;

	public String title;
	
	@Column( length = 100 )
	public String description;
	public Date postingDate;
	
	@Column(length=5000)
	public String postContent;

	public Double rating;
	
	/**
	 * event/news
	 */
	@Column(name = "postType")
	public String postType;
	
	@OneToOne
	public MapLocation mapLocation;

	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	@OneToOne
	public PostContent content;

	@ManyToOne
	public User sender;

	@ElementCollection
	public List<String> tags;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	public List<Comment> comments;
	

	public Post(){
		super();
	}
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
		this.postType = "news";

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
		this.postType = "news";

	}

	public static Post getMostRecent()
	{
		List<Post> posts = Post.findAll();
		
		if(posts.size() > 0)
		{
			return posts.get(posts.size() - 1);
		}
		
		return null;
	}
	
	public static Post getMostRecentWithImages()
	{
		List<Post> posts = Post.findAll();
		
		for (int i = posts.size() - 1; i >= 0; i--) {
			Post post = posts.get(i);
			if (post.content.pictures.size() > 0) {
				return post;
			}
		}

		return null;
	}
	

	public static Post getMostLikedWithImages() {
		
		List<Post> chronologicalPosts = Post.findAll();
		
		if (chronologicalPosts.size() > 1) {

			List<Post> posts = Post.find("order by rating desc").fetch();
			Long mostRecentPostId = chronologicalPosts.get(chronologicalPosts.size() - 1).getId();

			for (int i = 0; i < posts.size() - 1; i++) {
				if (posts.get(i).content.pictures.size() > 0 && posts.get(i).getId().compareTo(mostRecentPostId) != 0) {
					return posts.get(i);
				}
			}
		}

		return null;
	}
	
	public static Post getMostLiked() {
		
		if (Post.findAll().size() > 1) {

			List<Post> posts = Post.find("order by rating desc").fetch();

			return posts.get(0);
		}

		return null;
	}
	
	public static List<Post> getAllOrderedByLikes() {
		
		if (Post.findAll().size() > 1) {

			return Post.find("order by rating desc").fetch();
		}

		return null;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public MapLocation getMapLocation() {
		return mapLocation;
	}
	public void setMapLocation(MapLocation mapLocation) {
		this.mapLocation = mapLocation;
	}
	public PostContent getContent() {
		return content;
	}
	public void setContent(PostContent content) {
		this.content = content;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		return "Post [title=" + title + ", description=" + description
				+ ", postingDate=" + postingDate + ", postContent="
				+ postContent + ", rating=" + rating + ", mapLocation="
				+ mapLocation + ", content=" + gson.toJson(content) + ", sender=" + sender
				+ ", tags=" + tags + ", comments=" + comments + "]";
	}
	
	
}