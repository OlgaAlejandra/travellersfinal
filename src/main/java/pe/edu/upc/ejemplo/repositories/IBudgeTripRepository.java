package pe.edu.upc.ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.BudgeTrip;

@Repository
public interface IBudgeTripRepository extends JpaRepository<BudgeTrip, Integer> {

	@Query(value = "Select budgetrip.num_budget, budgetrip.id_type_trip  from budgetrip order by num_budget ASC",nativeQuery = true)
	public List<String[]> budgetAmount();
	
}
