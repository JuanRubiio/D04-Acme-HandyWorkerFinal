
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository		finderRepository;

	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;

	@Autowired
	private ActorService			actorService;


	public Finder create() {
		Finder res;
		res = new Finder();
		final Actor actor = this.actorService.getPrincipal();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("HANDYWORKER"));
		res.setFixUpTasks(new ArrayList<FixUpTask>());
		final Integer idActor = actor.getId();
		final HandyWorker handyWorker = this.handyWorkerRepository.findOne(idActor);
		res.setHandyWorker(handyWorker);
		return res;
	}

	public Finder save(final Finder finder) {
		Finder res;
		Assert.notNull(finder);
		res = this.finderRepository.save(finder);
		return res;
	}

	public Collection<FixUpTask> findFixUpTasks(final Finder f) {
		final String keyWord = f.getKeyWord();
		Double minPrice = f.getMinPrice();
		Double maxPrice = f.getMaxPrice();
		final Date minDate = f.getMinDate();
		final Date maxDate = f.getMaxDate();

		if (minPrice == null)
			minPrice = 0000.00;
		if (maxPrice == null)
			maxPrice = 9999.99;
		if (minDate == null) {
			final Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -30);
			minDate = c.getTime();
		}
		if (maxDate == null) {
			final Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, 30);
			maxDate = c.getTime();
		}

		if (keyWord == null)
			keyWord = "";

		return this.finderRepository.findFixUpTasks(keyWord, minPrice, maxPrice, minDate, maxDate);
	}

}
