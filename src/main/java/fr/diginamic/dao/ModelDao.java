package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Model;

public class ModelDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public Model findById(Long id) {
		Model model = em.find(Model.class, id);
		return model;
	}
	
	public List<Model> findAll() {
		TypedQuery<Model> query = em.createQuery("SELECT mo FROM Model mo JOIN mo.typeVehicle t JOIN mo.make ma ORDER BY t.categoryVehicle, t.name, ma.name, mo.name", Model.class);
		List<Model> models = query.getResultList();
		return models;
	}

}
