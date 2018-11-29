
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CreditCardServiceTest extends AbstractTest {

	@Autowired
	private CreditCardService	creditCardService;


	@Test
	public void createTest() {
		CreditCard creditCard = new CreditCard();
		creditCard = this.creditCardService.create();
		Assert.notNull(creditCard);
	}

	@Test
	public void saveTest() {
		CreditCard creditCard = new CreditCard();
		creditCard = this.creditCardService.findOne(1372);
		creditCard.setBrandName("MASTER");
		creditCard.setHolderName("Holder Name");
		creditCard.setExpiryMonth(9);
		creditCard.setExpiryYear(2020);
		creditCard.setCvv(100);
		creditCard.setNumber("1111111111111111");
		this.creditCardService.save(creditCard);
		Assert.isTrue(this.creditCardService.findOne(1372).getBrandName() == "MASTER");
	}

	@Test
	public void deleteTest() {
		CreditCard creditCard = new CreditCard();
		creditCard = this.creditCardService.findOne(1372);
		this.creditCardService.delete(creditCard);
		Assert.isTrue(!(this.creditCardService.findAll().contains(creditCard)));
	}

}
