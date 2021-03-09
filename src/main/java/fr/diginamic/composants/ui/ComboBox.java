package fr.diginamic.composants.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private Integer id;
	
	/** Constructeur
	 * @param label libellé
	 * @param name nom
	 * @param selectables liste des options de la liste
	 */
	public ComboBox(String label, String name, List<Selectable> selectables) {
		super(label, name);
		this.selectables = selectables;
		setEditable(true);
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
		setEditable(true);
	}
	
	@Override
	public JComponent convert() {
		JComboBox<Selectable> combobox = new JComboBox<>();
		for (Selectable selectable: selectables) {
			combobox.addItem(selectable);
		}
		if (selectedItem!=null) {
			combobox.setSelectedItem(selectedItem);
		}
		else {
			combobox.setSelectedIndex(0);
		}
		combobox.setVisible(true);
		combobox.setEditable(isEditable());
		if (!isEditable()) {
			combobox.setBackground(new Color(218, 243, 245));
		}
		return combobox;
	}

	@Override
	public Selectable getValue() {
		Optional<Selectable> opt = selectables.stream().filter(s->s.getId().equals(this.selectedItem.getId())).findFirst();
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@Override
	public void setValue(JComponent component) {
		this.selectedItem=(Selectable)((JComboBox<Selectable>)component).getSelectedItem();
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
