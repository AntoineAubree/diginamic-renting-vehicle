package fr.diginamic.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.Address;
import fr.diginamic.beans.CategoryDrivingLicence;
import fr.diginamic.beans.Client;
import fr.diginamic.beans.DrivingLicence;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.ClientDao;
import fr.diginamic.form.validator.ClientFormValidator;
import fr.diginamic.utils.LocalDateUtils;

public class AddClient extends MenuService {

	ClientDao clientDao = new ClientDao();

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
			Client client = new Client();
			client.setFirstname(form.getValue("firstname").trim());
			client.setLastname(form.getValue("lastname").trim());
			client.setPhoneNumber(form.getValue("phoneNumber").trim());
			client.setEmail(form.getValue("email").trim());
			
			Address address = new Address();
			address.setStreetNumber(Integer.parseInt(form.getValue("streetNumber").trim()));
			address.setStreetWording(form.getValue("streetWording").trim());
			address.setPostcode(form.getValue("postCode").trim());
			address.setCity(form.getValue("city").trim());
			client.setAddress(address);
			
			DrivingLicence drivingLicence = new DrivingLicence();
			CategoryDrivingLicence categoryDrivingLicence = CategoryDrivingLicence.getById(Long.parseLong(form.getValue("categoryDrivingLicence")));
			drivingLicence.setCategoryDrivingLicence(categoryDrivingLicence);
			drivingLicence.setDrivingLicenceNumber(Integer.parseInt(form.getValue("drivingLicenceNumber").trim()));
			drivingLicence.setObteningDate(LocalDateUtils.getDate(form.getValue("obteningDate")));
			client.setDrivingLicence(drivingLicence);
			
			clientDao.insert(client);
			
			traitement();
		}

	}

}
