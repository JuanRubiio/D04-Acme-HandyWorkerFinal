
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where (select count(f) from FixUpTask f where f.customer.id= c.id) >=  (select avg(1.10*(select count(fut) from FixUpTask fut where fut.customer.id=c.id )) from Customer c)")
	Collection<Customer> customerThatPublishedPercentageFUTAverage();
}
