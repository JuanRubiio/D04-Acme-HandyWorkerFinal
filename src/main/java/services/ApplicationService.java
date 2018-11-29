
package services;

import java.util.ArrayList;
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
import domain.CreditCard;
import domain.FixUpTask;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;
	@Autowired
	private ActorService			actorService;


	//¿Debería poner FixUpTaskService?

	public Application create(final FixUpTask fixUpTask) {

		final Application res = new Application();
		Assert.notNull(fixUpTask);
		res.setFixUpTask(fixUpTask);
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> autorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("HANDYWORKER"));
		res.setMoment(new Date());
		//¿Hace falta meter una CreditCard?, ya que es un atributo opcional
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

	public Boolean checkAccepted(final Integer appId, final CreditCard creditc) {
		Boolean res = false;

		final Application ap = this.findOne(appId);
		Assert.notNull(ap);
		if (ap.getStatus().equals("ACCEPTED")) {
			ap.setCreditCard(creditc);
			this.save(ap);
			res = true;
		}
		return res;

	}

}
