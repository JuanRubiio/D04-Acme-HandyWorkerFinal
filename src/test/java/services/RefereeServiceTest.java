
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import utilities.AbstractTest;
import domain.Referee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RefereeServiceTest extends AbstractTest {

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private RefereeRepository	refereeRepository;


	@Test
	public void testSaveReferee() {
		super.authenticate("administrator1");
		final Referee referee = this.refereeService.create();
		final Referee prueba = this.refereeService.save(referee);
		final Referee refereee = this.refereeRepository.findOne(prueba.getId());
		Assert.notNull(refereee);

		super.authenticate(null);
	}
}
