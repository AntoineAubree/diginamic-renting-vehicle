package fr.diginamic.form.validator;

import java.time.LocalDate;

import fr.diginamic.beans.Maintenance;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.utils.LocalDateUtils;
import fr.diginamic.utils.RegexUtils;

/**
 * validateur associé au formulaire
 * 
 * @author
 *
 */
public class RemoveVehicleFromMaintenanceFormValidator extends FormValidator {

	private Maintenance maintenance;

	@Override
	public boolean validate(Form form) {
		String finalDate = form.getValue("finalDate");
		if (finalDate == null || finalDate.trim().isEmpty()) {
			console.alert("La date de sortie de maintenance est obligatoire");
			return false;
		} else if (LocalDateUtils.getDate(finalDate).isAfter(LocalDate.now())) {
			console.alert("La date de fin de maintenance ne peut pas être suppérieure à la date du jour");
			return false;
		} else if (LocalDateUtils.getDate(finalDate).isBefore(maintenance.getStartDate())) {
			console.alert("La date de fin de maintenance ne peut pas être inférieure à la date de début");
			return false;
		} 
		
		String reparationCost = form.getValue("reparationCost").trim();
		if (reparationCost.isEmpty()) {
			console.alert("Le montant des réparations doit être renseigné");
			return false;
		} else if (!RegexUtils.isPositiveFloat(reparationCost)) {
			console.alert("Le montant des réparations doit être positif");
			return false;
		}
		return true;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

}
