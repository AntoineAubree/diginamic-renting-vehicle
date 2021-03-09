package fr.diginamic.ihm;

import java.util.List;

import fr.diginamic.beans.Booking;
import fr.diginamic.composants.MenuService;
import fr.diginamic.dao.BookingDao;

public class DisplayPayedBooking extends MenuService {

	BookingDao bookingDao = new BookingDao();

	@Override
	public void traitement() {
		List<Booking> bookings = bookingDao.findAllPayed();
		console.clear();
		console.println("<h1 class='bg-green'><center>Réservations payées</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Numéro de facture</td><td>Nom</td><td>Prénom</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>Date de début</td><td>Date de fin</td><td>Montant</td><td>Type de payement</td><td>Commentaire</td></tr>";
		for (Booking booking : bookings) {
			html += "<tr>" 
					+ "  <td width='100px'>" + booking.getReceipt().getNumber() + "</td>"
					+ "  <td width='100px'>" + booking.getClient().getLastname() + "</td>"
					+ "  <td width='100px'>" + booking.getClient().getFirstname() + "</td>"
					+ "  <td width='100px'>" + booking.getVehicle().getModel().getName() + "</td>"
					+ "  <td width='100px'>" + booking.getVehicle().getNumberPlate() + "</td>" 
					+ "  <td width='100px'>" + booking.getStartDate() + "</td>"
					+ "  <td width='100px'>" + booking.getFinalDate() + "</td>" 
					+ "  <td width='100px'>" + booking.getReceipt().getBookingCost() + "</td>" 
					+ "  <td width='100px'>" + booking.getReceipt().getPayment().getTypePayment() + "</td>" 
					+ "  <td width='100px'>" + booking.getComment() + "</td>" 
				+ "</tr>";
		}
		html += "</table>";
		console.println(html);
	}

}
