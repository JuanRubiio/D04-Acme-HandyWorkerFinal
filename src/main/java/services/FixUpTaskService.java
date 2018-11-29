
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.Authority;
import domain.Actor;
import domain.Customer;
import domain.FixUpTask;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;
	@Autowired
	private ActorService		actorService;

	@Autowired
	private UtilitiesService	utilitiesService;


	public FixUpTask create() {
		final FixUpTask res = new FixUpTask();
		res.setTicker(this.utilitiesService.generateTicker());

		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> autorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("CUSTOMER"));
		res.setCustomer((Customer) actor);

		res.setMoment(new Date());

		return res;
	}

	public Collection<FixUpTask> findAll() {
		Collection<FixUpTask> res;
		res = this.fixUpTaskRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public FixUpTask findOne(final Integer fixUpTaskId) {
		FixUpTask res;
		Assert.notNull(fixUpTaskId);
		res = this.fixUpTaskRepository.findOne(fixUpTaskId);
		Assert.notNull(res);
		return res;
	}

	public FixUpTask save(final FixUpTask fixUpTask) {
		FixUpTask res;
		Assert.notNull(fixUpTask);
		Assert.notNull(fixUpTask.getCustomer());
		Assert.notNull(fixUpTask.getMoment());
		res = this.fixUpTaskRepository.save(fixUpTask);
		Assert.notNull(res);
		return res;
	}

	public void delete(final FixUpTask fixUpTask) {
		Assert.notNull(fixUpTask);

		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> autorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("CUSTOMER"));

		this.fixUpTaskRepository.delete(fixUpTask);
	}

}
