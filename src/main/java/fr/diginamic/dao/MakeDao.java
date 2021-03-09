package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Make;

public class MakeDao extends AbstractDao {

	private static EntityManager em = emf.createEntityManager();

	public Make findById(Integer id) {
		Make make = em.find(Make.class, id);
		return make;
	}

	public List<Make> findAll() {
		TypedQuery<Make> query = em.createQuery("SELECT m FROM Make m ORDER BY m.name", Make.class);
		List<Make> makes = query.getResultList();
		return makes;
	}

	public void insert(Make make) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(make);
		transaction.commit();
	}

	public void delete(Make make) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(make);
		transaction.commit();
	}

	public boolean checkIfExist(String name) {
		TypedQuery<Make> query = em.createQuery("SELECT m FROM Make m WHERE LOWER(m.name) = :name", Make.class);
		query.setParameter("name", name.toLowerCase());
		List<Make> makeSelect = query.getResultList();
		if (makeSelect.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void update(Make make) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(make);
		transaction.commit();
	}

}
