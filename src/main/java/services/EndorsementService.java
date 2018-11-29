
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import domain.Endorsement;

@Service
@Transactional
public class EndorsementService {

	@Autowired
	private EndorsementRepository	endorsementRepository;


	//	public Endorsement create() {
	//		Endorsement result;
	//		result = new Endorsement();
	//		final Date moment = new Date();
	//		final Endorser from = (Endorser) this.actorService.getPrincipal();
	//		final Endorser to = (Endorser) this.actorService.getPrincipal();
	//		result.setMoment(moment);
	//		result.setWriteFrom(from);
	//	result.setWriteTo(to);
	//		return result;
	//
	//	}

	public Collection<Endorsement> findAll() {
		Collection<Endorsement> result;

		result = this.endorsementRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Endorsement findOne(final Integer EndorsementId) {
		Endorsement result;

		Assert.notNull(EndorsementId);

		result = this.endorsementRepository.findOne(EndorsementId);

		Assert.notNull(result);

		return result;

	}

	public Endorsement save(final Endorsement Endorsement) {
		Endorsement result;

		Assert.notNull(Endorsement);

		result = this.endorsementRepository.save(Endorsement);
		Assert.notNull(result);
		return result;

	}

	public void delete(final Endorsement Endorsement) {
		Assert.notNull(Endorsement);
		this.endorsementRepository.delete(Endorsement);
	}

	public Collection<Endorsement> findByCustomerId(final int customerID) {

		return this.endorsementRepository.findByCustomerId(customerID);
	}

	public Collection<Endorsement> findByHandyWorkerId(final int handyWorkerID) {

		return this.endorsementRepository.findByHandyWorkerId(handyWorkerID);
	}

}
