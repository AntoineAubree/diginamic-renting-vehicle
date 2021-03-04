/**
 * 
 */
package fr.diginamic.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Antoine
 *
 */
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Enumerated(EnumType.STRING)
	@Column(name = "type_payment", length = 15, nullable = false)
	private TypePayment typePayment;
	@OneToOne
	@JoinColumn(name = "id_receipt", nullable = false)
	private Receipt receipt;

	public Payment() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [id=");
		builder.append(id);
		builder.append(", typePayment=");
		builder.append(typePayment);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

}
