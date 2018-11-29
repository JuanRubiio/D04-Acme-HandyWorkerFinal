package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.FixUpTask;


import repositories.FixUpTaskRepository;
import security.Authority;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository fixUpTaskRepository;
	@Autowired
	private ActorService actorService;
	
	public FixUpTask create(){
		FixUpTask res = new FixUpTask();
		Actor actor = this.actorService.getPrincipal();
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(Authority.CUSTOMER));
		res.setMoment(new Date());
		
		return res;
	}
	
	public Collection<FixUpTask> findAll(){
		Collection<FixUpTask> res;
		res=this.fixUpTaskRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public FixUpTask findOne(final Integer fixUpTaskId){
		FixUpTask res;
		Assert.notNull(fixUpTaskId);
		res=this.fixUpTaskRepository.findOne(fixUpTaskId);
		Assert.notNull(res);
		return res;
	}
	
	public FixUpTask save(final FixUpTask fixUpTask){
		FixUpTask res;
		Assert.notNull(fixUpTask);
		res = this.fixUpTaskRepository.save(fixUpTask);
		Assert.notNull(res);
		return res;
	}
	
	public void delete(final FixUpTask fixUpTask){
		Assert.notNull(fixUpTask);
		this.fixUpTaskRepository.delete(fixUpTask);
	}
	
}
