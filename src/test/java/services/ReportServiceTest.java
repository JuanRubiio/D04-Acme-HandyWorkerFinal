
package services;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.ReportRepository;
import utilities.AbstractTest;

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

	//
	//	@Test
	//	public void testSaveReport() {
	//		super.authenticate("referee1");
	//		final Report report = this.reportService.findOne(1433);
	//		report.setDescription("esta es una nueva descrpcion");
	//		this.reportService.save(report);
	//		Assert.isTrue(this.reportService.findAll().contains(report));
	//	}
	//
	//	@Test(expected = IllegalArgumentException.class)
	//	public void testDeleteReport() {
	//
	//	}
}
