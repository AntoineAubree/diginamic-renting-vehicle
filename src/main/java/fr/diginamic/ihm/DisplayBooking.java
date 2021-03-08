package fr.diginamic.ihm;

import java.util.List;

import fr.diginamic.beans.Booking;
import fr.diginamic.composants.MenuService;
import fr.diginamic.dao.BookingDao;

public class DisplayBooking extends MenuService {

	BookingDao bookingDao = new BookingDao();

	@Override
	public void traitement() {
		List<Booking> bookings = bookingDao.findAllCurrent();
		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des réservations en cours</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Prénom</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>Kilométrage de début</td><td>Date de début</td><td>Date de fin estimée</td><td>Commentaire</td></tr>";
		for (Booking booking : bookings) {
			html += "<tr>" 
					+ "  <td width='100px'>" + booking.getClient().getLastname() + "</td>" 
					+ "  <td width='100px'>" + booking.getClient().getFirstname() + "</td>" 
					+ "  <td width='100px'>" + booking.getVehicle().getModel().getName() + "</td>" 
					+ "  <td width='100px'>" + booking.getVehicle().getNumberPlate() + "</td>" 
					+ "  <td width='100px'>" + booking.getStartMileage() + "</td>" 
					+ "  <td width='100px'>" + booking.getStartDate() + "</td>" 
					+ "  <td width='100px'>" + booking.getEstimatedFinalDate() + "</td>" 
					+ "  <td width='100px'>" + booking.getComment() + "</td>" 
					+ "  <td><a class='btn-blue' href='closeBooking(" + booking.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
				+ "</tr>";
		}
		html += "</table>";
		console.print(html);
	}

	protected void closeBooking(Long id) {
	}

}
