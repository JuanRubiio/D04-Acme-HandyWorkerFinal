
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
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TutorialServiceTest extends AbstractTest {

	@Autowired
	private TutorialService	tutorialService;
	private ActorService	actorService;


	@Test
	public void createTest() {
		super.authenticate("handyWorker1");
		Tutorial tutorial = new Tutorial();
		HandyWorker handyWorker = new HandyWorker();
		handyWorker = (HandyWorker) this.actorService.getPrincipal();
		handyWorker.setMark("Pepito chapuza");

		tutorial = this.tutorialService.create(handyWorker);
		Assert.notNull(tutorial);
	}

}
