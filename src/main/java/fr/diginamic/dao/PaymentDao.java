package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.beans.Payment;

public class PaymentDao extends AbstractDao {

	private static EntityManager em = emf.createEntityManager();
	
	public PaymentDao() {
	}

	public PaymentDao(EntityManager em) {
		PaymentDao.em = em;
	}

	public void insert(Payment payment) {
		em.persist(payment);
	}

}
