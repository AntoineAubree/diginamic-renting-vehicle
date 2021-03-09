package fr.diginamic.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.Booking;
import fr.diginamic.beans.Payment;
import fr.diginamic.beans.Receipt;
import fr.diginamic.beans.TypePayment;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.dao.BookingDao;
import fr.diginamic.dao.ReceiptDao;
import fr.diginamic.form.validator.PaymentFormValidator;

public class DisplayNotPayedBooking extends MenuService {

	BookingDao bookingDao = new BookingDao();
	ReceiptDao receiptDao = new ReceiptDao();

	@Override
	public void traitement() {
		List<Booking> bookings = bookingDao.findAllNotPayed();
		console.clear();
		console.println("<h1 class='bg-green'><center>Réservations non payées</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Numéro de facture</td><td>Nom</td><td>Prénom</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>Date de début</td><td>Date de fin</td><td>Montant</td><td>Commentaire</td><td>Payer</td></tr>";
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
					+ "  <td width='100px'>" + booking.getComment() + "</td>" 
					+ "  <td><a class='btn-blue' href='payeReceipt(" + booking.getReceipt().getId() + ")'><img width=25 src='images/euro.png'></a></td>" 
				+ "</tr>";
		}
		html += "</table>";
		console.println(html);
	}

	protected void payeReceipt(Integer id) {
		Receipt receipt = receiptDao.findById(id);
		List<Selectable> typesPaymentSelectable = new ArrayList<>();
		for (TypePayment typePaymentSelectable : TypePayment.values()) {
			typesPaymentSelectable.add(typePaymentSelectable);
		}
		
		Form form = new Form();
		form.addInput(new ComboBox("Moyen de payement:", "typePayment", typesPaymentSelectable, typesPaymentSelectable.get(0)));
		PaymentFormValidator validator = new PaymentFormValidator();
		boolean valide = console.input("Sélectionner le moyende payement pour un montant de " + receipt.getBookingCost(), form, validator);
		if (valide) {
			Payment payment = new Payment();
			payment.setTypePayment(form.getValue("typePayment"));
			receipt.setPayment(payment);
			payment.setReceipt(receipt);
			receiptDao.paye(receipt);
			traitement();
		}
	}

}
