package pe.edu.upc.ejemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.AccommodationType;

@Repository
public interface IAccommodationTypeRepository extends JpaRepository<AccommodationType, Integer>{

	
}
