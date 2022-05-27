package pe.edu.upc.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Geolocalization")
public class Geolocalization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGeolocalization;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;

	public Geolocalization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Geolocalization(int idGeolocalization, String image, String description) {
		super();
		this.idGeolocalization = idGeolocalization;
		this.image = image;
		this.description = description;
	}

	public int getIdGeolocalization() {
		return idGeolocalization;
	}

	public void setIdGeolocalization(int idGeolocalization) {
		this.idGeolocalization = idGeolocalization;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
