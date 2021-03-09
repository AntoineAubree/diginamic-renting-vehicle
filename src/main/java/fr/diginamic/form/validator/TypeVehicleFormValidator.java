package fr.diginamic.form.validator;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.dao.TypeVehiculeDao;
import fr.diginamic.utils.RegexUtils;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class TypeVehicleFormValidator extends FormValidator {

	TypeVehiculeDao typeVehiculeDao = new TypeVehiculeDao();
	private String name;

	@Override
	public boolean validate(Form form) {
		String name = form.getValue("name");
		name = name.trim();
		if (name.isEmpty() || name.length() > 50) {
			console.alert("Le nom du type de véhicule est obigatoire (50 caractères maximum)");
			return false;
		}
		boolean typeVehicleAvailable = typeVehiculeDao.checkIfExist(name);
		if (!typeVehicleAvailable && !name.equals(this.name)) {
			console.alert("Ce type est déjà renseigné");
			return false;
		}
		
		String dailyPrice = form.getValue("dailyPrice");
		dailyPrice = dailyPrice.trim();
		if (dailyPrice.isEmpty()) {
			console.alert("Le tarif journalier est obligatoire");
			return false;
		} else if (!RegexUtils.isPositiveFloat(dailyPrice)) {
			console.alert("Le tarif journalier doit être une valeur positive");
			return false;
		}
		
		String guarantee = form.getValue("guarantee");
		guarantee = guarantee.trim();
		if (guarantee.isEmpty()) {
			console.alert("Le montant de la caution est obligatoire");
			return false;
		} else if (!RegexUtils.isPositiveFloat(guarantee)) {
			console.alert("Le montant de la caution doit être une valeur positive");
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
