package services;

import domain.EndorserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.EndorserRecordRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class EndorserRecordService {
	
	@Autowired
	private EndorserRecordRepository endorserRecordRepository;
	
	public EndorserRecordService(){
		super();
	}
	
	public EndorserRecord create(){
		EndorserRecord result;
		
		result = new EndorserRecord();
		
		return result;
	}
	
	public Collection<EndorserRecord> findAll(){
		Collection<EndorserRecord> result;

		
		result = endorserRecordRepository.findAll();
		
		Assert.notNull(result);
		
		return result;
	}
	
	public EndorserRecord findOne(final Integer endorserRecordId){
		
		EndorserRecord result;
		
		Assert.notNull(endorserRecordId);
		
		result = endorserRecordRepository.findOne(endorserRecordId);
		
		Assert.notNull(result);
		
		return result;
		
		
	}
	
	public EndorserRecord save(final EndorserRecord endorserRecord){
		EndorserRecord result; 
		
		Assert.notNull(endorserRecord);
		
		result = endorserRecordRepository.save(endorserRecord);

		Assert.notNull(result);
		
		return result;
	}
	
	public void delete(final EndorserRecord endorserRecord){
		Assert.notNull(endorserRecord);
		
		endorserRecordRepository.delete(endorserRecord);
	}
		
}
