
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
	public void testCreateSponsor() {

		Sponsor sponsor, saved, recuperado;
		Collection<Sponsor> todos;
		sponsor = this.sponsorService.create();
		sponsor.setName("Alfonso");
		sponsor.setSurname("Ramirez");
		sponsor.setAddress("C/ Patriarca de la humanidad n12");
		sponsor.setEmail("emailsponsor@gmail.com");
		sponsor.setPhone("674590132");
		saved = this.sponsorService.save(sponsor);
		todos = this.sponsorService.findAll();
		recuperado = this.sponsorService.findOne(saved.getId());
		Assert.isTrue(todos.contains(recuperado));
		Assert.isTrue(recuperado.getName() == "Alfonso");
	}

	@Test
	public void testUpdateSponsor() {

		final Sponsor sponsor;
		Sponsor saved, recuperado;
		sponsor = this.sponsorService.findOne(1353);
		sponsor.setName("Cosculluela");
		saved = this.sponsorService.save(sponsor);
		recuperado = this.sponsorService.findOne(saved.getId());
		Assert.isTrue(recuperado.getName() == "Cosculluela");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteSponsor() {

		Sponsor s1;

		s1 = this.sponsorService.findOne(1358);
		this.sponsorService.delete(s1);
		this.sponsorService.findOne(1358);

	}

	@Test
	public void testFindAllSponsor() {

		final Collection<Sponsor> todos = this.sponsorService.findAll();
		Assert.isTrue(todos.size() == 2);
	}

	@Test
	public void testFindOneSponsor() {

		final Sponsor recuperado = this.sponsorService.findOne(1353);
		Assert.isTrue(recuperado.getName().equals("Antonio"));

	}
}
