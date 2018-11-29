
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import security.Authority;
import domain.Actor;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Tutorial;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private SponsorService			sponsorService;


	public Sponsorship create(final Tutorial t) {
		Sponsorship result;
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("SPONSOR"));
		result = new Sponsorship();
		final Sponsor s = this.sponsorService.getPrincipal();
		result.setSponsor(s);
		result.setTutorial(t);
		return result;
	}
	public Collection<Sponsorship> findByTutorialId(final Integer tutorialID) {

		Assert.notNull(tutorialID);
		return this.sponsorshipRepository.findByTutorialId(tutorialID);

	}

	public Collection<Sponsorship> findAll() {

		Collection<Sponsorship> res;

		res = this.sponsorshipRepository.findAll();

		Assert.notNull(res);

		return res;

	}

	public Sponsorship findOne(final Integer sponsorshipId) {
		Sponsorship result;

		Assert.notNull(sponsorshipId);

		result = this.sponsorshipRepository.findOne(sponsorshipId);

		Assert.notNull(result);

		return result;

	}

	public Sponsorship save(final Sponsorship sponsorship) {
		Sponsorship result;
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("SPONSOR"));
		Assert.notNull(sponsorship);
		result = this.sponsorshipRepository.save(sponsorship);

		return result;

	}

}
