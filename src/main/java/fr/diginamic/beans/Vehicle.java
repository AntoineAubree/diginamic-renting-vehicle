/**
 * 
 */
package fr.diginamic.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Antoine
 *
 */
@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "number_plate", length = 12, nullable = false, unique = true)
	private String numberPlate;
	@Column(name = "comment", length = 300)
	private String comment;
	@Column(name = "mileage")
	private float mileage;
	@Enumerated(EnumType.STRING)
	@Column(name = "status_vehicle", length = 15, nullable = false)
	private StatusVehicle statusVehicle;
	@ManyToOne
	@JoinColumn(name = "id_model", nullable = false)
	private Model model;
	@OneToMany(mappedBy = "vehicle")
	private List<Maintenance> maintenances = new ArrayList<>();
	@OneToMany(mappedBy = "vehicle")
	private List<Booking> bookings = new ArrayList<>();

	public Vehicle() {
		this.statusVehicle = StatusVehicle.AVAILABLE;
	}

	public Vehicle(String numberPlate, float mileage) {
		this.numberPlate = numberPlate;
		this.mileage = mileage;
		this.statusVehicle = StatusVehicle.AVAILABLE;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehicle [id=");
		builder.append(id);
		builder.append(", numberPlate=");
		builder.append(numberPlate);
		builder.append(", mileage=");
		builder.append(mileage);
		builder.append(", statusVehicle=");
		builder.append(statusVehicle);
		builder.append(", model=");
		builder.append(model.getName());
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public StatusVehicle getStatusVehicle() {
		return statusVehicle;
	}

	public void setStatusVehicle(StatusVehicle statusVehicle) {
		this.statusVehicle = statusVehicle;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public List<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void addMaintenance(Maintenance maintenance) {
		this.getMaintenances().add(maintenance);
		maintenance.setVehicle(this);
	}

	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
