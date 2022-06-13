package pe.edu.upc.ejemplo.serviceinterface;


import java.util.List;
import java.util.Optional;


import pe.edu.upc.ejemplo.entities.BudgeTrip;

public interface IBudgetripService {
	public void insert(BudgeTrip budgetrip);
	List<BudgeTrip> list();
	public void delete(int idBudgetrip);
	Optional<BudgeTrip> listId(int idBudgetrip);
	public void update(BudgeTrip accommodation);
}
