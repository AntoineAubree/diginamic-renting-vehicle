package fr.diginamic.services;

import java.util.List;

import fr.diginamic.beans.Vehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.dao.VehicleDao;

public class DisplayVehiclesService extends MenuService {

	VehicleDao vehicleDao = new VehicleDao();

	@Override
	public void traitement() {

		List<Vehicle> vehicles = vehicleDao.findAll();
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle);
		}

		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des véhicules</center></h1>");

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Catégorie</td><td>Type</td><td>Marque</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>kilométrage</td></tr>";
		for (Vehicle vehicle : vehicles) {
			html += "<tr>"
				  + "  <td><a class='btn-blue' href='update(" + vehicle.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
				  + "  <td><a class='btn-red' href='delete(" + vehicle.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
				  + "  <td width='150px'>" + vehicle.getModel().getCategory() + "</td>"
				  + "  <td width='150px'>" + vehicle.getModel().getTypeVehicle().getName() + "</td>"
				  + "  <td width='150px'>" + vehicle.getModel().getMake().getName() + "</td>"
				  + "  <td width='150px'>" + vehicle.getModel().getName() + "</td>"
				  + "  <td width='150px'>" + vehicle.getNumberPlate() + "</td>"
				  + "  <td width='150px'>" + vehicle.getMileage() + "</td>"
				  +"</tr>";
		}
		html += "</table>";
		
		console.print(html);
	}
	
	

}
