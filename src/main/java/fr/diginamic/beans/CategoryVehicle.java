/**
 * 
 */
package fr.diginamic.beans;

import fr.diginamic.composants.ui.Selectable;

/**
 * @author Antoine
 *
 */
public enum CategoryVehicle implements Selectable {

	CAR(1, "Voiture"), TRUCK(2, "Camion");

	private int id;
	private String wording;

	private CategoryVehicle(int id, String wording) {
		this.id = id;
		this.wording = wording;
	}
	
	public static CategoryVehicle getById(int id) {
		for (CategoryVehicle categoryVehicle : CategoryVehicle.values()) {
			if (categoryVehicle.getId() == id) {
				return categoryVehicle;
			}
		}
		return null;
	}

	public String getWording() {
		return wording;
	}

	@Override
	public Integer getId() {
		return id;
	}

}
