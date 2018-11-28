package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Application;
import domain.FixUpTask;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"})
@Transactional
public class ApplicationTest extends AbstractTest{

	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private FixUpTaskService fixUpTaskService;
	
	@Test
	public void testSaveApplication(){
		super.authenticate("handyWorker1");
		
		final FixUpTask fixUpTask = this.fixUpTaskService.create();
		final Application application = this.applicationService.create(fixUpTask);
		this.applicationService.save(application);
	}
	
}
