/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity(name = "user_table")
/*
 * 学联会员号	      用户名	    电话	   工作/学习单位	已获学位	                在读学位	是否确认	报名日期	      确认日期	登录次数	最后登录
 * cssam000102	cssam000102	123    TUM	     Transportation Systems		Master		y	2009-01-01	2009-01-01	2	    2009-01-22
 */
public class User extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	public String email; //Email
	
	@Column(unique=true)
	public String username; //cssa old login name
	
	public String password;
	public String fullname; //姓名
	public String address;  //地址
	public String major;    //专业
	public String gender;   //性别
	public Date birthday;   //生日 1985-1-4
	public String registerDate;
	
	public boolean isAdmin;
	public boolean isConfirmed;
	public boolean isOnWaitingList;
	
	@Column(unique=true)
	public String cssaID;
	public String mobileNo;
	public String organization;
	public String lastDegree;
	public String currentDegree;
	
	@OneToOne
	public Image image;

	@OneToMany
	public List<User> followed;
	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	public List<Post> posts;
	
	@ManyToMany(mappedBy = "members")
	public List<Event> events;
	
	@ManyToMany(mappedBy="confirmedUsers")
	public List<Event> confirmedEvent;
	
//	@ManyToMany(mappedBy = "waitingMembers")
//	public List<Event> waitingEvents;
	
	@ManyToMany(mappedBy="onWaitingListUsers")
	public List<Event> waitingEvent;
		
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isOnWaitingList() {
		return isOnWaitingList;
	}

	public void setOnWaitingList(boolean isOnWaitingList) {
		this.isOnWaitingList = isOnWaitingList;
	}

	public String getCssaID() {
		return cssaID;
	}

	public void setCssaID(String cssaID) {
		this.cssaID = cssaID;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getLastDegree() {
		return lastDegree;
	}

	public void setLastDegree(String lastDegree) {
		this.lastDegree = lastDegree;
	}

	public String getCurrentDegree() {
		return currentDegree;
	}

	public void setCurrentDegree(String currentDegree) {
		this.currentDegree = currentDegree;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<User> getFollowed() {
		return followed;
	}

	public void setFollowed(List<User> followed) {
		this.followed = followed;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}



	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getConfirmedEvent() {
		return confirmedEvent;
	}

	public void setConfirmedEvent(List<Event> confirmedEvent) {
		this.confirmedEvent = confirmedEvent;
	}

	public List<Event> getWaitingEvent() {
		return waitingEvent;
	}

	public void setWaitingEvent(List<Event> waitingEvent) {
		this.waitingEvent = waitingEvent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User(String email, String password){
		super();
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password, String fullname, String major,
			String address, String gender, Date birthday,
			Image image, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.major = major;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
		this.image = image;
		this.isAdmin = isAdmin;
	}

	public User(String email, String password, String fullname, String address,
			String major, String gender, Date birthday, boolean isAdmin,
			String cssaID, String mobileNo, String organization,
			String lastDegree, String currentDegree, Image image) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
		this.major = major;
		this.gender = gender;
		this.birthday = birthday;
		this.isAdmin = isAdmin;
		this.cssaID = cssaID;
		this.mobileNo = mobileNo;
		this.organization = organization;
		this.lastDegree = lastDegree;
		this.currentDegree = currentDegree;
		this.image = image;
	}

	
	public static User connect(String email, String password) {
	    return find("byEmailAndPassword", email, password).first();
	}

}
