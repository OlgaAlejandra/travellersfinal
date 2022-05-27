package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Geolocalization;
import pe.edu.upc.ejemplo.repositories.IGeolocalizationRepository;
import pe.edu.upc.ejemplo.serviceinterface.IGeolocalizationService;

@Service
public class GeolocalizationServiceImpl implements IGeolocalizationService{

	@Autowired
	private IGeolocalizationRepository geolocalizationRepository;
	
	@Override
	public void insert(Geolocalization geolocalization) {
		geolocalizationRepository.save(geolocalization);
	}

	@Override
	public List<Geolocalization> list() {
		return geolocalizationRepository.findAll();
	}

	@Override
	public void delete(int idGeolocalization) {
		geolocalizationRepository.deleteById(idGeolocalization);
	}

	@Override
	public Optional<Geolocalization> listId(int idGeolocalization) {
		return geolocalizationRepository.findById(idGeolocalization);
	}

	@Override
	public void update(Geolocalization geolocalization) {
		geolocalizationRepository.save(geolocalization);
	}

	
}
