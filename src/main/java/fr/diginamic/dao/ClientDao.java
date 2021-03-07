package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Client;

public class ClientDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public ClientDao() {
	}

	public ClientDao(EntityManager em) {
		this.em = em;
	}

	public Client findById(Long id) {
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

}
