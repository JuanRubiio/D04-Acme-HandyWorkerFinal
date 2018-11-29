
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import security.Authority;
import domain.Actor;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	@Autowired
	private ConfigurationRepository	configurationRepository;

	@Autowired
	private ActorService			actorService;


	public Collection<Configuration> findAll() {
		Collection<Configuration> result;

		result = this.configurationRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public Configuration findOne(final Integer configurationId) {
		Configuration result;

		Assert.notNull(configurationId);

		result = this.configurationRepository.findOne(configurationId);

		Assert.notNull(result);

		return result;

	}

	public Configuration save(final Configuration configuration) {
		Configuration result;

		Assert.notNull(configuration);
		final Actor actor = this.actorService.getPrincipal();
		final Collection<Authority> autorities = actor.getUserAccount().getAuthorities();
		final ArrayList<String> listAuth = new ArrayList<String>();

		if (!autorities.isEmpty())
			for (final Authority au : autorities)
				listAuth.add(au.getAuthority());

		Assert.isTrue(listAuth.contains("ADMIN"));

		result = this.configurationRepository.save(configuration);

		Assert.notNull(result);

		return result;
	}

	public List<String> getPositiveWords() {
		final String positiveWords;
		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		positiveWords = configlate.getPositiveSpanishWords() + configlate.getPositiveEnglishWords();

		final List<String> list = Arrays.asList(positiveWords.split(";"));

		return list;
	}

	public List<String> getNegativeWords() {
		final String negativeWords;
		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		negativeWords = configlate.getNegativeSpanishWords() + configlate.getNegativeEnglishWords();

		final List<String> list = Arrays.asList(negativeWords.split(";"));

		return list;
	}

}
