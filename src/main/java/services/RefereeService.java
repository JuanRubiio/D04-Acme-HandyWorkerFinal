
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MessageBoxService	messageboxService;


	public Referee create() {
		Referee res;
		res = new Referee();
		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.REFEREE);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		res.setUserAccount(userAccount);

		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authoritiesa = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authoritiesa)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("ADMIN"));
		Assert.notNull(res);
		//this.messageboxService.addDefaultMessageBoxs(res);
		return res;
	}

	public Referee save(final Referee referee) {
		Referee res;
		Assert.notNull(referee);
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("ADMIN"));

		res = this.refereeRepository.save(referee);
		Assert.notNull(res);
		return res;

	}

	public Collection<Referee> findAll() {
		Collection<Referee> res;

		res = this.refereeRepository.findAll();

		Assert.notNull(res);

		return res;
	}

	public Referee findOne(final Integer refereeId) {
		Referee res;

		Assert.notNull(refereeId);

		res = this.refereeRepository.findOne(refereeId);

		Assert.notNull(res);
		return res;
	}
}
