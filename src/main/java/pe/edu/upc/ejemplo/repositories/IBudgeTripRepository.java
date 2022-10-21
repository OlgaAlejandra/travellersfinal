package pe.edu.upc.ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.BudgeTrip;

@Repository
public interface IBudgeTripRepository extends JpaRepository<BudgeTrip, Integer> {

	@Query(value = "Select b.num_budget, t.name from budgetrip b join typetrip t on t.id_type_trip = b.id_type_trip order by num_budget Desc limit 1",nativeQuery = true)
	public List<String[]> budgetAmount();
	
	@Query(value = "Select b.num_budget, t.name from budgetrip b join typetrip t on t.id_type_trip = b.id_type_trip order by num_budget Desc", nativeQuery = true)
	public List<String[]> budgetAmountDesc();

	
}
