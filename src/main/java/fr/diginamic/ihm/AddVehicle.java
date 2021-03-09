package fr.diginamic.ihm;

import java.util.List;

import fr.diginamic.beans.Model;
import fr.diginamic.beans.Vehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.ModelDao;
import fr.diginamic.dao.VehicleDao;
import fr.diginamic.form.validator.VehicleFormValidator;

public class AddVehicle extends MenuService {

	ModelDao modelDao = new ModelDao();
	VehicleDao vehicleDao = new VehicleDao();

	@Override
	public void traitement() {

		List<Model> models = modelDao.findAll();

		console.clear();
		console.println("<h1 class='bg-green'><center>Ajouter un véhicule</center></h1>");
		console.println("<p>Ajouter un modèle qui n'est pas dans la liste <a class='btn-green' href='#'><img width=25 src='images/plus-green.png'></a></p>");

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Sélectionner</td><td>Catégorie</td><td>Type</td><td>Marque</td><td>Modèle</td></tr>";
		for (Model model : models) {
			html += "<tr>" 
					+ "  <td><a class='btn-green' href='addVehicle(" + model.getId() + ")'><img width=25 src='images/plus-green.png'></a></td>" 
					+ "  <td width='150px'>" + model.getCategory() + "</td>" 
					+ "  <td width='150px'>" + model.getTypeVehicle().getName() + "</td>" 
					+ "  <td width='150px'>" + model.getMake().getName() + "</td>"
					+ "  <td width='150px'>" + model.getName() + "</td>" 
				+ "</tr>";
		}
		html += "</table>";
		console.println(html);

	}

	protected void addVehicle(Integer id) {
		Form form = new Form();
		form.addInput(new TextField("Plaque d'immatriculation:", "numberPlate"));
		form.addInput(new TextField("Kilométrage:", "mileage", "0"));
		VehicleFormValidator validator = new VehicleFormValidator();
		validator.setIfNumberPlate(true);
		boolean valide = console.input("Saisissez les informations du véhicule", form, validator);
		if (valide) {
			Model model = modelDao.findById(id);
			String numberPlate = form.getValue("numberPlate");
			String mileage = form.getValue("mileage");
			Vehicle vehicle = new Vehicle(numberPlate.toUpperCase(), Float.parseFloat(mileage.trim()));
			vehicle.setModel(model);
			vehicleDao.insert(vehicle);
			traitement();
		}
	}

}
