package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Restaurant;

public interface IRestaurantService {
public void insert(Restaurant restaurant);
List<Restaurant> list();
public void delete(int idRestaurant);
Optional<Restaurant> listId(int idRestaurant);
public void update(Restaurant restaurant);
}
