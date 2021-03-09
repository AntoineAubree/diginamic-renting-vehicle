package fr.diginamic.ihm;

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
import fr.diginamic.form.validator.PutVehicleInMaintenanceFormValidator;
import fr.diginamic.form.validator.RemoveVehicleFromMaintenanceFormValidator;
import fr.diginamic.form.validator.VehicleFormValidator;
import fr.diginamic.utils.LocalDateUtils;

public class DisplayVehicle extends MenuService {

	ModelDao modelDao = new ModelDao();
	VehicleDao vehicleDao = new VehicleDao();
	MaintenanceDao maintenanceDao = new MaintenanceDao();

	@Override
	public void traitement() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles = vehicleDao.findAll();
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle);
		}
		console.clear();
		console.println("<h1 class='bg-green'><center>Gestion des véhicules</center></h1>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Catégorie</td><td>Type</td><td>Marque</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>kilométrage</td><td>statut</td><td>Maintenance</td><td>Modifier</td><td>Supprimer</td></tr>";
		for (Vehicle vehicle : vehicles) {
			html += "<tr>" 
					+ "  <td width='100px'>" + vehicle.getModel().getCategory() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getModel().getTypeVehicle().getName() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getModel().getMake().getName() + "</td>"
					+ "  <td width='150px'>" + vehicle.getModel().getName() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getNumberPlate() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getMileage() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getStatusVehicle().getWording() + "</td>"
					+ "  <td><center><a class='btn-red' href='maintenanceManagementVehicle(" + vehicle.getId() + ")'><img width=25 src='images/settings.png'></a></center></td>"
					+ "  <td><center><a class='btn-blue' href='updateVehicle(" + vehicle.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></center></td>"
					+ "  <td><center><a class='btn-red' href='deleteVehicle(" + vehicle.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></center></td>"
				+ "</tr>";
		}
		html += "</table>";
		console.println(html);
	}

	protected void maintenanceManagementVehicle(Integer id) {
		Vehicle vehicle = vehicleDao.findById(id);
		if (vehicle.getStatusVehicle().equals(StatusVehicle.AVAILABLE)) {
			putVehicleInMaintenance(vehicle);
		} else if (vehicle.getStatusVehicle().equals(StatusVehicle.MAINTENANCE)) {
			removeVehicleFromMaintenance(vehicle);
		} else {
			console.alert("Le véhicule doit être disponible pour pouvoir être mis en maintenance");
		}
	}

	private void removeVehicleFromMaintenance(Vehicle vehicle) {
		Maintenance maintenance = vehicle.getMaintenances().get(vehicle.getMaintenances().size() - 1);
		Form form = new Form();
		form.addInput(new DateField("Date de fin de maintenance:", "finalDate"));
		form.addInput(new TextField("Coût des réparations:", "reparationCost", "0"));
		RemoveVehicleFromMaintenanceFormValidator validator = new RemoveVehicleFromMaintenanceFormValidator();
		validator.setMaintenance(maintenance);
		boolean valide = console.input("Sortir de maintennace le véhicule immatriculé : " + vehicle.getNumberPlate(), form, validator);
		if (valide) {
			String reparationCost = form.getValue("reparationCost");
			maintenance.setReparationCost(Float.parseFloat(reparationCost.trim()));
			maintenance.setFinalDate(LocalDateUtils.getDate(form.getValue("finalDate")));
			maintenanceDao.finishMaintenance(maintenance);
			traitement();
		}
	}


	private void putVehicleInMaintenance(Vehicle vehicle) {
		Form form = new Form();
		form.addInput(new DateField("Date de début de maintenance:", "startDate"));
		PutVehicleInMaintenanceFormValidator validator = new PutVehicleInMaintenanceFormValidator();
		boolean valide = console.input("Mettre en maintennace le véhicule immatriculé : " + vehicle.getNumberPlate(), form, validator);
		if (valide) {
			Maintenance maintenance = new Maintenance(LocalDateUtils.getDate(form.getValue("startDate")), vehicle);
			maintenance.setVehicle(vehicle);
			maintenanceDao.insert(maintenance);
			traitement();
		}
	}
	
	protected void updateVehicle(Integer id) {
		Vehicle vehicle = vehicleDao.findById(id);
		List<Model> models = modelDao.findAll();
		int idModel = 0;
		for (Model model : models) {
			if (model.getId() == vehicle.getModel().getId()) {
				break;
			}
			idModel++;
		}
		List<Selectable> modelsSelectable = new ArrayList<>();
		modelsSelectable.addAll(models);
		Form form = new Form();
		VehicleFormValidator validator = new VehicleFormValidator();
		validator.setIfComment(true);
		if (vehicle.getBookings().isEmpty()) {
			form.addInput(new TextField("Plaque d'immatriculation:", "numberPlate", vehicle.getNumberPlate()));
			validator.setIfNumberPlate(true);
		}
		form.addInput(new ComboBox("Véhicule:", "model", modelsSelectable,
				modelsSelectable.get(idModel)));
		form.addInput(new TextField("Kilométrage:", "mileage", Float.toString(vehicle.getMileage())));
		form.addInput(new TextField("Commentaire:", "comment", vehicle.getComment()));
		validator.setNumberPlate(vehicle.getNumberPlate());
		boolean valide = console.input("Saisissez les informations du véhicules", form, validator);
		if (valide) {
			if (vehicle.getBookings().isEmpty()) {
				String numberPlate = form.getValue("numberPlate");
				vehicle.setNumberPlate(numberPlate.toUpperCase());
			}
			Model model = form.getValue("model");
			vehicle.setModel(model);
			String mileage = form.getValue("mileage");
			vehicle.setMileage(Float.parseFloat(mileage.trim()));
			String comment = form.getValue("comment");
			vehicle.setComment(comment.trim());
			System.out.println(vehicle);
			vehicleDao.update(vehicle);
			traitement();
		}
	}

	protected void deleteVehicle(Integer id) {
		Vehicle vehicle = vehicleDao.findById(id);
		if (vehicle.getBookings().isEmpty()) {
			vehicleDao.delete(vehicle);
		} else {
			console.alert("ce véhicule ne peut être supprimé car il a déjà été réservé");
		}
		traitement();
	}

}
