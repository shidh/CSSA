/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

/**
 * @author allen
 *
 */
@Entity
public class PostContent extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String video;
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn()
	public List<Image> pictures;
	@ElementCollection
	public List<String> picturesTitles;
	
	public String coverImgUrl;
	
	

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Image> getPictures() {
		return pictures;
	}

	public void setPictures(List<Image> pictures) {
		this.pictures = pictures;
	}

	public List<String> getPicturesTitles() {
		return picturesTitles;
	}

	public void setPicturesTitles(List<String> picturesTitles) {
		this.picturesTitles = picturesTitles;
	}

	public PostContent() {
		super();
	}

	public PostContent(String video, List<Image> pictures,
			List<String> picturesTitles) {
		super();
		this.video = video;
		this.pictures = pictures;
		this.picturesTitles = picturesTitles;
	}

	@Override
	public String toString() {
		return "PostContent [video=" + video + ", pictures=" + pictures
				+ ", picturesTitles=" + picturesTitles + "]";
	}
	
}
