
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsor;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	@Autowired
	private SponsorService	sponsorService;


	@Test
	public void testCrateSponsor() {

		Sponsor sponsor, saved, recuperado;
		sponsor = this.sponsorService.create();
		sponsor.setName("Alfonso");
		saved = this.sponsorService.save(sponsor);
		recuperado = this.sponsorService.findOne(saved.getId());
		Assert.isTrue(recuperado.getName() == "Alfonso");

	}

}
