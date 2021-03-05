package fr.diginamic.services;

import java.util.List;

import fr.diginamic.beans.Model;
import fr.diginamic.beans.Vehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.ModelDao;
import fr.diginamic.dao.VehicleDao;

public class AddVehicleService extends MenuService {

	ModelDao modelDao = new ModelDao();
	VehicleDao vehicleDao = new VehicleDao();

	@Override
	public void traitement() {

		List<Model> models = modelDao.findAll();

		console.clear();
		console.print("<h1 class='bg-green'><center>Ajouter un véhicule</center></h1>");
		console.print("<h2 class='bg-green'><center>Sélectionnez le modèle</center></h2>");
		console.print("<p>Si le modèle n'est pas dans la liste, <a class='btn-blue' href='#'>cliquer ici</a></p>");
		console.print("<br>");

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>Catégorie</td><td>Marque</td><td>Modèle</td><td>Type</td></tr>";
		for (Model model : models) {
			html += "<tr>" 
					+ "  <td><a class='btn-blue' href='setVehicleData(" + model.getId() + ")'><img width=25 src='images/plus-green.png'></a></td>" 
					+ "  <td width='150px'>" + model.getCategory() + "</td>" 
					+ "  <td width='150px'>" + model.getMake().getName() + "</td>"
					+ "  <td width='150px'>" + model.getName() + "</td>" 
					+ "  <td width='150px'>" + model.getTypeVehicle().getName() + "</td>" + "</tr>";
		}
		html += "</table>";
		console.print(html);

	}

	protected void setVehicleData(Long id) {
		Form form = new Form();
		form.addInput(new TextField("PLaque d'immatriculation:", "numberPlate"));
		form.addInput(new TextField("Kilométrage:", "mileage", "0"));
		FormAddVehicleValidator validator = new FormAddVehicleValidator();
		boolean valide = console.input("Saisissez les informations du véhicules", form, validator);
		if (valide) {
			Model model = modelDao.findById(id.intValue());
			Vehicle vehicle = new Vehicle(form.getValue("numberPlate").toUpperCase(), Float.parseFloat(form.getValue("mileage")));
			vehicle.setModel(model);
			vehicleDao.insert(vehicle);
			traitement();
		}
	}

}
