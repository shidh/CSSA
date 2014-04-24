/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity(name = "user_table")

public class User extends Model {
	public String email;
	public String password;
	public String fullname;
	public String city;
	public String major;
	public String gender;
	public Date birthday;
	public boolean isAdmin;
	public boolean isConfirmed;
	public boolean isOnWaitingList;

	@OneToOne
	public Image image;

	@OneToMany
	public List<User> followed;
	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	public List<Post> posts;

	public User(String email, String password, String fullname, String major,
			String city, String gender, Date birthday,
			Image image, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.major = major;
		this.city = city;
		this.gender = gender;
		this.birthday = birthday;
		this.image = image;
		this.isAdmin = isAdmin;
	}
	
	public User(String email, String password){
		super();
		this.email = email;
		this.password = password;
	}
	
	public static User connect(String email, String password) {
	    return find("byEmailAndPassword", email, password).first();
	}

}
