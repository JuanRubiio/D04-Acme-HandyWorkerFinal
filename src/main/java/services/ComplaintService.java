
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import repositories.UtilitiesRepository;
import domain.Complaint;

@Service
@Transactional
public class ComplaintService {

	//Managed repo
	@Autowired
	private ComplaintRepository	complaintRepository;

	UtilitiesRepository			utilitiesRepository;
	UtilitiesService			utilitiesService;


	//Supporting services
	public Complaint create() {
		final Complaint res;
		res = new Complaint();
		res.setMoment(new Date());
		res.setTicker("123456-AAAA");

		return res;
	}

	public Complaint save(final Complaint complaint) {
		Complaint res = new Complaint();
		Assert.notNull(complaint);
		Assert.notNull(complaint.getTicker());
		Assert.isTrue(complaint.getTicker().matches("\\d{6}-[A-Z]{4}"));
		Assert.notNull(complaint.getDescription());

		res = this.complaintRepository.save(complaint);
		Assert.notNull(res);

		return res;

	}
	public Complaint findOne(final Integer complaintId) {
		Complaint res;
		Assert.notNull(complaintId);
		res = this.complaintRepository.findOne(complaintId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Complaint> findAll() {
		Collection<Complaint> res;
		res = this.complaintRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	private Integer obtieneIdQuejaSinReferee() {
		final List<Integer> complaintsConReferee = new ArrayList<Integer>();
		final List<Integer> complaintsDelSistema = new ArrayList<Integer>();
		int res = 0;

		complaintsConReferee.addAll(this.complaintRepository.idQuejasConReferee());
		complaintsDelSistema.addAll(this.complaintRepository.idTodasLasQuejas());

		complaintsDelSistema.removeAll(complaintsConReferee);
		res = complaintsDelSistema.get(0);

		return res;
	}

	public Complaint obtieneQuejaSinReferee() {
		final Complaint complaint = this.complaintRepository.findOne(this.obtieneIdQuejaSinReferee());
		Assert.notNull(complaint);
		return complaint;
	}
}
