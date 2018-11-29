
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Referee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RefereeServiceTest extends AbstractTest {

	@Autowired
	private RefereeService	refereeService;


	@Test
	public void testSaveReferee() {
		super.authenticate("administrator1");
		final Referee referee;
		Referee saved, recuperado;
		referee = this.refereeService.create();
		referee.setName("jose");
		saved = this.refereeService.save(referee);
		recuperado = this.refereeService.findOne(saved.getId());
		Assert.isTrue(recuperado.getName() == "jose");
	}
}
