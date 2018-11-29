
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import security.Authority;
import domain.Actor;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository	warrantyRepository;
	@Autowired
	private ActorService		actorService;


	public Warranty create() {
		Warranty result;
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("ADMIN"));
		result = new Warranty();
		final Boolean draft = true;
		result.setDraft(draft);
		return result;
	}

	public Collection<Warranty> findAll() {
		Collection<Warranty> result;

		result = this.warrantyRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Warranty findOne(final Integer WarrantyId) {
		Warranty result;

		Assert.notNull(WarrantyId);

		result = this.warrantyRepository.findOne(WarrantyId);

		Assert.notNull(result);

		return result;

	}

	public Warranty save(final Warranty warranty) {
		final Warranty result;
		Assert.notNull(warranty);
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("ADMIN"));
		Assert.notNull(warranty);
		Assert.isTrue(warranty.getDraft());
		result = this.warrantyRepository.save(warranty);
		return result;

	}

	public void delete(final Warranty Warranty) {
		final Actor a = this.actorService.getPrincipal();
		final Collection<Authority> authorities = a.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();
		if (!authorities.isEmpty())
			for (final Authority au : authorities)
				listAuth.add(au.getAuthority());
		Assert.isTrue(listAuth.contains("ADMIN"));
		Assert.notNull(Warranty);
		Assert.isTrue(Warranty.getDraft());
		this.warrantyRepository.delete(Warranty);
	}

}
