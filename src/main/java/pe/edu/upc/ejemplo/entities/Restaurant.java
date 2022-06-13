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
@Table(name = "Restaurant")

public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
   private int idRestaurant;
	@Column(name = "name",length = 100, nullable = false)
   private String name;
	@Column(name = "description", length = 250, nullable = false)
   private String description;
	@Column(name = "location", length = 200, nullable = false)
   private String location;
	@Column(name="Typefood" , length=200 , nullable = false)
   private String Typefood;
	@Column(name = "image")
   private String image;
	@Column (name="cel" , length=20, nullable = false )
   private String cel;
	@Column(name = "email", nullable = false, length = 100)
   private String email;
	@ManyToOne
	@JoinColumn(name="idDestination")
   private Destination destination;
public Restaurant() {
	super();
	//TODO Auto-generated constructor stub
}
public Restaurant(int idRestaurant, String name, String description, String location, String typefood, String image,
		String cel, String email, Destination destination) {
	super();
	this.idRestaurant = idRestaurant;
	this.name = name;
	this.description = description;
	this.location = location;
	Typefood = typefood;
	this.image = image;
	this.cel = cel;
	this.email = email;
	this.destination = destination;
}
public int getIdRestaurant() {
	return idRestaurant;
}
public void setIdRestaurant(int idRestaurant) {
	this.idRestaurant = idRestaurant;
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
public String getTypefood() {
	return Typefood;
}
public void setTypefood(String typefood) {
	Typefood = typefood;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getCel() {
	return cel;
}
public void setCel(String cel) {
	this.cel = cel;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Destination getDestination() {
	return destination;
}

public void setDestination(Destination destination) {
	this.destination = destination;
}

}
