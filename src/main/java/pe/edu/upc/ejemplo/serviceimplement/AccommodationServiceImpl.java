package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Accommodation;
import pe.edu.upc.ejemplo.repositories.IAccommodationRepository;
import pe.edu.upc.ejemplo.serviceinterface.IAccommodationService;

@Service
public class AccommodationServiceImpl implements IAccommodationService{

	@Autowired
	private IAccommodationRepository accommodationRepository;

	@Override
	public void insert(Accommodation accommodation) {
		accommodationRepository.save(accommodation);
	}

	@Override
	public List<Accommodation> list() {
		return accommodationRepository.findAll();
	}

	@Override
	public void delete(int idAccommodation) {
		accommodationRepository.deleteById(idAccommodation);
	}

	@Override
	public Optional<Accommodation> listId(int idAccommodation) {
		return accommodationRepository.findById(idAccommodation);
	}

	@Override
	public void update(Accommodation accommodation) {
		accommodationRepository.save(accommodation);
	}

	@Override
	public List<Accommodation> findByName(String name) {
		// TODO Auto-generated method stub
		return accommodationRepository.findByName(name);
	}

	@Override
	public List<Accommodation> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return accommodationRepository.findByNameLikeIgnoreCase(name);
	}
	
	
}
