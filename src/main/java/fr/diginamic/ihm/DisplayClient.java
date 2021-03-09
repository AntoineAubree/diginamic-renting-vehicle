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
		console.println("<h1 class='bg-green'><center>Gestion des clients</center></h1>");
		String html = "<table cellspacing=0>"
						+ "<tr class='bg-green'>"
							+ "<td width='450px'><center>Client</center></td>"
							+ "<td width='400px'><center>Adresse</center></td>"
							+ "<td width='400px'><center>Permis de conduire</center></td>"
						+ "</tr>"
					+ "</table>"
					+ "<table cellspacing=0>"
						+ "<tr class='bg-green'>"
							+ "<td width='100px'>Nom</td>"
							+ "<td width='100px'>Prénom</td>"
							+ "<td width='100px'>Téléphone</td>"
							+ "<td width='150px'>Eamil</td>"
							+ "<td width='100px'>Code postal</td>"
							+ "<td width='100px'>Ville</td>"
							+ "<td width='50px'>Numéro</td>"
							+ "<td width='150px'>Libellé de voie</td>"
							+ "<td width='100px'>Type</td>"
							+ "<td width='150px'>Numéro</td>"
							+ "<td width='150px'>Date d'obtention</td>"
							+ "<td width='100px'>Modifier</td>"
							+ "<td width='100px'>Supprimer</td>"
						+ "</tr>";
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
		console.println(html);
	}
	
	protected void updateClient(Integer id) {
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
			String firstname = form.getValue("firstname");
			client.setFirstname(firstname.trim());
			String lastname = form.getValue("lastname");
			client.setLastname(lastname.trim());
			String phoneNumber = form.getValue("phoneNumber");
			client.setPhoneNumber(phoneNumber.trim());
			String email = form.getValue("email");
			client.setEmail(email.trim());
			
			Address address = new Address();
			String streetNumber = form.getValue("streetNumber");
			address.setStreetNumber(Integer.parseInt(streetNumber.trim()));
			String streetWording = form.getValue("streetWording");
			address.setStreetWording(streetWording.trim());
			String postCode = form.getValue("postCode");
			address.setPostcode(postCode.trim());
			String city = form.getValue("city");
			address.setCity(city.trim());
			client.setAddress(address);
			
			DrivingLicence drivingLicence = new DrivingLicence();
			drivingLicence.setCategoryDrivingLicence(form.getValue("categoryDrivingLicence"));
			String drivingLicenceNumber = form.getValue("drivingLicenceNumber");
			drivingLicence.setDrivingLicenceNumber(drivingLicenceNumber.trim());
			drivingLicence.setObteningDate(LocalDateUtils.getDate(form.getValue("obteningDate")));
			client.setDrivingLicence(drivingLicence);
			
			clientDao.update(client);
			
			traitement();
		}
	}

	protected void deleteClient(Integer id) {
		Client client = clientDao.findById(id);
		if (client.getBooginks().isEmpty()) {
			clientDao.delete(client);
		} else {
			console.alert("ce client ne peut être supprimé car il a déjà effectué des réservations");
		}
		traitement();
	}

}
