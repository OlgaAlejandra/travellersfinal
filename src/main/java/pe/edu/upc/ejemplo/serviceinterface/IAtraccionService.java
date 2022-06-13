package pe.edu.upc.ejemplo.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.ejemplo.entities.Accommodation;
import pe.edu.upc.ejemplo.entities.Atraccion;

public interface IAtraccionService {
public void insert(Atraccion atraccion);
List<Atraccion> list();
public void delete(int idAtraccion);
Optional<Atraccion> listId(int idAtraccion);
public void update(Atraccion atraccion);
}
