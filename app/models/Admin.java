/**
 * 
 */
package models;

import java.util.Date;

/**
 * @author allen
 *
 */
public class Admin extends User {

	public Admin(String email, String password, String fullname, String major,
			String city, String gender, Date birthday, Image image) {
		super(email, password, fullname, major, city, gender, birthday, image);
		// TODO Auto-generated constructor stub
	}

}
