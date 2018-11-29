
package services;

import java.sql.Date;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest {

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	@Test
	public void createProfessionalRecordTest() {
		final Date begin = new Date(1980 / 01 / 01);
		final Date end = new Date(1980 / 01 / 01);
		super.authenticate("handyWorker1");
		final ProfessionalRecord before = this.professionalRecordService.create();
		before.setCompanyName("company");
		before.setRole("role");
		before.setBegin(begin);
		before.setBegin(end);
		final Integer aux = this.professionalRecordService.save(before).getId();
		final ProfessionalRecord later = this.professionalRecordService.findOne(aux);
		Assert.isTrue(later.getCompanyName().equals("company"));
		Assert.isTrue(later.getRole().equals("role"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteProfessionalRecordTest() {

		final ProfessionalRecord before = this.professionalRecordService.findOne(279);
		this.professionalRecordService.delete(before);
		final ProfessionalRecord later = this.professionalRecordService.findOne(279);
	}
	@Test
	public void findAllProfessionalRecordTest() {
		final Collection<ProfessionalRecord> profr = this.professionalRecordService.findAll();
		Assert.isTrue(!profr.isEmpty());
	}

}
