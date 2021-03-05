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

/**
 * @author Antoine
 *
 */
@Entity
public class Maintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	@Column(name = "final_date")
	private LocalDate finalDate;
	@Column(name = "reparation_cost")
	private float reparationCost;
	@ManyToOne
	@JoinColumn(name = "id_vehicle", nullable = false)
	private Vehicle vehicle;

	public Maintenance() {
	}

	public Maintenance(LocalDate startDate, Vehicle vehicle) {
		this.startDate = startDate;
		this.vehicle = vehicle;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Maintenance [id=");
		builder.append(id);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", finalDate=");
		builder.append(finalDate);
		builder.append(", reparationCost=");
		builder.append(reparationCost);
		builder.append(", vehicle=");
		builder.append(vehicle.getId());
		builder.append("]");
		return builder.toString();
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

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	public float getReparationCost() {
		return reparationCost;
	}

	public void setReparationCost(float reparationCost) {
		this.reparationCost = reparationCost;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
