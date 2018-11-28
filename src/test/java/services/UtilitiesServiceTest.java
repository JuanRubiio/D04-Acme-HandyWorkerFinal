
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class UtilitiesServiceTest extends AbstractTest {

	@Autowired
	private UtilitiesService	utilitiesService;


	@Test
	public void testGenerateTicker() {
		final String t = this.utilitiesService.generateTicker();

	}

	@Test
	public void testCheckSpam() {

		final String s = "Voy a tener sexo toda la noche";
		final Boolean b = this.utilitiesService.checkSpam(s);

	}
}
