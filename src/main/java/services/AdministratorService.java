
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;


	//	@Autowired
	//private MessageBoxService		messageBoxService;

	public Administrator create() {
		Administrator res;

		res = new Administrator();
		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);
		//this.messageBoxService.addDefaultMessageBoxs(res);

		return res;
	}

	public Administrator save(final Administrator administrator) {
		Administrator res;
		Assert.notNull(administrator);
		res = this.administratorRepository.save(administrator);
		Assert.notNull(res);
		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;

		res = this.administratorRepository.findAll();

		Assert.notNull(res);

		return res;
	}

	public Administrator findOne(final Integer administratorId) {
		Administrator res;

		Assert.notNull(administratorId);

		res = this.administratorRepository.findOne(administratorId);

		Assert.notNull(res);
		return res;
	}

	//Queries del dashboard
	public List<Object> query1() {
		return this.administratorRepository.query1();
	}
	public List<Object> query2() {
		return this.administratorRepository.query2();
	}
	public List<Object> query3() {
		return this.administratorRepository.query3();
	}
	public List<Object> query4() {
		return this.administratorRepository.query4();
	}
	public Double query5() {
		return this.administratorRepository.query5();
	}
	public Double query6() {
		return this.administratorRepository.query6();
	}
	public HandyWorker query7() {
		return this.administratorRepository.query7();
	}
	public Double query8() {
		return this.administratorRepository.query8();
	}
	public Customer query9() {
		return this.administratorRepository.query9();
	}
	public Double query10() {
		return this.administratorRepository.query10();
	}
	public List<Object> query11() {
		return this.administratorRepository.query11();
	}
	public List<Object> query12() {
		return this.administratorRepository.query12();
	}
	public Double query13() {
		return this.administratorRepository.query13();
	}
	public Customer query14() {
		return this.administratorRepository.query14();
	}
	public HandyWorker query15() {
		return this.administratorRepository.query15();
	}
}
