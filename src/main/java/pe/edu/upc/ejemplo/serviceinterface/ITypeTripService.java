package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.TypeTrip;

public interface ITypeTripService {

	public void insert(TypeTrip typetrip);
	List<TypeTrip> list();
	public void delete(int idTypeTrip);
	Optional<TypeTrip> listId(int idTypeTrip);
	public void update(TypeTrip typeTrip);
}
