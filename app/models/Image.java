/**
 * 
 */
package models;

import javax.persistence.Entity;
import java.util.Date;

import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 * @author allen
 *
 */
@Entity
public class Image extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3590582909260228000L;
	public Blob imageDate;
	public String url;
	public String fileName;
	public Date imagetime;

	public Image(Blob imageData) {
		this.imageDate = imageData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Date getimagetime() {
		return imagetime;
	}

	public void setimagetime(Date imagetime) {
		this.imagetime = imagetime;
	}

}
