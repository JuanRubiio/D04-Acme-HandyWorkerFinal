
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.Authority;
import domain.Actor;
import domain.Complaint;
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
	private ActorService		actorService;

	@Autowired
	private ComplaintService	complaintService;


	@Autowired
	public Report create(final Complaint c) {
		Report res;
		res = new Report();
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("REFEREE"));

		res.setReferee((Referee) actor);
		res.setMoment(new Date());
		res.setComplaint(this.complaintService.findOne(c.getId()));
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

	public Collection<Report> findAll() {
		Collection<Report> res;

		res = this.reportRepository.findAll();

		Assert.notNull(res);

		return res;
	}

	public Report findOne(final Integer reportId) {
		Report res;

		Assert.notNull(reportId);

		res = this.reportRepository.findOne(reportId);

		Assert.notNull(res);
		return res;
	}
}
