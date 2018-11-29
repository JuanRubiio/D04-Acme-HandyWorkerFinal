
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import security.Authority;
import domain.Actor;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	@Autowired
	private PersonalRecordRepository	personalRecordRepository;

	@Autowired
	private ActorService				actorService;


	public PersonalRecordService() {
		super();
	}

	public PersonalRecord create() {
		PersonalRecord res;

		res = new PersonalRecord();

		return res;

	}

	public Collection<PersonalRecord> findAll() {
		Collection<PersonalRecord> result;

		Assert.notNull(this.personalRecordRepository);

		result = this.personalRecordRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public PersonalRecord save(final PersonalRecord personalRecord) {
		PersonalRecord result;

		Assert.notNull(personalRecord);
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));

		result = this.personalRecordRepository.save(personalRecord);

		Assert.notNull(result);
		return result;

	}

	public void delete(final PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> autorities = a.getUserAccount().getAuthorities();

		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));
		this.personalRecordRepository.delete(personalRecord);
	}

	public PersonalRecord findOne(final Integer personalRecordId) {
		PersonalRecord result;

		Assert.notNull(personalRecordId);

		result = this.personalRecordRepository.findOne(personalRecordId);

		Assert.notNull(result);

		return result;

	}

}
