package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Usuario;
import pe.edu.upc.ejemplo.repositories.IUsuarioRepository;
import pe.edu.upc.ejemplo.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public void insert(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> list() {
		return usuarioRepository.findAll();
	}

	@Override
	public void delete(int idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}

	@Override
	public Optional<Usuario> listId(int idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(idUsuario);
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.save(usuario);
	}
	
	
}
