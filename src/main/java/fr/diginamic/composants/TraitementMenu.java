package fr.diginamic.composants;

import java.util.concurrent.Callable;

/** Traitement asynchrone permettant d'exécuter un service
 * @author RichardBONNAMY
 *
 */
public class TraitementMenu implements Callable<Void> {
	
	/** menuService */
	private MenuService menuService;
	
	/** Constructeur
	 * @param menuService service à exécuter en asynchrone
	 */
	public TraitementMenu(MenuService menuService) {
		this.menuService=menuService;
	}

	@Override
	public Void call() throws Exception {
		menuService.traitement();
		return null;
	}

}