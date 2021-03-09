/**
 * 
 */
package fr.diginamic.beans;

import fr.diginamic.composants.ui.Selectable;

/**
 * @author Antoine
 *
 */
public enum CategoryDrivingLicence implements Selectable {

	A(1), B(2), C(3), D(4);

	private int id;

	private CategoryDrivingLicence(int id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	public static CategoryDrivingLicence getById(int id) {
		for (CategoryDrivingLicence categoryDrivingLicence : CategoryDrivingLicence.values()) {
			if (categoryDrivingLicence.getId() == id) {
				return categoryDrivingLicence;
			}
		}
		return null;
	}

}
