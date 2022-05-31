package pe.edu.upc.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accommodationType")

public class AccommodationType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAccommodationType;
	
	@Column(name = "description", nullable = false, length = 250)
	private String description;
	
	@Column(name = "numBeds", nullable = false, length = 10)
	private String numBeds;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "numSleeps", nullable = false, length = 10)
	private String numSleeps;

	public AccommodationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccommodationType(int idAccommodationType, String description, String numBeds, double price,
			String numSleeps) {
		super();
		this.idAccommodationType = idAccommodationType;
		this.description = description;
		this.numBeds = numBeds;
		this.price = price;
		this.numSleeps = numSleeps;
	}

	public int getIdAccommodationType() {
		return idAccommodationType;
	}

	public void setIdAccommodationType(int idAccommodationType) {
		this.idAccommodationType = idAccommodationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumBeds() {
		return numBeds;
	}

	public void setNumBeds(String numBeds) {
		this.numBeds = numBeds;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNumSleeps() {
		return numSleeps;
	}

	public void setNumSleeps(String numSleeps) {
		this.numSleeps = numSleeps;
	}
	
	
}
