package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUpTask;

@Repository
public interface FixUpTaskRepository extends JpaRepository<FixUpTask,Integer>{

	//Puede que valga para generar el ticker, es del año pasado
	@Query("select f from FixUpTask where f.ticker = ?1")
	FixUpTask findByTicker(String ticker);
	
	//DashBoard
	//Enunciado: The average, the minimum, the maximum, and the standard deviation of the number of fix-up tasks per user.
	@Query(" select avg(c.fixUpTasks.size),min(c.fixUpTasks.size),max(c.fixUpTasks.size) from Customer c")
	Collection<FixUpTask> fixUpTaskPerUser();
	
	
}
