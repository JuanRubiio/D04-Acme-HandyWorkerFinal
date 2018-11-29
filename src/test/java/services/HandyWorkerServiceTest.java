
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

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


	@Test
	public void testSaveHandyWorker() {

		final HandyWorker handyWorker;
		HandyWorker saved, recuperado;
		handyWorker = this.handyWorkerService.create();
		handyWorker.setAddress("adres");
		saved = this.handyWorkerService.save(handyWorker);
		recuperado = this.handyWorkerService.findOne(saved.getId());
		Assert.isTrue(recuperado.getAddress() == "adres");
	}

}
