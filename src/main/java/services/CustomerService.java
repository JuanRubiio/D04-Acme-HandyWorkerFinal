
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
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;


	public Customer create() {
		Customer result;
		//FALTA RELLENAR USER ACOUNT
		result = new Customer();
		final UserAccount cuenta = new UserAccount();
		final List<Authority> autoridades = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);
		autoridades.add(authority);
		cuenta.setAuthorities(autoridades);

		result.setUserAccount(cuenta);

		return result;
	}

	public Customer findOne(final Integer CustomerId) {

		Customer result;

		result = this.customerRepository.findOne(CustomerId);

		Assert.notNull(result);

		return result;

	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = this.customerRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Customer save(final Customer Customer) {
		Customer result;

		Assert.notNull(Customer);
		result = this.customerRepository.save(Customer);
		return result;

	}

}
