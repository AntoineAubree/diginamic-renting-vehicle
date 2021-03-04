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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Antoine
 *
 */
@Entity
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "number")
	private int number;
	@Column(name = "booking_cost")
	private float bookingCost;
	@Enumerated(EnumType.STRING)
	@Column(name = "status_receipt", length = 15, nullable = false)
	private StatusReceipt statusreceipt;
	@ManyToOne
	@JoinColumn(name = "id_client", nullable = false)
	private Client client;
	@OneToOne
	@JoinColumn(name = "id_payment")
	private Payment payment;
	@OneToOne
	@JoinColumn(name = "id_booking", nullable = false)
	private Booking booking;

	public Receipt() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Receipt [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", bookingCost=");
		builder.append(bookingCost);
		builder.append(", statusreceipt=");
		builder.append(statusreceipt);
		builder.append(", client=");
		builder.append(client);
		builder.append(", payment=");
		builder.append(payment);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getBookingCost() {
		return bookingCost;
	}

	public void setBookingCost(float bookingCost) {
		this.bookingCost = bookingCost;
	}

	public StatusReceipt getStatusreceipt() {
		return statusreceipt;
	}

	public void setStatusreceipt(StatusReceipt statusreceipt) {
		this.statusreceipt = statusreceipt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
