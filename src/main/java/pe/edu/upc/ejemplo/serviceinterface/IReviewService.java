package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Review;

public interface IReviewService {

	public void insert(Review review);
	List<Review> list();
	public void delete(int idReview);
	Optional<Review> listId(int idReview);
	public void update(Review review);
}
