package fr.diginamic.composants.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;

/** Liste de sélection
 * @author RichardBONNAMY
 *
 */
public class ComboBox extends Input {

	/** Liste des options de la liste */
	private List<Selectable> selectables = new ArrayList<>();
	
	/** Item sélectionné par défaut */
	private Selectable selectedItem;
	
	/** value */
	private Long id;
	
	/** Constructeur
	 * @param label libellé
	 * @param name nom
	 * @param selectables liste des options de la liste
	 */
	public ComboBox(String label, String name, List<Selectable> selectables) {
		super(label, name);
		this.selectables = selectables;
	}
	
	/** Constructeur
	 * @param label libellé
	 * @param name nom
	 * @param selectables liste des options de la liste
	 * @param selectedItem item sélectionné par défaut dans la liste
	 */
	public ComboBox(String label, String name, List<Selectable> selectables, Selectable selectedItem) {
		super(label, name);
		this.selectedItem = selectedItem;
		this.selectables = selectables;
	}
	
	@Override
	public JComponent convert() {
		JComboBox<Selectable> combobox = new JComboBox<>();
		for (Selectable selectable: selectables) {
			combobox.addItem(selectable);
		}
		combobox.setSelectedItem(selectedItem);
		combobox.setEditable(true);
		return combobox;
	}

	@Override
	public String getValue() {
		return Long.toString(id);
	}
	
	@Override
	public void setValue(JComponent component) {
		this.id=((Selectable)((JComboBox<Selectable>)component).getSelectedItem()).getId();
	}

	@Override
	public InputType getType() {
		return InputType.SELECT;
	}

	/** Getter
	 * @return the selectables
	 */
	public List<Selectable> getSelectables() {
		return selectables;
	}

	/** Setter
	 * @param selectables the selectables to set
	 */
	public void setSelectables(List<Selectable> selectables) {
		this.selectables = selectables;
	}

	/**
	 * @return the selectedItem
	 */
	public Selectable getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(Selectable selectedItem) {
		this.selectedItem = selectedItem;
	}
}
