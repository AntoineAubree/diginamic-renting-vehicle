package fr.diginamic.ihm;

import java.time.temporal.ChronoUnit;
import java.util.List;

import fr.diginamic.beans.Booking;
import fr.diginamic.beans.Receipt;
import fr.diginamic.beans.StatusReceipt;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.BookingDao;
import fr.diginamic.form.validator.RemoveVehicleFromBookingFormValidator;
import fr.diginamic.utils.LocalDateUtils;

public class DisplayBooking extends MenuService {

	BookingDao bookingDao = new BookingDao();

	@Override
	public void traitement() {
		List<Booking> bookings = bookingDao.findAllCurrent();
		console.clear();
		console.println("<h1 class='bg-green'><center>Gestion des réservations en cours</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Prénom</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>Kilométrage de début</td><td>Date de début</td><td>Date de fin estimée</td><td>Commentaire</td><td>Clore la réservation</td></tr>";
		for (Booking booking : bookings) {
			html += "<tr>" + "  <td width='100px'>" + booking.getClient().getLastname() + "</td>"
					+ "  <td width='100px'>" + booking.getClient().getFirstname() + "</td>" + "  <td width='100px'>"
					+ booking.getVehicle().getModel().getName() + "</td>" + "  <td width='100px'>"
					+ booking.getVehicle().getNumberPlate() + "</td>" + "  <td width='100px'>"
					+ booking.getStartMileage() + "</td>" + "  <td width='100px'>" + booking.getStartDate() + "</td>"
					+ "  <td width='100px'>" + booking.getEstimatedFinalDate() + "</td>" + "  <td width='100px'>"
					+ booking.getComment() + "</td>" + "  <td><a class='btn-blue' href='closeBooking(" + booking.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>" + "</tr>";
		}
		html += "</table>";
		console.println(html);
	}

	protected void closeBooking(Integer id) {
		Booking booking = bookingDao.findById(id);
		Form form = new Form();
		form.addInput(new TextField("Kilométrage final:", "finalMileage", String.valueOf(booking.getStartMileage())));
		form.addInput(new DateField("Date de fin:", "finalDate", LocalDateUtils.getDateInString(booking.getEstimatedFinalDate())));
		form.addInput(new TextField("Commentaire:", "comment", booking.getComment()));
		RemoveVehicleFromBookingFormValidator validator = new RemoveVehicleFromBookingFormValidator();
		validator.setBooking(booking);
		boolean valide = console.input("Saisissez les informations pour cloturer la réservation", form, validator);
		if (valide) {
			String finalMileage = form.getValue("finalMileage");
			booking.setFinalMileage(Float.parseFloat(finalMileage.trim()));
			booking.setFinalDate(LocalDateUtils.getDate(form.getValue("finalDate")));
			String comment = form.getValue("comment");
			booking.setComment(comment.trim());
			Receipt receipt = createReceipt(booking);
			booking.setReceipt(receipt);
			bookingDao.closeBooking(booking);
			traitement();
		}
	}

	private Receipt createReceipt(Booking booking) {
		Receipt receipt = new Receipt();
		receipt.setBooking(booking);
		receipt.setClient(booking.getClient());
		float dailyPrice = booking.getVehicle().getModel().getTypeVehicle().getDailyPrince();
		long days = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getFinalDate()) + 1;
		float bookingCost = dailyPrice * days;
		receipt.setBookingCost(bookingCost);
		String numberReceipt = booking.getId() + "-" + booking.getClient().getId() + "-" + booking.getVehicle().getId();
		receipt.setNumber(numberReceipt);
		receipt.setStatusReceipt(StatusReceipt.NOT_PAYED);
		return receipt;
	}

}
