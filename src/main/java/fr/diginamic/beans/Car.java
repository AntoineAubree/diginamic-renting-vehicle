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
public class Car extends Model {

	@Column(name = "places_number")
	private int placesNumber;

	public Car() {
	}

	public Car(String numberPlate, int placesNumber) {
		super(numberPlate);
		this.placesNumber = placesNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [placesNumber=");
		builder.append(placesNumber);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public int getPlacesNumber() {
		return placesNumber;
	}

	public void setPlacesNumber(int placesNumber) {
		this.placesNumber = placesNumber;
	}

	public String getCategory() {
		return "Voiture";
	}

}
