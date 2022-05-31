package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.AccommodationType;

public interface IAccommodationTypeService {

	public void insert(AccommodationType accommodationType);
	List<AccommodationType> list();
	public void delete(int idAccommodationType);
	Optional<AccommodationType> listId(int idAccommodationType);
	public void update(AccommodationType accommodationType);
}
