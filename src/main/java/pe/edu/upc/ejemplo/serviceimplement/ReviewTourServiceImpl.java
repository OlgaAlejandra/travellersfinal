package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.ReviewTour;
import pe.edu.upc.ejemplo.repositories.IReviewTourRepository;
import pe.edu.upc.ejemplo.serviceinterface.IReviewTourService;

@Service
public class ReviewTourServiceImpl implements IReviewTourService{

	@Autowired
	private IReviewTourRepository reviewTourRepository;
	
	@Override
	public void insert(ReviewTour review) {
		// TODO Auto-generated method stub
		reviewTourRepository.save(review);
	}

	@Override
	public List<ReviewTour> list() {
		// TODO Auto-generated method stub
		return reviewTourRepository.findAll();
	}

	@Override
	public void delete(int idReview) {
		// TODO Auto-generated method stub
		reviewTourRepository.deleteById(idReview);	}

	@Override
	public Optional<ReviewTour> listId(int idReview) {
		// TODO Auto-generated method stub
		return reviewTourRepository.findById(idReview);
	}

	@Override
	public void update(ReviewTour review) {
		// TODO Auto-generated method stub
		reviewTourRepository.save(review);

	}

}
