
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorsement;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Integer> {

	@Query("select e from Endorsement e where e.writeFrom.id = ?1")
	Collection<Endorsement> findByWriteFrom(int endorserID);

	@Query("select e from Endorsement e where e.writeTo.id = ?1")
	Collection<Endorsement> findByWriteTo(int endorserID);

}
