
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
import domain.MiscellaneousRecord;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscellaneousRecordServiceTest extends AbstractTest {

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	@Test
	public void findOneMiscellaneousRecordTest() {
		final MiscellaneousRecord aux = this.miscellaneousRecordService.findOne(1304);
		Assert.isTrue(aux.getTitle().equals("title"));
	}
	@Test
	public void createMiscellaneousRecordTest() {
		super.authenticate("handyWorker1");
		final MiscellaneousRecord before = this.miscellaneousRecordService.create();
		before.setTitle("title");
		final Integer aux = this.miscellaneousRecordService.save(before).getId();
		final MiscellaneousRecord later = this.miscellaneousRecordService.findOne(aux);
		Assert.isTrue(later.getTitle().equals("title"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMiscellaneousRecordTest() {
		final MiscellaneousRecord record = this.miscellaneousRecordService.findOne(1304);//281
		this.miscellaneousRecordService.delete(record);
		this.miscellaneousRecordService.findOne(1304);
	}
	@Test
	public void findAllMiscellaneousRecordTest() {
		final Collection<MiscellaneousRecord> edr = this.miscellaneousRecordService.findAll();
		Assert.isTrue(!edr.isEmpty());
	}
}
