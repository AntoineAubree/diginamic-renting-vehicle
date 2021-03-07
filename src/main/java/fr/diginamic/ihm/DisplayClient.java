package fr.diginamic.ihm;

import java.util.List;

import fr.diginamic.beans.Client;
import fr.diginamic.composants.MenuService;
import fr.diginamic.dao.ClientDao;

public class DisplayClient extends MenuService {

	ClientDao clientDao = new ClientDao();

	@Override
	public void traitement() {
		List<Client> clients = clientDao.findAll();
		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des clients</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td width='481px'><center>Client</center></td><td width='418px'><center>Adresse</center></td><td width='390px'><center>Permis de conduire</center></td></tr>"
				+ "</table>"
				+ "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Prénom</td><td>Téléphone</td><td>Eamil</td><td>Code postal</td><td>Ville</td><td>Numéro</td><td>Libellé de voie</td><td>Type</td><td>Numéro</td><td>Date d'obtention</td></tr>";
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
				+ "</tr>";
		}
		html += "</table>";
		console.print(html);
	}

}
