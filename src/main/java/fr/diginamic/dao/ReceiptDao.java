package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Receipt;

public class ReceiptDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public ReceiptDao() {
	}

	public ReceiptDao(EntityManager em) {
		this.em = em;
	}

	public Receipt findById(Integer id) {
		Receipt receipt = em.find(Receipt.class, id);
		return receipt;
	}

	public List<Receipt> findAll() {
		TypedQuery<Receipt> query = em.createQuery("SELECT r FROM Receipt r", Receipt.class);
		List<Receipt> receipts = query.getResultList();
		return receipts;
	}

	public void insertFromCloseBooking(Receipt receipt) {
		em.persist(receipt);
	}

	public void paye(Receipt receipt) {
		PaymentDao paymentDao = new PaymentDao(em);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(receipt);
		paymentDao.insert(receipt.getPayment());
		transaction.commit();
	}

}
