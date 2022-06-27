package pe.edu.upc.ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.Destination;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination, Integer>{

	@Query(value = "Select d.name, count(a.id_accommodation) from Destination d join Accommodation a on d.id_destination=a.id_destination group by d.name", nativeQuery = true)
	public List<String[]> destinationAmount();
	@Query(value = "Select d.name, count(r.id_restaurant) from Destination d inner join Restaurant r on d.id_destination=r.id_destination group by d.name", nativeQuery = true)
	public List<String[]> destinationAmountRes();
	@Query(value = "Select d.name, count(at.id_atraccion) from Destination d inner join Atraccion at on d.id_destination=at.id_destination group by d.name", nativeQuery = true)
	public List<String[]> destinationAmountAtr();
	@Query(value = "Select d.name, count(t.id_tour) from Destination d inner join Tour t on d.id_destination=t.id_destination group by d.name", nativeQuery = true)
	public List<String[]> destinationAmountTou();
}
