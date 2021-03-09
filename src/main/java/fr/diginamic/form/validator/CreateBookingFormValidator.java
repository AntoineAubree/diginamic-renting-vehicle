package fr.diginamic.form.validator;

import java.time.LocalDate;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.utils.LocalDateUtils;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class CreateBookingFormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String startDate = form.getValue("startDate");
		if (startDate == null || startDate.trim().isEmpty()) {
			console.alert("La date de début de réservation est obligatoire");
			return false;
		} else if (LocalDateUtils.getDate(startDate).isBefore(LocalDate.now())) {
			console.alert("La date de début de réservation doit être supérieure ou égale à la date du jour");
			return false;
		}
		
		String estimatedFinalDate = form.getValue("estimatedFinalDate");
		if (estimatedFinalDate == null || estimatedFinalDate.trim().isEmpty()) {
			console.alert("L'estimation de la date de fin de réservation est obligatoire");
			return false;
		} else if (LocalDateUtils.getDate(estimatedFinalDate).isBefore(LocalDateUtils.getDate(startDate))) {
			console.alert("L'estimation de la date de fin de réservation doit être supérieure ou égale à la date de début de réservation");
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

}
