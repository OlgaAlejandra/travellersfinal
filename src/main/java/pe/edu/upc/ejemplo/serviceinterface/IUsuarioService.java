package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;

import pe.edu.upc.ejemplo.entities.Usuario;

public interface IUsuarioService {

	public void insert(Usuario usuario);
	List<Usuario> list();
}
