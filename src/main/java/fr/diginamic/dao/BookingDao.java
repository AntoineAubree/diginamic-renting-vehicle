package fr.diginamic.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Booking;

public class BookingDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public Booking findById(Long id) {
		Booking booking = em.find(Booking.class, id);
		return booking;
	}

	public List<Booking> findAllCurrent() {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.finalDate IS NULL",
				Booking.class);
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

	public List<Booking> findAllOver() {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.finalDate IS NOT NULL",
				Booking.class);
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

	public void insert(Booking booking) {
		VehicleDao vehicleDao = new VehicleDao(em);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(booking);
		if (booking.getStartDate().isEqual(LocalDate.now())) {
			vehicleDao.putInBooking(booking.getVehicle());
		}
		transaction.commit();
	}

}
