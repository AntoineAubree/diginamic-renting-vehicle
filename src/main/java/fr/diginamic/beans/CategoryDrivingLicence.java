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

	A(1L), B(2L), C(3L), D(4L);

	private Long id;

	private CategoryDrivingLicence(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public static CategoryDrivingLicence getById(Long id) {
		for (CategoryDrivingLicence categoryDrivingLicence : CategoryDrivingLicence.values()) {
			if (categoryDrivingLicence.getId() == id) {
				return categoryDrivingLicence;
			}
		}
		return null;
	}

}
