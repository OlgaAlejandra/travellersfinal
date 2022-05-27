package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Geolocalization;

public interface IGeolocalizationService {

	public void insert(Geolocalization geolocalization);
	List<Geolocalization> list();
	public void delete(int idGeolocalization);
	Optional<Geolocalization> listId(int idGeolocalization);
	public void update(Geolocalization geolocalization);
}
