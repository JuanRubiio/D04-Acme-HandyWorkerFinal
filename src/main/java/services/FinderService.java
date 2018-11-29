
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
import security.Authority;
import domain.Actor;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Finder create() {
		Finder res;
		res = new Finder();
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("HANDYWORKER"));
		res.setFixUpTasks(new ArrayList<FixUpTask>());
		final Integer idActor = actor.getId();
		final HandyWorker handyWorker = this.handyWorkerService.findOne(idActor);
		res.setHandyWorker(handyWorker);
		return res;
	}

	public Finder save(final Finder finder) {
		Finder res;
		Assert.notNull(finder);
		res = this.finderRepository.save(finder);
		return res;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> res;

		res = this.finderRepository.findAll();

		Assert.notNull(res);

		return res;
	}

	public Finder findOne(final Integer finderId) {
		Finder res;

		Assert.notNull(finderId);

		res = this.finderRepository.findOne(finderId);

		Assert.notNull(res);
		return res;
	}

	public Collection<FixUpTask> findFixUpTasks(final Finder f) {
		String keyWord = f.getKeyWord();
		Double minPrice = f.getMinPrice();
		Double maxPrice = f.getMaxPrice();
		Date minDate = f.getMinDate();
		Date maxDate = f.getMaxDate();

		if (minPrice == null)
			minPrice = Double.MIN_VALUE;
		if (maxPrice == null)
			maxPrice = Double.MAX_VALUE;
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
