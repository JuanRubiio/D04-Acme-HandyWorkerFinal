
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EducationalRecordRepository;
import security.Authority;
import domain.Actor;
import domain.EducationalRecord;

@Service
@Transactional
public class EducationalRecordService {

	@Autowired
	private EducationalRecordRepository	educationalRecordRepository;

	@Autowired
	private ActorService				actorService;


	public EducationalRecordService() {
		super();
	}

	public EducationalRecord create() {
		EducationalRecord result;

		result = new EducationalRecord();

		return result;
	}

	public Collection<EducationalRecord> findAll() {
		Collection<EducationalRecord> result;

		Assert.notNull(this.educationalRecordRepository);

		result = this.educationalRecordRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public EducationalRecord findOne(final Integer educationalRecordId) {
		EducationalRecord result;

		Assert.notNull(educationalRecordId);

		result = this.educationalRecordRepository.findOne(educationalRecordId);

		Assert.notNull(result);

		return result;
	}

	public EducationalRecord save(final EducationalRecord educationalRecord) {
		EducationalRecord result;

		Assert.notNull(educationalRecord);

		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));

		result = this.educationalRecordRepository.save(educationalRecord);

		Assert.notNull(result);

		return result;
	}

	public void delete(final EducationalRecord educationalRecord) {
		Assert.notNull(educationalRecord);

		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> autorities = a.getUserAccount().getAuthorities();

		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));

		this.educationalRecordRepository.delete(educationalRecord);
	}
}
