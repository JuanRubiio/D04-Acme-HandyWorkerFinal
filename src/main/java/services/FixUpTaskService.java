package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

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
		res.setTicker(this.generateTicker());
		
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
	
	public void save(final FixUpTask fixUpTask){
		Assert.notNull(fixUpTask);
		this.fixUpTaskRepository.save(fixUpTask);
	}
	
	public void delete(final FixUpTask fixUpTask){
		Assert.notNull(fixUpTask);
		this.fixUpTaskRepository.delete(fixUpTask);
	}
	
	private String generateTicker() {
		String res;
		final Random rdn = new Random();
		final String[] a = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		String letra1 = a[rdn.nextInt(a.length - 1)];
		String letra2 = a[rdn.nextInt(a.length - 1)];
		String letra3 = a[rdn.nextInt(a.length - 1)];
		String letra4 = a[rdn.nextInt(a.length - 1)];
		String division = "-";
		String dia = Integer.toString(Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH));
		String mes = Integer.toString(Calendar.getInstance().get(
				Calendar.MONTH)+1);
		if(mes.length()==1){
			mes = "0"+mes;
		}
		String annio = Integer.toString(Calendar.getInstance().get(
				Calendar.YEAR)).substring(2);

		res =  annio + mes + dia + division + letra1 + letra2 + letra3 + letra4;

		FixUpTask fixUpTask = this.fixUpTaskRepository.findByTicker(res);
		if (fixUpTask != null)
			res = this.generateTicker();
		return res;

	}
	
}
