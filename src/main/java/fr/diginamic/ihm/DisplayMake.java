package fr.diginamic.ihm;

import java.util.List;

import fr.diginamic.beans.Make;
import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.dao.MakeDao;
import fr.diginamic.form.validator.MakeFormValidator;

public class DisplayMake extends MenuService {

	MakeDao makeDao = new MakeDao();

	@Override
	public void traitement() {
		List<Make> makes = makeDao.findAll();
		console.clear();
		console.println("<h1 class='bg-green'><center>Gestion des marques de véhicules</center></h1>");
		console.println("<p>Ajouter une marque : <a class='btn-green' href='addMake(" + 0 + ")'><img width=25 src='images/plus-green.png'></a></p>");
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>Nom</td><td>Modifier</td><td>Supprimer</td></tr>";
		for (Make make : makes) {
			html += "<tr>" 
					+ "  <td width='100px'>" + make.getName() + "</td>" 
					+ "  <td><a class='btn-blue' href='updateMake(" + make.getId() + ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='deleteMake(" + make.getId() + ")'><img width=25 src='images/trash-red-xs.png'></a></td>"
				+ "</tr>";
		}
		html += "</table>";
		console.println(html);
	}
	
	protected void addMake(Integer id) {
		Form form = new Form();
		form.addInput(new TextField("Nom de la marque:", "name"));
		MakeFormValidator validator = new MakeFormValidator();
		boolean valide = console.input("Saisissez les informations de la marque", form, validator);
		if (valide) {
			String name = form.getValue("name");
			Make make = new Make(name.trim());
			makeDao.insert(make);
			traitement();
		}
	}

	protected void updateMake(Integer id) {
		Make make = makeDao.findById(id);
		Form form = new Form();
		form.addInput(new TextField("Nom de la marque:", "name", make.getName()));
		MakeFormValidator validator = new MakeFormValidator();
		validator.setName(make.getName());
		boolean valide = console.input("Saisissez les informations de la marque", form, validator);
		if (valide) {
			String name = form.getValue("name");
			make.setName(name.trim());
			makeDao.update(make);
			traitement();
		}
	}

	protected void deleteMake(Integer id) {
		Make make = makeDao.findById(id);
		if (make.getModels().isEmpty()) {
			makeDao.delete(make);
		} else {
			console.alert("Cette marque ne peut pas être supprimée car elle contient des modèles de véhicule");
		}
		traitement();
	}

}
