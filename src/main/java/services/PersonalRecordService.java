
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	@Autowired
	private PersonalRecordRepository	personalRecordRepository;


	public PersonalRecord create() {
		PersonalRecord res;

		res = new PersonalRecord();

		return res;

	}

	public Collection<PersonalRecord> findAll() {
		Collection<PersonalRecord> result;

		Assert.notNull(this.personalRecordRepository);

		result = this.personalRecordRepository.findAll();

		Assert.notNull(result);

		return result;

	}
	public PersonalRecord save(final PersonalRecord personalRecord) {
		PersonalRecord result;

		Assert.notNull(personalRecord);

		result = this.personalRecordRepository.save(personalRecord);
		return result;

	}

	public void delete(final PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		this.personalRecordRepository.delete(personalRecord);
	}

	public PersonalRecord findOne(final Integer personalRecordId) {
		PersonalRecord result;

		Assert.notNull(personalRecordId);

		result = this.personalRecordRepository.findOne(personalRecordId);

		Assert.notNull(result);

		return result;

	}

}
