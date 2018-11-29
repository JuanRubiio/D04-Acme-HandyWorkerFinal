
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
import domain.Endorser;

@Service
@Transactional
public class EndorserService {

	@Autowired
	private EndorserRepository	endorserRepository;


	public Collection<Endorser> findAll() {
		Collection<Endorser> result;

		result = this.endorserRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public Endorser findOne(final Integer EndorserId) {
		Endorser result;
		Assert.notNull(EndorserId);

		result = this.endorserRepository.findOne(EndorserId);

		Assert.notNull(result);
		return result;

	}

	public Endorser save(final Endorser endorser) {
		final Endorser result = endorser;
		Assert.notNull(endorser);

		this.endorserRepository.save(endorser);

		Assert.notNull(result);
		return result;

	}

	public void delete(final Endorser endorser) {
		Assert.notNull(endorser);
		this.endorserRepository.delete(endorser);

	}
}
