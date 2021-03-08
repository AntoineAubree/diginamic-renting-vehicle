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

public class DisplayClient extends MenuService {

	ClientDao clientDao = new ClientDao();

	@Override
	public void traitement() {
		List<Client> clients = clientDao.findAll();
		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des clients</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td><center>Client</center></td><td><center>Adresse</center></td><td><center>Permis de conduire</center></td><td>&nbsp;</td><td>&nbsp;</td></tr>"
				+ "</table>"
				+ "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Prénom</td><td>Téléphone</td><td>Eamil</td><td>Code postal</td><td>Ville</td><td>Numéro</td><td>Libellé de voie</td><td>Type</td><td>Numéro</td><td>Date d'obtention</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		for (Client client : clients) {
			html += "<tr>" 
					+ "  <td width='100px'>" + client.getLastname() + "</td>" 
					+ "  <td width='100px'>" + client.getFirstname() + "</td>" 
					+ "  <td width='100px'>" + client.getPhoneNumber() + "</td>"
					+ "  <td width='150px'>" + client.getEmail() + "</td>" 
					+ "  <td width='100px'>" + client.getAddress().getPostcode() + "</td>" 
					+ "  <td width='100px'>" + client.getAddress().getCity() + "</td>" 
					+ "  <td width='50px'>" + client.getAddress().getStreetNumber() + "</td>"
					+ "  <td width='150px'>" + client.getAddress().getStreetWording() + "</td>"
					+ "  <td width='100px'>" + client.getDrivingLicence().getCategoryDrivingLicence() + "</td>"
					+ "  <td width='150px'>" + client.getDrivingLicence().getDrivingLicenceNumber() + "</td>"
					+ "  <td width='150px'>" + client.getDrivingLicence().getObteningDate() + "</td>"
					+ "  <td><a class='btn-blue' href='updateClient(" + client.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='deleteClient(" + client.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
				+ "</tr>";
		}
		html += "</table>";
		console.print(html);
	}
	
	protected void updateClient(Long id) {
		List<Selectable> drivingLicenceSelectable = new ArrayList<>();
		for (CategoryDrivingLicence categoryDrivingLicence : CategoryDrivingLicence.values()) {
			drivingLicenceSelectable.add(categoryDrivingLicence);
		}
		
		Client client = clientDao.findById(id);
		Form form = new Form();
		form.addInput(new TextField("Prénom:", "firstname", client.getFirstname()));
		form.addInput(new TextField("Nom:", "lastname", client.getLastname()));
		form.addInput(new TextField("Numéro de téléphone:", "phoneNumber", client.getPhoneNumber()));
		form.addInput(new TextField("Adresse email:", "email", client.getEmail()));
		form.addInput(new TextField("Numéro de rue:", "streetNumber", String.valueOf(client.getAddress().getStreetNumber())));
		form.addInput(new TextField("Libellé de voie:", "streetWording", client.getAddress().getStreetWording()));
		form.addInput(new TextField("Code postal:", "postCode", client.getAddress().getPostcode()));
		form.addInput(new TextField("Ville:", "city", client.getAddress().getCity()));
		form.addInput(new ComboBox("Type de permis:", "categoryDrivingLicence", drivingLicenceSelectable, client.getDrivingLicence().getCategoryDrivingLicence()));
		form.addInput(new TextField("Numéro du permis:", "drivingLicenceNumber", String.valueOf(client.getDrivingLicence().getDrivingLicenceNumber())));
		form.addInput(new DateField("Date d'obtention du permis:", "obteningDate", LocalDateUtils.getDateInString(client.getDrivingLicence().getObteningDate())));
		ClientFormValidator validator = new ClientFormValidator();
		validator.setPhoneNumber(client.getPhoneNumber());
		validator.setEmail(client.getEmail());
		boolean valide = console.input("Saisissez les informations du client", form, validator);
		if (valide) {
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
			
			clientDao.update(client);
			
			traitement();
		}
	}

	protected void deleteClient(Long id) {
		Client client = clientDao.findById(id);
		if (client.getBooginks().isEmpty()) {
			clientDao.delete(client);
		} else {
			console.alert("ce client ne peut être supprimé car il a déjà effectué des réservations");
		}
		traitement();
	}

}
