/**
 * 
 */
package models;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * @author allen
 *
 */
@Entity
public class PostContent extends Model {
	public String video;
	@ElementCollection
	public List<Image> pictures;
	@ElementCollection
	public List<String> picturesTitles;

	public PostContent(String video, List<Image> pictures,
			List<String> picturesTitles) {
		super();
		this.video = video;
		this.pictures = pictures;
		this.picturesTitles = picturesTitles;
	}
}
