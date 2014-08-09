/**
 * 
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 * @author allen
 *
 */
@Entity
public class Image extends Model {
	public Blob imageDate;
	public String url;

	public Image(Blob imageData) {
		this.imageDate = imageData;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
