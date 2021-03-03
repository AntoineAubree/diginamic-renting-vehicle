/**
 * 
 */
package fr.diginamic.beans;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Antoine
 *
 */
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "firstname", length = 50, nullable = false)
	private String firstname;
	@Column(name = "lastename", length = 50, nullable = false)
	private String lastename;
	@Embedded
	private Address address;

	public Client() {
	}

	public Client(String firstname, String lastename) {
		this.firstname = firstname;
		this.lastename = lastename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [firstname=");
		builder.append(firstname);
		builder.append(", lastename=");
		builder.append(lastename);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastename() {
		return lastename;
	}

	public void setLastename(String lastename) {
		this.lastename = lastename;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
