
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	@Autowired
	private AdministratorRepository	administratorRepository;
	@Autowired
	private AdministratorService	administratorService;


	@Test
	public void testSaveAdministrator() {
		super.authenticate("administrator1");
		final Administrator administrator = this.administratorService.create();
		final Administrator prueba = this.administratorService.save(administrator);
		final Administrator administratorr = this.administratorRepository.findOne(administrator.getId());
		Assert.notNull(administratorr);
		super.authenticate(null);
	}
}
