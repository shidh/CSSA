/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;


/**
 * @author allen
 * 
 */
@Entity
public class Event extends Post {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isClosed;
	
	public Integer capacity;

//	@OneToOne
//	public MapLocation mapLocation;
	
	@OneToMany(mappedBy = "confirmedEvent", cascade = CascadeType.ALL)
	public List<User> confirmedUsers;
	
	@OneToMany(mappedBy = "waitingEvent", cascade = CascadeType.ALL)
	public List<User> onWaitingListUsers;
	
	@ManyToMany()
    public List<User> members = new ArrayList<User>();

	public Event(boolean isClosed, Integer capacity, MapLocation mapLocation,
			List<User> confirmedUsers, List<User> onWaitingListUsers) {
		super();
		this.isClosed = isClosed;
		this.capacity = capacity;
		this.mapLocation = mapLocation;
		this.confirmedUsers = confirmedUsers;
		this.onWaitingListUsers = onWaitingListUsers;
		this.postType = "event";
	}

	public Event(String title, String description, String postContent,
			MapLocation mapLocation, Date postingDate, User sender,
			PostContent content, List<String> tags, Integer capacity) {
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
		this.capacity = 0;
		this.postType = "event";
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
		this.postType = "event";
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<User> getConfirmedUsers() {
		return confirmedUsers;
	}

	public void setConfirmedUsers(List<User> confirmedUsers) {
		this.confirmedUsers = confirmedUsers;
	}

	public List<User> getOnWaitingListUsers() {
		return onWaitingListUsers;
	}

	public void setOnWaitingListUsers(List<User> onWaitingListUsers) {
		this.onWaitingListUsers = onWaitingListUsers;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean isFull(){
		Long eventId = this.id;
		boolean isFull = false;
		if (eventId != null) {
			Event post = Event.findById(eventId);
			if (post != null) {
				if (post.confirmedUsers.size() < post.capacity){
					isFull = false;
					System.out.println("#not full");
				}else{
					isFull = true;
					System.out.println("#is full");
				}
			}	
		}

		return isFull;
	}
	
	public boolean isConfirmed(Long userId){
		Long eventId = this.id;
		boolean isSigned = false;
		if (userId != null) {
			User user = User.findById(userId);
			Event post = Event.findById(eventId);
			if (user != null) {
				if (post.confirmedUsers.contains(user) ){
					isSigned = true;
					System.out.println("###You are already confirmed");
				}else{
					isSigned = false;
					System.out.println("###You haven't signed up");
				}
			}
		}
		return isSigned;
	}

	public boolean isOnWaiting(Long userId){
		Long eventId = this.id;
		boolean isSigned = false;
		if (userId != null) {
			User user = User.findById(userId);
			Event post = Event.findById(eventId);
			if (user != null) {
				if (post.onWaitingListUsers.contains(user)){
					isSigned = true;
					System.out.println("###You are already on waiting list");
				}else{
					isSigned = false;
					System.out.println("###You haven't signed up");
				}
			}
		}
		return isSigned;
	}

}
