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
@Table(name = "Tour")
public class Tour {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTour;
	
	@Column(name = "name",length = 100, nullable = false)
	private String name;
	
	@Column(name="description",length = 250, nullable = false)
	private String description;
	
	@Column(name = "location", length = 200, nullable = false)
	private String location;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "availability", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date availability;
	
	@Column(name = "startTime", nullable = false)
	@DateTimeFormat(pattern = "HH:mm")
	private Date startTime;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne
	@JoinColumn(name="idDestination")
	private Destination destination;

	public Tour() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tour(int idTour, String name, String description, String location, double price, Date availability,
			Date startTime, String image, Destination destination) {
		super();
		this.idTour = idTour;
		this.name = name;
		this.description = description;
		this.location = location;
		this.price = price;
		this.availability = availability;
		this.startTime = startTime;
		this.image = image;
		this.destination = destination;
	}

	public int getIdTour() {
		return idTour;
	}

	public void setIdTour(int idTour) {
		this.idTour = idTour;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getAvailability() {
		return availability;
	}

	public void setAvailability(Date availability) {
		this.availability = availability;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
}
