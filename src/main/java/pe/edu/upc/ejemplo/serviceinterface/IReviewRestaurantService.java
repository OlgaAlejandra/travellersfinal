package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.ReviewRestaurant;

public interface IReviewRestaurantService {
	public void insert(ReviewRestaurant review);
	List<ReviewRestaurant> list();
	public void delete(int idReview);
	Optional<ReviewRestaurant> listId(int idReview);
	public void update(ReviewRestaurant review);
}
