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
public class VehicleFormValidator extends FormValidator {

	VehicleDao vehicleDao = new VehicleDao();
	private String numberPlate;
	private boolean ifNumberPlate;
	private boolean ifComment;

	@Override
	public boolean validate(Form form) {
		if (ifNumberPlate) {
			String numberPlate = form.getValue("numberPlate");
			numberPlate = numberPlate.toUpperCase();
			String[] numberPlatePieces = numberPlate.split("-");
			if (numberPlatePieces.length != 3) {
				console.alert("La plaque d'immatriculation doit être au format XX-XXX-XX");
				return false;
			} else if (numberPlatePieces[0].length() != 2 || numberPlatePieces[1].length() != 3
					|| numberPlatePieces[2].length() != 2) {
				console.alert("La plaque d'immatriculation doit être au format XX-XXX-XX");
				return false;
			}
			for (String string : numberPlatePieces) {
				if (!RegexUtils.containsOnlyIntegerOrUppercaseLetter(string)) {
					console.alert("La plaque d'immatriculation doit être au format XX-XXX-XX");
					return false;
				}
			}
			boolean numberPlateAvailable = vehicleDao.checkNumberPlate(numberPlate);
			if (!numberPlateAvailable && !numberPlate.equals(this.numberPlate)) {
				console.alert("Cette plaque d'immatriculation est déjà renseignée");
				return false;
			}
		}

		String mileage = form.getValue("mileage");
		mileage = mileage.trim();
		if (mileage.isEmpty()) {
			console.alert("Le kilométragde doit être renseigné");
			return false;
		} else if (!RegexUtils.isPositiveFloat(mileage)) {
			console.alert("Le kilométragde doit être un nombre positif");
			return false;
		}

		if (ifComment) {
			String comment = form.getValue("comment");
			comment = comment.trim();
			if (comment.length() > 300) {
				console.alert("Le commentaire doit contenir moins de 300 caractères");
				return false;
			}
		}
		return true;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public boolean isIfNumberPlate() {
		return ifNumberPlate;
	}

	public void setIfNumberPlate(boolean ifNumberPlate) {
		this.ifNumberPlate = ifNumberPlate;
	}

	public boolean isIfComment() {
		return ifComment;
	}

	public void setIfComment(boolean ifComment) {
		this.ifComment = ifComment;
	}

}
