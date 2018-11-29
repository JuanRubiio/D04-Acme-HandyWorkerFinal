
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
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FixUpTaskServiceTest extends AbstractTest {

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@Test
	public void testSaveFixUpTask() {
		super.authenticate("customer3");

		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(1426);
		Assert.notNull(fixUpTask);
		fixUpTask.setAddress("calle prueba");
		this.fixUpTaskService.save(fixUpTask);
		Assert.isTrue(this.fixUpTaskService.findOne(1426).getAddress().equals("calle prueba"));
	}

	@Test
	public void findOneTest() {
		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(1426);
		Assert.notNull(fixUpTask);
	}

	@Test
	public void findAll() {
		final Collection<FixUpTask> fixUpTask = this.fixUpTaskService.findAll();
		Assert.notEmpty(fixUpTask);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFixUpTask() {
		super.authenticate("customer3");
		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(1426);

		this.fixUpTaskService.delete(fixUpTask);
		this.fixUpTaskService.findOne(1426);
		super.authenticate(null);
	}

}
