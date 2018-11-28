package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Integer>{
	
	//Enunciado: Seleccionar las SOLICITUDES para las TAREAS del CLIENTE
	//select app from Application app where app.fixUpTask.id =(select f from FixUpTask f where f.customer.id= parametroID);
	//Problemas encontrados: cuando un customer tiene 2 fixUpTask creadas, da error, ya que no consigue dar un conjunto de resultados. Si solo tiene una creada no hay problema.
	//Error: could not extract ResultSet.
	@Query("select app from Application app where app.fixUpTask.id =(select f from FixUpTask f where f.customer.id= ?1)")
	Collection<Application> findApplicationByFixUpTaskOfCustomer(int customId);
	
	//Enunciado: Seleccionar las SOLICITUDES del MANITAS
	//select app from Application app where app.HandyWorker.id= parametroID;
	@Query("select app from Application app where app.HandyWorker.id= ?1")
	Collection<Application> findApplicationByHandyWorker(int handyWorkerId);
	
	
	
	
	
	//Querys del dashboard(¿Hay que meterlos?En caso afirmativo, mirar el tema de los parametros)
	//Enunciado:The average, the minimum, the maximum, and the standard deviation of the number of applications per fix-up task.
	@Query("select avg(fut.applications.size),min(fut.applications.size),max(fut.applications.size) from FixUpTask fut")
	Collection<Application> numberApplicationByFixUpTask();
	
	//Enunciado: The average, the minimum, the maximum, and the standard deviation of the price offered in the applications.
	@Query("select min(a.price),avg(a.price), stddev(a.price),max(a.price) from Application a")
	Collection<Application> priceOfferedByApplication();
	
	//Enunciado: The ratio of pending applications.
	@Query("select sum(case when a.status='PENDING' then 1.00 else 0.00 end)/count(a)*1.0 from Application a")
	Collection<Application> pendingApplication();
	
	//Enunciado: The ratio of pending applications that cannot change its status because their time periods elapsed.
	@Query("select 1.0*count(a)/(select count(a) from Application a) from Application a join a.fixUpTask f where a.status = 'PENDING' and a.moment > f.maxDate")
	Collection<Application> applicationwithPeriodElapse();
	
	//Enunciado:The listing of handy workers who have got accepted at least 10% more applications than the average, ordered by number of applications.
	@Query("select hw from HandyWorker hw join hw.applications a where ((a.status='ACCEPTED')and (a.size>=(select 1.1*(sum(hw.applications.size)/count(hw))from HandyWorker hw))")
	Collection<Application> handyWorkerBetterAverage();
	
	//Enunciado: The ratio of accepted applications
	@Query("select sum(case when a.status='ACCEPTED' then 1.00 else 0.00 end)/count(a)*1.0 from Application a")
	Collection<Application> acceptedApplication();
	
	//Enunciado: The listing of customers who have published at least 10% more fix-up tasks than the average, ordered by number of applications.
	@Query("select c from Customer c join c.fixUpTasks fut where (c.fixUpTasks.size >= (select avg(cc.fixUpTasks.size) from Customer cc)*1.10) order by fut.applications.size")
	Collection<Application> customerBetterAverage();
	
	//Enunciado: The ratio of rejected applications.
	@Query("select sum(case when a.status='REJECTED' then 1.00 else 0.00 end)/count(a)*1.0 from Application a")
	Collection<Application> rejectedApplication();
	
	
}
