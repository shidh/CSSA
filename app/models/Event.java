/**
 * 
 */
package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;


/**
 * @author allen
 * 
 */
@Entity
public class Event extends Post {
	public boolean isClosed;

	@OneToMany(mappedBy = "confirmedEvent", cascade = CascadeType.ALL)
	public List<User> confirmedUsers;
	
	@OneToMany(mappedBy = "waitingEvent", cascade = CascadeType.ALL)
	public List<User> onWaitingListUsers;

	
	public Event(boolean isClosed, List<User> confirmedUsers,
			LinkedList<User> onWaitingListUsers) {
		super();
		this.isClosed = isClosed;
		this.confirmedUsers = confirmedUsers;
		this.onWaitingListUsers = onWaitingListUsers;
	}

	public Event(String title, String description, String postContent,
			MapLocation mapLocation, Date postingDate, User sender,
			PostContent content, List<String> tags) {
		super(title, description, postContent, mapLocation, postingDate, sender,
				content, tags);
		this.title = title;
		this.description = description;
		this.mapLocation = mapLocation;
		this.postingDate = postingDate;
		this.sender = sender;
		this.content = content;
		this.tags = tags;
		this.rating = 0.0;	
	}
	
	public Event(String title, String description, MapLocation mapLocation,
			Date postingDate, User sender, PostContent content,
			List<String> tags) {
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
