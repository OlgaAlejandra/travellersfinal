package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Review;
import pe.edu.upc.ejemplo.repositories.IReviewRepository;
import pe.edu.upc.ejemplo.serviceinterface.IReviewService;

@Service
public class ReviewServiceImpl implements IReviewService{

	private IReviewRepository reviewRepository;
	
	@Override
	public void insert(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public List<Review> list() {
		return reviewRepository.findAll();
	}

	@Override
	public void delete(int idReview) {
		reviewRepository.deleteById(idReview);
	}

	@Override
	public Optional<Review> listId(int idReview) {
		return reviewRepository.findById(idReview);
	}

	@Override
	public void update(Review review) {
		reviewRepository.save(review);
	}

}
