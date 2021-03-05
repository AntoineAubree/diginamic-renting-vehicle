/**
 * 
 */
package fr.diginamic.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Antoine
 *
 */
@Entity
public class Truck extends Model {

	@Column(name = "volume")
	private int volume;

	public Truck() {
	}

	public Truck(String numberPlate, int volume) {
		super(numberPlate);
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public String getCategory() {
		return "Camion";
	}

}
