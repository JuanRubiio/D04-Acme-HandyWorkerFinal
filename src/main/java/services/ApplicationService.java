
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import domain.Actor;
import domain.Application;
import domain.FixUpTask;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;
	@Autowired
	private ActorService			actorService;


	public Application create(final FixUpTask fixUpTask) {

		final Application res = new Application();
		Assert.notNull(fixUpTask);
		res.setFixUpTask(fixUpTask);
		final Actor actor = this.actorService.getPrincipal();
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(Authority.HANDYWORKER));
		res.setMoment(new Date());
		return res;
	}

	public Collection<Application> findAll() {
		Collection<Application> res;
		res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application findOne(final Integer applicationId) {
		Application res;
		Assert.notNull(applicationId);
		res = this.applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}

	public Application save(final Application application) {
		Application res;
		Assert.notNull(application);
		res = this.applicationRepository.save(application);
		Assert.notNull(res);
		return res;
	}

}
