
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
	private CustomerService		customerService;


	@Test
	public void testFindByWriteFromComments() {

		final Collection<String> coleccionComentarios;
		coleccionComentarios = this.endorsementService.findByWriteFromComments(1338);
		Assert.isTrue(coleccionComentarios.size() == 1);
	}

	@Test
	public void testFindByWriteToComments() {

		final Collection<String> coleccionComentarios;
		coleccionComentarios = this.endorsementService.findByWriteToComments(1313);
		Assert.isTrue(coleccionComentarios.size() == 1);
	}

	@Test
	public void testFindOneEndorsement() {

		final Endorsement endorsement;
		endorsement = this.endorsementService.findOne(1417);
		Assert.isTrue(endorsement.getComments().equals("Poco eficiente"));
	}

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

	@Test
	public void testUpdateEndorsement() {

		super.authenticate("handyWorker1");
		Endorsement endorsement, saved, recuperado;
		endorsement = this.endorsementService.findOne(1416);
		endorsement.setComments("la vida es bella");
		saved = this.endorsementService.save(endorsement);
		recuperado = this.endorsementService.findOne(saved.getId());
		Assert.isTrue(recuperado.getComments().equals("la vida es bella"));
		super.authenticate(null);
	}

	@Test
	public void testDeleteEndorsement() {

		super.authenticate("handyWorker1");
		final Endorsement endorsement;
		Collection<Endorsement> todos;
		endorsement = this.endorsementService.findOne(1417);
		this.endorsementService.delete(endorsement);
		todos = this.endorsementService.findAll();
		Assert.isTrue(!todos.contains(endorsement));
		super.authenticate(null);
	}

}
