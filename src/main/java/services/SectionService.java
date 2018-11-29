
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {

	//Managed repo
	@Autowired
	private SectionRepository	sectionRepository;

	@Autowired
	private TutorialService		tutorialService;


	//Supporting services
	public Section create(final Integer tutorialId) {
		final Section res;
		res = new Section();
		res.setOrden(this.numberOfSection(this.tutorialService.findOne(tutorialId)));

		return res;

	}

	public Section findOne(final Integer sectionId) {
		final Section res;
		Assert.notNull(sectionId);
		res = this.sectionRepository.findOne(sectionId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Section> findAll() {
		final Collection<Section> res;
		res = this.sectionRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Section save(final Section section) {
		final Section res;
		Assert.notNull(section);
		Assert.isTrue(section.getTitle() != "");
		Assert.isTrue(section.getText() != "");
		Assert.notNull(section.getOrden());
		res = this.sectionRepository.save(section);
		Assert.notNull(res);

		return res;
	}

	public void delete(final Section section) {
		Assert.notNull(section);
		this.sectionRepository.delete(section);
	}

	private Integer numberOfSection(final Tutorial tutorial) {
		final Integer numeroActualSeccion = tutorial.getSections().size() + 1;
		return numeroActualSeccion;
	}
}
