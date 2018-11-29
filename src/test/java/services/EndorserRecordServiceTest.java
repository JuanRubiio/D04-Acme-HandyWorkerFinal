
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
import domain.EndorserRecord;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorserRecordServiceTest extends AbstractTest {

	@Autowired
	private EndorserRecordService	endorserRecordService;


	@Test
	public void findOneEndorserRecordTest() {
		final EndorserRecord aux = this.endorserRecordService.findOne(1302);
		Assert.isTrue(aux.getPhoneNumber().equals("123456789"));
	}
	@Test
	public void createEndorserRecordTest() {
		super.authenticate("handyWorker1");
		final EndorserRecord before = this.endorserRecordService.create();
		before.setName("name");
		before.setPhoneNumber("123456789");
		final Integer aux = this.endorserRecordService.save(before).getId();
		final EndorserRecord later = this.endorserRecordService.findOne(aux);
		Assert.isTrue(later.getName().equals("name"));
		Assert.isTrue(later.getPhoneNumber().equals("123456789"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteEndorserRecordTest() {

		final EndorserRecord before = this.endorserRecordService.findOne(279);
		this.endorserRecordService.delete(before);
		final EndorserRecord later = this.endorserRecordService.findOne(279);
	}
	@Test
	public void findAllEndorserRecordTest() {
		final Collection<EndorserRecord> endr = this.endorserRecordService.findAll();
		Assert.isTrue(!endr.isEmpty());
	}
}
