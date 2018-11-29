
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class HandyWorkerServiceTest extends AbstractTest {

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private MessageBoxService	messageboxService;


	@Test
	public void testSaveHandyWorker() {

		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setAddress("adres");

		this.handyWorkerService.save(handyWorker);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteHandyWorker() {

		final HandyWorker handyWorker = this.handyWorkerService.findOne(1308);

		this.handyWorkerService.delete(handyWorker);
		this.handyWorkerService.findOne(1308);
		super.authenticate(null);

	}

	//	@Test
	//	public void addMessageBox() {
	//		final HandyWorker handyWorker = this.handyWorkerService.create();
	//		handyWorker.setAddress("adres");
	//
	//		final HandyWorker handy = this.handyWorkerService.save(handyWorker);
	//
	//		this.messageboxService.addDefaultMessageBoxs(handy);
	//
	//		handy.setMessageBoxes(this.messageboxService.getMessageBoxsByActor(handy.getId()));
	//		final HandyWorker res = this.handyWorkerService.findOne(handy.getId());
	//	}

}
