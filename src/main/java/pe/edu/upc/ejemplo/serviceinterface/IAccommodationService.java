package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Accommodation;

public interface IAccommodationService {

	public void insert(Accommodation accommodation);
	List<Accommodation> list();
	public void delete(int idAccommodation);
	Optional<Accommodation> listId(int idAccommodation);
	public void update(Accommodation accommodation);
	List<Accommodation> findByName(String name);

	List<Accommodation> findByNameLikeIgnoreCase(String name);
}
