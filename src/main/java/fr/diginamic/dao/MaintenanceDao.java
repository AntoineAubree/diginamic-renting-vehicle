package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.beans.Maintenance;
import fr.diginamic.beans.Vehicle;

public class MaintenanceDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public MaintenanceDao() {
	}

	public MaintenanceDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Maintenance maintenance) {
		VehicleDao vehicleDao = new VehicleDao(em);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(maintenance);
		vehicleDao.putInMaintenance(maintenance.getVehicle());
		transaction.commit();
	}

	public void deleteFromVehicle(Vehicle vehicle) {
		for (Maintenance maintenance : vehicle.getMaintenances()) {
			em.remove(maintenance);
		}
	}

}
