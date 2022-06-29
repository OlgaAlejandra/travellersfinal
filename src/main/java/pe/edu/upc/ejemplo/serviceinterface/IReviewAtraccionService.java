package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.ReviewAtraccion;

public interface IReviewAtraccionService {
	public void insert(ReviewAtraccion review);
	List<ReviewAtraccion>list();
	public void delete(int idReview);
	Optional<ReviewAtraccion> listId(int idReview);
	public void update(ReviewAtraccion review);
	
	public List<String[]> ReviewAtraccionmayor();
}
