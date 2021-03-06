package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.StatusVehicle;
import fr.diginamic.beans.Vehicle;

public class VehicleDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();
	
	MaintenanceDao maintenanceDao = new MaintenanceDao(em);

	public VehicleDao() {
	}

	public VehicleDao(EntityManager em) {
		this.em = em;
	}

	public Vehicle findById(Long id) {
		Vehicle vehicle = em.find(Vehicle.class, id);
		return vehicle;
	}

	public List<Vehicle> findAll() {
		TypedQuery<Vehicle> query = em.createQuery(
				"SELECT v FROM Vehicle v JOIN v.model mo JOIN mo.typeVehicle t JOIN mo.make ma ORDER BY t.categoryVehicle, ma.name, mo.name",
				Vehicle.class);
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

	public void update(Vehicle vehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(vehicle);
		transaction.commit();
	}

	public void putInMaintenance(Vehicle vehicle) {
		vehicle.setStatusVehicle(StatusVehicle.MAINTENANCE);;
		em.merge(vehicle);
	}

	public void delete(Vehicle vehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		maintenanceDao.deleteFromVehicle(vehicle);
		em.remove(vehicle);
		transaction.commit();
	}

}
