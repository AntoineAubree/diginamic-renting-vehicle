/**
 * 
 */
package fr.diginamic.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	@Column(name = "estimated_final_date", nullable = false)
	private LocalDate estimatedFinalDate;
	@Column(name = "final_date")
	private LocalDate finalDate;
	@Column(name = "start_mileage")
	private float startMileage;
	@Column(name = "final_mileage")
	private float finalMileage;
	@Column(name = "comment", length = 400)
	private String comment;
	@ManyToOne
	@JoinColumn(name = "id_client", nullable = false)
	private Client client;
	@ManyToOne
	@JoinColumn(name = "id_vehicle", nullable = false)
	private Vehicle vehicle;
	@OneToOne
	@JoinColumn(name = "id_receipt")
	private Receipt receipt;

	public Booking() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEstimatedFinalDate() {
		return estimatedFinalDate;
	}

	public void setEstimatedFinalDate(LocalDate estimatedFinalDate) {
		this.estimatedFinalDate = estimatedFinalDate;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	public float getStartMileage() {
		return startMileage;
	}

	public void setStartMileage(float startMileage) {
		this.startMileage = startMileage;
	}

	public float getFinalMileage() {
		return finalMileage;
	}

	public void setFinalMileage(float finalMileage) {
		this.finalMileage = finalMileage;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

}
