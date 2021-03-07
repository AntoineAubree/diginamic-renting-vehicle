/**
 * 
 */
package fr.diginamic.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Antoine
 *
 */
@Embeddable
public class Address {

	@Column(name = "street_number")
	private int streetNumber;
	@Column(name = "street_wording", length = 50, nullable = false)
	private String streetWording;
	@Column(name = "postcode", length = 5, nullable = false)
	private String postcode;
	@Column(name = "city", length = 50, nullable = false)
	private String city;

	public Address() {
	}

	public Address(int streetNumber, String streetWording, String postcode, String city) {
		this.streetNumber = streetNumber;
		this.streetWording = streetWording;
		this.postcode = postcode;
		this.city = city;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [streetNumber=");
		builder.append(streetNumber);
		builder.append(", streetWording=");
		builder.append(streetWording);
		builder.append(", postcode=");
		builder.append(postcode);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetWording() {
		return streetWording;
	}

	public void setStreetWording(String streetWording) {
		this.streetWording = streetWording;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
