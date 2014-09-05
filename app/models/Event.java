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
import javax.persistence.OneToOne;


/**
 * @author allen
 * 
 */
@Entity
public class Event extends Post {
	public boolean isClosed;
	
	public Integer capacity;

//	@OneToOne
//	public MapLocation mapLocation;
	
	@OneToMany(mappedBy = "confirmedEvent", cascade = CascadeType.ALL)
	public List<User> confirmedUsers;
	
	@OneToMany(mappedBy = "waitingEvent", cascade = CascadeType.ALL)
	public List<User> onWaitingListUsers;

	public Event(boolean isClosed, Integer capacity, MapLocation mapLocation,
			List<User> confirmedUsers, List<User> onWaitingListUsers) {
		super();
		this.isClosed = isClosed;
		this.capacity = capacity;
		this.mapLocation = mapLocation;
		this.confirmedUsers = confirmedUsers;
		this.onWaitingListUsers = onWaitingListUsers;
	}

	public Event(String title, String description, String postContent,
			MapLocation mapLocation, Date postingDate, User sender,
			PostContent content, List<String> tags, Integer capacity) {
		super(title, description, postContent, postingDate, sender,
				content, tags);
		this.title = title;
		this.description = description;
		this.mapLocation = mapLocation;
		this.postingDate = postingDate;
		this.sender = sender;
		this.content = content;
		this.tags = tags;
		this.rating = 0.0;
		this.capacity = 0;
	}
	
	public Event(String title, String description, MapLocation mapLocation,
			Date postingDate, User sender, PostContent content,
			List<String> tags, Integer capacity) {
		this.title = title;
		this.description = description;
		this.mapLocation = mapLocation;
		this.postingDate = postingDate;
		this.sender = sender;
		this.content = content;
		this.tags = tags;
		this.rating = 0.0;
		this.capacity = 0;

	}


}
