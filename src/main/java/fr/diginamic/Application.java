package fr.diginamic;

import fr.diginamic.beans.CategoryVehicle;
import fr.diginamic.beans.TypeVehicle;
import fr.diginamic.composants.AbstractApplication;
import fr.diginamic.dao.TypeVehiculeDao;

/**
 * Fenêtre principale qui porte les principaux composants graphiques de
 * l'application:<br>
 * - les boutons du menu,<br>
 * - le panneau d'affichage des résultats<br>
 * 
 * @author RichardBONNAMY
 *
 */
public class Application extends AbstractApplication {

	/** serialVersionUID */
	private static final long serialVersionUID = 6755835482616236832L;

	/**
	 * Constructeur
	 * 
	 * @param title titre
	 */
	public Application(String title) {
		super(title);
	}

	/**
	 * Code principal
	 * 
	 */
	public void main() {
		TypeVehicle type1 = new TypeVehicle();
		type1.setName("SUV");
		type1.setCategoryVehicle(CategoryVehicle.CAR);

		TypeVehiculeDao typeVehicleDao = new TypeVehiculeDao();
		typeVehicleDao.insert(type1);
	}
}