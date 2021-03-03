/**
 * 
 */
package fr.diginamic.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
public class Make {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@OneToMany(mappedBy = "make")
	private Set<Model> models = new HashSet<>();

	public Make() {
	}

	public Make(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Make [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
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

	public Set<Model> getModels() {
		return models;
	}

	public void addModel(Model model) {
		this.getModels().add(model);
		model.setMake(this);
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

}
