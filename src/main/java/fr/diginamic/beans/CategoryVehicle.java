/**
 * 
 */
package fr.diginamic.beans;

/**
 * @author Antoine
 *
 */
public enum CategoryVehicle {

	CAR("Voiture"), TRUCK("Camion");

	private String wording;

	private CategoryVehicle(String wording) {
		this.wording = wording;
	}

	public String getWording() {
		return wording;
	}

}
