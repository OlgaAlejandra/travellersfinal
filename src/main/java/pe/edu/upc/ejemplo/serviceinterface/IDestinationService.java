package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Destination;

public interface IDestinationService {

	public void insert(Destination destination);
	List<Destination> list();
	public void delete(int idDestination);
	Optional<Destination> listId(int idDestination);
	public void update(Destination destination);
}
