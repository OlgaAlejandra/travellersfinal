package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.ReviewTour;

public interface IReviewTourService {
	public void insert(ReviewTour review);
	List<ReviewTour> list();
	public void delete(int idReview);
	Optional<ReviewTour> listId(int idReview);
	public void update(ReviewTour review);
}
