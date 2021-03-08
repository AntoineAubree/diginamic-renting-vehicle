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

	CAR(1L, "Voiture"), TRUCK(2L, "Camion");

	private Long id;
	private String wording;

	private CategoryVehicle() {
	}

	private CategoryVehicle(String wording) {
		this.wording = wording;
	}

	private CategoryVehicle(Long id, String wording) {
		this.id = id;
		this.wording = wording;
	}
	
	public static CategoryVehicle getById(Long id) {
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
	public Long getId() {
		return id;
	}

}
