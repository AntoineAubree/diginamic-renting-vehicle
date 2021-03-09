package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Car;
import fr.diginamic.beans.Model;
import fr.diginamic.beans.Truck;

public class ModelDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public Model findById(Integer id) {
		Model model = em.find(Model.class, id);
		return model;
	}

	public List<Model> findAll() {
		TypedQuery<Model> query = em.createQuery(
				"SELECT mo FROM Model mo JOIN mo.typeVehicle t JOIN mo.make ma ORDER BY t.categoryVehicle, t.name, ma.name, mo.name",
				Model.class);
		List<Model> models = query.getResultList();
		return models;
	}

	public void delete(Model model) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(model);
		transaction.commit();
	}

	public void insertCar(Car model) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(model);
		transaction.commit();
	}

	public void insertTruck(Truck model) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(model);
		transaction.commit();
	}

	public void updateCar(Car car) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(car);
		transaction.commit();
	}

	public void updateTruck(Truck truck) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(truck);
		transaction.commit();
	}

}
