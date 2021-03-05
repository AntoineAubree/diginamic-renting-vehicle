package fr.diginamic.services;

import java.util.regex.Pattern;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.dao.VehicleDao;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class FormVehicleValidator extends FormValidator {

	VehicleDao vehicleDao = new VehicleDao();
	private String numberPlate;

	@Override
	public boolean validate(Form form) {
		String numberPLate = form.getValue("numberPlate").toUpperCase();
		String[] numberPlatePieces = numberPLate.split("-");
		if (numberPlatePieces.length != 3) {
			console.alert("La plaque d'immatriculation doit être au format XX-XXX-XX");
			return false;
		}
		if (numberPlatePieces[0].length() != 2 || numberPlatePieces[1].length() != 3
				|| numberPlatePieces[2].length() != 2) {
			console.alert("La plaque d'immatriculation doit être au format XX-XXX-XX");
			return false;
		}
		for (String string : numberPlatePieces) {
			if (!Pattern.matches("^[A-Z0-9]*$", string)) {
				console.alert("La plaque d'immatriculation doit être au format XX-XXX-XX");
				return false;
			}
		}
		boolean numberPlateAvailable = vehicleDao.checkNumberPlate(numberPLate);
		if (!numberPlateAvailable && !numberPLate.equals(this.numberPlate)) {
			console.alert("Cette plaque d'immatriculation est déjà renseignée");
			return false;
		}
		return true;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

}
