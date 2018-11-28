package services;

import domain.ProfessionalRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest{


	@Autowired
	private ProfessionalRecordService professionalRecordService;

	@Test
	public void testSaveProfessionalRecord() {

		ProfessionalRecord professionalRecord = professionalRecordService.create();

		professionalRecordService.save(professionalRecord);



	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteProfessionalRecord() {
		ProfessionalRecord deleted;
		Collection<ProfessionalRecord> prs;

		deleted = professionalRecordService.findOne(274);
		professionalRecordService.delete(deleted);
		prs = professionalRecordService.findAll();
		Assert.isTrue(!prs.contains(deleted));

	}

}
