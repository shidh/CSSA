/**
 * 
 */
package models;

import java.util.Date;
import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity(name = "user_table")
/**
 * @author allen
 *
 */
public class User extends Model {
	public String email;
	public String password;
	public String fullname;
	public String gender;
	public Date birthday;


	public User(String email, String password, String fullname, 
            String gender, Date birthday) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
		this.birthday = birthday;
	}

}
