package services;

import domain.ProfessionalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ProfessionalRecordRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository professionalRecordRepository;

	public ProfessionalRecordService() {
		super();
	}

	public ProfessionalRecord create() {
		ProfessionalRecord result;

		result = new ProfessionalRecord();

		return result;
	}

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> result;

		result = professionalRecordRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public ProfessionalRecord findOne(final Integer professionalRecordId) {

		ProfessionalRecord result;

		Assert.notNull(professionalRecordId);

		result = professionalRecordRepository.findOne(professionalRecordId);

		Assert.notNull(result);

		return result;

	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		ProfessionalRecord result;

		Assert.notNull(professionalRecord);

		result = professionalRecordRepository.save(professionalRecord);

		return result;
	}

	public void delete(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);

		professionalRecordRepository.delete(professionalRecord);
	}

}
