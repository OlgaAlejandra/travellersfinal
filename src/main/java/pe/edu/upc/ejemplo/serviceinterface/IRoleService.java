package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;

import pe.edu.upc.ejemplo.entities.Role;

public interface IRoleService {

	public void insertar(Role r);
	List<Role> listar();
}
