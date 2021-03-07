package fr.diginamic.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.CategoryDrivingLicence;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.ModelDao;
import fr.diginamic.dao.VehicleDao;
import fr.diginamic.form.validator.ClientFormValidator;

public class AddClient extends MenuService {

	ModelDao modelDao = new ModelDao();
	VehicleDao vehicleDao = new VehicleDao();

	@Override
	public void traitement() {

		List<Selectable> drivingLicenceSelectable = new ArrayList<>();
		for (CategoryDrivingLicence categoryDrivingLicence : CategoryDrivingLicence.values()) {
			drivingLicenceSelectable.add(categoryDrivingLicence);
		}

		console.clear();
		console.print("<h1 class='bg-green'><center>Ajouter un véhicule</center></h1>");
		Form form = new Form();
		form.addInput(new TextField("Prénom:", "firstname"));
		form.addInput(new TextField("Nom:", "lastname"));
		form.addInput(new TextField("Numéro de téléphone:", "phoneNumber"));
		form.addInput(new TextField("Adresse email:", "email"));
		form.addInput(new TextField("Numéro de rue:", "streetNumber"));
		form.addInput(new TextField("Libellé de voie:", "streetWording"));
		form.addInput(new TextField("Code postal:", "postCode"));
		form.addInput(new TextField("Ville:", "city"));
		form.addInput(new ComboBox("Type de permis:", "categoryDrivingLicence", drivingLicenceSelectable, drivingLicenceSelectable.get(1)));
		form.addInput(new TextField("Numéro du permis:", "drivingLicenceNumber"));
		form.addInput(new DateField("Date d'obtention du permis:", "obteningDate"));
		ClientFormValidator validator = new ClientFormValidator();
		boolean valide = console.input("Saisissez les informations du client", form, validator);
		if (valide) {
			traitement();
		}

	}

}
