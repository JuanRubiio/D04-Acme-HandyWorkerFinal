
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.HandyWorker;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	//Managed repo
	@Autowired
	private TutorialRepository	tutorialRepository;
	@Autowired
	private ActorService		actorService;


	//Supporting services
	public Tutorial create(final HandyWorker handyWorker) {
		final Tutorial res = new Tutorial();
		Assert.isTrue(this.actorService.getPrincipal().getUserAccount().getAuthorities().contains("HANDYWORKER"));
		Assert.notNull(handyWorker);
		res.setHandyWorker(handyWorker);
		res.setMomentCreate(new Date());
		return res;
	}

	public Tutorial findOne(final Integer tutorialId) {
		final Tutorial res;
		Assert.notNull(tutorialId);
		res = this.tutorialRepository.findOne(tutorialId);
		Assert.notNull(res);

		return res;

	}

	public Collection<Tutorial> findAll() {
		final Collection<Tutorial> res;
		res = this.tutorialRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Tutorial save(final Tutorial tutorial) {
		final Tutorial res;
		Assert.notNull(tutorial);
		res = this.tutorialRepository.save(tutorial);
		res.setMomentUpdate(new Date());
		Assert.notNull(res);

		return res;
	}

	public void delete(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		this.tutorialRepository.delete(tutorial);

	}

}
