package fr.diginamic;

import fr.diginamic.composants.AbstractApplication;
import fr.diginamic.services.AddVehicleService;
import fr.diginamic.services.DisplayVehiclesService;

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
	 * 
	 */
	public void main() {

		addMenu(1, "Véhicule");

		addMenuOption(1, "Afficher les véhicules", new DisplayVehiclesService());
		addMenuOption(1, "Ajouter un véhicule", new AddVehicleService());
	}
}