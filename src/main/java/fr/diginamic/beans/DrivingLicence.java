/**
 * 
 */
package fr.diginamic.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Antoine
 *
 */
@Embeddable
public class DrivingLicence {

	@Column(name = "driving_licence_number")
	private int drivingLicenceNumber;
	@Column(name = "obtening_date", nullable = false)
	private LocalDate obteningDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "category_driving_licence", length = 1, nullable = false)
	private CategoryDrivingLicence categoryDrivingLicence;

	public DrivingLicence() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrivingLicence [drivingLicenceNumber=");
		builder.append(drivingLicenceNumber);
		builder.append(", obteningDate=");
		builder.append(obteningDate);
		builder.append(", categoryDrivingLicence=");
		builder.append(categoryDrivingLicence);
		builder.append("]");
		return builder.toString();
	}

	public int getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(int drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}

	public LocalDate getObteningDate() {
		return obteningDate;
	}

	public void setObteningDate(LocalDate obteningDate) {
		this.obteningDate = obteningDate;
	}

	public CategoryDrivingLicence getCategoryDrivingLicence() {
		return categoryDrivingLicence;
	}

	public void setCategoryDrivingLicence(CategoryDrivingLicence categoryDrivingLicence) {
		this.categoryDrivingLicence = categoryDrivingLicence;
	}

}
