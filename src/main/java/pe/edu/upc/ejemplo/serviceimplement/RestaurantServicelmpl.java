package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.Restaurant;
import pe.edu.upc.ejemplo.repositories.IRestaurantRepository;
import pe.edu.upc.ejemplo.serviceinterface.IRestaurantService;
@Service
public class RestaurantServicelmpl  implements IRestaurantService {

	@Autowired
	private IRestaurantRepository restaurantRepository;
	@Override
	public void insert(Restaurant restaurant) {
		// TODO Auto-generated method stub
		restaurantRepository.save(restaurant);
	}

	@Override
	public List<Restaurant> list() {
		// TODO Auto-generated method stub
		return restaurantRepository.findAll();
	}

	@Override
	public void delete(int idRestaurant) {
		// TODO Auto-generated method stub
		restaurantRepository.deleteById(idRestaurant);
	}

	@Override
	public Optional<Restaurant> listId(int idRestaurant) {
		// TODO Auto-generated method stub
		return restaurantRepository.findById(idRestaurant);
	}

	@Override
	public void update(Restaurant restaurant) {
		// TODO Auto-generated method stub
		restaurantRepository.save(restaurant);
		
	}

}
