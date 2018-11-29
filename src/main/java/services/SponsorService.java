
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;
	@Autowired
	private MessageBoxService	messageboxService;


	public Sponsor create() {
		Sponsor res;

		res = new Sponsor();
		final UserAccount userAccount = new UserAccount();
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		res.setUserAccount(userAccount);
		//this.messageboxService.addDefaultMessageBoxs(res);
		return res;
	}

	public Collection<Sponsor> findAll() {
		Collection<Sponsor> result;

		Assert.notNull(this.sponsorRepository);

		result = this.sponsorRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public Sponsor findOne(final Integer sponsorId) {
		Sponsor result;

		Assert.notNull(this.sponsorRepository);

		result = this.sponsorRepository.findOne(sponsorId);

		Assert.notNull(result);

		return result;

	}

	public Sponsor save(final Sponsor sponsor) {
		Sponsor result;

		Assert.notNull(sponsor);
		result = this.sponsorRepository.save(sponsor);
		return result;

	}

	public Sponsor getPrincipal() {

		final Sponsor result = this.sponsorRepository.getPrincipal(LoginService.getPrincipal().getId());
		Assert.notNull(result);

		return result;
	}

}
