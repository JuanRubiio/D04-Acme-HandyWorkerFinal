package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.EducationalRecord;
import domain.PersonalRecord;


@Service
@Transactional
public class PersonalRecordService {
	
	@Autowired
	private PersonalRecordRepository personalRecordRepository;
	
	public PersonalRecordService(){
		super();
	}
	
	public PersonalRecord create(){
		PersonalRecord res;
		
		res= new PersonalRecord();
		
		return res;
		
	}
	
	public Collection<PersonalRecord> findAll(){
		Collection<PersonalRecord> result;
		
		Assert.notNull(personalRecordRepository);
		
		result = personalRecordRepository.findAll();
		
		Assert.notNull(result);
		
		return result;
		
	}
	public PersonalRecord save(final PersonalRecord personalRecord) {
		PersonalRecord result;

		Assert.notNull(personalRecord);

		result = personalRecordRepository.save(personalRecord);
		return result;

	}

	public void delete(final PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		personalRecordRepository.delete(personalRecord);
	}
	
	public PersonalRecord findOne(final Integer personalRecordId) {
		PersonalRecord result;

		Assert.notNull(personalRecordId);

		result = personalRecordRepository.findOne(personalRecordId);

		Assert.notNull(result);

		return result;

	}

}
