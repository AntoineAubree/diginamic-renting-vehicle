/**
 * 
 */
package fr.diginamic.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@Column(name = "phone_number", length = 10, nullable = false, unique = true)
	private String phoneNumber;
	@Column(name = "email", length = 80, nullable = false, unique = true)
	private String email;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "client")
	private Set<Receipt> receipts = new HashSet<>();
	@OneToMany(mappedBy = "client")
	private Set<Booking> booginks = new HashSet<>();

	public Client() {
	}

	public Client(String firstname, String lastename) {
		this.firstname = firstname;
		this.lastename = lastename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastename=");
		builder.append(lastename);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(Set<Receipt> receipts) {
		this.receipts = receipts;
	}

	public Set<Booking> getBooginks() {
		return booginks;
	}

	public void setBooginks(Set<Booking> booginks) {
		this.booginks = booginks;
	}

}
