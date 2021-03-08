package fr.diginamic.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.beans.CategoryVehicle;
import fr.diginamic.beans.TypeVehicle;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.TypeVehiculeDao;
import fr.diginamic.form.validator.TypeVehicleFormValidator;

public class DisplayTypeVehicle extends MenuService {

	TypeVehiculeDao typeVehiculeDao = new TypeVehiculeDao();

	@Override
	public void traitement() {
		List<TypeVehicle> typesVehicle = typeVehiculeDao.findAll();
		console.clear();
		console.print("<h1 class='bg-green'><center>Liste des types de véhicules</center></h1>");
		console.print("<p>Ajouter un type de véhicule : <a class='btn-green' href='addTypeVehicle(" + 0 + ")'><img width=25 src='images/plus-green.png'></a></p>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Catégorie</td><td>Type</td><td>Tarif journalier</td><td>Caution</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		for (TypeVehicle typeVehicle : typesVehicle) {
			html += "<tr>" 
					+ "  <td width='100px'>" + typeVehicle.getCategoryVehicle().getWording() + "</td>" 
					+ "  <td width='100px'>" + typeVehicle.getName() + "</td>" 
					+ "  <td width='100px'>" + typeVehicle.getDailyPrince() + "</td>" 
					+ "  <td width='100px'>" + typeVehicle.getGuarantee() + "</td>" 
					+ "  <td><a class='btn-blue' href='updateTypeVehicle(" + typeVehicle.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='deleteTypeVehicle(" + typeVehicle.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
				+ "</tr>";
		}
		html += "</table>";
		console.print(html);
	}
	
	protected void addTypeVehicle(Long id) {
		List<Selectable> categoryVehicleSelectable = new ArrayList<>();
		for (CategoryVehicle categoryVehicle : CategoryVehicle.values()) {
			categoryVehicleSelectable.add(categoryVehicle);
		}
		Form form = new Form();
		form.addInput(new ComboBox("Catégorie de véhicule:", "categoryVehicle", categoryVehicleSelectable, categoryVehicleSelectable.get(0)));
		form.addInput(new TextField("Nom du type", "name"));
		form.addInput(new TextField("Tarif journalier", "dailyPrice"));
		form.addInput(new TextField("Caution", "guarantee"));
		TypeVehicleFormValidator validator = new TypeVehicleFormValidator();
		boolean valide = console.input("Saisissez les informations du type de véhicule", form, validator);
		if (valide) {
			TypeVehicle typeVehicle = new TypeVehicle();
			CategoryVehicle categoryVehicle = CategoryVehicle.getById(Long.parseLong(form.getValue("categoryVehicle")));
			typeVehicle.setCategoryVehicle(categoryVehicle);
			typeVehicle.setName(form.getValue("name").trim());
			typeVehicle.setDailyPrince(Float.parseFloat(form.getValue("dailyPrice").trim()));
			typeVehicle.setGuarantee(Float.parseFloat(form.getValue("guarantee").trim()));
			typeVehiculeDao.insert(typeVehicle);
			traitement();
		}
	}

	protected void updateTypeVehicle(Long id) {
		List<Selectable> categoryVehicleSelectable = new ArrayList<>();
		for (CategoryVehicle categoryVehicle : CategoryVehicle.values()) {
			categoryVehicleSelectable.add(categoryVehicle);
		}
		TypeVehicle typeVehicle = typeVehiculeDao.findById(id);
		Form form = new Form();
		form.addInput(new ComboBox("Catégorie de véhicule:", "categoryVehicle", categoryVehicleSelectable, typeVehicle.getCategoryVehicle()));
		form.addInput(new TextField("Nom du type", "name", typeVehicle.getName()));
		form.addInput(new TextField("Tarif journalier", "dailyPrice", String.valueOf(typeVehicle.getDailyPrince())));
		form.addInput(new TextField("Caution", "guarantee", String.valueOf(typeVehicle.getGuarantee())));
		TypeVehicleFormValidator validator = new TypeVehicleFormValidator();
		validator.setName(typeVehicle.getName());
		boolean valide = console.input("Saisissez les informations du type de véhicule", form, validator);
		if (valide) {
			typeVehicle.setName(form.getValue("name").trim());
			CategoryVehicle categoryVehicle = CategoryVehicle.getById(Long.parseLong(form.getValue("categoryVehicle")));
			typeVehicle.setCategoryVehicle(categoryVehicle);
			typeVehicle.setDailyPrince(Float.parseFloat(form.getValue("dailyPrice").trim()));
			typeVehicle.setGuarantee(Float.parseFloat(form.getValue("guarantee").trim()));
			typeVehiculeDao.update(typeVehicle);
			traitement();
		}
	}

	protected void deleteTypeVehicle(Long id) {
		TypeVehicle typeVehicle = typeVehiculeDao.findById(id);
		if (typeVehicle.getModels().isEmpty()) {
			typeVehiculeDao.delete(typeVehicle);
		} else {
			console.alert("Ce type ne peut pas être supprimé car il contient des modèles de véhicule");
		}
		traitement();
	}

}
