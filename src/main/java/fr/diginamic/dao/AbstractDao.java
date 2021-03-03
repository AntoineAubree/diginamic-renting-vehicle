package fr.diginamic.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.utils.ConfigUtils;

public class AbstractDao {

	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("renting_vehicle", ConfigUtils.getPassword());

}
