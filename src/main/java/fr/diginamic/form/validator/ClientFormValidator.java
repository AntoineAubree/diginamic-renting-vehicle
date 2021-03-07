package fr.diginamic.form.validator;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.dao.VehicleDao;
import fr.diginamic.utils.RegexUtils;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class ClientFormValidator extends FormValidator {

	VehicleDao vehicleDao = new VehicleDao();

	@Override
	public boolean validate(Form form) {
		String firstname = form.getValue("firstname");
		String lastname = form.getValue("lastname");
		if (firstname.trim().isEmpty() || !RegexUtils.containsOnlyLetterOrMinusCharactereOrSpace(firstname) || firstname.length() > 50) {
			console.alert("Le prénom est obligatoire et doit contenir uniquement des lettres, des - ou des espaces (50 caractères maximum)");
			return false;
		} else if (lastname.trim().isEmpty() || !RegexUtils.containsOnlyLetterOrMinusCharactereOrSpace(lastname) || lastname.length() > 50) {
			console.alert("Le nom est obligatoire et doit contenir uniquement des lettres, des - ou des espaces (50 caractères maximum)");
			return false;
		}
		return true;
	}

}
