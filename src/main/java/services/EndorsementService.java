
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import security.Authority;
import domain.Actor;
import domain.Endorsement;
import domain.Endorser;

@Service
@Transactional
public class EndorsementService {

	@Autowired
	private EndorsementRepository	endorsementRepository;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private EndorserService			endorserService;


	public Endorsement create(final Actor a2) {
		Endorsement result;
		result = new Endorsement();
		final Date moment = new Date();
		final Actor a1 = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a1.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("CUSTOMER") || listAuth.contains("HANDYWORKER"));
		//	final Endorser e1 = this.endorserService.getPrincipal();
		final Collection<Authority> authorities2 = a2.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth2 = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities2)
				listAuth2.add(au.getAuthority());
		Assert.isTrue(listAuth2.contains("CUSTOMER") || listAuth.contains("HANDYWORKER"));
		//		final Endorser e2 = this.endorserService.getPrincipal();
		result.setMoment(moment);
		result.setWriteFrom((Endorser) a1);
		result.setWriteTo((Endorser) a2);
		return result;

	}

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

		final Actor a1 = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a1.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("CUSTOMER") || listAuth.contains("HANDYWORKER"));

		result = this.endorsementRepository.save(Endorsement);
		Assert.notNull(result);
		return result;

	}

	public void delete(final Endorsement Endorsement) {
		Assert.notNull(Endorsement);

		final Actor a1 = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a1.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("CUSTOMER") || listAuth.contains("HANDYWORKER"));

		this.endorsementRepository.delete(Endorsement);
	}

	public Collection<Endorsement> findByWriteFrom(final int endorserID) {

		return this.endorsementRepository.findByWriteFrom(endorserID);
	}

	public Collection<Endorsement> findByWriteTo(final int endorserID) {

		return this.endorsementRepository.findByWriteTo(endorserID);
	}

}
