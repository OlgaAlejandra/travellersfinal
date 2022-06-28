package pe.edu.upc.ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.Accommodation;

@Repository
public interface IAccommodationRepository extends JpaRepository<Accommodation, Integer>{

	@Query("Select a from Accommodation a where a.name like %:name%")
	List<Accommodation> findByName(String name);

	List<Accommodation> findByNameLikeIgnoreCase(String name);

}
