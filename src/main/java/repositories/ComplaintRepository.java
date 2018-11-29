
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select f.complaints from FixUpTask f where f.customer.id=?1")
	Collection<Complaint> getListOfComplaintsPerCustomer(Integer id);

	/*
	 * @Query("select f.applications from FixUpTask f")
	 * Collection<Complaint> getListOfComplaintsPerHandyWorker(Integer id);
	 */

}
