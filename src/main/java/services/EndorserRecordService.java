
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import security.Authority;
import domain.Actor;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;

	@Autowired
	private ActorService				actorService;


	public EndorserRecordService() {
		super();
	}

	public EndorserRecord create() {
		EndorserRecord result;

		result = new EndorserRecord();

		return result;
	}

	public Collection<EndorserRecord> findAll() {
		Collection<EndorserRecord> result;

		result = this.endorserRecordRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public EndorserRecord findOne(final Integer endorserRecordId) {

		EndorserRecord result;

		Assert.notNull(endorserRecordId);

		result = this.endorserRecordRepository.findOne(endorserRecordId);

		Assert.notNull(result);

		return result;

	}

	public EndorserRecord save(final EndorserRecord endorserRecord) {
		EndorserRecord result;

		Assert.notNull(endorserRecord);
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> autorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));

		result = this.endorserRecordRepository.save(endorserRecord);

		Assert.notNull(result);

		return result;
	}

	public void delete(final EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);

		this.endorserRecordRepository.delete(endorserRecord);
	}

}
