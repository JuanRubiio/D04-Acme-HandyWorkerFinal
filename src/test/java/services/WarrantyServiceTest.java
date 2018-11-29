
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
import domain.Warranty;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class WarrantyServiceTest extends AbstractTest {

	@Autowired
	private WarrantyService	warrantyService;


	@Test
	public void testFindAllWarranty() {

		super.authenticate("administrator1");
		final Collection<Warranty> todas = this.warrantyService.findAll();
		Assert.isTrue(todas.size() == 3);
		super.authenticate(null);
	}

	@Test
	public void testFindOneWarranty() {

		super.authenticate("administrator1");
		final Warranty recuperada = this.warrantyService.findOne(1421);
		Assert.isTrue(recuperada.getTitle() == "NORMAL");
		super.authenticate(null);
	}

	@Test
	public void testCrateWarranty() {

		super.authenticate("administrator1");
		Warranty warranty, saved;
		Collection<Warranty> warrantys;
		warranty = this.warrantyService.create();
		warranty.setLaws("law1,law2,law3");
		warranty.setTerms("terminos de prueba");
		warranty.setTitle("titulo prueba");
		saved = this.warrantyService.save(warranty);
		warrantys = this.warrantyService.findAll();
		Assert.isTrue(warrantys.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testUpdateWarranty() {

		super.authenticate("administrator1");
		Warranty warranty, saved, recuperado;
		warranty = this.warrantyService.findOne(1420);
		warranty.setTitle("URGENCIA");
		saved = this.warrantyService.save(warranty);
		recuperado = this.warrantyService.findOne(saved.getId());
		Assert.isTrue(recuperado.getTitle() == "URGENCIA");
		super.authenticate(null);
	}

	@Test
	public void testDeleteWarranty() {

		super.authenticate("administrator1");
		Warranty warranty;
		warranty = this.warrantyService.findOne(1420);
		this.warrantyService.delete(warranty);
		final Collection<Warranty> todas = this.warrantyService.findAll();
		Assert.isTrue(!todas.contains(warranty));
		super.authenticate(null);
	}

}
