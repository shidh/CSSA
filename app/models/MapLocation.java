/**
 * 
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * @author allen
 *
 */
@Entity
public class MapLocation extends Model {
	public String address;
	public Double longitude;
	public Double latitude;

	public MapLocation(String address, Double longitude, Double latitude) {
		super();
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
	}
}
