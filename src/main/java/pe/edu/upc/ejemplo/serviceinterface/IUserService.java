package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Users;

public interface IUserService {

	public void insertar(Users u);
	List<Users> listar();
	public void delete(Long idU);
	Optional<Users> listId(Long idU);
	public void update(Users u);
}
