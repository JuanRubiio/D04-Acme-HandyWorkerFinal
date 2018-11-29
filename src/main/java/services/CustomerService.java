
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	//Managed repo
	@Autowired
	private CustomerRepository	customerRepository;

	@Autowired
	private ActorService		actorService;


	//Supporting services
	public Customer create() {
		Customer res;
		res = new Customer();
		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		res.setUserAccount(userAccount);

		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authoritiesa = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authoritiesa)
				listAuth.add(au.getAuthority());
		Assert.notNull(res);
		//this.messageboxService.addDefaultMessageBoxs(res);
		return res;
	}

	public Customer findOne(final Integer customerId) {
		final Customer res;
		Assert.notNull(customerId);
		res = this.customerRepository.findOne(customerId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Customer> findAll() {
		final Collection<Customer> res;
		res = this.customerRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Customer save(final Customer customer) {
		Customer res;
		Assert.notNull(customer);
		//Assert.isTrue(customer.getName() != "");
		//Assert.isTrue(customer.getSurname() != "");
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());

		res = this.customerRepository.save(customer);
		Assert.notNull(res);
		return res;

	}

	public void delete(final Customer customer) {
		Assert.notNull(customer);
		this.customerRepository.delete(customer);
	}
}
