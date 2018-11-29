
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.PersonalRecord;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	@Autowired
	private CurriculumService			curriculumService;
	@Autowired
	private PersonalRecordService		personalRecordService;
	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;
	@Autowired
	private EndorserRecordService		endorserRecordService;
	@Autowired
	private ProfessionalRecordService	professionalRecordService;
	@Autowired
	private EducationalRecordService	educationalRecordService;


	//
	//	@Test
	//	public void createCurriculumTest() {
	//		this.authenticate("handyWorker1");
	//		final Curriculum curriculum = this.curriculumService.create();
	//		this.curriculumService.save(curriculum);
	//	}
	//		super.authenticate("handyWorker1");
	//		
	//		final PersonalRecord p1 = this.personalRecordService.create();
	//		final MiscellaneousRecord p2 = this.miscellaneousRecordService.create();
	//		final EndorserRecord p3 = this.endorserRecordService.create();
	//		final EducationalRecord p4 = this.educationalRecordService.create();
	//		final ProfessionalRecord p5 = this.professionalRecordService.create();
	//		
	//		final Collection<EducationalRecord> edr = new Collection<EducationalRecord>();
	//		final Collection<ProfessionalRecord> profr = this.professionalRecordService.create();
	//		final Collection<MiscellaneousRecord> misr = this.miscellaneousRecordService.create();
	//		final Collection<EndorserRecord> endr = this.endorserRecordService.create();
	//		
	//
	//		final Curriculum before = this.curriculumService.create();
	//		before.setPersonalRecord(p1);
	//		final Integer aux = this.curriculumService.save(before).getId();
	//		final Curriculum later = this.curriculumService.findOne(aux);
	//		Assert.isTrue(later.getPersonalRecord().equals(p1));
	//	}
	@Test(expected = IllegalArgumentException.class)
	public void deleteCurriculumTest() {
		final Curriculum curriculum = this.curriculumService.findOne(273);
		this.curriculumService.delete(curriculum);
		this.curriculumService.findOne(273);
	}
	@Test
	public void findAllPersonalRecordTest() {
		final Collection<PersonalRecord> pr = this.personalRecordService.findAll();
		Assert.isTrue(!pr.isEmpty());
	}
}
