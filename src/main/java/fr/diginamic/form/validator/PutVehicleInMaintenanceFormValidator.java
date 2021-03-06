package fr.diginamic.form.validator;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;

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
		}
		return true;
	}

}
