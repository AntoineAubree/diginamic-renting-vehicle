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
		TypedQuery<Model> query = em.createQuery("SELECT m FROM Model m", Model.class);
		List<Model> models = query.getResultList();
		return models;
	}

}
