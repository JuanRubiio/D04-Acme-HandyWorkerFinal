package services;

import java.util.Collection;

import domain.Category;
import domain.EducationalRecord;
import domain.SocialProfile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import javax.transaction.Transactional;

//NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class EducationalRecordServiceTest extends AbstractTest {

	@Autowired
	private EducationalRecordService educationalRecordService;

	@Test
	public void createEducationalRecordTest() {
		EducationalRecord before = educationalRecordService.create();
		before.setTitle("title1");
		EducationalRecord later = educationalRecordService.findOne(educationalRecordService.save(before).getId());
		Assert.isTrue(later.getTitle().equals("title1"));
	}

	@Test
	public void findAllEducationalRecordTest() {
		final Collection<EducationalRecord> edr = this.educationalRecordService.findAll();
		Assert.isTrue(!edr.isEmpty());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deleteEducationalRecordTest() {
		EducationalRecord before = educationalRecordService.findOne(2740);
		educationalRecordService.delete(before);
		EducationalRecord later = educationalRecordService.findOne(274);
	}
	
	@Test
	public void saveEducationalRecordTest() {

		super.authenticate("handyworker1");
		final EducationalRecord edr = this.educationalRecordService.create();

		final EducationalRecord prueba = this.educationalRecordService.save(edr);

		final EducationalRecord ed = this.educationalRecordService.findOne(prueba.getId());
		Assert.notNull(ed);

		super.authenticate(null);
	}
}

