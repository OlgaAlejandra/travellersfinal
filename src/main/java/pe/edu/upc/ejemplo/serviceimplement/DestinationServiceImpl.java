package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Destination;
import pe.edu.upc.ejemplo.repositories.IDestinationRepository;
import pe.edu.upc.ejemplo.serviceinterface.IDestinationService;

@Service
public class DestinationServiceImpl implements IDestinationService{

	@Autowired
	private IDestinationRepository destinationRepository;

	@Override
	public void insert(Destination destination) {
		destinationRepository.save(destination);
	}

	@Override
	public List<Destination> list() {
		return destinationRepository.findAll();
	}

	@Override
	public void delete(int idDestination) {
		destinationRepository.deleteById(idDestination);
	}

	@Override
	public Optional<Destination> listId(int idDestination) {
		return destinationRepository.findById(idDestination);
	}

	@Override
	public void update(Destination destination) {
		destinationRepository.save(destination);
	}




}
