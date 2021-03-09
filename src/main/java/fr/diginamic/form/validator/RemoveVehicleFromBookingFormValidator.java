package fr.diginamic.form.validator;

import java.time.LocalDate;

import fr.diginamic.beans.Booking;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.utils.LocalDateUtils;
import fr.diginamic.utils.RegexUtils;

/**
 * validateur associé au formulaire
 * 
 * @author
 *
 */
public class RemoveVehicleFromBookingFormValidator extends FormValidator {

	private Booking booking;

	@Override
	public boolean validate(Form form) {
		String finalDate = form.getValue("finalDate");
		if (finalDate == null || finalDate.trim().isEmpty()) {
			console.alert("La date de fin de réservation est obligatoire");
			return false;
		} else if (LocalDateUtils.getDate(finalDate).isAfter(LocalDate.now())) {
			console.alert("La date de fin de réservation ne peut pas être suppérieure à la date du jour");
			return false;
		} else if (LocalDateUtils.getDate(finalDate).isBefore(booking.getStartDate())) {
			console.alert("La date de fin de réservation ne peut pas être inférieure à la date de début");
			return false;
		}

		String finalMileage = form.getValue("finalMileage");
		finalMileage = finalMileage.trim();
		if (finalMileage.isEmpty()) {
			console.alert("Le kilométrage de fin est obligatoire");
			return false;
		} else if (!RegexUtils.isPositiveFloat(finalMileage)) {
			console.alert("Le kilométrage de fin doit être positif");
			return false;
		} else if (Float.parseFloat(finalMileage) < booking.getStartMileage()) {
			console.alert("Le kilométrage de fin doit être supérieur à celui du début");
			return false;
		}
		
		String comment = form.getValue("comment");
		comment = comment.trim();
		if (comment.length() > 400) {
			console.alert("Le commentaire doit contenir moins de 400 caractères");
			return false;
		} 
		return true;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
