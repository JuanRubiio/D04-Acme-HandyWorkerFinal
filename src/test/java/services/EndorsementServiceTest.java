
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
import domain.Customer;
import domain.Endorsement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorsementServiceTest extends AbstractTest {

	@Autowired
	private EndorsementService	endorsementService;
	@Autowired
	private EndorserService		endorserService;
	@Autowired
	private CustomerService		customerService;


	@Test
	public void testCrateEndorsement() {

		super.authenticate("customer1");
		Endorsement endorsement, saved;
		final Collection<Endorsement> endorsements;
		final Customer c = this.customerService.findOne(1343);
		endorsement = this.endorsementService.create(c);
		endorsement.setComments("comentarios prueba");
		saved = this.endorsementService.save(endorsement);
		endorsements = this.endorsementService.findAll();
		Assert.isTrue(endorsements.contains(saved));
		super.authenticate(null);
	}

}
