package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.ReviewAtraccion;
import pe.edu.upc.ejemplo.repositories.IReviewAtraccionRepository;
import pe.edu.upc.ejemplo.serviceinterface.IReviewAtraccionService;

@Service
public class ReviewAtraccionServiceImpl implements IReviewAtraccionService{

	@Autowired
	private IReviewAtraccionRepository reviewAtraccionRepository;
	
	@Override
	public void insert(ReviewAtraccion review) {
		// TODO Auto-generated method stub
		reviewAtraccionRepository.save(review);
	}

	@Override
	public List<ReviewAtraccion> list() {
		// TODO Auto-generated method stub
		return reviewAtraccionRepository.findAll();
	}

	@Override
	public void delete(int idReview) {
		// TODO Auto-generated method stub
		reviewAtraccionRepository.deleteById(idReview);
	}

	@Override
	public Optional<ReviewAtraccion> listId(int idReview) {
		// TODO Auto-generated method stub
		return reviewAtraccionRepository.findById(idReview);
	}

	@Override
	public void update(ReviewAtraccion review) {
		// TODO Auto-generated method stub
		reviewAtraccionRepository.save(review);
	}
	@Override
	public List<String[]> ReviewAtraccionmayor() {
		// TODO Auto-generated method stub
		return reviewAtraccionRepository.ReviewAtraccionmayor();
	}
}
