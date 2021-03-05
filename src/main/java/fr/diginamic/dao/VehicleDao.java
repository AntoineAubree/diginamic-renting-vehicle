package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Vehicle;

public class VehicleDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public List<Vehicle> findAll() {
		TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
		List<Vehicle> vehicles = query.getResultList();
		return vehicles;
	}

	public void insert(Vehicle vehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(vehicle);
		transaction.commit();
	}

	public boolean checkNumberPlate(String numberPlate) {
		TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v WHERE v.numberPlate = :number_plate",
				Vehicle.class);
		query.setParameter("number_plate", numberPlate);
		List<Vehicle> vehicleSelect = query.getResultList();
		if (vehicleSelect.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
