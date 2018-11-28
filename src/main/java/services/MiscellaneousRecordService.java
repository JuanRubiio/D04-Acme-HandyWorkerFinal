package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {
	
	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;
	
	public MiscellaneousRecordService(){
		super();
	}
	
	public MiscellaneousRecord create(){
		MiscellaneousRecord res;
		
		res = new MiscellaneousRecord();
		
		return res;
	}
	
	public Collection<MiscellaneousRecord> findAll(){
		Collection<MiscellaneousRecord> result;
		
		Assert.notNull(miscellaneousRecordRepository);
		
		result = miscellaneousRecordRepository.findAll();
		
		Assert.notNull(result);
		
		return result;	
	}
	
	public MiscellaneousRecord findOne(final Integer miscellaneousRecordId) {

		MiscellaneousRecord result;

		Assert.notNull(miscellaneousRecordId);

		result = miscellaneousRecordRepository.findOne(miscellaneousRecordId);

		Assert.notNull(result);

		return result;

	}
	public MiscellaneousRecord save(final MiscellaneousRecord miscellaneousRecord){
		MiscellaneousRecord result;
		
		Assert.notNull(miscellaneousRecord);
		
		result = miscellaneousRecordRepository.save(miscellaneousRecord);
			
		return result;
	}
	
	public void delete(final MiscellaneousRecord miscellaneousRecord){
		Assert.notNull(miscellaneousRecord);
		
		miscellaneousRecordRepository.delete(miscellaneousRecord);
		
	}
}
