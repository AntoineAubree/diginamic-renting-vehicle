package fr.diginamic.form.validator;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.dao.MakeDao;

/**
 * validateur associé au formulaire
 * 
 * @author rbonn
 *
 */
public class MakeFormValidator extends FormValidator {

	MakeDao makeDao = new MakeDao();
	private String name;

	@Override
	public boolean validate(Form form) {
		String name = form.getValue("name").trim();
		if (name.isEmpty() || name.length() > 100) {
			console.alert("Le nom de la marque est obigatoire (100 caractères maximum)");
			return false;
		}
		boolean makeAvailable = makeDao.checkIfExist(name);
		if (!makeAvailable && !name.equals(this.name)) {
			console.alert("Cette marque est déjà renseignée");
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
