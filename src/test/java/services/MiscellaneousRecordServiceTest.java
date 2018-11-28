package services;

import domain.MiscellaneousRecord;
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
public class MiscellaneousRecordServiceTest extends AbstractTest {

	@Autowired
	private MiscellaneousRecordService miscellaneousRecordService;

	@Test
	public void createMiscellaneousTest() {
		MiscellaneousRecord record = miscellaneousRecordService.create();
		miscellaneousRecordService.save(record);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMiscellaneousTest() {
		MiscellaneousRecord record = miscellaneousRecordService.findOne(281);
		miscellaneousRecordService.delete(record);
		miscellaneousRecordService.findOne(281);
	}
}
