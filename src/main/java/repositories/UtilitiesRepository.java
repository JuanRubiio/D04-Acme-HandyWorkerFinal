package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Spam;

@Repository
public interface UtilitiesRepository extends JpaRepository<Spam, Integer> {

	@Query("select cur.ticker from Curriculum cur")
	Collection<String> obtenerTickerCurriculum();

	@Query("select com.ticker from Complaint com")
	Collection<String> obtenerTickerComplaint();

	@Query("select fix.ticker from FixUpTask fix")
	Collection<String> obtenerTickerFixUpTask();

}