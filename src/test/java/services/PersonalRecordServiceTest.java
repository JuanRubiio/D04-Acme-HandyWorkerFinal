package services;

import domain.PersonalRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;

//NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PersonalRecordServiceTest extends AbstractTest {

	@Autowired
	private PersonalRecordService personalRecordService;

	@Test
	public void createPersonalRecordTest() {
		PersonalRecord personalRecord = personalRecordService.create();
		personalRecordService.save(personalRecord);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deletePersonalRecordTest() {
		PersonalRecord personalRecord = personalRecordService.findOne(273);
		personalRecordService.delete(personalRecord);
		personalRecordService.findOne(273);
	}
}
