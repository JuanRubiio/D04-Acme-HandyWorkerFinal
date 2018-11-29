
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
import domain.PersonalRecord;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PersonalRecordServiceTest extends AbstractTest {

	@Autowired
	private PersonalRecordService	personalRecordService;


	@Test
	public void createPersonalRecordTest() {
		super.authenticate("handyWorker1");
		final PersonalRecord before = this.personalRecordService.create();
		before.setName("name");
		before.setPhoneNumber("123456789");
		final Integer aux = this.personalRecordService.save(before).getId();
		final PersonalRecord later = this.personalRecordService.findOne(aux);
		Assert.isTrue(later.getName().equals("name"));
		Assert.isTrue(later.getPhoneNumber().equals("123456789"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deletePersonalRecordTest() {
		final PersonalRecord personalRecord = this.personalRecordService.findOne(273);
		this.personalRecordService.delete(personalRecord);
		this.personalRecordService.findOne(273);
	}
	@Test
	public void findAllPersonalRecordTest() {
		final Collection<PersonalRecord> pr = this.personalRecordService.findAll();
		Assert.isTrue(!pr.isEmpty());
	}
}
