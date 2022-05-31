package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.AccommodationType;
import pe.edu.upc.ejemplo.repositories.IAccommodationTypeRepository;
import pe.edu.upc.ejemplo.serviceinterface.IAccommodationTypeService;

@Service
public class AccommodationTypeServiceImpl implements IAccommodationTypeService {

	@Autowired
	private IAccommodationTypeRepository accommodationTypeRepository;
	
	@Override
	public void insert(AccommodationType accommodationType) {
		accommodationTypeRepository.save(accommodationType);
	}

	@Override
	public List<AccommodationType> list() {
		return accommodationTypeRepository.findAll();
	}

	@Override
	public void delete(int idAccommodationType) {
		accommodationTypeRepository.deleteById(idAccommodationType);
	}

	@Override
	public Optional<AccommodationType> listId(int idAccommodationType) {
		return accommodationTypeRepository.findById(idAccommodationType);
	}

	@Override
	public void update(AccommodationType accommodationType) {
		accommodationTypeRepository.save(accommodationType);
	}

}
