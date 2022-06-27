package pe.edu.upc.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Destination")
public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDestination;
	
	@Column(name = "name", length = 50,nullable = false)
	private String name;

	@Column(name="country", length = 50, nullable = false)
	private String country;
	
	@Column(name = "description", length = 250, nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "idTypeTrip")
	private TypeTrip typeTrip;

	@Column(name = "image")
	private String image;
	
	public Destination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Destination(int idDestination, String name, String country, String description, TypeTrip typeTrip,
			String image) {
		super();
		this.idDestination = idDestination;
		this.name = name;
		this.country = country;
		this.description = description;
		this.typeTrip = typeTrip;
		this.image = image;
	}



	public int getIdDestination() {
		return idDestination;
	}

	public void setIdDestination(int idDestination) {
		this.idDestination = idDestination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeTrip getTypeTrip() {
		return typeTrip;
	}

	public void setTypeTrip(TypeTrip typeTrip) {
		this.typeTrip = typeTrip;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
