/**
 * 
 */
package fr.diginamic.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Antoine
 *
 */
@Entity
@Table(name = "type_vehicle")
public class TypeVehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	@Column(name = "daily_price")
	private float dailyPrince;
	@Column(name = "guarantee")
	private float guarantee;
	@Enumerated(EnumType.STRING)
	@Column(name = "category_vehicle", length = 7, nullable = false)
	private CategoryVehicle categoryVehicle;
	@OneToMany(mappedBy = "typeVehicle")
	private Set<Model> models = new HashSet<>();

	public TypeVehicle() {
	}

	public TypeVehicle(String name, float dailyPrince, float guarantee, CategoryVehicle categoryVehicle) {
		this.name = name;
		this.dailyPrince = dailyPrince;
		this.guarantee = guarantee;
		this.categoryVehicle = categoryVehicle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeVehicle [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dailyPrince=");
		builder.append(dailyPrince);
		builder.append(", guarantee=");
		builder.append(guarantee);
		builder.append(", categoryVehicle=");
		builder.append(categoryVehicle);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
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

	public float getDailyPrince() {
		return dailyPrince;
	}

	public void setDailyPrince(float dailyPrince) {
		this.dailyPrince = dailyPrince;
	}

	public float getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(float guarantee) {
		this.guarantee = guarantee;
	}

	public CategoryVehicle getCategoryVehicle() {
		return categoryVehicle;
	}

	public void setCategoryVehicle(CategoryVehicle categoryVehicle) {
		this.categoryVehicle = categoryVehicle;
	}

	public Set<Model> getModels() {
		return models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

}
