package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.Model;
import fr.diginamic.beans.Vehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.ModelDao;
import fr.diginamic.dao.VehicleDao;

public class DisplayVehiclesService extends MenuService {

	ModelDao modelDao = new ModelDao();
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
				  + "  <td><a class='btn-blue' href='updateVehicle(" + vehicle.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
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
	
	protected void updateVehicle(Long id) {
		Vehicle vehicle = vehicleDao.findById(id);
		List<Model> models = modelDao.findAll();
		List<Selectable> modelsSelectable = new ArrayList<>();
		modelsSelectable.addAll(models);
		
		Form form = new Form();
		if (vehicle.getBookings().isEmpty()) {
			form.addInput(new TextField("Plaque d'immatriculation:", "numberPlate", vehicle.getNumberPlate()));
		}
		form.addInput(new ComboBox("Véhicule:", "model", modelsSelectable, modelsSelectable.get((vehicle.getModel().getId()).intValue() - 1)));
		form.addInput(new TextField("Kilométrage:", "mileage", Float.toString(vehicle.getMileage())));
		FormVehicleValidator validator = new FormVehicleValidator();
		validator.setNumberPlate(vehicle.getNumberPlate());
		boolean valide = console.input("Saisissez les informations du véhicules", form, validator);
		
		if (valide) {
			if (vehicle.getBookings().isEmpty()) {
				vehicle.setNumberPlate(form.getValue("numberPlate").toUpperCase());
			}
			vehicle.setMileage(Float.parseFloat(form.getValue("mileage")));
			Model model = modelDao.findById(Long.parseLong(form.getValue("model")));
			vehicle.setModel(model);
			vehicleDao.update(vehicle);
			traitement();
		}
	}
	
	

}
