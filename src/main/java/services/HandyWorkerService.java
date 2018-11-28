
 package services;

 import java.util.ArrayList;
import java.util.Collection;
 import java.util.List;

 import javax.transaction.Transactional;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

 import repositories.HandyWorkerRepository;
 import security.Authority;
 import security.UserAccount;
import domain.HandyWorker;

 @Service
 @Transactional
 public class HandyWorkerService {

 @Autowired
 private HandyWorkerRepository handyworkerRepository;

 @Autowired
 private MessageBoxService messageboxService;

 @Autowired
 private CurriculumService curriculumService;


 public HandyWorker create() {
	 HandyWorker res;
	
	 res = new HandyWorker();
	 final UserAccount userAccount = new UserAccount();
	 final List<Authority> authorities = new ArrayList<Authority>();
	 final Authority authority = new Authority();
	 authority.setAuthority(Authority.HANDYWORKER);
	 authorities.add(authority);
	 userAccount.setAuthorities(authorities);
	
	 res.setUserAccount(userAccount);
	 this.messageboxService.addDefaultMessageBoxs(res);
	
	 res.setCurriculum(this.curriculumService.create());
	
	 return res;
 }
 
 public Collection<HandyWorker> findAll(){
	 Collection<HandyWorker> res;
	 res = this.handyworkerRepository.findAll();
	 Assert.notNull(res);
	 return res;
 }
 
 public HandyWorker findOne(final Integer handyWorkerId){
	 HandyWorker res;
	 Assert.notNull(res);
	 res = this.handyworkerRepository.findOne(handyWorkerId);
	 return res;
 }
 
 public void save(final HandyWorker handyWorker){
	 Assert.notNull(handyWorker);
	 this.handyworkerRepository.save(handyWorker);
 }
 
 public void delete(final HandyWorker handyWorker){
	 Assert.notNull(handyWorker);
	 this.handyworkerRepository.delete(handyWorker);
 }
}
