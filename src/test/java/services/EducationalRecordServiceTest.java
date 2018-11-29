
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EducationalRecord;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationalRecordServiceTest extends AbstractTest {

	@Autowired
	private EducationalRecordService	educationalRecordService;


	@Test
	public void createEducationalRecordTest() {
		super.authenticate("handyWorker1");
		final Date begin = new Date(1980 / 01 / 01);
		final Date end = new Date(1980 / 01 / 01);

		final EducationalRecord before = this.educationalRecordService.create();
		before.setTitle("title1");
		before.setInstitution("institution1");
		before.setBegin(begin);
		before.setEnd(end);
		final Integer aux = this.educationalRecordService.save(before).getId();
		final EducationalRecord later = this.educationalRecordService.findOne(aux);
		Assert.isTrue(later.getTitle().equals("title1"));
		Assert.isTrue(later.getInstitution().equals("institution1"));
		Assert.isTrue(later.getBegin().equals(begin));
		Assert.isTrue(later.getEnd().equals(end));

	}
	@Test
	public void findAllEducationalRecordTest() {
		final Collection<EducationalRecord> edr = this.educationalRecordService.findAll();
		Assert.isTrue(!edr.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteEducationalRecordTest() {
		final EducationalRecord before = this.educationalRecordService.findOne(1298);
		this.educationalRecordService.delete(before);
		final EducationalRecord later = this.educationalRecordService.findOne(1298);
	}

	@Test
	public void saveEducationalRecordTest() {

		super.authenticate("handyworker1");

		final EducationalRecord edr = this.educationalRecordService.create();

		final EducationalRecord prueba = this.educationalRecordService.save(edr);

		final EducationalRecord aux = this.educationalRecordService.findOne(prueba.getId());
		Assert.notNull(aux);

		super.authenticate(null);
	}
}
