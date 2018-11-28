package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Application;
import domain.FixUpTask;
import domain.Phase;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"})
@Transactional
public class PhaseTest extends AbstractTest{

	@Autowired
	private PhaseService phaseService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private FixUpTaskService fixUpTaskService;
	
	@Test
	public void testSavePhase(){
		super.authenticate("handyWorker1");
		final FixUpTask fixUpTask = this.fixUpTaskService.create();
		final Application application = this.applicationService.create(fixUpTask);	
		final Phase phase = this.phaseService.create(application);
		this.phaseService.save(phase);
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeletePhase() {
		super.authenticate("handyWorker1");
		final Phase phase = this.phaseService.findOne(1423);

		this.phaseService.delete(phase);
		this.phaseService.findOne(1423);
		super.authenticate(null);
	}
	
}
