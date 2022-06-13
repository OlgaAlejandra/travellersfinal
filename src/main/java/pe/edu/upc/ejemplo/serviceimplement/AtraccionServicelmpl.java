package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Atraccion;
import pe.edu.upc.ejemplo.repositories.IAtraccionRepository;
import pe.edu.upc.ejemplo.serviceinterface.IAtraccionService;

@Service
public class AtraccionServicelmpl implements IAtraccionService {
	
	@Autowired
	private IAtraccionRepository atraccionrepository;
	
	@Override
	public void insert(Atraccion atraccion) {
		// TODO Auto-generated method stub
		atraccionrepository.save(atraccion);
	}

	@Override
	public List<Atraccion> list() {
		// TODO Auto-generated method stub
		return atraccionrepository.findAll();
	}

	@Override
	public void delete(int idAtraccion) {
		// TODO Auto-generated method stub
		atraccionrepository.deleteById(idAtraccion);
	}

	@Override
	public Optional<Atraccion> listId(int idAtraccion) {
		// TODO Auto-generated method stub
		return atraccionrepository.findById(idAtraccion);
	}

	@Override
	public void update(Atraccion atraccion) {
		// TODO Auto-generated method stub
		atraccionrepository.save(atraccion);
	}

}
