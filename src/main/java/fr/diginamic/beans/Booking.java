/**
 * 
 */
package fr.diginamic.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Antoine
 *
 */
@Entity
public abstract class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	@Column(name = "estimated_final_date", nullable = false)
	private LocalDate estimatedFinalDate;
	@Column(name = "final_date")
	private LocalDate finalDate;
	@Column(name = "start_mileage")
	private int startMileage;
	@Column(name = "final_mileage")
	private int finalMileage;
	@Column(name = "comment", length = 400)
	private String comment;
	@ManyToOne
	@JoinColumn(name = "id_client", nullable = false)
	private Client client;
	@ManyToOne
	@JoinColumn(name = "id_vehicle", nullable = false)
	private Vehicle vehicle;
	@OneToOne
	@JoinColumn(name = "id_receipt")
	private Receipt receipt;

	public Booking() {
	}

}
