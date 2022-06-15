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
@Table(name = "ReviewTour")
public class ReviewTour {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReview;
	
	@Column(name = "description", length = 250, nullable = false)
	public String description;
	
	@Column(name = "numRating", nullable = false)
	public String numRating;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idAttraccion")
	private Atraccion atraccion;
	
	@ManyToOne
	@JoinColumn(name = "idRestaurant")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "idTour")
	private Tour tour;
	
	@ManyToOne
	@JoinColumn(name = "idAccommodation")
	private Accommodation accommodation;

	public ReviewTour() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewTour(int idReview, String description, String numRating, Usuario usuario, Atraccion atraccion,
			Restaurant restaurant, Tour tour, Accommodation accommodation) {
		super();
		this.idReview = idReview;
		this.description = description;
		this.numRating = numRating;
		this.usuario = usuario;
		this.atraccion = atraccion;
		this.restaurant = restaurant;
		this.tour = tour;
		this.accommodation = accommodation;
	}

	public int getIdReview() {
		return idReview;
	}

	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumRating() {
		return numRating;
	}

	public void setNumRating(String numRating) {
		this.numRating = numRating;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Atraccion getAtraccion() {
		return atraccion;
	}

	public void setAtraccion(Atraccion atraccion) {
		this.atraccion = atraccion;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}
	
	
	
}
