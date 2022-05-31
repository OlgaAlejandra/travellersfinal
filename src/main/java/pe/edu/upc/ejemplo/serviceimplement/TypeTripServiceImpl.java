package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.TypeTrip;
import pe.edu.upc.ejemplo.repositories.ITypeTripRepository;
import pe.edu.upc.ejemplo.serviceinterface.ITypeTripService;

@Service
public class TypeTripServiceImpl implements ITypeTripService{

	@Autowired
	private ITypeTripRepository typeTripRepository;
	
	@Override
	public void insert(TypeTrip typetrip) {
		typeTripRepository.save(typetrip);
	}

	@Override
	public List<TypeTrip> list() {
		return typeTripRepository.findAll();
	}

	@Override
	public void delete(int idTypeTrip) {
		typeTripRepository.deleteById(idTypeTrip);
	}

	@Override
	public Optional<TypeTrip> listId(int idTypeTrip) {
		return typeTripRepository.findById(idTypeTrip);
	}

	@Override
	public void update(TypeTrip typeTrip) {
		typeTripRepository.save(typeTrip);
	}

	
}
