package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.beans.Payment;

public class PaymentDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();
	
	public PaymentDao() {
	}

	public PaymentDao(EntityManager em) {
		this.em = em;
	}

	public void insert(Payment payment) {
		em.persist(payment);
	}

}
