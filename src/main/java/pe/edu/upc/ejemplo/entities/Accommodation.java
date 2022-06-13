package pe.edu.upc.ejemplo.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Accommodation")
public class Accommodation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAccommodation;
	
	@Column(name = "name",length = 100, nullable = false)
	public String name;
	
	@Column(name = "description", length = 250, nullable = false)
	public String description;
	
	@Column(name = "location", length = 200, nullable = false)
	public String location;
	
	@Column(name = "facilities", length = 250, nullable = false)
	public String facilities;
	
	@Column(name = "availabilityIn", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date availabilityIn;
	
	@Column(name = "availabilityOut", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date availabilityOut;
	
	@Column(name = "numRoomAvailable", nullable = false)
	public String numRoomAvailable;
	
	@Column(name = "image")
	public String image;
	
	@ManyToOne
	@JoinColumn(name="idAccommodationType")
	private AccommodationType accommodationType;
	
	@ManyToOne
	@JoinColumn(name="idDestination")
	private Destination destination;

	public Accommodation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accommodation(int idAccommodation, String name, String description, String location, String facilities,
			Date availabilityIn, Date availabilityOut, String numRoomAvailable, String image,
			AccommodationType accommodationType, Destination destination) {
		super();
		this.idAccommodation = idAccommodation;
		this.name = name;
		this.description = description;
		this.location = location;
		this.facilities = facilities;
		this.availabilityIn = availabilityIn;
		this.availabilityOut = availabilityOut;
		this.numRoomAvailable = numRoomAvailable;
		this.image = image;
		this.accommodationType = accommodationType;
		this.destination = destination;
	}

	public int getIdAccommodation() {
		return idAccommodation;
	}

	public void setIdAccommodation(int idAccommodation) {
		this.idAccommodation = idAccommodation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public Date getAvailabilityIn() {
		return availabilityIn;
	}

	public void setAvailabilityIn(Date availabilityIn) {
		this.availabilityIn = availabilityIn;
	}

	public Date getAvailabilityOut() {
		return availabilityOut;
	}

	public void setAvailabilityOut(Date availabilityOut) {
		this.availabilityOut = availabilityOut;
	}

	public String getNumRoomAvailable() {
		return numRoomAvailable;
	}

	public void setNumRoomAvailable(String numRoomAvailable) {
		this.numRoomAvailable = numRoomAvailable;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
}
