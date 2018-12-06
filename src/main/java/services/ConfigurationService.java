
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

	//MANAGE LIST OF NEGATIVE AND POSITIVE WORDS SERVICES

	public List<String> getPositiveWords() {
		final String positiveWords;
		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		positiveWords = configlate.getPositiveSpanishWords() + ";" + configlate.getPositiveEnglishWords();

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

		negativeWords = configlate.getNegativeSpanishWords() + ";" + configlate.getNegativeEnglishWords();

		final List<String> list = Arrays.asList(negativeWords.split(";"));

		return list;
	}

	public List<String> CreatePositiveWords() {

		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		Assert.isNull(configlate.getPositiveEnglishWords());
		Assert.isNull(configlate.getPositiveSpanishWords());

		final String spanishWords = "good;fantastic;excelent;great;amazing;terrific;beautiful";
		final String englishWords = "bueno;fantastico;excelente;increible;estupendo;hermoso";

		configlate.setPositiveSpanishWords(spanishWords);

		configlate.setPositiveEnglishWords(englishWords);

		final String positiveWords = configlate.getPositiveSpanishWords() + ";" + configlate.getPositiveEnglishWords();

		final List<String> list = Arrays.asList(positiveWords.split(";"));

		return list;
	}

	public List<String> CreateNegativeWords() {

		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		Assert.isNull(configlate.getNegativeEnglishWords());
		Assert.isNull(configlate.getNegativeSpanishWords());

		final String englishWords = "not;bad;horrible;average;disaster";
		final String spanishWords = "no;malo;horrible;mediocre;desastre";

		configlate.setNegativeEnglishWords(englishWords);
		configlate.setNegativeSpanishWords(spanishWords);

		final String NegativeWords = configlate.getNegativeSpanishWords() + ";" + configlate.getNegativeEnglishWords();

		final List<String> list = Arrays.asList(NegativeWords.split(";"));

		return list;
	}

	public List<String> UpdatePositiveWords(final String spanishWord, final String englishWord) {

		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		final String positiveEnglish = configlate.getPositiveSpanishWords();
		final String positiveSpanish = configlate.getPositiveEnglishWords();

		positiveEnglish.concat(";" + englishWord);
		positiveSpanish.concat(";" + spanishWord);

		final String positiveWords = configlate.getPositiveSpanishWords() + ";" + configlate.getPositiveEnglishWords();

		final List<String> list = Arrays.asList(positiveWords.split(";"));

		return list;
	}

	public List<String> UpdateNegativeWords(final String spanishWord, final String englishWord) {

		Configuration configlate = new Configuration();
		final Collection<Configuration> configurations = this.configurationRepository.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		final String positiveEnglish = configlate.getNegativeSpanishWords();
		final String positiveSpanish = configlate.getNegativeEnglishWords();

		positiveEnglish.concat(";" + englishWord);
		positiveSpanish.concat(";" + spanishWord);

		final String positiveWords = configlate.getNegativeSpanishWords() + ";" + configlate.getNegativeEnglishWords();

		final List<String> list = Arrays.asList(positiveWords.split(";"));

		return list;
	}

	public void deletePositiveWords(final Configuration c) {
		Assert.notNull(c);

		c.setPositiveEnglishWords(null);
		c.setPositiveSpanishWords(null);
	}

	public void deleteNegativeWords(final Configuration c) {
		Assert.notNull(c);

		c.setNegativeEnglishWords(null);
		c.setNegativeSpanishWords(null);
	}

}
