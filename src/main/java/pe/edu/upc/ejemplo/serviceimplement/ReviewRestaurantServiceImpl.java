package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.ReviewRestaurant;
import pe.edu.upc.ejemplo.repositories.IReviewRestaurantRepository;
import pe.edu.upc.ejemplo.serviceinterface.IReviewRestaurantService;

@Service
public class ReviewRestaurantServiceImpl implements IReviewRestaurantService{

	@Autowired
	private IReviewRestaurantRepository reviewRestaurantRepository;
	
	@Override
	public void insert(ReviewRestaurant review) {
		// TODO Auto-generated method stub
		reviewRestaurantRepository.save(review);
	}

	@Override
	public List<ReviewRestaurant> list() {
		// TODO Auto-generated method stub
		return reviewRestaurantRepository.findAll();
	}

	@Override
	public void delete(int idReview) {
		// TODO Auto-generated method stub
		reviewRestaurantRepository.deleteById(idReview);
	}

	@Override
	public Optional<ReviewRestaurant> listId(int idReview) {
		// TODO Auto-generated method stub
		return reviewRestaurantRepository.findById(idReview);
	}

	@Override
	public void update(ReviewRestaurant review) {
		// TODO Auto-generated method stub
		reviewRestaurantRepository.save(review);
	}

}
