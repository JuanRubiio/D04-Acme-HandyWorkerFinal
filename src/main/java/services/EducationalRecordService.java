package services;

import domain.Actor;
import domain.EducationalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.EducationalRecordRepository;
import security.Authority;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class EducationalRecordService {

	@Autowired
	private EducationalRecordRepository educationalRecordRepository;
	
	@Autowired
	private ActorService actorService;
	
	public EducationalRecordService(){
		super();
	}
	
	public EducationalRecord create(){
		EducationalRecord result;
		
		result = new EducationalRecord();
		
		return result;
	}
	
	public Collection<EducationalRecord> findAll(){
		Collection<EducationalRecord> result;
		
		Assert.notNull(educationalRecordRepository);
		
		result = educationalRecordRepository.findAll();
		
		Assert.notNull(result);
		
		return result;
		
	}
	
	
	public EducationalRecord findOne(final Integer educationalRecordId){
		EducationalRecord result;
		
		Assert.notNull(educationalRecordId);
		
		result = educationalRecordRepository.findOne(educationalRecordId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public EducationalRecord save(final EducationalRecord educationalRecord){
		EducationalRecord result;
		
		Assert.notNull(educationalRecord);
		
		final Actor a = this.actorService.getPrincipal();
		Assert.isTrue(a.getUserAccount().getAuthorities().contains(Authority.HANDYWORKER));
		
		result = educationalRecordRepository.save(educationalRecord);

		Assert.notNull(result);
		
		return result;
		
	}
	
	public void delete(final EducationalRecord educationalRecord){
		Assert.notNull(educationalRecord);

		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> autorities = a.getUserAccount().getAuthorities();
		
		final ArrayList<String> listAuth = new ArrayList<String>();

		if(!autorities.isEmpty()){
			for(final Authority au: autorities)
				listAuth.add(au.getAuthority());
		}
		
		Assert.isTrue(listAuth.contains("HANDYWORKER"));
		
		educationalRecordRepository.delete(educationalRecord);	
	}
}

