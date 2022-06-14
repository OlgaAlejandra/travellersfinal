package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Users;
import pe.edu.upc.ejemplo.repositories.UserRepository;
import pe.edu.upc.ejemplo.serviceinterface.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository tR;
	
	@Override
	public void insertar(Users u) {
		// TODO Auto-generated method stub
		tR.save(u);
	}

	@Override
	public List<Users> listar() {
		// TODO Auto-generated method stub
		return tR.findAll();
	}

	
}
