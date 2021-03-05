package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.beans.Maintenance;

public class MaintenanceDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();
	
	VehicleDao vehicleDao = new VehicleDao(em);

	public void insert(Maintenance maintenance) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(maintenance);
		vehicleDao.update(maintenance);
		transaction.commit();
	}

}
