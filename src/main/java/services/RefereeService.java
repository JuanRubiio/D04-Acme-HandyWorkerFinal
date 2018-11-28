
package services;

import java.util.ArrayList;
import java.util.Collection;

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
		final Actor actor = this.actorService.getPrincipal();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("ADMIN"));
		Assert.notNull(res);
		res.setUserAccount(userAccount);
		this.messageboxService.addDefaultMessageBoxs(res);
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
}
