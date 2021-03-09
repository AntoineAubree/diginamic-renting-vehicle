package fr.diginamic.form.validator;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.utils.RegexUtils;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class CarFormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String placesNumber = form.getValue("placesNumber");
		placesNumber = placesNumber.trim();
		if (placesNumber.isEmpty()) {
			console.alert("Le nombre de place est obligatoire");
			return false;
		} else if (!RegexUtils.isInteger(placesNumber)) {
			console.alert("Le nombre de place est compris entre 1 et 9");
			return false;
		} else if (Integer.parseInt(placesNumber) < 1 || Integer.parseInt(placesNumber) > 9) {
			console.alert("Le nombre de place est compris entre 1 et 9");
			return false;
		}

		String name = form.getValue("name");
		name = name.trim();
		if (name.isEmpty()) {
			console.alert("Le nom est obligatoire");
			return false;
		}
		return true;
	}

}
