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
		for (Client client : clients) {
			System.out.println(client);
		}
		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des clients</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Prénom</td><td>Téléphone</td><td>Eamil</td><td>Code postal</td><td>Ville</td><td>Numéro</td><td>Rue</td></tr>";
		for (Client client : clients) {
			html += "<tr>" 
					+ "  <td width='100px'>" + client.getLastname() + "</td>" 
					+ "  <td width='100px'>" + client.getFirstname() + "</td>" 
					+ "  <td width='100px'>" + client.getPhoneNumber() + "</td>"
					+ "  <td width='150px'>" + client.getEmail() + "</td>" 
					+ "  <td width='100px'>" + client.getAddress().getPostcode() + "</td>" 
					+ "  <td width='100px'>" + client.getAddress().getCity() + "</td>" 
					+ "  <td width='50px'>" + client.getAddress().getNumber() + "</td>"
					+ "  <td width='150px'>" + client.getAddress().getStreetWording() + "</td>"
				+ "</tr>";
		}
		html += "</table>";
		console.print(html);
	}

}
