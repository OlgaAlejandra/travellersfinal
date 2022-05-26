package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Usuario;

public interface IUsuarioService {

	public void insert(Usuario usuario);
	List<Usuario> list();
	public void delete(int idUsuario);
	Optional<Usuario> listId(int idUsuario);
	public void update(Usuario usuario);
}
