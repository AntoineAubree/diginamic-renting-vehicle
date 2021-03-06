package fr.diginamic;

import fr.diginamic.composants.AbstractApplication;
import fr.diginamic.ihm.AddBooking;
import fr.diginamic.ihm.AddClient;
import fr.diginamic.ihm.AddVehicle;
import fr.diginamic.ihm.DisplayBooking;
import fr.diginamic.ihm.DisplayClient;
import fr.diginamic.ihm.DisplayMake;
import fr.diginamic.ihm.DisplayModel;
import fr.diginamic.ihm.DisplayNotPayedBooking;
import fr.diginamic.ihm.DisplayPayedBooking;
import fr.diginamic.ihm.DisplayTypeVehicle;
import fr.diginamic.ihm.DisplayVehicle;

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
		addMenu(2, "Client");
		addMenu(3, "Réservation");

		addMenuOption(1, "Gestion les véhicules", new DisplayVehicle());
		addMenuOption(1, "Ajout d'un véhicule", new AddVehicle());
		addMenuOption(1, "Gestion des marques de véhicules", new DisplayMake());
		addMenuOption(1, "Gestion des types de véhicules", new DisplayTypeVehicle());
		addMenuOption(1, "Gestion des modèles de véhicules", new DisplayModel());
		
		addMenuOption(2, "Gestion les clients", new DisplayClient());
		addMenuOption(2, "Ajout d'un client", new AddClient());
	
		addMenuOption(3, "Gestion des réservations en cours", new DisplayBooking());
		addMenuOption(3, "Gestion des résevations non payées", new DisplayNotPayedBooking());
		addMenuOption(3, "Affichage des anciennes réservations", new DisplayPayedBooking());
		addMenuOption(3, "Ajout d'une réservation", new AddBooking());
	}
}