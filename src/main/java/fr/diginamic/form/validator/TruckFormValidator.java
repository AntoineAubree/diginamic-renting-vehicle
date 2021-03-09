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
public class TruckFormValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		String volume = form.getValue("volume");
		volume = volume.trim();
		if (volume.isEmpty()) {
			console.alert("Le volume est obligatoire");
			return false;
		} else if (!RegexUtils.isPositiveFloat(volume)) {
			console.alert("Le volume doit être une valeur positive");
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
