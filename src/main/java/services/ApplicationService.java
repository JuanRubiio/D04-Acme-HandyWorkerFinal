package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Application;
import domain.FixUpTask;


import repositories.ApplicationRepository;
import security.Authority;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private ActorService actorService;
	//¿Debería poner FixUpTaskService?
	
	public Application create(final FixUpTask fixUpTask){
		
		Application res = new Application();
		Assert.notNull(fixUpTask);
		res.setFixUpTask(fixUpTask);
		Actor actor = this.actorService.getPrincipal();
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(Authority.HANDYWORKER));
		res.setMoment(new Date());
		//¿Hace falta meter una CreditCard?, ya que es un atributo opcional
		return res;		
	}
	
	public Collection<Application> findAll(){
		Collection<Application> res;
		res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Application findOne(final Integer applicationId){
		Application res;
		Assert.notNull(applicationId);
		res = this.applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}
	
	public Application save(final Application application){
		Application res;
		Assert.notNull(application);
		res = this.applicationRepository.save(application);
		Assert.notNull(res);
		return res;
	}
	
	
}

