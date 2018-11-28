package services;

import javax.transaction.Transactional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.HandyWorker;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class HandyWorkerTest extends AbstractTest{

	@Autowired
	private HandyWorkerService handyWorkerService;
	
	@Test
	public void testSaveHandyWorker(){
		super.authenticate("handyWorker1");
		final HandyWorker handyWorker  = this.handyWorkerService.create();
		this.handyWorkerService.save(handyWorker);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteHandyWorker(){
		super.authenticate("handyWorker1");
		final HandyWorker handyWorker = this.handyWorkerService.findOne(1308);
		
		this.handyWorkerService.delete(handyWorker);
		this.handyWorkerService.findOne(1308);
		super.authenticate(null);
		
	}
	
}
