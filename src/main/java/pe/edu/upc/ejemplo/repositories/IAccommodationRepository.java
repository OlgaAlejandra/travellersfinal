package pe.edu.upc.ejemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.Accommodation;

@Repository
public interface IAccommodationRepository extends JpaRepository<Accommodation, Integer>{

}
