
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService	actorService;


	@Test
	public void createTest() {
		super.authenticate("administrator1");
		final Message mes = this.messageService.create();

		Assert.notNull(mes);

	}

	@Test
	public void saveTest() {
		super.authenticate("administrator1");
		final Message mes = this.messageService.create();

		Assert.notNull(mes);
		final Actor act = this.actorService.findOne(1308);
		mes.setRecipient(act);
		mes.setSubject("Hola");
		mes.setBody("amigos");
		mes.setPriority("LOW");

		this.messageService.save(mes);

	}

	//	@Test
	//	public void broadcastTest() {
	//		super.authenticate("administrator1");
	//
	//		final Message mes = this.messageService.create();
	//
	//		Assert.notNull(mes);
	//
	//		mes.setSubject("Hola");
	//		mes.setBody("amigos");
	//		mes.setPriority("LOW");
	//		this.messageService.broadcast(mes);
	//
	//	}

}
