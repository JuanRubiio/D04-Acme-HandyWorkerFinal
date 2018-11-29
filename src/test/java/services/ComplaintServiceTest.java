
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Complaint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;

	private UtilitiesService	utilitiesService;


	@Test
	public void createTest() {
		final Complaint complaint = this.complaintService.create();
		Assert.notNull(complaint);
	}

	/*
	 * @Test
	 * public void saveTest() {
	 * final Complaint complaint = this.complaintService.findOne(1408);
	 * Assert.notNull(complaint);
	 * complaint.setDescription("Actualizamos la descripcion");
	 * this.complaintService.save(complaint);
	 * Assert.isTrue(this.complaintService.findOne(1408).getDescription().equals("Actualizamos la descripcion"));
	 * }
	 * 
	 * @Test
	 * public void findOneTest() {
	 * final Complaint complaint = this.complaintService.findOne(1408);
	 * Assert.notNull(complaint);
	 * }
	 * 
	 * @Test
	 * public void findAll() {
	 * final Collection<Complaint> complaints = this.complaintService.findAll();
	 * Assert.notEmpty(complaints);
	 * }
	 */
}
