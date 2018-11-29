
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;


	public ProfessionalRecord create() {
		ProfessionalRecord result;

		result = new ProfessionalRecord();

		return result;
	}

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> result;

		result = this.professionalRecordRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public ProfessionalRecord findOne(final Integer professionalRecordId) {

		ProfessionalRecord result;

		Assert.notNull(professionalRecordId);

		result = this.professionalRecordRepository.findOne(professionalRecordId);

		Assert.notNull(result);

		return result;

	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		ProfessionalRecord result;

		Assert.notNull(professionalRecord);

		result = this.professionalRecordRepository.save(professionalRecord);

		return result;
	}

	public void delete(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);

		this.professionalRecordRepository.delete(professionalRecord);
	}

}
