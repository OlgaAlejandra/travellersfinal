package pe.edu.upc.ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.ejemplo.entities.ReviewAtraccion;

@Repository
public interface IReviewAtraccionRepository extends JpaRepository<ReviewAtraccion, Integer>{
	@Query(value = "SELECT * FROM review_atraccion\r\n"
			+ "WHERE num_rating = (SELECT MAX(num_rating) FROM review_atraccion)\r\n"
			+ "ORDER BY  id_review ASC",nativeQuery = true)
	public List<String[]> ReviewAtraccionmayor();
}
