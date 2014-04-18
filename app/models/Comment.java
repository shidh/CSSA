/**
 * 
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * @author allen
 *
 */
@Entity
public class Comment extends Model {
	@Column(name = "textContent")
	public String text;
	public Integer rating;

	@ManyToOne
	public User sender;

	@ManyToOne
	public Post post;

	public Comment(String text, Integer rating, User sender, Post post) {
		super();
		this.text = text;
		this.rating = rating;
		this.sender = sender;
		this.post = post;
	}
}
