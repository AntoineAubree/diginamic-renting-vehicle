package fr.diginamic.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.Car;
import fr.diginamic.beans.CategoryVehicle;
import fr.diginamic.beans.Make;
import fr.diginamic.beans.Model;
import fr.diginamic.beans.Truck;
import fr.diginamic.beans.TypeVehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.MakeDao;
import fr.diginamic.dao.ModelDao;
import fr.diginamic.dao.TypeVehiculeDao;
import fr.diginamic.form.validator.CarFormValidator;
import fr.diginamic.form.validator.TruckFormValidator;

public class DisplayModel extends MenuService {

	ModelDao modelDao = new ModelDao();
	MakeDao makeDao = new MakeDao();
	TypeVehiculeDao typeVehicleDao = new TypeVehiculeDao();

	@Override
	public void traitement() {
		List<Model> models = modelDao.findAll();
		console.clear();
		console.println("<h1 class='bg-green'><center>Gestion des modèles de véhicules</center></h1>");
		console.println("<p>Ajouter une voiture : <a class='btn-green' href='addCar(" + 0 + ")'><img width=25 src='images/plus-green.png'></a></p>");
		console.println("<p>Ajouter un camion : <a class='btn-green' href='addTruck(" + 0 + ")'><img width=25 src='images/plus-green.png'></a></p>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Modifier</td><td>Supprimer</td></tr>";
		for (Model model : models) {
			html += "<tr>" 
					+ "  <td width='100px'>" + model.getName() + "</td>" 
					+ "  <td><a class='btn-blue' href='updateModel(" + model.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='deleteModel(" + model.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
				+ "</tr>";
		}
		html += "</table>";
		console.println(html);
	}
	
	protected void addCar(Integer id) {
		List<Make> makes = makeDao.findAll();
		List<Selectable> makesSelectable = new ArrayList<>();
		makesSelectable.addAll(makes);
		List<TypeVehicle> typesVehicle = typeVehicleDao.findAllCar();
		List<Selectable> typesVehicleSelectable = new ArrayList<>();
		typesVehicleSelectable.addAll(typesVehicle);
		Form form = new Form();
		form.addInput(new TextField("Nom du modèle:", "name"));
		form.addInput(new ComboBox("Marque:", "make", makesSelectable, makesSelectable.get(0)));
		form.addInput(new ComboBox("Type de véhicule:", "typeVehicle", typesVehicleSelectable, typesVehicleSelectable.get(0)));
		form.addInput(new TextField("Nombre de places:", "placesNumber"));
		CarFormValidator validator = new CarFormValidator();
		boolean valide = console.input("Saisissez les informations du modèle", form, validator);
		if (valide) {
			Car model = new Car();
			String name = form.getValue("name");
			model.setName(name.trim());
			String placesNumber = form.getValue("placesNumber");
			model.setPlacesNumber(Integer.parseInt(placesNumber.trim()));
			model.setMake(form.getValue("make"));
			model.setTypeVehicle(form.getValue("typeVehicle"));
			modelDao.insertCar(model);
			traitement();
		}
	}

	protected void addTruck(Integer id) {
		List<Make> makes = makeDao.findAll();
		List<Selectable> makesSelectable = new ArrayList<>();
		makesSelectable.addAll(makes);
		List<TypeVehicle> typesVehicle = typeVehicleDao.findAllTruck();
		List<Selectable> typesVehicleSelectable = new ArrayList<>();
		typesVehicleSelectable.addAll(typesVehicle);
		Form form = new Form();
		form.addInput(new TextField("Nom du modèle:", "name"));
		form.addInput(new ComboBox("Marque:", "make", makesSelectable, makesSelectable.get(0)));
		form.addInput(new ComboBox("Type de véhicule:", "typeVehicle", typesVehicleSelectable,
				typesVehicleSelectable.get(0)));
		form.addInput(new TextField("Volume (m3):", "volume"));
		TruckFormValidator validator = new TruckFormValidator();
		boolean valide = console.input("Saisissez les informations du modèle", form, validator);
		if (valide) {
			Truck model = new Truck();
			String name = form.getValue("name");
			model.setName(name.trim());
			String volume = form.getValue("volume");
			model.setVolume(Float.parseFloat(volume.trim()));
			model.setMake(form.getValue("make"));
			model.setTypeVehicle(form.getValue("typeVehicle"));
			modelDao.insertTruck(model);
			traitement();
		}
	}

	protected void updateModel(Integer id) {
		Model model = modelDao.findById(id);
		List<Make> makes = makeDao.findAll();
		int idMake = 0;
		for (Make make : makes) {
			if (make.getId() == model.getMake().getId()) {
				break;
			}
			idMake++;
		}
		List<Selectable> makesSelectable = new ArrayList<>();
		makesSelectable.addAll(makes);
		if (model.getTypeVehicle().getCategoryVehicle().equals(CategoryVehicle.CAR)) {
			Car car = (Car) model;
			List<TypeVehicle> typesVehicle = typeVehicleDao.findAllCar();
			int idTypeVehicle = 0;
			for (TypeVehicle typeVehicle : typesVehicle) {
				if (typeVehicle.getId() == model.getTypeVehicle().getId()) {
					break;
				}
				idTypeVehicle++;
			}
			List<Selectable> typesVehicleSelectable = new ArrayList<>();
			typesVehicleSelectable.addAll(typesVehicle);
			Form form = new Form();
			form.addInput(new TextField("Nom du modèle:", "name", model.getName()));
			form.addInput(new ComboBox("Marque:", "make", makesSelectable, makesSelectable.get(idMake)));
			form.addInput(new ComboBox("Type de véhicule:", "typeVehicle", typesVehicleSelectable,
					typesVehicleSelectable.get(idTypeVehicle)));
			form.addInput(new TextField("Nombre de places:", "placesNumber", Integer.toString(car.getPlacesNumber())));
			CarFormValidator validator = new CarFormValidator();
			boolean valide = console.input("Saisissez les informations du modèle", form, validator);
			if (valide) {
				String name = form.getValue("name");
				car.setName(name.trim());
				String placesNumber = form.getValue("placesNumber");
				car.setPlacesNumber(Integer.parseInt(placesNumber.trim()));
				car.setMake(form.getValue("make"));
				car.setTypeVehicle(form.getValue("typeVehicle"));
				modelDao.updateCar(car);
				traitement();
			}
		} else if (model.getTypeVehicle().getCategoryVehicle().equals(CategoryVehicle.TRUCK)) {
			Truck truck = (Truck) model;
			List<TypeVehicle> typesVehicle = typeVehicleDao.findAllTruck();
			int idTypeVehicle = 0;
			for (TypeVehicle typeVehicle : typesVehicle) {
				if (typeVehicle.getId() == model.getTypeVehicle().getId()) {
					break;
				}
				idTypeVehicle++;
			}
			List<Selectable> typesVehicleSelectable = new ArrayList<>();
			typesVehicleSelectable.addAll(typesVehicle);
			Form form = new Form();
			form.addInput(new TextField("Nom du modèle:", "name", model.getName()));
			form.addInput(new ComboBox("Marque:", "make", makesSelectable, makesSelectable.get(idMake)));
			form.addInput(new ComboBox("Type de véhicule:", "typeVehicle", typesVehicleSelectable,
					typesVehicleSelectable.get(idTypeVehicle)));
			form.addInput(new TextField("Volume (m3):", "volume", Float.toString(truck.getVolume())));
			TruckFormValidator validator = new TruckFormValidator();
			boolean valide = console.input("Saisissez les informations du modèle", form, validator);
			if (valide) {
				String name = form.getValue("name");
				truck.setName(name.trim());
				String volume = form.getValue("volume");
				truck.setVolume(Float.parseFloat(volume.trim()));
				truck.setMake(form.getValue("make"));
				truck.setTypeVehicle(form.getValue("typeVehicle"));
				modelDao.updateTruck(truck);
				traitement();
			}
		}
	}

	protected void deleteModel(Integer id) {
		Model model = modelDao.findById(id);
		if (model.getVehicles().isEmpty()) {
			modelDao.delete(model);
		} else {
			console.alert("Ce modèle ne peut pas être supprimé car il contient des véhicules");
		}
		traitement();
	}

}
