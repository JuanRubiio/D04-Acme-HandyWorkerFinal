
package services;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.NoteRepository;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	@Autowired
	private NoteService			noteService;

	@Autowired
	private NoteRepository		noteRepository;

	@Autowired
	private ReportService		reportService;

	@Autowired
	private ComplaintService	complaintService;

	//	@Test
	//	public void testSaveNote() {
	//		super.authenticate("referee1");
	//		final Complaint c = this.complaintService.create();
	//		final Report r = this.reportService.create(c);
	//		final Note note = this.noteService.create(r);
	//		final Note prueba = this.noteService.save(note);
	//		final Note notee = this.noteRepository.findOne(note.getId());
	//		Assert.notNull(notee);
	//		super.authenticate(null);

}
