
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import utilities.AbstractTest;
import domain.Complaint;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ReportServiceTest extends AbstractTest {

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ReportService		reportService;

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ComplaintService	complaintService;


	@Test
	public void testSaveReport() {
		super.authenticate("referee1");
		final Complaint c = this.complaintService.create();
		final Report report = this.reportService.create(c);
		final Report prueba = this.reportService.save(report);
		final Report reportt = this.reportRepository.findOne(prueba.getId());
		Assert.notNull(reportt);
		super.authenticate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteReport() {
		super.authenticate("referee1");
		final Report report = this.reportRepository.findOne(1400);

		this.reportService.delete(report);
		this.reportRepository.findOne(1400);
		super.authenticate(null);
	}
}
