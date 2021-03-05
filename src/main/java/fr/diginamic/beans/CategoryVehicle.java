/**
 * 
 */
package fr.diginamic.beans;

/**
 * @author Antoine
 *
 */
public enum CategoryVehicle {

	CAR(1L, "Voiture"), TRUCK(2L, "Camion");

	private Long id;
	private String name;

	private CategoryVehicle(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static CategoryVehicle getInstanceById(Long id) {
		for (CategoryVehicle categoryVehicle : CategoryVehicle.values()) {
			if (categoryVehicle.getId() == id) {
				return categoryVehicle;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return this.id;
	}

}
