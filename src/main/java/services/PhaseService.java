package services;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Application;
import domain.Phase;

import repositories.PhaseRepository;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository phaseRepository;
	//¿Poner applicationService?
	
	
	public Phase create(final Application application){
		Phase res = new Phase();
		Assert.notNull(application);
		//¿Debe ser application aceptada?
		
		//La intención es parsear el TimeStamp en date
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date startMoment = new Date(stamp.getTime());
		res.setStartMoment(startMoment);
		
		return res;
	}
	
	public Collection<Phase> findAll(){
		Collection<Phase>res;
		res = this.phaseRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Phase findOne(final Integer phaseId){
		Phase res;
		Assert.notNull(phaseId);
		res = this.phaseRepository.findOne(phaseId);
		Assert.notNull(res);
		return res;
	}
	
	public void save(final Phase phase){
		Assert.notNull(phase);
		this.phaseRepository.save(phase);
	}
	
	public void delete(final Phase phase){
		Assert.notNull(phase);
		this.phaseRepository.save(phase);
	}
}

