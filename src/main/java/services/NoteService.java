
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.Authority;
import domain.Actor;
import domain.Note;
import domain.Report;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository	noteRepository;

	@Autowired
	ReportService			reportService;

	@Autowired
	ActorService			actorService;


	@Autowired
	public Note create(final Report report) {
		Note res;
		res = new Note();
		res.setMoment(new Date());
		res.setReport(this.reportService.findOne(report.getId()));
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("REFEREE") || listAuth.contains("CUSTOMER") || listAuth.contains("HANDYWORKER"));
		return res;
	}
	public Note save(final Note note) {
		Note res;
		Assert.notNull(note);
		res = this.noteRepository.save(note);
		return res;
	}

	public Collection<Note> findAll() {
		Collection<Note> res;

		res = this.noteRepository.findAll();

		Assert.notNull(res);

		return res;
	}

	public Note findOne(final Integer noteId) {
		Note res;

		Assert.notNull(noteId);

		res = this.noteRepository.findOne(noteId);

		Assert.notNull(res);
		return res;
	}

}
