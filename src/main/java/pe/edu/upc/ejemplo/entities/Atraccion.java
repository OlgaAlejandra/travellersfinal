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
@Table(name="Atraccion")
public class Atraccion {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAtraccion;
	@Column(name = "name",length = 100, nullable = false)
	public String name;
	@Column(name = "description", length = 250, nullable = false)
	public String description;
	@Column(name = "location", length = 200, nullable = false)
	public String location;
	@Column(name = "startTime", nullable = false)
	@DateTimeFormat(pattern = "HH:mm")
	private Date startTime;
	@Column(name = "availabilityIn", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date availabilityIn;
	@Column(name = "type", length = 100, nullable = false)
	public String type;
	@Column(name = "image")
	public String image;
	@Column(name = "price", length = 100, nullable = false)
	public String price;
	@ManyToOne
	@JoinColumn(name="idDestination")
	private Destination destination;
	public Atraccion() {
		super();
		//TODO Auto-generated constructor stub
	}
	public Atraccion(int idAtraccion, String name, String description, String location, Date startTime,
			Date availabilityIn, String type, String image, String price, Destination destination) {
		super();
		this.idAtraccion = idAtraccion;
		this.name = name;
		this.description = description;
		this.location = location;
		this.startTime = startTime;
		this.availabilityIn = availabilityIn;
		this.type = type;
		this.image = image;
		this.price = price;
		this.destination = destination;
	}
	public int getIdAtraccion() {
		return idAtraccion;
	}
	public void setIdAtraccion(int idAtraccion) {
		this.idAtraccion = idAtraccion;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getAvailabilityIn() {
		return availabilityIn;
	}
	public void setAvailabilityIn(Date availabilityIn) {
		this.availabilityIn = availabilityIn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
}
