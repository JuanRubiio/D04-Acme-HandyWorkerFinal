
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import security.Authority;
import domain.Actor;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRecordRepository;

	@Autowired
	private ActorService					actorService;


	public MiscellaneousRecordService() {
		super();
	}

	public MiscellaneousRecord create() {
		MiscellaneousRecord res;

		res = new MiscellaneousRecord();

		return res;
	}

	public Collection<MiscellaneousRecord> findAll() {
		Collection<MiscellaneousRecord> result;

		Assert.notNull(this.miscellaneousRecordRepository);

		result = this.miscellaneousRecordRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public MiscellaneousRecord findOne(final Integer miscellaneousRecordId) {

		MiscellaneousRecord result;

		Assert.notNull(miscellaneousRecordId);

		result = this.miscellaneousRecordRepository.findOne(miscellaneousRecordId);

		Assert.notNull(result);

		return result;

	}
	public MiscellaneousRecord save(final MiscellaneousRecord miscellaneousRecord) {
		MiscellaneousRecord result;

		Assert.notNull(miscellaneousRecord);
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));

		result = this.miscellaneousRecordRepository.save(miscellaneousRecord);

		Assert.notNull(result);

		return result;
	}

	public void delete(final MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> autorities = a.getUserAccount().getAuthorities();

		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));

		this.miscellaneousRecordRepository.delete(miscellaneousRecord);

	}
}
