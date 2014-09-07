/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	public String email; //Email
	public String password;
	public String fullname; //姓名
	public String address;  //地址
	public String major;    //专业
	public String gender;   //性别
	public Date birthday;   //生日 1985-1-4
	public boolean isAdmin;
	public boolean isConfirmed;
	public boolean isOnWaitingList;
	
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
	
	@ManyToMany(mappedBy = "members", cascade = CascadeType.ALL)
	public List<Event> events;
	
	@ManyToOne
	public Event confirmedEvent;
	
	@ManyToOne
	public Event waitingEvent;
		
	
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
