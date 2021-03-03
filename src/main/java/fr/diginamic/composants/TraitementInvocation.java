package fr.diginamic.composants;

import java.util.concurrent.Callable;

import fr.diginamic.composants.reflect.ReflectUtils;

/** Traitement asynchrone permettant d'exécuter une chaine d'invocation du type: maClasse.maMethode(Long)
 * @author RichardBONNAMY
 *
 */
public class TraitementInvocation implements Callable<Void> {
	
	/** chaine d'invocation */
	private String invocation;
	
	/** Constructeur
	 * @param invocation chaine d'invocation à exécuter
	 */
	public TraitementInvocation(String invocation) {
		this.invocation=invocation;
	}

	@Override
	public Void call() throws Exception {
		ReflectUtils.invoke(invocation);
		return null;
	}

}