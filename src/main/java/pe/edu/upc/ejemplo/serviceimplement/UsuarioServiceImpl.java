package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;

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
	
	
}
