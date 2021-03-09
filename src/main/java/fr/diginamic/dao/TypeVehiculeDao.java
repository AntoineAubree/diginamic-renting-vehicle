package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.CategoryVehicle;
import fr.diginamic.beans.TypeVehicle;

public class TypeVehiculeDao extends AbstractDao {

	private static EntityManager em = emf.createEntityManager();

	public void insert(TypeVehicle typeVehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(typeVehicle);
		transaction.commit();
	}

	public List<TypeVehicle> findAll() {
		TypedQuery<TypeVehicle> query = em.createQuery("SELECT t FROM TypeVehicle t ORDER BY t.categoryVehicle, t.name", TypeVehicle.class);
		List<TypeVehicle> typesVehicleSelect = query.getResultList();
		return typesVehicleSelect;
	}

	public TypeVehicle findById(Integer id) {
		TypeVehicle typeVehicle = em.find(TypeVehicle.class, id);
		return typeVehicle;
	}

	public void delete(TypeVehicle typeVehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(typeVehicle);
		transaction.commit();
	}

	public boolean checkIfExist(String name) {
		TypedQuery<TypeVehicle> query = em.createQuery("SELECT t FROM TypeVehicle t WHERE LOWER(t.name) = :name",
				TypeVehicle.class);
		query.setParameter("name", name.toLowerCase());
		List<TypeVehicle> typeVehicleSelect = query.getResultList();
		if (typeVehicleSelect.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void update(TypeVehicle typeVehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(typeVehicle);
		transaction.commit();
	}

	public List<TypeVehicle> findAllCar() {
		TypedQuery<TypeVehicle> query = em.createQuery("SELECT t FROM TypeVehicle t WHERE t.categoryVehicle = :categoryVehicle ORDER BY t.name", TypeVehicle.class);
		query.setParameter("categoryVehicle", CategoryVehicle.CAR);
		List<TypeVehicle> typesVehicleSelect = query.getResultList();
		return typesVehicleSelect;
	}

	public List<TypeVehicle> findAllTruck() {
		TypedQuery<TypeVehicle> query = em.createQuery(
				"SELECT t FROM TypeVehicle t WHERE t.categoryVehicle = :categoryVehicle ORDER BY t.name",
				TypeVehicle.class);
		query.setParameter("categoryVehicle", CategoryVehicle.TRUCK);
		List<TypeVehicle> typesVehicleSelect = query.getResultList();
		return typesVehicleSelect;
	}

}
