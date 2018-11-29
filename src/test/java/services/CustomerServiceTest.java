
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	@Autowired
	private CustomerService	customerService;


	@Test
	public void createTest() {
		Customer customer = new Customer();
		customer = this.customerService.create();
		Assert.notNull(customer);
	}
	@Test
	public void saveTest() {
		Customer customer = new Customer();
		customer.setName("Manolito");
		customer.setSurname("Perez");
		customer.setEmail("manolito@correo.es");
		customer.setPhoto("www.urldelafoto.com/mifoto.php");
		customer = this.customerService.save(customer);
		Assert.notNull(customer);

	}
}
