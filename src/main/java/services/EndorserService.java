
package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
import security.LoginService;
import domain.Endorser;

@Service
@Transactional
public class EndorserService {

	@Autowired
	private EndorserRepository		endorserRepository;
	@Autowired
	private ConfigurationService	confService;
	@Autowired
	private EndorsementService		endorsementService;


	public Collection<Endorser> findAll() {
		Collection<Endorser> result;

		result = this.endorserRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public Endorser getPrincipal() {

		final Endorser result = this.endorserRepository.getPrincipal(LoginService.getPrincipal().getId());
		Assert.notNull(result);

		return result;
	}

	public Endorser findOne(final Integer EndorserId) {
		Endorser result;
		Assert.notNull(EndorserId);

		result = this.endorserRepository.findOne(EndorserId);

		Assert.notNull(result);
		return result;

	}

	public Integer getNumberOfPositiveWords(final int endorserId) {
		final Collection<String> receivedComments = this.endorsementService.findByWriteToComments(endorserId);
		final List<String> positiveWords = this.confService.getPositiveWords();
		Integer p = 0;
		for (final String comment : receivedComments) {
			final List<String> words = Arrays.asList(comment.split(" "));
			for (final String word : words)
				if (positiveWords.contains(word))
					p = p + 1;

		}
		return p;
	}
	public Integer getNumberOfNegativeWords(final int endorserId) {
		final Collection<String> sendedComments = this.endorsementService.findByWriteToComments(endorserId);
		final List<String> negativeWords = this.confService.getNegativeWords();
		Integer n = 0;
		for (final String comment : sendedComments) {
			final List<String> words = Arrays.asList(comment.split(" "));
			for (final String word : words)
				if (negativeWords.contains(word))
					n = n + 1;
		}
		return n;
	}

	public Double calculateScore(final int endorserId) {
		Double res = 0.0;
		final Integer p = this.getNumberOfPositiveWords(endorserId);
		final Integer n = this.getNumberOfNegativeWords(endorserId);
		res = (double) p - n;
		return res;
	}

}
