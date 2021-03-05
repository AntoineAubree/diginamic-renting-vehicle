package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.Maintenance;
import fr.diginamic.beans.Model;
import fr.diginamic.beans.StatusVehicle;
import fr.diginamic.beans.Vehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.MaintenanceDao;
import fr.diginamic.dao.ModelDao;
import fr.diginamic.dao.VehicleDao;
import fr.diginamic.utils.LocalDateUtils;

public class DisplayVehiclesService extends MenuService {

	ModelDao modelDao = new ModelDao();
	VehicleDao vehicleDao = new VehicleDao();
	MaintenanceDao maintenanceDao = new MaintenanceDao();

	@Override
	public void traitement() {

		List<Vehicle> vehicles = vehicleDao.findAll();

		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des véhicules</center></h1>");

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>Catégorie</td><td>Type</td><td>Marque</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>kilométrage</td><td>statut</td></tr>";
		for (Vehicle vehicle : vehicles) {
			html += "<tr>" 
					+ "  <td><a class='btn-blue' href='updateVehicle(" + vehicle.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='deleteVehicle(" + vehicle.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='maintenanceVehicle(" + vehicle.getId() + ")'><img width=25 src='images/settings.png'></a></td>"
					+ "  <td width='150px'>" + vehicle.getModel().getCategory() + "</td>" 
					+ "  <td width='150px'>" + vehicle.getModel().getTypeVehicle().getName() + "</td>" 
					+ "  <td width='150px'>" + vehicle.getModel().getMake().getName() + "</td>"
					+ "  <td width='150px'>" + vehicle.getModel().getName() + "</td>" 
					+ "  <td width='150px'>" + vehicle.getNumberPlate() + "</td>" 
					+ "  <td width='150px'>" + vehicle.getMileage() + "</td>" 
					+ "  <td width='150px'>" + vehicle.getStatusVehicle() + "</td>" + "</tr>";
		}
		html += "</table>";

		console.print(html);
	}

	protected void maintenanceVehicle(Long id) {
		Vehicle vehicle = vehicleDao.findById(id);
		
		Form form = new Form();
		if (vehicle.getStatusVehicle().equals(StatusVehicle.AVAILABLE.getWording())) {
			form.addInput(new DateField("Date de début de maintenance:", "startDate"));
			FormMaintenanceValidator validator = new FormMaintenanceValidator();
			boolean valide = console.input("Mettre en maintennace le véhicule immatriculé : " + vehicle.getNumberPlate(), form, validator);
			if (valide) {
				Maintenance maintenance = new Maintenance(LocalDateUtils.getDate(form.getValue("startDate")), vehicle);
				vehicle.addMaintenance(maintenance);
				vehicle.setStatusVehicle(StatusVehicle.MAINTENANCE);
				maintenanceDao.insert(maintenance);
				traitement();
			}
		} else if (vehicle.getStatusVehicle().equals(StatusVehicle.MAINTENANCE.getWording())) {
			
		}
		else {
			console.alert("Le véhicule doit être disponible pour pouvoir être mis en maintenance");
		}
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
		form.addInput(new ComboBox("Véhicule:", "model", modelsSelectable,
				modelsSelectable.get((vehicle.getModel().getId()).intValue() - 1)));
		form.addInput(new TextField("Kilométrage:", "mileage", Float.toString(vehicle.getMileage())));
		form.addInput(new TextField("Commentaire:", "comment", vehicle.getComment()));
		FormVehicleValidator validator = new FormVehicleValidator();
		validator.setNumberPlate(vehicle.getNumberPlate());
		boolean valide = console.input("Saisissez les informations du véhicules", form, validator);

		if (valide) {
			if (vehicle.getBookings().isEmpty()) {
				vehicle.setNumberPlate(form.getValue("numberPlate").toUpperCase());
			}
			Model model = modelDao.findById(Long.parseLong(form.getValue("model")));
			vehicle.setModel(model);
			vehicle.setMileage(Float.parseFloat(form.getValue("mileage")));
			vehicle.setComment(form.getValue("comment"));
			vehicleDao.update(vehicle);
			traitement();
		}
	}

	protected void deleteVehicle(Long id) {
		Vehicle vehicle = vehicleDao.findById(id);
		if (vehicle.getBookings().isEmpty()) {
			vehicleDao.delete(vehicle);
		} else {
			System.out.println("cette donnée ne peut être supprimée");
		}
		traitement();
	}

}
