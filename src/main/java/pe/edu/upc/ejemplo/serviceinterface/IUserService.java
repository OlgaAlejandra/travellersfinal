package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;

import pe.edu.upc.ejemplo.entities.Users;

public interface IUserService {

	public void insertar(Users u);
	List<Users> listar();
}
