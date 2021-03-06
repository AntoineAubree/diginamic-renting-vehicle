package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.beans.Booking;

public class BookingDao extends AbstractDao {

	private static EntityManager em = emf.createEntityManager();

	public Booking findById(Integer id) {
		Booking booking = em.find(Booking.class, id);
		return booking;
	}

	public List<Booking> findAllCurrent() {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.finalDate IS NULL",
				Booking.class);
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

	public List<Booking> findAllNotPayed() {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b JOIN b.receipt r WHERE b.finalDate IS NOT NULL AND r.payment IS NULL",
				Booking.class);
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

	public List<Booking> findAllPayed() {
		TypedQuery<Booking> query = em.createQuery(
				"SELECT b FROM Booking b JOIN b.receipt r WHERE b.finalDate IS NOT NULL AND r.payment IS NOT NULL",
				Booking.class);
		List<Booking> bookings = query.getResultList();
		return bookings;
	}

	public void insert(Booking booking) {
		VehicleDao vehicleDao = new VehicleDao(em);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(booking);
		vehicleDao.putInBooking(booking.getVehicle());
		transaction.commit();
	}

	public void closeBooking(Booking booking) {
		VehicleDao vehicleDao = new VehicleDao(em);
		ReceiptDao receiptDao = new ReceiptDao(em);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(booking);
		booking.getVehicle().setMileage(booking.getFinalMileage());
		vehicleDao.removeFromBooking(booking.getVehicle());
		receiptDao.insertFromCloseBooking(booking.getReceipt());
		transaction.commit();
	}

}
