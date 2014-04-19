/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

/**
 * @author allen
 * 
 */
public class Activity extends Model {

	public String title;
	public String description;
	public Date postingDate;
	public Double rating;
	public boolean isClosed;

	@OneToOne
	public MapLocation mapLocation;

	@OneToOne
	public PostContent content;

	@ManyToOne
	public User sender;

	@ElementCollection
	public List<String> tags;

	@OneToMany(mappedBy = "Activity", cascade = CascadeType.ALL)
	public List<Comment> comments;
	
	@OneToMany(mappedBy = "Activity", cascade = CascadeType.ALL)
	public List<User> confirmedUsers;
	
	@OneToMany(mappedBy = "Activity", cascade = CascadeType.ALL)
	public List<User> onWaitingListUsers;

	
	public Activity(String title, String description, MapLocation mapLocation,
			Date postingDate, User sender, PostContent content,
			List<String> tags) {
		super();
		this.title = title;
		this.description = description;
		this.mapLocation = mapLocation;
		this.postingDate = postingDate;
		this.sender = sender;
		this.content = content;
		this.tags = tags;
		this.rating = 0.0;
	}
	
	

}
