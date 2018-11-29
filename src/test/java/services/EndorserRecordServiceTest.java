package services;

import domain.EndorserRecord;
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
public class EndorserRecordServiceTest extends AbstractTest {

	@Autowired
	private EndorserRecordService endorserRecordService;

	@Test
	public void createEndorserRecordTest() {

		EndorserRecord before = endorserRecordService.create();
		before.setName("titulo2");
		EndorserRecord later = endorserRecordService
				.findOne(endorserRecordService.save(before).getId());
		Assert.isTrue(later.getName().equals("titulo2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteEndorserRecordTest() {

		EndorserRecord before = endorserRecordService.findOne(279);
		endorserRecordService.delete(before);
		EndorserRecord later = endorserRecordService.findOne(279);
	}
}

