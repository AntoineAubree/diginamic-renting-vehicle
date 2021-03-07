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
public class PutVehicleInMaintenanceFormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String startDate = form.getValue("startDate");
		if (startDate == null || startDate.trim().isEmpty()) {
			console.alert("La date de début de maintenance est obligatoire");
			return false;
		} else if (LocalDateUtils.getDate(startDate).isAfter(LocalDate.now())) {
			console.alert("La date de début de maintenance ne peut pas être suppérieure à la date du jour");
			return false;
		}
		return true;
	}

}
