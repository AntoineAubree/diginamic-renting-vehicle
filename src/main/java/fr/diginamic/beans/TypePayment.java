/**
 * 
 */
package fr.diginamic.beans;

import fr.diginamic.composants.ui.Selectable;

/**
 * @author Antoine
 *
 */
public enum TypePayment implements Selectable {

	CASH(1, "espèces"), CHEQUE(2, "chèque"), DEBIT_CARD(3, "carte bancaire");

	private int id;
	private String wording;

	private TypePayment(int id, String wording) {
		this.id = id;
		this.wording = wording;
	}

	public String getWording() {
		return wording;
	}

	@Override
	public Integer getId() {
		return id;
	}

}
