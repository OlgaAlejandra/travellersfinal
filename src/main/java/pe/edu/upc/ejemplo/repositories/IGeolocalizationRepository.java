package pe.edu.upc.ejemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.Geolocalization;

@Repository
public interface IGeolocalizationRepository extends JpaRepository<Geolocalization, Integer>{

}
