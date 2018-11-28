
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import repositories.ReportRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Complaint;
import domain.Note;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private RefereeRepository	refereeRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private ComplaintRepository	complaintRepository;


	@Autowired
	public Report create(final Complaint c) {
		Report res;
		res = new Report();
		final Actor actor = this.actorService.getPrincipal();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("REFEREE"));
		final Integer idActor = actor.getId();
		final Referee referee = this.refereeRepository.findOne(idActor);
		res.setReferee(referee);
		res.setMoment(new Date());
		res.setComplaint(this.complaintRepository.findOne(c.getId();
		res.setDraft(false);
		return res;
	}
	public Report save(final Report report) {
		Report res;
		Assert.notNull(report);
		Assert.isTrue(report.getDraft());
		res = this.reportRepository.save(report);
		return res;
	}

	public void delete(final Report report) {
		Assert.notNull(report);
		Assert.isTrue(report.getDraft());
	}
}
