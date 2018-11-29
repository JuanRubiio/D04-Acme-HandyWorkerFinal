<<<<<<< HEAD
=======

>>>>>>> dev_jose
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUpTask;

@Repository
<<<<<<< HEAD
public interface FixUpTaskRepository extends JpaRepository<FixUpTask,Integer>{
=======
public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {
>>>>>>> dev_jose

	//Puede que valga para generar el ticker, es del año pasado
	//select f from FixUpTask f where f.ticker = '181120-ABCD';
	@Query("select f from FixUpTask f where f.ticker = ?1")
	FixUpTask findByTicker(String ticker);
<<<<<<< HEAD
	
	//Enunciado: Filtrar las TAREAS por los ESTADOS de un MANITAS
	//select f from FixUpTask f join f.applications a where a.id=(select a.id from Application a where (a.handyWorker=1308 AND a.status='ACCEPTED'));
	@Query("select f from FixUpTask f join f.applications a where a.id=(select a.id from Application a where (a.handyWorker= ?1 AND a.status='?2'))")
	Collection<FixUpTask> ListingFixUpTaskByHandyWorker(int handyWorkerId,String status);
=======

	//Enunciado: Filtrar las TAREAS por los ESTADOS de un MANITAS
	//select f from FixUpTask f join f.applications a where a.id=(select a.id from Application a where (a.handyWorker=1308 AND a.status='ACCEPTED'));
	@Query("select f from FixUpTask f join f.applications a where a.id=(select a.id from Application a where (a.handyWorker= ?1 AND a.status='?2'))")
	Collection<FixUpTask> ListingFixUpTaskByHandyWorker(int handyWorkerId, String status);
>>>>>>> dev_jose

}
