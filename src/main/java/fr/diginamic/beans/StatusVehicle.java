/**
 * 
 */
package fr.diginamic.beans;

/**
 * @author Antoine
 *
 */
public enum StatusVehicle {
	
	AVAILABLE("disponible"), RENTED("en location"), MAINTENANCE("en maintenance");

	private String wording;

	private StatusVehicle(String wording) {
		this.wording = wording;
	}

	public String getWording() {
		return wording;
	}


}
