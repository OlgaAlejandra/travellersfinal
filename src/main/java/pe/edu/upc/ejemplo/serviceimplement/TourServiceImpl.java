package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Tour;
import pe.edu.upc.ejemplo.repositories.ITourRepository;
import pe.edu.upc.ejemplo.serviceinterface.ITourService;

@Service
public class TourServiceImpl implements ITourService{

	@Autowired
	private ITourRepository tourRepository;
	
	@Override
	public void insert(Tour tour) {
		tourRepository.save(tour);
	}

	@Override
	public List<Tour> list() {
		return tourRepository.findAll();
	}

	@Override
	public void delete(int idTour) {
		tourRepository.deleteById(idTour);
	}

	@Override
	public Optional<Tour> listId(int idTour) {
		return tourRepository.findById(idTour);
	}

	@Override
	public void update(Tour tour) {
		tourRepository.save(tour);
	}

	
}
