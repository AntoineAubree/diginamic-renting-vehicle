/**
 * 
 */
package fr.diginamic.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import fr.diginamic.composants.ui.Selectable;

/**
 * @author Antoine
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Model implements Selectable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "id_make", nullable = false)
	private Make make;
	@OneToMany(mappedBy = "model")
	private List<Vehicle> vehicles = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "id_type_vehicle", nullable = false)
	private TypeVehicle typeVehicle;

	public Model() {
	}

	public Model(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getCategory());
		builder.append(" | ");
		builder.append(make.getName());
		builder.append(" | ");
		builder.append(name);
		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		this.getVehicles().add(vehicle);
		vehicle.setModel(this);
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public TypeVehicle getTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(TypeVehicle typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

	public abstract String getCategory();

}
