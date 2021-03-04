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

	@Column(name = "number")
	private int number;
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
		builder.append("DrivingLicence [number=");
		builder.append(number);
		builder.append(", obteningDate=");
		builder.append(obteningDate);
		builder.append(", categoryDrivingLicence=");
		builder.append(categoryDrivingLicence);
		builder.append("]");
		return builder.toString();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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
