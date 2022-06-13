package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Tour;

public interface ITourService {

	public void insert(Tour tour);
	List<Tour> list();
	public void delete(int idTour);
	Optional<Tour> listId(int idTour);
	public void update(Tour tour);
}
