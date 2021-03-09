package fr.diginamic.form.validator;

import java.time.LocalDate;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.dao.ClientDao;
import fr.diginamic.utils.LocalDateUtils;
import fr.diginamic.utils.RegexUtils;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class ClientFormValidator extends FormValidator {

	ClientDao clientDao = new ClientDao();

	private String phoneNumber;
	private String email;

	@Override
	public boolean validate(Form form) {
		String firstname = form.getValue("firstname");
		firstname = firstname.trim();
		if (firstname.isEmpty() || !RegexUtils.containsOnlyLetterOrMinusCharactereOrSpace(firstname)
				|| firstname.length() > 50) {
			console.alert(
					"Le prénom est obligatoire et doit contenir uniquement des lettres, des - ou des espaces (50 caractères maximum)");
			return false;
		}

		String lastname = form.getValue("lastname");
		lastname = lastname.trim();
		if (lastname.isEmpty() || !RegexUtils.containsOnlyLetterOrMinusCharactereOrSpace(lastname)
				|| lastname.length() > 50) {
			console.alert(
					"Le nom est obligatoire et doit contenir uniquement des lettres, des - ou des espaces (50 caractères maximum)");
			return false;
		}

		String phoneNumber = form.getValue("phoneNumber");
		phoneNumber = phoneNumber.trim();
		if (phoneNumber.length() != 10) {
			console.alert("Le numéro de téléphone est obligatoire et doit contenir 10 caractères");
			return false;
		} else if (phoneNumber.charAt(0) != '0' || !RegexUtils.isIntegerBetwenOneAndNine(phoneNumber.charAt(1))) {
			console.alert("Le numéro de téléphone doit dommencer par 01, 02, 03, 04, 05, 06, 07, 08 ou 09.");
			return false;
		}
		for (int i = 2; i < 10; i++) {
			if (!RegexUtils.isInteger(phoneNumber.charAt(i))) {
				console.alert("Le numéro de téléphone ne doit contenir que des chiffres.");
				return false;
			}
		}

		String email = form.getValue("email");
		email = email.trim();
		String[] emailPieces = email.split("@");
		if (email.length() > 80) {
			console.alert("L'email est obligatoire (80 caractères maximum)");
			return false;
		} else if (emailPieces.length != 2) {
			console.alert("L'email est obligatoire et doit être au format xxx@xxx.xxx");
			return false;
		} else {
			String[] emailPieces2 = emailPieces[1].split("\\.");
			if (emailPieces2.length != 2) {
				console.alert("L'email est obligatoire et doit être au format xxx@xxx.xxx");
				return false;
			}
		}

		String streetNumber = form.getValue("streetNumber");
		streetNumber = streetNumber.trim();
		if (streetNumber.isEmpty()) {
			console.alert("Le numéro de rue est obligatoire.");
			return false;
		} else if (!RegexUtils.isInteger(streetNumber)) {
			console.alert("Le numéro de rue doit être un entier positif.");
			return false;
		}

		String streetWording = form.getValue("streetWording");
		streetWording = streetWording.trim();
		if (streetWording.isEmpty() || streetWording.length() > 50) {
			console.alert("Le libellé de voie est obligatoire (50 caractères maximum)");
			return false;
		}

		String postCode = form.getValue("postCode");
		postCode = postCode.trim();
		if (postCode.length() != 5) {
			console.alert("Le code postal est obligatoire et doit contenir 5 chiffres");
			return false;
		}
		for (int i = 0; i < 5; i++) {
			if (!RegexUtils.isInteger(postCode.charAt(i))) {
				console.alert("Le code postal est obligatoire et doit contenir 5 chiffres");
				return false;
			}
		}

		String city = form.getValue("city");
		city = city.trim();
		if (city.isEmpty() || city.length() > 50) {
			console.alert("La ville est obligatoire (50 caractères maximum)");
			return false;
		}

		String drivingLicenceNumber = form.getValue("drivingLicenceNumber");
		drivingLicenceNumber = drivingLicenceNumber.trim();
		if (drivingLicenceNumber.isEmpty()) {
			console.alert("Le numéro du permis est obligatoire");
			return false;
		} else if (drivingLicenceNumber.length() > 20) {
			console.alert("Le numéro du permis doit contenir maximum 20 caractères");
			return false;
		}

		String obteningDate = form.getValue("obteningDate");
		if (obteningDate == null || obteningDate.trim().isEmpty()) {
			console.alert("La date d'obtention du permis est obligatoire");
			return false;
		} else if (LocalDateUtils.getDate(obteningDate).isAfter(LocalDate.now())) {
			console.alert("La date d'obtention du permis ne peut pas être supérieure à la date du jour");
			return false;
		}

		boolean phoneNumberAvailable = clientDao.checkPhoneNumber(phoneNumber);
		if (!phoneNumberAvailable && !phoneNumber.equals(this.phoneNumber)) {
			console.alert("Ce numéro de téléphone est déjà renseigné");
			return false;
		}

		boolean emailAvailable = clientDao.checkEamil(email);
		if (!emailAvailable && !email.equals(this.email)) {
			console.alert("Cet email est déjà renseigné");
			return false;
		}
		
		return true;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
