package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.beans.TypeVehicle;

public class TypeVehiculeDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public void insert(TypeVehicle typeVehicle) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(typeVehicle);
		transaction.commit();
	}

}
