package fr.diginamic.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.Booking;
import fr.diginamic.beans.Car;
import fr.diginamic.beans.CategoryDrivingLicence;
import fr.diginamic.beans.CategoryVehicle;
import fr.diginamic.beans.Client;
import fr.diginamic.beans.Truck;
import fr.diginamic.beans.Vehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.BookingDao;
import fr.diginamic.dao.ClientDao;
import fr.diginamic.dao.VehicleDao;
import fr.diginamic.form.validator.CreateBookingFormValidator;
import fr.diginamic.utils.LocalDateUtils;

public class AddBooking extends MenuService {

	VehicleDao vehicleDao = new VehicleDao();
	ClientDao clientDao = new ClientDao();
	BookingDao bookingDao = new BookingDao();

	private static Client client;
	private static Vehicle vehicle;

	@Override
	public void traitement() {
		selectClient(0);
	}

	protected void selectClient(Integer id) {
		List<Client> clients = clientDao.findAll();
		console.clear();
		console.println("<h1 class='bg-green'><center>Créer une réservation</center></h1>");
		console.println("<h2 class='bg-green'><center>Sélectionnez le client</center></h2>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'>"
					+ "<td width='450px'><center>Client</center></td>"
					+ "<td width='400px'><center>Adresse</center></td>"
					+ "<td width='400px'><center>Permis de conduire</center></td>"
				+ "</tr>"
			+ "</table>"
			+ "<table cellspacing=0>"
				+ "<tr class='bg-green'>"
					+ "<td>Sélectionner</td>"
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
				+ "</tr>";
		for (Client client : clients) {
			html += "<tr>" 
					+ "  <td><a class='btn-green' href='selectVehicle(" + client.getId() + ")'><img width=25 src='images/plus-green.png'></a></td>" 
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
		console.println(html);
	}

	protected void selectVehicle(Integer id) {
		AddBooking.client = clientDao.findById(id);
		List<Vehicle> vehicles = new ArrayList<>();
		if (AddBooking.client.getDrivingLicence().getCategoryDrivingLicence().equals(CategoryDrivingLicence.C)
				|| AddBooking.client.getDrivingLicence().getCategoryDrivingLicence().equals(CategoryDrivingLicence.D)) {
			vehicles = vehicleDao.findAllAvailable();
		} else if (AddBooking.client.getDrivingLicence().getCategoryDrivingLicence().equals(CategoryDrivingLicence.B)) {
			vehicles = vehicleDao.findAllCarAvailable();
		}
		console.clear();
		console.println("<h1 class='bg-green'><center>Créer une réservation</center></h1>");
		console.println("<h2 class='bg-green'><center>Sélectionnez le véhicule</center></h2>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Sélectionner</td><td>Catégorie</td><td>Type</td><td>Marque</td><td>Modèle</td><td>Plaque d'immatriculation</td><td>kilométrage</td><td>Nombre de places / volume</td></tr>";
		for (Vehicle vehicle : vehicles) {
			html += "<tr>" + "  <td><a class='btn-green' href='createBooking(" + vehicle.getId() + ")'><img width=25 src='images/plus-green.png'></a></td>" 
					+ "  <td width='100px'>" + vehicle.getModel().getCategory() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getModel().getTypeVehicle().getName() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getModel().getMake().getName() + "</td>" 
					+ "  <td width='150px'>" + vehicle.getModel().getName() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getNumberPlate() + "</td>" 
					+ "  <td width='100px'>" + vehicle.getMileage() + "</td>";
			if (vehicle.getModel().getTypeVehicle().getCategoryVehicle().equals(CategoryVehicle.CAR)) {
				Car car = (Car) vehicle.getModel();
				html += "  <td width='100px'>" + car.getPlacesNumber() + " places</td>";
			} else if (vehicle.getModel().getTypeVehicle().getCategoryVehicle().equals(CategoryVehicle.TRUCK)) {
				Truck truck = (Truck) vehicle.getModel();
				html += "  <td width='100px'>" + truck.getVolume() + " m3</td>";
			}
			html += "</tr>";
		}
		html += "</table>";
		console.println(html);
	}
	
	protected void createBooking(Integer id) {
		AddBooking.vehicle = vehicleDao.findById(id);
		Form form = new Form();
		form.addInput(new DateField("Date de début de réservation:", "startDate"));
		form.addInput(new DateField("Date de fin de réservation estimée:", "estimatedFinalDate"));
		form.addInput(new TextField("Commentaire:", "comment"));
		CreateBookingFormValidator validator = new CreateBookingFormValidator();
		boolean valide = console.input("Saisissez les informations de la réservation", form, validator);
		if (valide) {
			Booking booking = new Booking();
			booking.setStartDate(LocalDateUtils.getDate(form.getValue("startDate")));
			booking.setEstimatedFinalDate(LocalDateUtils.getDate(form.getValue("estimatedFinalDate")));
			String comment = form.getValue("comment");
			booking.setComment(comment.trim());
			booking.setStartMileage(AddBooking.vehicle.getMileage());
			booking.setClient(AddBooking.client);
			booking.setVehicle(AddBooking.vehicle);
			
			bookingDao.insert(booking);
			
			traitement();
		}
	}


}
