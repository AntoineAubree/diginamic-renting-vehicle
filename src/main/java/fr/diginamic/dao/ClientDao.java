package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Client;

public class ClientDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public ClientDao() {
	}

	public ClientDao(EntityManager em) {
		this.em = em;
	}

	public Client findById(Integer id) {
		Client client = em.find(Client.class, id);
		return client;
	}

	public List<Client> findAll() {
		TypedQuery<Client> query = em.createQuery(
				"SELECT c FROM Client c JOIN c.address a ORDER BY c.lastname, c.firstname, a.postcode, a.city",
				Client.class);
		List<Client> clients = query.getResultList();
		return clients;
	}

	public boolean checkPhoneNumber(String phoneNumber) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.phoneNumber = :phoneNumber",
				Client.class);
		query.setParameter("phoneNumber", phoneNumber);
		List<Client> clientSelect = query.getResultList();
		if (clientSelect.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkEamil(String email) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class);
		query.setParameter("email", email);
		List<Client> clientSelect = query.getResultList();
		if (clientSelect.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void insert(Client client) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(client);
		transaction.commit();
	}

	public void update(Client client) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(client);
		transaction.commit();
	}

	public void delete(Client client) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(client);
		transaction.commit();
	}

}
