
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
import domain.CreditCard;
import domain.Sponsorship;
import domain.Tutorial;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorShipServiceTest extends AbstractTest {

	@Autowired
	private SponsorshipService	sponsorshipService;
	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private CreditCardService	ccService;


	@Test
	public void testfindByTutorialID() {

		super.authenticate("sponsor1");
		final Collection<Sponsorship> sponsorships;
		sponsorships = this.sponsorshipService.findByTutorialId(1415);
		Assert.isTrue(sponsorships.size() == 2);
		super.authenticate(null);
	}

	@Test
	public void testCrateSponsorShip() {

		super.authenticate("sponsor1");
		Sponsorship sponsorship, saved;
		final Collection<Sponsorship> sponsorships;
		final Tutorial t = this.tutorialService.findOne(1415);
		sponsorship = this.sponsorshipService.create(t);
		final CreditCard creditCard = this.ccService.findOne(1372);
		sponsorship.setCreditCard(creditCard);
		saved = this.sponsorshipService.save(sponsorship);
		Assert.notNull(saved);

	}

}
